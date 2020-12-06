package com.eric.crud.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer empId;

    @Pattern(regexp = "(^[a-zA-Z0-9]).....", message = "msg error:username validation")
    private String lastName;

    private String gender;

    @Email
    private String email;

    private Integer dId;

    private Department department;

}