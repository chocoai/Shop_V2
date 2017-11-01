package com.lecshop.store.openstore;

import com.lecshop.baseinfo.bean.BaseInfoSet;
import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.service.BrandService;
import com.lecshop.category.bean.Category;
import com.lecshop.category.service.CategoryService;
import com.lecshop.customer.bean.Customer;
import com.lecshop.openstore.bean.StoreBusiness;
import com.lecshop.openstore.bean.StoreBusinessInfo;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.service.StoreInfoService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 开店控制器
 *
 * @author sunluyang on 2017/6/14.
 */
@Controller
public class OpenStoreController {

    /**
     * 注入开店店铺信息service
     */
    @Autowired
    private StoreInfoService storeInfoService;
    /**
     * 注入信息设置实现类
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;
    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 注入品牌service
     */
    @Autowired
    private BrandService brandService;

    /**
     * 跳转到开店协议
     *
     * @return 开店协议页面
     */
    @RequestMapping("/store_toopenprotocol")
    @UnAuth
    public ModelAndView toOpenProtocol(HttpServletRequest request) {
        return openStoreStep(request, 1);
    }

    /**
     * 跳转到填写店铺信息
     *
     * @return 店铺信息页面
     */
    @RequestMapping("/store_tofillinstoreinfo")
    @UnAuth
    public ModelAndView toFillInStoreInfo(HttpServletRequest request, String isCheck) {
        if ("true".equals(isCheck)) {
            request.getSession().setAttribute("IS_CHECK", "true");
        }
        return openStoreStep(request, 2);
    }

    /**
     * 跳转到选择经营范围页面
     *
     * @return 经营范围页面
     */
    @RequestMapping("/store_toscopeofbusiness")
    @UnAuth
    public ModelAndView toScopeOfBusiness(HttpServletRequest request) {
        return openStoreStep(request, 3);
    }

    /**
     * 跳转到提交结果页
     *
     * @return 提交结果页
     */
    @RequestMapping("/store_tosubmitresults")
    @UnAuth
    public ModelAndView toSubmitResults(HttpServletRequest request) {
        return openStoreStep(request, 4);
    }

    /**
     * 查询基本信息和高级信息设置,用于页面图标展示,不需要拦截
     *
     * @return 基本信息和高级信息设置实体类
     */
    @RequestMapping("/store_querybaseinfo")
    @ResponseBody
    @UnAuth
    public BaseInfoSet querybaseinfo() {
        return baseInfoSetService.queryBaseInfoSet();
    }

    /**
     * 查询开店填写的店铺信息
     *
     * @param request request请求
     * @return 店铺信息
     */
    @RequestMapping("/store_querystoreinfo")
    @ResponseBody
    @UnAuth
    public StoreInfo queryStoreInfo(HttpServletRequest request) {
        StoreInfo returnStoreInfo = new StoreInfo();
        returnStoreInfo.setDelFlag("1");
        StoreInfo storeInfo = storeInfoService.queryStoreInfo(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        return storeInfo == null ? returnStoreInfo : storeInfo;
    }

    /**
     * 开店-处理店铺信息
     *
     * @param storeInfo 店铺信息
     * @param request   request请求
     * @return 返回值跳转页面  0 出错 1 下一页 2店铺首页 3 拒绝通过页 4 登录页
     */
    @RequestMapping("/store_dealstoreinfo")
    @ResponseBody
    @UnAuth
    public int dealStoreInfo(@RequestBody StoreInfo storeInfo, HttpServletRequest request) {
        return storeInfoService.dealStoreInfo(storeInfo, StoreLoginUtils.getInstance().getCustomerFromSession(request), customer -> {
            // 将用户信息放入session中
            StoreLoginUtils.getInstance().putCustomerToSession(request, customer);
        });
    }

    /**
     * 开店查询店铺信息
     *
     * @param request request 请求
     * @return 店铺信息
     */
    @RequestMapping("/store_querybusinessinfo")
    @ResponseBody
    @UnAuth
    public StoreBusinessInfo queryStoreBusinessInfo(HttpServletRequest request) {
        return storeInfoService.queryStoreBusinessInfo(StoreLoginUtils.getInstance().getStoreIdFromSession(request), "0");
    }

    /**
     * 开店-处理经营范围
     *
     * @param request       request请求
     * @param storeBusiness 店铺经营实体类
     * @return 返回码
     */
    @RequestMapping("/store_businessinfo")
    @ResponseBody
    @UnAuth
    public int dealStoreBusinessInfo(HttpServletRequest request, @RequestBody StoreBusiness storeBusiness) {
        Customer customer = StoreLoginUtils.getInstance().getCustomerFromSession(request);
        if (storeInfoService.dealStoreBusinessInfo(customer, storeBusiness.getStoreName(), storeBusiness.getCategoryIds(), storeBusiness.getBrandIds(), storeBusiness.getBrands()) == 1) {
            request.getSession().removeAttribute("IS_CHECK");
            return 1;
        }
        return storeInfoService.dealStoreBusinessInfo(customer, storeBusiness.getStoreName(), storeBusiness.getCategoryIds(), storeBusiness.getBrandIds(), storeBusiness.getBrands());
    }

    /**
     * 查询一级二级分类
     *
     * @return 返回分类集合
     */
    @RequestMapping("/store_oneandtwocategory")
    @ResponseBody
    @UnAuth
    public List<Category> queryAllFirstAndSecondCategory() {
        return categoryService.queryAllFirstAndSecondCategory();
    }

    /**
     * 根据父级id查询所有子分类
     *
     * @param parentId 父级id
     * @return 子分类集合
     */
    @RequestMapping("/store_threecategory")
    @ResponseBody
    @UnAuth
    public List<Category> queryCategoryByParentId(long parentId) {
        return categoryService.queryCategoryByParentId(parentId);
    }

    /**
     * 查询admin端品牌
     *
     * @return 品牌集合
     */
    @RequestMapping("/store_queryallbrand")
    @ResponseBody
    @UnAuth
    public List<Brand> queryAllAdminBrand() {
        return brandService.queryAllAdminBrands();
    }

    /**
     * 判断是否拦截
     *
     * @param request request请求
     * @return map
     */
    private Map<String, Object> isIntercept(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("isIntercept", false);
        map.put("redirectUrl", "");
        StoreInfo storeInfo = storeInfoService.queryStoreInfo(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
        if (Objects.isNull(storeInfo) || "0".equals(storeInfo.getStatus())) {//开店第一步
            if (!(request.getSession().getAttribute("IS_CHECK") == null || request.getSession().getAttribute("IS_CHECK") != "true")) {
                map.put("isIntercept", false);
                map.put("redirectUrl", "");
            } else {
                map.put("isIntercept", true);
                map.put("redirectUrl", "openstore/openprotocol");
            }
            return map;
        }
        if ("2".equals(storeInfo.getStatus())) {//开店审核通过->后台首页
            map.put("isIntercept", true);
            map.put("redirectUrl", "redirect:/store_tostoreinfo.htm");
            return map;
        }
        if ("1".equals(storeInfo.getStatus()) || "3".equals(storeInfo.getStatus())) {//审核中
            map.put("isIntercept", false);
            map.put("redirectUrl", storeInfo.getStatus() + "|" + storeInfo.getReason());
            return map;
        }
        return map;
    }

    /**
     * 判断跳转开店第几步页面
     *
     * @param request request请求
     * @param step    开店步骤
     * @return 页面
     */
    private ModelAndView openStoreStep(HttpServletRequest request, int step) {
        Map<String, Object> map = isIntercept(request);
        if ((boolean) map.get("isIntercept")) {
            return new ModelAndView(map.get("redirectUrl").toString());
        }
        switch (step) {
            case 1:
                return new ModelAndView("openstore/openprotocol");
            case 2:
                return new ModelAndView("openstore/fillinstoreinfo");
            case 3:
                return new ModelAndView("openstore/scopeofbusiness");
            case 4:
                return new ModelAndView("openstore/submitresults").addObject("result", map.get("redirectUrl").toString().split("\\|")[0])
                        .addObject("reason", map.get("redirectUrl").toString().split("\\|")[1]);
            default:
                return new ModelAndView("redirect:/store_tologin.htm");
        }
    }
}
