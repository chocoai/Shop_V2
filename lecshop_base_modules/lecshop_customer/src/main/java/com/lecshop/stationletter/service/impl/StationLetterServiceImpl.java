package com.lecshop.stationletter.service.impl;

import com.lecshop.stationletter.bean.StationLetter;
import com.lecshop.stationletter.mapper.StationLetterMapper;
import com.lecshop.stationletter.service.StationLetterService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/27.
 * 站内信服务接口实现
 */
@Service
public class StationLetterServiceImpl implements StationLetterService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(StationLetterServiceImpl.class);

    /**
     * 注入站内信数据库接口
     */
    @Autowired
    private StationLetterMapper stationLetterMapper;

    @Override
    public int addStationLetters(List<StationLetter> stationLetters) {
        logger.debug("addStationLetters and stationLetters:{}", stationLetters);

        if (CollectionUtils.isEmpty(stationLetters)) {
            logger.error("addStationLetters fail due to params is null...");
            return 0;
        }
        return stationLetterMapper.addStationLetters(stationLetters);
    }

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteStationLetters(long id) {
        logger.debug("deleteStationLetters and id :{}", id);
        return stationLetterMapper.deleteStationLetters(id);
    }

    /**
     * 更新会员的站内信的阅读状态
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateStationLettersIsRead(long id) {
        logger.debug("updateStationLettersIsRead and id :{}", id);
        return stationLetterMapper.updateStationLettersIsRead(id);
    }

    @Override
    public PageHelper<StationLetter> queryStationLettersByCustomerId(PageHelper<StationLetter> pageHelper, long customerId) {
        logger.debug("queryStationLettersByCustomerId and customerId:{} and pageHelper:{}", customerId, pageHelper);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        return pageHelper.setListDates(stationLetterMapper.queryStationLettersByCustomerId(pageHelper.getQueryParams(params, stationLetterMapper.queryStationLettersCountByCustomerId(params))));
    }
}
