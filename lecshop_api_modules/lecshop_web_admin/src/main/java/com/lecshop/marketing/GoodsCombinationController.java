package com.lecshop.marketing;

import com.lecshop.marketingmanager.bean.CombinationSku;
import com.lecshop.marketingmanager.bean.GoodsCombination;
import com.lecshop.marketingmanager.service.CombinationSkuService;
import com.lecshop.marketingmanager.service.GoodsCombinationService;
import com.lecshop.sku.bean.Sku;
import com.lecshop.sku.service.SkuService;
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
 * 商品组合列表控制器
 *
 * Created by LecShop on 2017/6/12.
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
    @RequestMapping("/togoodscombination")
    public ModelAndView toGoodsCombination() {
        return new ModelAndView("marketing/goodscombination");
    }

    /**
     * 跳转到管理商品组合页面
     *
     * @return 管理商品组合页面
     */
    @RequestMapping("/tomanagegoodscombination")
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
    @RequestMapping("/querygoodscombination")
    @ResponseBody
    public BaseResponse queryGoodsCombination(PageHelper<GoodsCombination> pageHelper, String name) {
        return BaseResponse.build(goodsCombinationService.queryGoodsCombination(pageHelper, name, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 查找商品组合下的商品
     *
     * @param pageHelper 分页帮助类
     * @param id         商品组合id
     * @return           商品组合下的商品
     */
    @RequestMapping("/queryskuofgoodscombination")
    @ResponseBody
    public BaseResponse queryGoodsOfGoodsCombination(PageHelper<CombinationSku> pageHelper, Long id) {
        return BaseResponse.build(combinationSkuService.querySkuOfGoodCom(pageHelper, id, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 添加商品组合
     *
     * @param goodsCombination 商品组合
     * @return                 成功返回1，失败返回0
     */
    @RequestMapping("/addgoodscombination")
    @ResponseBody
    public int addGoodsCombination(@RequestBody GoodsCombination goodsCombination) {
        goodsCombination.setStoreId(CommonConstant.ADMIN_STOREID);
        return goodsCombinationService.addGoodsCombination(goodsCombination);
    }

    /**
     * 删除商品组合
     *
     * @param id 商品组合id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/deletegoodscombination")
    @ResponseBody
    public int deleteGoodsCombination(long id) {
        return goodsCombinationService.deleteGoodsCombination(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 批量删除商品组合
     *
     * @param ids 商品组合id数组
     * @return    成功返回>=1，失败返回0
     */
    @RequestMapping("/batchdeletegoodscombination")
    @ResponseBody
    public int batchDeleteGoodsCombination(long[] ids) {
        return goodsCombinationService.batchDeleteGoodsCombination(ids, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 通过id查找商品组合
     *
     * @param id 商品组合id
     * @return   商品组合
     */
    @RequestMapping("/querygoodscombinationbyid")
    @ResponseBody
    public GoodsCombination queryGoodsCombinationById(long id) {
        return goodsCombinationService.queryGoodsCombinationById(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 修改商品组合
     *
     * @param goodsCombination 商品组合
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/updategoodscombination")
    @ResponseBody
    public int updateGoodsCombination(@RequestBody GoodsCombination goodsCombination) {
        goodsCombination.setStoreId(CommonConstant.ADMIN_STOREID);
        return goodsCombinationService.updateGoodsCombination(goodsCombination);
    }

    /**
     * 为商品组合添加单品
     *
     * @param combinationSku 商品组合下的单品
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/addcombinationsku")
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
    @RequestMapping("/deletecombinationsku")
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
    @RequestMapping("/batchdeletecombinationsku")
    @ResponseBody
    public int batchDeleteCombinationSku(String[] ids) {
        return combinationSkuService.batchDeleteCombiantionSKu(ids);
    }

}
