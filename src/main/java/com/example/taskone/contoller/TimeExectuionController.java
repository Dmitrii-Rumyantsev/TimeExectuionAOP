package com.example.taskone.contoller;

import com.example.taskone.dto.MethodDTO;
import com.example.taskone.dto.MethodTimeDTO;
import com.example.taskone.service.impl.MethodServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class TimeExectuionController {

  private final MethodServiceImpl methodService;

  @Autowired
  public TimeExectuionController(MethodServiceImpl methodService) {
    this.methodService = methodService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<MethodDTO>> getAllMethods() {
    return ResponseEntity.ok(methodService.getAll());
  }

  @GetMapping("/min")
  public ResponseEntity<List<MethodTimeDTO>> getAllMinMethods() {
    return ResponseEntity.ok(methodService.getMinTimeMethod());
  }

  @GetMapping("/avg")
  public ResponseEntity<List<MethodTimeDTO>> getAllAvgMethods() {
    return ResponseEntity.ok(methodService.getAVGTimeMethod());
  }

  @GetMapping("/max")
  public ResponseEntity<List<MethodTimeDTO>> getAllMaxMethods() {
    return ResponseEntity.ok(methodService.getMaxTimeMethod());
  }
}
