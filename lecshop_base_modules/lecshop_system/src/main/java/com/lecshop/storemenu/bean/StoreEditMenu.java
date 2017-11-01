package com.lecshop.storemenu.bean;

import lombok.Data;

/**
 * admin中对菜单编辑对实体类
 */
@Data
public class StoreEditMenu {
    /**
     * 菜单Id
     */
    private int id;
    /**
     * 父级Id
     */
    private int pId;
    /**
     *
     */
    private String name;
    /**
     * 前端需要对字段
     */
    private boolean open = false;

    public static StoreEditMenu getAdminEditMenu(int id, int pId, String name) {
        StoreEditMenu storeEditMenu = new StoreEditMenu();
        storeEditMenu.id = id;
        storeEditMenu.pId = pId;
        storeEditMenu.name = name;
        if (pId == 0) {
            storeEditMenu.open = true;
        } else {
            storeEditMenu.open = false;
        }
        return storeEditMenu;
    }
}
