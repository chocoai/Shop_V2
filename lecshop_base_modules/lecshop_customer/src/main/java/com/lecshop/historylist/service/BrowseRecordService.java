package com.lecshop.historylist.service;

import com.lecshop.historylist.bean.BrowseRecordList;

import java.util.List;

/**
 * 浏览历史service
 * <p>
 * Created by LecShop on 2017/7/4.
 */
public interface BrowseRecordService {

    /**
     * 查询浏览历史列表
     *
     * @param customerId 会员id
     * @return 浏览历史列表
     */
    List<BrowseRecordList> queryBrowseRecord(long customerId);

    /**
     * 按天删除浏览历史
     * @param customerId 会员id
     * @param createTime 日期
     * @return 成功返回>=1，失败返回0
     */
    int deleteBrowseRecordByDay(long customerId, String createTime);

    /**
     * 根据id删除浏览历史
     *
     * @param id 浏览历史id
     * @return 成功返回1，失败返回0
     */
    int deleteBrowseRecordById(long id);

}
