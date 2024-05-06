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
  public void asyncTimePointcut() {
  }

  @Around("asyncTimePointcut()")
  public Object trackAsyncTime(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    String className = joinPoint.getSignature().getDeclaringTypeName();
    log.info("Async method {} from class {} is invoked", methodName, className);

    Method method = methodRepository.findByMethodNameAndClassName(methodName, className)
        .orElseGet(() -> methodRepository.save(new Method(methodName, className)));

    LocalDateTime startTime = LocalDateTime.now();
    Object result;

    try {
      result = joinPoint.proceed();
    } catch (Throwable throwable) {
      log.error("Async method {} encountered an error: {}", methodName, throwable.getMessage());
      throw throwable;
    }

    LocalDateTime endTime = LocalDateTime.now();
    Duration executionTime = Duration.between(startTime, endTime);

    TimeExectuion timeExecution = new TimeExectuion();
    timeExecution.setStartTime(startTime);
    timeExecution.setEndTime(endTime);
    timeExecution.setExecution(executionTime.toMillis());
    timeExecution.setIsComplete(true);
    timeExecution.setMethod(method);
    timeExecutionRepository.save(timeExecution);

    log.info("Async method {} executed in {} milliseconds", methodName, executionTime.toMillis());
    return result;
  }
}