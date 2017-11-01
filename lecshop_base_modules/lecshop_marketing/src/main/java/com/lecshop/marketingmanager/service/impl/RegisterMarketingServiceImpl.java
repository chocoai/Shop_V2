package com.lecshop.marketingmanager.service.impl;

import com.lecshop.coupon.service.CouponService;
import com.lecshop.marketingmanager.bean.RegisterMarketing;
import com.lecshop.marketingmanager.mapper.RegisterMarketingMapper;
import com.lecshop.marketingmanager.service.RegisterMarketingService;
import com.lecshop.util.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册营销service实现类
 *
 * Created by LecShop on 2017/6/6.
 */
@Service
public class RegisterMarketingServiceImpl implements RegisterMarketingService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(RegisterMarketingServiceImpl.class);

    /**
     * 自动注入注册营销数据库接口
     */
    @Autowired
    private RegisterMarketingMapper registerMarketingMapper;

    /**
     * 自动注入优惠券service接口
     */
    @Autowired
    private CouponService couponService;

    /**
     * 查找注册营销
     *
     * @return 注册营销
     */
    @Override
    public RegisterMarketing queryRegisterMarketing() {
        logger.debug("queryRegisterMarketing...");
        RegisterMarketing marketing = registerMarketingMapper.queryRegisterMarketing();
        marketing.setCouponList(couponService.queryCouponToSet(CommonConstant.ADMIN_STOREID));
        return marketing;
    }

    /**
     * 修改注册营销
     *
     * @param registerMarketing 注册营销
     * @return  成功返回1  失败返回0
     */
    @Override
    public int updateRegisterMarketing(RegisterMarketing registerMarketing) {
        logger.debug("updateRegisterMarketing and registerMarketing :{}", registerMarketing);
        return registerMarketingMapper.updateRegisterMarketing(registerMarketing);
    }

    /**
     * 根据id删除优惠券
     *
     * @param ids 优惠券id
     * @return   成功返回1  失败返回0
     */
    @Override
    public int batchDeleteCoupon(long[] ids) {
        logger.debug("deleteCoupon and id :{}", ids);
        return registerMarketingMapper.batchDeleteCoupon(ids);
    }

}
