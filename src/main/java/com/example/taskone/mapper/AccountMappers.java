package com.example.taskone.mapper;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.model.Account;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMappers {

  AccountDTO toDTO(Account account);

  Account toModel(AccountDTO accountDTO);

  List<Account> toModelList(List<AccountDTO> list);

  List<AccountDTO> toDTOList(List<Account> list);
}
