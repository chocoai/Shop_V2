package com.lecshop.order;

import com.lecshop.logistics.bean.LogisticsCompany;
import com.lecshop.logistics.service.LogisticsCompanyService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dujinkai on 17/5/15.
 * 物流公司控制器
 */
@Controller
public class LogisticsCompanyController {


    /**
     * 注入物流公司服务接口
     */
    @Autowired
    private LogisticsCompanyService logisticsCompanyService;

    /**
     * 跳转到物流公司页面
     *
     * @return 返回物流公司页面
     */
    @RequestMapping("/tologisticscompany")
    public ModelAndView toLogisticsCompany() {
        return new ModelAndView("order/logisticscompany");
    }

    /**
     * 新增物流公司
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/addlogisticscompany")
    @ResponseBody
    public int addLogisticsCompany(@RequestBody LogisticsCompany logisticsCompany) {
        return logisticsCompanyService.addLogisticsCompany(logisticsCompany.setDefaultValuesForAdd(CommonConstant.ADMIN_STOREID));
    }

    /**
     * 分页查询物流公司
     *
     * @param pageHelper 分页帮助类
     * @param name       物流公司名称
     * @return 返回物流信息
     */
    @RequestMapping("/querylogisticscompanys")
    @ResponseBody
    public BaseResponse queryLogisticsCompanys(PageHelper<LogisticsCompany> pageHelper, String name) {
        return BaseResponse.build(logisticsCompanyService.queryLogisticsCompanys(pageHelper, name, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 删除物流公司
     *
     * @param id 物流公司id
     * @return 成功返回 1 失败返回0 -1 物流公司被关联不能删除
     */
    @RequestMapping("/deletelogisticscompany")
    @ResponseBody
    public int deleteLogisticsCompany(int id) {
        return logisticsCompanyService.deleteLogisticsCompany(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 批量删除物流公司
     *
     * @param ids 物流公司ids集合
     * @return 成功返回  1 失败返回0
     */
    @RequestMapping("/deletelogisticscompanys")
    @ResponseBody
    public int deleteLogisticsCompanys(Long[] ids) {
        return logisticsCompanyService.deleteLogisticsCompanys(ids, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 根据id查询物流公司信息
     *
     * @param id 物流id
     * @return 返回物流信息
     */
    @RequestMapping("/querylogisticscompanybyid")
    @ResponseBody
    public LogisticsCompany queryLogisticsCompanyById(long id) {
        return logisticsCompanyService.queryLogisticsCompanyById(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 更新物流公司信息
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/updatelogisticscompany")
    @ResponseBody
    public int updateLogisticsCompany(@RequestBody LogisticsCompany logisticsCompany) {
        return logisticsCompanyService.updateLogisticsCompany(logisticsCompany.setDefaultValuesForUpdate(CommonConstant.ADMIN_STOREID));
    }

    /**
     * 查询没有被物流模版关联的物流公司
     *
     * @return 返回可用的物流公司
     */
    @RequestMapping("/querycanusecompany")
    @ResponseBody
    public List<LogisticsCompany> queryCanUseCompany() {
        return logisticsCompanyService.queryCanUseCompany(CommonConstant.ADMIN_STOREID);
    }
}
