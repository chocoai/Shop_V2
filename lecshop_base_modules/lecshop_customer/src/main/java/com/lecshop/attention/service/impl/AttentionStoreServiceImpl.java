package com.lecshop.attention.service.impl;

import com.lecshop.attention.bean.AttentionStore;
import com.lecshop.attention.mapper.AttentionStoreMapper;
import com.lecshop.attention.service.AttentionStoreService;
import com.lecshop.openstore.mapper.StoreInfoMapper;
import com.lecshop.sku.service.SkuService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 关注店铺service实现类
 *
 * @author sunluyang on 2017/7/4.
 */
@Service
public class AttentionStoreServiceImpl implements AttentionStoreService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(AttentionStoreServiceImpl.class);

    /**
     * 注入关注的店铺mapper
     */
    @Autowired
    private AttentionStoreMapper attentionStoreMapper;

    /**
     * 注入店铺信息service
     */
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /**
     * 注入单品service
     */
    @Autowired
    private SkuService skuService;

    /**
     * 根据店铺id查询关注的店铺
     *
     * @param customerId 会员id
     * @return 关注的店铺信息集合
     */
    @Override
    public PageHelper<AttentionStore> queryAttentionByCustomerId(PageHelper<AttentionStore> pageHelper, long customerId) {
        logger.debug("queryAttentionByCustomerId and customerId:{}", customerId);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        PageHelper<AttentionStore> pageHelperData = pageHelper.setListDates(attentionStoreMapper.queryAttentionByCustomerId(pageHelper.getQueryParams(params, attentionStoreMapper.queryAttentionByCustomerIdCount(params))));
        pageHelperData.getList().forEach(attentionStore -> attentionStore.getAttentionStore(storeInfoMapper.queryAuditPassStoreInfo(attentionStore.getStoreId()),
                skuService.queryFiveDataForAttentionStore(attentionStore.getStoreId()), attentionStoreMapper.queryStoreAttentionCountByStoreId(attentionStore.getStoreId())));
        return pageHelperData;
    }

    /**
     * 根据店铺id和会员id取消关注
     *
     * @param storeId    店铺id
     * @param customerId 会员id
     * @return 删除返回码
     */
    @Override
    public int cancelStoreAttention(long storeId, long customerId) {
        logger.debug("queryAttentionByCustomerId and customerId:{}\r\n storeId:{}", customerId, storeId);
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("customerId", customerId);
        return attentionStoreMapper.cancelStoreAttention(map);
    }
}
