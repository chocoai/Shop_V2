package com.lecshop.store.spu;

import com.lecshop.category.bean.StoreSignedCategory;
import com.lecshop.category.service.StoreSignedCategoryService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dujinkai on 17/6/14.
 * 签约分类控制器
 */
@Controller
public class StoreSignedCategoryController {

    /**
     * 注入签约分类服务接口
     */
    @Autowired
    private StoreSignedCategoryService storeSignedCategoryService;

    /**
     * 查询店铺签约的分类
     *
     * @return 返回店铺签约分类
     */
    @RequestMapping("/store_queryallsignedcategorys")
    @ResponseBody
    public List<StoreSignedCategory> queryAllSignedCategorys(HttpServletRequest request) {
        return storeSignedCategoryService.queryAllSignedCategorys(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }
}
