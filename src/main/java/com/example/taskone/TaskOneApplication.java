package com.example.taskone;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class TaskOneApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskOneApplication.class, args);
  }
}
