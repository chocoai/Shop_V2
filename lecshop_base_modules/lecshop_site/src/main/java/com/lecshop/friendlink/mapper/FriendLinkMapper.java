package com.lecshop.friendlink.mapper;

import com.lecshop.friendlink.bean.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 友情链接mapper层
 *
 * Created by LecShop on 2017/5/19.
 */
@Repository
public interface FriendLinkMapper {
    /**
     * 分页查询友情链接数据
     */
    List<FriendLink> queryFriendLinks(Map<String, Object> params);

    /**
     * 查询链接的总记录数
     *
     * @param params 查询参数
     * @return   返回总记录数
     */
    int queryFriendLinksCount(Map<String, Object> params);

    /**
     * 根据友情链接的id查询友情链接信息
     *
     * @param id 友情链接id
     * @return   返回友情链接信息
     */
    FriendLink queryFriendLinkById(long id);

    /**
     * 更新友情链接
     *
     * @param  friendLink  友情链接信息
     * @return 成功返回1  失败返回0
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 新增友情链接
     *
     * @param  friendLink
     * @return 返回0 失败 1 成功
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
