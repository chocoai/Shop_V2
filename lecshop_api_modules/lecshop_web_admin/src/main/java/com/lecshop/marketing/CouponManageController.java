package com.lecshop.marketing;

import com.lecshop.coupon.bean.Coupon;
import com.lecshop.coupon.bean.CouponDetails;
import com.lecshop.coupon.service.CouponService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
    @RequestMapping("/toaddcoupon")
    public ModelAndView toAddCoupon() {
        return new ModelAndView("marketing/addcoupon");
    }

    /**
     * 跳转到优惠券列表页面
     *
     * @return 优惠券列表页面
     */
    @RequestMapping("/tocouponlist")
    public ModelAndView toCouponList() {
        return new ModelAndView("marketing/couponlist");
    }

    /**
     * 跳转到优惠券详情页
     *
     * @return 优惠券详情页
     */
    @RequestMapping("/tocoupondetails")
    public ModelAndView toCouponDetails(long cid) {
        return new ModelAndView("marketing/coupondetails").addObject("cid", cid);
    }

    /**
     * 添加优惠券
     *
     * @param coupon 优惠券实体类
     * @return 添加返回码 -1 失败 -2 限领张数不正确 -3 限领张数不能大于总张数 >=1 成功
     */
    @RequestMapping("/addcoupon")
    @ResponseBody
    public int addCoupon(@RequestBody Coupon coupon) {
        return couponService.addCoupon(coupon, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 查询优惠券
     *
     * @param pageHelper 分页帮助类
     * @param name       查询条件,优惠券名称
     * @return 优惠券集合
     */
    @RequestMapping("/querycoupon")
    @ResponseBody
    public BaseResponse queryCoupon(PageHelper<Coupon> pageHelper, String name) {
        return BaseResponse.build(couponService.queryCoupon(pageHelper, name, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 删除优惠券
     *
     * @param ids 优惠券ids数组
     * @return 删除返回码 -1失败 >=1成功
     */
    @RequestMapping("/deletecoupon")
    @ResponseBody
    public int deleteCoupon(long[] ids) {
        return couponService.deleteCoupon(ids,CommonConstant.ADMIN_STOREID);
    }

    /**
     * 复制优惠券链接
     *
     * @param couponId 优惠券id
     */
    @RequestMapping("/copycoupon")
    public void copyCoupon(long couponId, HttpServletResponse response) throws IOException {
        response.getWriter().print(couponService.copyCoupon(couponId, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 导出优惠券
     *
     * @param couponId 优惠券id
     */
    @RequestMapping("/exportcoupon")
    public void exportCoupon(long couponId, HttpServletResponse response) {
        couponService.exportCoupon(response, CommonConstant.ADMIN_STOREID, couponId);
    }

    /**
     * 查询优惠券详情
     *
     * @param couponId 优惠券id
     * @return 优惠券详情实体类
     */
    @RequestMapping("/querycoupondetails")
    @ResponseBody
    public CouponDetails queryCouponDetails(long couponId) {
        return couponService.queryCouponDetails(CommonConstant.ADMIN_STOREID, couponId);
    }
}
