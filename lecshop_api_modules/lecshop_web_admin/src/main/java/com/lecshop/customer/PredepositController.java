package com.lecshop.customer;

import com.lecshop.predeposit.bean.PredepositRecord;
import com.lecshop.predeposit.bean.QueryCriteria;
import com.lecshop.predeposit.service.PredepositRecordService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/5/25.
 * 预存款控制器
 */
@Controller
public class PredepositController {

    /**
     * 预存款服务接口
     */
    @Autowired
    private PredepositRecordService predepositRecordService;


    /**
     * 跳转到预存款页面
     *
     * @param customerId 会员id
     * @return 返回预存款页面
     */
    @RequestMapping("/toquerypredeposit")
    public ModelAndView toQueryPredeposit(long customerId) {
        return new ModelAndView("customer/customerpredeposit").addObject("customerId", customerId);
    }

    /**
     * 分页查询预存款信息
     *
     * @param pageHelper    分页帮助类
     * @param queryCriteria 查询条件
     * @return 返回分类信息
     */
    @RequestMapping("/querypredeposits")
    @ResponseBody
    public BaseResponse queryPredeposits(PageHelper<PredepositRecord> pageHelper, QueryCriteria queryCriteria) {
        return BaseResponse.build(predepositRecordService.queryPredepositRecords(pageHelper, queryCriteria));
    }
}
