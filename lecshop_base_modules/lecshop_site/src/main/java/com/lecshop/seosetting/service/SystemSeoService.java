package com.lecshop.seosetting.service;

import com.lecshop.seosetting.bean.SystemSeo;

/**
 * 系统seo设置service
 *
 * Created by LecShop on 2017/6/30.
 */
public interface SystemSeoService {

    /**
     * 查询系统seo设置
     *
     * @return 系统seo设置
     */
    SystemSeo querySystemSeo();

    /**
     * 保存系统seo设置
     *
     * @param systemSeo 系统seo设置
     * @return 成功返回1，失败返回0
     */
    int updateSystemSeo(SystemSeo systemSeo);

}
