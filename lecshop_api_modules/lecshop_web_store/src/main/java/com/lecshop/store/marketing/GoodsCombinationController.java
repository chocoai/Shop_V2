package com.lecshop.store.marketing;

import com.lecshop.marketingmanager.bean.CombinationSku;
import com.lecshop.marketingmanager.bean.GoodsCombination;
import com.lecshop.marketingmanager.service.CombinationSkuService;
import com.lecshop.marketingmanager.service.GoodsCombinationService;
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

/**
 * 商品组合列表控制器
 *
 * Created by LecShop on 2017/6/19.
 */
@Controller
public class GoodsCombinationController {

    /**
     * 自动注入商品组合service
     */
    @Autowired
    private GoodsCombinationService goodsCombinationService;

    /**
     * 自动注入商品组合与单品的关联service
     */
    @Autowired
    private CombinationSkuService combinationSkuService;

    /**
     * 跳转到商品组合页面
     *
     * @return 商品组合页面
     */
    @RequestMapping("/store_togoodscombination")
    public ModelAndView toGoodsCombination() {
        return new ModelAndView("marketing/goodscombination");
    }

    /**
     * 跳转到管理商品组合页面
     *
     * @return 管理商品组合页面
     */
    @RequestMapping("/store_tomanagegoodscombination")
    public ModelAndView toManageGoodsCombination(long id) {
        return new ModelAndView("marketing/managegoodscombination").addObject("id", id);
    }

    /**
     * 查询商品组合
     *
     * @param pageHelper 分页帮助类
     * @param name       商品组合名称
     * @return           返回商品组合信息
     */
    @RequestMapping("/store_querygoodscombination")
    @ResponseBody
    public BaseResponse queryGoodsCombination(HttpServletRequest request, PageHelper<GoodsCombination> pageHelper, String name) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return BaseResponse.build(goodsCombinationService.queryGoodsCombination(pageHelper, name, storeId));
    }

    /**
     * 查找商品组合下的商品
     *
     * @param pageHelper 分页帮助类
     * @param id         商品组合id
     * @return           商品组合下的商品
     */
    @RequestMapping("/store_queryskuofgoodscombination")
    @ResponseBody
    public BaseResponse queryGoodsOfGoodsCombination(HttpServletRequest request, PageHelper<CombinationSku> pageHelper, Long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return BaseResponse.build(combinationSkuService.querySkuOfGoodCom(pageHelper, id, storeId));
    }

    /**
     * 添加商品组合
     *
     * @param goodsCombination 商品组合
     * @return                 成功返回1，失败返回0
     */
    @RequestMapping("/store_addgoodscombination")
    @ResponseBody
    public int addGoodsCombination(HttpServletRequest request, @RequestBody GoodsCombination goodsCombination) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        goodsCombination.setStoreId(storeId);
        return goodsCombinationService.addGoodsCombination(goodsCombination);
    }

    /**
     * 删除商品组合
     *
     * @param id 商品组合id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/store_deletegoodscombination")
    @ResponseBody
    public int deleteGoodsCombination(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return goodsCombinationService.deleteGoodsCombination(id, storeId);
    }

    /**
     * 批量删除商品组合
     *
     * @param ids 商品组合id数组
     * @return    成功返回>=1，失败返回0
     */
    @RequestMapping("/store_batchdeletegoodscombination")
    @ResponseBody
    public int batchDeleteGoodsCombination(HttpServletRequest request, long[] ids) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return goodsCombinationService.batchDeleteGoodsCombination(ids, storeId);
    }

    /**
     * 通过id查找商品组合
     *
     * @param id 商品组合id
     * @return   商品组合
     */
    @RequestMapping("/store_querygoodscombinationbyid")
    @ResponseBody
    public GoodsCombination queryGoodsCombinationById(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return goodsCombinationService.queryGoodsCombinationById(id, storeId);
    }

    /**
     * 修改商品组合
     *
     * @param goodsCombination 商品组合
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/store_updategoodscombination")
    @ResponseBody
    public int updateGoodsCombination(HttpServletRequest request, @RequestBody GoodsCombination goodsCombination) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        goodsCombination.setStoreId(storeId);
        return goodsCombinationService.updateGoodsCombination(goodsCombination);
    }

    /**
     * 为商品组合添加单品
     *
     * @param combinationSku 商品组合下的单品
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/store_addcombinationsku")
    @ResponseBody
    public int addCombinationSku(@RequestBody CombinationSku combinationSku) {
        return combinationSkuService.addCombinationSku(combinationSku);
    }

    /**
     * 删除商品组合下的单品
     *
     * @param id 单品id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/store_deletecombinationsku")
    @ResponseBody
    public int deleteCombinationSku(String id) {
        return combinationSkuService.deleteCombinationSku(id);
    }

    /**
     * 批量删除商品组合下的单品
     *
     * @param ids 单品id数组
     * @return    成功返回>=1，失败返回0
     */
    @RequestMapping("/store_batchdeletecombinationsku")
    @ResponseBody
    public int batchDeleteCombinationSku(String[] ids) {
        return combinationSkuService.batchDeleteCombiantionSKu(ids);
    }

}
