package com.example.taskone.service.impl;

import com.example.taskone.annotation.TrackAsyncTime;
import com.example.taskone.annotation.TrackTime;
import com.example.taskone.dto.AccountDTO;
import com.example.taskone.mapper.AccountMappers;
import com.example.taskone.model.Account;
import com.example.taskone.repository.AccountRepository;
import com.example.taskone.service.AccountService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMappers accountMappers;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, AccountMappers accountMappers) {
    this.accountRepository = accountRepository;
    this.accountMappers = accountMappers;
  }

  @Override
  @TrackTime
  public Account addAccount(AccountDTO accountDTO) {
    return accountRepository.save(accountMappers.toModel(accountDTO));
  }

  @Override
  @Transactional
  @TrackTime
  public List<Account> addAccounts(List<AccountDTO> accountDTOList) {
    return accountDTOList.stream()
        .filter(x -> accountRepository.findByPhoneOrEmail(x.getPhone(), x.getEmail()).isEmpty())
        .map(accountMappers::toModel)
        .map(accountRepository::save)
        .collect(Collectors.toList());
  }


  @Override
  @TrackTime
  public List<Account> getAllAccount() {
    return accountRepository.findAll();
  }

  @Override
  @TrackAsyncTime
  @Async("asyncExecutor")
  public CompletableFuture<Optional<Account>> getAccountByEmail(String email) {
    return CompletableFuture.supplyAsync(() -> {
      return accountRepository.findByEmail(email);
    });
  }

  @Override
  @TrackAsyncTime
  @Async("asyncExecutor")
  public CompletableFuture<Optional<Account>> getAccountById(Long id) {
    return CompletableFuture.supplyAsync(() -> {
          return accountRepository.findById(id);
        }
    );
  }
}
