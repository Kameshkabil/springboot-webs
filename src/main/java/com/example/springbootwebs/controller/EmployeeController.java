package com.example.springbootwebs.controller;

import com.example.springbootwebs.model.Employee;
import com.example.springbootwebs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create-employee")
    public String createNewAccount(@RequestBody Employee employee){
       return employeeService.createNewAccount(employee);
    }

    @GetMapping("/activate/{activationToken}")
    public String activateEmployee(@PathVariable(value = "activationToken") String activationToken){
        return employeeService.activateEmployee(activationToken);
    }
}
