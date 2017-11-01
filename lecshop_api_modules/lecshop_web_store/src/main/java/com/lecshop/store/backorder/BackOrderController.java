package com.lecshop.store.backorder;

import com.lecshop.backorder.bean.BackOrder;
import com.lecshop.backorder.bean.QueryCriteria;
import com.lecshop.backorder.service.BackOrderService;
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
 * Created by dujinkai on 17/6/18.
 * 退单控制器
 */
@Controller
public class BackOrderController {

    /**
     * 注入退单服务接口
     */
    @Autowired
    private BackOrderService backOrderService;

    /**
     * 跳转到退单列表页面
     *
     * @return 返回退单列表页面
     */
    @RequestMapping("/store_toquerybackorder")
    public ModelAndView toQueryBackOrder() {
        return new ModelAndView("backorder/backorderlist");
    }

    /**
     * 分页查询退单列表
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回退单列表
     */
    @RequestMapping("/store_querybackorders")
    @ResponseBody
    public BaseResponse queryBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria, HttpServletRequest request) {
        queryCriteria.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return BaseResponse.build(backOrderService.queryBackOrders(pageHelper, queryCriteria));
    }

    /**
     * 跳转到退款审核页面
     *
     * @param backOrderId 退单id
     * @return 返回退款审核页面
     */
    @RequestMapping("/store_torefundreview")
    public ModelAndView toRefundReview(long backOrderId) {
        return new ModelAndView("backorder/refundreview").addObject("backOrderId", backOrderId);
    }

    /**
     * 根据退单id查询退单信息(包含退单的商品, 和操作日志 此接口调用慎重)
     *
     * @param backOrderId 退单id
     * @return 返回退单信息
     */
    @RequestMapping("/store_querybackorderbyid")
    @ResponseBody
    public BackOrder queryBackOrderById(long backOrderId, HttpServletRequest request) {
        return backOrderService.queryBackOrderById(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }


    /**
     * 同意退款
     *
     * @param backOrderId 提单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_agreetorefund")
    @ResponseBody
    public int agreeToRefund(long backOrderId, String message, HttpServletRequest request) {
        return backOrderService.agreeToRefund(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message);
    }

    /**
     * 拒绝退单
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_refuserefund")
    @ResponseBody
    public int refuseRefund(long backOrderId, String message, HttpServletRequest request) {
        return backOrderService.refuseRefund(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message);
    }

    /**
     * 跳转到退货审核页面
     *
     * @param backOrderId 退单id
     * @return 返回退货审核页面
     */
    @RequestMapping("/store_toreturnreview")
    public ModelAndView toReturnReview(long backOrderId) {
        return new ModelAndView("backorder/retrunreview").addObject("backOrderId", backOrderId);
    }

    /**
     * 同意退货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_agreetoreturn")
    @ResponseBody
    public int agreeToReturn(long backOrderId, String message, HttpServletRequest request) {
        return backOrderService.agreeToReturn(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message);
    }

    /**
     * 拒绝退货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_refusereturn")
    @ResponseBody
    public int refuseReturn(long backOrderId, String message, HttpServletRequest request) {
        return backOrderService.refuseReturn(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message);
    }

    /**
     * 同意确认退货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @param money       实际退款
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_confirmreturn")
    @ResponseBody
    public int confirmReturn(long backOrderId, String message, BigDecimal money, HttpServletRequest request) {
        return backOrderService.confirmReturn(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message, money);
    }

    /**
     * 拒绝收货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/store_refusetoreceive")
    @ResponseBody
    public int refuseToReceive(long backOrderId, String message, HttpServletRequest request) {
        return backOrderService.refuseToReceive(backOrderId, StoreLoginUtils.getInstance().getStoreIdFromSession(request), message);
    }

}
