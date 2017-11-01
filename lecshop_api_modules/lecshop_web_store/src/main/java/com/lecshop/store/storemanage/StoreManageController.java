package com.lecshop.store.storemanage;

import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.bean.BrandApply;
import com.lecshop.brand.service.BrandApplyService;
import com.lecshop.brand.service.BrandService;
import com.lecshop.customer.bean.StoreStaff;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.service.StoreInfoService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.storemenu.bean.StoreEditMenu;
import com.lecshop.storerole.bean.StoreRole;
import com.lecshop.storerole.service.StoreRoleService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 店铺管理控制器
 *
 * @author sunluyang on 2017/6/9.
 */
@Controller
public class StoreManageController {

    /**
     * 店铺角色service
     */
    @Autowired
    private StoreRoleService storeRoleService;

    /**
     * 注入会员service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 注入店铺信息service
     */
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 品牌service
     */
    @Autowired
    private BrandService brandService;

    /**
     * 品牌申请service
     */
    @Autowired
    private BrandApplyService brandApplyService;

    /**
     * 跳转到角色列表
     *
     * @return 角色列表
     */
    @RequestMapping("/store_torolemanage")
    public ModelAndView toRoleList() {
        return new ModelAndView("storemanage/rolemanage");
    }

    /**
     * 跳转员工列表页面
     *
     * @return 管理员列表页面
     */
    @RequestMapping("/store_tostafflist")
    public ModelAndView toManagerList() {
        return new ModelAndView("storemanage/stafflist");
    }

    /**
     * 查询所有角色
     *
     * @param pageHelper 分页帮助类
     * @param roleName   角色名称
     * @param isPaging   是否需要分页 1需要 0不需要
     * @return 返回角色信息
     */
    @RequestMapping("/store_rolelist")
    @ResponseBody
    public BaseResponse queryAllRole(PageHelper<StoreRole> pageHelper, String roleName, int isPaging, HttpServletRequest request) {
        return BaseResponse.build(storeRoleService.queryAllStoreRole(pageHelper, roleName, isPaging, StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId()));
    }

    /**
     * store菜单显示
     *
     * @param request 页面请求
     * @return 角色菜单
     */
    @RequestMapping("/store_roleauthmenu")
    @ResponseBody
    public List<StoreEditMenu> roleAuthMenu(HttpServletRequest request) {
        return storeRoleService.storeRoleAuthMenu(StoreLoginUtils.getInstance().getCustomerFromSession(request).getId());
    }

    /**
     * 添加角色
     *
     * @param roleName 角色名称
     * @param authIds  角色id
     * @param request  请求对象
     * @return 添加返回码
     */
    @RequestMapping("/store_addrole")
    @ResponseBody
    public int addRole(String roleName, long[] authIds, HttpServletRequest request) {
        return storeRoleService.addStoreRole(roleName, authIds, StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId());
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @RequestMapping("/store_queryauthid")
    @ResponseBody
    public List<Long> queryAuthIdByRoleId(long roleId) {
        return storeRoleService.queryAuthIdByRoleId(roleId);
    }

    /**
     * 删除角色
     *
     * @param roleIds 角色id
     * @return 删除返回值
     */
    @RequestMapping("/store_deleterole")
    @ResponseBody
    public int deleteRole(long[] roleIds, HttpServletRequest request) {
        return storeRoleService.deleteRole(roleIds, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 编辑角色
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    @RequestMapping("/store_editrole")
    @ResponseBody
    public int editRole(long roleId, String roleName, long[] authIds, HttpServletRequest request) {
        return storeRoleService.editRole(roleId, roleName, authIds, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 查询所有管理员
     *
     * @param pageHelper 分页帮助类
     * @param name       管理员名称
     * @return 返回管理员信息
     */
    @RequestMapping("/store_querystaff")
    @ResponseBody
    public BaseResponse queryAllManager(PageHelper<StoreStaff> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(customerService.queryStoreStall(pageHelper, StoreLoginUtils.getInstance().getStoreIdFromSession(request), name));
    }

    /**
     * 查询店铺角色用于添加员工
     *
     * @return 店铺角色集合
     */
    @RequestMapping("/store_querystaffrole")
    @ResponseBody
    public List<StoreRole> queryStaffRole(HttpServletRequest request) {
        return storeRoleService.queryStoreRoleForAddStaff(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 添加员工
     *
     * @param storeStaff 角色和会员关联的实体类
     * @return 添加返回码
     */
    @RequestMapping("/store_addstaff")
    @ResponseBody
    public int addStoreStaff(@RequestBody StoreStaff storeStaff, HttpServletRequest request) {
        return customerService.addStoreStaff(storeStaff, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 删除员工以及删除员工关联的角色
     *
     * @param customerIds 员工id数组
     * @return 删除返回码
     */
    @RequestMapping("/store_deletestaff")
    @ResponseBody
    public int deleteStoreStaff(Long[] customerIds) {
        return customerService.delCustomersAndStaffs(customerIds);
    }

    /**
     * 编辑员工以及编辑员工关联的角色
     *
     * @param storeStaff 员工实体类
     * @return 编辑返回码
     */
    @RequestMapping("/store_editstaff")
    @ResponseBody
    public int editStoreStaff(@RequestBody StoreStaff storeStaff) {
        return customerService.editCustomerAndStaff(storeStaff);
    }

    /**
     * 根据店铺id查询店铺信息
     *
     * @param request request请求
     * @return 返回店铺信息
     */
    @RequestMapping("/store_mystoreinfo")
    @ResponseBody
    public StoreInfo queryStoreInfo(HttpServletRequest request) {
        return storeInfoService.queryStoreInfo(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 编辑店铺信息-客服QQ-公司信息-银行信息
     *
     * @param storeInfo 店铺信息实体类
     * @param flag      1客服QQ 2公司信息 3银行信息
     * @return -1参数错误编辑失败 1 编辑成功
     */
    @RequestMapping("/store_editmystoreinfo")
    @ResponseBody
    public int editMyStoreInfo(@RequestBody StoreInfo storeInfo, String flag) {
        return storeInfoService.editMyStoreInfo(storeInfo, flag);
    }

    /**
     * 跳转到品牌列表
     *
     * @return 品牌管理页面
     */
    @RequestMapping("/store_tobrandlist")
    public ModelAndView toBrandManage() {
        return new ModelAndView("storemanage/brandlist");
    }

    /**
     * 开店查询店铺信息
     *
     * @param request request 请求
     * @return 店铺信息
     */
    @RequestMapping("/store_querystorebrand")
    @ResponseBody
    public BaseResponse queryStoreBrand(PageHelper<Brand> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(brandService.queryStoreBrandsForPage(pageHelper, StoreLoginUtils.getInstance().getStoreIdFromSession(request), name));
    }

    @RequestMapping("/store_queryalladminbrand")
    @ResponseBody
    public List<Brand> queryAllAdminBrand() {
        return brandService.queryAllAdminBrands();
    }

    /**
     * 申请品牌
     *
     * @param brandApply 品牌实体类
     * @return 添加返回码
     */
    @RequestMapping("/store_addstorebrand")
    @ResponseBody
    public int addStoreBrand(@RequestBody BrandApply brandApply, HttpServletRequest request) {
        return brandApplyService.doAddStoreBrand(brandApply, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    @RequestMapping("/store_deletestorebrand")
    @ResponseBody
    public int deleteStoreBrand(HttpServletRequest request, long[] ids) {
        return brandApplyService.deleteStoreBrandByStoreIdAndBrandId(StoreLoginUtils.getInstance().getStoreIdFromSession(request), ids);
    }

    /**
     * 跳转到自定义品牌列表
     *
     * @return 自定义品牌列表
     */
    @RequestMapping("/store_tocustombrandlist")
    public ModelAndView toCustomBrandList() {
        return new ModelAndView("storemanage/custombrandlist");
    }

    /**
     * 查询所有管理员
     *
     * @param pageHelper 分页帮助类
     * @param name       管理员名称
     * @return 返回管理员信息
     */
    @RequestMapping("/store_querycustombrand")
    @ResponseBody
    public BaseResponse queryCustomBrand(PageHelper<Brand> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(brandService.queryBrands(pageHelper, name, "", StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 添加自定义品牌
     *
     * @param brand 品牌实体类
     * @return 添加返回码
     */
    @RequestMapping("store_addcustombrand")
    @ResponseBody
    public int addCustomBrand(@RequestBody Brand brand, HttpServletRequest request) {
        return brandService.addBrand(brand.setDefaultValuesForAdd(StoreLoginUtils.getInstance().getCustomerNameFromSession(request), StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 删除自定义品牌
     *
     * @param ids     自定义品牌id
     * @param request request请求
     * @return 删除返回码
     */
    @RequestMapping("/store_deletecustombrand")
    @ResponseBody
    public int deleteCustomBrand(Long[] ids, HttpServletRequest request) {
        return brandService.batchDeleteBrands(Arrays.stream(ids).map(
                id -> Brand.buildDeleteBrand(id, StoreLoginUtils.getInstance().getCustomerNameFromSession(request), StoreLoginUtils.getInstance().getStoreIdFromSession(request))
        ).collect(Collectors.toList()));
    }
}