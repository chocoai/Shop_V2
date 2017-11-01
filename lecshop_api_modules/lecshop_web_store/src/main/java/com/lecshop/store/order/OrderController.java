package com.lecshop.store.order;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.order.bean.Order;
import com.lecshop.order.bean.QueryCriteria;
import com.lecshop.order.service.OrderService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/17.
 * 订单控制器
 */
@Controller
public class OrderController {

    /**
     * 订单服务接口
     */
    @Autowired
    private OrderService orderService;

    /**
     * 跳转到查询订单页面
     *
     * @return 返回订单页面
     */
    @RequestMapping("/store_toqueryorders")
    public ModelAndView toQueryOrders() {
        return new ModelAndView("order/orderlist");
    }

    /**
     * 分页查询订单
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回订单信息
     */
    @RequestMapping("/store_queryorders")
    @ResponseBody
    public BaseResponse queryOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria,HttpServletRequest request) {
            queryCriteria.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return BaseResponse.build(orderService.queryOrders(pageHelper, queryCriteria));
    }

    /**
     * 确认付款
     *
     * @param id 订单id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/store_confirmorder")
    @ResponseBody
    public int confirmOrder(long id, HttpServletRequest request) {
        return orderService.confirmOrder(id,StoreLoginUtils.getInstance().getStoreIdFromSession(request), StoreLoginUtils.getInstance().getCustomerNameFromSession(request));
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_cancelorder")
    @ResponseBody
    public int cancelOrder(long id, HttpServletRequest request) {
        return orderService.cancelOrder(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request), StoreLoginUtils.getInstance().getCustomerNameFromSession(request));
    }

    /**
     * 修改价格
     *
     * @param id     订单id
     * @param price  修改价格(减多少钱)
     * @param reason 原因
     * @return 成功返回1 失败返回 0
     */
    @RequestMapping("/store_modifyprice")
    @ResponseBody
    public int modifyPrice(long id, BigDecimal price, String reason, HttpServletRequest request) {
        return orderService.modifyPrice(id, price, reason, StoreLoginUtils.getInstance().getStoreIdFromSession(request),  StoreLoginUtils.getInstance().getCustomerNameFromSession(request));
    }

    /**
     * 查询订单的物流模版信息
     *
     * @param id 订单id
     * @return 返回订单物流模版信息
     */
    @RequestMapping("/store_querylogisticstemplatebyorderid")
    @ResponseBody
    public LogisticsTemplate queryLogisticsTemplateByOrderId(long id,HttpServletRequest request) {
        return orderService.queryLogisticsTemplateByOrderId(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 发货
     *
     * @param id          订单id
     * @param waybillCode 运单号
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/store_deliverorder")
    @ResponseBody
    public int deliverOrder(long id, String waybillCode, HttpServletRequest request) {
        return orderService.deliverOrder(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request), waybillCode,  StoreLoginUtils.getInstance().getCustomerNameFromSession(request));
    }

    /**
     * 根据订单id查询订单信息  (订单的所有信息 此接口慎用)
     *
     * @param id 订单id
     * @return 返回订单信息
     */
    @RequestMapping("/store_queryorderbyid")
    @ResponseBody
    public Order queryOrderById(long id,HttpServletRequest request) {
        return orderService.queryOrderById(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 跳转到订单详情页
     *
     * @param id 订单id
     * @return 返回订单详情页
     */
    @RequestMapping("/store_toorderdetail")
    public ModelAndView toOrderDetail(long id) {
        return new ModelAndView("order/orderdetail").addObject("id", id);
    }
}
