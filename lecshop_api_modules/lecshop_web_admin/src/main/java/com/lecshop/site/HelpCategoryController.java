package com.lecshop.site;

import com.lecshop.helpcenter.bean.HelpCategory;
import com.lecshop.helpcenter.service.HelpCategoryService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 帮助分类控制器
 *
 * Created by LecShop on 2017/5/23.
 */
@Controller
public class HelpCategoryController {

    /**
     * 注入帮助分类服务接口
     */
    @Autowired
    private HelpCategoryService helpCategoryService;

    /**
     * 跳转到帮助分类页面
     *
     * @return 帮助分类页面
     */
    @RequestMapping("/tohelpclassify")
    public ModelAndView toHelpCenter() {
        return new ModelAndView("site/helpcategory");
    }


    /**
     * 查询帮助分类
     *
     * @param pageHelper 分页帮助类
     * @param name       帮助分类名称
     * @return           返回帮助分类信息
     */
    @RequestMapping("/queryhelpcategory")
    @ResponseBody
    public BaseResponse queryHelpCategoryList(PageHelper<HelpCategory> pageHelper, String name) {
        return BaseResponse.build(helpCategoryService.queryHelpCategory(pageHelper, name));
    }

    /**
     *  添加帮助分类
     *
     * @param helpCategory 帮助分类
     * @return             返回 0 失败，1 成功
     */
    @RequestMapping("/addhelpcategory")
    @ResponseBody
    public int addHelpCategory(@RequestBody HelpCategory helpCategory) {
        return helpCategoryService.addHelpCategory(helpCategory);
    }

    /**
     * 删除帮助分类
     *
     * @param id 帮助分类id
     * @return   成功返回1  失败返回0
     */
    @RequestMapping("/deletehelpcategory")
    @ResponseBody
    public int deletefriendlink(long id) {
        return helpCategoryService.deleteHelpCategory(id);
    }

    /**
     * 批量删除帮助分类
     *
     * @param ids 帮助分类id数组
     * @return    成功返回1  失败返回0
     */
    @RequestMapping("/batchDeletehelpcategory")
    @ResponseBody
    public int batchDeleteHelpCategory(long [] ids) {
        return helpCategoryService.batchDeleteHelpCategory(ids);
    }

    /**
     * 根据id查询帮助分类
     *
     * @param id 帮助分类id
     * @return   返回帮助分类
     */
    @RequestMapping("/queryhelpcategorybyid")
    @ResponseBody
    public HelpCategory updateHelpCategory(long id) {
        return helpCategoryService.queryHelpCategoryById(id);
    }

    /**
     * 修改帮助分类
     *
     * @param helpCategory 帮助分类
     * @return             成功返回1  失败返回0
     */
    @RequestMapping("updatehelpcategory")
    @ResponseBody
    public int updateHelpCategory(@RequestBody HelpCategory helpCategory) {
        return helpCategoryService.updateHelpCategory(helpCategory);
    }

}
