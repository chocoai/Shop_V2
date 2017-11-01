package com.lecshop.comment.service.impl;

import com.lecshop.comment.bean.Comment;
import com.lecshop.comment.mapper.CommentMapper;
import com.lecshop.comment.service.CommentService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/24.
 * 评论服务接口实现
 */
@Service
public class CommentServiceImpl implements CommentService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * 注入评论数据库接口
     */
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageHelper<Comment> queryComments(PageHelper<Comment> pageHelper, String customerName, String skuName) {
        logger.debug("queryComments and pageHelper:{} \r\n customerName :{} \r\n skuName:{}", pageHelper, customerName, skuName);

        Map<String, Object> params = new HashMap<>();
        params.put("customerName", customerName);
        params.put("skuName", skuName);
        return pageHelper.setListDates(commentMapper.queryComments(pageHelper.getQueryParams(params, commentMapper.queryCommentCount(params))));
    }

    @Override
    public Comment queryCommentById(long id) {
        logger.debug("queryCommentById and id :{}", id);

        Comment comment = commentMapper.queryCommentById(id);
        comment.setCommentPics(commentMapper.queryCommentPicsByCommentId(id));
        return comment;
    }

    @Override
    public int deleteComments(List<Long> ids) {
        logger.debug("deleteComments and ids:{}", ids);

        if (CollectionUtils.isEmpty(ids)) {
            logger.error("deleteComments fail due to ids is empty....");
            return 0;
        }
        return commentMapper.deleteComments(ids);
    }

    @Override
    public int queryCommentCountBySkuId(String skuId) {
        logger.debug("queryCommentCountBySkuId and skuId:{}", skuId);
        return commentMapper.queryCommentCountBySkuId(skuId);
    }
}
