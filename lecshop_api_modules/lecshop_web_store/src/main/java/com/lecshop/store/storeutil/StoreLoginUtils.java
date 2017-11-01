package com.lecshop.store.storeutil;

import com.lecshop.customer.bean.Customer;
import com.lecshop.util.CommonConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * store登录工具类
 *
 * @author sunluyang on 2017/6/6.
 */
public class StoreLoginUtils {

    private static final StoreLoginUtils STORE_LOGIN_UTILS = new StoreLoginUtils();


    private StoreLoginUtils() {

    }

    public static StoreLoginUtils getInstance() {
        return STORE_LOGIN_UTILS;
    }

    /**
     * 将用户信息放入session中
     *
     * @param customer 商家对象
     */
    public void putCustomerToSession(HttpServletRequest request, Customer customer) {
        request.getSession().setAttribute(CommonConstant.STORE_LOGIN_SESSION_KEY, customer);
    }

    /**
     * 从session中获得管理员的名称
     *
     * @return 返回管理员名称
     */
    public String getCustomerNameFromSession(HttpServletRequest request) {
        return Objects.nonNull(getCustomerFromSession(request)) ? getCustomerFromSession(request).getUserName() : "";
    }


    /**
     * 从session中获得管理员信息
     *
     * @return 返回管理员信息
     */
    public Customer getCustomerFromSession(HttpServletRequest request) {
        return Objects.nonNull(request.getSession().getAttribute(CommonConstant.STORE_LOGIN_SESSION_KEY)) ? (Customer) request.getSession().getAttribute(CommonConstant.STORE_LOGIN_SESSION_KEY) : null;
    }

    /**
     * 判断是否登录
     *
     * @return 登录返回true  未登录返回false
     */
    public boolean isLogin(HttpServletRequest request) {
        return Objects.nonNull(getCustomerFromSession(request));
    }

    /**
     * 从session获取店铺id
     *
     * @return 获取店铺id
     */
    public long getStoreIdFromSession(HttpServletRequest request) {
        return getCustomerFromSession(request).getStoreId();
    }
}
