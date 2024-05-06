package com.example.taskone.service;

import com.example.taskone.dto.MethodDTO;
import com.example.taskone.dto.MethodTimeDTO;
import java.util.List;
import java.util.Optional;

public interface MethodService {

  List<MethodDTO> getAll();

  Optional<MethodDTO> getMethodByName(String methodName);

  List<MethodTimeDTO> getAVGTimeMethod();

  List<MethodTimeDTO> getMaxTimeMethod();

  List<MethodTimeDTO> getMinTimeMethod();
}
