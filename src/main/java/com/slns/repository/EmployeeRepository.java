package com.slns.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.slns.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee employee){
        this.dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployee(String employeeId){
        return this.dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String updateEmployee(String employeeId){
        Employee employee1 = dynamoDBMapper.load(Employee.class, employeeId);
         employee1.setEmail("Ricky@gmail.com");
        dynamoDBMapper.save(employee1);
        return "Employee updated";
    }

    //Another example of update employee in dynamo db
    //Update the employee object based on matching condition
    //It is quiet similar to update emp set sal = 10000 where sal < 20000;
    public String updateEmployeeBasedONCondition(Employee employee){
        dynamoDBMapper.save(employee, new DynamoDBSaveExpression()
                .withExpectedEntry("employeeId",
                        new ExpectedAttributeValue(
                                new AttributeValue()
                                        .withS(employee.getEmployeeId())
                        )));
        return "Employee updated";
    }

    public String deleteEmployee(String employeeId){
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        dynamoDBMapper.delete(employee);
        return "Employee Deleted";
    }

}
