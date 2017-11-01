package com.lecshop.distributionwithdrawals.mapper;

import com.lecshop.distributionwithdrawals.bean.Withdrawals;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 提现列表Mapper层
 *
 * Created by LecShop on 2017/5/24.
 */
@Repository
public interface WithdrawalsMapper {

    /**
     * 分页查询提现数据
     *
     * @param params 查询参数
     * @return       返回分销提现
     */
    List<Withdrawals> queryWithdrawals(Map<String, Object> params);

    /**
     * 查询提现的总记录数
     *
     * @param params 查询参数
     * @return 返回分销提现的总记录数
     */
    int queryWithdrawalsCount(Map<String, Object> params);

    /**
     * 拒绝提现申请
     *
     * @param withdrawals 提现申请信息
     * @return            成功返回1  失败返回0
     */
    int refuseApply(Withdrawals withdrawals);

    /**
     * 驳回提现申请
     *
     * @param withdrawals 提现申请信息
     * @return            成功返回1  失败返回0
     */
    int rejectApply(Withdrawals withdrawals);

    /**
     * 通过提现申请
     *
     * @param withdrawals 提现申请信息
     * @return            成功返回1  失败返回0
     */
    int passApply(Withdrawals withdrawals);
}
