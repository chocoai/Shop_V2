package com.lecshop.distributionset.service.impl;

import com.lecshop.distributionset.bean.DistributionSet;
import com.lecshop.distributionset.mapper.DistributionSetMapper;
import com.lecshop.distributionset.service.DistributionSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 分销设置service实现类
 *
 * @author sunluyang on 2017/5/24.
 */
@Service
public class DistributionSetServiceImpl implements DistributionSetService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(DistributionSetServiceImpl.class);
    /**
     * 注入分销设置mapper
     */
    @Autowired
    private DistributionSetMapper distributionSetMapper;

    /**
     * 根据店铺id查询分销设置(admin端 店铺id默认为0)
     *
     * @return 分销设置实体类
     */
    @Override
    public DistributionSet queryDistributionSet(long storeId) {
        logger.debug("queryDistributionSet and storeId:{}", storeId);
        return distributionSetMapper.queryDistributionSet(storeId);
    }

    /**
     * 编辑分销设置
     *
     * @param distributionSet 分销设置实体类
     * @return 编辑返回码 -1失败 1成功
     */
    @Override
    public int editDistributionSet(DistributionSet distributionSet) {
        logger.debug("editDistributionSet and distributionSet:{}", distributionSet);
        if (Objects.isNull(distributionSet)) {
            logger.error("editDistributionSet error due to distributionSet is empty");
            return -1;
        }
        return distributionSetMapper.editDistributionSet(distributionSet);
    }

    /**
     * 添加分销设置
     *
     * @param storeId 店铺id
     * @return 添加返回码
     */
    @Override
    public int addDistributionSet(long storeId) {
        logger.debug("addDistributionSet and storeId:{}", storeId);
        return distributionSetMapper.addDistributionSet(new DistributionSet().getDefaultDistributionSet(storeId));
    }

    /**
     * 删除分销设置
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    @Override
    public int deleteDistributionSet(long storeId) {
        logger.debug("deleteDistributionSet and storeId:{}", storeId);
        return distributionSetMapper.deleteDistributionSet(storeId);
    }
}
