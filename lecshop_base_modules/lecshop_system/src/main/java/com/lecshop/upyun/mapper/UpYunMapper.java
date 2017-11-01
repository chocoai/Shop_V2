package com.lecshop.upyun.mapper;

import com.lecshop.upyun.bean.UpYunSetting;

/**
 * Created by dujinkai on 17/5/8.
 * 又拍云设置数据库接口
 */
public interface UpYunMapper {

    /**
     * 查询系统设置的又拍云信息
     *
     * @return 返回又拍云信息
     */
    UpYunSetting queryUpYunSetting();

    /**
     * 修改又拍云信息
     *
     * @param upYunSetting 又拍云信息
     * @return 成功返回1 失败返回0
     */
    int updateUpYun(UpYunSetting upYunSetting);
}
