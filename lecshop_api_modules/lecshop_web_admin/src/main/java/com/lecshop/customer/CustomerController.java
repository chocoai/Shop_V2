package com.lecshop.customer;

import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.bean.CustomerSearchCondition;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.apache.commons.io.FileUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by dujinkai on 17/5/19.
 * 会员控制器
 */
@Controller
public class CustomerController {

    /**
     * 会员服务接口
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到会员页面
     *
     * @return 返回会员页面
     */
    @RequestMapping("/toquerycustomer")
    public ModelAndView toQueryCustomer() {
        return new ModelAndView("customer/customer");
    }

    /**
     * 分页查询会员信息
     *
     * @param pageHelper      分页帮助类
     * @param searchCondition 搜索条件
     * @return 返回会员信息
     */
    @RequestMapping("/querycustomers")
    @ResponseBody
    public BaseResponse queryCustomers(PageHelper<Customer> pageHelper, CustomerSearchCondition searchCondition) {
        return BaseResponse.build(customerService.queryCustomers(pageHelper, searchCondition));
    }

    /**
     * 查询会员详情
     *
     * @param customerId 会员id
     * @return 返回会员详情页面
     */
    @RequestMapping("/tocustomerinfo")
    public ModelAndView toCustomerInfo(long customerId) {
        return new ModelAndView("customer/customerinfo").addObject("customer", customerService.queryCustomerWithNoPasswordById(customerId));
    }

    /**
     * 根据会员id查询会员信息
     *
     * @param customerId 会员id
     * @return 返回会员信息(没有密码)
     */
    @RequestMapping("/querycustomerbyid")
    @ResponseBody
    public Customer queryCustomerById(long customerId) {
        return customerService.queryCustomerWithNoPasswordById(customerId);
    }

    /**
     * 删除会员信息
     *
     * @param id 会员id
     * @return 成返回 1 失败返回0
     */
    @RequestMapping("/deletecustomer")
    @ResponseBody
    public int deleteCustomer(long id) {
        return customerService.deleteCustomers(Arrays.asList(id));
    }

    /**
     * 批量删除会员信息
     *
     * @param ids 会员ID集合
     * @return 成功返回>1 失败返回0
     */
    @RequestMapping("/deletecustomers")
    @ResponseBody
    public int deleteCustomers(Long[] ids) {
        return customerService.deleteCustomers(Arrays.stream(ids).map(Long::valueOf).collect(Collectors.toList()));
    }

    /**
     * 新增会员
     *
     * @param customer 会员信息
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/addcustomer")
    @ResponseBody
    public int addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer.setDefaultValuesForAdminAdd());
    }

    /**
     * 修改会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatecustomer")
    @ResponseBody
    public int updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

}
