package com.example.taskone.contoller;

import com.example.taskone.dto.AccountDTO;
import com.example.taskone.mapper.AccountMappers;
import com.example.taskone.model.Account;
import com.example.taskone.service.AccountService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;

  private final AccountMappers accountMappers;

  @Autowired
  public AccountController(AccountMappers accountMappers, AccountService accountService) {
    this.accountMappers = accountMappers;
    this.accountService = accountService;
  }

  @PostMapping("/user")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Создание пользователя",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      ),
      @ApiResponse(
          responseCode = "400",
          description = "Ошибка валидации",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      )
  })
  public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
    Account account = accountService.addAccount(accountDTO);
    return new ResponseEntity<>(accountMappers.toDTO(account), HttpStatus.CREATED);
  }

  @PostMapping("/users")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Создание пользователя",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      ),
      @ApiResponse(
          responseCode = "400",
          description = "Ошибка валидации",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      )
  })
  public ResponseEntity<List<AccountDTO>> createAccounts(
      @RequestBody @Valid List<AccountDTO> accountDTOList) {
    accountDTOList = accountService.addAccounts(accountDTOList);
    return new ResponseEntity<>(accountDTOList, HttpStatus.CREATED);
  }
  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "302",
          description = "Получение всех пользователей",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      )
  })
  public ResponseEntity<List<AccountDTO>> getAllAccounts() {
    List<AccountDTO> accounts = accountService.getAllAccount();
    return new ResponseEntity<>(accounts, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "302",
          description = "Получения пользователя по id",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
            description = "Пользователь не найден",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      )
  })
  public CompletableFuture<ResponseEntity<?>> getAccountById(@PathVariable Long id) {
    CompletableFuture<Optional<AccountDTO>> accountFuture = accountService.getAccountById(id);
    return accountFuture.thenApply(accountDTO ->
      accountDTO.map(v -> new ResponseEntity<>(v, HttpStatus.FOUND))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
  }

  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "302",
          description = "Получение пользователя по email",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Пользователь не найден",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = AccountDTO.class)
          )
      )
  })
  @GetMapping("/email/{email}")
  public CompletableFuture<ResponseEntity<AccountDTO>> getAccountByEmail(@PathVariable String email) {
    CompletableFuture<Optional<AccountDTO>> accountFuture = accountService.getAccountByEmail(email);
    return accountFuture.thenApply(accountDTOOptional ->
        accountDTOOptional.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
  }

}
