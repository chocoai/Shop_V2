package com.lecshop.friendlink.service;

import com.lecshop.friendlink.bean.FriendLink;
import com.lecshop.util.PageHelper;

/**
 * 友情链接service层
 *
 * Created by LecShop on 2017/5/19.
 */
public interface FriendLinkService {

    /**
     * 分页查询友情链接
     *
     * @param pageHelper  分页帮助类
     * @param name        链接名称
     * @param url         链接地址
     * @return            返回友情链接数据
     */
    PageHelper<FriendLink> queryFriendLinks(PageHelper<FriendLink> pageHelper, String name, String url);

    /**
     * 根据友情链接id查询友情链接信息
     *
     * @param id 友情链接id
     * @return   返回友情链接信息
     */
    FriendLink queryFriendLinkById(long id);

    /**
     * 更新友情链接
     *
     * @param  friendLink 友情链接信息
     * @return 成功返回1 失败返回0
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 新增友情链接
     *
     * @param  friendLink 友情链接
     * @return 返回码说明 0 失败 1成功
     */
    int addFriendLink(FriendLink friendLink);

    /**
     * 删除友情链接
     *
     * @param  id 友情链接id
     * @return 成功返回1  失败返回0
     */
    int deleteFriendLink(long id);

    /**
     * 批量删除友情链接
     *
     * @param ids 友情链接id数组
     * @return 成功返回1  失败返回0
     */
    int batchDeleteFriendLinks(long [] ids);
}
