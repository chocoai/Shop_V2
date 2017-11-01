package com.lecshop.storemenu.service;

import com.lecshop.storemenu.bean.StoreMenu;

import java.util.List;

/**
 * @author sunluyang on 2017/6/7.
 */
public interface StoreMenuService {
    /**
     * 根据管理员id查询菜单
     *
     * @param customerId 会员id
     * @return 管理员菜单实体类
     */
    List<StoreMenu> queryStoreMenu(Long customerId);

    /**
     * 根据管理员id所有菜单
     *
     * @param customerId 会员id
     * @return 管理员菜单实体类
     */
    List<StoreMenu> queryAllStoreMenu(Long customerId);

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param customerId 会员id
     * @param isMenu     是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    List<StoreMenu> storeMenuCommon(Long customerId, boolean isMenu);
}
