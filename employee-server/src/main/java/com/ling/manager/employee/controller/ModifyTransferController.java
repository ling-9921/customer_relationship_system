package com.ling.manager.employee.controller;

import com.ling.common.ResultVO;
import com.ling.manager.employee.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @time 2022/6/29 17:09
 */
@RestController
@RequestMapping("/emp")
public class ModifyTransferController {
    @Autowired
    TransferService transferService;

    @PostMapping("/transfer")
    public ResultVO addTransferLog(@RequestParam Integer targetId,@RequestParam Integer[] ids) {
       int i = transferService.addTransferLog(targetId,ids);
        if (i > 0) {
            return ResultVO.ok("添加了"+i+"条转移日志");
        }
        return ResultVO.error("添加转移日志失败");
    }
}
