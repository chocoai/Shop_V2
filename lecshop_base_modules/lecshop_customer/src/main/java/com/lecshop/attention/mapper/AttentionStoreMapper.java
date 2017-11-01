package com.lecshop.attention.mapper;


import com.lecshop.attention.bean.AttentionStore;

import java.util.List;
import java.util.Map;

/**
 * 关注的店铺mapper
 *
 * @author sunluyang on 2017/7/4.
 */
public interface AttentionStoreMapper {
    /**
     * 根据店铺id查询关注的店铺
     *
     * @param map 查询参数
     * @return 关注的店铺信息集合
     */
    List<AttentionStore> queryAttentionByCustomerId(Map<String, Object> map);

    /**
     * 根据店铺id查询关注的店铺个数
     *
     * @param map 查询参数
     * @return 关注的店铺信息个数
     */
    int queryAttentionByCustomerIdCount(Map<String, Object> map);

    /**
     * 查询店铺被关注数量
     *
     * @param storeId 店铺id
     * @return 店铺被关注数量
     */
    int queryStoreAttentionCountByStoreId(long storeId);

    /**
     * 根据店铺id和会员id取消关注
     *
     * @param map 店铺id和会员id
     * @return 删除返回码
     */
    int cancelStoreAttention(Map<String, Object> map);
}
