package com.lecshop.store;

import com.lecshop.sku.service.SkuService;
import com.lecshop.spu.bean.Spu;
import com.lecshop.spu.bean.SpuSearchCondition;
import com.lecshop.spu.service.SpuService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/6/15.
 * 商品审核控制器
 */
@Controller
public class AuditSpuController {

    /**
     * 注入商品服务接口
     */
    @Autowired
    private SpuService spuService;

    /**
     * 注入单品服务接口
     */
    @Autowired
    private SkuService skuService;

    /**
     * 查询所有店铺待审核的商品(商品下只要有一个单品待审核 则就查出商品信息)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索条件
     * @return 返回待审核的商品信息
     */
    @RequestMapping("/queryallstoretobeauditdspus")
    @ResponseBody
    public BaseResponse queryAllStoreToBeAuditdSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {
        return BaseResponse.build(spuService.queryAllStoreToBeAuditdSpus(pageHelper, spuSearchCondition));
    }


    /**
     * 跳转到店铺商品审核列表
     *
     * @return 返回店铺商品审核列表
     */
    @RequestMapping("/toauditspus")
    public ModelAndView toAuditSpus() {
        return new ModelAndView("store/auditspulist");
    }

    /**
     * 跳转到商品审核页面
     *
     * @return 返回商品审核页面
     */
    @RequestMapping("/toauditspu")
    public ModelAndView toAuditSpu(long id) {
        return new ModelAndView("store/auditspu").addObject("spuId", id);
    }

    /**
     * 审核通过
     *
     * @param skuIds 单品id集合
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/auditpass")
    @ResponseBody
    public int auditPass(String[] skuIds) {
        return skuService.auditPass(skuIds);
    }

    /**
     * 拒绝审核
     *
     * @param skuIds 单品id集合
     * @param reason 原因
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/auditrefuse")
    @ResponseBody
    public int auditRefuse(String[] skuIds, String reason) {
        return skuService.auditRefuse(skuIds, reason);
    }
}
