package com.example.taskone.service;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.model.Account;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

  Account addAccount(AccountDTO accountDTO);

  List<AccountDTO> addAccounts(List<AccountDTO> accountDTOList);
  List<AccountDTO> getAllAccount();

  CompletableFuture<Optional<AccountDTO>> getAccountById(Long id);

  CompletableFuture<Optional<AccountDTO>> getAccountByEmail(String email);
}
