package com.example.taskone.mapper;

import com.example.taskone.dto.MethodDTO;
import com.example.taskone.model.Method;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TimeExecutionMappers.class)
public interface MethodMapper {

  @Mapping(target = "timeExectuion", source = "timeExecutionList")
  MethodDTO toDTO(Method method);

  List<MethodDTO> toDTOList(List<Method> methods);
}
