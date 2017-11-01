package com.lecshop.site;

import com.lecshop.baseinfo.bean.BaseInfoSet;
import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 信息设置控制器
 *
 * @author sunluyang on 2017/5/17.
 */
@Controller
public class InfoSettingController {
    /**
     * 注入信息设置实现类
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;

    /**
     * 跳转到基本信息页面
     *
     * @return 基本信息页面
     */
    @RequestMapping("/tobaseinfoset")
    public ModelAndView toBaseInfoSet() {
        return new ModelAndView("site/baseinfo");
    }

    /**
     * 查询基本信息和高级信息设置
     *
     * @return 基本信息和高级信息设置实体类
     */
    @RequestMapping("/querybaseinfoset")
    @ResponseBody
    public BaseInfoSet queryBaseInfoSet() {
        return baseInfoSetService.queryBaseInfoSet();
    }

    /**
     * 查询基本信息和高级信息设置,用于页面图标展示,不需要拦截
     *
     * @return 基本信息和高级信息设置实体类
     */
    @RequestMapping("/querybaseinfosetunauth")
    @ResponseBody
    @UnAuth
    public BaseInfoSet queryBaseInfoSetUnAuth() {
        return baseInfoSetService.queryBaseInfoSet();
    }

    /**
     * 编辑基本信息和高级信息
     *
     * @param baseInfoSet 基本信息实体类
     * @param type        1 基本信息 0高级信息
     * @return -1编辑失败 1编辑成功 0编辑失败
     */
    @RequestMapping("/editbaseinfoset")
    @ResponseBody
    public int editBaseInfoSet(@RequestBody BaseInfoSet baseInfoSet, int type) {
        return baseInfoSetService.editBaseInfoSet(baseInfoSet, type);
    }
}
