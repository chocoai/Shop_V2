package com.lecshop.distributionwithdrawals.service.impl;

import com.lecshop.distributionwithdrawals.bean.Withdrawals;
import com.lecshop.distributionwithdrawals.mapper.WithdrawalsMapper;
import com.lecshop.distributionwithdrawals.service.WithdrawalsService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 提现列表service实现类
 * <p>
 * Created by LecShop on 2017/5/24.
 */
@Service
public class WithdrawalsServiceImpl implements WithdrawalsService {

    /**
     * 注入提现列表数据库接口
     */
    @Autowired
    WithdrawalsMapper withdrawalsMapper;

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(WithdrawalsServiceImpl.class);

    /**
     * 分页查询提现列表
     *
     * @param pageHelper 查询帮助类
     * @param tradeno    交易流水号
     * @param storeId    店铺id  平台的为0
     * @return 返回提现数据
     */
    @Override
    public PageHelper<Withdrawals> queryWithdrawals(PageHelper<Withdrawals> pageHelper, String tradeno, long storeId) {
        logger.debug("querywithdrawals and pagehelper :{} \r\n tradeno :{} \r\n storeId", pageHelper, tradeno, storeId);
        Map<String, Object> params = new HashMap();
        params.put("tradeno", tradeno);
        params.put("storeId", storeId);
        return pageHelper.setListDates(withdrawalsMapper.queryWithdrawals(pageHelper.getQueryParams(params, withdrawalsMapper.queryWithdrawalsCount(params))));
    }

    /**
     * 拒绝提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId 店铺id
     * @return 成功返回1  失败返回0
     */
    @Override
    public int refuseApply(Withdrawals withdrawals, long storeId) {
        withdrawals.setStatus("3");
        withdrawals.setStoreId(storeId);
        return withdrawalsMapper.refuseApply(withdrawals);
    }

    /**
     * 驳回提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId 店铺id
     * @return 成功返回1  失败返回0
     */
    @Override
    public int rejectApply(Withdrawals withdrawals, long storeId) {
        withdrawals.setStatus("3");
        withdrawals.setStoreId(storeId);
        return withdrawalsMapper.rejectApply(withdrawals);
    }

    /**
     * 通过提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId 店铺id
     * @return 成功返回1  失败返回0
     */
    @Override
    public int passApply(Withdrawals withdrawals, long storeId) {
        withdrawals.setStatus("1");
        withdrawals.setStoreId(storeId);
        return withdrawalsMapper.passApply(withdrawals);
    }

}
