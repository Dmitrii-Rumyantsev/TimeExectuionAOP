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
import java.util.stream.Collectors;
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
    return accountRepository.save(
        accountMappers.toModel(accountDTO)
    );
  }

  @Override
  @Transactional
  @TrackTime
  public List<AccountDTO> addAccounts(List<AccountDTO> accountDTOList) {
    return accountDTOList.stream()
        .filter(x -> accountRepository.findByPhoneOrEmail(x.getPhone(), x.getEmail()).isEmpty())
        .map(accountMappers::toModel)
        .map(accountRepository::save)
        .map(accountMappers::toDTO)
        .collect(Collectors.toList());
  }


  @Override
  @TrackTime
  public List<AccountDTO> getAllAccount() {
    List<Account> accounts = accountRepository.findAll();
    return accountMappers.toDTOList(accounts);
  }

  @Override
  @TrackAsyncTime
  @Async("asyncExecutor")
  public CompletableFuture<Optional<AccountDTO>> getAccountByEmail(String email) {
    return CompletableFuture.supplyAsync(() -> {
      Optional<Account> account = accountRepository.findByEmail(email);
      return account.map(accountMappers::toDTO);
    });
  }

  @Override
  @TrackAsyncTime
  @Async("asyncExecutor")
  public CompletableFuture<Optional<AccountDTO>> getAccountById(Long id) {
    return CompletableFuture.supplyAsync(() -> {
          Optional<Account> account = accountRepository.findById(id);
          return account.map(accountMappers::toDTO);
        }
    );
  }
}
