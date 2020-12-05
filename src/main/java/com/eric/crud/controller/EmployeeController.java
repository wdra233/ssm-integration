package com.eric.crud.controller;


import com.eric.crud.bean.Employee;
import com.eric.crud.bean.Msg;
import com.eric.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    @RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        List<Employee> employeeList = employeeService.getAll();
        // 传入当前页码以及页面大小
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo(employeeList, 5);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    @RequestMapping("/emps")
    // response body 需要jackson转换成json
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        List<Employee> employeeList = employeeService.getAll();
        // 传入当前页码以及页面大小
        PageHelper.startPage(pn, 5);
        PageInfo pageInfo = new PageInfo(employeeList, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @PostMapping(value = "/emp")
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.saveEmp(employee);
        return Msg.success();
    }





}
