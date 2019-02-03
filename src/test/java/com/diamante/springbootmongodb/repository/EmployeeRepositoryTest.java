package com.diamante.springbootmongodb.repository;

import com.diamante.springbootmongodb.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        employeeRepository.deleteAll();
        employeeRepository.save(new Employee( 1, "Bill", "Java Developer", 80000));
        employeeRepository.save(new Employee( 2, "Tom", "Dev Ops", 120000));
        employeeRepository.save(new Employee( 3, "Jimmy", "Human Resources", 65000));
    }

    @Test
    public void findById_returnsCorrectEmployee() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(1);
        assertThat(optionalEmployee.isPresent()).isTrue();
        Employee employee1 = optionalEmployee.get();
        assertThat(employee1.getName()).isEqualTo("Bill");
        assertThat(employee1.getRole()).isEqualTo("Java Developer");
        assertThat(employee1.getSalary()).isEqualTo(80000);

        optionalEmployee = employeeRepository.findById(2);
        assertThat(optionalEmployee.isPresent()).isTrue();
        Employee employee2 = optionalEmployee.get();
        assertThat(employee2.getName()).isEqualTo("Tom");
        assertThat(employee2.getRole()).isEqualTo("Dev Ops");
        assertThat(employee2.getSalary()).isEqualTo(120000);

        optionalEmployee = employeeRepository.findById(3);
        assertThat(optionalEmployee.isPresent()).isTrue();
        Employee employee3 = optionalEmployee.get();
        assertThat(employee3.getName()).isEqualTo("Jimmy");
        assertThat(employee3.getRole()).isEqualTo("Human Resources");
        assertThat(employee3.getSalary()).isEqualTo(65000);

        optionalEmployee = employeeRepository.findById(4);
        assertThat(optionalEmployee.isPresent()).isFalse();
    }

    @Test
    public void findAll_returnsAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        assertThat(employees.size()).isEqualTo(3);
    }

    @Test
    public void callingSave_storesThatEmployeeInDatabase() {
         Optional<Employee> optionalEmployee = employeeRepository.findById(4);
        assertThat(optionalEmployee.isPresent()).isFalse();

        employeeRepository.insert(new Employee( 4, "Paul", "C# Developer", 90000));
        optionalEmployee = employeeRepository.findById(4);
        assertThat(optionalEmployee.isPresent()).isTrue();
    }

    @Test
    public void callingDeleteByIdAndDelete_deletesThoseEmployees() {
        employeeRepository.deleteById(2);
        Optional<Employee> optionalEmployee = employeeRepository.findById(2);
        assertThat(optionalEmployee.isPresent()).isFalse();

        employeeRepository.delete(new Employee( 1, "Bill", "Java Developer", 80000));
        optionalEmployee = employeeRepository.findById(1);
        assertThat(optionalEmployee.isPresent()).isFalse();
    }

    @Test
    public void callingDeleteAll_deletesAllEmployees() {
        employeeRepository.deleteAll();
        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).isEmpty();
    }
}