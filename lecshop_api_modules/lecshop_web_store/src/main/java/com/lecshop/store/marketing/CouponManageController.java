package com.lecshop.store.marketing;

import com.lecshop.coupon.bean.Coupon;
import com.lecshop.coupon.bean.CouponDetails;
import com.lecshop.coupon.service.CouponService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.elasticsearch.index.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 优惠券管理控制器
 *
 * @author sunluyang on 2017/6/1.
 */
@Controller
public class CouponManageController {
    /**
     * 注入优惠券service
     */
    @Autowired
    private CouponService couponService;

    /**
     * 跳转到添加优惠券页面
     *
     * @return 添加优惠券页面
     */
    @RequestMapping("/store_toaddcoupon")
    public ModelAndView toAddCoupon() {
        return new ModelAndView("marketing/addcoupon");
    }

    /**
     * 跳转到优惠券列表页面
     *
     * @return 优惠券列表页面
     */
    @RequestMapping("/store_tocouponlist")
    public ModelAndView toCouponList() {
        return new ModelAndView("marketing/couponlist");
    }

    /**
     * 跳转到优惠券详情页
     *
     * @return 优惠券详情页
     */
    @RequestMapping("/store_tocoupondetails")
    public ModelAndView toCouponDetails(long cid) {
        return new ModelAndView("marketing/coupondetails").addObject("cid", cid);
    }

    /**
     * 添加优惠券
     *
     * @param coupon 优惠券实体类
     * @return 添加返回码 -1 失败 -2 限领张数不正确 -3 限领张数不能大于总张数 >=1 成功
     */
    @RequestMapping("/store_addcoupon")
    @ResponseBody
    public int addCoupon(@RequestBody Coupon coupon, HttpServletRequest request) {
        return couponService.addCoupon(coupon, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 查询优惠券
     *
     * @param pageHelper 分页帮助类
     * @param name       查询条件,优惠券名称
     * @return 优惠券集合
     */
    @RequestMapping("/store_querycoupon")
    @ResponseBody
    public BaseResponse queryCoupon(PageHelper<Coupon> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(couponService.queryCoupon(pageHelper, name, StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 删除优惠券
     *
     * @param ids 优惠券ids数组
     * @return 删除返回码 -1失败 >=1成功
     */
    @RequestMapping("/store_deletecoupon")
    @ResponseBody
    public int deleteCoupon(long[] ids, HttpServletRequest request) {
        return couponService.deleteCoupon(ids, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 复制优惠券链接
     *
     * @param couponId 优惠券id
     */
    @RequestMapping("/store_copycoupon")
    public void copyCoupon(long couponId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.getWriter().print(couponService.copyCoupon(couponId, StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 导出优惠券
     *
     * @param couponId 优惠券id
     */
    @RequestMapping("/store_exportcoupon")
    public void exportCoupon(long couponId, HttpServletResponse response, HttpServletRequest request) {
        couponService.exportCoupon(response, StoreLoginUtils.getInstance().getStoreIdFromSession(request), couponId);
    }

    /**
     * 查询优惠券详情
     *
     * @param couponId 优惠券id
     * @return 优惠券详情实体类
     */
    @RequestMapping("/store_querycoupondetails")
    @ResponseBody
    public CouponDetails queryCouponDetails(long couponId, HttpServletRequest request) {
        return couponService.queryCouponDetails(StoreLoginUtils.getInstance().getStoreIdFromSession(request), couponId);
    }
}
