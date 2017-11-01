package com.lecshop.storemenu.service.impl;

import com.lecshop.storemenu.bean.StoreMenu;
import com.lecshop.storemenu.mapper.StoreMenuMapper;
import com.lecshop.storemenu.service.StoreMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunluyang on 2017/6/7.
 */
@Service
public class StoreMenuServiceImpl implements StoreMenuService {
    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(StoreMenuServiceImpl.class);

    /**
     * 菜单mapper
     */
    @Autowired
    private StoreMenuMapper storeMenuMapper;

    /**
     * 根据管理员id查询菜单
     *
     * @param customerId 管理员id
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<StoreMenu> queryStoreMenu(Long customerId) {
        logger.debug("queryAdminMenu...");
        return storeMenuCommon(customerId, true);
    }

    /**
     * 查询该用户所有菜单
     *
     * @param customerId 管理员id
     * @return 菜单实体类
     */
    @Override
    public List<StoreMenu> queryAllStoreMenu(Long customerId) {
        logger.debug("queryAllAdminMenu...");
        return storeMenuMapper.queryStoreMenu(customerId);
    }

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param customerId 管理员id
     * @param isMenu     是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<StoreMenu> storeMenuCommon(Long customerId, boolean isMenu) {
        //查询所有该用户菜单
        List<StoreMenu> adminMenu = queryAllStoreMenu(customerId);
        //返回该用户一级菜单
        List<StoreMenu> parentList = adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0).collect(Collectors.toList());
        //返回该用户二级菜单
        for (StoreMenu parentMenu : parentList) {
            List<StoreMenu> list = new ArrayList();
            for (StoreMenu allMenu : adminMenu) {
                if (parentMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 2) {
                    if (!isMenu && allMenu.getAuthorityId() == 11) {
                        continue;
                    }
                    list.add(allMenu);
                    parentMenu.setChildMenu(list);
                }
            }
        }
        //返回该用户三级菜单
        for (StoreMenu parentMenu : parentList) {
            if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                continue;
            }
            for (StoreMenu childMenu : parentMenu.getChildMenu()) {
                List<StoreMenu> list = new ArrayList();
                for (StoreMenu allMenu : adminMenu) {
                    if (childMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 3) {
                        list.add(allMenu);
                        childMenu.setChildMenu(list);
                    }
                }
            }
        }
        //如果是用于菜单则直接返回，否则将四级菜单遍历出来
        if (isMenu) {
            return parentList;
        } else {
            //返回该用户四级菜单
            for (StoreMenu parentMenu : parentList) {
                if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                    continue;
                }
                for (StoreMenu childMenu : parentMenu.getChildMenu()) {
                    if (CollectionUtils.isEmpty(childMenu.getChildMenu())) {
                        continue;
                    }
                    for (StoreMenu thirdMenu : childMenu.getChildMenu()) {
                        List<StoreMenu> list = new ArrayList();
                        for (StoreMenu allMenu : adminMenu) {
                            if (thirdMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 4) {
                                list.add(allMenu);
                                thirdMenu.setChildMenu(list);
                            }
                        }
                    }
                }
            }
            return parentList;
        }
    }
}
