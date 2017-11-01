package com.lecshop.onlineservice.bean;

import lombok.Data;

/**
 * 在线客服联系方式
 *
 * @author sunluyang on 2017/5/19.
 */
@Data
public class CustomerServiceInfo {
    /**
     * 主键id
     */
    private long id;
    /**
     * 客服qq
     */
    private String qq;
    /**
     * 客服名称
     */
    private String name;
    /**
     * 删除标记 0 未删除 1 删除 默认0
     */
    private String delFlag;
}
