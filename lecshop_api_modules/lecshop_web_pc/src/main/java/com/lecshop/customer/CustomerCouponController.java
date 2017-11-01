package com.lecshop.customer;

import com.lecshop.coupon.bean.CouponInfo;
import com.lecshop.coupon.service.CouponService;
import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 我的优惠券控制器
 *
 * @author sunluyang on 2017/7/4.
 */
@Controller
@RequestMapping("/customer")
public class CustomerCouponController {

    /**
     * 注入优惠券service
     */
    @Autowired
    private CouponService couponService;

    /**
     * 跳转到我的优惠券页面
     *
     * @return 我的优惠券页面
     */
    @RequestMapping("/tomycoupon")
    public ModelAndView toDistributionList() {
        return new ModelAndView("customer/mycoupon");
    }

    /**
     * 查询会员领取优惠券信息
     *
     * @param pageHelper 分页帮助类
     * @param request    request请求
     * @param status     优惠券状态
     * @return 会员领取优惠券信息集合
     */
    @RequestMapping("/querycouponinfo")
    @ResponseBody
    public BaseResponse queryCouponInfoByCustomerId(PageHelper<CouponInfo> pageHelper, HttpServletRequest request, String status) {
        return BaseResponse.build(couponService.queryCouponInfoByCustomerId(pageHelper, PcLoginUtils.getInstance().getCustomerIdFromSession(request), status));
    }
}
