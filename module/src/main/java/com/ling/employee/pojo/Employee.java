package com.ling.employee.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TbEmployee)实体类
 *
 * @author ling
 * @since 2022-06-27 16:22:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_employee")
public class Employee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer employeeId;

    private String employeeName;

    private String password;

    private String email;

    private String phone;

    private String address;

    private String info;
    /**
     * 0.专员 1.主管 2.超管
     */
    private Integer roleId;

    @TableField(exist = false)
    private Role role;
}

