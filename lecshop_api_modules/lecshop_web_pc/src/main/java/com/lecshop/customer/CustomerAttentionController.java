package com.lecshop.customer;

import com.lecshop.attention.bean.Attention;
import com.lecshop.attention.bean.AttentionStore;
import com.lecshop.attention.service.AttentionService;
import com.lecshop.attention.service.AttentionStoreService;
import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 关注商品/店铺控制器
 *
 * @author sunluyang on 2017/7/3.
 */
@Controller
@RequestMapping("/customer")
public class CustomerAttentionController {

    /**
     * 注入关注商品service
     */
    @Autowired
    private AttentionService attentionService;
    /**
     * 注入关注店铺service
     */
    @Autowired
    private AttentionStoreService attentionStoreService;

    /**
     * 跳转到关注商品页面
     *
     * @return 关注商品页面
     */
    @RequestMapping("/toattentionsku")
    public ModelAndView toAttentionSku() {
        return new ModelAndView("customer/attentionsku");
    }

    /**
     * 获取用户关注的商品信息
     *
     * @param pageHelper 分页帮助类
     * @param request    request请求
     * @return 返回分页关注商品
     */
    @RequestMapping("/queryattentionsku")
    @ResponseBody
    public BaseResponse queryAttentionSku(PageHelper<Attention> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(attentionService.queryAttentionsForCustomerCentre(pageHelper, PcLoginUtils.getInstance().getCustomerIdFromSession(request)));
    }

    /**
     * 取消关注商品
     *
     * @param skuId   单品id
     * @param request request请求
     * @return 返回删除码
     */
    @RequestMapping("/cancelattention")
    @ResponseBody
    public int cancelAttention(String skuId, HttpServletRequest request) {
        return attentionService.cancelAttention(skuId, PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }

    /**
     * 跳转到关注的店铺页面
     *
     * @return 关注的店铺页面
     */
    @RequestMapping("/toattentionstore")
    public ModelAndView toAttentionStore() {
        return new ModelAndView("customer/attentionstore");
    }

    /**
     * 查询关注店铺
     *
     * @param request request请求
     * @return 返回关注店铺集合
     */
    @RequestMapping("/queryattentionstore")
    @ResponseBody
    public BaseResponse queryAttentionStore(PageHelper<AttentionStore> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(attentionStoreService.queryAttentionByCustomerId(pageHelper, PcLoginUtils.getInstance().getCustomerIdFromSession(request)));
    }

    /**
     * 取消关注店铺
     *
     * @param storeId 店铺id
     * @param request 请求id
     * @return 删除返回码
     */
    @RequestMapping("/cancelstoreattention")
    @ResponseBody
    public int cancelStoreAttention(long storeId, HttpServletRequest request) {
        return attentionStoreService.cancelStoreAttention(storeId, PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }
}
