package com.lecshop.coupon.mapper;

import com.lecshop.coupon.bean.CouponCode;
import com.lecshop.coupon.bean.CouponInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 优惠卷卷码mapper
 *
 * @author sunluyang on 2017/6/1.
 */
@Repository
public interface CouponCodeMapper {
    /**
     * 添加优惠券券码
     *
     * @param couponCode 优惠券卷码实体类
     * @return 添加返回码
     */
    int addCouponCode(List<CouponCode> couponCode);

    /**
     * 删除优惠券券码
     *
     * @param couponIds 优惠券id数组
     * @return 删除返回码
     */
    int deleteCouponCode(long[] couponIds);

    /**
     * 根据优惠券id查询券码信息
     *
     * @param couponId 优惠券id
     * @return 券码信息
     */
    List<CouponCode> queryCouponCodeByCouponId(long couponId);

    /**
     * 根据会员id和优惠券状态查询会员领取优惠券信息
     *
     * @param map 查询条件
     * @return 会员领取优惠券信息集合
     */
    List<CouponInfo> queryCouponInfoByCustomerId(Map<String, Object> map);

    /**
     * 根据会员id和优惠券状态查询会员领取优惠券条数
     *
     * @param map 查询条件
     * @return 领取优惠券条数
     */
    int queryCouponInfoCountByCustomerId(Map<String, Object> map);
}
