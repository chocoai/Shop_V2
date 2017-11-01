package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.GroupBuyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 17/6/8.
 * 团购服务接口实现
 */
@Service("groupBuyService")
public class GroupBuyServiceImpl extends MarketingTemplate {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(GroupBuyServiceImpl.class);

    /**
     * 注入团购数据库接口
     */
    @Autowired
    private GroupBuyMapper groupBuyMapper;

    @Override
    public int addMarketingDetail(Marketing marketing) {
        logger.debug("addGroupBuy and marketing:{}", marketing);

        if (!validateMarketing(marketing)) {
            logger.error("addGroupBuy fail due to params is null...");
            return 0;
        }

        return groupBuyMapper.addGroupBuy(marketing.getGroupBuy());
    }


    /**
     * 设置团购详情
     *
     * @param marketing 促销信息
     */
    @Override
    public void setMarketingDetail(Marketing marketing) {
        logger.debug("setMarketingDetail and marketing:{}", marketing);

        if (Objects.isNull(marketing) || !marketing.isGroupBuyMarketing()) {
            logger.error("setMarketingDetail fail due to params is error...");
            return;
        }

        marketing.setGroupBuy(groupBuyMapper.queryGroupBuyByMarketingId(marketing.getId()));
    }

    /**
     * 更新团购信息
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    @Override
    public int updateMarketingDetail(Marketing marketing) {
        logger.debug("updateMarketingDetail and marketing:{}", marketing);

        if (!validateMarketing(marketing)) {
            logger.error("updateMarketingDetail fail due marketing is null...");
            return 0;
        }
        return groupBuyMapper.updateGroupBuy(marketing.getGroupBuy());
    }

    /**
     * 校验促销
     *
     * @param marketing 促销
     * @return 成功返回true  失败返回false  主要校验参数是否为空,当前的促销是否正确
     */
    private boolean validateMarketing(Marketing marketing) {
        return Objects.nonNull(marketing) && Objects.nonNull(marketing.getGroupBuy()) && marketing.isGroupBuyMarketing();
    }
}
