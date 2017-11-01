package com.lecshop.predeposit.bean;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 会员预存款查询条件
 */
@Data
public class QueryCriteria {

    /**
     * 会员id
     */
    private long customerId;

    /**
     * 会员名称
     */
    private String userName;

    /**
     * 交易单号
     */
    private String transCode;

    /**
     * 创建时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 获得查询条件
     *
     * @return 返回查询条件
     */
    public Map<String, Object> getQueryMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("userName", userName);
        params.put("transCode", transCode);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        return params;
    }

}
