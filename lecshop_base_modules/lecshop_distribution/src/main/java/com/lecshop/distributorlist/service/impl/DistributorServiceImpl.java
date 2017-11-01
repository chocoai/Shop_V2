package com.lecshop.distributorlist.service.impl;

import com.lecshop.distributorlist.bean.Distributor;
import com.lecshop.distributorlist.mapper.DistributorMapper;
import com.lecshop.distributorlist.service.DistributorService;
import com.lecshop.util.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 分销商service实体类
 *
 * @author sunluyang on 2017/5/26.
 */
@Service
public class DistributorServiceImpl implements DistributorService {
    /**
     * 调试日式
     */
    private Logger logger = LoggerFactory.getLogger(DistributorServiceImpl.class);
    /**
     * 注入分销商mapper
     */
    @Autowired
    private DistributorMapper distributorMapper;

    /**
     * 根据所属店铺id查询所有分销商
     *
     * @param belongStoreId 所属店铺
     * @param username      用户名
     * @return 分销商集合
     */
    @Override
    public PageHelper<Distributor> queryDistributor(PageHelper<Distributor> pageHelper, long belongStoreId, String username) {
        logger.debug("queryDistributor and pageHelper : {} \r\n belongStoreId: {}", pageHelper, belongStoreId);
        Map<String, Object> params = new HashMap<>();
        params.put("belongStoreId", belongStoreId);
        params.put("username", username);
        return pageHelper.setListDates(distributorMapper.queryDistributor(pageHelper.getQueryParams(params, distributorMapper.queryDistributorCount(params))));
    }

    /**
     * 删除分销商
     *
     * @param distributor 分销商实体类
     * @return 删除返回码-1 失败 1成功
     */
    @Override
    public int deleteDistributor(Distributor distributor) {
        //判断该会员下是否有下级分销商,有则更新下级分销商的上级
        if (CollectionUtils.isNotEmpty(distributorMapper.queryDistributorBySuperiorId(distributor.getId()))) {
            distributorMapper.updateDistributor(distributor);
        }
        return distributorMapper.deleteDistributor(distributor.getId());
    }
}
