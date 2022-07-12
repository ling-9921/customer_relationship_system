package com.ling.employee.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ling
 * @time 2022/6/28 12:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_role")
public class Role implements Serializable {
    @TableId("id")
    private int roleId;
    private String role;
}
