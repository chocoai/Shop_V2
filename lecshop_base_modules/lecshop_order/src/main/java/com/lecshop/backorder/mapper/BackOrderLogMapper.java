package com.lecshop.backorder.mapper;

import com.lecshop.backorder.bean.BackOrderLog;

import java.util.List;

/**
 * Created by dujinkai on 17/6/6.
 * 退单日志数据库接口
 */
public interface BackOrderLogMapper {

    /**
     * 根据退单id 查询退单日志
     *
     * @param bacKOrderId 退单id
     * @return 返回退单日志
     */
    List<BackOrderLog> queryByBackOrderId(long bacKOrderId);

    /**
     * 新增退单日志
     *
     * @param backOrderLog 退单日志
     * @return 成功返回1 失败返回0
     */
    int addBackOrderLog(BackOrderLog backOrderLog);
}
