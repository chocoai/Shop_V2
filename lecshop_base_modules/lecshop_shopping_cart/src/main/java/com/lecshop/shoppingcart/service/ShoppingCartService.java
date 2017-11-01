package com.lecshop.shoppingcart.service;

import com.lecshop.shoppingcart.bean.CookieShopingCart;
import com.lecshop.shoppingcart.bean.ShoppingCart;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/7/5.
 * 购物车服务接口
 */
public interface ShoppingCartService {

    /**
     * 加入购物车(登录情况)
     *
     * @param shoppingCart 购物车
     * @return 返回码: 1 成功 0 失败 -1 库存不足  -2 单品不存在 -3 参数错误 -4 单品已下架
     */
    int addShoppingCart(ShoppingCart shoppingCart);


    /**
     * 加入购物车到cookie中 (未登录请求)
     *
     * @param shoppingCart       购物车
     * @param cookieShopingCarts cookie 中已经存在的购物车信息
     * @param consumer           回调
     * @return 返回码: 1 成功 0 失败 -1 库存不足  -2 单品不存在 -3 参数错误 -4 单品已下架
     */
    int addShoppingCartToCookie(ShoppingCart shoppingCart, List<CookieShopingCart> cookieShopingCarts, Consumer<List<CookieShopingCart>> consumer);

}
