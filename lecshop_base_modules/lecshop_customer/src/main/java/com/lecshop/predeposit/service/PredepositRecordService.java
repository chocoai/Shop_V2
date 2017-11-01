package com.lecshop.predeposit.service;

import com.lecshop.predeposit.bean.PredepositRecord;
import com.lecshop.predeposit.bean.QueryCriteria;
import com.lecshop.util.PageHelper;

import java.math.BigDecimal;

/**
 * Created by dujinkai on 17/5/23.
 * 预存款服务接口
 */
public interface PredepositRecordService {

    /**
     * 分页查询预存款记录
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回预存款记录
     */
    PageHelper<PredepositRecord> queryPredepositRecords(PageHelper<PredepositRecord> pageHelper, QueryCriteria queryCriteria);

    /**
     * 查询会员的预存款总金额
     *
     * @param id 会员id
     * @return 返回会员的预存款总金额
     */
    BigDecimal queryCutomerAllPredeposit(long id);
}
