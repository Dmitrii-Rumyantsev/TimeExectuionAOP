package com.example.taskone.repository;

import com.example.taskone.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Optional<Account> findById(Long id);
  List<Account> findAll();

  Optional<Account> findByEmail(String email);

  Optional<Account> findByPhoneOrEmail(String phone, String email);
}
