package com.diamante.springbootmongodb.service;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.insert(employee);
    }

    public List<Employee> getAll() {
       return employeeRepository.findAll();
    }

    public Employee getOne(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        return null;
    }

    public Employee update(Employee updatedEmployee) {
        Employee employeeAfterUpdate;
        Optional<Employee> optionalOriginalEmployee = employeeRepository.findById(updatedEmployee.getId());
        if(optionalOriginalEmployee.isPresent()) {
            Employee originalEmployee = optionalOriginalEmployee.get();
            originalEmployee.setName(updatedEmployee.getName());
            originalEmployee.setRole(updatedEmployee.getRole());
            originalEmployee.setSalary(updatedEmployee.getSalary());
            employeeAfterUpdate = employeeRepository.insert(originalEmployee);
            return employeeAfterUpdate;
        }
        return null;
    }

    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
