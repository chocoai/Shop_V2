package com.lecshop.order.service.impl;

import com.lecshop.order.bean.OrderSku;
import com.lecshop.order.mapper.OrderSkuMapper;
import com.lecshop.order.service.OrderSkuService;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dujinkai on 17/6/6.
 * 订单单品服务接口实现
 */
@Service
public class OrderSkuServiceImpl implements OrderSkuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(OrderSkuServiceImpl.class);

    /**
     * 订单单品数据库接口
     */
    @Autowired
    private OrderSkuMapper orderSkuMapper;

    @Override
    public List<OrderSku> queryByOrderId(long orderId) {
        logger.debug("queryByOrderId and orderId:{}", orderId);
        return orderSkuMapper.queryByOrderId(orderId);
    }

    @Override
    public List<OrderSku> queryByOrderIdAndSkuIds(long orderId, String skuIds) {
        logger.debug("queryByOrderCodeAndSkuIds and orderId:{} \r\n skuIds:{}", orderId, skuIds);

        if (StringUtils.isEmpty(skuIds)) {
            logger.error("queryByOrderCodeAndSkuIds fail due to params is empty....");
            return Collections.emptyList();
        }

        return Stream.of(skuIds.split(",")).map(skuId -> queryByOrderIdAndSkuId(orderId, skuId)).collect(Collectors.toList());

    }

    /**
     * 根据订单id和单品id查询订单商品
     *
     * @param orderId 订单id
     * @param skuId   单品id
     * @return 返回订单商品
     */
    private OrderSku queryByOrderIdAndSkuId(long orderId, String skuId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("skuId", skuId);
        return orderSkuMapper.queryByOrderIdAndSkuId(params);
    }
}
