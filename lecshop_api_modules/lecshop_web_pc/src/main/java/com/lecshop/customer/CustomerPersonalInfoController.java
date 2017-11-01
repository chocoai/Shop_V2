package com.lecshop.customer;

import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.pcutil.PcLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员个人信息控制器
 *
 * @author sunluyang on 2017/7/5.
 */
@Controller
@RequestMapping("/customer")
public class CustomerPersonalInfoController {

    /**
     * 注入会员service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到个人信息页面
     *
     * @return 个人信息页面
     */
    @RequestMapping("/topersonalinfo")
    public ModelAndView toPersonalInfo() {
        return new ModelAndView("customer/personalinfo");
    }

    /**
     * 根据会员id查询会员个人信息
     *
     * @param request request请求
     * @return 会员个人信息
     */
    @RequestMapping("/querypersonalinfo")
    @ResponseBody
    public Customer queryCustomerPersonalInfo(HttpServletRequest request) {
        return customerService.queryCustomerWithCustomerLevel(PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }

    /**
     * 编辑用户个人信息
     *
     * @param request  request请求
     * @param customer 会员实体类
     * @return 返回码
     */
    @RequestMapping("/editpersonalinfo")
    @ResponseBody
    public int editCustomerPersonalInfo(HttpServletRequest request, @RequestBody Customer customer, int param) {
        return customerService.updatePersonalInfo(customer.setCustomerId(PcLoginUtils.getInstance().getCustomerIdFromSession(request)), param);
    }
}
