package com.ling.manager.employee.service.impl;

import com.ling.employee.pojo.Employee;
import com.ling.employee.pojo.Transfer;
import com.ling.manager.employee.mapper.TransferMapper;
import com.ling.manager.employee.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author ling
 * @time 2022/6/29 17:08
 */
@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    TransferMapper transferMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public int addTransferLog(Integer targetId,Integer[] customerIds) {
        Employee employee = (Employee) redisTemplate.opsForValue().get("login");
        assert employee != null;
        int rowCount = 0;
        for (Integer id : customerIds) {
            Transfer transfer = new Transfer();
            transfer.setCustomerId(id);
            transfer.setFromEmpId(employee.getEmployeeId());
            transfer.setToEmpId(targetId);
            //向tb_transfer插入记录
            rowCount += transferMapper.insert(transfer);
        }
        //调用customer服务，更新tb_customer表

        return rowCount;
    }
}
