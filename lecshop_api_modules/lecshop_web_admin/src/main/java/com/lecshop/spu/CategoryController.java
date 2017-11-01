package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.category.bean.Category;
import com.lecshop.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dujinkai on 17/5/11.
 * 分类控制器
 */
@Controller
public class CategoryController {

    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加分类
     *
     * @param category 分类实体
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addcategory")
    @ResponseBody
    public int addCategory(@RequestBody Category category, HttpServletRequest request) {
        return categoryService.addCategory(category.setDefaultsForAdd(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 根据分类id查询分类信息
     *
     * @param id 分类id
     * @return 分类信息
     */
    @RequestMapping("/querycategorybyid")
    @ResponseBody
    public Category queryCategoryById(long id) {
        return categoryService.queryCategoryById(id);
    }

    /**
     * 根据分类id查询分类信息
     *
     * @param parentId 父级id
     * @return 返回该父级下的所有分类信息
     */
    @RequestMapping("/querycategorybyparentid")
    @ResponseBody
    public List<Category> queryCategoryByParentId(long parentId) {
        return categoryService.queryCategoryByParentId(parentId);
    }

    /**
     * 跳转到分类查询页
     *
     * @return 返回分类查询页
     */
    @RequestMapping("/toquerycategory")
    public ModelAndView toQueryCategory() {
        return new ModelAndView("spu/category");
    }

    /**
     * 删除分类信息
     *
     * @param id 分类id
     * @return -1 有子分类不能删除  1 成功 0 失败
     */
    @RequestMapping("/deletecategory")
    @ResponseBody
    public int deleteCategory(long id, HttpServletRequest request) {
        return categoryService.deleteCategory(Category.buildForDelete(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 更新商品分类
     *
     * @param category 分类
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatecategory")
    @ResponseBody
    public int updateCategory(@RequestBody Category category, HttpServletRequest request) {
        return categoryService.updateCategory(category.setDefaultValuesForModify(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }
}
