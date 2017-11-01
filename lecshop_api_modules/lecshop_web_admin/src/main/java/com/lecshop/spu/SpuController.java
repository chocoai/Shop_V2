package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.brand.service.BrandService;
import com.lecshop.spu.bean.Spu;
import com.lecshop.spu.bean.SpuSearchCondition;
import com.lecshop.spu.service.SpuService;
import com.lecshop.spuimport.service.SpuImportService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/12.
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

    /**
     * 注入商品导入服务接口
     */
    @Autowired
    private SpuImportService spuImportService;


    @RequestMapping("/toaddspu")
    public ModelAndView toAddSpu(Long importId) {
        return new ModelAndView("spu/addspu").addObject("spuImport", spuImportService.querySpuImprotById(importId));
    }


    /**
     * 新增商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addspu")
    @ResponseBody
    public int addSpu(@RequestBody Spu spu, HttpServletRequest request) {
        return spuService.addSpu(spu.setDefaultValuesForAdd(AdminLoginUtils.getInstance().getManagerNameFromSession(request), CommonConstant.ADMIN_STOREID));
    }

    /**
     * 根据商品id查询商品信息
     *
     * @param id 商品id
     * @return 返回商品详细信息(包含 商品服务支持, 商品图片, 商品属性值, 商品规格值, 商品单品......)
     */
    @RequestMapping("/queryspubyid")
    @ResponseBody
    public Spu querySpuById(long id) {
        return spuService.querySpu(id, CommonConstant.QUERY_WITH_NO_STORE);
    }


    /**
     * 分页查询商品信息
     *
     * @param pageHelper         分页帮助类
     * @param spuSearchCondition 搜索请求
     * @return 返回商品信息
     */
    @RequestMapping("/queryspus")
    @ResponseBody
    public BaseResponse querySpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {
        spuSearchCondition.setStoreId(CommonConstant.ADMIN_STOREID);
        return BaseResponse.build(spuService.querySpus(pageHelper, spuSearchCondition));
    }


    /**
     * 跳转到商品列表页
     *
     * @return 返回商品列表表
     */
    @RequestMapping("/toqueryspus")
    public ModelAndView toQuerySpus() {
        return new ModelAndView("spu/spulist").addObject("brands", brandService.queryAllBrands(CommonConstant.ADMIN_STOREID));
    }

    /**
     * 删除商品信息
     *
     * @param spuId 商品id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/deletespu")
    @ResponseBody
    public int deleteSpu(long spuId, HttpServletRequest request) {
        return spuService.deleteSpu(Spu.buildForDelete(AdminLoginUtils.getInstance().getManagerNameFromSession(request), spuId, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 商品id集合
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/deletespus")
    @ResponseBody
    public int deleteSpus(Long[] ids, HttpServletRequest request) {
        return spuService.deleteSpus(Arrays.stream(ids).map(id -> Spu.buildForDelete(AdminLoginUtils.getInstance().getManagerNameFromSession(request), id, CommonConstant.ADMIN_STOREID)).collect(Collectors.toList()));
    }

    /**
     * 批量修改单品的上下架状态
     *
     * @param ids    商品id
     * @param status 上下架状态
     * @return 成功返回>1 失败返回0
     */
    @RequestMapping("/updateshelvesstatus")
    @ResponseBody
    public int updateShelvesStatus(Long[] ids, String status) {
        return spuService.updateShelvesStatus(Arrays.asList(ids), status, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 跳转到修改商品页面
     *
     * @return 返回修改商品页面
     */
    @RequestMapping("/toupdatespu")
    public ModelAndView toUpdateSpu(long id) {
        return new ModelAndView("spu/updatespu").addObject("spuId", id);
    }


    /**
     * 更新商品信息
     *
     * @param spu 商品信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatespu")
    @ResponseBody
    public int updateSpu(@RequestBody Spu spu) {
        spu.setStoreId(CommonConstant.ADMIN_STOREID);
        return spuService.updateSpu(spu);
    }


    /**
     * 查询所有店铺的商品信息 分页信息
     *
     * @param spuSearchCondition 搜索条件
     * @return 返回所有店铺的商品信息
     */
    @RequestMapping("/queryallstorespus")
    @ResponseBody
    public BaseResponse queryAllStoreSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {
        return BaseResponse.build(spuService.queryAllStoreSpus(pageHelper, spuSearchCondition));
    }

    /**
     * 跳转到店铺商品列表
     *
     * @return 返回店铺商品列表
     */
    @RequestMapping("/toqueryallstorespus")
    public ModelAndView toQueryAllStoreSpus() {
        return new ModelAndView("store/storespulist");
    }


    /**
     * 跳转到商品详情页
     *
     * @return 返回商品详情页
     */
    @RequestMapping("/tospudetail")
    public ModelAndView toSpuDetail(long id) {
        return new ModelAndView("store/storespudetail").addObject("spuId", id);
    }


}
