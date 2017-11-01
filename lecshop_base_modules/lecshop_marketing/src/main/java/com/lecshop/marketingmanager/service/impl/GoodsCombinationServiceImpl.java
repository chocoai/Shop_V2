package com.lecshop.marketingmanager.service.impl;

import com.lecshop.marketingmanager.bean.GoodsCombination;
import com.lecshop.marketingmanager.mapper.GoodsCombinationMapper;
import com.lecshop.marketingmanager.service.GoodsCombinationService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品组合service实现类
 *
 * Created by LecShop on 2017/6/12.
 */
@Service
public class GoodsCombinationServiceImpl implements GoodsCombinationService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(GoodsCombinationServiceImpl.class);

    /**
     * 自动注入商品组合数据库接口
     */
    @Autowired
    private GoodsCombinationMapper goodsCombinationMapper;

    /**
     * 分页查询商品组合
     *
     * @param pageHelper 查询参数
     * @param name       商品组合名称
     * @param storeId    店铺id
     * @return    返回商品组合数据
     */
    @Override
    public PageHelper<GoodsCombination> queryGoodsCombination(PageHelper<GoodsCombination> pageHelper, String name, long storeId) {
        logger.debug("queryGoodsCombination and pageHelper :{} \r\n name :{} \r\n storeId :{}", pageHelper, name, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("storeId", storeId);
        return pageHelper.setListDates(goodsCombinationMapper.queryGoodsCombination(pageHelper.getQueryParams(params, goodsCombinationMapper.queryGoodsCombinationCount(params))));
    }

    /**
     * 添加商品组合
     *
     * @param goodsCombination 商品组合
     * @return      成功返回1，失败返回0
     */
    @Override
    public int addGoodsCombination(GoodsCombination goodsCombination) {
        logger.debug("addGoodsCombination and goodsCombination :{}", goodsCombination);
        return goodsCombinationMapper.addGoodsCombination(goodsCombination);
    }

    /**
     * 删除商品组合
     *
     * @param id      商品组合id
     * @param storeId 店铺id
     * @return        成功返回1，失败返回0
     */
    @Override
    public int deleteGoodsCombination(long id, long storeId) {
        logger.debug("deleteGoodsCombination and id :{}", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return goodsCombinationMapper.deleteGoodsCombination(params);
    }

    /**
     * 批量删除商品组合
     *
     * @param ids     商品组合id数组
     * @param storeId 店铺id
     * @return        成功返回>=1，失败返回0
     */
    @Transactional
    @Override
    public int batchDeleteGoodsCombination(long[] ids, long storeId) {
        logger.debug("batchDeleteGoodsCombination and ids :{}", ids);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("storeId", storeId);
        return goodsCombinationMapper.batchDeleteGoodsCombination(params);
    }

    /**
     * 通过id查找商品组合
     *
     * @param id      商品组合id
     * @param storeId 店铺id
     * @return        商品组合
     */
    @Override
    public GoodsCombination queryGoodsCombinationById(long id, long storeId) {
        logger.debug("queryGoodsCombinationById and id :{}", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return goodsCombinationMapper.queryGoodsCombinationById(params);
    }

    /**
     * 修改商品组合
     *
     * @param goodsCombination 商品组合
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateGoodsCombination(GoodsCombination goodsCombination) {
        logger.debug("updateGoodsCombination and goodsCombination :{}", goodsCombination);
        return goodsCombinationMapper.updateGoodsCombination(goodsCombination);
    }

}
