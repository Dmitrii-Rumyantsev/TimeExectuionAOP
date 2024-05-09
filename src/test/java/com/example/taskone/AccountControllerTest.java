package com.example.taskone;

import com.example.taskone.contoller.AccountController;
import com.example.taskone.dto.AccountDTO;
import com.example.taskone.mapper.AccountMappers;
import com.example.taskone.mapper.AccountMappersImpl;
import com.example.taskone.model.Account;
import com.example.taskone.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountMappersImpl accountMappers;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1L, "Дмитрий Иванов", LocalDate.parse("1999-01-04"), "1234567890", "dima123@example.com"));

        when(accountService.getAllAccount()).thenReturn(accounts);
        when(accountMappers.toDTOList(accounts)).thenCallRealMethod();

        ResponseEntity<List<AccountDTO>> responseEntity = accountController.getAllAccounts();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(accounts.size(), responseEntity.getBody().size());
        assertEquals(accounts.get(0).getFullName(), responseEntity.getBody().get(0).getFullName());
        assertEquals(accounts.get(0).getDateBirth(), responseEntity.getBody().get(0).getDateBirth());
        assertEquals(accounts.get(0).getPhone(), responseEntity.getBody().get(0).getPhone());
        assertEquals(accounts.get(0).getEmail(), responseEntity.getBody().get(0).getEmail());

    }

    @Test
    public void testGetAccountById() {
        Long accountId = 1L;
        Account account = new Account(accountId, "Дмитрий Иванов", LocalDate.parse("1999-01-04"), "1234567890", "john@example.com");

        CompletableFuture<Optional<Account>> accountFuture = CompletableFuture.completedFuture(Optional.of(account));
        when(accountService.getAccountById(accountId)).thenReturn(accountFuture);
        when(accountMappers.toDTO(account)).thenCallRealMethod();

        ResponseEntity<?> responseEntity = accountController.getAccountById(accountId).join();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(account.getFullName(), ((AccountDTO) responseEntity.getBody()).getFullName());
        assertEquals(account.getDateBirth(), ((AccountDTO) responseEntity.getBody()).getDateBirth());
        assertEquals(account.getPhone(), ((AccountDTO) responseEntity.getBody()).getPhone());
        assertEquals(account.getEmail(), ((AccountDTO) responseEntity.getBody()).getEmail());
    }

    @Test
    public void testGetAccountByEmail() {
        String email = "john@example.com";
        Account account = new Account(1L, "Дмитрий Иванов", LocalDate.parse("1999-01-04"), "1234567890", email);

        CompletableFuture<Optional<Account>> accountFuture = CompletableFuture.completedFuture(Optional.of(account));
        when(accountService.getAccountByEmail(email)).thenReturn(accountFuture);
        when(accountMappers.toDTO(account)).thenCallRealMethod();

        ResponseEntity<AccountDTO> responseEntity = accountController.getAccountByEmail(email).join();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(email, responseEntity.getBody().getEmail());
    }
}