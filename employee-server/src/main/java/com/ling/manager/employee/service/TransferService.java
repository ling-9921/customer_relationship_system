package com.ling.manager.employee.service;

/**
 * @author ling
 * @time 2022/6/29 17:07
 */
public interface TransferService {
    int addTransferLog(Integer targetId,Integer[] customerIds);
}
