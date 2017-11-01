package com.lecshop.marketing.mapper;

import com.lecshop.marketing.bean.PanicBuy;

/**
 * Created by dujinkai on 17/6/8.
 * 抢购数据库接口
 */
public interface PanicBuyMapper {

    /**
     * 新增抢购
     *
     * @param panicBuy 抢购实体
     * @return 成功返回1 失败返回0
     */
    int addPanicBuy(PanicBuy panicBuy);

    /**
     * 根据促销id查询抢购 信息
     *
     * @param marketingId 促销id
     * @return 返回抢购信息
     */
    PanicBuy queryByMarketingId(long marketingId);

    /**
     * 更新抢购
     *
     * @param panicBuy 抢购信息
     * @return 成功返回1 失败返回0
     */
    int updatePanicBuy(PanicBuy panicBuy);
}
