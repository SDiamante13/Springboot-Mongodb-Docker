package com.diamante.springbootmongodb.controller;

import com.diamante.springbootmongodb.entity.Employee;
import com.diamante.springbootmongodb.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

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
    }

    @Test
    public void givenEmployeesExist_whenGetAllEmployeesEndpointIsHit_thenReturnAllEmployees() throws Exception {
        when(employeeService.getAll())
                .thenReturn(Arrays.asList(employee1, employee2, employee3));

        mockMvc.perform(get("/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(employee1.getName())));
    }
}