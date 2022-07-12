package com.ling.manager.customer.controller;

import com.ling.common.ResultVO;
import com.ling.manager.customer.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultVO transferCustomer(@RequestParam("targetId") int targetId,Integer[] ids){
        int i = customerService.transferCustomer(targetId, ids);
        if (i > 0) {
            return ResultVO.ok("转移成功");
        }
        return ResultVO.error("转移失败");
    }
}
