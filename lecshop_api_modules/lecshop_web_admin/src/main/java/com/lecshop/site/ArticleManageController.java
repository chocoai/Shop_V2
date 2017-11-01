package com.lecshop.site;

import com.lecshop.articlemanage.bean.ArticleList;
import com.lecshop.articlemanage.bean.ColumnList;
import com.lecshop.articlemanage.service.ArticleListService;
import com.lecshop.articlemanage.service.ColumnListService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 文章管理控制器
 *
 * @author sunluyang on 2017/5/22.
 */
@Controller
public class ArticleManageController {
    /**
     * 注入文章列表service
     */
    @Autowired
    private ArticleListService articleListService;

    /**
     * 注入栏目列表service
     */
    @Autowired
    private ColumnListService columnListService;

    /**
     * 跳转到文章列表页面
     *
     * @return 文章列表页面
     */
    @RequestMapping("/toarticlelist")
    public ModelAndView toArticleList() {
        return new ModelAndView("site/articlelist");
    }

    /**
     * 跳转到栏目列表页面
     *
     * @return 栏目列表页面
     */
    @RequestMapping("/tocolumnlist")
    public ModelAndView toColumnList() {
        return new ModelAndView("site/columnlist");
    }

    /**
     * 分页查询文章列表
     *
     * @param pageHelper 分页帮助类
     * @param title      查询条件，文章标题
     * @return BaseResponse集合对象
     */
    @RequestMapping("/articlelist")
    @ResponseBody
    public BaseResponse queryArticleList(PageHelper<ArticleList> pageHelper, String title) {
        return BaseResponse.build(articleListService.queryArticleList(pageHelper, title));
    }

    /**
     * 跳转到添加文章页面
     *
     * @return 添加文章页面
     */
    @RequestMapping("/toaddarticle")
    public ModelAndView toAddArticle() {
        return new ModelAndView("site/addarticle");
    }

    /**
     * 跳转到编辑文章页面
     *
     * @return 编辑文章页面
     */
    @RequestMapping("/toeditarticle")
    public ModelAndView toEditArticle(long aid) {
        return new ModelAndView("site/editarticle").addObject("editId", aid);
    }

    /**
     * 添加文章
     *
     * @param articleList 文章实体类
     * @return 添加返回码 -1失败 1成功
     */
    @RequestMapping("/addarticle")
    @ResponseBody
    public int addArticle(@RequestBody ArticleList articleList) {
        return articleListService.addArticle(articleList);
    }

    /**
     * 删除文章列表
     *
     * @param ids 文章id数组
     * @return 删除返回码 -1 删除失败 >=1删除成功
     */
    @RequestMapping("/deletearticle")
    @ResponseBody
    public int deleteArticle(long[] ids) {
        return articleListService.deleteArticle(ids);
    }

    /**
     * 根据文章id查询文章信息
     *
     * @param id 文章主键id
     * @return 文章信息
     */
    @RequestMapping("/queryarticlebyid")
    @ResponseBody
    public ArticleList queryArticleById(long id) {
        return articleListService.queryArticleById(id);
    }

    /**
     * 编辑文章
     *
     * @param articleList 文章实体类
     * @return 编辑返回码 -1失败 1成功
     */
    @RequestMapping("/editarticle")
    @ResponseBody
    public int editArticle(@RequestBody ArticleList articleList) {
        return articleListService.editArticle(articleList);
    }

    /**
     * 查询所有栏目列表
     *
     * @return 栏目列表集合
     */
    @RequestMapping("/querycolumnlist")
    @ResponseBody
    public List<ColumnList> queryColumnList() {
        return columnListService.queryColumnList();
    }

    /**
     * 查询所有一级栏目列表
     *
     * @return 栏目列表集合
     */
    @RequestMapping("/queryparentcolumnlist")
    @ResponseBody
    public List<ColumnList> queryParentColumnList() {
        return columnListService.queryParentColumnList();
    }

    /**
     * 查询所有二级栏目列表
     *
     * @return 栏目列表集合
     */
    @RequestMapping("/querychildcolumnlist")
    @ResponseBody
    public List<ColumnList> queryChildColumnList() {
        return columnListService.queryChildColumnList();
    }

    /**
     * 添加栏目
     *
     * @param columnList 栏目实体类
     * @return 返回码 1 成功 -1 失败
     */
    @RequestMapping("/addcolumn")
    @ResponseBody
    public int addColumn(@RequestBody ColumnList columnList) {
        return columnListService.addColumn(columnList);
    }

    /**
     * 编辑栏目
     *
     * @param columnList 栏目实体类
     * @return 返回码 1成功 -1失败
     */
    @RequestMapping("/editcolumn")
    @ResponseBody
    public int editColumn(@RequestBody ColumnList columnList) {
        return columnListService.editColumn(columnList);
    }

    /**
     * 删除栏目
     *
     * @param columnList 栏目
     * @return -1 失败 1成功
     */
    @RequestMapping("/deletecolumn")
    @ResponseBody
    public int deleteColumn(@RequestBody ColumnList columnList) {
        return columnListService.deleteColumn(columnList);
    }
}
