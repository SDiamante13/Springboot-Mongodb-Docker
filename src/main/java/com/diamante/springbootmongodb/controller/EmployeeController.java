package com.diamante.springbootmongodb.controller;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) throws URISyntaxException {
            employeeService.save(employee);
            return ResponseEntity.created(new URI("/employee/"+employee.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAll();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer employeeId) {
        Employee employee = employeeService.getOne(employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee updatedEmployee) {
        employeeService.update(updatedEmployee);
        return ResponseEntity.accepted().body("Successfully updated!");
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

}
