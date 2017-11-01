package com.lecshop.comment.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/5/24.
 * 评论图片
 */
@Data
public class CommentPic {

    /**
     * 主键id
     */
    private long id;

    /**
     * 评论id
     */
    private long commentId;

    /**
     * 评论图片
     */
    private String url;
}
