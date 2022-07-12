package com.ling.manager.customer.controller;

import com.ling.common.ResultVO;
import com.ling.customer.pojo.Customer;
import com.ling.manager.customer.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/29 15:57
 */
@RestController
@RequestMapping("/customer")
public class ModifyCustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/transfer")
    public ResultVO transferCustomer(@RequestParam int targetId, @RequestParam Object[] ids){
        int i = customerService.transferCustomer(targetId, ids);
        if (i > 0) {
            return ResultVO.ok("转移成功");
        }
        return ResultVO.error("转移失败");
    }
}
