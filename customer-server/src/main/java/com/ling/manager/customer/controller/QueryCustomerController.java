package com.ling.manager.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.common.ResultVO;
import com.ling.customer.pojo.Customer;
import com.ling.manager.customer.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/29 11:28
 */
@RestController
@RequestMapping("/customer")
public class QueryCustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/page/role/{pageNum}/{pageSize}")
    public ResultVO getPage(@PathVariable int pageNum, @PathVariable int pageSize){
        IPage<Customer> page = customerService.selectByPage(pageNum, pageSize);
        if (Objects.nonNull(page)){
            return ResultVO.ok("查询成功",page);
        }
        return ResultVO.error("查询失败");
    }
}
