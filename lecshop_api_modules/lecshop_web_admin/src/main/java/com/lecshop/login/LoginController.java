package com.lecshop.login;

import com.lecshop.adminlogin.LoginService;
import com.lecshop.adminmenu.service.AdminMenuService;
import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.manager.bean.Manager;
import com.lecshop.manager.service.ManagerService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.UnAuth;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dujinkai on 17/5/4.
 * 登录控制器
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private ManagerService managerService;
    /**
     * 注入信息设置实现类
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     用户输入的验证码
     * @return -1 参数不对  -2 验证码不对 -3 用户名或者密码错误 -4 用户被禁用 0成功
     */
    @RequestMapping("/login")
    @ResponseBody
    @UnAuth
    public int login(String username, String password, String code, HttpServletRequest request, HttpServletResponse response) {
        //判断是否开启验证码
        String codeInSession = "1".equals(baseInfoSetService.queryBaseInfoSet().getCaptchaOpen()) ? "" :
                request.getSession().getAttribute(CommonConstant.ADMIN_LOGIN_KAPTCHA_KEY).toString();
        return loginService.login(username, password, code, codeInSession, manager -> {
            alterLoginSuccess(manager, request, response);
            // 清除session中的验证码
            request.getSession().removeAttribute(CommonConstant.ADMIN_LOGIN_KAPTCHA_KEY);
        });
    }

    /**
     * 跳转到登录页或跳转到锁屏页
     *
     * @param request 页面请求
     * @return 跳转到登录页或跳转到锁屏页
     */
    @RequestMapping("/lockscanner")
    @UnAuth
    public ModelAndView lockScanner(HttpServletRequest request) {
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return new ModelAndView("redirect:/tologin.htm");
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CommonConstant.ADMIN_LOGIN_COOKIES_USERNAME_KEY)) {
                userName = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(userName)) {
            return new ModelAndView("redirect:/tologin.htm");
        } else {
            return new ModelAndView("lockscreen/lockscreen").addObject("userName", userName);
        }
    }

    /**
     * 登录页面
     *
     * @return 跳转到登录页面
     */
    @RequestMapping("/tologin")
    @UnAuth
    public ModelAndView toLogin() {
        return new ModelAndView("login/login").addObject("isOpen", baseInfoSetService.queryBaseInfoSet().getCaptchaOpen());
    }

    /**
     * 锁屏页登录
     *
     * @return -3:用户不存在或密码不正确,1:登录成功
     */
    @RequestMapping("/locklogin")
    @ResponseBody
    @UnAuth
    public int lockLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return loginService.lockLogin(username, password, manager ->
                alterLoginSuccess(manager, request, response)
        );
    }

    /**
     * 登录成功后跳转到首页
     *
     * @param request 请求
     * @return 首页
     */
    @RequestMapping("/index")
    public ModelAndView loginSuccessToIndex(HttpServletRequest request) {
        return new ModelAndView("index/index").addObject(AdminLoginUtils.getInstance().getManagerFromSession(request));
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
    @RequestMapping("/updatemanagerpassword")
    @ResponseBody
    public int updateManagerPassWord(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword) {
        return managerService.updateManagerPassWord(AdminLoginUtils.getInstance().getManagerFromSession(request), oldPassword, newPassword, reNewPassword);
    }

    /**
     * 登录成功后处理的事
     *
     * @param manager 管理员
     */
    private void alterLoginSuccess(Manager manager, HttpServletRequest request, HttpServletResponse response) {
        // 将用户信息放入session中
        AdminLoginUtils.getInstance().putManagerToSession(request, manager);
        //设置cookie,保存用户名到Cookie
        Cookie cookie = new Cookie(CommonConstant.ADMIN_LOGIN_COOKIES_USERNAME_KEY, manager.getUsername());
        cookie.setMaxAge(60 * 60 * 24);//生命周期
        response.addCookie(cookie);
        //将admin后台菜单添加到session中
        request.getSession().setAttribute("adminMenu", adminMenuService.queryAdminMenu(manager.getId()));
        request.getSession().setAttribute(CommonConstant.ADMIN_MENU_FIRSTMENUS, 1);
    }
}