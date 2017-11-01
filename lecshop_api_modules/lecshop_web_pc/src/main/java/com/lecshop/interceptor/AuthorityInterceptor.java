package com.lecshop.interceptor;

import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.util.UnAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dujinkai on 17/7/3.
 * 权限拦截器 (pc端主要是登录 判断用户有没有登录)
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不需要拦截 则直接返回
        if (!isNeedFilter(handler)) {
            logger.debug("Donot need  authority.....");
            return true;
        }

        // 判断是否具有权限
        if (!hasAuth(request)) {
            try {
                response.getWriter().write("loginerror");
            } catch (IOException e) {
                logger.error("io error...", e);
            }
            return false;
        }
        return true;
    }

    /**
     * 判断是否需要拦截
     */
    private boolean isNeedFilter(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getMethod().getAnnotation(UnAuth.class) == null;
    }

    /**
     * 判断用户是否有权限(主要判断用户是否登录 登录返回true 没登录返回false)
     */
    private boolean hasAuth(HttpServletRequest request) {
        return PcLoginUtils.getInstance().isLogin(request);
    }

}
