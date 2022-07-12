package com.ling.manager.employee.controller;

import com.ling.common.ResultVO;
import com.ling.employee.pojo.Employee;
import com.ling.manager.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @time 2022/6/28 11:44
 */
@RestController
@RequestMapping("/emp")
public class ModifyEmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    RedisTemplate redisTemplate;

    //更新不需要做权限校验，只需要提供给前端当前用户可以更新的内容(即查询需要校验）
    @PostMapping("/update")
    public ResultVO update(@RequestBody Employee employee){
        int i = employeeService.updateById(employee);
        if (i > 0) {
            return ResultVO.ok("update OK");
        }
        return ResultVO.error("更新失败",employee);
    }

    @PostMapping("/add")
    public ResultVO add(@RequestBody  Employee employee){
        int i = employeeService.insert(employee);
        if (i > 0) {
            return ResultVO.ok("update OK");
        }
        return ResultVO.error("添加失败",employee);
    }

}
