package com.lecshop.backorder;

import com.lecshop.backorder.bean.BackOrder;
import com.lecshop.backorder.bean.QueryCriteria;
import com.lecshop.backorder.service.BackOrderService;
import com.lecshop.manager.util.ManagerConstant;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/6/6.
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
    @RequestMapping("/toquerybackorder")
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
    @RequestMapping("/querybackorders")
    @ResponseBody
    public BaseResponse queryBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria) {
        queryCriteria.setStoreId(CommonConstant.ADMIN_STOREID);
        return BaseResponse.build(backOrderService.queryBackOrders(pageHelper, queryCriteria));
    }

    /**
     * 跳转到退款审核页面
     *
     * @param backOrderId 退单id
     * @return 返回退款审核页面
     */
    @RequestMapping("/torefundreview")
    public ModelAndView toRefundReview(long backOrderId) {
        return new ModelAndView("backorder/refundreview").addObject("backOrderId", backOrderId);
    }

    /**
     * 根据退单id查询退单信息(包含退单的商品, 和操作日志 此接口调用慎重)
     *
     * @param backOrderId 退单id
     * @return 返回退单信息
     */
    @RequestMapping("/querybackorderbyid")
    @ResponseBody
    public BackOrder queryBackOrderById(long backOrderId) {
        return backOrderService.queryBackOrderById(backOrderId, CommonConstant.QUERY_WITH_NO_STORE);
    }


    /**
     * 同意退款
     *
     * @param backOrderId 提单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/agreetorefund")
    @ResponseBody
    public int agreeToRefund(long backOrderId, String message) {
        return backOrderService.agreeToRefund(backOrderId, CommonConstant.ADMIN_STOREID, message);
    }

    /**
     * 拒绝退单
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/refuserefund")
    @ResponseBody
    public int refuseRefund(long backOrderId, String message) {
        return backOrderService.refuseRefund(backOrderId, CommonConstant.ADMIN_STOREID, message);
    }

    /**
     * 跳转到退货审核页面
     *
     * @param backOrderId 退单id
     * @return 返回退货审核页面
     */
    @RequestMapping("/toreturnreview")
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
    @RequestMapping("/agreetoreturn")
    @ResponseBody
    public int agreeToReturn(long backOrderId, String message) {
        return backOrderService.agreeToReturn(backOrderId, CommonConstant.ADMIN_STOREID, message);
    }

    /**
     * 拒绝退货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/refusereturn")
    @ResponseBody
    public int refuseReturn(long backOrderId, String message) {
        return backOrderService.refuseReturn(backOrderId, CommonConstant.ADMIN_STOREID, message);
    }

    /**
     * 同意确认退货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @param money       实际退款
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/confirmreturn")
    @ResponseBody
    public int confirmReturn(long backOrderId, String message, BigDecimal money) {
        return backOrderService.confirmReturn(backOrderId, CommonConstant.ADMIN_STOREID, message, money);
    }

    /**
     * 拒绝收货
     *
     * @param backOrderId 退单id
     * @param message     留言
     * @return 成功返回1  失败返回0  当前退单状态不对 返回-1
     */
    @RequestMapping("/refusetoreceive")
    @ResponseBody
    public int refuseToReceive(long backOrderId, String message) {
        return backOrderService.refuseToReceive(backOrderId, CommonConstant.ADMIN_STOREID, message);
    }


    /**
     * 跳转到店铺退单页面
     *
     * @return 返回店铺退单页面
     */
    @RequestMapping("/toquerystorebackorders")
    public ModelAndView toQueryStoreBackOrders() {
        return new ModelAndView("store/storebackorderlist");
    }


    /**
     * 查询店铺退单列表
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 搜索条件
     * @return 返回店铺退单数据
     */
    @RequestMapping("/querystorebackorders")
    @ResponseBody
    public BaseResponse queryStoreBackOrders(PageHelper<BackOrder> pageHelper, QueryCriteria queryCriteria) {
        return BaseResponse.build(backOrderService.queryStoreBackOrders(pageHelper, queryCriteria));
    }

    /**
     * 跳转到店铺退单详情页
     *
     * @param backOrderId 退单id
     * @return 返回退单详情页面
     */
    @RequestMapping("/tostorebackorderdetail")
    public ModelAndView toStoreBackOrderDetail(long backOrderId) {
        return new ModelAndView("store/storebackordertail").addObject("backOrderId", backOrderId);
    }
}
