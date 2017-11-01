package com.lecshop.openstore.service.impl;

import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.bean.BrandApply;
import com.lecshop.brand.service.BrandApplyService;
import com.lecshop.brand.service.BrandService;
import com.lecshop.category.bean.StoreSignedCategory;
import com.lecshop.category.service.CategoryService;
import com.lecshop.category.service.StoreSignedCategoryService;
import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.distributionset.service.DistributionSetService;
import com.lecshop.openstore.bean.StoreBusinessInfo;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.mapper.StoreInfoMapper;
import com.lecshop.openstore.service.StoreInfoService;
import com.lecshop.storerole.bean.RoleAndCustomer;
import com.lecshop.storerole.service.StoreRoleService;
import com.lecshop.util.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

/**
 * 开店流程service
 *
 * @author sunluyang on 2017/6/13.
 */
@Service
public class StoreInfoServiceImpl implements StoreInfoService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(StoreInfoServiceImpl.class);

    /**
     * 注入店铺信息mapper
     */
    @Autowired
    private StoreInfoMapper storeInfoMapper;

    /**
     * 注入会员service
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 签约分类service
     */
    @Autowired
    private StoreSignedCategoryService storeSignedCategoryService;

    /**
     * 品牌申请service
     */
    @Autowired
    private BrandApplyService brandApplyService;

    /**
     * 品牌service
     */
    @Autowired
    private BrandService brandService;

    /**
     * 分类service
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 注入店铺角色service
     */
    @Autowired
    private StoreRoleService storeRoleService;

    /**
     * 注入分销设置service
     */
    @Autowired
    private DistributionSetService distributionSetService;

    /**
     * 根据店铺id查询店铺信息
     *
     * @param storeId 店铺id
     * @return 店铺信息
     */
    @Override
    public StoreInfo queryStoreInfo(long storeId) {
        logger.debug("queryAuditPassStoreInfo and storeId:{}", storeId);
        return storeInfoMapper.queryStoreInfo(storeId);
    }

    /**
     * 根据店铺id查询店铺审核通过的信息
     *
     * @param storeId 店铺id
     * @return 店铺审核通过的信息
     */
    @Override
    public StoreInfo queryAuditPassStoreInfo(long storeId) {
        logger.debug("queryAuditPassStoreInfo and storeId:{}", storeId);
        return storeInfoMapper.queryAuditPassStoreInfo(storeId);
    }

    /**
     * 开店处理店铺信息
     *
     * @param storeInfo 店铺实体类
     * @param customer  会员实体类
     * @return 返回值跳转页面  0 出错 1 下一页 2店铺首页 3 拒绝通过页 4 登录页
     */
    @Override
    public int dealStoreInfo(StoreInfo storeInfo, Customer customer, Consumer<Customer> consumer) {
        logger.debug("dealStoreInfo and storeInfo:{}\r\n customer:{}\r\n consumer:{}", storeInfo, customer, consumer);
        StoreInfo queryStoreInfo = storeInfoMapper.queryStoreInfo(customer.getStoreId());
        storeInfo.setStatus("0");
        //店铺信息有无信息
        if (Objects.isNull(queryStoreInfo)) {
            //新增
            if (storeInfoMapper.addStoreInfo(storeInfo) == 1) {
                //更新会员表中的数据
                Customer reCustomer = new Customer();
                reCustomer.setStoreId(storeInfo.getId());
                reCustomer.setType("2");
                reCustomer.setId(customer.getId());
                //更新会员表中的storeI的和type
                customer.setStoreId(storeInfo.getId());
                return customerService.updateStoreIdAndType(reCustomer);
            }
            return 0;
        }
        if ("0".equals(queryStoreInfo.getStatus())) {
            storeInfo.setId(queryStoreInfo.getId());
            //编辑
            return storeInfoMapper.editStoreInfo(storeInfo);
        }
        if ("2".equals(queryStoreInfo.getStatus())) {//审核通过的->重定向后台首页
            return 2;
        }
        if ("1".equals(queryStoreInfo.getStatus()) || "3".equals(queryStoreInfo.getStatus())) {
            return 3;
        }
        return 4;
    }

    /**
     * 开店-处理店铺经营信息
     *
     * @param customer    会员实体类
     * @param storeName   店铺名称
     * @param categoryIds 分类ids
     * @param brandIds    品牌ids
     * @param brands      自定义品牌集合
     * @return 返回码 1处理成功
     */
    @Override
    @Transactional
    public int dealStoreBusinessInfo(Customer customer, String storeName, long[] categoryIds, long[] brandIds, List<Brand> brands) {
        logger.debug("dealStoreBusinessInfo and customer:{}\r\n storeName:{}\r\n categoryIds:{}\r\n categoryIds:{}\r\n" +
                "brandIds:{}\r\n brands:{}", customer, storeName, categoryIds, brandIds, brands);
        StoreInfo storeInfo = storeInfoMapper.queryStoreInfoByName(storeName);
        if (!Objects.isNull(storeInfo) && customer.getStoreId() != storeInfo.getId()) {
            return -1;
        }
        long storeId = customer.getStoreId();
        String name = customer.getUserName();
        //先删后增
        storeSignedCategoryService.deleteSignedCategory(storeId);
        brandApplyService.deleteStoreBrand(storeId);
        brandService.batchDeleteCustomBrand(storeId);
        //添加签约分类
        List<StoreSignedCategory> storeSignedCategories = new ArrayList<>();
        Arrays.stream(categoryIds).forEach(categoryId -> storeSignedCategories.add(new StoreSignedCategory().getStoreSignedCategory(storeId, categoryId)));
        storeSignedCategoryService.addSignedCategory(storeSignedCategories);
        //添加店铺品牌
        List<BrandApply> brandApplies = new ArrayList<>();
        Arrays.stream(brandIds).forEach(brandId -> brandApplies.add(new BrandApply().getBrandApply(storeId, brandId)));
        brandApplyService.addStoreBrand(brandApplies);
        //添加自定义品牌
        if (CollectionUtils.isNotEmpty(brands)) {
            List<Brand> brandList = new ArrayList<>();
            brands.forEach(brand -> brandList.add(new Brand().addMySelfBrand(brand.getName(), brand.getUrl(), brand.getCertificatUrl(), storeId, name)));
            brandService.batchAddCustomBrand(brandList);
        }
        //更新店铺名称
        return storeInfoMapper.editStoreName(new StoreInfo().getStoreInfoToEditStoreName(storeId, "1", storeName));
    }

    /**
     * 开店-查询店铺信息
     *
     * @param storeId 店铺id
     * @param status  品牌状态 状态  0 申请中  1通过 2 拒绝
     * @return 店铺信息
     */
    @Override
    @Transactional
    public StoreBusinessInfo queryStoreBusinessInfo(long storeId, String status) {
        logger.debug("queryStoreBusinessInfo and storeId:{}\r\n status:{}", storeId, status);
        return new StoreBusinessInfo().getStoreBusinessInfo(queryStoreInfo(storeId), categoryService.queryTwoCategoryByStoreId(storeId),
                categoryService.queryThreeCategoryByStoreId(storeId), brandService.queryStoreBrands(storeId, status),
                brandService.queryCustomBrandByStoreIdAndStatus(storeId, status));
    }

    /**
     * 查询已审核/未审核商家集合
     *
     * @param pageHelper  分页帮助类
     * @param status      店铺状态 0填写资料中 1店铺审核中 2审核通过 3审核不通过 4店铺关闭
     * @param companyName 公司名称
     * @param storeName   店铺名称
     * @return 已审核/未审核商家集合
     */
    @Override
    public PageHelper<StoreInfo> queryStoreInfoForAuditList(PageHelper<StoreInfo> pageHelper, String status, String companyName, String storeName) {
        logger.debug("queryStoreInfoForAuditList and status:{}\r\n companyName:{}\r\n storeName:{}", status, companyName, storeName);
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("companyName", companyName);
        params.put("storeName", storeName);
        return pageHelper.setListDates(storeInfoMapper.queryStoreInfoForAuditList(pageHelper.getQueryParams(params, storeInfoMapper.queryStoreInfoForAuditListCount(params))));
    }

    /**
     * 编辑店铺有效期,结算周期,是否关店
     *
     * @param storeInfo 店铺信息
     * @return 编辑返回码
     */
    @Override
    public int editStoreTimeAndIsClose(StoreInfo storeInfo) {
        logger.debug("editStoreTimeAndIsClose and storeInfo:{}", storeInfo);
        if (ArrayUtils.isEmpty(storeInfo.getBillingCycle().split(","))) {
            return -1;
        }
        return storeInfoMapper.editStoreTimeAndIsClose(storeInfo);
    }

    /**
     * 通过商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    @Override
    @Transactional
    public int passStoreAudit(StoreInfo storeInfo) {
        logger.debug("passStoreAudit and storeInfo :{}", storeInfo);
        passStoreAuditDealData(storeInfo.getId());
        return storeInfoMapper.passStoreAudit(storeInfo);
    }

    /**
     * 拒绝商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    @Override
    public int refuseStoreAudit(StoreInfo storeInfo) {
        logger.debug("refuseStoreAudit and storeInfo :{}", storeInfo);
        return storeInfoMapper.refuseStoreAudit(storeInfo);
    }

    /**
     * 删除商家
     *
     * @param id 商家id
     * @return 成功返回1，失败返回0
     */
    @Override
    @Transactional
    public int deleteStore(long id) {
        logger.debug("deleteStore and id :{}", id);
        deleteStoreDealData(id);
        return storeInfoMapper.deleteStore(id);
    }

    /**
     * 编辑店铺信息-客服QQ-公司信息-银行信息
     *
     * @param storeInfo 店铺信息实体类
     * @param flag      1客服QQ 2公司信息 3银行信息
     * @return -1参数错误编辑失败 1 编辑成功
     */
    @Override
    public int editMyStoreInfo(StoreInfo storeInfo, String flag) {
        logger.debug("editMyStoreInfo and storeInfo :{}\r\n flag:{}", storeInfo, flag);
        if ("1".equals(flag)) {
            return storeInfoMapper.editStoreInfoForServiceQQ(storeInfo);
        }
        if ("2".equals(flag)) {
            return storeInfoMapper.editStoreInfoForCompanyInfo(storeInfo);
        }
        if ("3".equals(flag)) {
            return storeInfoMapper.editStoreInfoForBankInfo(storeInfo);
        }
        return -1;
    }

    /**
     * 店铺审核通过后处理相关数据
     *
     * @param storeId 店铺id
     */
    @Transactional
    private void passStoreAuditDealData(long storeId) {
        //为该用户添加权限
        storeRoleService.linkStaffRole(new RoleAndCustomer().getRoleAndCustomer(customerService.queryCustomerIdByStoreId(storeId).getId(), 1));
        //将该店铺下的自定义品牌变为审核通过
        brandService.passCustomBrandByStoreId(storeId);
        //将店铺的签约品牌变为审核通过
        brandApplyService.passBrandAuditByStoreId(storeId);
        //设置店铺默认分销设置
        distributionSetService.addDistributionSet(storeId);
    }

    /**
     * 删除店铺后处理数据
     *
     * @param storeId 店铺id
     */
    @Transactional
    private void deleteStoreDealData(long storeId) {
        //删除签约分类
        storeSignedCategoryService.deleteSignedCategory(storeId);
        //删除店铺品牌
        brandApplyService.deleteStoreBrand(storeId);
        //删除自定义品牌
        brandService.batchDeleteCustomBrand(storeId);
        //删除分销设置
        distributionSetService.deleteDistributionSet(storeId);
    }
}
