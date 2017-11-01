package com.lecshop.point.service.impl;

import com.lecshop.point.bean.CustomerPoint;
import com.lecshop.point.mapper.CustomerPointMapper;
import com.lecshop.point.service.CustomerPointService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/25.
 * 会员积分服务接口实现
 */
@Service
public class CustomerPointServiceImpl implements CustomerPointService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CustomerPointServiceImpl.class);

    /**
     * 注入会员积分数据库接口
     */
    @Autowired
    private CustomerPointMapper customerPointMapper;

    @Override
    public PageHelper<CustomerPoint> queryCustomerPoints(PageHelper<CustomerPoint> pageHelper, long customerId) {
        logger.debug("queryCustomerPoints and pageHelper:{} \r\n customerId:{}", pageHelper, customerId);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        return pageHelper.setListDates(customerPointMapper.queryCustomerPoints(pageHelper.getQueryParams(params, customerPointMapper.queryCustomerPointCount(params))));
    }

    @Override
    public int queryCustomerPointCount(long customerId) {
        logger.debug("queryCustomerPointCount and customerId:{}", customerId);
        Integer point = customerPointMapper.queryCustomerPointAllCount(customerId);
        return Objects.isNull(point) ? 0 : point;
    }

    @Override
    public int addCustomerPoint(CustomerPoint customerPoint) {
        logger.debug("addCustomerPoint and customerPoint:{}", customerPoint);

        if (Objects.isNull(customerPoint)) {
            logger.error("addCustomerPoint fail due to params is null...");
            return 0;
        }
        return customerPointMapper.addCustomerPoint(customerPoint);
    }
}
