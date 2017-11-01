package com.lecshop.historylist.service.impl;

import com.lecshop.historylist.bean.BrowseRecord;
import com.lecshop.historylist.bean.BrowseRecordList;
import com.lecshop.historylist.mapper.BrowseRecordMapper;
import com.lecshop.historylist.service.BrowseRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.event.ObjectChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 浏览历史service实现类
 * <p>
 * Created by LecShop on 2017/7/4.
 */
@Service
public class BrowseRecordServiceImpl implements BrowseRecordService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(BrowseRecordServiceImpl.class);


    /**
     * 自动注入浏览历史数据库接口
     */
    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    /**
     * 查询浏览历史列表
     *
     * @param customerId 会员id
     * @return 浏览历史列表
     */
    @Override
    public List<BrowseRecordList> queryBrowseRecord(long customerId) {
        logger.debug("queryBrowseRecord and customerId :{}", customerId);
        List<BrowseRecordList> browseRecordLists = new ArrayList<>();
        List<BrowseRecord> list = browseRecordMapper.queryBrowseRecord(customerId);
        if (CollectionUtils.isEmpty(list)) {
            return browseRecordLists;
        }
        list.stream().collect(Collectors.groupingBy(BrowseRecord::getGroupByTime)).forEach((key, value) -> {
            BrowseRecordList browseRecordList = new BrowseRecordList();
            browseRecordList.setTime(key);
            browseRecordList.setBrowseRecordList(value);
            browseRecordLists.add(browseRecordList);
        });
        Collections.sort(browseRecordLists);
        return browseRecordLists;
    }

    /**
     * 按天删除浏览历史
     * @param customerId 会员id
     * @param createTime 日期
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int deleteBrowseRecordByDay(long customerId, String createTime) {
        logger.debug("deleteBrowseRecordByDay and customerId \r\n and createTime :{}", customerId, createTime);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("createTime", createTime);
        return browseRecordMapper.deleteBrowseRecordByDay(params);
    }

    /**
     * 根据id删除浏览历史
     *
     * @param id 浏览历史id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteBrowseRecordById(long id) {
        logger.debug("deleteBrowseRecordById and id :{}", id);
        return browseRecordMapper.deleteBrowseRecordById(id);
    }

}
