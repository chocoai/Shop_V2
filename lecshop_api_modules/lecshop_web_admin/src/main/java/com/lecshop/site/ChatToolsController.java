package com.lecshop.site;

import com.lecshop.onlineservice.bean.CustomerService;
import com.lecshop.onlineservice.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 聊天工具控制器
 *
 * @author sunluyang on 2017/5/19.
 */
@Controller
public class ChatToolsController {
    /**
     * 注入在线客服实现类
     */
    @Autowired
    private CustomerServiceService customerServiceService;

    /**
     * 跳转到快商通设置页面
     *
     * @return 快商通设置页面
     */
    @RequestMapping("/tokuaishangtong")
    public ModelAndView toKuaiShangTong() {
        return new ModelAndView("");
    }

    /**
     * 跳转到在线客服设置页面
     *
     * @return 在线客服设置页面
     */
    @RequestMapping("/tocustomerservice")
    public ModelAndView toOnlineService() {
        return new ModelAndView("site/customerservice");
    }

    /**
     * 查询在线客服
     *
     * @return 在线客服实体类
     */
    @RequestMapping("/querycustomerservice")
    @ResponseBody
    public CustomerService queryCustomerService() {
        return customerServiceService.queryCustomerService();
    }

    /**
     * 编辑在线客服
     *
     * @param customerService 在线客服实体类
     * @return 返回编辑行数 -1 失败 >=1 成功
     */
    @RequestMapping("/editcustomerserviceinfo")
    @ResponseBody
    public int editCustomerServiceInfo(@RequestBody CustomerService customerService) {
        return customerServiceService.editCustomerServiceInfo(customerService);
    }
}
