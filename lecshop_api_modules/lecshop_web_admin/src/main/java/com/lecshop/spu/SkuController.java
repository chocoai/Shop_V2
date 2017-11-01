package com.lecshop.spu;

import com.lecshop.sku.bean.Sku;
import com.lecshop.sku.service.SkuService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by dujinkai on 17/6/7.
 * 单品控制器
 */
@Controller
public class SkuController {

    /**
     * 注入单品服务接口
     */
    @Autowired
    private SkuService skuService;

    /**
     * 分页查询单品信息
     *
     * @param pageHelper 分页帮助类
     * @param name       单品名称
     * @param skuNo      单品编号
     * @return 返回单品信息(包括规格信息)
     */
    @RequestMapping("/queryskus")
    @ResponseBody
    public BaseResponse querySkus(PageHelper<Sku> pageHelper, String name, String skuNo) {
        return BaseResponse.build(skuService.querySkusWithSpecs(pageHelper, CommonConstant.ADMIN_STOREID, name, skuNo));
    }
}
