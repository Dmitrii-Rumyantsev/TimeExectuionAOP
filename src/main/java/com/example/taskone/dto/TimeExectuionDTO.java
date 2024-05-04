package com.example.taskone.dto;

import com.example.taskone.model.Method;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TimeExectuionDTO {

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private Long execution;

  private Boolean isComplete;

  private Method method;
}
