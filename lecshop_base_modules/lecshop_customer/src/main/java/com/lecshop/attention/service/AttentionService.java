package com.lecshop.attention.service;

import com.lecshop.attention.bean.Attention;
import com.lecshop.util.PageHelper;

/**
 * Created by dujinkai on 17/5/27.
 * 商品关注服务接口
 */
public interface AttentionService {

    /**
     * 分页查询会员的商品关注
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回会员的商品关注
     */
    PageHelper<Attention> queryAttentions(PageHelper<Attention> pageHelper, long customerId);

    /**
     * 分页查询会员的商品关注-会员中心
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回会员的商品关注
     */
    PageHelper<Attention> queryAttentionsForCustomerCentre(PageHelper<Attention> pageHelper, long customerId);

    /**
     * 取消关注商品
     *
     * @param skuId      单品id
     * @param customerId 会员id
     * @return 返回删除码
     */
    int cancelAttention(String skuId, long customerId);
}
