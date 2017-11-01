package com.lecshop.storerole.service;

import com.lecshop.storemenu.bean.StoreEditMenu;
import com.lecshop.storerole.bean.RoleAndCustomer;
import com.lecshop.storerole.bean.StoreRole;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * 角色Service层接口
 */
public interface StoreRoleService {
    /**
     * 查询所有角色
     *
     * @param roleName 空查询所有,不为空按条件查询
     * @param isPaging 是否分页 1需要 0不需要
     * @return 角色集合
     */
    PageHelper<StoreRole> queryAllStoreRole(PageHelper<StoreRole> pageHelper, String roleName, int isPaging, long storeId);

    /**
     * 根据用户Id查询角色菜单
     *
     * @param customerId 会员Id
     * @return 菜单
     */
    List<StoreEditMenu> storeRoleAuthMenu(long customerId);

    /**
     * 添加角色并关联角色权限
     *
     * @param roleName 角色实体类
     * @param authIds  权限Id
     * @param storeId  店铺id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    int addStoreRole(String roleName, long[] authIds, long storeId);

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    List<Long> queryAuthIdByRoleId(long roleId);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    int deleteRole(long[] roleId, long storeId);

    /**
     * 编辑角色
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @param storeId  店铺id
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    int editRole(long roleId, String roleName, long[] authIds, long storeId);

    /**
     * 查询店铺角色用于添加员工
     *
     * @param storeId 店铺id
     * @return 店铺角色集合
     */
    List<StoreRole> queryStoreRoleForAddStaff(long storeId);

    /**
     * 添加员工进行添加角色和会员关联表
     *
     * @param roleAndCustomer 实体类
     * @return 添加返回码
     */
    int linkStaffRole(RoleAndCustomer roleAndCustomer);

    /**
     * 批量删除员工的角色关联数据
     *
     * @param customerIds 员工id
     * @return 删除返回码
     */
    int deleteStoreStaff(List<Long> customerIds);

    /**
     * 编辑员工-编辑员工关联的角色id
     *
     * @param roleAndCustomer 实体类
     * @return 编辑返回码
     */
    int updateRoleId(RoleAndCustomer roleAndCustomer);
}
