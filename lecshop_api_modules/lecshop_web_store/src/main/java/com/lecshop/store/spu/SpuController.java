package com.lecshop.store.spu;

import com.lecshop.brand.service.BrandService;
import com.lecshop.spu.bean.Spu;
import com.lecshop.spu.bean.SpuSearchCondition;
import com.lecshop.spu.service.SpuService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/6/14.
 * 商品控制器
 */
@Controller
public class SpuController {


    /**
     * 注入商品服务接口
     */
    @Autowired
    private SpuService spuService;

    /**
     * 注入品牌服务接口
     */
    @Autowired
    private BrandService brandService;


    @RequestMapping("/store_toaddspu")
    public ModelAndView toAddSpu() {
        return new ModelAndView("spu/addspu");
    }


    /**
     * 新增商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_addspu")
    @ResponseBody
    public int addSpu(@RequestBody Spu spu, HttpServletRequest request) {
        return spuService.addSpu(spu.setDefaultValuesForAdd(StoreLoginUtils.getInstance().getCustomerNameFromSession(request), StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品详细信息(包含 商品服务支持, 商品图片, 商品属性值, 商品规格值, 商品单品......)
     */
    @RequestMapping("/store_queryspubyid")
    @ResponseBody
    public Spu querySpuById(long id, HttpServletRequest request) {
        return spuService.querySpu(id, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }


    /**
     * 分页查询商品信息
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索请求
     * @return 返回商品信息
     */
    @RequestMapping("/store_queryspus")
    @ResponseBody
    public BaseResponse querySpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition, HttpServletRequest request) {
        spuSearchCondition.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return BaseResponse.build(spuService.querySpus(pageHelper, spuSearchCondition));
    }

    /**
     * 分页查询商品信息(关联查询单品)
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索条件
     * @return 返回商品信息
     */
    @RequestMapping("/store_queryspuswithsku")
    @ResponseBody
    public BaseResponse querySpusWithSku(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition, HttpServletRequest request) {
        spuSearchCondition.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return BaseResponse.build(spuService.querySpusWithSku(pageHelper, spuSearchCondition));
    }

    /**
     * 跳转到待审核商品列表页面
     *
     * @return 跳转到待审核商品列表页面
     */
    @RequestMapping("/store_toquerytobeauditspus")
    public ModelAndView toQueryToBeAuditSpus(HttpServletRequest request) {
        return new ModelAndView("spu/toauditspus").addObject("brands", brandService.queryAllBrands(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }


    /**
     * 跳转到审核通过商品列表页面
     *
     * @return 跳转到审核通过商品列表页面
     */
    @RequestMapping("/store_toqueryauditpass")
    public ModelAndView toQueryAuditPass(HttpServletRequest request) {
        return new ModelAndView("spu/auditedspus").addObject("brands", brandService.queryAllBrands(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }


    /**
     * 跳转到拒绝审核商品列表页面
     *
     * @return 跳转到拒绝审核商品列表页面
     */
    @RequestMapping("/store_toqueryrefusespus")
    public ModelAndView toQueryRefuseSpus(HttpServletRequest request) {
        return new ModelAndView("spu/refusespus").addObject("brands", brandService.queryAllBrands(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 跳转到商品列表页
     *
     * @return 返回商品列表表
     */
    @RequestMapping("/store_toqueryspus")
    public ModelAndView toQuerySpus(HttpServletRequest request) {
        return new ModelAndView("spu/spulist").addObject("brands", brandService.queryAllBrands(StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 删除商品信息
     *
     * @param spuId 商品id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_deletespu")
    @ResponseBody
    public int deleteSpu(long spuId, HttpServletRequest request) {
        return spuService.deleteSpu(Spu.buildForDelete(StoreLoginUtils.getInstance().getCustomerNameFromSession(request), spuId, StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 商品id集合
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/store_deletespus")
    @ResponseBody
    public int deleteSpus(Long[] ids, HttpServletRequest request) {
        return spuService.deleteSpus(Arrays.stream(ids).map(id -> Spu.buildForDelete(StoreLoginUtils.getInstance().getCustomerNameFromSession(request), id, StoreLoginUtils.getInstance().getStoreIdFromSession(request))).collect(Collectors.toList()));
    }

    /**
     * 批量修改单品的上下架状态
     *
     * @param ids    商品id
     * @param status 上下架状态
     * @return 成功返回>1 失败返回0
     */
    @RequestMapping("/store_updateshelvesstatus")
    @ResponseBody
    public int updateShelvesStatus(Long[] ids, String status, HttpServletRequest request) {
        return spuService.updateShelvesStatus(Arrays.asList(ids), status, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 跳转到修改商品页面
     *
     * @return 返回修改商品页面
     */
    @RequestMapping("/store_toupdatespu")
    public ModelAndView toUpdateSpu(long id) {
        return new ModelAndView("spu/updatespu").addObject("spuId", id);
    }


    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_updatespu")
    @ResponseBody
    public int updateSpu(@RequestBody Spu spu, HttpServletRequest request) {
        spu.setStoreId(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return spuService.updateSpu(spu);
    }
}
