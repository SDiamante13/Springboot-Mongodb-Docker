package com.diamante.springbootmongodb.config;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.repository.EmployeeRepository;
import com.diamante.springbootmongodb.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = EmployeeRepository.class)
@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeService employeeService) {
        return strings -> {
          employeeService.save(new Employee(1, "Bobby", "Java Developer", 80000));
          employeeService.save(new Employee(2, "Sam", "Dev Ops", 120000));
          employeeService.save(new Employee(3, "Hank", "Human Resources", 65000));
        };
    }
}
