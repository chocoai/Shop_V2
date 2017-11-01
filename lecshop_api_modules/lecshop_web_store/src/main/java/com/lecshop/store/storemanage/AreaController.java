package com.lecshop.store.storemanage;

import com.lecshop.area.bean.Province;
import com.lecshop.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dujinkai on 17/5/15.
 * 区域控制器
 */
@Controller
public class AreaController {

    /**
     * 注入地区服务接口
     */
    @Autowired
    private AreaService areaService;


    /**
     * 查询所有省份  包括省份下面的市
     *
     * @return 返回所有省份
     */
    @RequestMapping("/store_queryallprovinceswithcity")
    @ResponseBody
    public List<Province> queryAllProvincesWithCity() {
        return areaService.queryAllProvincesWithCity();
    }
}
