package com.example.taskone.service.impl;

import com.example.taskone.dto.MethodDTO;
import com.example.taskone.dto.MethodTimeDTO;
import com.example.taskone.mapper.MethodMapper;
import com.example.taskone.model.Method;
import com.example.taskone.model.TimeExectuion;
import com.example.taskone.repository.MethodRepository;
import com.example.taskone.service.MethodService;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodServiceImpl implements MethodService {

  private final MethodRepository methodRepository;
  private final MethodMapper methodMapper;

  @Autowired
  public MethodServiceImpl(MethodRepository methodRepository, MethodMapper methodMapper) {
    this.methodRepository = methodRepository;
    this.methodMapper = methodMapper;
  }

  public List<MethodDTO> getAll() {
    return StreamSupport.stream(methodRepository.findAll().spliterator(), false)
        .map(methodMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public Optional<MethodDTO> getMethodByName(String methodName) {
    return methodRepository.findByMethodName(methodName).map(methodMapper::toDTO);
  }

  private List<MethodTimeDTO> calculateTime(List<Method> methods,
      ToLongFunction<TimeExectuion> mapper,
      ToDoubleFunction<LongStream> reducer) {
    return methods.stream().map(x -> {
      double result = reducer.applyAsDouble(
          x.getTimeExecutionList().stream().mapToLong(mapper)
      );
      MethodTimeDTO methodTimeDTO = new MethodTimeDTO();
      methodTimeDTO.setMethodName(x.getMethodName());
      methodTimeDTO.setClassName(x.getClassName());
      methodTimeDTO.setTimeInMills(result);
      return methodTimeDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public List<MethodTimeDTO> getAVGTimeMethod() {
    List<Method> list = StreamSupport.stream(methodRepository.findAll().spliterator(), false)
        .toList();
    return calculateTime(list, TimeExectuion::getExecution, x -> x.average().orElse(0));
  }

  @Override
  public List<MethodTimeDTO> getMaxTimeMethod() {
    List<Method> list = StreamSupport.stream(methodRepository.findAll().spliterator(), false)
        .toList();
    return calculateTime(list, TimeExectuion::getExecution,
        x -> x.max().orElse(0));
  }


  @Override
  public List<MethodTimeDTO> getMinTimeMethod() {
    List<Method> list = StreamSupport.stream(methodRepository.findAll().spliterator(), false)
        .toList();
    return calculateTime(list, TimeExectuion::getExecution, x -> x.min().orElse(0));
  }
}
