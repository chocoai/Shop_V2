package com.lecshop.attention.service;

import com.lecshop.attention.bean.AttentionStore;
import com.lecshop.util.PageHelper;

/**
 * 关注的店铺service
 *
 * @author sunluyang on 2017/7/4.
 */
public interface AttentionStoreService {
    /**
     * 根据店铺id查询关注的店铺
     *
     * @param customerId 会员id
     * @return 关注的店铺信息集合
     */
    PageHelper<AttentionStore> queryAttentionByCustomerId(PageHelper<AttentionStore> pageHelper, long customerId);

    /**
     * 根据店铺id和会员id取消关注
     *
     * @param storeId    店铺id
     * @param customerId 会员id
     * @return 删除返回码
     */
    int cancelStoreAttention(long storeId, long customerId);
}
