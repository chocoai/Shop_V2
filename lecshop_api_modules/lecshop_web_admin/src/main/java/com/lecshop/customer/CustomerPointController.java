package com.lecshop.customer;

import com.lecshop.point.bean.CustomerPoint;
import com.lecshop.point.service.CustomerPointService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/5/25.
 * 会员积分控制器
 */
@Controller
@RequestMapping("/customer")
public class CustomerPointController {

    /**
     * 注入会员积分服务接口
     */
    @Autowired
    private CustomerPointService customerPointService;

    /**
     * 查询用户积分记录
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回用户积分
     */
    @RequestMapping("/querycustomerpoint")
    @ResponseBody
    public BaseResponse queryCustomerPoint(PageHelper<CustomerPoint> pageHelper, long customerId) {
        return BaseResponse.build(customerPointService.queryCustomerPoints(pageHelper, customerId));
    }

    /**
     * 跳转到查询会员积分页面
     *
     * @param customerId 会员id
     * @return 返回会员积分页面
     */
    @RequestMapping("/toquerycustomerpoint")
    public ModelAndView toQueryCustomerPoint(long customerId) {
        return new ModelAndView("customer/customerpoint").addObject("customerId", customerId);
    }

    /**
     * 查询会员积分总额
     *
     * @param customerId 会员id
     * @return 返回会员积分总额
     */
    @RequestMapping("/querycustomerpointcount")
    @ResponseBody
    public int queryCustomerPointCount(long customerId) {
        return customerPointService.queryCustomerPointCount(customerId);
    }

    /**
     * 新增会员积分
     *
     * @param customerPoint 会员积分
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/addcustomerpoint")
    @ResponseBody
    public int addCustomerPoint(@RequestBody CustomerPoint customerPoint) {
        return customerPointService.addCustomerPoint(customerPoint.setValuesForManagerAdd());
    }
}
