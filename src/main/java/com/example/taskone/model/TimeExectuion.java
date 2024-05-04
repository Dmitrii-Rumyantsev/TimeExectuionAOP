package com.example.taskone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "time_exectuion")
@AllArgsConstructor
@NoArgsConstructor
public class TimeExectuion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "time_id")
  private Long id;

  @Column(name = "start_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime startTime;

  @Column(name = "end_time")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime endTime;

  @Column(name = "execution")
  private Long execution;

  @Column(name = "is_complete")
  private Boolean isComplete;

  @ManyToOne
  @JoinColumn(name = "method_id")
  private Method method;
}
