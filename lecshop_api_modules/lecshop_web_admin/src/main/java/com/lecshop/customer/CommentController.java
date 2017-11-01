package com.lecshop.customer;

import com.lecshop.comment.bean.Comment;
import com.lecshop.comment.service.CommentService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


/**
 * Created by dujinkai on 17/5/24.
 * 评论控制器
 */
@Controller
public class CommentController {

    /**
     * 注入评论服务接口
     */
    @Autowired
    private CommentService commentService;

    /**
     * 跳转到评论页面
     *
     * @return 返回评论页面
     */
    @RequestMapping("/tocomment")
    public ModelAndView toComment() {
        return new ModelAndView("customer/comment");
    }

    /**
     * 分页查询评论
     *
     * @param pageHelper   分页帮助类
     * @param customerName 发表人名称
     * @param skuName      单品名称
     * @return 返回评论数据
     */
    @RequestMapping("/querycommnts")
    @ResponseBody
    public BaseResponse queryCommnts(PageHelper<Comment> pageHelper, String customerName, String skuName) {
        return BaseResponse.build(commentService.queryComments(pageHelper, customerName, skuName));
    }

    /**
     * 根据评论id查询评论信息
     *
     * @param id 评论id
     * @return 返回评论信息
     */
    @RequestMapping("/querycommentbyid")
    @ResponseBody
    public Comment queryCommentById(long id) {
        return commentService.queryCommentById(id);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论id
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/deletecomment")
    @ResponseBody
    public int deleteComment(long id) {
        return commentService.deleteComments(Arrays.asList(id));
    }

    /**
     * 批量删除评论信息
     *
     * @param ids 评论id集合
     * @return 成功返回>1 失败返回0
     */
    @RequestMapping("/deletecomments")
    @ResponseBody
    public int deleteComments(Long[] ids) {
        return commentService.deleteComments(Arrays.asList(ids));
    }
}
