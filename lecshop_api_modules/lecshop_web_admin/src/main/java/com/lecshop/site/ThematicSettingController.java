package com.lecshop.site;

import com.lecshop.thematicsetting.bean.Thematic;
import com.lecshop.thematicsetting.service.ThematicSettingService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 专题设置控制器
 *
 * Created by LecShop on 2017/6/8.
 */
@Controller
public class ThematicSettingController {

    /**
     * 自动注入专题设置service接口
     */
    @Autowired
    private ThematicSettingService thematicSettingService;

    /**
     * 跳转至专题设置页面
     *
     * @return 专题设置页面
     */
    @RequestMapping("/tothematicsetting")
    public ModelAndView toThematic() {
        return new ModelAndView("site/thematic");
    }

    /**
     * 跳转至添加专题页面
     *
     * @return 专题添加页面
     */
    @RequestMapping("/toaddthematic")
    public ModelAndView toAddThematic() {
        return new ModelAndView("site/thematicadd");
    }

    /**
     * 跳转至修改专题页面
     *
     * @return  修改专题页面
     */
    @RequestMapping("/toeditthematic.htm")
    public ModelAndView toEditThematic(long id) {
        return new ModelAndView("site/thematicedit").addObject("id", id);
    }

    /**
     * 分页查询专题
     *
     * @param pageHelper 分页帮助类
     * @param name       专题设置名称
     * @return           返回专题设置信息
     */
    @RequestMapping("/querythematic")
    @ResponseBody
    public BaseResponse queryThematic(PageHelper<Thematic> pageHelper, String name) {
        return BaseResponse.build(thematicSettingService.queryThematic(pageHelper, name, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 添加专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/addthematic")
    @ResponseBody
    public int addThematic(@RequestBody Thematic thematic) {
        return thematicSettingService.addThematic(thematic, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 删除专题
     *
     * @param id 专题id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/deletethematic")
    @ResponseBody
    public int deleteThematic(long id) {
        return thematicSettingService.deleteThematic(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 批量删除专题
     *
     * @param ids 专题id数组
     * @return    删除返回码 -1，失败 >=1成功
     */
    @RequestMapping("/batchdeletethematic.htm")
    @ResponseBody
    public int batchDeleteThematic(long [] ids) {
        return thematicSettingService.batchDeleteThematic(ids, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 根据id查询专题
     *
     * @param id 专题id
     * @return   返回专题
     */
    @RequestMapping("/querythematicbyid")
    @ResponseBody
    public Thematic queryThematicById(long id) {
        return thematicSettingService.queryThematicById(id, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 修改专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/updatethematic")
    @ResponseBody
    public int updateThematic(@RequestBody Thematic thematic) {
        return thematicSettingService.updateThematic(thematic, CommonConstant.ADMIN_STOREID);
    }

}
