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
public class TrackAsyncTimeAspect {

  @Autowired
  private MethodRepository methodRepository;
  @Autowired
  private TimeExecutionRepository timeExecutionRepository;

  @Pointcut("@annotation(com.example.taskone.annotation.TrackAsyncTime)")
  public void timeAsyncPoincut() {
  }

  @Around("timeAsyncPoincut()")
  public Object trackAsyncTime(ProceedingJoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getSignature().getDeclaringTypeName();
    log.info("Из класса {} запустился метод асинхронный метод {}", className, methodName);
    Method method = methodRepository.findByMethodNameAndClassName(methodName, className)
        .orElseGet(() -> {
          Method newMethod = new Method();
          newMethod.setMethodName(methodName);
          newMethod.setClassName(className);
          return methodRepository.save(newMethod);
        });
    LocalDateTime startTime = LocalDateTime.now();
    Object result;
    TimeExectuion timeExectuion = new TimeExectuion();
    timeExectuion.setMethod(method);
    timeExectuion.setStartTime(startTime);
    try {
      result = joinPoint.proceed();
    } catch (Throwable e) {
      LocalDateTime endTime = LocalDateTime.now();
      timeExectuion.setEndTime(endTime);
      timeExectuion.setExecution(Duration.between(
              startTime, endTime)
          .toMillis()
      );
      timeExectuion.setIsComplete(false);
      timeExecutionRepository.save(timeExectuion);
      log.error("Асинхронный метод {} не выполнился {}", methodName, className,
          e.getMessage());
      throw new RuntimeException(e);
    }
    LocalDateTime endTime = LocalDateTime.now();
    timeExectuion.setEndTime(endTime);
    Duration execution = Duration.between(startTime,endTime);
    log.info("Асинхронный методы {} выполнился за {} ms",methodName, execution);
    timeExectuion.setExecution(execution.toMillis());
    timeExectuion.setIsComplete(true);
    timeExecutionRepository.save(timeExectuion);
    log.info("Сохранение в БД");
    return result;
  }
}
