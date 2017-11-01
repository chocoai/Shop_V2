package com.lecshop.customer;

import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.point.bean.CustomerPoint;
import com.lecshop.point.service.CustomerPointService;
import com.lecshop.stationletter.bean.StationLetter;
import com.lecshop.stationletter.service.StationLetterService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/6/28.
 * 用户消息控制器
 */
@Controller
@RequestMapping("/customer")
public class CustomerMessageController {

    /**
     * 注入站内信服务接口
     */
    @Autowired
    private StationLetterService stationLetterService;

    /**
     * 注入会员积分服务接口
     */
    @Autowired
    private CustomerPointService customerPointService;

    /**
     * 跳转到查询用户消息页面
     *
     * @return 返回查询用户消息页面
     */
    @UnAuth
    @RequestMapping("/toquerymessage")
    public ModelAndView toQueryMessage() {
        return new ModelAndView("customer/message");
    }

    /**
     * 跳转到我的信息页面
     *
     * @return 我的信息页面
     */
    @RequestMapping("/tocustomerinfo")
    @UnAuth
    public ModelAndView toCustomerInfo() {
        return new ModelAndView("customer/customerinfo");
    }

    /**
     * 跳转到我的积分页面
     *
     * @return 我的积分页面
     */
    @RequestMapping("/tocustomerpoint")
    @UnAuth
    public ModelAndView toCustomerPoint() {
        return new ModelAndView("customer/customerpoint");
    }

    /**
     * 查询用户的站内信
     *
     * @param pageHelper 分页帮助类
     * @return 返回用户站内信
     */
    @RequestMapping("/querymessage")
    @ResponseBody
    public BaseResponse queryCustomerInfo(HttpServletRequest request, PageHelper<StationLetter> pageHelper) {
        return BaseResponse.build(stationLetterService.queryStationLettersByCustomerId(pageHelper, PcLoginUtils.getInstance().getCustomerIdFromSession(request)));
    }

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/deletecustomer")
    @ResponseBody
    public int deleteCustomerInfo(long id) {
        return stationLetterService.deleteStationLetters(id);
    }

    /**
     * 更新站内信阅读状态
     *
     * @param id 站内信id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/updatecustomerinfoisread")
    @ResponseBody
    public int updateCustomerInfoIsRead(long id) {
        return stationLetterService.updateStationLettersIsRead(id);
    }

    /**
     * 查询用户积分列表
     *
     * @param pageHelper  分页帮助类
     * @return  用户积分列表
     */
    @RequestMapping("/querypoint")
    @ResponseBody
    public BaseResponse queryPoint(HttpServletRequest request, PageHelper<CustomerPoint> pageHelper) {
        return BaseResponse.build(customerPointService.queryCustomerPoints(pageHelper, PcLoginUtils.getInstance().getCustomerIdFromSession(request)));
    }

    /**
     * 查询用户积分总额
     *
     * @return 用户积分总额
     */
    @RequestMapping("/querycustomerpointcount")
    @ResponseBody
    public int queryCustomerPointCount(HttpServletRequest request) {
        return customerPointService.queryCustomerPointCount(PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }
}
