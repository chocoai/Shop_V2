package com.lecshop.storerole.mapper;

import com.lecshop.storerole.bean.RoleAndCustomer;
import com.lecshop.storerole.bean.StoreRole;
import com.lecshop.storerole.bean.StoreRoleAndAuth;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色mapper层
 */
@Repository
public interface StoreRoleMapper {

    /**
     * 查询所有角色
     *
     * @param params 空查询所有,不为空按条件查询
     * @return 角色集合
     */
    List<StoreRole> queryAllStoreRole(Map<String, Object> params);

    /**
     * 查询所有角色条数
     *
     * @param params 查询条件
     * @return 条数
     */
    int queryStoreRoleCount(Map<String, Object> params);

    /**
     * 添加角色
     *
     * @param role 角色实体类
     * @return 返回添加主键ID
     */
    int addStoreRole(StoreRole role);

    /**
     * 添加角色权限关联
     *
     * @param list 角色ID
     * @return 返回添加结果
     */
    int addStoreRoleAndAuth(List<StoreRoleAndAuth> list);

    /**
     * 用于校验角色名称是否重复
     *
     * @param roleName 角色名称
     * @return 返回查询结果
     */
    StoreRole roleNameCheck(String roleName);

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
     * @param map 角色id,店铺id
     * @return 删除返回值
     */
    int deleteRole(Map<String, Object> map);

    /**
     * 根据角色id删除该角色下的所有权限
     *
     * @param idList 角色id
     * @return 返回删除结果
     */
    int deleteAllAuthByRoleId(long[] idList);

    /**
     * 编辑角色名称
     *
     * @param storeRole 角色实体类
     * @return 编辑返回值
     */
    int editRoleName(StoreRole storeRole);

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
