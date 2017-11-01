package com.lecshop.logistics.service;

import com.lecshop.logistics.bean.LogisticsCompany;
import com.lecshop.logistics.bean.LogisticsTemplate;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/15.
 * 物流公司服务接口
 */
public interface LogisticsCompanyService {

    /**
     * 新增物流公司
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    int addLogisticsCompany(LogisticsCompany logisticsCompany);

    /**
     * 分页查询物流公司
     *
     * @param pageHelper 分页帮助类
     * @param name       物流公司名称
     * @param storeId    店铺id
     * @return 返回物流公司
     */
    PageHelper<LogisticsCompany> queryLogisticsCompanys(PageHelper<LogisticsCompany> pageHelper, String name, long storeId);

    /**
     * 删除物流公司
     *
     * @param id      物流公司id
     * @param storeId 店铺id
     * @return 成功返回1  失败返回0
     */
    int deleteLogisticsCompany(long id, long storeId);

    /**
     * 批量删除物流公司
     *
     * @param ids     物流公司id
     * @param storeId 店铺id
     * @return 成功返回1 , 失败返回0 -1 物流公司被关联不能删除
     */
    int deleteLogisticsCompanys(Long[] ids, long storeId);

    /**
     * 根据物流公司id查询物流公司信息
     *
     * @param id      物流公司id
     * @param storeId 店铺id
     * @return 返回物流公司信息
     */
    LogisticsCompany queryLogisticsCompanyById(long id, long storeId);

    /**
     * 修改物流公司
     *
     * @param logisticsCompany 物流公司
     * @return 成功返回 1 失败返回0
     */
    int updateLogisticsCompany(LogisticsCompany logisticsCompany);


    /**
     * 查询可以使用的物流公司(没有被物流模版关联的物流公司)
     *
     * @param storeId 店铺id
     * @return 返回可以使用的物流公司
     */
    List<LogisticsCompany> queryCanUseCompany(long storeId);
}
