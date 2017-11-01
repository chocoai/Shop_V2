package com.lecshop.openstore.service;

import com.lecshop.brand.bean.Brand;
import com.lecshop.customer.bean.Customer;
import com.lecshop.openstore.bean.StoreBusinessInfo;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.util.PageHelper;

import java.util.List;
import java.util.function.Consumer;

/**
 * 店铺信息service
 *
 * @author sunluyang on 2017/6/13.
 */
public interface StoreInfoService {
    /**
     * 根据店铺id查询店铺信息
     *
     * @param storeId 店铺id
     * @return 店铺信息
     */
    StoreInfo queryStoreInfo(long storeId);

    /**
     * 根据店铺id查询店铺审核通过的信息
     *
     * @param storeId 店铺id
     * @return 店铺审核通过的信息
     */
    StoreInfo queryAuditPassStoreInfo(long storeId);

    /**
     * 开店处理店铺信息
     *
     * @param storeInfo 店铺实体类
     * @param customer  会员实体类
     * @return 返回值跳转页面
     */
    int dealStoreInfo(StoreInfo storeInfo, Customer customer, Consumer<Customer> consumer);

    /**
     * 处理店铺经营类型
     *
     * @param customer    会员实体类
     * @param storeName   店铺名称
     * @param categoryIds 分类ids
     * @param brandIds    品牌ids
     * @param brands      自定义品牌集合
     * @return 添加返回码
     */
    int dealStoreBusinessInfo(Customer customer, String storeName, long[] categoryIds, long[] brandIds, List<Brand> brands);

    /**
     * 开店查询店铺信息
     *
     * @param storeId 店铺id
     * @param status  品牌状态 状态  0 申请中  1通过 2 拒绝
     * @return 店铺信息
     */
    StoreBusinessInfo queryStoreBusinessInfo(long storeId, String status);

    /**
     * 查询已审核/未审核商家集合
     *
     * @param pageHelper  分页帮助类
     * @param status      店铺状态 0填写资料中 1店铺审核中 2审核通过 3审核不通过 4店铺关闭
     * @param companyName 公司名称
     * @param storeName   店铺名称
     * @return 已审核/未审核商家集合
     */
    PageHelper<StoreInfo> queryStoreInfoForAuditList(PageHelper<StoreInfo> pageHelper, String status, String companyName, String storeName);


    /**
     * 编辑店铺有效期,结算周期,是否关店
     *
     * @param storeInfo 店铺信息
     * @return 编辑返回码
     */
    int editStoreTimeAndIsClose(StoreInfo storeInfo);

    /**
     * 通过商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    int passStoreAudit(StoreInfo storeInfo);

    /**
     * 拒绝商家审核
     *
     * @param storeInfo 商家实例
     * @return 成功返回1，失败返回0
     */
    int refuseStoreAudit(StoreInfo storeInfo);

    /**
     * 删除商家
     *
     * @param id 商家id
     * @return 成功返回1，失败返回0
     */
    int deleteStore(long id);

    /**
     * 编辑店铺信息-客服QQ-公司信息-银行信息
     *
     * @param storeInfo 店铺信息实体类
     * @param flag      1客服QQ 2公司信息 3银行信息
     * @return -1参数错误编辑失败 1 编辑成功
     */
    int editMyStoreInfo(StoreInfo storeInfo, String flag);
}
