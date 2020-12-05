package com.eric.crud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empId;

    private String lastName;

    private String gender;

    private String email;

    private Integer dId;

    private Department department;

}