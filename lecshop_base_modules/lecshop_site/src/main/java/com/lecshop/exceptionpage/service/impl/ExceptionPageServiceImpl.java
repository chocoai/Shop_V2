package com.lecshop.exceptionpage.service.impl;

import com.lecshop.exceptionpage.bean.ExceptionPage;
import com.lecshop.exceptionpage.mapper.ExceptionPageMapper;
import com.lecshop.exceptionpage.service.ExceptionPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 异常页面service实现类
 *
 * @author sunluyang on 2017/5/19.
 */
@Service
public class ExceptionPageServiceImpl implements ExceptionPageService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ExceptionPageServiceImpl.class);

    @Autowired
    private ExceptionPageMapper exceptionPageMapper;

    /**
     * 查询异常页面
     *
     * @return 返回异常页面集合
     */
    @Override
    public List<ExceptionPage> queryExceptionPage() {
        logger.debug("queryExceptionPage...");
        return exceptionPageMapper.queryExceptionPage();
    }

    /**
     * 编辑异常页面
     *
     * @param exceptionPage 异常页面实体类
     * @return -1编辑失败 1编辑成功
     */
    @Override
    public int editExceptionPage(ExceptionPage exceptionPage) {
        if (Objects.isNull(exceptionPage)) {
            logger.error("editExceptionPage error due to exceptionPage is empty...");
            return -1;
        }
        return exceptionPageMapper.editExceptionPage(exceptionPage);
    }
}
