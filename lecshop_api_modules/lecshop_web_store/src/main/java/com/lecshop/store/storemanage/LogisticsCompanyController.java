package com.lecshop.store.storemanage;

import com.lecshop.logistics.bean.LogisticsCompany;
import com.lecshop.logistics.service.LogisticsCompanyService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dujinkai on 17/6/13.
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
    @RequestMapping("/store_tologisticscompany")
    public ModelAndView toLogisticsCompany() {
        return new ModelAndView("storemanage/logisticscompany");
    }

    /**
     * 新增物流公司
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/store_addlogisticscompany")
    @ResponseBody
    public int addLogisticsCompany(@RequestBody LogisticsCompany logisticsCompany, HttpServletRequest request) {
        return logisticsCompanyService.addLogisticsCompany(logisticsCompany.setDefaultValuesForAdd(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 分页查询物流公司
     *
     * @param pageHelper 分页帮助类
     * @param name       物流公司名称
     * @return 返回物流信息
     */
    @RequestMapping("/store_querylogisticscompanys")
    @ResponseBody
    public BaseResponse queryLogisticsCompanys(PageHelper<LogisticsCompany> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(logisticsCompanyService.queryLogisticsCompanys(pageHelper, name, StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 删除物流公司
     *
     * @param id 物流公司id
     * @return 成功返回 1 失败返回0 -1 物流公司被关联不能删除
     */
    @RequestMapping("/store_deletelogisticscompany")
    @ResponseBody
    public int deleteLogisticsCompany(int id, HttpServletRequest request) {
        return logisticsCompanyService.deleteLogisticsCompany(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 批量删除物流公司
     *
     * @param ids 物流公司ids集合
     * @return 成功返回  1 失败返回0
     */
    @RequestMapping("/store_deletelogisticscompanys")
    @ResponseBody
    public int deleteLogisticsCompanys(Long[] ids, HttpServletRequest request) {
        return logisticsCompanyService.deleteLogisticsCompanys(ids, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 根据id查询物流公司信息
     *
     * @param id 物流id
     * @return 返回物流信息
     */
    @RequestMapping("/store_querylogisticscompanybyid")
    @ResponseBody
    public LogisticsCompany queryLogisticsCompanyById(long id, HttpServletRequest request) {
        return logisticsCompanyService.queryLogisticsCompanyById(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 更新物流公司信息
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/store_updatelogisticscompany")
    @ResponseBody
    public int updateLogisticsCompany(@RequestBody LogisticsCompany logisticsCompany, HttpServletRequest request) {
        return logisticsCompanyService.updateLogisticsCompany(logisticsCompany.setDefaultValuesForUpdate(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 查询没有被物流模版关联的物流公司
     *
     * @return 返回可用的物流公司
     */
    @RequestMapping("/store_querycanusecompany")
    @ResponseBody
    public List<LogisticsCompany> queryCanUseCompany(HttpServletRequest request) {
        return logisticsCompanyService.queryCanUseCompany(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }
}
