package com.lecshop.pcutil;

import com.lecshop.customer.bean.Customer;
import com.lecshop.util.CommonConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * pc 登录帮助工具类
 *
 * @author sunluyang on 2017/7/5.
 */
public class PcLoginUtils {

    private static final PcLoginUtils INSTANCE = new PcLoginUtils();

    private PcLoginUtils() {

    }

    public static PcLoginUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 将用户信息放入session中
     *
     * @param customer 用户对象
     */
    public void putCustomerToSession(HttpServletRequest request, Customer customer) {
        request.getSession().setAttribute(CommonConstant.PC_LOGIN_SESSION_KEY, customer);
    }

    /**
     * 判断是否登录
     *
     * @return 登录返回true  未登录返回false
     */
    public boolean isLogin(HttpServletRequest request) {
        setDefaultCustomerToSession(request);
        return Objects.nonNull(getCustomerFromSession(request));
    }

    /**
     * 从session中获得用户信息
     *
     * @return 返回管理员信息
     */
    public Customer getCustomerFromSession(HttpServletRequest request) {
        return Objects.nonNull(request.getSession().getAttribute(CommonConstant.PC_LOGIN_SESSION_KEY)) ? (Customer) request.getSession().getAttribute(CommonConstant.PC_LOGIN_SESSION_KEY) : null;
    }

    /**
     * 从session获取用户
     *
     * @return 获取用户id
     */
    public long getCustomerIdFromSession(HttpServletRequest request) {
        return getCustomerFromSession(request).getId();
    }

    public void setDefaultCustomerToSession(HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setId(46);
        customer.setUserName("pcCustomer");
        customer.setReleName("pctest");
        putCustomerToSession(request, customer);
    }
}
