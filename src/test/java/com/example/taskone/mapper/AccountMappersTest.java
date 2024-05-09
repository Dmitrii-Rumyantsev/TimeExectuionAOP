package com.example.taskone.mapper;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMappersTest {

    @Autowired
    private AccountMappers accountMappers;

    @Test
    void toDTOTest() {
        Account account = Account.builder()
                .id(1L)
                .fullName("Дмитрий Иванов")
                .dateBirth(LocalDate.now())
                .phone("1234567890")
                .email("dima123@gmail.com")
                .build();
        AccountDTO accountDTO = accountMappers.toDTO(account);
        assertNotNull(accountDTO);
        assertEquals(account.getFullName(),accountDTO.getFullName());
        assertEquals(account.getDateBirth(),accountDTO.getDateBirth());
        assertEquals(account.getPhone(),accountDTO.getPhone());
        assertEquals(account.getEmail(),accountDTO.getEmail());
    }

    @Test
    void toModelTest() {
        AccountDTO accountDTO = AccountDTO.builder()
                .fullName("Дмитрий Иванов")
                .dateBirth(LocalDate.now())
                .phone("1234567890")
                .email("dima123@gmail.com")
                .build();
        Account account = accountMappers.toModel(accountDTO);
        assertNotNull(account);
        assertEquals(account.getFullName(),accountDTO.getFullName());
        assertEquals(account.getDateBirth(),accountDTO.getDateBirth());
        assertEquals(account.getPhone(),accountDTO.getPhone());
        assertEquals(account.getEmail(),accountDTO.getEmail());
    }

    @Test
    void toDTOListTest() {
        List<Account> account = List.of(
                Account.builder().id(1L).fullName("Дмитрий Иванов").phone("1234567890").dateBirth(LocalDate.now()).email("dima123@gmail.com").build(),
                Account.builder().id(2L).fullName("Алексей Иванов").phone("1234567891").dateBirth(LocalDate.now()).email("lexa123@gmail.com").build()
        );
        List<AccountDTO> accountDTOList = accountMappers.toDTOList(account);
        assertNotNull(accountDTOList);
        assertEquals(account.size(),accountDTOList.size());
        assertEquals(account.get(0).getFullName(),accountDTOList.get(0).getFullName());
        assertEquals(account.get(0).getDateBirth(),accountDTOList.get(0).getDateBirth());
        assertEquals(account.get(0).getPhone(),accountDTOList.get(0).getPhone());
        assertEquals(account.get(0).getEmail(),accountDTOList.get(0).getEmail());
        assertEquals(account.get(1).getFullName(),accountDTOList.get(1).getFullName());
        assertEquals(account.get(1).getDateBirth(),accountDTOList.get(1).getDateBirth());
        assertEquals(account.get(1).getPhone(),accountDTOList.get(1).getPhone());
        assertEquals(account.get(1).getEmail(),accountDTOList.get(1).getEmail());
    }

    @Test
    void toModelListTest() {
        List<AccountDTO> accountDTOList = List.of(
                AccountDTO.builder().fullName("Дмитрий Иванов").phone("1234567890").dateBirth(LocalDate.now()).email("dima123@gmail.com").build(),
                AccountDTO.builder().fullName("Алексей Иванов").phone("1234567891").dateBirth(LocalDate.now()).email("lexa123@gmail.com").build()
        );
        List<Account> accountList = accountMappers.toModelList(accountDTOList);
        assertNotNull(accountDTOList);
        assertEquals(accountDTOList.size(),accountDTOList.size());
        assertEquals(accountDTOList.get(0).getFullName(),accountDTOList.get(0).getFullName());
        assertEquals(accountDTOList.get(0).getDateBirth(),accountDTOList.get(0).getDateBirth());
        assertEquals(accountDTOList.get(0).getPhone(),accountDTOList.get(0).getPhone());
        assertEquals(accountDTOList.get(0).getEmail(),accountDTOList.get(0).getEmail());
        assertEquals(accountDTOList.get(1).getFullName(),accountDTOList.get(1).getFullName());
        assertEquals(accountDTOList.get(1).getDateBirth(),accountDTOList.get(1).getDateBirth());
        assertEquals(accountDTOList.get(1).getPhone(),accountDTOList.get(1).getPhone());
        assertEquals(accountDTOList.get(1).getEmail(),accountDTOList.get(1).getEmail());
    }
}