package com.ling.manager.visit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.visit.pojo.Visit;


/**
 * @author ling
 * @time 2022/6/28 22:57
 */
public interface VisitService {
    /**
     * 根据当前登入的员工角色，分页查询
     * @return
     */
    IPage<Visit> findAllByPage(int pageNum,int pageSize);
}
