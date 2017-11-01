package com.lecshop.store;

import com.lecshop.category.bean.Category;
import com.lecshop.category.service.CategoryService;
import com.lecshop.category.service.StoreSignedCategoryService;
import com.lecshop.openstore.bean.StoreBusinessInfo;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.service.StoreInfoService;
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
 * 审核商家控制器
 *
 * @author sunluyang on 2017/6/20.
 */
@Controller
public class AuditSellerController {

    @Autowired
    private StoreInfoService storeInfoService;
    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * 注入签约分类接口
     */
    @Autowired
    private StoreSignedCategoryService storeSignedCategoryService;

    /**
     * 跳转到已审核商家列表
     *
     * @return 已审核商家列表
     */
    @RequestMapping("/tosellerauditpasslist")
    public ModelAndView toSellerAuditPassList() {
        return new ModelAndView("store/sellerauditpasslist");
    }

    /**
     * 跳转到未审核商家列表
     *
     * @return 未审核商家列表
     */
    @RequestMapping("/tosellerunauditlist")
    public ModelAndView toSellerUnauditList() {
        return new ModelAndView("store/sellerunauditlist");
    }

    /**
     * 查询已审核通过商家信息
     *
     * @param pageHelper  分页帮助类
     * @param companyName 公司名称
     * @param storeName   店铺名称
     * @return 已审核通过商家信息
     */
    @RequestMapping("/querysellerauditpasslist")
    @ResponseBody
    public BaseResponse querySellerAuditPassList(PageHelper<StoreInfo> pageHelper, String companyName, String storeName) {
        return BaseResponse.build(storeInfoService.queryStoreInfoForAuditList(pageHelper, "2", companyName, storeName));
    }

    /**
     * 查询未审核的商家信息
     *
     * @param pageHelper  分页帮助类
     * @param companyName 公司名称
     * @param storeName   店铺名称
     * @return 未审核的商家信息
     */
    @RequestMapping("/querysellerunauditlist")
    @ResponseBody
    public BaseResponse querySellerUnAuditList(PageHelper<StoreInfo> pageHelper, String companyName, String storeName) {
        return BaseResponse.build(storeInfoService.queryStoreInfoForAuditList(pageHelper, "1", companyName, storeName));
    }

    /**
     * 通过商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/passstoreaudit")
    @ResponseBody
    public int passStoreAudit(@RequestBody StoreInfo storeInfo) {
        return storeInfoService.passStoreAudit(storeInfo);
    }

    /**
     * 拒绝商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/refusestoreaudit")
    @ResponseBody
    public int refuseStoreAudit(@RequestBody StoreInfo storeInfo) {
        return storeInfoService.refuseStoreAudit(storeInfo);
    }

    /**
     * 删除商家
     *
     * @param id 商家id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/deletestore")
    @ResponseBody
    public int deleteStore(long id) {
        return storeInfoService.deleteStore(id);
    }

    /**
     * 跳转到店铺详情信息
     *
     * @return 已审核商家列表
     */
    @RequestMapping("/tostoredetailinfo")
    public ModelAndView toStoreDetailInfo(long storeId) {
        return new ModelAndView("store/storedetailinfo").addObject("storeId", storeId).addObject("status", "1");
    }

    /**
     * 跳转到未审核店铺详情信息
     *
     * @return 未审核商家列表
     */
    @RequestMapping("/tounauditstoredetailinfo")
    public ModelAndView toUnAuditStoreDetailInfo(long storeId) {
        return new ModelAndView("store/storedetailinfo").addObject("storeId", storeId).addObject("status", "0");
    }

    /**
     * 根据店铺id查询店铺详情信息
     *
     * @param storeId 店铺id
     * @return 店铺详情信息
     */
    @RequestMapping("/querystoredetailinfo")
    @ResponseBody
    public StoreBusinessInfo storeDetailInfoByStoreId(long storeId, String status) {
        return storeInfoService.queryStoreBusinessInfo(storeId, status);
    }

    /**
     * 跳转到店铺的信息设置页面
     *
     * @return 店铺的信息设置页面
     */
    @RequestMapping("/tostoreinfoset")
    @ResponseBody
    public ModelAndView toStoreInfoSet(long storeId) {
        return new ModelAndView("store/sellerauditpassset").addObject("storeId", storeId);
    }

    /**
     * 查询一二级分类信息
     *
     * @return 分类集合
     */
    @RequestMapping("/queryoneandtwocategory")
    @ResponseBody
    public List<Category> queryAllFirstAndSecondCategory() {
        return categoryService.queryAllFirstAndSecondCategory();
    }

    /**
     * 根据父级id查询所有子分类
     *
     * @param parentId 父级id
     * @return 子分类集合
     */
    @RequestMapping("/querythreecategory")
    @ResponseBody
    public List<Category> queryCategoryByParentId(long parentId) {
        return categoryService.queryCategoryByParentId(parentId);
    }

    /**
     * 添加签约分类
     *
     * @param categoryIds 分类id数组
     * @param storeId     店铺id
     * @return 返回添加码 -1 未添加分类 >=1添加成功
     */
    @RequestMapping("/addstoresignedcategory")
    @ResponseBody
    public int addStoreSignedCategory(long[] categoryIds, long storeId) {
        return storeSignedCategoryService.addSignedCategoryAdmin(categoryIds, storeId);
    }

    /**
     * 根据店铺id和分类id删除签约分类
     *
     * @param storeId 店铺id
     * @param cateId  分类id
     * @return 删除返回码
     */
    @RequestMapping("/deletesingedcategory")
    @ResponseBody
    public int deleteStoreSingedCategoryById(long storeId, long cateId) {
        return storeSignedCategoryService.deleteSingedCategoryById(storeId, cateId);
    }

    /**
     * 编辑店铺有效期,结算周期,是否关店
     *
     * @param storeInfo 店铺信息
     * @return 编辑返回码
     */
    @RequestMapping("editstoretimeandisclose")
    @ResponseBody
    public int editStoreTimeAndIsClose(@RequestBody StoreInfo storeInfo) {
        return storeInfoService.editStoreTimeAndIsClose(storeInfo);
    }
}
