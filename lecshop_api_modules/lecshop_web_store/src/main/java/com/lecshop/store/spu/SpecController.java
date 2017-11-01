package com.lecshop.store.spu;

import com.lecshop.spec.bean.Spec;
import com.lecshop.spec.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dujinkai on 17/6/14.
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
     * 查询指定的规格信息
     *
     * @param ids 规格id集合
     * @return 返回指定的规格信息(包含规格值)
     */
    @RequestMapping("/store_queryspecsbyids")
    @ResponseBody
    public List<Spec> querySpecsByIds(Long[] ids) {
        return specService.querySpecsByIds(ids);
    }
}
