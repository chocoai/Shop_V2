package com.lecshop.site;

import com.lecshop.seosetting.bean.SystemSeo;
import com.lecshop.seosetting.service.SystemSeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * seo设置控制器
 *
 * @author sunluyang on 2017/6/30.
 */
@Controller
public class SeoSetController {

    /**
     * 自动注入系统设置seo业务层
     */
    @Autowired
    private SystemSeoService systemSeoService;

    /**
     * 跳转到SEO设置页面
     *
     * @return SEO设置页面
     */
    @RequestMapping("/toseoset")
    public ModelAndView toSeoSet() {
        return new ModelAndView("site/seoset");
    }

    /**
     * 查询系统seo设置
     *
     * @return 系统seo设置
     */
    @RequestMapping("/querysystemseo")
    @ResponseBody
    public SystemSeo querySystemSeo() {
        return systemSeoService.querySystemSeo();
    }

    /**
     * 修改系统seo设置
     *
     * @param systemSeo 系统seo设置
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updatesystemseo")
    @ResponseBody
    public int updateSystemSeo(@RequestBody SystemSeo systemSeo) {
        return systemSeoService.updateSystemSeo(systemSeo);
    }

}
