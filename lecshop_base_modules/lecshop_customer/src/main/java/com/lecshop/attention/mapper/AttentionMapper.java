package com.lecshop.attention.mapper;

import com.lecshop.attention.bean.Attention;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/27.
 * 商品关注数据库接口
 */
public interface AttentionMapper {

    /**
     * 查询商品关注总数
     *
     * @param params 查询参数
     * @return 返回商品关注总数
     */
    int queryAttentionCount(Map<String, Object> params);

    /**
     * 查询商品关注
     *
     * @param params 查询参数
     * @return 返回商品关注
     */
    List<Attention> queryAttentions(Map<String, Object> params);

    /**
     * 取消关注商品
     *
     * @param attention 商品关注实体类
     * @return 返回删除结果
     */
    int cancelAttention(Attention attention);
}
