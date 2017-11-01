package com.lecshop.helpcenter.mapper;

import com.lecshop.helpcenter.bean.ServiceSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 服务支持数据库接口
 * <p>
 * Created by LecShop on 2017/6/2.
 */
@Repository
public interface ServiceSupportMapper {

    /**
     * 分页查询服务支持
     *
     * @param params 查询参数
     * @return 返回服务支持
     */
    List<ServiceSupport> queryServiceSupport(Map<String, Object> params);

    /**
     * 查询帮助列表的总记录数
     *
     * @param params 查询参数
     * @return 返回服务支持总记录数
     */
    int queryServiceSupportCount(Map<String, Object> params);

    /**
     * 添加服务支持
     *
     * @param serviceSupport 服务支持
     * @return 成功返回1，失败返回0
     */
    int addServiceSupport(ServiceSupport serviceSupport);

    /**
     * 删除服务支持
     *
     * @param id 服务支持id
     * @return 成功返回1  失败返回0
     */
    int deleteServiceSupport(long id);

    /**
     * 批量删除服务支持
     *
     * @param ids 服务支持id数组
     * @return 成功返回1  失败返回0
     */
    int batchDeleteServiceSupport(long[] ids);

    /**
     * 通过id查询服务支持
     *
     * @param id 服务支持id
     * @return 返回服务支持
     */
    ServiceSupport queryServiceSupportById(long id);

    /**
     * 修改服务支持
     *
     * @param serviceSupport 服务支持
     * @return 成功返回1，失败返回0
     */
    int updateServiceSupport(ServiceSupport serviceSupport);

    /**
     * 查询所有服务支持
     *
     * @return 返回所有服务支持
     */
    List<ServiceSupport> queryAllServiceSupport();
}
