package com.lecshop.store.spu;

import com.lecshop.type.bean.Type;
import com.lecshop.type.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by dujinkai on 17/6/14.
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
     * 查询类型详情
     *
     * @param id 类型id
     * @return 返回类型详情 包括类型,属性,属性值 和类型的规格
     */
    @RequestMapping("/store_querytypedetail")
    @ResponseBody
    public Type queryTypeDetail(long id) {
        return typeService.queryTypeDetail(id);
    }


}
