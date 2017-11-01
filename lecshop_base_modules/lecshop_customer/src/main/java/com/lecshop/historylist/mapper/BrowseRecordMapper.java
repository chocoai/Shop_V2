package com.lecshop.historylist.mapper;

import com.lecshop.historylist.bean.BrowseRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 浏览历史数据库接口
 *
 * Created by LecShop on 2017/7/4.
 */
@Repository
public interface BrowseRecordMapper {

    /**
     * 查询浏览历史列表
     *
     * @param customerId 会员id
     * @return 浏览历史列表
     */
    List<BrowseRecord> queryBrowseRecord(long customerId);

    /**
     * 按天删除浏览历史
     *
     * @param params 日期及会员id
     * @return 成功返回>=1，失败返回0
     */
    int deleteBrowseRecordByDay(Map<String, Object> params);

    /**
     * 根据id删除浏览历史
     *
     * @param id 浏览历史id
     * @return 成功返回1，失败返回0
     */
    int deleteBrowseRecordById(long id);

    /**
     * 查询浏览历史总记录数
     *
     * @param customerId 会员id
     * @return 浏览历史总记录数
     */
    int queryBrowseRecordCount(long customerId);

}
