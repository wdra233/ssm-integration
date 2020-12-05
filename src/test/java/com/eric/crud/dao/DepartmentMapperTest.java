package com.eric.crud.dao;

import com.eric.crud.bean.Department;
import com.eric.crud.bean.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepartmentMapperTest {
    @Autowired
    DepartmentMapper mapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testInsert() {
        // 批量插入
        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
        for(int i = 0; i < 100; i++) {
            departmentMapper.insert(new Department(null, "developer"));
        }
    }

    @Test
    public void testEmployeeInsert() {
        // 批量插入
//        employeeMapper.insert(new Employee(null, "eric", "M", "eric@gmail.com", 402, null));
        Employee selectedEmployee = employeeMapper.selectByPrimaryKeyWithDept(1);
        System.out.println(selectedEmployee.toString());
    }
}