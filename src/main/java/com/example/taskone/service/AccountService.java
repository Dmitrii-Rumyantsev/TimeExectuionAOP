package com.example.taskone.service;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.model.Account;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

  Account addAccount(AccountDTO accountDTO);

  List<Account> addAccounts(List<AccountDTO> accountDTOList);
  List<Account> getAllAccount();

  CompletableFuture<Optional<Account>> getAccountById(Long id);

  CompletableFuture<Optional<Account>> getAccountByEmail(String email);
}
