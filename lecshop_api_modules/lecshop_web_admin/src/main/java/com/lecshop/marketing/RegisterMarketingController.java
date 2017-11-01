package com.lecshop.marketing;

import com.lecshop.marketingmanager.bean.RegisterMarketing;
import com.lecshop.marketingmanager.service.RegisterMarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 注册营销设置控制器
 *
 * Created by LecShop on 2017/6/6.
 */
@Controller
public class RegisterMarketingController {

    /**
     * 自动注入注册营销service
     */
    @Autowired
    private RegisterMarketingService registerMarketingService;

    /**
     * 跳转到注册营销设置页面
     *
     * @return 注册营销设置页面
     */
    @RequestMapping("/toregistermarketing")
    public ModelAndView toRegisterMarketing() {
        return new ModelAndView("marketing/registermarketing");
    }

    /**
     * 查找注册营销
     *
     * @return 返回注册营销
     */
    @RequestMapping("/queryregistermarketing")
    @ResponseBody
    public RegisterMarketing queryRegisterMarketing() {
        return registerMarketingService.queryRegisterMarketing();
    }

    /**
     * 保存注册营销
     * @param registerMarketing 注册营销
     * @return  成功返回1 失败返回0
     */
    @RequestMapping("/updateregistermarketing")
    @ResponseBody
    public int updateRegisterMarketing(@RequestBody RegisterMarketing registerMarketing) {
        return registerMarketingService.updateRegisterMarketing(registerMarketing);
    }

 }
