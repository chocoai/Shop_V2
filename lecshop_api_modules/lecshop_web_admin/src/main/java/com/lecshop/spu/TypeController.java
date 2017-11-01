package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.type.bean.Attribute;
import com.lecshop.type.bean.AttributeValue;
import com.lecshop.type.bean.Type;
import com.lecshop.type.bean.TypeSpec;
import com.lecshop.type.service.TypeService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/10.
 * 类型控制器
 */
@Controller
public class TypeController {

    /**
     * 注入类型服务接口
     */
    @Autowired
    private TypeService typeService;

    /**
     * 分页查询类型信息
     *
     * @param pageHelper 分页帮助类
     * @param name       类型名称
     * @return 返回分页信息
     */
    @RequestMapping("/querytypes")
    @ResponseBody
    public BaseResponse queryTypes(PageHelper<Type> pageHelper, String name) {
        return BaseResponse.build(typeService.queryTypes(pageHelper, name));
    }

    /**
     * 跳转到商品类型页
     *
     * @return 返回商品类型页
     */
    @RequestMapping("/toquerytypes")
    public ModelAndView toQueryTypes() {
        return new ModelAndView("spu/type");
    }

    /**
     * 新增类型
     *
     * @param type 类型实体
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addtype")
    @ResponseBody
    public int addType(@RequestBody Type type, HttpServletRequest request) {
        return typeService.addType(type.setDefaultValuesForAdd(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 根据类型id 删除类型
     *
     * @param id 类型id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/deletetype")
    @ResponseBody
    public int deleteType(long id, HttpServletRequest request) {
        return typeService.deleteType(Type.buildForDelete(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 批量删除类型信息
     *
     * @param ids 类型id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/batchdeletetypes")
    @ResponseBody
    public int batchDeleteTypes(Long[] ids, HttpServletRequest request) {
        return typeService.batchDeleteTypes(Arrays.stream(ids).map(id -> Type.buildForDelete(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request))).collect(Collectors.toList()));
    }

    /**
     * 查询类型详情
     *
     * @param id 类型id
     * @return 返回类型详情 包括类型,属性,属性值 和类型的规格
     */
    @RequestMapping("/querytypedetail")
    @ResponseBody
    public Type queryTypeDetail(long id) {
        return typeService.queryTypeDetail(id);
    }

    /**
     * 查询所有的类型信息
     *
     * @return 返回所有的类型信息
     */
    @RequestMapping("/queryalltypes")
    @ResponseBody
    public List<Type> queryAllTypes() {
        return typeService.queryAllType();
    }

    /**
     * 更新类型
     *
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatetype")
    @ResponseBody
    public int updateType(@RequestBody Type type,HttpServletRequest request) {
        return typeService.updateType(type.setDefalutValuesForModify(AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

}
