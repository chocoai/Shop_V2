package com.lecshop.order;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.manager.util.ManagerConstant;
import com.lecshop.order.bean.Order;
import com.lecshop.order.bean.QueryCriteria;
import com.lecshop.order.service.OrderService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/5.
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
    @RequestMapping("/toqueryorders")
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
    @RequestMapping("/queryorders")
    @ResponseBody
    public BaseResponse queryOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria) {
        queryCriteria.setStoreId(CommonConstant.ADMIN_STOREID);
        return BaseResponse.build(orderService.queryOrders(pageHelper, queryCriteria));
    }

    /**
     * 确认付款
     *
     * @param id 订单id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmorder")
    @ResponseBody
    public int confirmOrder(long id, HttpServletRequest request) {
        return orderService.confirmOrder(id, CommonConstant.ADMIN_STOREID, AdminLoginUtils.getInstance().getManagerNameFromSession(request));
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/cancelorder")
    @ResponseBody
    public int cancelOrder(long id, HttpServletRequest request) {
        return orderService.cancelOrder(id, CommonConstant.ADMIN_STOREID, AdminLoginUtils.getInstance().getManagerNameFromSession(request));
    }

    /**
     * 修改价格
     *
     * @param id     订单id
     * @param price  修改价格(减多少钱)
     * @param reason 原因
     * @return 成功返回1 失败返回 0
     */
    @RequestMapping("/modifyprice")
    @ResponseBody
    public int modifyPrice(long id, BigDecimal price, String reason, HttpServletRequest request) {
        return orderService.modifyPrice(id, price, reason, CommonConstant.ADMIN_STOREID, AdminLoginUtils.getInstance().getManagerNameFromSession(request));
    }

    /**
     * 查询订单的物流模版信息
     *
     * @param id 订单id
     * @return 返回订单物流模版信息
     */
    @RequestMapping("/querylogisticstemplatebyorderid")
    @ResponseBody
    public LogisticsTemplate queryLogisticsTemplateByOrderId(long id) {
        return orderService.queryLogisticsTemplateByOrderId(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 发货
     *
     * @param id          订单id
     * @param waybillCode 运单号
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/deliverorder")
    @ResponseBody
    public int deliverOrder(long id, String waybillCode, HttpServletRequest request) {
        return orderService.deliverOrder(id, CommonConstant.ADMIN_STOREID, waybillCode, AdminLoginUtils.getInstance().getManagerNameFromSession(request));
    }

    /**
     * 根据订单id查询订单信息  (订单的所有信息 此接口慎用)
     *
     * @param id 订单id
     * @return 返回订单信息
     */
    @RequestMapping("/queryorderbyid")
    @ResponseBody
    public Order queryOrderById(long id) {
        return orderService.queryOrderById(id, CommonConstant.QUERY_WITH_NO_STORE);
    }

    /**
     * 跳转到订单详情页
     *
     * @param id 订单id
     * @return 返回订单详情页
     */
    @RequestMapping("/toorderdetail")
    public ModelAndView toOrderDetail(long id) {
        return new ModelAndView("order/orderdetail").addObject("id", id);
    }

    /**
     * 查询所有店铺订单
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回所有店铺订单
     */
    @RequestMapping("/querystoreorders")
    @ResponseBody
    public BaseResponse queryStoreOrders(PageHelper<Order> pageHelper, QueryCriteria queryCriteria) {
        return BaseResponse.build(orderService.queryStoreOrders(pageHelper, queryCriteria));
    }

    /**
     * 跳转到查询店铺订单页面
     *
     * @return 返回店铺订单页面
     */
    @RequestMapping("/toquerystoreorders")
    public ModelAndView toQueryStoreOrders() {
        return new ModelAndView("order/storeorderlist");
    }

    /**
     * 跳转到店铺订单详情页
     *
     * @param id 订单id
     * @return 返回订单详情页
     */
    @RequestMapping("/tostoreorderdetail")
    public ModelAndView toStoreOrderDetail(long id) {
        return new ModelAndView("order/storeorderdetail").addObject("id", id);
    }


}
