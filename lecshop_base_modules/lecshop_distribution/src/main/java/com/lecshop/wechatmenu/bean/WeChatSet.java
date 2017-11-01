package com.lecshop.wechatmenu.bean;

import lombok.Data;

/**
 * 第三方微信设置
 *
 * @author sunluyang on 2017/5/25.
 */
@Data
public class WeChatSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 店铺id
     */
    private long storeId;
    /**
     * 公众号id
     */
    private String appId;
    /**
     * 公众号秘钥
     */
    private String appSecret;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * api秘钥
     */
    private String apiSecret;
}
