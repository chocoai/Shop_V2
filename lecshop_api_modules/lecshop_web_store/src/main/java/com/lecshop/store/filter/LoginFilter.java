package com.lecshop.store.filter;

import com.lecshop.store.storeutil.StoreLoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dujinkai on 17/6/2.
 * 登录过滤器
 */
public class LoginFilter implements Filter {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    /**
     * 不需要过滤的url
     */
    private static final List<String> noNeedFilters = new ArrayList<>();

    /**
     * 初始化不要过滤的url
     */
    static {
        noNeedFilters.addAll(Stream.of("/store_createkaptcha.htm", "/store_tologin.htm", "/store_login.htm", "/store_lockscanner.htm", "/store_locklogin.htm", "/store_querybaseinfosetunauth.htm").collect(Collectors.toList()));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 如果不需要过滤 则直接放通
        if (!isNeedFilter(request.getServletPath())) {
            logger.debug("No need filter and url :{}", request.getServletPath());
            filterChain.doFilter(request, response);
            return;
        }
        // 没有登录 则直接跳转到锁屏页面
        if (!StoreLoginUtils.getInstance().isLogin(request)) {
            try {
                logger.error("you is not login..." + request.getServletPath());
                response.sendRedirect("store_lockscanner.htm");
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("error...", e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }


    /**
     * 判断是否需要过滤
     */
    private boolean isNeedFilter(String url) {
        return !noNeedFilters.contains(url);
    }


    @Override
    public void destroy() {

    }
}
