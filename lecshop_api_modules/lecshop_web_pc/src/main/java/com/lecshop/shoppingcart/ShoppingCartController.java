package com.lecshop.shoppingcart;

import com.alibaba.fastjson.JSON;
import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.shoppingcart.bean.CookieShopingCart;
import com.lecshop.shoppingcart.bean.ShoppingCart;
import com.lecshop.shoppingcart.service.ShoppingCartService;
import com.lecshop.util.UnAuth;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/7/5.
 * 购物车控制器
 */
@Controller
public class ShoppingCartController {

    /**
     * 注入购物车服务接口
     */
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     *
     * @param shoppingCart 购物车
     * @return 成功返回1  失败返回0 -1 库存不足 -2 单品不存在 -3 参数错误 -4 单品已下架
     */
    @RequestMapping("/addshoppingcart")
    @ResponseBody
    @UnAuth
    public int addShoppingCart(HttpServletRequest request, HttpServletResponse response, ShoppingCart shoppingCart) {

        // 判断用户是否登录  登录则将购物车信息放入数据库 未登录则将购物车信息放入cookie中
        if (PcLoginUtils.getInstance().isLogin(request)) {
            return shoppingCartService.addShoppingCart(shoppingCart.setDefaultValuesForAdd(PcLoginUtils.getInstance().getCustomerIdFromSession(request)));
        } else {
            return shoppingCartService.addShoppingCartToCookie(shoppingCart, getShoppingCartsFromCookie(request), cookieShopingCarts -> setShoppingCartsToCookie(response, cookieShopingCarts));
        }
    }

    /**
     * 获得cookie中的购物车信息
     *
     * @return 返回cookie中的购物车
     */
    private List<CookieShopingCart> getShoppingCartsFromCookie(HttpServletRequest request) {
        // 获得cookie
        Cookie[] cookies = request.getCookies();
        List<CookieShopingCart> cookieShopingCarts = new ArrayList<>();

        if (ArrayUtils.isNotEmpty(cookies)) {
            Arrays.stream(cookies).filter(cookie -> "lecshop_shoppingcart".equals(cookie.getName())).findFirst().ifPresent(cookie1 -> cookieShopingCarts.addAll(JSON.parseArray(cookie1.getValue(), CookieShopingCart.class)));
        }
        return cookieShopingCarts;
    }

    /**
     * 将购物车信息放入cookie中
     *
     * @param cookieShopingCarts 购物车信息
     */
    private void setShoppingCartsToCookie(HttpServletResponse response, List<CookieShopingCart> cookieShopingCarts) {
        Cookie cook = new Cookie("lecshop_shoppingcart", JSON.toJSONString(cookieShopingCarts));
        cook.setMaxAge(15 * 24 * 3600);
        cook.setPath("/");
        response.addCookie(cook);
    }
}
