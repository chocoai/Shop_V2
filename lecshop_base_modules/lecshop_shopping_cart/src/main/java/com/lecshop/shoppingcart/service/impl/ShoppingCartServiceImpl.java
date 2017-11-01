package com.lecshop.shoppingcart.service.impl;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.service.MarketingQueryService;
import com.lecshop.shoppingcart.bean.CookieShopingCart;
import com.lecshop.shoppingcart.bean.ShoppingCart;
import com.lecshop.shoppingcart.mapper.ShoppingCartMapper;
import com.lecshop.shoppingcart.service.ShoppingCartService;
import com.lecshop.sku.bean.Sku;
import com.lecshop.sku.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/7/5.
 * 购物车服务实现
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    /**
     * 注入购物车数据库接口
     */
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 注入单品服务接口
     */
    @Autowired
    private SkuService skuService;

    /**
     * 注入促销查询接口
     */
    @Autowired
    private MarketingQueryService marketingQueryService;

    @Override
    public int addShoppingCart(ShoppingCart shoppingCart) {

        logger.debug("addShoppingCart and shoppingCart:{}", shoppingCart);

        // 校验参数
        if (Objects.isNull(shoppingCart) || !shoppingCart.validate()) {
            logger.error("addShoppingCart fail due to params is error....");
            return -3;
        }

        // 校验单品信息 主要校验单品是否存在,单品库存和状态
        int validateResult = validateSku(shoppingCart.getSkuId(), shoppingCart.getNum());

        // 单品校验不通过直接返回失败
        if (validateResult != 0) {
            logger.debug("addShoppingCart fail due to validateSku fail.....");
            return validateResult;
        }

        // 首先查询用户购物车中是否已经包含此单品 如果包含 则更新其数量 如果不包含 则新增
        if (hasAlerdyExist(shoppingCart.getSkuId(), shoppingCart.getCustomerId())) {

            logger.info("customer shoppingcart has alerdy exist this sku and skuId:{} \r\n customerId:{}", shoppingCart.getSkuId(), shoppingCart.getCustomerId());

            Map<String, Object> params = new HashMap<>();
            params.put("skuId", shoppingCart.getSkuId());
            params.put("num", shoppingCart.getNum());
            params.put("customerId", shoppingCart.getCustomerId());

            return shoppingCartMapper.updateShoppingCartNum(params);

        } else {

            // 设置购物车中单品的默认促销(满减和满折促销随机选择一个)
            setDefaultMarketing(shoppingCart);

            return shoppingCartMapper.addShoppingCart(shoppingCart);
        }
    }


    @Override
    public int addShoppingCartToCookie(ShoppingCart shoppingCart, List<CookieShopingCart> cookieShopingCarts, Consumer<List<CookieShopingCart>> consumer) {

        logger.debug("addShoppingCartToCookie and shoppingCart:{} \r\n cookieShopingCarts:{}", shoppingCart, cookieShopingCarts);

        // 校验参数
        if (Objects.isNull(shoppingCart) || !shoppingCart.validateWithNoLogin()) {
            logger.error("addShoppingCartToCookie fail due to params is error....");
            return -3;
        }


        // 校验单品信息 主要校验单品是否存在,单品库存和状态
        int validateResult = validateSku(shoppingCart.getSkuId(), shoppingCart.getNum());

        // 单品校验不通过直接返回失败
        if (validateResult != 0) {
            logger.debug("addShoppingCartToCookie fail due to validateSku fail.....");
            return validateResult;
        }

        // cookie中的购物车有当前的单品 则追加数量 没有则新增
        if (hasAlerdyExistInCookie(shoppingCart.getSkuId(), cookieShopingCarts)) {

            // 更新购物车的数量
            updateShoppingCartNumInCookie(shoppingCart, cookieShopingCarts);
        } else {

            // 设置购物车中单品的默认促销(满减和满折促销随机选择一个)
            setDefaultMarketing(shoppingCart);

            cookieShopingCarts.add(CookieShopingCart.build(shoppingCart));
        }


        // 回调 将购物车信息放入cookie中
        consumer.accept(cookieShopingCarts);

        return 1;
    }

    /**
     * 更新cookie中购物车的数量
     *
     * @param shoppingCart       购物车
     * @param cookieShopingCarts cookie中的购物车信息
     */
    private void updateShoppingCartNumInCookie(ShoppingCart shoppingCart, List<CookieShopingCart> cookieShopingCarts) {
        cookieShopingCarts.stream().filter(cookieShopingCart -> cookieShopingCart.getSkuId().equals(shoppingCart.getSkuId())).findFirst().get().updateNum(shoppingCart.getNum());
    }

    /**
     * 设置购物车中单品的默认促销(满减和满折促销随机选择一个)
     *
     * @param shoppingCart 购物车
     */
    private void setDefaultMarketing(ShoppingCart shoppingCart) {
        getDefaultMarketingId(shoppingCart.getSkuId()).ifPresent(shoppingCart::setMarketingId);
    }


    /**
     * 获得默认的促销id
     *
     * @param skuId 单品id
     * @return 返回默认的促销id (促销是 满减和满折)
     */
    private Optional<Long> getDefaultMarketingId(String skuId) {

        // 单品有效的满减和满折促销 (随机查出一个)
        Marketing marketing = marketingQueryService.queryMarketingForShoppingCart(skuId);

        if (Objects.isNull(marketing)) {
            return Optional.empty();
        } else {
            return Optional.of(marketing.getId());
        }
    }

    /**
     * 校验单品信息 主要校验单品是否存在,单品库存和单品状态
     *
     * @param skuId  单品id
     * @param buyNum 加入购物车的数量
     * @return 成功返回0  -2 单品不存在 -1 库存不足 -4 单品状态不对
     */
    private int validateSku(String skuId, int buyNum) {
        Sku sku = skuService.querySkuById(skuId);
        if (Objects.isNull(sku)) {
            logger.error("validateSku fail due to sku is not exist");
            return -2;
        }

        // 校验单品是否是上架状态
        if (!sku.validateStatus()) {
            logger.error("validateSku fail due to  sku status is error...");
            return -4;
        }


        //校验单品是否还有库存
        if (!sku.validateStock(buyNum)) {
            logger.error("validateSku fail due to has no stock...");
            return -1;
        }

        return 0;
    }

    /**
     * 判断cookie中的购物车是否已经包含要添加的单品
     *
     * @param skuId              单品id
     * @param cookieShopingCarts cookie中的购物车
     * @return 包含返回true 不包含返回false
     */
    private boolean hasAlerdyExistInCookie(String skuId, List<CookieShopingCart> cookieShopingCarts) {
        if (CollectionUtils.isEmpty(cookieShopingCarts)) {
            return false;
        }
        return cookieShopingCarts.stream().anyMatch(cookieShopingCart -> skuId.equals(cookieShopingCart.getSkuId()));
    }

    /**
     * 判断购物车中是否已经存在用户的要购买的单品
     *
     * @param skuId      单品id
     * @param customerId 会员id
     * @return 存在 返回true  不存在返回false
     */
    private boolean hasAlerdyExist(String skuId, long customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("skuId", skuId);
        params.put("customerId", customerId);

        return shoppingCartMapper.queryBySkuIdAndCustomerIdCount(params) != 0;
    }
}
