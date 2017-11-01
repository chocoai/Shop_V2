package com.lecshop.smsset.bean;

import lombok.Data;

/**
 * 短信接口实体类
 */
@Data
public class SmsSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * key
     */
    private String key;
    /**
     *
     */
    private String secret;
    /**
     * URL链接
     */
    private String url;
    /**
     *
     */
    private String sign;
    /**
     * 模板id
     */
    private String templateId;
}
