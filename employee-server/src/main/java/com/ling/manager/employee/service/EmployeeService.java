package com.ling.manager.employee.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ling.employee.pojo.Employee;

import java.util.List;

/**
 * @author ling
 * @time 2022/6/27 16:31
 */
public interface EmployeeService {
    List<Employee> findAll();
    //一对一关系映射
    List<Employee> selectEmployeesWithRoleName(QueryWrapper<Employee> queryWrapper,Integer pageNum, Integer pageSize);
    List<Employee> selectByRole(Integer employeeId);
    List<Employee> findByPage(Integer pageNum, Integer pageSize);
    Employee loginByNameAndPassword(String employeeName,String password);
    Employee selectById(Integer employeeId);
    int updateById(Employee employee);
    int insert(Employee employee);
    int deleteById(Integer employeeId);

}
