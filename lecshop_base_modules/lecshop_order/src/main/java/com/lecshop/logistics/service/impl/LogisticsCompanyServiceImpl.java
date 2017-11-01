package com.lecshop.logistics.service.impl;

import com.lecshop.logistics.bean.LogisticsCompany;
import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.logistics.mapper.LogisticsCompanyMapper;
import com.lecshop.logistics.service.LogisticsCompanyService;
import com.lecshop.logistics.service.LogisticsTemplateService;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dujinkai on 17/5/15.
 * 物流公司服务接口实现
 */
@Service
public class LogisticsCompanyServiceImpl implements LogisticsCompanyService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LogisticsCompanyServiceImpl.class);

    /**
     * 注入物流公司数据库接口
     */
    @Autowired
    private LogisticsCompanyMapper logisticsCompanyMapper;

    /**
     * 注入物流模版服务接口
     */
    @Autowired
    private LogisticsTemplateService logisticsTemplateService;

    @Override
    public int addLogisticsCompany(LogisticsCompany logisticsCompany) {

        logger.debug("addLogisticsCompany and logisticsCompany:{}", logisticsCompany);

        if (Objects.isNull(logisticsCompany)) {
            logger.error("addLogisticsCompany fail due to logisticsCompany is null...");
            return 0;
        }
        return logisticsCompanyMapper.addLogisticsCompany(logisticsCompany);
    }

    @Override
    public PageHelper<LogisticsCompany> queryLogisticsCompanys(PageHelper<LogisticsCompany> pageHelper, String name, long storeId) {
        logger.debug("queryLogisticsCompanys and pageHelper:{},name:{} ,storeId:{}", pageHelper, name, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("storeId", storeId);
        return pageHelper.setListDates(logisticsCompanyMapper.queryLogisticsCompanys(pageHelper.getQueryParams(params, logisticsCompanyMapper.queryLogisticsCompanyCount(params))));
    }

    @Override
    public int deleteLogisticsCompany(long id, long storeId) {

        logger.debug("deleteLogisticsCompany and id:{},storeId:{}", id, storeId);

        if (!isCanDelete(id)) {
            logger.error("deleteLogisticsCompany fail due to there is template use this company....");
            return -1;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return logisticsCompanyMapper.deleteLogisticsCompany(params);
    }


    /**
     * 判断物流公司是否可以删除
     *
     * @param id 物流公司id
     * @return 物流公司关联了物流模版则不能删除  返回false  否则返回true
     */
    private boolean isCanDelete(long id) {
        return logisticsTemplateService.queryCountByCompanyId(id) == 0;
    }

    @Override
    public int deleteLogisticsCompanys(Long[] ids, long storeId) {

        logger.debug("deleteLogisticsCompanys and ids :{} storeId:{}", ids, storeId);

        if (ArrayUtils.isEmpty(ids)) {
            logger.error("deleteLogisticsCompanys fail due to ids is null...");
            return 0;
        }

        // 判断规格是否可以删除
        if (Arrays.stream(ids).map(this::isCanDelete).filter(Boolean::booleanValue).count() != ids.length) {
            logger.error("deleteLogisticsCompanys fail due to there is template use this company....");
            return -1;
        }

        Arrays.stream(ids).forEach(id -> deleteLogisticsCompany(id, storeId));

        return 1;
    }

    @Override
    public LogisticsCompany queryLogisticsCompanyById(long id, long storeId) {

        logger.debug("queryLogisticsCompanyById and id:{} , storeId:{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        return logisticsCompanyMapper.queryLogisticsCompanyById(params);
    }

    @Override
    public int updateLogisticsCompany(LogisticsCompany logisticsCompany) {
        logger.debug("updateLogisticsCompany and logisticsCompany:{}", logisticsCompany);

        if (Objects.isNull(logisticsCompany)) {
            logger.error("updateLogisticsCompany fail due to logisticsCompany is null....");
            return 0;
        }
        return logisticsCompanyMapper.updateLogisticsCompany(logisticsCompany);
    }

    @Override
    public List<LogisticsCompany> queryCanUseCompany(long storeId) {

        logger.debug("queryCanUseTemplates and storeId:{}", storeId);

        return logisticsCompanyMapper.queryCanUseCompany(storeId);
    }
}
