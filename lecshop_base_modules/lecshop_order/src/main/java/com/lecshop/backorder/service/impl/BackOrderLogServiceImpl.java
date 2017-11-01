package com.lecshop.backorder.service.impl;

import com.lecshop.backorder.bean.BackOrderLog;
import com.lecshop.backorder.mapper.BackOrderLogMapper;
import com.lecshop.backorder.service.BackOrderLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/6/6.
 * 退单操作日志服务接口实现
 */
@Service
public class BackOrderLogServiceImpl implements BackOrderLogService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(BackOrderLogServiceImpl.class);

    /**
     * 注入退单数据库接口
     */
    @Autowired
    private BackOrderLogMapper backOrderLogMapper;

    @Override
    public List<BackOrderLog> queryByBackOrderId(long bacKOrderId) {
        logger.debug("queryByBackOrderId and bacKOrderId:{}", bacKOrderId);
        return backOrderLogMapper.queryByBackOrderId(bacKOrderId);
    }

    @Override
    public int addBackOrderLog(BackOrderLog backOrderLog) {
        logger.debug("addBackOrderLog and backOrderLog:{}", backOrderLog);

        if (Objects.isNull(backOrderLog)) {
            logger.error("addBackOrderLog fail due to params is null....");
            return 0;
        }
        return backOrderLogMapper.addBackOrderLog(backOrderLog);
    }
}
