package com.lecshop.distributorlist.service;

import com.lecshop.distributorlist.bean.Distributor;
import com.lecshop.util.PageHelper;


/**
 * 分销商service接口
 *
 * @author sunluyang on 2017/5/25.
 */
public interface DistributorService {

    /**
     * 根据所属店铺id查询所有分销商
     *
     * @param belongStoreId 所属店铺
     * @param username      用户名
     * @return 分销商集合
     */
    PageHelper<Distributor> queryDistributor(PageHelper<Distributor> pageHelper, long belongStoreId, String username);

    /**
     * 根据会员id删除分销商(将分销商变成普通会员)
     *
     * @param distributor 分销商实体类
     * @return 更新返回码
     */
    int deleteDistributor(Distributor distributor);
}
