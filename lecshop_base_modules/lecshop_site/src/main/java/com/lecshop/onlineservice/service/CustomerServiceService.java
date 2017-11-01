package com.lecshop.onlineservice.service;

import com.lecshop.onlineservice.bean.CustomerService;

/**
 * 在线客服service接口层
 *
 * @author sunluyang on 2017/5/19.
 */
public interface CustomerServiceService {
    /**
     * 查询在线客服
     *
     * @return 在线客服实体类
     */
    CustomerService queryCustomerService();

    /**
     * 编辑在线客服
     *
     * @param customerService 在线客服实体类
     * @return 返回编辑行数
     */
    int editCustomerServiceInfo(CustomerService customerService);
}
