package com.lecshop.logistics.mapper;

import com.lecshop.logistics.bean.LogisticsTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/31.
 * 物流模版数据库接口
 */
public interface LogisticsTemplateMapper {

    /**
     * 根据店铺id查询所有的运费模版
     *
     * @param storeId 店铺id
     * @return 返回店铺所有的运费模版
     */
    List<LogisticsTemplate> queryAllTemplates(long storeId);

    /**
     * 将运费模版设置成 不是默认的
     *
     * @param storeId 店铺id
     */
    void setLogisticsTemplateUnDefault(long storeId);

    /**
     * 设置默认运费模版
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int setDefaultLogisticsTemplate(Map<String, Object> params);

    /**
     * 新增物流公司模版
     *
     * @param logisticsTemplate 物流公司模版
     * @return 成功返回1 失败返回0
     */
    int addLogisticsTemplate(LogisticsTemplate logisticsTemplate);

    /**
     * 查询物流模版
     *
     * @param params 查询参数
     * @return 返回物流模版
     */
    LogisticsTemplate queryLogisticsTemplate(Map<String, Object> params);

    /**
     * 删除物流模版
     *
     * @param params 参数
     * @return 成功返回1  失败返回0
     */
    int deleteLogisticsTemplate(Map<String, Object> params);

    /**
     * 更新物流模版信息
     *
     * @param logisticsTemplate 物流模版
     * @return 成功返回1 失败返回0
     */
    int updateLogisticsTemplate(LogisticsTemplate logisticsTemplate);

    /**
     * 根据物流公司id查询物流模版总数
     *
     * @param companyId 物流公司id
     * @return 返回物流模版总数
     */
    int queryCountByCompanyId(long companyId);
}
