package com.example.taskone.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

  @NotNull
  private String fullName;

  @Column(name = "data_of_birth")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @NotNull
  private LocalDate dateBirth;

  @Digits(fraction = 0, integer = 11)
  @NotNull
  private String phone;

  @Email
  @NotNull
  private String email;
}
