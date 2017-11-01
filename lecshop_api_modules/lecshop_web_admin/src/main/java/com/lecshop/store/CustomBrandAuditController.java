package com.lecshop.store;

import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.service.BrandService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义品牌审核控制器
 *
 * Created by LecShop on 2017/6/22.
 */
@Controller
public class CustomBrandAuditController {

    /**
     * 自动注入品牌service
     */
    @Autowired
    private BrandService brandService;

    /**
     * 跳转至自定义品牌审核页面
     *
     * @return 自定义品牌审核页面
     */
    @RequestMapping("/tocustombrandaudit")
    public ModelAndView toMySelfBrandAudit() {
        return new ModelAndView("store/custombrandaudit");
    }

    /**
     * 分页查询自定义品牌
     *
     * @param pageHelper 分页帮助类
     * @param brandName  品牌名称
     * @param storeName  店铺名称
     * @return 自定义品牌
     */
    @RequestMapping("/querycustombrands")
    @ResponseBody
    public BaseResponse queryMySelfBrands(PageHelper<Brand> pageHelper, String brandName, String storeName) {
        return BaseResponse.build(brandService.queryCustomBrandByStatus(pageHelper, brandName, storeName));
    }

    /**
     * 通过自定义品牌审核
     *
     * @param id 自定义品牌id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/passcustombrandaudit")
    @ResponseBody
    public int passMySelfBrandAudit(long id) {
        return brandService.passCustomBrandAudit(id);
    }

    /**
     * 批量通过自定义品牌审核
     *
     * @param ids 自定义品牌id数组
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/batchpasscustombrandaudit")
    @ResponseBody
    public int batchPassMySelfBrandAudit(long[] ids) {
        return brandService.batchPassCustomBrandAudit(ids);
    }

    /**
     * 拒绝自定义品牌审核
     *
     * @param brand 自定义品牌id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/refusecustombrandaudit")
    @ResponseBody
    public int refuseMySelfBrandAudit(@RequestBody Brand brand) {
        return brandService.refuseCustomBrandAudit(brand);
    }

    /**
     * 批量拒绝自定义品牌审核
     *
     * @param ids    自定义品牌id数组
     * @param reason 拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/batchrefusecustombrandaudit")
    @ResponseBody
    public int batchRefuseMySelfBrandAudit(long[] ids, String reason) {
        return brandService.batchRefuseCustomBrandAudit(ids, reason);
    }

}
