package com.lecshop.site;

import com.lecshop.helpcenter.bean.ServiceSupport;
import com.lecshop.helpcenter.service.ServiceSupportService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 服务支持控制器
 *
 * Created by LecShop on 2017/6/1.
 */
@Controller
public class ServiceSupportController {

    /**
     * 自动注入服务支持业务层接口
     */
    @Autowired
    private ServiceSupportService serviceSupportService;

    /**
     * 跳转到服务支持页面
     *
     * @return 服务支持页面
     */
    @RequestMapping("/toservicesupport")
    public ModelAndView toHelpSupport() {
        return new ModelAndView("site/servicesupport");
    }

    /**
     * 分页查询服务支持
     *
     * @param pageHelper 分页帮助类
     * @param name       服务支持名称
     * @return           返回服务支持信息
     */
    @RequestMapping("/queryservicesupport")
    @ResponseBody
    public BaseResponse queryServiceSupport(PageHelper<ServiceSupport> pageHelper, String name) {
        return BaseResponse.build(serviceSupportService.queryServiceSupport(pageHelper, name));
    }


    /**
     * 添加服务支持
     *
     * @param serviceSupport 服务支持
     * @return               成功返回1，失败返回0
     */
    @RequestMapping("/addservicesupport")
    @ResponseBody
    public int addServiceSupport(@RequestBody ServiceSupport serviceSupport) {
        return serviceSupportService.addServiceSupport(serviceSupport);
    }

    /**
     * 删除服务支持
     *
     * @param id 服务支持id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/deleteservicesupport")
    @ResponseBody
    public int deleteServiceSupport(long id) {
        return serviceSupportService.deleteServiceSupport(id);
    }

    /**
     * 批量删除服务支持
     *
     * @param ids 服务支持id数组
     * @return    成功返回1，失败返回0
     */
    @RequestMapping("/batchdeleteservicesupport")
    @ResponseBody
    public int batchDeleteServiceSupport(long [] ids) {
        return serviceSupportService.batchDeleteServiceSupport(ids);
    }

    /**
     * 通过id查询服务支持
     *
     * @param id 服务支持id
     * @return   返回服务支持
     */
    @RequestMapping("/queryservicesupportbyid")
    @ResponseBody
    public ServiceSupport queryServiceSupport(long id) {
        return serviceSupportService.queryServiceSupportById(id);
    }

    /**
     * 修改服务支持
     *
     * @param serviceSupport 服务支持
     * @return               成功返回1，失败返回0
     */
    @RequestMapping("/updateservicesupport")
    @ResponseBody
    public int updateServiceSupport(@RequestBody ServiceSupport serviceSupport) {
        return serviceSupportService.updateServiceSupport(serviceSupport);
    }

    /**
     * 查询所有服务支持
     *
     * @return 返回所有服务支持
     */
    @RequestMapping("/queryallservicesupport")
    @ResponseBody
    public List<ServiceSupport> queryAllServiceSupport() {
        return serviceSupportService.queryAllServiceSupport();
    }

}
