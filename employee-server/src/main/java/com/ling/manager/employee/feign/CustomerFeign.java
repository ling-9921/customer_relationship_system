package com.ling.manager.employee.feign;

import com.ling.common.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author ling
 * @time 2022/6/29 21:11
 */
@FeignClient("customer-server")
@RequestMapping("/customer")
public interface CustomerFeign {
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
//    @PostMapping(path = "/transfer")
    ResultVO transferCustomer(@RequestParam(value = "targetId") int targetId, @RequestParam(value = "ids") Integer[] ids);
}
