package com.lecshop.customer.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/19.
 * 会员搜索条件
 */
@Data
public class CustomerSearchCondition {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户的真实姓名
     */
    private String releName;


    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机是否验证  0 否 1 验证 默认0
     */
    private String isMobileVerification;

    /**
     * 邮箱是否验证   0 否 1 验证 默认0
     */
    private String isEmailVerification;

    /**
     * 用户状态 1 正常 2 冻结 默认1
     */
    private String status;


    /**
     * 1 普通用户 2 商家店铺用户
     */
    private String type;


    /**
     * 获得搜索条件
     *
     * @return 返回搜索套件
     */
    public Map<String, Object> getSearchParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("releName", releName);
        params.put("mobile", mobile);
        params.put("email", email);
        params.put("isMobileVerification", isMobileVerification);
        params.put("isEmailVerification", isEmailVerification);
        params.put("status", status);
        params.put("type", type);
        return params;
    }
}
