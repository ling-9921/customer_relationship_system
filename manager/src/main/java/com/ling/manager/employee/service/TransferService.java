package com.ling.manager.employee.service;

import com.ling.employee.pojo.Transfer;

/**
 * @author ling
 * @time 2022/6/29 17:07
 */
public interface TransferService {
    int addTransferLog(Integer targetId,Integer[] customerIds);
}
