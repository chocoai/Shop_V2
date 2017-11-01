package com.lecshop.marketing.mapper;

import com.lecshop.marketing.bean.GroupBuy;

/**
 * Created by dujinkai on 17/6/8.
 * 团购数据库接口
 */
public interface GroupBuyMapper {

    /**
     * 新增团购信息
     *
     * @param groupBuy 团购信息
     * @return 成功返回1 失败返回0
     */
    int addGroupBuy(GroupBuy groupBuy);

    /**
     * 根据促销id 查询团购信息
     *
     * @param marketingId 促销id
     * @return 返回团购信息
     */
    GroupBuy queryGroupBuyByMarketingId(long marketingId);

    /**
     * 更新团购信息
     *
     * @param groupBuy 团购
     * @return 成功返回1 失败返回0
     */
    int updateGroupBuy(GroupBuy groupBuy);
}
