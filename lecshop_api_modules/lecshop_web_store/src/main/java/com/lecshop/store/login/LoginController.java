package com.lecshop.store.login;

import com.lecshop.baseinfo.bean.BaseInfoSet;
import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.service.StoreInfoService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.storemenu.service.StoreMenuService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.UnAuth;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Objects;

/**
 * store登录控制器
 *
 * @author sunluyang on 2017/6/6.
 */
@Controller
public class LoginController {

    /**
     * 注入会员service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 注入店铺菜单service
     */
    @Autowired
    private StoreMenuService storeMenuService;

    /**
     * 注入店铺信息service
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 注入信息设置实现类
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;


    /**
     * store后台登录页面
     *
     * @return 后台登录页面
     */
    @RequestMapping("/store_tologin")
    @UnAuth
    public ModelAndView toLogin() {
        return new ModelAndView("login/login");
    }

    /**
     * 跳转到店铺信息页面
     *
     * @return 店铺信息页面
     */
    @RequestMapping("/store_tostoreinfo")
    public ModelAndView toStoreInfo() {
        return new ModelAndView("storemanage/storeinfo");
    }

    /**
     * 商家登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return 返回码 0 输入为空 -1 验证码不正确 -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
     */
    @RequestMapping("/store_login")
    @ResponseBody
    @UnAuth
    public int customerStoreLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String code) throws IllegalAccessException {
        return customerService.customerStoreLogin(username, password, code, request.getSession().getAttribute(CommonConstant.STORE_LOGIN_KAPTCHA_KEY).toString(), customer -> {
            //登录成功过之后
            afterLoginSuccess(customer, request, response);
            // 清除session中的验证码
            request.getSession().removeAttribute(CommonConstant.STORE_LOGIN_KAPTCHA_KEY);
        });
    }

    /**
     * 修改管理员密码
     *
     * @param request       请求
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 修改返回码 0 没有登录 -1 输入不能为空 1 修改成功 2 两次输入密码不一致 3 原始密码不正确
     */
    @RequestMapping("/store_updatepassword")
    @ResponseBody
    public int updateCustomerPassWord(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword) {
        return customerService.updateCustomerPassWord(StoreLoginUtils.getInstance().getCustomerFromSession(request), oldPassword, newPassword, reNewPassword);
    }

    /**
     * 跳转到登录页或跳转到锁屏页
     *
     * @param request 页面请求
     * @return 跳转到登录页或跳转到锁屏页
     */
    @RequestMapping("/store_lockscanner")
    @UnAuth
    public ModelAndView lockScanner(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return new ModelAndView("redirect:/store_tologin.htm");
        }
        String[] userName = {""};
        Arrays.stream(cookies).forEach(cookie ->
                userName[0] = cookie.getName().equals(CommonConstant.STORE_LOGIN_COOKIES_USERNAME_KEY) ? cookie.getValue() : userName[0]
        );
        return new ModelAndView(StringUtils.isEmpty(userName[0]) ? "redirect:/store_tologin.htm" : "lockscreen/lockscreen").addObject("userName", userName[0]);
    }

    /**
     * 锁屏页登录
     *
     * @return -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
     */
    @RequestMapping("/store_locklogin")
    @ResponseBody
    @UnAuth
    public int lockLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return customerService.storeLockLogin(username, password, customer -> afterLoginSuccess(customer, request, response));
    }

    /**
     * 登录成功后进行检测店铺状态
     *
     * @param request request请求
     * @return 跳转页面
     */
    @RequestMapping("/store_stateofbeing")
    @UnAuth
    public ModelAndView stateOfBeing(HttpServletRequest request) {
        if (!StoreLoginUtils.getInstance().isLogin(request)) {
            return new ModelAndView("redirect:/store_tologin.htm");
        }
        StoreInfo storeInfo = storeInfoService.queryStoreInfo(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        if (Objects.isNull(storeInfo) || "0".equals(storeInfo.getStatus())) {//开店第一步
            return new ModelAndView("redirect:/store_toopenprotocol.htm");
        }
        if ("2".equals(storeInfo.getStatus())) {//开店审核通过->后台首页
            return new ModelAndView("redirect:/store_tostoreinfo.htm");
        }
        if ("1".equals(storeInfo.getStatus()) || "3".equals(storeInfo.getStatus())) {//审核中
            return new ModelAndView("redirect:/store_tosubmitresults.htm");
        }
        //异常跳转登录页
        return new ModelAndView("redirect:/store_tologin.htm");
    }

    /**
     * 查询基本信息和高级信息设置,用于页面图标展示,不需要拦截
     *
     * @return 基本信息和高级信息设置实体类
     */
    @RequestMapping("/store_querybaseinfosetunauth")
    @ResponseBody
    @UnAuth
    public BaseInfoSet queryBaseInfoSetUnAuth() {
        return baseInfoSetService.queryBaseInfoSet();
    }

    /**
     * 登录成功后处理的事
     *
     * @param customer 管理员
     */
    private void afterLoginSuccess(Customer customer, HttpServletRequest request, HttpServletResponse response) {
        //设置cookie,保存用户名到Cookie
        setCookie(customer, response);
        // 将用户信息放入session中
        StoreLoginUtils.getInstance().putCustomerToSession(request, customer);
        //将store后台菜单添加到session中
        request.getSession().setAttribute("storeMenu", storeMenuService.queryStoreMenu(customer.getId()));
        //一 二 三 级标题锁定
        request.getSession().setAttribute(CommonConstant.STORE_MENU_FIRSTMENUS, 1);
        request.getSession().setAttribute(CommonConstant.STORE_MENU_SECONDMENUS, 9);
        request.getSession().setAttribute(CommonConstant.STORE_MENU_THIRDMENUS, 10);
    }

    /**
     * 添加cookie
     */
    private void setCookie(Customer customer, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie(CommonConstant.STORE_LOGIN_COOKIES_USERNAME_KEY, URLEncoder.encode(customer.getUserName(), CommonConstant.ENCODE));
            cookie.setMaxAge(60 * 60 * 24);//生命周期
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
