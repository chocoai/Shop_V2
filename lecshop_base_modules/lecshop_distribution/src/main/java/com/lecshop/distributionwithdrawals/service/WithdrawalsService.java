package com.lecshop.distributionwithdrawals.service;

import com.lecshop.distributionwithdrawals.bean.Withdrawals;
import com.lecshop.util.PageHelper;

/**
 * 提现列表service层
 *
 * Created by LecShop on 2017/5/24.
 */
public interface WithdrawalsService {

    /**
     *分页查询提现列表
     *
     * @param pageHelper 查询帮助类
     * @param tradeno    交易流水号
     * @param storeid    店铺id  平台的为0
     * @return           返回提现数据
     */
    PageHelper<Withdrawals> queryWithdrawals(PageHelper<Withdrawals> pageHelper,String tradeno,long storeid);

    /**
     * 拒绝提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId     店铺id
     * @return            成功返回1  失败返回0
     */
    int refuseApply(Withdrawals withdrawals, long storeId);

    /**
     * 驳回提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId     店铺id
     * @return            成功返回1  失败返回0
     */
    int rejectApply(Withdrawals withdrawals, long storeId);

    /**
     * 通过提现申请
     *
     * @param withdrawals 提现申请信息
     * @param storeId     店铺id
     * @return            成功返回1  失败返回0
     */
    int passApply(Withdrawals withdrawals, long storeId);

}
