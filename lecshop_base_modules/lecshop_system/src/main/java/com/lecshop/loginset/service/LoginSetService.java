package com.lecshop.loginset.service;

import com.lecshop.loginset.bean.LoginSetCommon;

/**
 * 登录接口设置service接口
 */
public interface LoginSetService {

    /**
     * 查询登录接口
     *
     * @return 登录接口设置实体类
     */
    LoginSetCommon queryLoginSet();

    /**
     * 编辑登录接口设置
     *
     * @param key      AppId
     * @param value    AppSecret
     * @param url      回调地址
     * @param isUse    是否启用
     * @param codeType 1微博 2QQ 3微信
     * @return -1 无法修改 1修改成功
     */
    int editLoginSet(String key, String value, String url, String isUse, int codeType);
}
