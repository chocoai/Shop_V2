package com.lecshop.site;

import com.lecshop.friendlink.bean.FriendLink;
import com.lecshop.friendlink.service.FriendLinkService;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 友情链接控制器
 *
 * Created by LecShop on 2017/5/19.
 */
@Controller
public class FriendLinkController {

    /**
     * 注入友情链接服务接口
     */
    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 跳转到友情链接页面
     *
     * @return 友情链接页面
     */
    @RequestMapping("/tofriendlink")
    public ModelAndView toFriendLink() {
        return new ModelAndView("site/friendlink");
    }

    /**
     *查询友情链接
     *
     * @param pageHelper 分页帮助类
     * @param name       链接名称
     * @param url        链接地址
     * @return           返回链接信息
     */
    @RequestMapping("/queryfriendlinks")
    @ResponseBody
    public BaseResponse queryFriendLinks(PageHelper<FriendLink> pageHelper, String name, String url) {
        return BaseResponse.build(friendLinkService.queryFriendLinks(pageHelper, name, url));
    }

    /**
     * 根据友情链接id查询友情链接信息
     *
     * @param id 友情链接id
     * @return   返回友情链接信息
     */
    @RequestMapping("/queryfriendlinkbyid")
    @ResponseBody
    public FriendLink queryFriendLinkById(long id) {
        return friendLinkService.queryFriendLinkById(id);
    }

    /**
     * 修改友情链接
     *
     * @param  friendLink 友情链接信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatefriendlink")
    @ResponseBody
    public int updateFriendLink(@RequestBody FriendLink friendLink) {
        return friendLinkService.updateFriendLink(friendLink);
    }

    /**
     * 新增友情链接
     *
     * @param  friendLink 友情链接
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addfriendlink")
    @ResponseBody
    public int addFriendLink(@RequestBody FriendLink friendLink) {
        return friendLinkService.addFriendLink(friendLink);
    }

    /**
     * 删除友情链接
     *
     * @param  id 友情链接id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("deletefriendlink")
    @ResponseBody
    public int deleteFriendLink(long id) {
        return friendLinkService.deleteFriendLink(id);
    }

    /**
     * 批量删除友情链接
     *
     * @param ids 友情链接id数组
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("batchdeletefriendlinks")
    @ResponseBody
    public int batchDeleteFriendLinks(long [] ids) {
        return friendLinkService.batchDeleteFriendLinks(ids);
    }
}
