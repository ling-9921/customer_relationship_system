package com.ling.manager.employee.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.common.ResultVO;
import com.ling.employee.pojo.Employee;
import com.ling.manager.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/27 16:51
 */
@RestController
@RequestMapping("/emp")
public class QueryEmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/all")
    public ResultVO getAll() {
        List<Employee> employees = employeeService.findAll();
        if (Objects.nonNull(employees)) {
            return ResultVO.ok("查询成功", employees);
        }
        return null;
    }

    @GetMapping("/page/{pageNum}/{pageSize}")
    public ResultVO getEmpByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Employee> byPage = employeeService.findByPage(pageNum, pageSize);
        if (Objects.nonNull(byPage)) {
            return ResultVO.ok("分页查询成功", byPage);
        }
        return ResultVO.error("分页查询失败");
    }

//    @GetMapping("page/role/{pageNum}/{pageSize}")
//    public ResultVO getEmpWithRoleByPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
//        List<Employee> employees = employeeService.selectEmployeesWithRoleName(null,pageNum,pageSize);
//        if(Objects.nonNull(employees)){
//            return ResultVO.ok("查询成功",employees);
//        }
//        return ResultVO.error("查询失败");
//    }

    @GetMapping("page/role/{pageNum}/{pageSize}")
    public ResultVO getEmpByRole(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Employee employee = (Employee) redisTemplate.opsForValue().get("login");
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        assert employee != null;
        Integer roleId = employee.getRoleId();
        if (roleId == 3) {
            List<Employee> employees = employeeService.selectEmployeesWithRoleName(null, pageNum, pageSize);
            if (Objects.nonNull(employees)) {
                return ResultVO.ok("超级管理员查询", employees);
            }
        } else if (roleId == 2) {
            List<Employee> employees = employeeService.selectByRole(employee.getEmployeeId());
            if (Objects.nonNull(employees)) {
                for (Employee employee1 : employees) {
                    employee1.setPassword("无权访问");
                }
                return ResultVO.ok("主管查询", employees);
            }
        }
        queryWrapper.eq("employee_id", employee.getEmployeeId());
        List<Employee> employees = employeeService.selectEmployeesWithRoleName(queryWrapper, pageNum, pageSize);
        if (Objects.nonNull(employees)) {
            return ResultVO.ok("个人信息查询", employees);
        }
        return ResultVO.error("查询失败");
    }


}
