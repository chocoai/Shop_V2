package com.lecshop.loginset.bean;

import lombok.Data;

/**
 * QQ登录设置实体类
 *
 * @author sunluyang on 2017/5/16.
 */
@Data
public class QQLogin {
    /**
     * key
     */
    private String key;
    /**
     * value
     */
    private String value;
    /**
     * 回调地址
     */
    private String url;
    /**
     * 是否启用
     */
    private String isUse;
}
