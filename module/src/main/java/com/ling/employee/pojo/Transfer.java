package com.ling.employee.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ling
 * @time 2022/6/29 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_transfer")
public class Transfer {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer customerId;
    private Integer fromEmpId;
    private Integer toEmpId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date transferTime;
}
