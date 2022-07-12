package com.ling.manager;

import com.ling.employee.pojo.Employee;
import com.ling.manager.employee.mapper.EmployeeMapper;
import com.ling.manager.employee.service.EmployeeService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ling
 * @time 2022/6/28 11:35
 */
@SpringBootTest
public class EmployeeTest {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    void modifyPwdTest() {
        Employee employee = employeeService.selectById(1001);
        employee.setPassword("root");
        System.out.println(employeeService.updateById(employee));
    }

    @Test
    void deleteTest() {
        System.out.println(employeeService.deleteById(666));
    }

    @Test
    void selectByRoleTest(){
        List<Employee> employees = employeeMapper.selectByRole(1002);
        System.out.println(employees);
    }

    @Test
    void roleTest() {
        System.out.println(employeeMapper.selectById(1));
    }
}
