package com.diamante.springbootmongodb.service;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@DataMongoTest
public class EmployeeServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    Employee employee1 = new Employee( 1,
            "Bill",
            "Kotlin Developer",
            80000);
    Employee employee2 = new Employee( 2,
            "Joe",
            "Project Manager",
            80000);
    Employee employee3 = new Employee( 3,
            "Terry",
            "XP Developer",
            80000);

    @Before
    public void setUp() throws Exception {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee1));
        when(employeeRepository.findById(2)).thenReturn(Optional.of(employee2));
        when(employeeRepository.findById(3)).thenReturn(Optional.of(employee3));

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
    }

    @Test
    public void whenGetOneIsCalled_thenReturnCorrectEmployee() {
        Employee actualEmployee = employeeService.getOne(1);
        assertThat(actualEmployee).isEqualToComparingFieldByField(employee1);

        actualEmployee = employeeService.getOne(2);
        assertThat(actualEmployee).isEqualToComparingFieldByField(employee2);

        actualEmployee = employeeService.getOne(3);
        assertThat(actualEmployee).isEqualToComparingFieldByField(employee3);
    }

    @Test
    public void whenGetAllIsCalled_thenReturnAllEmployees() {
        List<Employee> employees = employeeService.getAll();
        assertThat(employees.size()).isEqualTo(3);
    }

    @Test
    public void whenSaveIsCalled_thenSaveEmployee() {
        when(employeeRepository.insert((Employee) any())).thenReturn(employee3);

        Employee savedEmployee = employeeService.save(employee3);

        assertThat(savedEmployee).isEqualToComparingFieldByField(employee3);
    }

    @Test
    public void whenUpdateIsCalled_thenUpdateTheEmployee() {
        when(employeeRepository.insert((Employee) any())).thenReturn(employee2);

        Employee updatedEmployee = new Employee( 2,
                "Joe",
                "Program Lead Manager",
                120000);

        Employee employeeAfterUpdate = employeeService.update( updatedEmployee);

        assertThat(employeeAfterUpdate).isEqualToComparingFieldByField(employee2);
    }

    @Test
    public void whenUpdateIsCalled_andIdsDoNotMatch_thenThrowException() {
        when(employeeRepository.insert((Employee) any())).thenReturn(employee2);

        Employee updatedEmployee = new Employee( 6,
                "Joe",
                "Program Lead Manager",
                120000);

        Employee employeeAfterUpdate = employeeService.update(updatedEmployee);
        assertThat(employeeAfterUpdate).isNull();
    }



    @Test
    public void whenDeleteIsCalled_thenDeleteThatEmployee() {
        doNothing().when(employeeRepository).deleteById(anyInt());
        employeeService.delete(3);
        verify(employeeRepository).deleteById(3);
    }
}