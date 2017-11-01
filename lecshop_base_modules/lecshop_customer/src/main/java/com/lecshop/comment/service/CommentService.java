package com.lecshop.comment.service;

import com.lecshop.comment.bean.Comment;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/24.
 * 评论服务接口
 */
public interface CommentService {

    /**
     * 分页查询评论信息
     *
     * @param pageHelper   分页帮助类
     * @param customerName 发表人名称
     * @param skuName      单品名称
     * @return 返回评论信息
     */
    PageHelper<Comment> queryComments(PageHelper<Comment> pageHelper, String customerName, String skuName);

    /**
     * 根据id查询评论信息
     *
     * @param id 评论id
     * @return 返回评论信息
     */
    Comment queryCommentById(long id);

    /**
     * 删除评论
     *
     * @param ids 评论id集合
     * @return 成功返回>1 失败返回0
     */
    int deleteComments(List<Long> ids);

    /**
     * 根据skuId查询评论总数
     *
     * @param skuId 单品id
     * @return 评论总数
     */
    int queryCommentCountBySkuId(String skuId);

}
