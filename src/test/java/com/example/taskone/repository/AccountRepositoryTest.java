package com.example.taskone.repository;

import com.example.taskone.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder()
                .id(1L)
                .phone("1234567890")
                .dateBirth(LocalDate.now())
                .email("dima123@gmail.com").build();
    }

    @DisplayName("Добавление аккаунта")
    @Test
    void  testAddingEntity(){
        Account addingAccount = accountRepository.save(account);
        assertNotNull(addingAccount);
        assertEquals(account.getId(), addingAccount.getId());
        assertEquals(account.getPhone(), addingAccount.getPhone());
        assertEquals(account.getDateBirth(), addingAccount.getDateBirth());
        assertEquals(account.getEmail(), addingAccount.getEmail());
    }

    @DisplayName("Поиск по id")
    @Test
    void  testFindingEntityById(){
        accountRepository.save(account);
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        assertTrue(accountOptional.isPresent());
        assertEquals(account.getId(), accountOptional.get().getId());
        assertEquals(account.getPhone(), accountOptional.get().getPhone());
        assertEquals(account.getDateBirth(), accountOptional.get().getDateBirth());
        assertEquals(account.getEmail(), accountOptional.get().getEmail());
    }
    @DisplayName("Поиск по email")
    @Test
    void  testFindingEntityByEmail(){
        accountRepository.save(account);
        Optional<Account> accountOptional = accountRepository.findByEmail(account.getEmail());
        assertTrue(accountOptional.isPresent());
        assertEquals(account.getId(), accountOptional.get().getId());
        assertEquals(account.getPhone(), accountOptional.get().getPhone());
        assertEquals(account.getDateBirth(), accountOptional.get().getDateBirth());
        assertEquals(account.getEmail(), accountOptional.get().getEmail());
    }
}