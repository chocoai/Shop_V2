package com.lecshop.shoppingcart.mapper;

import com.lecshop.shoppingcart.bean.ShoppingCart;

import java.util.Map;

/**
 * Created by dujinkai on 17/7/5.
 * 购物车数据库接口
 */
public interface ShoppingCartMapper {

    /**
     * 查询购物车中用户的指定单品的数量
     *
     * @param params 查询参数
     * @return 返回用户购物车中指定商品的数量
     */
    int queryBySkuIdAndCustomerIdCount(Map<String, Object> params);

    /**
     * 更改购物车的数量
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int updateShoppingCartNum(Map<String, Object> params);

    /**
     * 新增购物车
     *
     * @param shoppingCart 购物车数据
     * @return 成功返回1 失败返回0
     */
    int addShoppingCart(ShoppingCart shoppingCart);
}
