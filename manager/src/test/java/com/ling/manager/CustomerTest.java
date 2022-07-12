package com.ling.manager;

import com.ling.manager.customer.service.impl.CustomerService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author ling
 * @time 2022/6/29 11:11
 */
@SpringBootTest
public class CustomerTest {
    @Autowired
    CustomerService customerService;

    @Test
    void queryTest(){
        customerService.selectByPage(1,3);
    }

    @Test
    void transferTest(){
        customerService.transferCustomer(1005, null);
    }
}
