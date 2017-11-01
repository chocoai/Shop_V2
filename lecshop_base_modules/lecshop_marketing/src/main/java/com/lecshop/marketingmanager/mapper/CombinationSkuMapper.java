package com.lecshop.marketingmanager.mapper;

import com.lecshop.marketingmanager.bean.CombinationSku;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品组合单品关联数据库接口
 *
 * Created by LecShop on 2017/6/13.
 */
@Repository
public interface CombinationSkuMapper {

    /**
     * 分页查询商品组合下的商品
     *
     * @param params 查询参数
     * @return       商品组合下的单品
     */
    List<CombinationSku> querySkuOfGoodCom(Map<String, Object> params);

    /**
     * 查询商品组合下的单品数目
     *
     * @param params 查询参数
     * @return       商品组合下的单品数目
     */
    int querySkuCount(Map<String, Object> params);

    /**
     * 查询指定商品组合下的单品id集合（防止商品组合添加重复单品）
     *
     * @param combinationSku 商品组合与单品的关联类
     * @return  单品id集合
     */
    List<String> querySkuIdWithAdd(CombinationSku combinationSku);

    /**
     * 为商品组合添加单品
     *
     * @param combinationSku 单品
     * @return 成功返回1，失败返回0
     */
    int addCombinationSku(CombinationSku combinationSku);

    /**
     * 删除商品组合下的单品
     *
     * @param id 单品id
     * @return   成功返回1，失败返回0
     */
    int deleteCombinationSku(String id);

    /**
     * 批量删除单品
     * @param ids 单品id数组
     * @return    成功返回>=1，失败返回0
     */
    int batchDeleteCombiantionSKu(String[] ids);

}
