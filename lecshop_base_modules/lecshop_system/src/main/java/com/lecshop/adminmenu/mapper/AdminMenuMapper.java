package com.lecshop.adminmenu.mapper;

import com.lecshop.adminmenu.bean.AdminMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * boss端菜单数据库层
 */
@Repository
public interface AdminMenuMapper {

    /**
     * 根据管理员id查询菜单
     *
     * @param ManagerId 管理员id
     * @return 管理员菜单实体类
     */
    List<AdminMenu> queryAdminMenu(Long ManagerId);
}
