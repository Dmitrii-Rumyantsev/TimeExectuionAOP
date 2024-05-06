package com.example.taskone.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TimeExectuionDTO {

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  private Long execution;

  private Boolean isComplete;
}
