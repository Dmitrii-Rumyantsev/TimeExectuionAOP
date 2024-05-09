package com.example.taskone.mapper;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.dto.MethodDTO;
import com.example.taskone.model.Account;
import com.example.taskone.model.Method;
import com.example.taskone.model.TimeExectuion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MethodMapperTest {

    @Autowired
    private MethodMapper methodMapper;

    @Test
    void toDTOTest() {
        Method method = Method.builder()
                .methodName("addAccounts")
                .className("com.example.taskone.service.impl.AccountServiceImpl")
                .timeExecutionList(List.of(TimeExectuion.builder().startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).execution(1L).isComplete(true).build()))
                .build();
        MethodDTO methodDTO = methodMapper.toDTO(method);
        assertNotNull(methodDTO);
        assertEquals(methodDTO.getMethodName(), method.getMethodName());
        assertEquals(methodDTO.getClassName(), method.getClassName());
    }

    @Test
    void toDTOListTest() {
        List<Method> methodList = List.of(Method.builder()
                .methodName("addAccounts")
                .className("com.example.taskone.service.impl.AccountServiceImpl")
                .timeExecutionList(List.of(TimeExectuion.builder().startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).execution(1L).isComplete(true).build()))
                .build(),
                Method.builder()
                        .methodName("addAccount")
                        .className("com.example.taskone.service.impl.AccountServiceImpl")
                        .timeExecutionList(List.of(TimeExectuion.builder().startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).execution(1L).isComplete(true).build()))
                        .build());
        List<MethodDTO> methodDTOList = methodMapper.toDTOList(methodList);
        assertNotNull(methodDTOList);
        assertEquals(methodDTOList.size(), methodList.size());
        assertEquals(methodDTOList.get(0).getMethodName(), methodDTOList.get(0).getMethodName());
        assertEquals(methodDTOList.get(0).getClassName(), methodDTOList.get(0).getClassName());
        assertEquals(methodDTOList.get(1).getMethodName(), methodDTOList.get(1).getMethodName());
        assertEquals(methodDTOList.get(1).getClassName(), methodDTOList.get(1).getClassName());
    }
}