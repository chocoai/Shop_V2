package com.lecshop.marketing.service;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/6/8.
 * 促销查询接口
 */
public interface MarketingQueryService {

    /**
     * 分页查询促销信息
     *
     * @param pageHelper 分页帮助类
     * @param name       促销名称
     * @param type       促销类型
     * @param storeId    店铺id
     * @return 返回促销信息
     */
    PageHelper<Marketing> queryMarketings(PageHelper<Marketing> pageHelper, String name, String type, long storeId);


    /**
     * 各级id查询促销信息
     *
     * @param id      营销id
     * @param storeId 店铺id
     * @return 返回营销信息(营销的基本信息 和营销的具体信息)
     */
    Marketing queryMarketingById(long id, long storeId);

    /**
     * 查询单品放入购物车中的默认促销id (促销主要是 满减和满折 随机查出一个)
     *
     * @param skuId 单品id
     * @return 返回可用的满减和满折的促销id
     */
     Marketing queryMarketingForShoppingCart(String skuId);

}
