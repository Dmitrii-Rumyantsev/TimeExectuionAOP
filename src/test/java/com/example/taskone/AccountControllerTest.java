package com.example.taskone;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.taskone.contoller.AccountController;
import com.example.taskone.dto.AccountDTO;
import com.example.taskone.model.Account;
import com.example.taskone.service.AccountService;
import com.example.taskone.service.impl.AccountServiceImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

  @Mock
  private AccountServiceImpl accountService;

  @InjectMocks
  private AccountController accountController;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
  }

  @Test
  void createAccount() throws Exception {
    AccountDTO accountDTO = new AccountDTO("Дима Иванов", LocalDate.parse("1992-01-01"),
        "1234567899", "ivan1@example.com");
    Account account = new Account();
    when(accountService.addAccount(accountDTO)).thenReturn(account);
    mockMvc.perform(post("/api/accounts/user"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.fullName").value("Дима Иванов"))
        .andExpect(jsonPath("$.dateBirth").value("1992-01-01"))
        .andExpect(jsonPath("$.phone").value("1234567899"))
        .andExpect(jsonPath("$.email").value("ivan1@example.com"));
  }
}
