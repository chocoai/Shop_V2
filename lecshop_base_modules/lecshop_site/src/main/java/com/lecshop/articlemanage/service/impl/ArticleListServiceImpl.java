package com.lecshop.articlemanage.service.impl;

import com.lecshop.articlemanage.bean.ArticleList;
import com.lecshop.articlemanage.mapper.ArticleListMapper;
import com.lecshop.articlemanage.service.ArticleListService;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 文章列表service实现类
 *
 * @author sunluyang on 2017/5/22.
 */
@Service
public class ArticleListServiceImpl implements ArticleListService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(ArticleListServiceImpl.class);

    /**
     * 注入文章信息mapper
     */
    @Autowired
    private ArticleListMapper articleListMapper;

    /**
     * 分页查询文章信息
     *
     * @return 文章信息集合
     */
    @Override
    public PageHelper<ArticleList> queryArticleList(PageHelper<ArticleList> pageHelper, String title) {
        logger.debug("queryArticleList and pageHelper : {} \r\n title: {}", pageHelper, title);
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        return pageHelper.setListDates(articleListMapper.queryArticleList(pageHelper.getQueryParams(params, articleListMapper.queryArticleListCount(params))));
    }

    /**
     * 添加文章
     *
     * @param articleList 文章实体类
     * @return 添加返回码 -1失败 1成功
     */
    @Override
    public int addArticle(ArticleList articleList) {
        logger.debug("addArticle and articleList : {}", articleList);
        if (Objects.isNull(articleList)) {
            logger.error("addArticle error due to articleList is null...");
            return -1;
        }
        return articleListMapper.addArticle(articleList);
    }

    /**
     * 删除文章列表
     *
     * @param ids 文章id数组
     * @return 删除返回码 -1 删除失败 >=1删除成功
     */
    @Override
    public int deleteArticle(long[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            logger.error("deleteArticleList error due to ids is empty...");
            return -1;
        }
        return articleListMapper.deleteArticle(ids);
    }

    /**
     * 编辑文章
     *
     * @param articleList 文章实体类
     * @return 编辑返回码 -1失败 1成功
     */
    @Override
    public int editArticle(ArticleList articleList) {
        logger.debug("addArticle and articleList : {}", articleList);
        if (Objects.isNull(articleList)) {
            logger.error("editArticle error due to articleList is null...");
            return -1;
        }
        return articleListMapper.editArticle(articleList);
    }

    /**
     * 根据文章id查询文章信息
     *
     * @param id 文章主键id
     * @return 文章信息
     */
    @Override
    public ArticleList queryArticleById(long id) {
        logger.debug("queryArticleById and id : {}", id);
        return articleListMapper.queryArticleById(id);
    }
}
