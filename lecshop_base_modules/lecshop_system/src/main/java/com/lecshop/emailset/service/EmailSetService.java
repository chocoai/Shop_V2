package com.lecshop.emailset.service;

import com.lecshop.emailset.bean.EmailSet;

/**
 * 邮箱接口设置service接口
 */
public interface EmailSetService {

    /**
     * 查询email设置
     *
     * @return email设置实体类
     */
    EmailSet queryEmailSet();

    /**
     * 编辑邮箱接口设置
     *
     * @param emailSet 邮箱接口设置实体类
     * @return 编辑返回码
     */
    int editEmailSet(EmailSet emailSet);
}
