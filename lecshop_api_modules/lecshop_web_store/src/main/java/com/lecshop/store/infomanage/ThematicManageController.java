package com.lecshop.store.infomanage;

import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.thematicsetting.bean.Thematic;
import com.lecshop.thematicsetting.service.ThematicSettingService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 专题管理控制器
 *
 * Created by LecShop on 2017/6/9.
 */
@Controller
public class ThematicManageController {

    /**
     * 自动注入专题设置service接口
     */
    @Autowired
    private ThematicSettingService thematicSettingService;

    /**
     * 跳转至专题管理页面
     *
     * @return 专题管理页面
     */
    @RequestMapping("/store_tothematicmanage")
    public ModelAndView toThematicManage() {
        return new ModelAndView("infomanage/thematic");
    }

    /**
     * 跳转至添加专题页面
     *
     * @return 专题添加页面
     */
    @RequestMapping("/store_toaddthematic")
    public ModelAndView toAddThematic() {
        return new ModelAndView("infomanage/thematicadd");
    }

    /**
     * 跳转至修改专题页面
     *
     * @return  修改专题页面
     */
    @RequestMapping("/store_toeditthematic")
    public ModelAndView toEditThematic(long id) {
        return new ModelAndView("infomanage/thematicedit").addObject("id", id);
    }

    /**
     * 分页查询专题
     *
     * @param pageHelper 分页帮助类
     * @param name       专题名称
     * @return           返回专题信息
     */
    @RequestMapping("/store_querythematic")
    @ResponseBody
    public BaseResponse queryThematic(HttpServletRequest request, PageHelper<Thematic> pageHelper, String name) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return BaseResponse.build(thematicSettingService.queryThematic(pageHelper, name, storeId));
    }

    /**
     * 添加专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/store_addthematic")
    @ResponseBody
    public int addThematic(HttpServletRequest request, @RequestBody Thematic thematic) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return thematicSettingService.addThematic(thematic, storeId);
    }

    /**
     * 删除专题
     *
     * @param id 专题id
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/store_deletethematic")
    @ResponseBody
    public int deleteThematic(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return thematicSettingService.deleteThematic(id, storeId);
    }

    /**
     * 批量删除专题
     *
     * @param ids 专题id数组
     * @return    删除返回码 -1，失败 >=1成功
     */
    @RequestMapping("/store_batchdeletethematic")
    @ResponseBody
    public int batchDeleteThematic(HttpServletRequest request, long [] ids) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return thematicSettingService.batchDeleteThematic(ids, storeId);
    }

    /**
     * 根据id查询专题
     *
     * @param id 专题id
     * @return   返回专题
     */
    @RequestMapping("/store_querythematicbyid")
    @ResponseBody
    public Thematic queryThematicById(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return thematicSettingService.queryThematicById(id, storeId);
    }

    /**
     * 修改专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @RequestMapping("/store_editthematic")
    @ResponseBody
    public int updateThematic(HttpServletRequest request, @RequestBody Thematic thematic) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return thematicSettingService.updateThematic(thematic, storeId);
    }

}
