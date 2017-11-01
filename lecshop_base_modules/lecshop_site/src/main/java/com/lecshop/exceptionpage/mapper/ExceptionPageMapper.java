package com.lecshop.exceptionpage.mapper;

import com.lecshop.exceptionpage.bean.ExceptionPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 异常页面mapper层
 *
 * @author sunluyang on 2017/5/19.
 */
@Repository
public interface ExceptionPageMapper {

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
     * @return 返回编辑行数
     */
    int editExceptionPage(ExceptionPage exceptionPage);
}
