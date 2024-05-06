package com.example.taskone.repository;

import com.example.taskone.model.Method;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends CrudRepository<Method, Long> {

  Iterable<Method> findAll();

  Optional<Method> findByMethodNameAndClassName(String methodName, String className);

  Optional<Method> findByMethodName(String nameMethod);
}
