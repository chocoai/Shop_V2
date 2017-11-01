package com.lecshop.exceptionpage.service;

import com.lecshop.exceptionpage.bean.ExceptionPage;

import java.util.List;

/**
 * 异常页面service接口
 *
 * @author sunluyang on 2017/5/19.
 */
public interface ExceptionPageService {
    /**
     * 查询异常页面
     *
     * @return 返回异常页面集合
     */
    List<ExceptionPage> queryExceptionPage();

    /**
     * 编辑异常页面
     *
     * @param exceptionPage 异常页面实体类
     * @return -1编辑失败 1编辑成功
     */
    int editExceptionPage(ExceptionPage exceptionPage);
}
