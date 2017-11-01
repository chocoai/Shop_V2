package com.lecshop.order;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.logistics.service.LogisticsTemplateService;
import com.lecshop.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 物流模版控制器
 */
@Controller
public class LogisticsTemplateController {

    /**
     * 注入物流模版服务接口
     */
    @Autowired
    private LogisticsTemplateService logisticsTemplateService;

    /**
     * 跳转到物流模版页面
     *
     * @return 跳转到物流模版页面
     */
    @RequestMapping("/toquerylogisticstemplate")
    public ModelAndView toQueryLogisticsTemplate() {
        return new ModelAndView("order/logisticstemplate");
    }

    /**
     * 跳转到新增模版页面
     *
     * @return 返回新增模版页面
     */
    @RequestMapping("/toaddtemplate")
    public ModelAndView toAddTemplate() {
        return new ModelAndView("order/addlogistictemplate");
    }

    /**
     * 查询所有的物流模版
     *
     * @return 返回所有的物流模版
     */
    @RequestMapping("/queryalllogisticstemplates")
    @ResponseBody
    public List<LogisticsTemplate> queryAllLogisticsTemplates() {
        return logisticsTemplateService.queryAllTemplates(CommonConstant.ADMIN_STOREID);
    }

    /**
     * 设置默认运费模版
     *
     * @param id 模版id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/setdefaulttemplate")
    @ResponseBody
    public int setDefaultTemplate(long id) {
        return logisticsTemplateService.setDefaultTemplate(id, CommonConstant.ADMIN_STOREID);
    }


    /**
     * 新增物流模版
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addlogisticstemplate")
    @ResponseBody
    public int addLogisticsTemplate(@RequestBody LogisticsTemplate logisticsTemplate) {
        logisticsTemplate.setStoreId(CommonConstant.ADMIN_STOREID);
        return logisticsTemplateService.addLogisticsTemplate(logisticsTemplate);
    }

    /**
     * 根据物流模版id查询物流模版信息(包含运费方式和运费方式区域)
     *
     * @param id 运费模版id
     * @return 返回物流模版
     */
    @RequestMapping("/querylogisticstemplatebyid")
    @ResponseBody
    public LogisticsTemplate queryLogisticsTemplateById(long id) {
        return logisticsTemplateService.queryLogisticsTemplate(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 根据id删除运费模版 包含运费方式和运费方式区域
     *
     * @param id 运费模版id
     * @return 成功返回1 失败返回0  是默认模版不能删除返回-1
     */
    @RequestMapping("/deletelogisticstemplatebyid")
    @ResponseBody
    public int deleteLogisticsTemplateById(long id) {
        return logisticsTemplateService.deleteLogisticsTemplate(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 跳转到修改物流模版页面
     *
     * @param id 物流模版id
     * @return 返回物流模版页面
     */
    @RequestMapping("/toupdatelogisticstemplate")
    public ModelAndView toUpdateLogisticsTemplate(long id) {
        return new ModelAndView("order/updatelogistictemplte").addObject("id", id);
    }

    /**
     * 更新物流模版
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatelogisticstemplate")
    @ResponseBody
    public int updateLogisticsTemplate(@RequestBody LogisticsTemplate logisticsTemplate) {
        logisticsTemplate.setStoreId(CommonConstant.ADMIN_STOREID);
        return logisticsTemplateService.updateLogisticsTemplate(logisticsTemplate);
    }
}
