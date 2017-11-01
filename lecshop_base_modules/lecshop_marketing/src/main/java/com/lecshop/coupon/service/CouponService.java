package com.lecshop.coupon.service;

import com.lecshop.coupon.bean.Coupon;
import com.lecshop.coupon.bean.CouponDetails;
import com.lecshop.coupon.bean.CouponInfo;
import com.lecshop.util.PageHelper;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 优惠券service接口
 *
 * @author sunluyang on 2017/6/1.
 */
public interface CouponService {
    /**
     * 添加优惠券
     *
     * @param coupon  优惠券实体类
     * @param storeId 店铺id
     * @return 添加返回码 -1 失败 1 成功
     */
    int addCoupon(Coupon coupon, long storeId);

    /**
     * 分页查询店铺优惠券
     *
     * @param pageHelper 分页帮助类
     * @param name       优惠券名称
     * @param storeId    店铺id
     * @return 优惠券集合
     */
    PageHelper<Coupon> queryCoupon(PageHelper<Coupon> pageHelper, String name, long storeId);

    /**
     * 删除优惠券
     *
     * @param ids     优惠券id数组
     * @param storeId 店铺id
     * @return 删除返回码 -1 失败 >=1成功
     */
    int deleteCoupon(long[] ids, long storeId);

    /**
     * 复制优惠券链接
     *
     * @param id      优惠券id
     * @param storeId 店铺id
     * @return 返回优惠券链接 -1:没有查询到该优惠券, 0:优惠券已过期, 其他字符串为优惠券链接
     */
    String copyCoupon(long id, long storeId);

    /**
     * 导出优惠券券码
     *
     * @param storeId  店铺id
     * @param couponId 优惠券id
     */
    void exportCoupon(HttpServletResponse response, long storeId, long couponId);

    /**
     * 根据店铺id查询优惠券(用于设置注册营销)
     *
     * @param storeId 店铺id
     * @return 优惠券集合
     */
    List<Coupon> queryCouponToSet(long storeId);

    /**
     * 查询优惠券详情页数据
     *
     * @return 优惠券详情页数据
     */
    CouponDetails queryCouponDetails(long storeId, long couponId);

    /**
     * 根据会员id查询会员领取优惠券信息
     *
     * @param customerId 会员id
     * @return 会员领取优惠券信息集合
     */
    PageHelper<CouponInfo> queryCouponInfoByCustomerId(PageHelper<CouponInfo> pageHelper, long customerId, String status);
}
