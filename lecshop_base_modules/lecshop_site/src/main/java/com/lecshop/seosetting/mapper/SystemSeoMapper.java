package com.lecshop.seosetting.mapper;

import com.lecshop.seosetting.bean.SystemSeo;
import org.springframework.stereotype.Repository;

/**
 * 系统seo设置数据库接口
 *
 * Created by LecShop on 2017/6/30.
 */
@Repository
public interface SystemSeoMapper {

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
