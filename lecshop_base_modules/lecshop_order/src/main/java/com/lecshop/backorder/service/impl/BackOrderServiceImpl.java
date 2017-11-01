package com.lecshop.backorder.service.impl;

import com.lecshop.backorder.bean.BackOrder;
import com.lecshop.backorder.bean.BackOrderLog;
import com.lecshop.backorder.bean.QueryCriteria;
import com.lecshop.backorder.mapper.BackOrderMapper;
import com.lecshop.backorder.service.BackOrderLogService;
import com.lecshop.backorder.service.BackOrderService;
import com.lecshop.order.service.OrderSkuService;
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
 * Created by dujinkai on 17/6/6.
 * 退单服务接口
 */
@Service
public class BackOrderServiceImpl implements BackOrderService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(BackOrderServiceImpl.class);

    /**
     * 退单数据库接口
     */
    @Autowired
    private BackOrderMapper backOrderMapper;

    /**
     * 退单操作日志服务接口
     */
    @Autowired
    private BackOrderLogService backOrderLogService;

    /**
     * 注入订单商品服务
     */
    @Autowired
    private OrderSkuService orderSkuService;

    @Override
    public PageHelper<BackOrder> queryBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria) {
        logger.debug("queryBackOrders pageHelper:{} \r\n queryCriteria:{}", pageHelper, queryCriteria);
        // 查询参数
        Map<String, Object> params = queryCriteria.getQueryMap();

        return pageHelper.setListDates(backOrderMapper.queryBackOrders(pageHelper.getQueryParams(params, backOrderMapper.queryBackOrderCount(params))));
    }

    @Override
    public PageHelper<BackOrder> queryStoreBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria) {
        logger.debug("queryStoreBackOrders pageHelper:{} \r\n queryCriteria:{}", pageHelper, queryCriteria);

        // 查询参数
        Map<String, Object> params = queryCriteria.getQueryMap();

        return pageHelper.setListDates(backOrderMapper.queryStoreBackOrders(pageHelper.getQueryParams(params, backOrderMapper.queryStoreBackOrdersCount(params))));
    }

    @Override
    public BackOrder queryBackOrderById(long backOrderId, long storeId) {
        logger.debug("queryBackOrderById and backOrderId:{} \r\n storeId:{}", backOrderId, storeId);

        Map<String, Object> parmas = new HashMap<>();
        parmas.put("id", backOrderId);
        parmas.put("storeId", storeId);

        BackOrder backOrder = backOrderMapper.queryBackOrderById(parmas);

        if (Objects.isNull(backOrder)) {
            logger.error("queryBackOrderById fail due to BackOrder is not exist....");
            return backOrder;
        }

        // 设置退单操作日志
        backOrder.setBackOrderLogs(backOrderLogService.queryByBackOrderId(backOrderId));

        // 设置退单商品信息
        setBackOrderSkus(backOrder);

        return backOrder;
    }

    @Transactional
    @Override
    public int agreeToRefund(long backOrderId, long storeId, String message) {
        logger.debug("agreeToRefund and backOrderId:{} \r\n storeId:{} \r\n message:{}", backOrderId, storeId, message);

        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);


        if (backOrderMapper.agreeToRefund(params) != 1) {
            logger.error("agreeToRefund fail due to backorder status is not right.....");
            return -1;
        }

        //TODO 如果是预存款的订单  则将钱退至预存款中

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForAgreeToRefund(backOrderId, message));

        return 1;
    }

    @Transactional
    @Override
    public int refuseRefund(long backOrderId, long storeId, String message) {
        logger.debug("refuseRefund and backOrderId:{} \r\n storeId:{} \r\n message:{}", backOrderId, storeId, message);


        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);


        if (backOrderMapper.refuseRefund(params) != 1) {
            logger.error("refuseRefund fail due to backorder status is not right.....");
            return -1;
        }

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForRefuseToRefund(backOrderId, message));

        return 1;
    }

    @Transactional
    @Override
    public int agreeToReturn(long backOrderId, long storeId, String message) {
        logger.debug("agreeToReturn and backOrderId:{} \r\n storeId:{} \r\n message:{}", backOrderId, storeId, message);

        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);

        if (backOrderMapper.agreeToReturn(params) != 1) {
            logger.error("agreeToReturn fail due to backorder status is not right.....");
            return -1;
        }

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForAgreeToReturn(backOrderId, message));

        return 1;
    }

    @Transactional
    @Override
    public int refuseReturn(long backOrderId, long storeId, String message) {
        logger.debug("refuseReturn and backOrderId:{} \r\n storeId:{} \r\n message:{}", backOrderId, storeId, message);

        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);

        if (backOrderMapper.refuseRrturn(params) != 1) {
            logger.error("refuseReturn fail due to backorder status is not right.....");
            return -1;
        }

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForRefuseReturn(backOrderId, message));

        return 1;
    }

    @Transactional
    @Override
    public int confirmReturn(long backOrderId, long storeId, String message, BigDecimal money) {
        logger.debug("confirmReturn and backOrderId:{} \r\n ,storeId:{} \r\n ,message:{} \r\n , money:{}", backOrderId, storeId, message, money);

        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);
        params.put("money", money);

        if (backOrderMapper.confirmReturn(params) != 1) {
            logger.error("confirmReturn fail due to backorder status is not right.....");
            return -1;
        }

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForConfirmReturn(backOrderId, message));

        return 1;
    }

    @Transactional
    @Override
    public int refuseToReceive(long backOrderId, long storeId, String message) {
        logger.debug("refuseToReceive and backOrderId:{} \r\n storeId:{} \r\n message:{}", backOrderId, storeId, message);


        Map<String, Object> params = new HashMap<>();
        params.put("id", backOrderId);
        params.put("storeId", storeId);

        if (backOrderMapper.refuseToReceive(params) != 1) {
            logger.error("refuseToReceive fail due to backorder status is not right.....");
            return -1;
        }

        // 新增退单日志
        backOrderLogService.addBackOrderLog(BackOrderLog.buildForRefuseToReceive(backOrderId, message));

        return 1;
    }

    /**
     * 设置退单的商品信息
     *
     * @param backOrder 退单信息
     */
    private void setBackOrderSkus(BackOrder backOrder) {
        backOrder.setOrderSkus(orderSkuService.queryByOrderIdAndSkuIds(backOrder.getOrderId(), backOrder.getSkuIds()));
    }
}
