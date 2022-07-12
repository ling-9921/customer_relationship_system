package com.ling.manager.visit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ling.employee.pojo.Employee;
import com.ling.manager.visit.mapper.VisitMapper;
import com.ling.manager.visit.service.VisitService;
import com.ling.visit.pojo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/28 22:57
 */
@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    VisitMapper visitMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public IPage<Visit> findAllByPage(int pageNum,int pageSize) {
        Employee employee = (Employee) redisTemplate.opsForValue().get("login");
        QueryWrapper<Visit> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(employee)) {
            Integer roleId = employee.getRoleId();
            if (roleId.equals(1)) {//如果是专员则直接查询
                queryWrapper.eq("manager_id",employee.getEmployeeId());
                IPage<Visit> page = new Page<>(pageNum,pageSize);
                return visitMapper.selectPage(page, queryWrapper);
            }else if (roleId.equals(2)){//如果是主管，则查询管理的专员有哪些客户
                queryWrapper.eq("manager_id",employee.getEmployeeId());
                Page<Visit> page = new Page<>(pageNum, pageSize);
                return visitMapper.selectByRole(page,queryWrapper);
            }
            //超管查询不到
            return null;
        }
        //正常不会走这，能访问这个接口，肯定是redis里有”login“数据
        return null;
    }
}
