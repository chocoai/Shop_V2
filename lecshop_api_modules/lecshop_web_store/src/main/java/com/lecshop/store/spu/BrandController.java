package com.lecshop.store.spu;

import com.lecshop.brand.bean.Brand;
import com.lecshop.brand.service.BrandService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dujinkai on 17/6/14.
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
     * 查询所有品牌(审核通过的和自定义审核通过的品牌)
     *
     * @return 返回所有品牌
     */
    @RequestMapping("/store_queryallbrands")
    @ResponseBody
    public List<Brand> queryAllBrands(HttpServletRequest request) {
        return brandService.queryAllBrands(StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }
}
