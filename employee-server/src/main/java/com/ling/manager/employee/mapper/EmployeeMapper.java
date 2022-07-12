package com.ling.manager.employee.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.employee.pojo.Employee;
import com.ling.employee.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ling
 * @time 2022/6/27 16:25
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select({"<script>",
            "select id,employee_id,employee_name,password,email,phone,address,info,role_id from tb_employee",
            "<where>",
                "<if test= 'ew != null'>",
                "<if test= 'ew.sqlSegment!=null' >${ew.sqlSegment}</if>",
                "</if>",
            "</where>",
            "</script>"})
    @Results(id = "employeeMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "employee_id",property = "employeeId"),
            @Result(column = "employee_name",property = "employeeName"),
            @Result(column = "password",property = "password"),
            @Result(column = "email",property = "email"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "address",property = "address"),
            @Result(column = "info",property = "info"),
            @Result(column = "role_id",property = "roleId"),
            @Result(column = "role_id",property = "role",javaType = Role.class,
                    one = @One(select = "com.ling.manager.employee.mapper.RoleMapper.selectById" ))
    })
    List<Employee> selectEmployeesWithRoleNameByPage(IPage<Employee> page, @Param("ew") QueryWrapper<Employee> queryWrapper);

    @Select("select e.id,e.employee_id,employee_name,password,email,phone,address,info,role_id from tb_manager m right join tb_employee e " +
            "on m.employee_id = e.employee_id where manager_id = #{employeeId}")
    @ResultMap(value = "employeeMap")
    List<Employee> selectByRole(int employeeId);

}
