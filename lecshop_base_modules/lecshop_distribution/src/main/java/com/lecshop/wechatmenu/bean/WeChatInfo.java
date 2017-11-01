package com.lecshop.wechatmenu.bean;

import lombok.Data;

/**
 * 微信信息实体类
 *
 * @author sunluyang on 2017/5/24.
 */
@Data
public class WeChatInfo {
    /**
     * 店铺id
     */
    private long storeId;
    /**
     * 店铺appId
     */
    private String appId;
    /**
     * 店铺appSecret
     */
    private String appSecret;

    /**
     * 有参构造器
     *
     * @param storeId   店铺id
     * @param appId     店铺appId
     * @param appSecret 店铺appSecret
     */
    public WeChatInfo(long storeId, String appId, String appSecret) {
        this.storeId = storeId;
        this.appId = appId;
        this.appSecret = appSecret;
    }
}
