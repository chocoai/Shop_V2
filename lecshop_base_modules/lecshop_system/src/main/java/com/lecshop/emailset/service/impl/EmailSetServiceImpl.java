package com.lecshop.emailset.service.impl;

import com.lecshop.emailset.bean.EmailSet;
import com.lecshop.emailset.mapper.EmailSetMapper;
import com.lecshop.emailset.service.EmailSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 邮箱接口设置service实现类
 */
@Service
public class EmailSetServiceImpl implements EmailSetService {
    /**
     * 调试日式
     */
    private Logger logger = LoggerFactory.getLogger(EmailSetServiceImpl.class);

    /**
     * 邮箱设置mapper
     */
    @Autowired
    private EmailSetMapper emailSetMapper;

    /**
     * 查询email设置
     *
     * @return email设置实体类
     */
    @Override
    public EmailSet queryEmailSet() {
        logger.debug("queryEmailSet...");
        return emailSetMapper.queryEmailSet();
    }

    /**
     * 编辑邮箱接口设置
     *
     * @param emailSet 邮箱接口设置实体类
     * @return -1 email为空
     */
    @Override
    public int editEmailSet(EmailSet emailSet) {
        if (Objects.isNull(emailSet)) {
            logger.error("editEmailSet error due to editEmailSet is null");
            return -1;
        }
        return emailSetMapper.editEmailSet(emailSet);
    }
}
