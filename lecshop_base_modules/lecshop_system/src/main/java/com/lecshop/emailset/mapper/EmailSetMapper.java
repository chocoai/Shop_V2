package com.lecshop.emailset.mapper;

import com.lecshop.emailset.bean.EmailSet;
import org.springframework.stereotype.Repository;

/**
 * 邮箱接口设置mapper
 */
@Repository
public interface EmailSetMapper {

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
