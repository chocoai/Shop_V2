package com.lecshop.stationletter.mapper;

import com.lecshop.stationletter.bean.StationLetter;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/27.
 * 站内信
 */
public interface StationLetterMapper {

    /**
     * 新增站内信
     *
     * @param stationLetters 站内信
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
     * 根据会员id查询会员的站内信
     *
     * @param params 查询参数
     * @return 返回会员的站内信
     */
    List<StationLetter> queryStationLettersByCustomerId(Map<String, Object> params);

    /**
     * 查询会员的站内信总纪录数
     *
     * @param params 查询参数
     * @return 返回会员的站内信总记录数
     */
    int queryStationLettersCountByCustomerId(Map<String, Object> params);

    /**
     * 更新会员的站内信的阅读状态
     *
     * @param id 站内信id
     * @return  成功返回1，失败返回0
     */
    int updateStationLettersIsRead(long id);

}
