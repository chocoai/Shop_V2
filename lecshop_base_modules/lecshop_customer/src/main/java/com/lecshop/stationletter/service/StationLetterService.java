package com.lecshop.stationletter.service;

import com.lecshop.stationletter.bean.StationLetter;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/27.
 * 站内信服务接口
 */
public interface StationLetterService {


    /**
     * 新增站内信
     *
     * @param stationLetters 站内信集合
     * @return 成功返回>1 失败返回0
     */
    int addStationLetters(List<StationLetter> stationLetters);

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    int deleteStationLetters(long id);

    /**
     * 根据会员di查询会员的站内信
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回会员的站内信
     */
    PageHelper<StationLetter> queryStationLettersByCustomerId(PageHelper<StationLetter> pageHelper, long customerId);

    /**
     * 更新会员的站内信的阅读状态
     *
     * @param id 站内信id
     * @return  成功返回1，失败返回0
     */
    int updateStationLettersIsRead(long id);

}
