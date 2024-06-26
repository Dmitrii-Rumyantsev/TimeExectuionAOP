package com.example.taskone.mapper;

import com.example.taskone.dto.TimeExectuionDTO;
import com.example.taskone.model.TimeExectuion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeExecutionMappers {

 TimeExectuionDTO toDTO(TimeExectuion timeExectuion);

 TimeExectuion toModel(TimeExectuionDTO timeExectuionDTO);
}
