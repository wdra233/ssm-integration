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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if(result.hasErrors()) {
            // JSR303校验失败
            Map<String, Object> errMap = new HashMap<>();
            List<FieldError> errs = result.getFieldErrors();
            for(FieldError err : errs) {
                errMap.put(err.getField(), err.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", errMap);

        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    @ResponseBody
    @GetMapping("/checkuser")
    public Msg checkUser(@RequestParam("empName")String empName) {
        boolean result = employeeService.checkUser(empName);
        if (result) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }


    @GetMapping(value = "/emp/{id}")
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    @PutMapping("/emp/{empId}")
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }


    /**
     * single delete: 1
     * batch delete: 1-2-3
     * @param ids
     * @return
     */
    @DeleteMapping("/emp/{ids}")
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("ids") String ids) {
        if(ids.contains("-")) {
            String[] strIds = ids.split("-");
            List<Integer> delIds = Arrays.stream(strIds)
                    .map(str_id -> Integer.parseInt(str_id))
                    .collect(Collectors.toList());
            employeeService.deleteBatch(delIds);
        } else {
            int id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();

    }

}
