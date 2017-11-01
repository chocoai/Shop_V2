package com.lecshop.seosetting.service.impl;

import com.lecshop.seosetting.bean.SystemSeo;
import com.lecshop.seosetting.mapper.SystemSeoMapper;
import com.lecshop.seosetting.service.SystemSeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统seo设置service实现类
 *
 * Created by LecShop on 2017/6/30.
 */
@Service
public class SystemSeoServiceImpl implements SystemSeoService{

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SystemSeoServiceImpl.class);

    /**
     * 自动注入系统seo设置数据库接口
     */
    @Autowired
    private SystemSeoMapper systemSeoMapper;

    /**
     * 查询系统seo设置
     *
     * @return 系统seo设置
     */
    @Override
    public SystemSeo querySystemSeo() {
        logger.debug("querySystemSeo...");
        return systemSeoMapper.querySystemSeo();
    }

    /**
     * 保存系统seo设置
     *
     * @param systemSeo 系统seo设置
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateSystemSeo(SystemSeo systemSeo) {
        logger.debug("updateSystemSeo and systemSeo :{}", systemSeo);
        return systemSeoMapper.updateSystemSeo(systemSeo);
    }

}
