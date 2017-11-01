package com.lecshop.helpcenter.service.impl;

import com.lecshop.helpcenter.bean.HelpList;
import com.lecshop.helpcenter.bean.ServiceSupport;
import com.lecshop.helpcenter.mapper.ServiceSupportMapper;
import com.lecshop.helpcenter.service.HelpListService;
import com.lecshop.helpcenter.service.ServiceSupportService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务支持service实现类
 * <p>
 * Created by LecShop on 2017/6/2.
 */
@Service
public class ServiceSupportServiceImpl implements ServiceSupportService {

    /**
     * 注入服务支持mapper
     */
    @Autowired
    private ServiceSupportMapper serviceSupportMapper;

    /**
     * 自动注入帮助service
     */
    @Autowired
    private HelpListService helpListService;

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ServiceSupportServiceImpl.class);

    /**
     * 分页查询服务支持
     *
     * @param pageHelper 分页帮助类
     * @param name       服务支持名称
     * @return 返回服务支持
     */
    @Override
    public PageHelper<ServiceSupport> queryServiceSupport(PageHelper<ServiceSupport> pageHelper, String name) {
        logger.debug("queryServiceSupport and pageHelper :{} \r\n name :{}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(serviceSupportMapper.queryServiceSupport(pageHelper.getQueryParams(params, serviceSupportMapper.queryServiceSupportCount(params))));
    }


    /**
     * 添加服务支持
     *
     * @param serviceSupport 服务支持
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addServiceSupport(ServiceSupport serviceSupport) {
        logger.debug("addServiceSupport and serviceSupport :{}", serviceSupport);
        return serviceSupportMapper.addServiceSupport(serviceSupport);
    }

    /**
     * 删除服务支持
     *
     * @param id 服务支持id
     * @return 成功返回1  失败返回0
     */
    @Override
    public int deleteServiceSupport(long id) {
        logger.debug("deleteServiceSupport and id :{}", id);
        return serviceSupportMapper.deleteServiceSupport(id);
    }

    /**
     * 批量删除服务支持
     *
     * @param ids 服务支持id数组
     * @return 成功返回1  失败返回0
     */
    @Override
    public int batchDeleteServiceSupport(long[] ids) {
        logger.debug("batchDeleteServiceSupport and ids :{}", ids);
        return serviceSupportMapper.batchDeleteServiceSupport(ids);
    }

    /**
     * 通过id查询服务支持
     *
     * @param id 服务支持id
     * @return 返回服务支持
     */
    @Override
    public ServiceSupport queryServiceSupportById(long id) {
        logger.debug("queryServiceSupportById and id :{}", id);
        return serviceSupportMapper.queryServiceSupportById(id);
    }

    /**
     * 修改服务支持
     *
     * @param serviceSupport 服务支持
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateServiceSupport(ServiceSupport serviceSupport) {
        logger.debug("updateServiceSupport and serviceSupport :{}", serviceSupport);
        return serviceSupportMapper.updateServiceSupport(serviceSupport);
    }

    /**
     * 查询所有服务支持
     *
     * @return 返回所有服务支持
     */
    @Override
    public List<ServiceSupport> queryAllServiceSupport() {
        logger.debug("queryAllServiceSupport .....");
        return serviceSupportMapper.queryAllServiceSupport();
    }
}
