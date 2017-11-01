package com.lecshop.smsset.mapper;

import com.lecshop.smsset.bean.SmsSet;
import org.springframework.stereotype.Repository;

/**
 * 短信接口设置mapper层
 */
@Repository
public interface SmsSetMapper {

    /**
     * 查询短信设置
     *
     * @return 短信实体类
     */
    SmsSet querySmsSet();

    /**
     * 编辑短信设置
     *
     * @param smsSet 短信设置实体类
     * @return 编辑返回码
     */
    int editSmsSet(SmsSet smsSet);
}
