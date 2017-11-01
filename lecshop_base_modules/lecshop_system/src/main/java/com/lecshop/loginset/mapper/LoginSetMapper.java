package com.lecshop.loginset.mapper;

import com.lecshop.loginset.bean.LoginSet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录接口设置mapper层
 */
@Repository
public interface LoginSetMapper {

    /**
     * 查询登录接口
     *
     * @return 登录接口设置实体类
     */
    List<LoginSet> queryLoginSet();

    /**
     * 删除登录设置
     *
     * @param codeType 登录方式 1 微博 2 QQ 3 微信
     * @return 返回删除结果
     */
    int deleteLoginSet(int codeType);

    /**
     * 批量插入登录设置
     *
     * @param loginSets 登录设置集合
     * @return 插入的行数
     */
    int addLoginSet(List<LoginSet> loginSets);
}
