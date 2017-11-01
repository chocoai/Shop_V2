package com.lecshop.logistics.service.impl;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.logistics.mapper.LogisticsTemplateMapper;
import com.lecshop.logistics.service.LogisticsCompanyService;
import com.lecshop.logistics.service.LogisticsTemplateService;
import com.lecshop.logistics.service.ShippingMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/31.
 * 物流模版服务接口实现
 */
@Service
public class LogisticsTemplateServiceImpl implements LogisticsTemplateService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LogisticsTemplateServiceImpl.class);

    /**
     * 注入数据库服务接口
     */
    @Autowired
    private LogisticsTemplateMapper logisticsTemplateMapper;

    /**
     * 注入运费方式服务接口
     */
    @Autowired
    private ShippingMethodService shippingMethodService;

    /**
     * 注入物流公司服务接口
     */
    @Autowired
    private LogisticsCompanyService logisticsCompanyService;


    @Override
    public List<LogisticsTemplate> queryAllTemplates(long storeId) {
        logger.debug("queryAllTemplates and storeId:{}", storeId);
        return logisticsTemplateMapper.queryAllTemplates(storeId);
    }

    @Override
    public int addLogisticsTemplate(LogisticsTemplate logisticsTemplate) {
        logger.debug("addLogisticsTemplate and logisticsTemplate:{}", logisticsTemplate);

        // 新增运费模版主表
        logisticsTemplateMapper.addLogisticsTemplate(logisticsTemplate);

        // 设置运费方式的模版id
        logisticsTemplate.setShippingMethodTemplateId();

        // 新增运费方式
        shippingMethodService.addShippingMethod(logisticsTemplate.getShippingMethods());

        return 1;
    }

    @Transactional
    @Override
    public int setDefaultTemplate(long id, long storeId) {
        logger.debug("setDefaultTemplate and id:{} , storeId:{}", id, storeId);

        // 首先把所有的运费模版都改成非默认
        logisticsTemplateMapper.setLogisticsTemplateUnDefault(storeId);

        // 然后把当前的模版改成默认模版
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return logisticsTemplateMapper.setDefaultLogisticsTemplate(params);
    }

    @Override
    public LogisticsTemplate queryLogisticsTemplate(long id, long storeId) {
        logger.debug("queryLogisticsTemplate and id:{},storeId:{}", id, storeId);

        LogisticsTemplate logisticsTemplate = this.queryLogisticsTemplateWithCompany(id, storeId);

        if (Objects.isNull(logisticsTemplate)) {
            return logisticsTemplate;
        }

        logisticsTemplate.setShippingMethods(shippingMethodService.queryByTemplateId(id));

        return logisticsTemplate;
    }

    @Override
    public int deleteLogisticsTemplate(long id, long storeId) {
        logger.debug("deleteLogisticsTemplate id:{} \r\n storeId:{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        LogisticsTemplate logisticsTemplate = logisticsTemplateMapper.queryLogisticsTemplate(params);

        if (Objects.isNull(logisticsTemplate)) {
            return 1;
        }

        if (logisticsTemplate.isDefaultTemplate()) {
            logger.error("deleteLogisticsTemplate due to current template is default template....");
            return -1;
        }

        logisticsTemplateMapper.deleteLogisticsTemplate(params);

        // 删除运费方式
        shippingMethodService.deleteByTemplateId(id);

        return 1;
    }

    @Transactional
    @Override
    public int updateLogisticsTemplate(LogisticsTemplate logisticsTemplate) {
        logger.debug("updateLogisticsTemplate and logisticsTemplate:{}", logisticsTemplate);

        if (Objects.isNull(logisticsTemplate)) {
            logger.error("updateLogisticsTemplate fail due to logisticsTemplate is null...");
            return -1;
        }

        // 更新物流模版
        logisticsTemplateMapper.updateLogisticsTemplate(logisticsTemplate);


        // 设置运费方式的模版id
        logisticsTemplate.setShippingMethodTemplateId();

        // 更新运费方式
        shippingMethodService.updateShippingMethods(logisticsTemplate.getShippingMethods(), logisticsTemplate.getId());


        return 1;
    }

    @Override
    public int queryCountByCompanyId(long companyId) {
        logger.debug("queryCountByCompanyId and companyId:{}", companyId);
        return logisticsTemplateMapper.queryCountByCompanyId(companyId);
    }

    @Override
    public LogisticsTemplate queryLogisticsTemplateWithCompany(long id, long storeId) {
        logger.debug("queryLogisticsTemplateWithCompany and id:{} \r\n storeId;{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        LogisticsTemplate logisticsTemplate = logisticsTemplateMapper.queryLogisticsTemplate(params);

        if (Objects.isNull(logisticsTemplate)) {
            return logisticsTemplate;
        }

        logisticsTemplate.setLogisticsCompany(logisticsCompanyService.queryLogisticsCompanyById(logisticsTemplate.getCompanyId(), storeId));

        return logisticsTemplate;
    }
}
