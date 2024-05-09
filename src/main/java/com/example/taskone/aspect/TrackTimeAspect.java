package com.example.taskone.aspect;


import com.example.taskone.model.Method;
import com.example.taskone.model.TimeExectuion;
import com.example.taskone.repository.MethodRepository;
import com.example.taskone.repository.TimeExecutionRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TrackTimeAspect {

  @Autowired
  private MethodRepository methodRepository;

  @Autowired
  private TimeExecutionRepository timeExecutionRepository;

  @Pointcut("@annotation(com.example.taskone.annotation.TrackTime)")
  public void timePointcut() {
  }

  @Around("timePointcut()")
  public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getSignature().getDeclaringTypeName();
    log.info("Метод {} из класса {} запустился", methodName, className);

    Method method = methodRepository.findByMethodNameAndClassName(methodName, className)
            .orElseGet(() -> methodRepository.save(Method.builder()
                    .className(className)
                    .methodName(methodName)
                    .build()));

    LocalDateTime startTime = LocalDateTime.now();
    Object result;

    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      log.error("В методе {} произошла ошибка: {}", methodName, throwable.getMessage());
      throw throwable;
    }

    LocalDateTime endTime = LocalDateTime.now();
    Duration executionTime = Duration.between(startTime, endTime);

    TimeExectuion timeExecution = TimeExectuion.builder()
            .startTime(startTime)
            .endTime(endTime)
            .execution(executionTime.toMillis())
            .isComplete(true)
            .method(method)
            .build();
    timeExecutionRepository.save(timeExecution);

    log.info("Метод {} выполнился за {} мс", methodName, executionTime.toMillis());
    return result;
  }
}

