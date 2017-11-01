package com.lecshop.customer;

import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.predeposit.bean.PredepositRecord;
import com.lecshop.predeposit.bean.QueryCriteria;
import com.lecshop.predeposit.service.PredepositRecordService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 预存款控制器
 *
 * Created by LecShop on 2017/7/5.
 */
@Controller
@RequestMapping("/customer")
public class CustomerPreDepositController {

    /**
     * 自动注入预存款service
     */
    @Autowired
    private PredepositRecordService predepositRecordService;

    /**
     * 跳转至预存款页面
     *
     * @return 预存款页面
     */
    @RequestMapping("/topredeposit")
    @UnAuth
    public ModelAndView toPredeposit() {
        return new ModelAndView("customer/predeposit");
    }

    /**
     * 跳转至预存款收入页面
     *
     * @return 预存款收入页面
     */
    @RequestMapping("/toamountreceived")
    @UnAuth
    public ModelAndView toAmountReceived() {
        return new ModelAndView("customer/amountreceived");
    }

    /**
     * 跳转至预存款支出页面
     *
     * @return 预存款支出页面
     */
    @RequestMapping("/toamountpaid")
    @UnAuth
    public ModelAndView toAmountPaid() {
        return new ModelAndView("customer/amountpaid");
    }


    /**
     * 查询预存款列表
     *
     * @param pageHelper 分页帮助类
     * @return 预存款列表
     */
    @RequestMapping("/querypredeposit")
    @ResponseBody
    public BaseResponse queryPreDeposit(HttpServletRequest request, PageHelper<PredepositRecord> pageHelper) {
        QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setCustomerId(PcLoginUtils.getInstance().getCustomerIdFromSession(request));
        return BaseResponse.build(predepositRecordService.queryPredepositRecords(pageHelper, queryCriteria));
    }

    /**
     * 查询预存款总额
     *
     * @return 预存款总额
     */
    @RequestMapping("/queryreepositcount")
    @ResponseBody
    public BigDecimal queryPreDepositCount(HttpServletRequest request) {
        return predepositRecordService.queryCutomerAllPredeposit(PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }

}
