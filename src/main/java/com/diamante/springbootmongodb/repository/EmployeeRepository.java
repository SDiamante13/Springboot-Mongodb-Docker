package com.diamante.springbootmongodb.repository;

import com.diamante.springbootmongodb.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
}
