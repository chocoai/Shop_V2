package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.service.BrandService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
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
 * 品牌控制器
 */
@Controller
public class BrandController {

    /**
     * 注入品牌服务接口
     */
    @Autowired
    private BrandService brandService;

    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addbrand")
    @ResponseBody
    public int addBrand(@RequestBody Brand brand, HttpServletRequest request) {
        return brandService.addBrand(brand.setDefaultValuesForAdd(AdminLoginUtils.getInstance().getManagerNameFromSession(request), CommonConstant.ADMIN_STOREID));
    }

    /**
     * 根据品牌id查询品牌信息
     *
     * @param id 品牌id
     * @return 返回品牌信息
     */
    @RequestMapping("/querybrand")
    @ResponseBody
    public Brand queryBrand(long id) {
        return brandService.queryBrandById(id,CommonConstant.ADMIN_STOREID);
    }

    /**
     * 修改品牌
     *
     * @param brand 品牌信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatebrand")
    @ResponseBody
    public int updateBrand(@RequestBody Brand brand, HttpServletRequest request) {
        return brandService.updateBrand(brand.setDefaultValuesForModify(AdminLoginUtils.getInstance().getManagerNameFromSession(request),CommonConstant.ADMIN_STOREID));
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/deletebrand")
    @ResponseBody
    public int deleteBrand(long id, HttpServletRequest request) {
        return brandService.deleteBrand(Brand.buildDeleteBrand(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request),CommonConstant.ADMIN_STOREID));
    }


    /**
     * 批量删除品牌
     *
     * @param ids 品牌id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/batchdeletebrands")
    @ResponseBody
    public int batchDeleteBrands(Long[] ids, HttpServletRequest request) {
        return brandService.batchDeleteBrands(Arrays.stream(ids).map(id -> Brand.buildDeleteBrand(id, AdminLoginUtils.getInstance().getManagerNameFromSession(request),CommonConstant.ADMIN_STOREID)).collect(Collectors.toList()));
    }

    /**
     * 查询品牌
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @param nickName   品牌昵称
     * @return 返回品牌信息
     */
    @RequestMapping("/querybrands")
    @ResponseBody
    public BaseResponse queryBrands(PageHelper<Brand> pageHelper, String name, String nickName) {
        return BaseResponse.build(brandService.queryBrands(pageHelper, name, nickName,CommonConstant.ADMIN_STOREID));
    }

    /**
     * 跳转到品牌页面
     *
     * @return 返回品牌页面
     */
    @RequestMapping("/toquerybrands")
    public ModelAndView toQueryBrands() {
        return new ModelAndView("spu/brand");
    }


    /**
     * 查询所有品牌
     *
     * @return 返回所有品牌
     */
    @RequestMapping("/queryallbrands")
    @ResponseBody
    public List<Brand> queryAllBrands() {
        return brandService.queryAllBrands(CommonConstant.ADMIN_STOREID);
    }
}
