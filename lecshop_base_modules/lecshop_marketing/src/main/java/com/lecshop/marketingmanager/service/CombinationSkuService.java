package com.lecshop.marketingmanager.service;

import com.lecshop.marketingmanager.bean.CombinationSku;
import com.lecshop.sku.bean.Sku;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * 商品组合单品service
 *
 * Created by LecShop on 2017/6/13.
 */
public interface CombinationSkuService {

    /**
     * 分页查询商品组合下的商品
     *
     * @param id 商品组合id
     * @param storeId 店铺id
     * @return       商品组合下的单品
     */
    PageHelper<CombinationSku> querySkuOfGoodCom(PageHelper<CombinationSku> pageHelper, long id, long storeId);

    /**
     * 为商品组合添加单品
     *
     * @param combinationSku 商品组合单品
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
