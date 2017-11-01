package com.lecshop.marketing.mapper;

import com.lecshop.marketing.bean.Marketing;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/7.
 * 促销数据库接口
 */
public interface MarketingMapper {

    /**
     * 查询促销的总记录数
     *
     * @param params 查询参数
     * @return 返回促销总记录数
     */
    int queryMarketingCount(Map<String, Object> params);

    /**
     * 分页查询促销信息
     *
     * @param params 参数
     * @return 返回促销信息
     */
    List<Marketing> queryMarketings(Map<String, Object> params);

    /**
     * 新增促销
     *
     * @param marketing 促销信息
     * @return 成功返回 1 失败返回0
     */
    int addMarketing(Marketing marketing);

    /**
     * 查询促销信息
     *
     * @param params 查询参数
     * @return 返回促销信息
     */
    Marketing queryMarketingById(Map<String, Object> params);

    /**
     * 更新促销信息
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    int updateMarketing(Marketing marketing);

    /**
     * 删除促销
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int deleteMarketing(Map<String, Object> params);

    /**
     * 查询加入购物车的时候 单品的默认促销(主要是满减和满折促销 随机查出一个)
     *
     * @param skuId 单品id
     * @return 返回满减满折促销
     */
    Marketing queryMarketingForShoppingCart(String skuId);
}
