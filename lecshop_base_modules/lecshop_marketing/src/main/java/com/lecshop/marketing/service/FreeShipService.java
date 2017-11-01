package com.lecshop.marketing.service;

import com.lecshop.marketing.bean.Marketing;

/**
 * Created by dujinkai on 17/6/11.
 * 包邮服务接口
 */
public interface FreeShipService {

    /**
     * 新增包邮促销
     *
     * @param marketing 包邮促销
     * @return 成功返回1 失败返回0
     */
    int addMarketing(Marketing marketing);

    /**
     * 查询包邮促销
     *
     * @param storeId 店铺id
     * @return 返回包邮促销
     */
    Marketing queryFreeShip(long storeId);
}
