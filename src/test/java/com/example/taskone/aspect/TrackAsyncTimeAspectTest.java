package com.example.taskone.aspect;

import com.example.taskone.repository.MethodRepository;
import com.example.taskone.repository.TimeExecutionRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TrackAsyncTimeAspectTest {
    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private Signature signature;

    @InjectMocks
    private TrackAsyncTimeAspect trackAsyncTimeAspect;

    @Mock
    private MethodRepository methodRepository;
    @Mock
    private TimeExecutionRepository timeExecutionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Аспект в поиске аккаунта по email")
    @Test
    void trackTimeAspectInGetAccountByEmail() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("getAccountByEmail");
        when(signature.getDeclaringTypeName()).thenReturn("com.example.taskone.service.AccountService");
        trackAsyncTimeAspect.trackAsyncTime(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @DisplayName("Аспект в поиске аккаунта по id")
    @Test
    void trackTimeAspectInGetAccountById() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("getAccountById");
        when(signature.getDeclaringTypeName()).thenReturn("com.example.taskone.service.AccountService");
        trackAsyncTimeAspect.trackAsyncTime(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).proceed();
    }
}