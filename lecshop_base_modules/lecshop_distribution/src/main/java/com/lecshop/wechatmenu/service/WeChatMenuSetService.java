package com.lecshop.wechatmenu.service;

import com.lecshop.wechatmenu.bean.MenuInfo;
import com.lecshop.wechatmenu.bean.WeChatSet;

import java.util.Map;

/**
 * 微信菜单设置service
 *
 * @author sunluyang on 2017/5/24.
 */
public interface WeChatMenuSetService {
    /**
     * 根据店铺id查询,微信设置信息,获取菜单
     *
     * @param storeId 店铺id
     * @return 获取菜单
     */
    MenuInfo queryWechatInfo(long storeId);

    /**
     * 修改微信菜单
     *
     * @param menuInfo 菜单对象
     * @param storeId  店铺id
     * @return 返回码 -1失败 1成功
     */
    int editWeChatMenu(String menuInfo, long storeId);

    /**
     * 根据店铺id查询微信token
     *
     * @param storeId 店铺id
     * @return token Map
     */
    Map<String, String> getWeChatToken(long storeId);

    /**
     * 根据店铺id查询到店铺的微信设置
     *
     * @param storeId 店铺id
     * @return 微信设置
     */
    WeChatSet queryStoreWeChatSet(long storeId);

    /**
     * 根据店铺id编辑店铺的微信设置
     *
     * @param storeId   店铺id
     * @param weChatSet 微信信息实体类
     * @return 编辑返回码
     */
    int editStoreWeChatSet(long storeId, WeChatSet weChatSet);
}
