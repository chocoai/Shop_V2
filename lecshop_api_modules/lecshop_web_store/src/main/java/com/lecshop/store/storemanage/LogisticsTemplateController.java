package com.lecshop.store.storemanage;

import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.logistics.service.LogisticsTemplateService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/store_toquerylogisticstemplate")
    public ModelAndView toQueryLogisticsTemplate() {
        return new ModelAndView("storemanage/logisticstemplate");
    }

    /**
     * 跳转到新增模版页面
     *
     * @return 返回新增模版页面
     */
    @RequestMapping("/store_toaddtemplate")
    public ModelAndView toAddTemplate() {
        return new ModelAndView("storemanage/addlogistictemplate");
    }

    /**
     * 查询所有的物流模版
     *
     * @return 返回所有的物流模版
     */
    @RequestMapping("/store_queryalllogisticstemplates")
    @ResponseBody
    public List<LogisticsTemplate> queryAllLogisticsTemplates(HttpServletRequest request) {
        return logisticsTemplateService.queryAllTemplates(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 设置默认运费模版
     *
     * @param id 模版id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/store_setdefaulttemplate")
    @ResponseBody
    public int setDefaultTemplate(long id, HttpServletRequest request) {
        return logisticsTemplateService.setDefaultTemplate(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }


    /**
     * 新增物流模版
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/store_addlogisticstemplate")
    @ResponseBody
    public int addLogisticsTemplate(@RequestBody LogisticsTemplate logisticsTemplate, HttpServletRequest request) {
        logisticsTemplate.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return logisticsTemplateService.addLogisticsTemplate(logisticsTemplate);
    }

    /**
     * 根据物流模版id查询物流模版信息(包含运费方式和运费方式区域)
     *
     * @param id 运费模版id
     * @return 返回物流模版
     */
    @RequestMapping("/store_querylogisticstemplatebyid")
    @ResponseBody
    public LogisticsTemplate queryLogisticsTemplateById(long id, HttpServletRequest request) {
        return logisticsTemplateService.queryLogisticsTemplate(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 根据id删除运费模版 包含运费方式和运费方式区域
     *
     * @param id 运费模版id
     * @return 成功返回1 失败返回0  是默认模版不能删除返回-1
     */
    @RequestMapping("/store_deletelogisticstemplatebyid")
    @ResponseBody
    public int deleteLogisticsTemplateById(long id, HttpServletRequest request) {
        return logisticsTemplateService.deleteLogisticsTemplate(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 跳转到修改物流模版页面
     *
     * @param id 物流模版id
     * @return 返回物流模版页面
     */
    @RequestMapping("/store_toupdatelogisticstemplate")
    public ModelAndView toUpdateLogisticsTemplate(long id) {
        return new ModelAndView("storemanage/updatelogistictemplte").addObject("id", id);
    }

    /**
     * 更新物流模版
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/store_updatelogisticstemplate")
    @ResponseBody
    public int updateLogisticsTemplate(@RequestBody LogisticsTemplate logisticsTemplate, HttpServletRequest request) {
        logisticsTemplate.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return logisticsTemplateService.updateLogisticsTemplate(logisticsTemplate);
    }
}
