package com.example.taskone.dto;

import java.util.List;
import lombok.Data;

@Data
public class MethodDTO {

  private String className;
  private String methodName;
  private List<TimeExectuionDTO> timeExectuion;
}
