package com.example.taskone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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
  private Double startTime;

  @Column(name = "end_time")
  private Double endTime;

  @Column(name = "date")
  private LocalDate date;

  @ManyToOne
  private Method method;
}
