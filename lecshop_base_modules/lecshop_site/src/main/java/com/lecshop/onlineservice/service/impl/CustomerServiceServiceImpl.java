package com.lecshop.onlineservice.service.impl;

import com.lecshop.onlineservice.bean.CustomerService;
import com.lecshop.onlineservice.bean.CustomerServiceInfo;
import com.lecshop.onlineservice.mapper.CustomerServiceMapper;
import com.lecshop.onlineservice.service.CustomerServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 在线客服service实现层
 *
 * @author sunluyang on 2017/5/19.
 */
@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CustomerServiceServiceImpl.class);

    /**
     * 注入在线客服实现类
     */
    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 查询在线客服
     *
     * @return 在线客服实体类
     */
    @Override
    public CustomerService queryCustomerService() {
        //查询在线客服设置
        CustomerService customerService = customerServiceMapper.queryCustomerService();
        //查询在线客服信息集合
        List<CustomerServiceInfo> customerServiceInfos = customerServiceMapper.queryCustomerServiceInfo();
        if (!CollectionUtils.isEmpty(customerServiceInfos)) {
            logger.debug("queryCustomerService  customerServiceInfos is empty...");
            customerService.setCustomerServiceInfo(customerServiceInfos);
        }
        return customerService;
    }

    /**
     * 编辑在线客服,先删后增
     *
     * @param customerService 在线客服实体类
     * @return 返回删除结果
     */
    @Override
    @Transactional
    public int editCustomerServiceInfo(CustomerService customerService) {
        if (Objects.isNull(customerService)) {
            logger.error("editCustomerServiceInfo error due to customerService is null");
            return -1;
        }
        customerServiceMapper.deleteCustomerServiceInfo();
        if (!CollectionUtils.isEmpty(customerService.getCustomerServiceInfo())) {
            logger.debug("editCustomerServiceInfo CustomerServiceInfo isNotEmpty");
            customerServiceMapper.addCustomerServiceInfo(customerService.getCustomerServiceInfo());
        }
        return customerServiceMapper.editCustomerService(customerService);
    }
}
