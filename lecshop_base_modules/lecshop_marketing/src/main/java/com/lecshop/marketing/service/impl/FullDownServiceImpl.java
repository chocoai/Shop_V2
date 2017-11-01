package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.FullDownMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * Created by dujinkai on 17/6/8.
 * 满减服务接口
 */
@Service("fullDownService")
public class FullDownServiceImpl extends MarketingTemplate {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(FullDownServiceImpl.class);

    /**
     * 注入满减促销数据库接口
     */
    @Autowired
    private FullDownMapper fullDownMapper;

    @Override
    public int addMarketingDetail(Marketing marketing) {

        logger.debug("addMarketingDetail and marketing");

        if (!validateParams(marketing)) {
            logger.error("addMarketingDetail fail due to params is error....");
            return 0;
        }

        fullDownMapper.addFullDowns(marketing.getFullDowns());

        return 1;
    }

    @Override
    public int updateMarketingDetail(Marketing marketing) {
        logger.error("updateMarketingDetail and marketing:{}", marketing);
        if (!validateParams(marketing)) {
            logger.error("updateMarketingDetail fail due to params is error...");
            return 0;
        }

        // 删除满减促销
        fullDownMapper.deleteByMarketingId(marketing.getId());

        // 新增满减促销
        addMarketingDetail(marketing);

        return 1;
    }

    @Override
    public void setMarketingDetail(Marketing marketing) {
        logger.debug("setMarketingDetail and marketing:{}", marketing);

        if (Objects.isNull(marketing) || !marketing.isFullDownMarketing()) {
            logger.error("setMarketingDetail fail due to params is error...");
            return;
        }

        marketing.setFullDowns(fullDownMapper.queryByMarketingId(marketing.getId()));
    }

    /**
     * 校验当前促销是否正确
     *
     * @param marketing 促销信息
     * @return 正确返回 1 失败返回0
     */
    private boolean validateParams(Marketing marketing) {
        return Objects.nonNull(marketing) && !CollectionUtils.isEmpty(marketing.getFullDowns()) && marketing.isFullDownMarketing();
    }
}
