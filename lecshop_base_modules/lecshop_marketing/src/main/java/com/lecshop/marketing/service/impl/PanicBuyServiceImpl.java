package com.lecshop.marketing.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.mapper.PanicBuyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 17/6/8.
 * 抢购服务接口实现
 */
@Service("panicBuyService")
public class PanicBuyServiceImpl extends MarketingTemplate {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(PanicBuyServiceImpl.class);

    /**
     * 注入抢购数据库接口
     */
    @Autowired
    private PanicBuyMapper panicBuyMapper;

    @Override
    public int addMarketingDetail(Marketing marketing) {

        logger.debug("addMarketingDetail and marketing:{}", marketing);

        if (!validateParams(marketing)) {
            logger.error("addMarketingDetail fail due to param is error....");
            return 0;
        }

        return panicBuyMapper.addPanicBuy(marketing.getPanicBuy());
    }

    @Override
    public int updateMarketingDetail(Marketing marketing) {
        logger.debug("updateMarketingDetail and marketing:{}", marketing);

        if (!validateParams(marketing)) {
            logger.error("updateMarketingDetail fail due to params is error...");
            return 0;
        }
        return panicBuyMapper.updatePanicBuy(marketing.getPanicBuy());
    }


    @Override
    public void setMarketingDetail(Marketing marketing) {
        logger.debug("setMarketingDetail and marketing:{}", marketing);

        if (Objects.isNull(marketing) || !marketing.isPanicBuyMarketing()) {

            logger.error("setMarketingDetail fail due to params is error....");
            return;
        }

        marketing.setPanicBuy(panicBuyMapper.queryByMarketingId(marketing.getId()));
    }

    /**
     * 校验当前促销是否正确
     *
     * @param marketing 促销信息
     * @return 正确返回true 不正确返回false
     */
    private boolean validateParams(Marketing marketing) {
        return Objects.nonNull(marketing) && Objects.nonNull(marketing.getPanicBuy()) && marketing.isPanicBuyMarketing();
    }
}
