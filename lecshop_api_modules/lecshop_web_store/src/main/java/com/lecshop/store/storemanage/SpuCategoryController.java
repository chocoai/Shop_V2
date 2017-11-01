package com.lecshop.store.storemanage;

import com.lecshop.spu.bean.SpuCategory;
import com.lecshop.spu.service.SpuCategoryService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品分类分类控制器
 *
 * Created by LecShop on 2017/6/15.
 */
@Controller
public class SpuCategoryController {

    /**
     * 自动注入商品分类service
     */
    @Autowired
    private SpuCategoryService spuCategoryService;

    /**
     * 跳转到店铺分类页面
     *
     * @return 店铺分类页面
     */
    @RequestMapping("/store_tospucategory")
    public ModelAndView toStoreCategory() {
        return new ModelAndView("spu/category");
    }

    /**
     * 根据父级id查询商品分类
     *
     * @param parentId 父级id
     * @return   商品分类
     */
    @RequestMapping("/store_queryspucategorybyparentid")
    @ResponseBody
    public List<SpuCategory> querySpuCategoryByParentId(HttpServletRequest request, long parentId) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return spuCategoryService.querySpuCategoryByParentId(parentId, storeId);
    }

    /**
     * 删除商品分类
     *
     * @param id 商品分类id
     * @return   -1 有子分类不能删除，  1 成功， 0 失败
     */
    @RequestMapping("/store_deletespucategory")
    @ResponseBody
    public int updateSpuCategory(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return spuCategoryService.deleteSpuCategory(id, storeId);
    }

    /**
     * 根据id查询商品分类
     *
     * @param id 商品分类id
     * @return   商品分类
     */
    @RequestMapping("/store_queryspucategorybyid")
    @ResponseBody
    public SpuCategory querySpuCategoryById(HttpServletRequest request, long id) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        return spuCategoryService.querySpuCategoryById(id, storeId);
    }

    /**
     * 更新商品分类
     *
     * @param spuCategory 商品分类
     * @return            成功返回1，失败返回0
     */
    @RequestMapping("/store_updatespucategory")
    @ResponseBody
    public int updateSpuCategory(HttpServletRequest request, @RequestBody SpuCategory spuCategory) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        spuCategory.setStoreId(storeId);
        return spuCategoryService.updateSpuCategory(spuCategory);
    }

    /**
     * 添加商品分类
     *
     * @param spuCategory 商品分类
     * @return            成功返回1，失败返回0
     */
    @RequestMapping("/store_addspucategory")
    @ResponseBody
    public int addSpuCategory(HttpServletRequest request, @RequestBody SpuCategory spuCategory) {
        long storeId = StoreLoginUtils.getInstance().getStoreIdFromSession(request);
        spuCategory.setStoreId(storeId);
        return spuCategoryService.addSpuCategory(spuCategory);
    }

}
