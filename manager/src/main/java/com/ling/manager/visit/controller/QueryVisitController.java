package com.ling.manager.visit.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.common.ResultVO;
import com.ling.manager.visit.mapper.VisitMapper;
import com.ling.manager.visit.service.VisitService;
import com.ling.visit.pojo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling
 * @time 2022/6/28 23:06
 */
@RestController
@RequestMapping("/visit")
public class QueryVisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping("/page/role/{pageNum}/{pageSize}")
    public ResultVO getVisits(@PathVariable int pageNum, @PathVariable int pageSize) {
        IPage<Visit> page = visitService.findAllByPage(pageNum,pageSize);
        if (page == null) {
            return ResultVO.error("查询失败");
        }
        return ResultVO.ok("查询成功",page);
    }
}
