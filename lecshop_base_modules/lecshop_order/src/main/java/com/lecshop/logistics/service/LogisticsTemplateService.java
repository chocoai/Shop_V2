package com.lecshop.logistics.service;

import com.lecshop.logistics.bean.LogisticsTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/31.
 * 物流模版服务接口
 */
public interface LogisticsTemplateService {


    /**
     * 根据店铺id查询所有的运费模版
     *
     * @param storeId 店铺id
     * @return 返回店铺所有的运费模版
     */
    List<LogisticsTemplate> queryAllTemplates(long storeId);

    /**
     * 设置默认模版
     *
     * @param id      模版id
     * @param storeId 店铺id
     * @return 成功返回1 失败返回0
     */
    int setDefaultTemplate(long id, long storeId);

    /**
     * 新增运费模版
     *
     * @param logisticsTemplate 运费模版
     * @return 成功返回1失败返回0
     */
    int addLogisticsTemplate(LogisticsTemplate logisticsTemplate);

    /**
     * 根据id查询物流模版(包含运费方式和运费方式区域)
     *
     * @param id      物流模版id
     * @param storeId 店铺id
     * @return 返回物流模版
     */
    LogisticsTemplate queryLogisticsTemplate(long id, long storeId);

    /**
     * 删除运费模版
     *
     * @param id      运费模版id
     * @param storeId 店铺id
     * @return 成功返回1 失败返回0
     */
    int deleteLogisticsTemplate(long id, long storeId);

    /**
     * 修改物流模版
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    int updateLogisticsTemplate(LogisticsTemplate logisticsTemplate);

    /**
     * 根据物流公司id查询物流模版数量
     *
     * @param companyId 物流公司id
     * @return 返回物流模版数量
     */
    int queryCountByCompanyId(long companyId);

    /**
     * 查询运费模版信息
     *
     * @param id      运费模版id
     * @param storeId 店铺id
     * @return 返回运费模版 (运费模版信息和运费模版的公司信息)
     */
    LogisticsTemplate queryLogisticsTemplateWithCompany(long id, long storeId);

}
