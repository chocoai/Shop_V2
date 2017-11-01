package com.lecshop.friendlink.bean;

import lombok.Data;


/**
 * 友情链接设置实体类
 *
 * Created by LecShop on 2017/5/19.
 */
@Data
public class FriendLink {
    /**
     * 主键id
     */
    private long id;
    /**
     * 链接名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 排序  数值越小排序越前
     */
    private int sort;
    /**
     * 链接图片地址
     */
    private String imageUrl;
    /**
     * 是否显示  0 显示，1 不显示。默认0
     */
    private int isShow;
    /**
     * 删除标记 0 未删除，1 删除。默认0
     */
    private int delFlag;

}
