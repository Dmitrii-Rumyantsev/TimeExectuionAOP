package com.example.taskone.mapper;

import com.example.taskone.dto.MethodDTO;
import com.example.taskone.model.Method;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MethodMappers {

 public MethodDTO toDTO(Method method);

 public Method toModel(MethodDTO methodDTO);
}
