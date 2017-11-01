package com.lecshop.adminmenu.service.impl;

import com.lecshop.adminmenu.bean.AdminMenu;
import com.lecshop.adminmenu.mapper.AdminMenuMapper;
import com.lecshop.adminmenu.service.AdminMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * boss端菜单service实现类层
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(AdminMenuServiceImpl.class);

    /**
     * 菜单mapper
     */
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    /**
     * 根据管理员id查询菜单
     *
     * @param managerId 管理员id
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<AdminMenu> queryAdminMenu(Long managerId) {
        logger.debug("queryAdminMenu...");
        return adminMenuCommon(managerId, true);
    }

    /**
     * 查询该用户所有菜单
     *
     * @param managerId 管理员id
     * @return 菜单实体类
     */
    @Override
    public List<AdminMenu> queryAllAdminMenu(Long managerId) {
        logger.debug("queryAllAdminMenu...");
        return adminMenuMapper.queryAdminMenu(managerId);
    }

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param managerId 管理员id
     * @param isMenu    是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<AdminMenu> adminMenuCommon(Long managerId, boolean isMenu) {
        //查询所有该用户菜单
        List<AdminMenu> adminMenu = queryAllAdminMenu(managerId);
        //返回该用户一级菜单
        List<AdminMenu> parentList = adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0).collect(Collectors.toList());
        //返回该用户二级菜单
        for (AdminMenu parentMenu : parentList) {
            List<AdminMenu> list = new ArrayList();
            for (AdminMenu allMenu : adminMenu) {
                if (parentMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 2) {
                    if (!isMenu && allMenu.getAuthorityId() == 8) {
                        continue;
                    }
                    list.add(allMenu);
                    parentMenu.setChildMenu(list);
                }
            }
        }
        //返回该用户三级菜单
        for (AdminMenu parentMenu : parentList) {
            if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                continue;
            }
            for (AdminMenu childMenu : parentMenu.getChildMenu()) {
                List<AdminMenu> list = new ArrayList();
                for (AdminMenu allMenu : adminMenu) {
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
            for (AdminMenu parentMenu : parentList) {
                if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                    continue;
                }
                for (AdminMenu childMenu : parentMenu.getChildMenu()) {
                    if (CollectionUtils.isEmpty(childMenu.getChildMenu())) {
                        continue;
                    }
                    for (AdminMenu thirdMenu : childMenu.getChildMenu()) {
                        List<AdminMenu> list = new ArrayList();
                        for (AdminMenu allMenu : adminMenu) {
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
