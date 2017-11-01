package com.lecshop.system;

import com.lecshop.emailset.bean.EmailSet;
import com.lecshop.emailset.service.EmailSetService;
import com.lecshop.loginset.bean.LoginSetCommon;
import com.lecshop.loginset.service.LoginSetService;
import com.lecshop.payset.bean.PaySetCommon;
import com.lecshop.payset.service.PaySetService;
import com.lecshop.smsset.bean.SmsSet;
import com.lecshop.smsset.service.SmsSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 接口设置控制器
 */
@Controller
public class InterfaceSetController {
    /**
     * 注入邮箱接口设置实现类
     */
    @Autowired
    private EmailSetService emailSetService;
    /**
     * 注入短信接口设置实现类
     */
    @Autowired
    private SmsSetService smsSetService;
    /**
     * 注入登录接口设置实现类
     */
    @Autowired
    private LoginSetService loginSetService;
    /**
     * 注入支付接口设置实现类
     */
    @Autowired
    private PaySetService paySetService;

    /**
     * 跳转到邮箱接口设置页面
     *
     * @return 邮箱接口设置页面
     */
    @RequestMapping("/toemailset")
    public ModelAndView toEmailSet() {
        return new ModelAndView("system/emilset");
    }

    /**
     * 查询email设置
     *
     * @return email设置实体类
     */
    @RequestMapping("/queryemailset")
    @ResponseBody
    public EmailSet queryEmailSet() {
        return emailSetService.queryEmailSet();
    }

    /**
     * 编辑邮箱接口设置
     *
     * @param emailSet 邮箱接口设置实体类
     * @return -1 emailSet为空 1 编辑成功
     */
    @RequestMapping("/editemailset")
    @ResponseBody
    public int editEmailSet(@RequestBody EmailSet emailSet) {
        return emailSetService.editEmailSet(emailSet);
    }

    /**
     * 跳转到短信接口设置页面
     *
     * @return 短信接口设置页面
     */
    @RequestMapping("/tosmsset")
    public ModelAndView toSmsSet() {
        return new ModelAndView("system/smsset");
    }

    /**
     * 查询sms设置
     *
     * @return sms设置实体类
     */
    @RequestMapping("/querysmsset")
    @ResponseBody
    public SmsSet querySmsSet() {
        return smsSetService.querySmsSet();
    }

    /**
     * 编辑邮箱接口设置
     *
     * @param smsSet 短信接口设置实体类
     * @return -1 sms为空 1 编辑成功
     */
    @RequestMapping("/editsmsset")
    @ResponseBody
    public int editSmsSet(@RequestBody SmsSet smsSet) {
        return smsSetService.editSmsSet(smsSet);
    }

    /**
     * 跳转到登录接口设置页面
     *
     * @return 登录接口设置页面
     */
    @RequestMapping("/tologinset")
    @ResponseBody
    public ModelAndView toLoginSet() {
        return new ModelAndView("system/loginset");
    }

    /**
     * 查询登录接口设置
     *
     * @return 登录接口
     */
    @RequestMapping("/queryloginset")
    @ResponseBody
    public LoginSetCommon queryLoginSet() {
        return loginSetService.queryLoginSet();
    }

    /**
     * 编辑登录接口设置
     *
     * @param key      AppId
     * @param value    AppSecret
     * @param url      回调地址
     * @param isUse    是否启用
     * @param codeType 1微博 2QQ 3微信
     * @return -1 无法修改 1修改成功
     */
    @RequestMapping("/editloginset")
    @ResponseBody
    public int editLoginSet(String key, String value, String url, String isUse, int codeType) {
        return loginSetService.editLoginSet(key, value, url, isUse, codeType);
    }

    /**
     * 跳转到支付接口设置页面
     *
     * @return 支付接口设置页面
     */
    @RequestMapping("/topayset")
    public ModelAndView toPaySet() {
        return new ModelAndView("system/payset");
    }

    /**
     * 查询支付接口设置
     *
     * @return PaySetCommon
     */
    @RequestMapping("/querypayset")
    @ResponseBody
    public PaySetCommon queryPaySet() {
        return paySetService.queryPaySet();
    }

    /**
     * 编辑支付接口设置
     *
     * @param paySetCommon 实体类参数
     * @param codeType     支付设置类型 1 支付宝 2 微信 3 银联
     * @return -1编辑出错 >=1成功
     */
    @RequestMapping("/editpayset")
    @ResponseBody
    public int editPaySet(@RequestBody PaySetCommon paySetCommon, String codeType) {
        return paySetService.editPaySet(paySetCommon, codeType);
    }
}
