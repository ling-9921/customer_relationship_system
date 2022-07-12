package com.ling.visit.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ling
 * @time 2022/6/28 22:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_visit")
public class Visit {
    private Integer id;
    private Integer customerId;
    private Integer employeeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Data visitTime;
    private String info;
}
