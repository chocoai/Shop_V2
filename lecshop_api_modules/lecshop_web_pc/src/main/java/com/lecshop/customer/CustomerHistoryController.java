package com.lecshop.customer;

import com.lecshop.historylist.bean.BrowseRecordList;
import com.lecshop.historylist.service.BrowseRecordService;
import com.lecshop.pcutil.PcLoginUtils;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 浏览历史控制器
 *
 * Created by LecShop on 2017/7/4.
 */
@Controller
@RequestMapping("/customer")
public class CustomerHistoryController {

    /**
     * 自动注入浏览历史service
     */
    @Autowired
    private BrowseRecordService browseRecordService;

    /**
     * 跳转至浏览历史页面
     *
     * @return 浏览历史页面
     */
    @RequestMapping("/tohistorylist")
    @UnAuth
    public ModelAndView toHistoryList() {
        return new ModelAndView("customer/historylist");
    }

    /**
     * 查询浏览历史
     *
     * @return 浏览历史
     */
    @RequestMapping("/queryhistorylist")
    @ResponseBody
    public List<BrowseRecordList> queryBrowseRecord(HttpServletRequest request) {
        return browseRecordService.queryBrowseRecord(PcLoginUtils.getInstance().getCustomerIdFromSession(request));
    }

    /**
     * 按天删除浏览历史
     *
     * @param createTime 日期
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/deletehistorybyday")
    @ResponseBody
    public int deleteBrowseRecordByDay(HttpServletRequest request, String createTime) {
        return browseRecordService.deleteBrowseRecordByDay(PcLoginUtils.getInstance().getCustomerIdFromSession(request), createTime);
    }

    /**
     * 根据id删除浏览历史
     *
     * @param id 浏览历史id
     * @return  成功返回1，失败返回0
     */
    @RequestMapping("/deletehistorybyid")
    @ResponseBody
    public int deleteBrowseRecordById(long id) {
        return browseRecordService.deleteBrowseRecordById(id);
    }

}
