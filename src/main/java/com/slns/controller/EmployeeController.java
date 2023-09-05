package com.slns.controller;

import com.slns.entity.Employee;
import com.slns.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("employeeId") String employeeId){
        log.info("Started fetching employee details for employeeId {}", employeeId);
        Employee employee = employeeRepository.getEmployee(employeeId);
        log.info("Fetched employee details for employeeId {}", employeeId);
       return  ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> saveEmployeeDetails(@RequestBody Employee employee){
        log.info("Saving Employee Details");
        employeeRepository.save(employee);
        log.info("Saved employee Details in Dynamo DD");
        return  ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<String> getEmployeeDetails(@PathVariable("employeeId") String employeeId, @RequestBody Employee employee){
        log.info("Before updating employee details for employeeId {} ", employeeId);
        String status = employeeRepository.updateEmployeeBasedONCondition(employee);
        log.info("After updating employee details for employeeId {}", employeeId);
        return  ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteEmployeeDetails(@RequestHeader("employeeId") String employeeId){
        log.info("Before Deleting employee details for employeeId {} ", employeeId);
        String status = employeeRepository.deleteEmployee(employeeId);
        log.info("After deleting employee details for employeeId {}", employeeId);
        return  ResponseEntity.status(HttpStatus.OK).body(status);
    }


}
