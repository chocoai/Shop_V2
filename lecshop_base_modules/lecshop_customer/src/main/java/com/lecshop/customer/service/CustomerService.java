package com.lecshop.customer.service;

import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.bean.StoreStaff;
import com.lecshop.customer.bean.CustomerSearchCondition;
import com.lecshop.util.PageHelper;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/5/19.
 * 会员服务接口
 */
public interface CustomerService {

    /**
     * 分页查询会员信息
     *
     * @param pageHelper              分页帮助类
     * @param customerSearchCondition 搜索条件
     * @return 返回会员信息
     */
    PageHelper<Customer> queryCustomers(PageHelper<Customer> pageHelper, CustomerSearchCondition customerSearchCondition);

    /**
     * 根据会员id查询会员信息(没有密码信息)
     *
     * @param id 会员id
     * @return 返回会员信息
     */
    Customer queryCustomerWithNoPasswordById(long id);

    /**
     * 根据店铺id查询会员信息和会员等级
     *
     * @param id 会员id
     * @return 会员信息
     */
    Customer queryCustomerWithCustomerLevel(long id);

    /**
     * 删除会员信息
     *
     * @param ids 会员id集合
     * @return 成功>1  失败返回0
     */
    int deleteCustomers(List<Long> ids);

    /**
     * 新增会员信息
     *
     * @param customer 会员
     * @return 成功返回1 失败返回0
     */
    int addCustomer(Customer customer);

    /**
     * 修改会员信息
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    int updateCustomer(Customer customer);

    /**
     * 商家登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return 返回码  0 输入为空 -1 验证码不正确 -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
     */
    int customerStoreLogin(String username, String password, String code, String codeInSession, Consumer<Customer> consumer);

    /**
     * 锁屏页登录
     *
     * @param username 用户名
     * @param password 密码
     * @return -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
     */
    int storeLockLogin(String username, String password, Consumer<Customer> consumer);

    /**
     * 更新会员密码
     *
     * @param customer      会员实体类
     * @param oldPassword   原始密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入
     * @return 0 未登录 -1 输入为空 1 成功过 2 两次输入不一致 3 原密码不正确
     */
    int updateCustomerPassWord(Customer customer, String oldPassword, String newPassword, String reNewPassword);

    /**
     * 分页查询会员信息
     *
     * @param pageHelper 分页帮助类
     * @param storeId    店铺id
     * @return 返回会员信息
     */
    PageHelper<StoreStaff> queryStoreStall(PageHelper<StoreStaff> pageHelper, long storeId, String name);

    /**
     * 添加店铺员工
     *
     * @param storeStaff 员工实体类
     * @param storeId    店铺id
     * @return 添加返回值 主键id
     */
    int addStoreStaff(StoreStaff storeStaff, long storeId);

    /**
     * 删除会员和关联的角色
     *
     * @param customerIds 会员id
     * @return 删除返回码
     */
    int delCustomersAndStaffs(Long[] customerIds);

    /**
     * 编辑会员和关联的角色
     *
     * @param storeStaff 员工实体类
     * @return 删除返回码
     */
    int editCustomerAndStaff(StoreStaff storeStaff);

    /**
     * 更新会员storeId和type
     *
     * @param customer 会员实体类
     * @return 添加返回码
     */
    int updateStoreIdAndType(Customer customer);

    /**
     * 根据店铺id查询会员id
     *
     * @param storeId 店铺id
     * @return 会员信息
     */
    Customer queryCustomerIdByStoreId(long storeId);

    /**
     *
     *
     * @param map 更新参数
     * @return 更新返回码
     */
    /**
     * 更新会员个人信息
     *
     * @param customer 会员实体类
     * @param param
     * @return
     */
    int updatePersonalInfo(Customer customer, int param);
}
