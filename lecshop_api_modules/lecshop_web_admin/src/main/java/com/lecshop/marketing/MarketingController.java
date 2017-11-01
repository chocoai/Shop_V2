package com.lecshop.marketing;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.marketing.service.FreeShipService;
import com.lecshop.marketing.service.MarketingQueryService;
import com.lecshop.marketing.service.MarketingService;
import com.lecshop.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * Created by dujinkai on 17/6/7.
 * 促销控制器
 */
@Controller
public class MarketingController {

    /**
     * 注入团购服务接口
     */
    @Resource(name = "groupBuyService")
    private MarketingService groupBuyService;

    /**
     * 注入抢购服务接口
     */
    @Resource(name = "panicBuyService")
    private MarketingService panicBuyService;

    /**
     * 注入直降服务接口
     */
    @Resource(name = "fallService")
    private MarketingService fallService;

    /**
     * 注入满减促销服务接口
     */
    @Resource(name = "fullDownService")
    private MarketingService fullDownService;

    /**
     * 注入满折促销服务接口
     */
    @Resource(name = "fullDiscountService")
    private MarketingService fullDiscountService;

    /**
     * 注入包邮服务接口
     */
    @Autowired
    private FreeShipService freeShipService;

    /**
     * 注入促销查询接口
     */
    @Autowired
    private MarketingQueryService marketingQueryService;


    /**
     * 跳转到促销查询页面
     *
     * @return 返回促销查询页面
     */
    @RequestMapping("/toquerymarketing")
    public ModelAndView toQueryMarketing() {
        return new ModelAndView("marketing/marketlist");
    }

    /**
     * 分页查询促销信息
     *
     * @param pageHelper 分页帮助类
     * @param name       名称
     * @param type       类型
     * @return 返回促销信息
     */
    @RequestMapping("/querymarketings")
    @ResponseBody
    public BaseResponse queryMarketings(PageHelper<Marketing> pageHelper, String name, String type) {
        return BaseResponse.build(marketingQueryService.queryMarketings(pageHelper, name, type, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 跳转到添加团购页面
     *
     * @return 返回添加团购页面
     */
    @RequestMapping("/toaddgroupmakerting")
    public ModelAndView toAddGroupMakerting() {
        return new ModelAndView("marketing/creategroupbuy");
    }

    /**
     * 跳转到修改团购页面
     *
     * @param id 促销id
     * @return 返回团购页面
     */
    @RequestMapping("/toupdategroupmarketing")
    public ModelAndView toUpdateGroupMarketing(long id) {
        return new ModelAndView("marketing/updategroupbuy").addObject("id", id);
    }

    /**
     * 新增团购促销
     *
     * @param marketing 团购促销
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addgroupmarketing")
    @ResponseBody
    public int addGroupMarketing(@RequestBody Marketing marketing) {
        return groupBuyService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.GROUP_BUY_MARKETING));
    }

    /**
     * 更新团购信息
     *
     * @param marketing 团购促销
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updategroupmarketing")
    @ResponseBody
    public int updateGroupMarketing(@RequestBody Marketing marketing) {
        return groupBuyService.updateMarketing(marketing.setUpdateMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.GROUP_BUY_MARKETING));
    }

    /**
     * 跳转到添加抢购页面
     *
     * @return 返回到添加抢购页面
     */
    @RequestMapping("/toaddpanicmarketing")
    public ModelAndView toAddPanicMarketing() {
        return new ModelAndView("marketing/createpanicbuy");
    }

    /**
     * 添加抢购促销
     *
     * @param marketing 抢购促销
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/addpanicmarketing")
    @ResponseBody
    public int addPanicMarketing(@RequestBody Marketing marketing) {
        return panicBuyService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.PANIC_BUY_MARKETING));
    }

    /**
     * 跳转到修改抢购页面
     *
     * @param id 促销id
     * @return 返回修改抢购页面
     */
    @RequestMapping("/toupdatepanicmarketing")
    public ModelAndView toUpdatePanicMarketing(long id) {
        return new ModelAndView("marketing/updatepanicbuy").addObject("id", id);
    }

    /**
     * 更新抢购
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatepanicmarketing")
    @ResponseBody
    public int updatePanicMarketing(@RequestBody Marketing marketing) {
        return panicBuyService.updateMarketing(marketing.setUpdateMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.PANIC_BUY_MARKETING));
    }

    /**
     * 跳转到添加直降促销页面
     *
     * @return 返回添加直降促销页面
     */
    @RequestMapping("/toaddfallmarketing")
    public ModelAndView toAddFallMarketing() {
        return new ModelAndView("marketing/createfall");
    }

    /**
     * 添加直降促销
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addfallmarketing")
    @ResponseBody
    public int addFallMarketing(@RequestBody Marketing marketing) {
        return fallService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FALL_MARKETING));
    }

    /**
     * 跳转到修改直降页面
     *
     * @param id 促销id
     * @return 返回修改直降促销页面
     */
    @RequestMapping("/toupdatefallmarketing")
    public ModelAndView toUpdateFallMarketing(long id) {
        return new ModelAndView("marketing/updatefall").addObject("id", id);
    }

    /**
     * 修改直降促销
     *
     * @param marketing 直降促销
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatefallmarketing")
    @ResponseBody
    public int updateFallMarketing(@RequestBody Marketing marketing) {
        return fallService.updateMarketing(marketing.setUpdateMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FALL_MARKETING));
    }

    /**
     * 跳转到添加满减促销页
     *
     * @return 返回满减促销页
     */
    @RequestMapping("/toaddfulldownmarketing")
    public ModelAndView toAddFullDownMarketing() {
        return new ModelAndView("marketing/createfulldown");
    }

    /**
     * 添加满减促销
     *
     * @param marketing 满减促销
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addfulldownmarketing")
    @ResponseBody
    public int addFullDownMarketing(@RequestBody Marketing marketing) {
        return fullDownService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FULL_DOWN_MARKETING));
    }

    /**
     * 跳转到修改满减页面
     *
     * @param id 促销id
     * @return 返回满减促销页面
     */
    @RequestMapping("/toupdatefulldown")
    public ModelAndView toUpdateFullDown(long id) {
        return new ModelAndView("marketing/updatefulldown").addObject("id", id);
    }

    /**
     * 更新满减
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatefulldown")
    @ResponseBody
    public int updateFullDown(@RequestBody Marketing marketing) {
        return fullDownService.updateMarketing(marketing.setUpdateMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FULL_DOWN_MARKETING));
    }

    /**
     * 跳转到添加满折页面
     *
     * @return 返回添加满折页面
     */
    @RequestMapping("/toaddfulldiscount")
    public ModelAndView toAddFullDiscount() {
        return new ModelAndView("marketing/createfulldiscount");
    }

    /**
     * 添加满折促销
     *
     * @param marketing 满折促销
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addfulldiscount")
    @ResponseBody
    public int addFullDiscount(@RequestBody Marketing marketing) {
        return fullDiscountService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FULL_DISCOUNT_MARKETING));
    }

    /**
     * 跳转到修改满折页面
     *
     * @param id 促销id
     * @return 返回修改满折页面
     */
    @RequestMapping("/toupdatefulldiscount")
    public ModelAndView toUpdateFullDiscount(long id) {
        return new ModelAndView("marketing/updatefulldiscount").addObject("id", id);
    }

    /**
     * 修改满折促销
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatefulldiscount")
    @ResponseBody
    public int updateFullDiscount(@RequestBody Marketing marketing) {
        return fullDiscountService.updateMarketing(marketing.setUpdateMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FULL_DISCOUNT_MARKETING));
    }


    /**
     * 跳转到包邮页面
     *
     * @return 返回包邮页面
     */
    @RequestMapping("/toaddfreeship")
    public ModelAndView toAddFreeShip() {
        return new ModelAndView("marketing/createfreeship");
    }

    /**
     * 新增包邮
     *
     * @param marketing 包邮促销
     * @return 成功返回1 失败返回0
     */
    @UnAuth
    @RequestMapping("/addfreeship")
    @ResponseBody
    public int addFreeShip(@RequestBody Marketing marketing) {
        return freeShipService.addMarketing(marketing.setAddMarketingDefaultValues(CommonConstant.ADMIN_STOREID, MarketingConstant.FREE_SHIP_MARKETING));
    }

    /**
     * 查询包邮信息
     *
     * @return 返回包邮信息
     */
    @UnAuth
    @RequestMapping("/queryfreeship")
    @ResponseBody
    public Marketing queryFreeShip() {
        return freeShipService.queryFreeShip(CommonConstant.ADMIN_STOREID);
    }


    /**
     * 根据促销id查询促销信息
     *
     * @param id 促销id
     * @return 返回促销详情信息
     */
    @RequestMapping("/querymarketingbyid")
    @ResponseBody
    public Marketing queryMarketingById(long id) {
        return marketingQueryService.queryMarketingById(id, CommonConstant.ADMIN_STOREID);
    }
}
