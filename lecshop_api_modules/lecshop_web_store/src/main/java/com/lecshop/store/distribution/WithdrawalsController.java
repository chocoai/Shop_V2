package com.lecshop.store.distribution;

import com.lecshop.distributionwithdrawals.bean.Withdrawals;
import com.lecshop.distributionwithdrawals.service.WithdrawalsService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 提现列表控制器
 * <p>
 * Created by LecShop on 2017/6/12.
 */
@Controller
public class WithdrawalsController {

    /**
     * 注入体现列表service
     */
    @Autowired
    private WithdrawalsService withdrawalsService;

    /**
     * 跳转至提现列表页面
     *
     * @return 提现列表页面
     */
    @RequestMapping("/store_towithdrawals")
    public ModelAndView toWithdrawals() {
        return new ModelAndView("distribution/withdrawals");
    }

    /**
     * 查询提现列表
     *
     * @param pageHelper 分页帮助类
     * @param tradeno    交易流水号
     * @return 返回提现信息
     */
    @RequestMapping("/store_querywithdrawals")
    @ResponseBody
    public BaseResponse queryWithdrawals(HttpServletRequest request, PageHelper<Withdrawals> pageHelper, String tradeno) {
        return BaseResponse.build(withdrawalsService.queryWithdrawals(pageHelper, tradeno, StoreLoginUtils.getInstance().getStoreIdFromSession(request)));
    }

    /**
     * 拒绝提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_refuse")
    @ResponseBody
    public int refuseApply(HttpServletRequest request, @RequestBody Withdrawals withdrawals) {
        return withdrawalsService.refuseApply(withdrawals, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 驳回提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_reject")
    @ResponseBody
    public int rejectApply(HttpServletRequest request, @RequestBody Withdrawals withdrawals) {
        return withdrawalsService.rejectApply(withdrawals, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

    /**
     * 通过提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/store_pass")
    @ResponseBody
    public int passApply(HttpServletRequest request, @RequestBody Withdrawals withdrawals) {
        return withdrawalsService.passApply(withdrawals, StoreLoginUtils.getInstance().getStoreIdFromSession(request));
    }

}
