package com.example.taskone.service;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.mapper.AccountMappers;
import com.example.taskone.model.Account;
import com.example.taskone.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AccountService {

  Account addAccount(AccountDTO accountDTO);

  List<AccountDTO> getAllAccount();

  CompletableFuture<Optional<AccountDTO>> getAccountById(Long id);

  CompletableFuture<Optional<AccountDTO>> getAccountByEmail(String email);
}
