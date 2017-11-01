package com.lecshop.customer;

import com.lecshop.attention.bean.Attention;
import com.lecshop.attention.service.AttentionService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/5/27.
 * 商品关注控制器
 */
@Controller
public class AttentionController {

    /**
     * 注入商品关注服务接口
     */
    @Autowired
    private AttentionService attentionService;


    /**
     * 跳转到商品关注页面
     *
     * @param customerId 会员id
     * @return 返回商品关注页面
     */
    @RequestMapping("/toqueryattention")
    public ModelAndView toQueryAttention(long customerId) {
        return new ModelAndView("customer/customerattention").addObject("customerId", customerId);
    }

    /**
     * 查询商品关注
     *
     * @param pageHelper 分页帮助类
     * @param customerId 会员id
     * @return 返回商品关注
     */
    @RequestMapping("/queryattentions")
    @ResponseBody
    public BaseResponse queryAttentions(PageHelper<Attention> pageHelper, long customerId) {
        return BaseResponse.build(attentionService.queryAttentions(pageHelper, customerId));
    }
}
