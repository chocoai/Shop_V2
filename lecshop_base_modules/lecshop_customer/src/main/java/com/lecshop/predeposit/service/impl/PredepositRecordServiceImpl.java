package com.lecshop.predeposit.service.impl;

import com.lecshop.predeposit.bean.PredepositRecord;
import com.lecshop.predeposit.bean.QueryCriteria;
import com.lecshop.predeposit.mapper.PredepositRecordMapper;
import com.lecshop.predeposit.service.PredepositRecordService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/23.
 * 预存款服务实现接口
 */
@Service
public class PredepositRecordServiceImpl implements PredepositRecordService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(PredepositRecordServiceImpl.class);

    /**
     * 注入预存款数据库接口
     */
    @Autowired
    private PredepositRecordMapper predepositRecordMapper;

    @Override
    public PageHelper<PredepositRecord> queryPredepositRecords(PageHelper<PredepositRecord> pageHelper, QueryCriteria queryCriteria) {
        logger.debug("queryPredepositRecords and pageHelper :{} \r\n queryCriteria:{}", pageHelper, queryCriteria);

        Map<String, Object> params = queryCriteria.getQueryMap();

        return pageHelper.setListDates(predepositRecordMapper.queryPredepositRecords(pageHelper.getQueryParams(params, predepositRecordMapper.queryPredepositRecordCount(params))));
    }

    @Override
    public BigDecimal queryCutomerAllPredeposit(long id) {

        PredepositRecord predepositRecord = predepositRecordMapper.queryLastRecordByCustomerId(id);

        logger.debug("queryCutomerAllPredeposit and id :{} \r\n and result:{}", id, predepositRecord);

        // 如果用户还没有预存款记录 则直接返回0
        if (Objects.isNull(predepositRecord)) {
            return new BigDecimal(0);
        }

        // 返回用户的预存款总金额
        return predepositRecord.getCurrentMoney();
    }

}
