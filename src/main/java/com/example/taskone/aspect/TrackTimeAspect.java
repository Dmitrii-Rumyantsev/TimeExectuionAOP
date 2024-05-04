package com.example.taskone.aspect;


import com.example.taskone.model.Method;
import com.example.taskone.model.TimeExectuion;
import com.example.taskone.repository.MethodRepository;
import com.example.taskone.repository.TimeExecutionRepository;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TrackTimeAspect {

  @Autowired
  private MethodRepository methodRepository;
  @Autowired
  private TimeExecutionRepository timeExecutionRepository;

  @Pointcut("@annotation(com.example.taskone.annotation.TrackTime)")
  public void timePoincut() {
  }

  @Around("timePoincut()")
  public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getSignature().getDeclaringTypeName();
    log.info("Из класса {} запустился метод {}", className, methodName);
    Method method = methodRepository.findByMethodNameAndClassName(methodName, className)
        .orElseGet(() -> {
          Method newMethod = new Method();
          newMethod.setMethodName(methodName);
          newMethod.setClassName(className);
          return methodRepository.save(newMethod);
        });
    LocalDateTime startTime = LocalDateTime.now();

    Object result = joinPoint.proceed();

    LocalDateTime endTime = LocalDateTime.now();

    Duration executionTime = Duration.between(startTime,endTime);
    log.info("Метод {} отработал за {} мс", methodName, executionTime);

    TimeExectuion timeExecution = new TimeExectuion();
    timeExecution.setStartTime(startTime);
    timeExecution.setEndTime(endTime);
    timeExecution.setExecution(executionTime.toMillis());
    timeExecution.setIsComplete(true);
    timeExecution.setMethod(method);
    timeExecutionRepository.save(timeExecution);
    log.info("Сохранение в БД ");
    return result;
  }
}
