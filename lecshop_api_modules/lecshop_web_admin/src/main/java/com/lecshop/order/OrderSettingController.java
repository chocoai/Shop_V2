package com.lecshop.order;

import com.lecshop.order.bean.OrderSetting;
import com.lecshop.order.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 订单设置控制器
 *
 * Created by LecShop on 2017/6/5.
 */
@Controller
public class OrderSettingController {

    /**
     * 自动注入订单设置service
     */
    @Autowired
    private OrderSettingService orderSettingService;

    /**
     * 跳转到订单设置页面
     *
     * @return 订单设置页面
     */
    @RequestMapping("/toordersetting")
    public ModelAndView toOrderSetting() {
        return new ModelAndView("order/ordersetting");
    }

    /**
     * 查找订单设置
     *
     * @return 返回订单设置
     */
    @RequestMapping("/queryordersetting")
    @ResponseBody
    public OrderSetting queryOrderSetting() {
        return orderSettingService.queryOrderSetting();
    }

    /**
     * 修改订单设置
     *
     * @param orderSetting 订单设置
     * @return             成功返回1 失败返回0
     */
    @RequestMapping("/updateordersetting")
    @ResponseBody
    public int updateOrderSetting(@RequestBody OrderSetting orderSetting) {
        return orderSettingService.updateOrderSetting(orderSetting);
    }
}
