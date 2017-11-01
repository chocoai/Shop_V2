package com.lecshop.distributorlist.bean;

import lombok.Data;

/**
 * @author sunluyang on 2017/5/25.
 */
@Data
public class Distributor {
    /**
     * 主键id
     */
    private long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 会员所属的店铺id   平台的为0(分销的时候使用)
     */
    private long belongStoreId;
    /**
     * 会员的上级id 　如果为0 则说明没有上级
     */
    private long superiorId;
}
