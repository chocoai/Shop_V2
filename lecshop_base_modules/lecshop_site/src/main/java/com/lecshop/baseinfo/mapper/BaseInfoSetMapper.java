package com.lecshop.baseinfo.mapper;

import com.lecshop.baseinfo.bean.BaseInfoSet;
import org.springframework.stereotype.Repository;

/**
 * 基本信息设置mapper层
 *
 * @author sunluyang on 2017/5/17.
 */
@Repository
public interface BaseInfoSetMapper {
    /**
     * 查询基本信息设置
     *
     * @return 基本信息设置实体类
     */
    BaseInfoSet queryBaseInfoSet();

    /**
     * 编辑基本信息
     *
     * @param baseInfoSet 信息实体类
     * @return 编辑返回码
     */
    int editBaseInfoSetA(BaseInfoSet baseInfoSet);

    /**
     * 编辑高级信息
     *
     * @param baseInfoSet 信息实体类
     * @return 编辑返回码
     */
    int editBaseInfoSetB(BaseInfoSet baseInfoSet);

    /**
     * 设置审核开关
     *
     * @param storeSpuAudit 店铺商品审核开关  0 需要审核 1 不需要 默认0
     * @return  成功返回1，失败返回0
     */
    int setAuditSwitch(String storeSpuAudit);
}
