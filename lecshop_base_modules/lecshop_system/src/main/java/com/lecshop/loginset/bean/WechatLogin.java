package com.lecshop.loginset.bean;

import lombok.Data;

/**
 * 微信登录设置实体类
 *
 * @author sunluyang on 2017/5/16.
 */
@Data
public class WechatLogin {
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
