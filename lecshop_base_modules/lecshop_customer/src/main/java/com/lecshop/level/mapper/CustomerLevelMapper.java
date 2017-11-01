package com.lecshop.level.mapper;

import com.lecshop.level.bean.CustomerLevel;

import java.util.List;

/**
 * Created by dujinkai on 17/5/22.
 * 会员等级数据库接口
 */
public interface CustomerLevelMapper {

    /**
     * 查询所有会员等级
     *
     * @return 返回会员等级信息
     */
    List<CustomerLevel> queryAllCustomerLevels();

    /**
     * 新增会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回 1 失败返回0
     */
    int addCustomerLevel(CustomerLevel customerLevel);

    /**
     * 根据id删除会员等级
     *
     * @param id 会员等级id
     * @return 成功返回1 失败会0
     */
    int deleteCustomerLevel(long id);

    /**
     * 根据id查询会员等级信息
     *
     * @param id 会员等级id
     * @return 返回会员等级信息
     */
    CustomerLevel queryCustomerLevelById(long id);

    /**
     * 修改会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回1 失败返回0
     */
    int updateCustomerLevel(CustomerLevel customerLevel);
}
