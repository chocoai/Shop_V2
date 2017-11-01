package com.lecshop.storerole.service.impl;

import com.lecshop.storemenu.bean.StoreEditMenu;
import com.lecshop.storemenu.bean.StoreMenu;
import com.lecshop.storemenu.service.StoreMenuService;
import com.lecshop.storerole.bean.RoleAndCustomer;
import com.lecshop.storerole.bean.StoreRole;
import com.lecshop.storerole.bean.StoreRoleAndAuth;
import com.lecshop.storerole.mapper.StoreRoleMapper;
import com.lecshop.storerole.service.StoreRoleService;
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

/**
 * 角色Service层
 */
@Service
public class StoreRoleServiceImpl implements StoreRoleService {

    /**
     * 注入店铺角色mapper
     */
    @Autowired
    private StoreRoleMapper storeRoleMapper;
    /**
     * 注入店铺菜单service
     */
    @Autowired
    private StoreMenuService storeMenuService;

    /**
     * 调试日式
     */
    private Logger Logger = LoggerFactory.getLogger(StoreRoleServiceImpl.class);

    /**
     * 分页查询所有店铺角色
     *
     * @param pageHelper 分页帮助类
     * @param roleName   空查询所有,不为空按条件查询
     * @param isPaging   是否分页 1需要 0不需要
     * @return 店铺角色分类
     */
    @Override
    public PageHelper<StoreRole> queryAllStoreRole(PageHelper<StoreRole> pageHelper, String roleName, int isPaging, long storeId) {
        Logger.debug("queryAllRole and pageHelper : {} \r\n name: {} \r\n isPaging{}", pageHelper, roleName, isPaging);
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        params.put("isPaging", isPaging);
        params.put("storeId", storeId);
        return pageHelper.setListDates(storeRoleMapper.queryAllStoreRole(pageHelper.getQueryParams(params, storeRoleMapper.queryStoreRoleCount(params))));
    }

    /**
     * 根据用户Id查询角色菜单
     *
     * @param customerId 会员Id
     * @return 菜单
     */
    @Override
    public List<StoreEditMenu> storeRoleAuthMenu(long customerId) {
        Logger.debug("storeRoleAuthMenu and customerId : {}", customerId);
        List<StoreMenu> storeMenuList = storeMenuService.storeMenuCommon(customerId, false);
        List<StoreEditMenu> storeEditMenuList = new ArrayList();
        for (StoreMenu storeMenu : storeMenuList) {
            storeEditMenuList.add(StoreEditMenu.getAdminEditMenu(storeMenu.getAuthorityId(), storeMenu.getParentId(), storeMenu.getName()));
            getNum(storeMenu, storeEditMenuList);
        }
        return storeEditMenuList;
    }

    /**
     * 添加角色并关联角色权限
     *
     * @param roleName 角色实体类
     * @param authIds  权限Id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    @Override
    @Transactional
    public int addStoreRole(String roleName, long[] authIds, long storeId) {
        Logger.debug("addStoreRole and roleName : {} \r\n authIds: {}\r\n storeId:{}", roleName, authIds, storeId);
        if (ArrayUtils.isEmpty(authIds)) {
            Logger.error("addStoreRole error due to authIds is empty");
            return -2;
        }
        if (!Objects.isNull(storeRoleMapper.roleNameCheck(roleName))) {
            Logger.error("addStoreRole error due to roleName is null");
            return -1;
        }
        StoreRole storeRole = new StoreRole();
        storeRole.setRoleName(roleName);
        storeRole.setStoreId(storeId);
        storeRoleMapper.addStoreRole(storeRole);
        List<StoreRoleAndAuth> list = new ArrayList();
        Arrays.stream(authIds).forEach(authId -> {
            StoreRoleAndAuth roleAndAuth = new StoreRoleAndAuth();
            roleAndAuth.setRoleId(storeRole.getId());
            roleAndAuth.setId(authId);
            list.add(roleAndAuth);
        });
        return storeRoleMapper.addStoreRoleAndAuth(list);
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @Override
    public List<Long> queryAuthIdByRoleId(long roleId) {
        Logger.debug("queryAuthIdByRoleId and roleId:{}", roleId);
        return storeRoleMapper.queryAuthIdByRoleId(roleId);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    @Override
    public int deleteRole(long[] roleId, long storeId) {
        Logger.debug("deleteRole and roleId:{}", roleId);
        if (ArrayUtils.isEmpty(roleId)) {
            Logger.error("deleteRole error roleId is empty");
            return -1;
        }
        //删除角色权限关联表中数据
        storeRoleMapper.deleteAllAuthByRoleId(roleId);
        //删除角色
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("idArrays", roleId);
        return storeRoleMapper.deleteRole(map);
    }

    /**
     * 编辑角色
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    @Override
    @Transactional
    public int editRole(long roleId, String roleName, long[] authIds, long storeId) {
        Logger.debug("editRole and roleId : {} \r\n authIds: {}\r\n roleName:{}\r\n storeId:{}", roleId, authIds, roleName, storeId);
        if (ArrayUtils.isEmpty(authIds)) {
            Logger.error("editRole error due to authIds is empty");
            return -2;
        }
        long[] array = {roleId};
        storeRoleMapper.deleteAllAuthByRoleId(array);
        List<StoreRoleAndAuth> list = new ArrayList();
        Arrays.stream(authIds).forEach(authId -> {
            StoreRoleAndAuth roleAndAuth = new StoreRoleAndAuth();
            roleAndAuth.setRoleId(roleId);
            roleAndAuth.setId(authId);
            list.add(roleAndAuth);
        });
        //如果角色名称为空则不进行角色表更新操作
        if (!StringUtils.isEmpty(roleName)) {
            StoreRole roleCheck = storeRoleMapper.roleNameCheck(roleName);
            if (!Objects.isNull(roleCheck) && roleCheck.getId() != roleId) {
                Logger.error("editRole error roleName is exit");
                return -1;
            }
            StoreRole storeRole = new StoreRole();
            storeRole.setId(roleId);
            storeRole.setRoleName(roleName);
            storeRole.setStoreId(storeId);
            storeRoleMapper.editRoleName(storeRole);
        }
        return storeRoleMapper.addStoreRoleAndAuth(list);
    }

    /**
     * 查询店铺角色用于添加员工
     *
     * @param storeId 店铺id
     * @return 店铺角色集合
     */
    @Override
    public List<StoreRole> queryStoreRoleForAddStaff(long storeId) {
        Logger.debug("queryStoreRoleForAddStaff and storeId:{}", storeId);
        return storeRoleMapper.queryStoreRoleForAddStaff(storeId);
    }

    /**
     * 添加员工进行添加角色和会员关联表
     *
     * @param roleAndCustomer 实体类
     * @return 添加返回码
     */
    @Override
    public int linkStaffRole(RoleAndCustomer roleAndCustomer) {
        Logger.debug("linkStaffRole and roleAndCustomer:{}", roleAndCustomer);
        return storeRoleMapper.linkStaffRole(roleAndCustomer);
    }

    /**
     * 批量删除员工的角色关联数据
     *
     * @param customerIds 员工id
     * @return 删除返回码
     */
    @Override
    public int deleteStoreStaff(List<Long> customerIds) {
        Logger.debug("deleteStoreStaff and customerIds:{}", customerIds);
        return storeRoleMapper.deleteStoreStaff(customerIds);
    }

    /**
     * 编辑员工-编辑员工关联的角色id
     *
     * @param roleAndCustomer 实体类
     * @return 编辑返回码
     */
    @Override
    public int updateRoleId(RoleAndCustomer roleAndCustomer) {
        Logger.debug("updateRoleId and roleAndCustomer:{}", roleAndCustomer);
        return storeRoleMapper.updateRoleId(roleAndCustomer);
    }

    /**
     * 递归加载菜单
     *
     * @param storeMenu         所有菜单
     * @param storeEditMenuList 生成的树形菜单
     * @return 树形菜单
     */
    private List<StoreEditMenu> getNum(StoreMenu storeMenu, List<StoreEditMenu> storeEditMenuList) {
        if (!CollectionUtils.isEmpty(storeMenu.getChildMenu())) {
            for (StoreMenu storeMenus : storeMenu.getChildMenu()) {
                storeEditMenuList.add(StoreEditMenu.getAdminEditMenu(storeMenus.getAuthorityId(), storeMenus.getParentId(), storeMenus.getName()));
                getNum(storeMenus, storeEditMenuList);
            }
        }
        return storeEditMenuList;
    }
}
