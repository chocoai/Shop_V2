package com.lecshop.openstore.mapper;

import com.lecshop.openstore.bean.StoreInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 店铺信息mapper
 *
 * @author sunluyang on 2017/6/13.
 */
@Repository
public interface StoreInfoMapper {
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
     * 开店-添加店铺信息(第二步)
     *
     * @param storeInfo 店铺信息实体类
     * @return 添加返回码
     */
    int addStoreInfo(StoreInfo storeInfo);

    /**
     * 开店-编辑店铺信息(第二步)
     *
     * @param storeInfo 店铺信息实体类
     * @return 编辑返回码
     */
    int editStoreInfo(StoreInfo storeInfo);

    /**
     * 编辑店铺名称
     *
     * @param storeInfo 店铺信息实体类
     * @return 编辑返回码
     */
    int editStoreName(StoreInfo storeInfo);

    /**
     * 根据店铺名称查询店铺
     *
     * @param storeName 店铺名称
     * @return 店铺信息实体类
     */
    StoreInfo queryStoreInfoByName(String storeName);

    /**
     * 查询已审核/未审核商家个数
     *
     * @param map 查询参数
     * @return 个数
     */
    int queryStoreInfoForAuditListCount(Map<String, Object> map);

    /**
     * 查询已审核/未审核商家集合
     *
     * @param map 查询参数
     * @return 已审核/未审核商家集合
     */
    List<StoreInfo> queryStoreInfoForAuditList(Map<String, Object> map);

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
     * 编辑店铺信息-客服QQ
     *
     * @param storeInfo 店铺信息实体类
     * @return 编辑返回码
     */
    int editStoreInfoForServiceQQ(StoreInfo storeInfo);

    /**
     * 编辑店铺信息-公司信息
     *
     * @param storeInfo 店铺信息实体类
     * @return 编辑返回码
     */
    int editStoreInfoForCompanyInfo(StoreInfo storeInfo);

    /**
     * 编辑店铺信息-银行信息
     *
     * @param storeInfo 店铺信息实体类
     * @return 编辑返回码
     */
    int editStoreInfoForBankInfo(StoreInfo storeInfo);
}
