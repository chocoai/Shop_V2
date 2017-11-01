package com.lecshop.smsset.service.impl;

import com.lecshop.smsset.bean.SmsSet;
import com.lecshop.smsset.mapper.SmsSetMapper;
import com.lecshop.smsset.service.SmsSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 短信设置service实现类
 */
@Service
public class SmsSetServiceImpl implements SmsSetService {


    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SmsSetServiceImpl.class);


    /**
     * 注入短信设置mapper
     */
    @Autowired
    private SmsSetMapper smsSetMapper;

    /**
     * 查询短信设置
     *
     * @return 短信实体类
     */
    @Override
    public SmsSet querySmsSet() {
        logger.debug("querySmsSet...");
        return smsSetMapper.querySmsSet();
    }

    /**
     * 编辑短信设置
     *
     * @param smsSet 短信设置实体类
     * @return -1实体类为空 1编辑成功
     */
    @Override
    public int editSmsSet(SmsSet smsSet) {
        if (Objects.isNull(smsSet)) {
            logger.error("editSmsSet error due to smsSet is empty...");
            return -1;
        }
        return smsSetMapper.editSmsSet(smsSet);
    }
}
