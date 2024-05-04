package com.example.taskone.repository;

import com.example.taskone.model.TimeExectuion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeExecutionRepository extends CrudRepository<TimeExectuion, Long> {

}
