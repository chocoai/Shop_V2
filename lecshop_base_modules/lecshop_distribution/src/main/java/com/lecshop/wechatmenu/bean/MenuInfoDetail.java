package com.lecshop.wechatmenu.bean;

import lombok.Data;

import java.util.Random;

/**
 * 微信菜单实体类
 *
 * @author sunluyang on 2017/5/24.
 */
@Data
public class MenuInfoDetail {
    /**
     * 类型
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 链接
     */
    private String url;
    /**
     * key
     */
    private String key;
    /**
     * 唯一id
     */
    private Long id = new Random().nextLong() + (int) Math.random();
}
