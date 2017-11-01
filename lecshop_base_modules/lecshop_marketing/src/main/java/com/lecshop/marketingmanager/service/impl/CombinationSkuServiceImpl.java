package com.lecshop.marketingmanager.service.impl;

import com.lecshop.marketingmanager.bean.CombinationSku;
import com.lecshop.marketingmanager.mapper.CombinationSkuMapper;
import com.lecshop.marketingmanager.service.CombinationSkuService;
import com.lecshop.util.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品组合下的单品service实现类
 * <p>
 * Created by LecShop on 2017/6/13.
 */
@Service
public class CombinationSkuServiceImpl implements CombinationSkuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CombinationSkuServiceImpl.class);

    /**
     * 自动注入商品组合与单品关联数据库接口
     */
    @Autowired
    private CombinationSkuMapper combinationSkuMapper;

    /**
     * 分页查询商品组合下的商品
     *
     * @param pageHelper 分页帮助类
     * @param id         商品组合id
     * @param storeId    店铺id
     * @return 商品组合下的所有单品
     */
    @Override
    public PageHelper<CombinationSku> querySkuOfGoodCom(PageHelper<CombinationSku> pageHelper, long id, long storeId) {
        logger.debug("querySkuOfGoodCom and id :{} \r\n storeId :{}", id, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return pageHelper.setListDates(combinationSkuMapper.querySkuOfGoodCom(pageHelper.getQueryParams(params, combinationSkuMapper.querySkuCount(params))));
    }

    /**
     * 为商品组合添加单品
     *
     * @param combinationSku 商品组合单品
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addCombinationSku(CombinationSku combinationSku) {
        logger.debug("addCombinationSku and combinationSku :{}", combinationSku);
        List<String> list = combinationSkuMapper.querySkuIdWithAdd(combinationSku);
        if (!CollectionUtils.isEmpty(list)) {
            if (list.stream().filter(id -> id.equals(combinationSku.getSkuId())).count() != 0) {
                return 0;
            }
        }
        return combinationSkuMapper.addCombinationSku(combinationSku);
    }

    /**
     * 删除商品组合下的单品
     *
     * @param id 单品id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteCombinationSku(String id) {
        logger.debug("deleteCombinationSku and id :{}", id);
        return combinationSkuMapper.deleteCombinationSku(id);
    }

    /**
     * 批量删除单品
     *
     * @param ids 单品id数组
     * @return 成功返回>=1，失败返回0
     */
    @Transactional
    @Override
    public int batchDeleteCombiantionSKu(String[] ids) {
        logger.debug("batchDeleteCombiantionSKu and :{}", ids);
        return combinationSkuMapper.batchDeleteCombiantionSKu(ids);
    }

}
