package com.lecshop.logistics.mapper;

import com.lecshop.logistics.bean.LogisticsCompany;
import com.lecshop.logistics.bean.LogisticsTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/15.
 * 物流公司数据库接口
 */
public interface LogisticsCompanyMapper {

    /**
     * 新增物流公司
     *
     * @param logisticsCompany 物流公司信息
     * @return 成功返回 1 失败返回0
     */
    int addLogisticsCompany(LogisticsCompany logisticsCompany);

    /**
     * 查询物流公司总数
     *
     * @param params 参数
     * @return 返回物流公司总数
     */
    int queryLogisticsCompanyCount(Map<String, Object> params);

    /**
     * 分页查询物流公司
     *
     * @param params 查询参数
     * @return 返回物流公司数据
     */
    List<LogisticsCompany> queryLogisticsCompanys(Map<String, Object> params);

    /**
     * 根据物流公司id删除物流公司
     *
     * @param params 参数
     * @return 成功返回 1 失败返回0
     */
    int deleteLogisticsCompany(Map<String, Object> params);

    /**
     * 根据id查询物流公司
     *
     * @param params 参数
     * @return 返回物流公司信息
     */
    LogisticsCompany queryLogisticsCompanyById(Map<String, Object> params);

    /**
     * 修改物流信息
     *
     * @param logisticsCompany 物流信息
     * @return 成功返回 1 失败返回0
     */
    int updateLogisticsCompany(LogisticsCompany logisticsCompany);

    /**
     * 查询可以使用的物流公司(没有被物流模版关联的物流公司)
     *
     * @param storeId 店铺id
     * @return 返回没有被物流模版关联的物流公司
     */
    List<LogisticsCompany> queryCanUseCompany(long storeId);
}
