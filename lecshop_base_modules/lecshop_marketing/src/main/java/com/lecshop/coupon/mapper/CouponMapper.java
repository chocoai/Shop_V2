package com.lecshop.coupon.mapper;

import com.lecshop.coupon.bean.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 优惠券mapper
 *
 * @author sunluyang on 2017/6/1.
 */
@Repository
public interface CouponMapper {
    /**
     * 添加优惠券
     *
     * @param coupon 优惠券实体类
     * @return 添加返回码
     */
    int addCoupon(Coupon coupon);

    /**
     * 该店铺下的所有优惠券条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryCouponCount(Map<String, Object> map);

    /**
     * 该店铺下的所有优惠券
     *
     * @param map 查询条件
     * @return 返回优惠券集合
     */
    List<Coupon> queryCoupon(Map<String, Object> map);

    /**
     * 根据Id删除优惠券
     *
     * @param map 删除参数
     * @return 返回删除码
     */
    int deleteCoupon(Map<String, Object> map);

    /**
     * 根据优惠券id查询优惠券信息
     *
     * @param map 查询条件(店铺id和优惠券id)
     * @return 优惠券信息
     */
    Coupon queryCouponById(Map<String, Object> map);

    /**
     * 根据店铺id查询优惠券(用于设置注册营销)
     *
     * @param storeId 店铺id
     * @return 优惠券集合
     */
    List<Coupon> queryCouponToSet(long storeId);
}
