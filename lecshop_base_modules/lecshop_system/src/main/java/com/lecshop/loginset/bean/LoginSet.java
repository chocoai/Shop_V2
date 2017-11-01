package com.lecshop.loginset.bean;

import lombok.Data;

/**
 * 登录接口设置
 */
@Data
public class LoginSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 登录类型 1 新浪 2qq  3微信
     */
    private int codeType;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段值
     */
    private String columnValue;

    public static LoginSet getLoginSet(int codeType, String columnName, String columnValue) {
        LoginSet loginSet = new LoginSet();
        loginSet.setCodeType(codeType);
        loginSet.setColumnName(columnName);
        loginSet.setColumnValue(columnValue);
        return loginSet;
    }
}
