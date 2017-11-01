package com.lecshop.onlineservice.bean;

import lombok.Data;

import java.util.List;

/**
 * 在线客服实体类
 *
 * @author sunluyang on 2017/5/19.
 */
@Data
public class CustomerService {
    /**
     * 主键id
     */
    private long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否启用 0 启用 1 未启用 默认0
     */
    private String isUse;

    /**
     * 在线客服联系方式实体类集合
     */
    private List<CustomerServiceInfo> customerServiceInfo;
}
