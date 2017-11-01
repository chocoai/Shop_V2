package com.lecshop.baseinfo.service;

import com.lecshop.baseinfo.bean.BaseInfoSet;

/**
 * 基本信息设置service层
 *
 * @author sunluyang on 2017/5/17.
 */
public interface BaseInfoSetService {
    /**
     * 查询基本信息和高级信息设置
     *
     * @return 基本信息设置实体类
     */
    BaseInfoSet queryBaseInfoSet();

    /**
     * 编辑基本信息和高级信息
     *
     * @param baseInfoSet 基本信息实体类
     * @param type        1 基本信息 0高级信息
     * @return -1编辑失败 1编辑成功 0编辑失败
     */
    int editBaseInfoSet(BaseInfoSet baseInfoSet, int type);

    /**
     * 设置审核开关
     *
     * @param storeSpuAudit 店铺商品审核开关  0 需要审核 1 不需要 默认0
     * @return 成功返回1，失败返回0
     */
    int setAuditSwitch(String storeSpuAudit);

    /**
     * 判断单品是否需要审核
     *
     * @return 需要审核返回true  不需要返回false
     */
    boolean isSkuNeedAudit();
}
