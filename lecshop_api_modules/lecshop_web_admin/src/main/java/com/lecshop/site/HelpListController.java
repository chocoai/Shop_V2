package com.lecshop.site;

import com.lecshop.helpcenter.bean.HelpCategory;
import com.lecshop.helpcenter.bean.HelpList;
import com.lecshop.helpcenter.service.HelpListService;
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
 * 帮助列表控制器
 *
 * Created by LecShop on 2017/5/27.
 */
@Controller
public class HelpListController {

    /**
     * 自动注入帮助列表业务层接口
     */
    @Autowired
    private HelpListService helpListService;

    /**
     * 跳转到帮助列表页面
     *
     * @return 帮助列表页面
     */
    @RequestMapping("/tohelplist")
    public ModelAndView toHelpList() {
        return new ModelAndView("site/helplist");
    }

    /**
     * 查询帮助列表
     *
     * @param pageHelper 分页帮助类
     * @param name       帮助名称
     * @return           返回帮助信息
     */
    @RequestMapping("/queryhelplist")
    @ResponseBody
    public BaseResponse queryHelpList(PageHelper<HelpList> pageHelper, String name) {
        return BaseResponse.build(helpListService.queryHelpList(pageHelper, name));
    }

    /**
     * 跳转到添加帮助页面
     *
     * @return 添加帮助页面
     */
    @RequestMapping("/toaddhelp")
    public ModelAndView toAddHelp() {
        return new ModelAndView("site/helplistadd");
    }

    /**
     * 添加帮助
     *
     * @param helpList 帮助
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/addhelp")
    @ResponseBody
    public int addHelp(@RequestBody HelpList helpList) {
        return helpListService.addHelp(helpList);
    }

    /**
     * 查询添加帮助所需的帮助分类
     *
     * @return 返回帮助分类集合
     */
    @RequestMapping("/queryallhelpcategory")
    @ResponseBody
    public List<HelpCategory> queryAllHelpCategory() {
        return helpListService.queryHelpCate();
    }

    /**
     * 删除帮助
     *
     * @param id 帮助id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/deletehelp")
    @ResponseBody
    public int deleteHelp(long id) {
        return helpListService.deleteHelp(id);
    }

    /**
     * 批量删除帮助
     *
     * @param ids 帮助id数组
     * @return    成功返回1，失败返回0
     */
    @RequestMapping("/batchdeletehelp")
    @ResponseBody
    public int batchDeleteHelp(long [] ids) {
        return helpListService.batchDeleteHelp(ids);
    }

    /**
     * 根据id查询帮助
     *
     * @param id 帮助id
     * @return   返回帮助
     */
    @RequestMapping("/queryhelpbyid")
    @ResponseBody
    public HelpList queryHelpById(long id) {
        return helpListService.queryHelpById(id);
    }

    /**
     * 跳转到修改帮助页面
     *
     * @return 修改帮助页面
     */
    @RequestMapping("toedithelp.htm")
    public ModelAndView toEditHelp(long id) {
        return new ModelAndView("site/helplistedit").addObject("editId", id);
    }

    /**
     * 修改帮助
     *
     * @param helpList 帮助
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/updatehelp")
    @ResponseBody
    public int updateHelp(@RequestBody HelpList helpList) {
        return helpListService.updateHelp(helpList);
    }

    /**
     * 查询帮助集合
     *
     * @return 返回帮助集合
     */
    @RequestMapping("/queryhelp")
    @ResponseBody
    public List<HelpList> queryHelp() {
        return helpListService.queryHelp();
    }

}
