package com.lecshop.marketing.service;

import com.lecshop.marketing.bean.Marketing;
import com.lecshop.util.PageHelper;

/**
 * Created by dujinkai on 17/6/7.
 * 促销服务接口
 */
public interface MarketingService {

    /**
     * 新增促销
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败返回0
     */
    int addMarketing(Marketing marketing);


    /**
     * 设置促销的详情
     *
     * @param marketing 促销信息
     */
    void setMarketingDetail(Marketing marketing);

    /**
     * 更新促销信息
     *
     * @param marketing 促销信息
     * @return 成功返回1 失败发回0
     */
    int updateMarketing(Marketing marketing);
}
