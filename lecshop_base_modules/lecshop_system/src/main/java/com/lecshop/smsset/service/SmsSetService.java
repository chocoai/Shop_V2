package com.lecshop.smsset.service;

import com.lecshop.smsset.bean.SmsSet;

/**
 * 短信设置service接口
 */
public interface SmsSetService {
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
     * @return -1实体类为空 1编辑成功
     */
    int editSmsSet(SmsSet smsSet);
}
