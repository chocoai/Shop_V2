package com.lecshop.backorder.service;

import com.lecshop.backorder.bean.BackOrderLog;

import java.util.List;

/**
 * Created by dujinkai on 17/6/6.
 * 退单操作日志服务接口
 */
public interface BackOrderLogService {

    /**
     * 根据退单id查询退单日志
     *
     * @param bacKOrderId 退单id
     * @return 返回退单日志
     */
    List<BackOrderLog> queryByBackOrderId(long bacKOrderId);

    /**
     * 新增退单日志
     *
     * @param backOrderLog 退单日志
     * @return 成功返回1 失败返回 0
     */
    int addBackOrderLog(BackOrderLog backOrderLog);
}
