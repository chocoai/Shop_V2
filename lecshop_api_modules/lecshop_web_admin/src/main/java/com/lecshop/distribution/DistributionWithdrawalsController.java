package com.lecshop.distribution;

import com.lecshop.distributionwithdrawals.bean.Withdrawals;
import com.lecshop.distributionwithdrawals.service.WithdrawalsService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 提现列表控制器
 * <p>
 * Created by LecShop on 2017/5/24.
 */
@Controller
public class DistributionWithdrawalsController {

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
    @RequestMapping("/towithdrawalslist")
    public ModelAndView toWithdrawalsList() {
        return new ModelAndView("distribution/withdrawals");
    }

    /**
     * 查询提现列表
     *
     * @param pageHelper 分页帮助类
     * @param tradeno    交易流水号
     * @return 返回提现信息
     */
    @RequestMapping("/querywithdrawals")
    @ResponseBody
    public BaseResponse queryWithdrawals(PageHelper<Withdrawals> pageHelper, String tradeno) {
        return BaseResponse.build(withdrawalsService.queryWithdrawals(pageHelper, tradeno, CommonConstant.ADMIN_STOREID));
    }

    /**
     * 拒绝提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/refuse")
    @ResponseBody
    public int refuseApply(@RequestBody Withdrawals withdrawals) {
        return withdrawalsService.refuseApply(withdrawals, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 驳回提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/reject")
    @ResponseBody
    public int rejectApply(@RequestBody Withdrawals withdrawals) {
        return withdrawalsService.rejectApply(withdrawals, CommonConstant.ADMIN_STOREID);
    }

    /**
     * 通过提现申请
     *
     * @param withdrawals 提现信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/pass")
    @ResponseBody
    public int passApply(@RequestBody Withdrawals withdrawals) {
        return withdrawalsService.passApply(withdrawals, CommonConstant.ADMIN_STOREID);
    }

}
