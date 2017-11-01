package com.lecshop.customer.mapper;

import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.bean.StoreStaff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/19.
 * 会员数据库接口
 */
@Repository
public interface CustomerMapper {

    /**
     * 查询会员总数
     *
     * @param params 查询参数
     * @return 返回会员总数
     */
    int queryCustomerCount(Map<String, Object> params);

    /**
     * 查询会员数据
     *
     * @param params 查询参数
     * @return 返回会员数据
     */
    List<Customer> queryCustomers(Map<String, Object> params);

    /**
     * 根据会员id查询会员信息
     *
     * @param id 会员id
     * @return 返回会员信息
     */
    Customer queryCustomerById(long id);

    /**
     * 删除会员信息
     *
     * @param ids 会员集合
     * @return 成功返回>1 失败返回0
     */
    int deleteCustomers(List<Long> ids);

    /**
     * 编辑员工-更新员工status
     *
     * @param storeStaff 员工实体类
     * @return 编辑返回码
     */
    int updateStatus(StoreStaff storeStaff);

    /**
     * 添加会员
     *
     * @param customer 会员信息
     * @return 成功返回1 失败返回0
     */
    int addCustomer(Customer customer);

    /**
     * 修改会员信息
     *
     * @param customer 会员信息
     * @return 成功返回 1 失败返回0
     */
    int updateCustomer(Customer customer);

    /**
     * 商家登录
     *
     * @param username 用户名
     * @return 会员信息
     */
    Customer customerStoreLogin(String username);

    /**
     * 更新最后登录时间
     *
     * @param customerId 会员id
     * @return 更新返回码
     */
    int updateLoginTime(long customerId);

    /**
     * 查询店铺员工总条数
     *
     * @param map 查询条件
     * @return 店铺员工总条数
     */
    int queryStoreStallCount(Map<String, Object> map);

    /**
     * 分页查询店铺员工
     *
     * @param map 查询条件
     * @return 店铺员工
     */
    List<StoreStaff> queryStoreStall(Map<String, Object> map);

    /**
     * 添加会员,store添加员工
     *
     * @param storeStaff 会员信息
     * @return 返回添加主键id
     */
    int addStoreStaff(StoreStaff storeStaff);

    /**
     * 更新密码
     *
     * @param customer 会员实体类
     * @return 更新返回码
     */
    int updatePassWord(Customer customer);

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
     * 更新会员个人信息
     *
     * @param map 更新参数
     * @return 更新返回码
     */
    int updatePersonalInfo(Map<String, Object> map);
}
