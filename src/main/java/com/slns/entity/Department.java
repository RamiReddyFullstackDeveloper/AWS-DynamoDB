package com.slns.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
@Getter
@Setter
public class Department {
    @DynamoDBAttribute
    private String departmentName;
    @DynamoDBAttribute
    private String departmentCode;
}
