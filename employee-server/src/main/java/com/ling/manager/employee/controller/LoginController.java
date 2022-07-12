package com.ling.manager.employee.controller;

import com.ling.common.ResultVO;
import com.ling.employee.pojo.Employee;
import com.ling.manager.employee.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/28 11:22
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/emp")
    public ResultVO loginByAccount(@Param("employeeName") String employeeName, @Param("password") String password, HttpServletRequest request) {
        Employee employee = employeeService.loginByNameAndPassword(employeeName, password);
        if (Objects.nonNull(employee)) {
            //放入redis，不用重复登入
            Employee employee1 = new Employee();
            employee1.setEmployeeId(employee.getEmployeeId());
            employee1.setRoleId(employee.getRoleId());
            employee1.setEmployeeName(employeeName);
            redisTemplate.opsForValue().set("login", employee1);
            return ResultVO.ok("登入成功");
        }
        return ResultVO.error("登入失败，请确认是否输入正确");
    }
}
