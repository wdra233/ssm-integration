package com.eric.crud.service;

import com.eric.crud.bean.Employee;
import com.eric.crud.bean.EmployeeExample;
import com.eric.crud.bean.Msg;
import com.eric.crud.dao.EmployeeMapper;
import org.aspectj.weaver.bcel.BcelConstantPoolReader;
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

    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andLastNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        // 当前数据库没有这条记录，name可用
        return count == 0;
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        // delete from XXX where emp_id in (1,2,3)
        employeeMapper.deleteByExample(example);
    }
}
