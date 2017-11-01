package com.lecshop.comment.mapper;

import com.lecshop.comment.bean.Comment;
import com.lecshop.comment.bean.CommentPic;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/24.
 * 评论数据库接口
 */
public interface CommentMapper {

    /**
     * 查询评论总数
     *
     * @param params 查询参数
     * @return 返回评论总数
     */
    int queryCommentCount(Map<String, Object> params);

    /**
     * 查询评论
     *
     * @param params 查询参数
     * @return 返回评论数据
     */
    List<Comment> queryComments(Map<String, Object> params);

    /**
     * 根据评论id查询评论信息
     *
     * @param id 评论id
     * @return 返回评论信息
     */
    Comment queryCommentById(long id);

    /**
     * 根据评论id查询评论图片
     *
     * @param commentId 评论id
     * @return 返回评论图片
     */
    List<CommentPic> queryCommentPicsByCommentId(long commentId);

    /**
     * 删除评论信息
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
