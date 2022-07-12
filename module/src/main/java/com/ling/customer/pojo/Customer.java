package com.ling.customer.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ling
 * @time 2022/6/29 9:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_customer")
public class Customer {
    private Integer id;
    private Integer customerId;
    private String customerName;
    private String phone;
    private String address;
    private String info;
    private Integer employeeId;
}
