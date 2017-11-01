package com.lecshop.order.service.impl;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.logistics.service.LogisticsTemplateService;
import com.lecshop.order.bean.Order;
import com.lecshop.order.bean.OrderOperatonLog;
import com.lecshop.order.bean.QueryCriteria;
import com.lecshop.order.mapper.OrderMapper;
import com.lecshop.order.service.OrderAttrService;
import com.lecshop.order.service.OrderOperatonLogService;
import com.lecshop.order.service.OrderService;
import com.lecshop.order.service.OrderSkuService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/5.
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 注入订单数据库接口
     */
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 注入运费模版服务接口
     */
    @Autowired
    private LogisticsTemplateService logisticsTemplateService;

    /**
     * 订单操作日志服务接口
     */
    @Autowired
    private OrderOperatonLogService orderOperatonLogService;

    /**
     * 订单附属信息
     */
    @Autowired
    private OrderAttrService orderAttrService;

    /**
     * 订单单品服务接口
     */
    @Autowired
    private OrderSkuService orderSkuService;

    @Override
    public PageHelper<Order> queryOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria) {
        logger.debug("queryOrders and pageHelper:{} \r\n queryCriteria:{}", pageHelper, queryCriteria);

        Map<String, Object> params = queryCriteria.getQueryMap();

        return pageHelper.setListDates(orderMapper.queryOrders(pageHelper.getQueryParams(params, orderMapper.queryOrderCount(params))));
    }

    @Override
    public PageHelper<Order> queryStoreOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria) {

        logger.debug("queryStoreOrders and pageHelper:{} \r\n queryCriteria:{}", pageHelper, queryCriteria);

        Map<String, Object> params = queryCriteria.getQueryMap();

        return pageHelper.setListDates(orderMapper.queryStoreOrders(pageHelper.getQueryParams(params, orderMapper.queryStoreOrdersCount(params))));
    }

    @Override
    public int confirmOrder(long id, long storeId, String operationName) {
        logger.debug("confirmOrder and id:{}", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        // 确认订单
        orderMapper.confirmOrder(params);

        // 新增订单操作日志
        orderOperatonLogService.addOrderOperatonLog(OrderOperatonLog.buildForConfirmOrder(id, operationName));

        return 1;
    }

    @Override
    public int cancelOrder(long id, long storeId, String operationName) {
        logger.debug("cancelOrder and id:{} \r\n storeid:{}", id, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        orderMapper.cancelOrder(params);

        orderOperatonLogService.addOrderOperatonLog(OrderOperatonLog.buildForCancelOrdere(id, operationName));
        return 1;
    }

    @Transactional
    @Override
    public int modifyPrice(long id, BigDecimal price, String reason, long storeId, String operationName) {
        logger.debug("modifyPrice and id:{} \r\n price:{} \r\n reason:{} \r\n storeId:{}", id, price, reason, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        params.put("price", price);
        // 修改订单价格
        orderMapper.modifyPrice(params);

        // 增加订单操作日志
        orderOperatonLogService.addOrderOperatonLog(OrderOperatonLog.buildForModifyPrice(id, reason, operationName));

        return 1;
    }

    @Override
    public LogisticsTemplate queryLogisticsTemplateByOrderId(long id, long storeId) {
        logger.debug("queryLogisticsTemplateByOrderId and id :{} \r\n storeId:{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return logisticsTemplateService.queryLogisticsTemplateWithCompany(orderMapper.queryTemplateId(params), storeId);
    }

    @Override
    public int deliverOrder(long id, long storeId, String waybillCode, String operationName) {
        logger.debug("deliverOrder and id:{} \r\n storeId:{} \r\n ,waybillCode:{} ", id, storeId, waybillCode);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        params.put("waybillCode", waybillCode);

        orderMapper.deliverOrder(params);

        orderOperatonLogService.addOrderOperatonLog(OrderOperatonLog.buildForDeliverOrder(id, operationName));
        return 1;
    }

    @Override
    public Order queryOrderById(long id, long storeId) {
        logger.debug("queryOrderById and id:{} \r\n storeId:{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        // 查询订单信息
        Order order = orderMapper.queryOrderById(params);

        if (Objects.isNull(order)) {
            logger.error("queryOrderById fail...due to order is null....");
            return order;
        }

        // 设置订单操作日志
        order.setOrderOperatonLogs(orderOperatonLogService.queryOrderOperatonLogByOrderId(id));

        // 设置订单的物流模版信息
        order.setLogisticsTemplate(logisticsTemplateService.queryLogisticsTemplateWithCompany(order.getFreightTemplateId(), CommonConstant.QUERY_WITH_NO_STORE));

        // 设置订单附属信息
        order.setOrderAttr(orderAttrService.queryByOrderId(id));

        // 设置订单单品信息
        order.setOrderSkus(orderSkuService.queryByOrderId(id));

        return order;
    }
}
