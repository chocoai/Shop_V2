package com.lecshop.articlemanage.service;

import com.lecshop.articlemanage.bean.ArticleList;
import com.lecshop.util.PageHelper;


/**
 * 文章信息service层接口
 *
 * @author sunluyang on 2017/5/22.
 */
public interface ArticleListService {

    /**
     * 分页查询文章信息
     *
     * @return 文章信息集合
     */
    PageHelper<ArticleList> queryArticleList(PageHelper<ArticleList> pageHelper, String title);

    /**
     * 添加文章
     *
     * @param articleList 文章实体类
     * @return 添加返回码 -1失败 1成功
     */
    int addArticle(ArticleList articleList);

    /**
     * 删除文章列表
     *
     * @param ids 文章id数组
     * @return 删除返回码
     */
    int deleteArticle(long[] ids);

    /**
     * 编辑文章
     *
     * @param articleList 文章实体类
     * @return 编辑返回码
     */
    int editArticle(ArticleList articleList);


    /**
     * 根据文章id查询文章信息
     *
     * @param id 文章主键id
     * @return 文章信息
     */
    ArticleList queryArticleById(long id);
}
