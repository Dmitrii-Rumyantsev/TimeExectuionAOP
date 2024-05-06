package com.example.taskone.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "method")
@AllArgsConstructor
@NoArgsConstructor
public class Method {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "method_id")
  private Long id;

  @Column(name = "class_name")
  private String className;

  @Column(name = "method_name")
  private String methodName;

  @OneToMany(mappedBy = "method", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<TimeExectuion> timeExecutionList = new ArrayList<>();

  public Method(String className, String methodName) {
    this.className = className;
    this.methodName = methodName;
  }
}
