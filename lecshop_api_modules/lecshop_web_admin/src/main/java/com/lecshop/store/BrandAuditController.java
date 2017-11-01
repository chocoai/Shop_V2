package com.lecshop.store;

import com.lecshop.brand.bean.BrandApply;
import com.lecshop.brand.service.BrandApplyService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 品牌审核控制器
 *
 * Created by LecShop on 2017/6/20.
 */
@Controller
public class BrandAuditController {

    /**
     * 自动注入品牌申请service
     */
    @Autowired
    private BrandApplyService brandApplyService;

    /**
     * 跳转至品牌审核页面
     *
     * @return 品牌审核页面
     */
    @RequestMapping("/tobrandaudit")
    public ModelAndView toBrandAudit() {
        return new ModelAndView("store/brandaudit");
    }

    /**
     * 分页查询待审核品牌
     *
     * @param pageHelper 分页帮助类
     * @param name 品牌名称
     * @param storeName 店铺名称
     * @return 返回品牌信息
     */
    @RequestMapping("/querybrandtobeaudit")
    @ResponseBody
    public BaseResponse queryBrandToBeAudit(PageHelper<BrandApply> pageHelper, String name, String storeName) {
        return BaseResponse.build(brandApplyService.queryBrandToBeAudit(pageHelper, name, storeName));
    }

    /**
     * 通过品牌审核
     *
     * @param id 品牌审核id
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/passbrandaudit")
    @ResponseBody
    public int passBrandAudit(long id) {
        return brandApplyService.passBrandAudit(id);
    }

    /**
     * 批量通过品牌审核
     *
     * @param ids 品牌审核id数组
     * @return    成功返回>=1，失败返回0
     */
    @RequestMapping("/batchpassbrandaudit")
    @ResponseBody
    public int batchPassBrandAudit(long[] ids) {
        return brandApplyService.batchPassBrandAudit(ids);
    }

    /**
     * 拒绝品牌审核
     *
     * @param brandApply 商品审核实例
     * @return           成功返回1，失败返回0
     */
    @RequestMapping("/refusebrandaudit")
    @ResponseBody
    public int refuseBrandAudit(@RequestBody BrandApply brandApply) {
        return brandApplyService.refuseBrandAudit(brandApply);
    }

    /**
     * 批量拒绝品牌审核
     *
     * @param ids 品牌审核id数组
     * @param reason 拒绝原因
     * @return  成功返回>=1，失败返回0
     */
    @RequestMapping("/batchrefusebrandaudit")
    @ResponseBody
    public int batchRefuseBrandAudit(long[] ids, String reason) {
        return brandApplyService.batchRefuseBrandAudit(ids, reason);
    }
}
