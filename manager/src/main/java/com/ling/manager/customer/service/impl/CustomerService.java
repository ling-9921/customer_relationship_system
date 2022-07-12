package com.ling.manager.customer.service.impl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.customer.pojo.Customer;

/**
 * @author ling
 * @time 2022/6/29 9:57
 */
public interface CustomerService {

    /**
     * 根据角色分页查询
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return 分页结果
     */
    IPage<Customer> selectByPage(int pageNum,int pageSize);

    int transferCustomer(Integer targetId,Object customerIds[]);

}
