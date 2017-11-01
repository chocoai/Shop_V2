package com.lecshop.customer;

import com.lecshop.pointset.bean.PointSeting;
import com.lecshop.pointset.service.PointSetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/5/23.
 * 积分设置控制器
 */
@Controller
public class PointSetingController {

    /**
     * 注入积分设置服务接口
     */
    @Autowired
    private PointSetingService pointSetingService;

    /**
     * 跳转到积分设置页面
     *
     * @return 返回积分设置页面
     */
    @RequestMapping("/topointseting")
    public ModelAndView toPoingSeting() {
        return new ModelAndView("customer/pointset").addObject("pointset", pointSetingService.queryPointSeting());
    }

    /**
     * 更新积分设置
     *
     * @param pointSeting 积分设置
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/updatepointseting")
    @ResponseBody
    public int updatePointSeting(@RequestBody PointSeting pointSeting) {
        return pointSetingService.updatePointSeting(pointSeting);
    }
}
