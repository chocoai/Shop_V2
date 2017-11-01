package com.lecshop.customer;

import com.lecshop.level.bean.CustomerLevel;
import com.lecshop.level.service.CustomerLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dujinkai on 17/5/22.
 * 会员等级控制器
 */
@Controller
public class CustomerLevelController {

    /**
     * 会员等级服务接口
     */
    @Autowired
    private CustomerLevelService customerLevelService;


    /**
     * 查询会员等级
     *
     * @return 返回会员等级
     */
    @RequestMapping("/querycustomerlevels")
    @ResponseBody
    public ModelAndView queryCustomerLevels() {
        return new ModelAndView("customer/customerlevel").addObject("customerLevels", customerLevelService.queryAllCustomerLevels());
    }

    /**
     * 查询所有的会员等级
     *
     * @return 返回所有的会员等级
     */
    @RequestMapping("/queryalllevels")
    @ResponseBody
    public List<CustomerLevel> queryAllLevels() {
        return customerLevelService.queryAllCustomerLevels();
    }

    /**
     * 新增会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回 1 失败返回0 -1 消费金额段已经存在 添加失败
     */
    @RequestMapping("/addcustomerlevel")
    @ResponseBody
    public int addCustomerLevel(@RequestBody CustomerLevel customerLevel) {
        return customerLevelService.addCustomerLevel(customerLevel);
    }

    /**
     * 根据id删除会员等级
     *
     * @param id 会员等级id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/deletecustomerlevel")
    @ResponseBody
    public int deleteCustomerLevel(long id) {
        return customerLevelService.deleteCustomerLevel(id);
    }

    /**
     * 根据会员等级id查询会员等级信息
     *
     * @param id 会员等级id
     * @return 返回会员等级信息
     */
    @RequestMapping("/querycustomerlevelbyid")
    @ResponseBody
    public CustomerLevel queryCustomerLevelById(long id) {
        return customerLevelService.queryCustomerLevelById(id);
    }

    /**
     * 修改会员等级
     *
     * @param customerLevel 会员等级
     * @return 成功返回1 失败返回0 -1 消费金额段已经存在 修改
     */
    @RequestMapping("/updatecustomerlevel")
    @ResponseBody
    public int updateCustomerLevel(@RequestBody CustomerLevel customerLevel) {
        return customerLevelService.updateCustomerLevel(customerLevel);
    }
}
