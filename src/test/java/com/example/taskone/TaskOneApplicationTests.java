package com.example.taskone;

import com.example.taskone.annotation.TrackTime;
import com.example.taskone.dto.AccountDTO;
import com.example.taskone.dto.MethodDTO;
import com.example.taskone.mapper.AccountMappers;
import com.example.taskone.model.Account;
import com.example.taskone.model.Method;
import com.example.taskone.model.TimeExectuion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;

@SpringBootTest
class TaskOneApplicationTests {

  @Autowired
  private AccountMappers accountMappers;
  @Test
  void contextLoads() {
    Account account = new Account();
    account.setId(1L);
    account.setPhone("77777777777");
    account.setDateBirth(LocalDate.parse("1999-01-22"));
    account.setEmail("dimadima@email.com");
    AccountDTO accountDTO = accountMappers.toDTO(account);
    Assertions.assertEquals(account.getDateBirth(),accountDTO.getDateBirth());
    Assertions.assertEquals(account.getFullName(),accountDTO.getFullName());
    Assertions.assertEquals(account.getEmail(),accountDTO.getEmail());
    Assertions.assertEquals(account.getPhone(),accountDTO.getPhone());
  }
  @Test
  void ToModel(){
    AccountDTO accountDTO = new AccountDTO();
    accountDTO.setFullName("Dima");
    accountDTO.setEmail("dadsdsads@example.com");
    accountDTO.setPhone("1234567890");
    accountDTO.setDateBirth(LocalDate.parse("1990-01-01"));
    Account account = accountMappers.toModel(accountDTO);
    Assertions.assertEquals(account.getDateBirth(),accountDTO.getDateBirth());
    Assertions.assertEquals(account.getFullName(),accountDTO.getFullName());
    Assertions.assertEquals(account.getEmail(),accountDTO.getEmail());
    Assertions.assertEquals(account.getPhone(),accountDTO.getPhone());

  }
  @Test
  void Datt(){
    TimeExectuion timeExectuion = new TimeExectuion();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    timeExectuion.setStartTime(LocalDateTime.parse("2022-01-01T12:00:00",format));
    LocalDateTime expectedDateTime = LocalDateTime.parse("2022-01-01T12:00:00", format);
    Assertions.assertTrue(expectedDateTime.isEqual(timeExectuion.getStartTime()));
  }

}
