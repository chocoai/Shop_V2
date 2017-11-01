package com.lecshop.wechatmenu.bean;

import lombok.Data;

/**
 * 微信菜单实体类
 *
 * @author sunluyang on 2017/5/24.
 */
@Data
public class MenuInfoDetailExtends extends MenuInfoDetail {

    /**
     * 二级菜单
     */
    private SubMenu sub_button;
}
