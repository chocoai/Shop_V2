package com.lecshop.site;

import com.lecshop.exceptionpage.bean.ExceptionPage;
import com.lecshop.exceptionpage.service.ExceptionPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 异常页面控制器
 *
 * @author sunluyang on 2017/5/18.
 */
@Controller
public class ExceptionPageController {
    /**
     * 注入异常页面实现类
     */
    @Autowired
    private ExceptionPageService exceptionPageService;

    /**
     * 跳转到异常设置页面
     *
     * @return 异常设置页面
     */
    @RequestMapping("/toexceptionpage")
    public ModelAndView toExceptionPage() {
        return new ModelAndView("site/exceptionpage");
    }

    /**
     * 查询异常页面
     *
     * @return 返回异常页面集合
     */
    @RequestMapping("/queryexceptionpage")
    @ResponseBody
    public List<ExceptionPage> queryExceptionPage() {
        return exceptionPageService.queryExceptionPage();
    }

    /**
     * 编辑异常页面
     *
     * @param exceptionPage 异常页面实体类
     * @return -1编辑失败 1编辑成功
     */
    @RequestMapping("/editexceptionpage")
    @ResponseBody
    public int editExceptionPage(@RequestBody ExceptionPage exceptionPage) {
        return exceptionPageService.editExceptionPage(exceptionPage);
    }
}
