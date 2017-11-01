package com.lecshop.customer.service.impl;

import com.lecshop.customer.bean.Customer;
import com.lecshop.customer.bean.StoreStaff;
import com.lecshop.customer.bean.CustomerSearchCondition;
import com.lecshop.customer.mapper.CustomerMapper;
import com.lecshop.customer.service.CustomerService;
import com.lecshop.level.service.CustomerLevelService;
import com.lecshop.openstore.bean.StoreInfo;
import com.lecshop.openstore.service.StoreInfoService;
import com.lecshop.predeposit.service.PredepositRecordService;
import com.lecshop.storerole.bean.RoleAndCustomer;
import com.lecshop.storerole.service.StoreRoleService;
import com.lecshop.util.MD5Utils;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/5/19.
 * 会员服务接口实现
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * 注入会员数据库接口
     */
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 注入预存款服务接口
     */
    @Autowired
    private PredepositRecordService predepositRecordService;

    /**
     * 注入会员等级服务接口
     */
    @Autowired
    private CustomerLevelService customerLevelService;

    /**
     * 注入店铺角色service
     */
    @Autowired
    private StoreRoleService storeRoleService;

    /**
     * 注入店铺信息service
     */
    @Autowired
    private StoreInfoService storeInfoService;

    @Override
    public PageHelper<Customer> queryCustomers(PageHelper<Customer> pageHelper, CustomerSearchCondition customerSearchCondition) {
        logger.debug("queryCustomers and pageHelper:{} \r\n customerSearchCondition:{}", pageHelper, customerSearchCondition);
        // 查询参数
        Map<String, Object> params = customerSearchCondition.getSearchParams();
        return pageHelper.setListDates(clearPassword(setCustomerLevelName(setCustomerPredeposit(customerMapper.queryCustomers(pageHelper.getQueryParams(params, customerMapper.queryCustomerCount(params)))))));
    }

    @Override
    public Customer queryCustomerWithNoPasswordById(long id) {
        logger.debug("queryCustomerById and id:{}", id);
        return customerMapper.queryCustomerById(id).clearPassword();
    }

    @Override
    public Customer queryCustomerWithCustomerLevel(long id) {
        Customer customer = customerMapper.queryCustomerById(id).clearPassword();
        customer.setCustomerLevel(customerLevelService.queryCustomerLevelByMoney(customer.getConsumptionAmount()));
        return customer;
    }


    @Override
    public int deleteCustomers(List<Long> ids) {
        logger.debug("deleteCustomers and ids:{}", ids);
        if (CollectionUtils.isEmpty(ids)) {
            logger.error("deleteCustomers fail due to ids is null....");
            return 0;
        }
        return customerMapper.deleteCustomers(ids);
    }

    @Override
    public int addCustomer(Customer customer) {
        logger.debug("addCustomer and customer :{}", customer);
        customer.setPassword(MD5Utils.getInstance().createMd5(customer.getPassword()));
        return customerMapper.addCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        logger.debug("updateCustomer and customer:{}", customer);
        if (Objects.isNull(customer)) {
            logger.error("updateCustomer fail due to customer is null...");
            return 0;
        }
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 商家登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return 返回码 0 输入为空 -1 验证码不正确 -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 -6 店铺关闭  1 成功
     */
    @Override
    public int customerStoreLogin(String username, String password, String code, String codeInSession, Consumer<Customer> consumer) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            logger.error("store login fail due to params is error.....");
            return 0;
        }
        if (!code.equals(codeInSession)) {
            logger.error("store login fail due to code is error.....");
            return -1;
        }
        Customer customer = customerMapper.customerStoreLogin(username);
        if (Objects.isNull(customer)) {
            logger.error("store login fail due to customer is not exit.....");
            return -2;
        }
        if (!customer.isPasswordRight(password)) {
            logger.error("store login fail due to password is error.....");
            return -3;
        }
        if ("2".equals(customer.getStatus())) {//1 正常 2冻结 3未启用
            logger.error("store login fail due to customer is frozen.....");
            return -4;
        }
        if ("3".equals(customer.getStatus())) {
            logger.error("store login fail due to customer is not use.....");
            return -5;
        }
        StoreInfo storeInfo = storeInfoService.queryStoreInfo(customer.getStoreId());
        if (!Objects.isNull(storeInfo)) {
            if ("4".equals(storeInfo.getStatus())) {
                logger.error("store login fail due to store is close.....");
                return -6;
            }
        }
        // 登录成功 进行回调
        customer.clearPassword();
        consumer.accept(customer);
        return customerMapper.updateLoginTime(customer.getId());
    }

    /**
     * 锁屏页登录
     *
     * @param username 用户名
     * @param password 密码
     * @return -2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
     */
    @Override
    public int storeLockLogin(String username, String password, Consumer<Customer> consumer) {
        // 根据用户名查询管理员信息
        Customer customer = customerMapper.customerStoreLogin(username);
        // 用户不存在 直接返回
        if (Objects.isNull(customer)) {
            return -2;
        }
        // 判断密码是否正确  如果不正确 则返回
        if (!customer.isPasswordRight(password)) {
            return -3;
        }
        if ("2".equals(customer.getStatus())) {//1 正常 2冻结 3未启用
            return -4;
        }
        if ("3".equals(customer.getStatus())) {
            return -5;
        }
        StoreInfo storeInfo = storeInfoService.queryStoreInfo(customer.getStoreId());
        if (!Objects.isNull(storeInfo)) {
            if ("4".equals(storeInfo.getStatus())) {
                logger.error("store login fail due to store is close.....");
                return -6;
            }
        }
        // 登录成功 进行回调
        customer.clearPassword();
        consumer.accept(customer);
        return customerMapper.updateLoginTime(customer.getId());
    }

    /**
     * 更新用户密码
     *
     * @param customer      会员实体类
     * @param oldPassword   原始密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入
     * @return 0 未登录 -1 输入为空 1 成功过 2 两次输入不一致 3 原密码不正确
     */
    @Override
    public int updateCustomerPassWord(Customer customer, String oldPassword, String newPassword, String reNewPassword) {
        if (Objects.isNull(customer)) {
            return 0;
        }
        //判断是否问空
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(reNewPassword)) {
            return -1;
        }
        if (!newPassword.equals(reNewPassword)) {
            return 2;
        }
        // 根据用户名查询管理员信息queryManagerByName
        Customer queryCustomer = customerMapper.queryCustomerById(customer.getId());
        //判断原密码是否正确
        if (!queryCustomer.isPasswordRight(oldPassword)) {//oldPassword
            return 3;
        }
        customer.setPassword(MD5Utils.getInstance().createMd5(newPassword));
        return customerMapper.updatePassWord(customer);
    }

    /**
     * 查询店铺员工
     *
     * @param pageHelper 分页帮助类
     * @param storeId    店铺id
     * @return 店铺员工信息
     */
    @Override
    public PageHelper<StoreStaff> queryStoreStall(PageHelper<StoreStaff> pageHelper, long storeId, String name) {
        logger.debug("queryCustomers and pageHelper:{} \r\n customerSearchCondition:{}", pageHelper, storeId);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("name", name);
        return pageHelper.setListDates(customerMapper.queryStoreStall(pageHelper.getQueryParams(params, customerMapper.queryStoreStallCount(params))));
    }

    /**
     * 添加员工会员
     *
     * @param storeStaff 员工实体类
     * @return 添加返回码
     */
    @Override
    @Transactional
    public int addStoreStaff(StoreStaff storeStaff, long storeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("name", "");
        if (customerMapper.queryStoreStallCount(params) >= 50) {
            return -1;
        }
        //添加会员-员工
        customerMapper.addStoreStaff(storeStaff.getAddStoreStaffData(storeStaff, storeId, "3"));
        //添加员工关联角色
        return storeRoleService.linkStaffRole(new RoleAndCustomer().getRoleAndCustomer(storeStaff.getId(), storeStaff.getRoleId()));
    }

    /**
     * 删除会员和关联的角色
     *
     * @param customerIds 会员id
     * @return 删除返回码
     */
    @Override
    @Transactional
    public int delCustomersAndStaffs(Long[] customerIds) {
        if (ArrayUtils.isEmpty(customerIds)) {
            return -1;
        }
        //删除会员
        customerMapper.deleteCustomers(Arrays.asList(customerIds));
        //删除关联的角色
        return storeRoleService.deleteStoreStaff(Arrays.asList(customerIds));
    }

    /**
     * 编辑员工
     *
     * @param storeStaff 员工实体类
     * @return 编辑返回码
     */
    @Override
    public int editCustomerAndStaff(StoreStaff storeStaff) {
        storeRoleService.updateRoleId(new RoleAndCustomer().getRoleAndCustomer(storeStaff.getId(), storeStaff.getRoleId()));
        return customerMapper.updateStatus(storeStaff);
    }

    /**
     * 更新会员storeId和type
     *
     * @param customer 会员实体类
     * @return 添加返回码
     */
    @Override
    public int updateStoreIdAndType(Customer customer) {
        return customerMapper.updateStoreIdAndType(customer);
    }

    /**
     * 根据店铺id查询会员id
     *
     * @param storeId 店铺id
     * @return 会员信息
     */
    @Override
    public Customer queryCustomerIdByStoreId(long storeId) {
        return customerMapper.queryCustomerIdByStoreId(storeId);
    }

    /**
     * 更新会员个人信息
     *
     * @param customer 会员实体类
     * @param param    参数
     * @return 编辑返回码
     */
    @Override
    public int updatePersonalInfo(Customer customer, int param) {
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
        map.put("customer", customer);
        return customerMapper.updatePersonalInfo(map);
    }

    /**
     * 清除用户密码
     *
     * @param customers 用户信息
     * @return 返回用户信息
     */
    private List<Customer> clearPassword(List<Customer> customers) {
        customers.parallelStream().forEach(customer -> customer.clearPassword());
        return customers;
    }

    /**
     * 设置会员等级
     *
     * @param customers 会员信息
     * @return 返回会员信息
     */
    private List<Customer> setCustomerLevelName(List<Customer> customers) {
        customers.parallelStream().forEach(customer -> customer.setCustomerLevel(customerLevelService.queryCustomerLevelByMoney(customer.getConsumptionAmount())));
        return customers;
    }

    /**
     * 设置会员的预存款总金额
     *
     * @param customers 会员信息
     * @return 返回会员的信息
     */
    private List<Customer> setCustomerPredeposit(List<Customer> customers) {
        customers.parallelStream().forEach(customer -> customer.setAllPredeposit(predepositRecordService.queryCutomerAllPredeposit(customer.getId())));
        return customers;
    }
}
