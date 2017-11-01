package com.lecshop.wechatmenu.bean;

import lombok.Data;

/**
 * 微信菜单实体类
 *
 * @author sunluyang on 2017/5/24.
 */
@Data
public class MenuInfo {
    /**
     * 是否自定义菜单
     */
    private int is_menu_open;
    /**
     * 自定义菜单
     */
    private SelfMenuInfo selfmenu_info;
}
