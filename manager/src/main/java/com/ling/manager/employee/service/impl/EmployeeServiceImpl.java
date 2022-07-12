package com.ling.manager.employee.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ling.employee.pojo.Employee;
import com.ling.manager.employee.mapper.EmployeeMapper;
import com.ling.manager.employee.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ling
 * @time 2022/6/27 16:48
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findAll() {
        return employeeMapper.selectList(null);
    }

    @Override
    public List<Employee> selectEmployeesWithRoleName(QueryWrapper<Employee> queryWrapper,Integer pageNum, Integer pageSize) {
        IPage<Employee> page = new Page<>(pageNum,pageSize);
        return employeeMapper.selectEmployeesWithRoleNameByPage( page,queryWrapper);
    }

    @Override
    public List<Employee> findByPage(Integer pageNum, Integer pageSize) {
        IPage<Employee> page = new Page<>(pageNum,pageSize);
        IPage<Employee> employeeIPage = employeeMapper.selectPage(page, null);
        return employeeIPage.getRecords();
    }

    @Override
    public Employee loginByNameAndPassword(String employeeName, String password) {
        QueryWrapper<Employee> empQueryWrapper = new QueryWrapper<>();
        empQueryWrapper.select("employee_name","employee_id","role_id").eq("employee_name",employeeName)
                .eq("password",password);
        return employeeMapper.selectOne(empQueryWrapper);
    }

    @Override
    public List<Employee> selectByRole(Integer employeeId) {
        return employeeMapper.selectByRole(employeeId);
    }

    @Override
    public Employee selectById(Integer employeeId) {
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id",employeeId);
        return employeeMapper.selectOne(queryWrapper);
    }

    @Override
    public int updateById(Employee employee) {
       return employeeMapper.updateById(employee);
    }

    @Override
    public int insert(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int deleteById(Integer employeeId) {
        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("employee_id",employeeId);
        return employeeMapper.delete(updateWrapper);
    }
}
