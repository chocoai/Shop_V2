package com.lecshop.helpcenter.service;

import com.lecshop.helpcenter.bean.ServiceSupport;
import com.lecshop.util.PageHelper;

import java.util.List;


/**
 * 服务支持service
 * <p>
 * Created by LecShop on 2017/6/2.
 */
public interface ServiceSupportService {

    /**
     * 分页查询服务支持
     *
     * @param pageHelper 分页帮助类
     * @param name       服务支持名称
     * @return 返回服务支持
     */
    PageHelper<ServiceSupport> queryServiceSupport(PageHelper<ServiceSupport> pageHelper, String name);

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
     * 查询所有的服务支持
     *
     * @return 查询所有服务支持
     */
    List<ServiceSupport> queryAllServiceSupport();

}
