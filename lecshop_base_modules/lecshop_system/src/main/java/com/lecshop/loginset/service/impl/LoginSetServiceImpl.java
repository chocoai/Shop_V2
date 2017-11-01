package com.lecshop.loginset.service.impl;

import com.lecshop.loginset.bean.LoginSet;
import com.lecshop.loginset.bean.LoginSetCommon;
import com.lecshop.loginset.mapper.LoginSetMapper;
import com.lecshop.loginset.service.LoginSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 登录接口设置service实现类
 */
@Service
public class LoginSetServiceImpl implements LoginSetService {

    /**
     * 调试日式
     */
    private Logger logger = LoggerFactory.getLogger(LoginSetServiceImpl.class);

    /**
     * 注入登录接口设置mapper
     */
    @Autowired
    private LoginSetMapper loginSetMapper;

    /**
     * 查询登录接口
     *
     * @return 登录接口设置实体类
     */
    @Override
    public LoginSetCommon queryLoginSet() {
        LoginSetCommon loginSetCommon = new LoginSetCommon();
        loginSetMapper.queryLoginSet().forEach(loginSet -> {
            String value = loginSet.getColumnValue();
            loginSetCommon.getData(loginSetCommon, loginSet, value);
        });
        return loginSetCommon;
    }

    /**
     * 编辑登录接口设置
     *
     * @param key      AppId
     * @param value    AppSecret
     * @param url      回调地址
     * @param isUse    是否启用
     * @param codeType 1微博 2QQ 3微信
     * @return -1 无法修改 1修改成功
     */
    @Override
    @Transactional
    public int editLoginSet(String key, String value, String url, String isUse, int codeType) {
        if (!(codeType == 1 || codeType == 2 || codeType == 3)) {
            logger.error("editLoginSet error due to codeType is illegal");
            return -1;
        }
        loginSetMapper.deleteLoginSet(codeType);
        List<LoginSet> list = new ArrayList<>();
        if (codeType == 1) {
            list.add(LoginSet.getLoginSet(codeType, "AppKey", key));
            list.add(LoginSet.getLoginSet(codeType, "AppSecret", value));
        }
        if (codeType == 2) {
            list.add(LoginSet.getLoginSet(codeType, "AppID", key));
            list.add(LoginSet.getLoginSet(codeType, "AppKey", value));
        }
        if (codeType == 3) {
            list.add(LoginSet.getLoginSet(codeType, "AppID", key));
            list.add(LoginSet.getLoginSet(codeType, "AppSecret", value));
        }
        list.add(LoginSet.getLoginSet(codeType, "url", url));
        list.add(LoginSet.getLoginSet(codeType, "isUse", isUse));
        return loginSetMapper.addLoginSet(list);
    }
}
