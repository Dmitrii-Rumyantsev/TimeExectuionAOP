package com.example.taskone.repository;

import com.example.taskone.model.Method;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends CrudRepository<Method, Long> {

  List<Method> findAll();

  Method save(Method method);

  Optional<Method> findById(Long method_id);
}