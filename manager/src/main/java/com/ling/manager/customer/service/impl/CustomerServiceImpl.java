package com.ling.manager.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ling.customer.pojo.Customer;
import com.ling.employee.pojo.Employee;
import com.ling.manager.customer.mapper.CustomerMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ling
 * @time 2022/6/29 9:57
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public IPage<Customer> selectByPage(int pageNum, int pageSize) {
        Employee employee = (Employee) redisTemplate.opsForValue().get("login");
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        if(Objects.nonNull(employee)){
            Integer roleId = employee.getRoleId();
            Integer employeeId = employee.getEmployeeId();
            if(roleId.equals(1)){//专员，只查询自己负责的客户
                queryWrapper.eq("employee_id",employeeId);
                IPage<Customer> page = new Page<>(pageNum,pageSize);
                return customerMapper.selectPage(page,queryWrapper);
            }else if(roleId.equals(2)){//主管，只查询负责的专员对应客户
                queryWrapper.eq("manager_id",employeeId);
                IPage<Customer> page = new Page<>(pageNum,pageSize);
                return customerMapper.selectByRole(page,queryWrapper);
            }
            //超管查所有
            IPage<Customer> page = new Page<>(pageNum, pageSize);
            return customerMapper.selectPage(page,null);
        }
        return null;
    }

    /**
     * 把当前登入员工（专员）下的多个客户转移给其他员工
     * @param targetId 其他员工
     * @param customerIds 被转移的客户
     * @return
     */
    @Override
    public int transferCustomer(Integer targetId,Object[] customerIds) {
        Employee employee = (Employee) redisTemplate.opsForValue().get("login");
        if (Objects.nonNull(employee) && employee.getRoleId().equals(1)) {//只有专员可以转移客户
            QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
            Integer employeeId = employee.getEmployeeId();//转移自己的客户
            queryWrapper.eq("employee_id",employeeId).in("customer_id",customerIds);
            return customerMapper.updateOwner(targetId,queryWrapper);
        }
        return 0;
    }
}
