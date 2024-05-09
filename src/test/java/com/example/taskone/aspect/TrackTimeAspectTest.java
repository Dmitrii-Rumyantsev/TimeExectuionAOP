package com.example.taskone.aspect;

import com.example.taskone.aspect.TrackTimeAspect;
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

import static org.mockito.Mockito.*;

class TrackTimeAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private Signature signature;

    @InjectMocks
    private TrackTimeAspect trackTimeAspect;

    @Mock
    private MethodRepository methodRepository;
    @Mock
    private TimeExecutionRepository timeExecutionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
     }
    @DisplayName("Аспект в добавлении аккаунта")
    @Test
    void trackTimeAspectInAddAccount() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("addAccount");
        when(signature.getDeclaringTypeName()).thenReturn("com.example.taskone.service.AccountService");
        trackTimeAspect.trackTime(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).proceed();
    }
    @DisplayName("Аспект в добавлении аккаунтов")
    @Test
    void trackTimeAspectInaddAccounts() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("addAccounts");
        when(signature.getDeclaringTypeName()).thenReturn("com.example.taskone.service.AccountService");
        trackTimeAspect.trackTime(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).proceed();
    }
}
