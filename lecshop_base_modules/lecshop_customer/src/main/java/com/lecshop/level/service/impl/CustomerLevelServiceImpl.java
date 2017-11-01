package com.lecshop.level.service.impl;

import com.lecshop.level.bean.CustomerLevel;
import com.lecshop.level.mapper.CustomerLevelMapper;
import com.lecshop.level.service.CustomerLevelService;
import com.lecshop.sku.service.SkuMemberPriceServicce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/22.
 * 会员等级服务接口实现类
 */
@Service
public class CustomerLevelServiceImpl implements CustomerLevelService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CustomerLevelServiceImpl.class);

    /**
     * 注入会员等级数据库接口
     */
    @Autowired
    private CustomerLevelMapper customerLevelMapper;

    /**
     * 注入会员等级价格服务接口
     */
    @Autowired
    private SkuMemberPriceServicce skuMemberPriceServicce;

    @Override
    public List<CustomerLevel> queryAllCustomerLevels() {
        logger.debug("begin to queryAllCustomerLevels .....");
        return customerLevelMapper.queryAllCustomerLevels();
    }

    @Override
    public int addCustomerLevel(CustomerLevel customerLevel) {
        logger.debug("addCustomerLevel and customerLevel:{}", customerLevel);

        if (Objects.isNull(customerLevel)) {
            logger.error("addCustomerLevel fail due to customerLevel is null...");
            return 0;
        }

        // 存在交集 返回错误
        if (hasIntersection(customerLevel, queryAllCustomerLevels())) {
            logger.error("addCustomerLevel fail due to hasIntersection");
            return -1;
        }

        return customerLevelMapper.addCustomerLevel(customerLevel);
    }

    /**
     * 判断新增加的会员等级的消费金额和已经存在的会员等级消费金额是否存在交集
     *
     * @param customerLevel 会员等级
     * @return 存在返回true  不存在返回false
     */
    private boolean hasIntersection(CustomerLevel customerLevel, List<CustomerLevel> customerLevels) {
        return customerLevels.stream().map(customerLevel1 -> customerLevel1.hasIntersection(customerLevel)).filter(Boolean::booleanValue).count() >= 1;
    }

    @Override
    public int deleteCustomerLevel(long id) {
        logger.debug("deleteCustomerLevel and id:{}", id);
        skuMemberPriceServicce.deleteByLevelId(id);
        return customerLevelMapper.deleteCustomerLevel(id);
    }

    @Override
    public CustomerLevel queryCustomerLevelById(long id) {
        logger.debug("queryCustomerLevelById and id :{}", id);
        return customerLevelMapper.queryCustomerLevelById(id);
    }

    @Override
    public int updateCustomerLevel(CustomerLevel customerLevel) {
        logger.debug("updateCustomerLevel and customerLevel:{}", customerLevel);

        // 存在交集 返回错误
        if (hasIntersection(customerLevel, queryAllCustomerLevels().stream().filter(customerLevel1 -> customerLevel1.getId() != customerLevel.getId()).collect(Collectors.toList()))) {
            logger.error("updateCustomerLevel fail due to hasIntersection");
            return -1;
        }

        return customerLevelMapper.updateCustomerLevel(customerLevel);
    }

    @Override
    public CustomerLevel queryCustomerLevelByMoney(BigDecimal money) {
        logger.debug("queryCustomerLevelByMoney and money:{}", money);
        return this.queryAllCustomerLevels().parallelStream().filter(customerLevel -> customerLevel.isCurrentLevl(money)).findFirst().orElseGet(CustomerLevel::buildNoLevel);
    }
}
