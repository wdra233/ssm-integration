package com.eric.crud.service;

import com.eric.crud.bean.Employee;
import com.eric.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectAllWithDept();
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

//    public boolean checkUser(String empName) {
//        employeeMapper.
//
//    }
}
