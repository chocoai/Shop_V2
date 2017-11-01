package com.lecshop.onlineservice.mapper;

import com.lecshop.onlineservice.bean.CustomerService;
import com.lecshop.onlineservice.bean.CustomerServiceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在线客服mapper层
 *
 * @author sunluyang on 2017/5/19.
 */
@Repository
public interface CustomerServiceMapper {

    /**
     * 查询在线客服
     *
     * @return 在线客服实体类
     */
    CustomerService queryCustomerService();

    /**
     * 查询在线客服联系方式
     *
     * @return 在线客服联系方式实体类
     */
    List<CustomerServiceInfo> queryCustomerServiceInfo();

    /**
     * 编辑在线客服
     *
     * @param customerService 在线客服实体类
     * @return 返回编辑行数
     */
    int editCustomerService(CustomerService customerService);

    /**
     * 批量添加在线客服信息
     *
     * @param customerServiceInfo 在线客服信息实体类
     * @return 返回编辑行数
     */
    int addCustomerServiceInfo(List<CustomerServiceInfo> customerServiceInfo);

    /**
     * 删除在线客服信息
     *
     * @return 返回删除行数
     */
    int deleteCustomerServiceInfo();
}
