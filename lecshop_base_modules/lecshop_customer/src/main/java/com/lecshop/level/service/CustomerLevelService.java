package com.lecshop.level.service;

import com.lecshop.level.bean.CustomerLevel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dujinkai on 17/5/22.
 * 会员等级服务接口
 */
public interface CustomerLevelService {

    /**
     * 查询所有的会员等级
     *
     * @return 返回所有的会员等级
     */
    List<CustomerLevel> queryAllCustomerLevels();

    /**
     * 新增会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回 1 失败返回0 -1 消费金额段已经存在 添加失败
     */
    int addCustomerLevel(CustomerLevel customerLevel);

    /**
     * 根据id删除会员等级
     *
     * @param id 会员等级id
     * @return 成功返回1 失败返回0
     */
    int deleteCustomerLevel(long id);

    /**
     * 根据id查询会员等级
     *
     * @param id 会员等级id
     * @return 会员等级信息返回
     */
    CustomerLevel queryCustomerLevelById(long id);

    /**
     * 修改会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回1 失败返回0  -1 消费金额段已经存在 修改
     */
    int updateCustomerLevel(CustomerLevel customerLevel);

    /**
     * 根据消费金额计算出会员等级
     *
     * @param money 消费金额
     * @return 返回会员等级
     */
    CustomerLevel queryCustomerLevelByMoney(BigDecimal money);
}
