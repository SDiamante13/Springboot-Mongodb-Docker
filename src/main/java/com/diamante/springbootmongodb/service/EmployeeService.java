package com.diamante.springbootmongodb.service;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(Employee employee) {
        employeeRepository.insert(employee);
    }

    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    public Employee getOne(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public void update(Employee updatedEmployee) {
        Employee originalEmployee = employeeRepository.findById(updatedEmployee.getId()).get();
        if(originalEmployee != null) {
            originalEmployee.setName(updatedEmployee.getName());
            originalEmployee.setRole(updatedEmployee.getRole());
            originalEmployee.setSalary(updatedEmployee.getSalary());
            employeeRepository.save(originalEmployee);
        }
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }

}
