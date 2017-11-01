package com.lecshop.store.spu;

import com.lecshop.helpcenter.bean.ServiceSupport;
import com.lecshop.helpcenter.service.ServiceSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 服务支持控制器
 * <p>
 * Created by LecShop on 2017/6/1.
 */
@Controller
public class ServiceSupportController {

    /**
     * 自动注入服务支持业务层接口
     */
    @Autowired
    private ServiceSupportService serviceSupportService;


    /**
     * 查询所有服务支持
     *
     * @return 返回所有服务支持
     */
    @RequestMapping("/store_queryallservicesupport")
    @ResponseBody
    public List<ServiceSupport> queryAllServiceSupport() {
        return serviceSupportService.queryAllServiceSupport();
    }

}
