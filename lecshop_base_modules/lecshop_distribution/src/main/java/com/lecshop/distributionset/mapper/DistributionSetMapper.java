package com.lecshop.distributionset.mapper;

import com.lecshop.distributionset.bean.DistributionSet;
import org.springframework.stereotype.Repository;

/**
 * 分销设置mapper
 *
 * @author sunluyang on 2017/5/24.
 */
@Repository
public interface DistributionSetMapper {

    /**
     * 根据店铺id查询分销设置(admin端 店铺id默认为0)
     *
     * @return 分销设置实体类
     */
    DistributionSet queryDistributionSet(long storeId);

    /**
     * 编辑分销设置
     *
     * @param distributionSet 分销设置实体类
     * @return 编辑返回码
     */
    int editDistributionSet(DistributionSet distributionSet);

    /**
     * 添加分销设置
     *
     * @param distributionSet 分销设置实体类
     * @return 添加返回码
     */
    int addDistributionSet(DistributionSet distributionSet);

    /**
     * 删除分销设置
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    int deleteDistributionSet(long storeId);
}
