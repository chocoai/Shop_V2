package com.lecshop.predeposit.mapper;

import com.lecshop.predeposit.bean.PredepositRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/23.
 * 预存款数据接口
 */
public interface PredepositRecordMapper {

    /**
     * 查询预存款记录总数
     *
     * @param params 查询条件
     * @return 返回预存款记录总数
     */
    int queryPredepositRecordCount(Map<String, Object> params);

    /**
     * 查询预存款记录
     *
     * @param params 查询条件
     * @return 返回预存款记录
     */
    List<PredepositRecord> queryPredepositRecords(Map<String, Object> params);

    /**
     * 根据会员id查询会员最近的一次预存款记录
     *
     * @param id 会员id
     * @return 返回会员的最近的一次预存款记录
     */
    PredepositRecord queryLastRecordByCustomerId(long id);
}
