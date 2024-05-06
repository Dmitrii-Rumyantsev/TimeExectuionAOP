package com.example.taskone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MethodTimeDTO {

  private String className;
  private String methodName;
  private Double timeInMills;
}
