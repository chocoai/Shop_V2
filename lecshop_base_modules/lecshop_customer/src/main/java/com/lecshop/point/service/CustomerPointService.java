package com.lecshop.point.service;

import com.lecshop.point.bean.CustomerPoint;
import com.lecshop.util.PageHelper;

/**
 * Created by dujinkai on 17/5/25.
 * 会员积分服务接口
 */
public interface CustomerPointService {

    /**
     * 分页查询积分记录
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回积分记录
     */
    PageHelper<CustomerPoint> queryCustomerPoints(PageHelper<CustomerPoint> pageHelper, long customerId);

    /**
     * 查询用户积分总额
     *
     * @param customerId 用户id
     * @return 返回用户积分总额
     */
    int queryCustomerPointCount(long customerId);

    /**
     * 新增会员积分
     *
     * @param customerPoint 会员积分
     * @return 成功返回1 失败返回0
     */
    int addCustomerPoint(CustomerPoint customerPoint);
}
