package com.lecshop.loginset.bean;

import lombok.Data;

/**
 * 组装的登录接口实体类
 */
@Data
public class LoginSetCommon {
    /**
     * QQ登录接口
     */
    private QQLogin qqLogin = new QQLogin();
    /**
     * 微信登录接口
     */
    private SinaLogin sinaLogin = new SinaLogin();
    /**
     * 微博登录接口
     */
    private WechatLogin wechatLogin = new WechatLogin();

    /**
     * 组装登录接口-用于数据库修改
     *
     * @param loginSetCommon 组装的登录接口实体类
     * @param loginSet       数据库映射实体类
     * @param value          字段的值
     */
    public void getData(LoginSetCommon loginSetCommon, LoginSet loginSet, String value) {
        //微博
        if (loginSet.getCodeType() == 1) {
            if (loginSet.getColumnName().equals("AppKey")) {
                loginSetCommon.getSinaLogin().setKey(value);
            }
            if (loginSet.getColumnName().equals("AppSecret")) {
                loginSetCommon.getSinaLogin().setValue(value);
            }
            if (loginSet.getColumnName().equals("url")) {
                loginSetCommon.getSinaLogin().setUrl(value);
            }
            if (loginSet.getColumnName().equals("isUse")) {
                loginSetCommon.getSinaLogin().setIsUse(value);
            }
        }
        //QQ
        if (loginSet.getCodeType() == 2) {
            if (loginSet.getColumnName().equals("AppID")) {
                loginSetCommon.getQqLogin().setKey(value);
            }
            if (loginSet.getColumnName().equals("AppKey")) {
                loginSetCommon.getQqLogin().setValue(value);
            }
            if (loginSet.getColumnName().equals("url")) {
                loginSetCommon.getQqLogin().setUrl(value);
            }
            if (loginSet.getColumnName().equals("isUse")) {
                loginSetCommon.getQqLogin().setIsUse(value);
            }
        }
        //微信
        if (loginSet.getCodeType() == 3) {
            if (loginSet.getColumnName().equals("AppID")) {
                loginSetCommon.getWechatLogin().setKey(value);
            }
            if (loginSet.getColumnName().equals("AppSecret")) {
                loginSetCommon.getWechatLogin().setValue(value);
            }
            if (loginSet.getColumnName().equals("url")) {
                loginSetCommon.getWechatLogin().setUrl(value);
            }
            if (loginSet.getColumnName().equals("isUse")) {
                loginSetCommon.getWechatLogin().setIsUse(value);
            }
        }
    }

}
