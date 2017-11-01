package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.spec.bean.Spec;
import com.lecshop.spec.service.SpecService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/8.
 * 规格控制器
 */
@Controller
public class SpecController {

    /**
     * 注入规格服务接口
     */
    @Autowired
    private SpecService specService;

    /**
     * 添加规格信息
     *
     * @param spec 规格
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addspec")
    @ResponseBody
    public int addSpec(@RequestBody Spec spec, HttpServletRequest request) {
        return specService.addSpec(spec.setDetaultValuesForAdd(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 修改规格信息
     *
     * @param spec 规格信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatespec")
    @ResponseBody
    public int updateSpec(@RequestBody Spec spec, HttpServletRequest request) {
        return specService.updateSpec(spec.setDetaultValuesForUpdate(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 根据规格id 查询规格信息(包含规格值)
     *
     * @param id 规格id
     * @return 返回规格信息 (包含规格值)
     */
    @RequestMapping("/queryspecbyid")
    @ResponseBody
    public Spec querySpecById(long id) {
        return specService.querySpecById(id);
    }

    /**
     * 查询指定的规格信息
     *
     * @param ids 规格id集合
     * @return 返回指定的规格信息(包含规格值)
     */
    @RequestMapping("/queryspecsbyids")
    @ResponseBody
    public List<Spec> querySpecsByIds(Long[] ids) {
        return specService.querySpecsByIds(ids);
    }

    /**
     * 删除规格信息
     *
     * @param id 规格id
     * @return 成功返回1  失败返回0 -1 商品已经使用规格不能删除
     */
    @RequestMapping("/deletespec")
    @ResponseBody
    public int deleteSpec(long id, HttpServletRequest request) {
        return specService.deleteSpec(Spec.buildForDelete(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 批量删除规格信息
     *
     * @param ids 规格id
     * @return 成功返回1  失败返回0 -1 商品已经使用规格不能删除
     */
    @RequestMapping("/deletespecs")
    @ResponseBody
    public int deleteSpecs(Long[] ids, HttpServletRequest request) {
        return specService.deleteSpecs(Arrays.stream(ids).map(id -> Spec.buildForDelete(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request))).collect(Collectors.toList()));
    }


    /**
     * 跳转到规格页面
     *
     * @return 返回规格页面
     */
    @RequestMapping("toqueryspecs")
    public ModelAndView toQuerySpecs() {
        return new ModelAndView("spu/spec");
    }

    /**
     * 分页查询规格
     *
     * @param pageHelper 分页帮助类
     * @param name       查询名称
     * @return 返回规格信息
     */
    @RequestMapping("/queryspecs")
    @ResponseBody
    public BaseResponse querySpecs(PageHelper<Spec> pageHelper, String name) {
        return BaseResponse.build(specService.querySpecs(pageHelper, name));
    }

    @RequestMapping("/queryallspecs")
    @ResponseBody
    public List<Spec> queryAllSpecs() {
        return specService.queryAllSpec();
    }
}
