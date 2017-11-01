package com.lecshop.store;

import com.lecshop.baseinfo.service.BaseInfoSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 审核开关控制器
 *
 * Created by LecShop on 2017/6/20.
 */
@Controller
public class AuditSwitchController {

    /**
     * 注入信息设置实现类
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;

    /**
     * 跳转至审核开关页面
     *
     * @return 审核开关页面
     */
    @RequestMapping("/toauditswitchsetting")
    public ModelAndView toAuditSwitch() {
        return new ModelAndView("store/auditswitch");
    }

    /**
     * 设置审核开关
     *
     * @param storeSpuAudit 审核开关
     * @return            成功返回1，失败返回0
     */
    @RequestMapping("/setAuditSwitch")
    @ResponseBody
    public int setAuditSwitch(String storeSpuAudit) {
        return baseInfoSetService.setAuditSwitch(storeSpuAudit);
    }

}
