package com.example.taskone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "method")
public class Method {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "method_id")
  private Long id;

  @Column(name = "class_name")
  @NotNull
  private String className;

  @Column(name = "method_name")
  @NotNull
  private String methodName;

  @OneToMany(mappedBy = "method", cascade = CascadeType.ALL)
  private List<TimeExectuion> timeExectuionList = new ArrayList<>();
}
