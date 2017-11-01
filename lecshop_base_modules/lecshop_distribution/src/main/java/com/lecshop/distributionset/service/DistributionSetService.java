package com.lecshop.distributionset.service;


import com.lecshop.distributionset.bean.DistributionSet;

/**
 * 分销设置service接口
 *
 * @author sunluyang on 2017/5/24.
 */
public interface DistributionSetService {

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
     * @return 编辑返回码 -1失败 1成功
     */
    int editDistributionSet(DistributionSet distributionSet);

    /**
     * 添加分销设置
     *
     * @param storeId 店铺id
     * @return 添加返回码
     */
    int addDistributionSet(long storeId);

    /**
     * 删除分销设置
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    int deleteDistributionSet(long storeId);
}
