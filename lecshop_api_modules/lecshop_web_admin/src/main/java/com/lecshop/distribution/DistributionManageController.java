package com.lecshop.distribution;

import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.distributionset.bean.DistributionSet;
import com.lecshop.distributionset.service.DistributionSetService;
import com.lecshop.distributorlist.bean.Distributor;
import com.lecshop.distributorlist.service.DistributorService;
import com.lecshop.util.*;
import com.lecshop.wechatmenu.bean.MenuInfo;
import com.lecshop.wechatmenu.service.WeChatMenuSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


/**
 * 分销设置控制器
 *
 * @author sunluyang on 2017/5/24.
 */
@Controller
public class DistributionManageController {
    /**
     * 注入分销商设置service
     */
    @Autowired
    private DistributionSetService distributionSetService;
    /**
     * 注入微信菜单设置service
     */
    @Autowired
    private WeChatMenuSetService weChatMenuSetService;
    /**
     * 注入分销商service
     */
    @Autowired
    private DistributorService distributorService;
    /**
     * 注入基本信息设置service
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;

    /**
     * 跳转到分销设置页面
     *
     * @return 分销设置页面
     */
    @RequestMapping("/todistributionset")
    public ModelAndView toDistributionSet() {
        return new ModelAndView("distribution/distributionset");
    }

    /**
     * 跳转到微信菜单设置
     *
     * @return 微信菜单设置页面
     */
    @RequestMapping("/towechatmenuset")
    public ModelAndView toWechatMenuSet() {
        return new ModelAndView("distribution/wechatmenu");
    }

    /**
     * 跳转到分销商列表
     *
     * @return 分销商列表
     */
    @RequestMapping("/todistributorlist")
    public ModelAndView toDistributorList() {
        return new ModelAndView("distribution/distributorlist");
    }

    /**
     * 跳转到微信支付信息页面
     *
     * @return 微信支付信息页面
     */
    @RequestMapping("/towechatpayinfo")
    public ModelAndView toWeChatPayInfo() {
        return new ModelAndView("distribution/wechatpayinfo").addObject("url", baseInfoSetService.queryBaseInfoSet().getSiteUrl() + "/mobile/initMain.htm?storeId=0&reStoreId=0");
    }

    /**
     * 根据店铺id查询分销设置(admin端 店铺id默认为0)
     *
     * @return 分销设置实体类
     */
    @RequestMapping("/querydistributionset")
    @ResponseBody
    public DistributionSet queryDistributionSet() {
        return distributionSetService.queryDistributionSet(CommonConstant.ADMIN_STOREID);
    }

    /**
     * 编辑分销设置
     *
     * @param distributionSet 分销设置实体类
     * @return 编辑返回码 -1失败 1成功
     */
    @RequestMapping("/editdistributionset")
    @ResponseBody
    public int editDistributionSet(@RequestBody DistributionSet distributionSet) {
        distributionSet.setStoreId(0);
        return distributionSetService.editDistributionSet(distributionSet);
    }

    /**
     * 查询admin端的微信菜单
     *
     * @return MenuInfo菜单
     */
    @RequestMapping("/queryadminwechatinfo")
    @ResponseBody
    public MenuInfo queryWechatInfo() {
        return weChatMenuSetService.queryWechatInfo(0);
    }

    /**
     * 编辑微信菜单
     *
     * @param menuInfo 菜单字符串
     * @return 返回码 -1失败 1成功
     */
    @RequestMapping("/editwechatmenu")
    @ResponseBody
    public int editWeChatMenu(String menuInfo) {
        return weChatMenuSetService.editWeChatMenu(menuInfo, 0);
    }

    /**
     * 查询该店铺的分销商
     *
     * @param pageHelper 分页帮助类
     * @param username   用户名
     * @return 分销商
     */
    @RequestMapping("/querydistributor")
    @ResponseBody
    public BaseResponse queryDistributor(PageHelper<Distributor> pageHelper, String username) {
        return BaseResponse.build(distributorService.queryDistributor(pageHelper, 0, username));
    }

    /**
     * 删除分销商
     *
     * @param distributor 分销商实体类
     * @return 删除返回码-1 失败 1成功
     */
    @RequestMapping("/deletedistributor")
    @ResponseBody
    public int deleteDistributor(@RequestBody Distributor distributor) {
        return distributorService.deleteDistributor(distributor);
    }

    /**
     * 将微信首页地址制作成二维码
     *
     * @param response 请求响应
     * @throws Exception 抛出异常
     */
    @RequestMapping("/createqrcode")
    public void createQrCode(HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        QRCodeUtils.createQrCode(baseInfoSetService.queryBaseInfoSet().getSiteUrl() + "/mobile/initMain.htm?storeId=0&reStoreId=0", 500, 500, "png", stream);
        stream.flush();
        stream.close();
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return -1失败 1成功
     */
    @RequestMapping("/fileupload")
    @ResponseBody
    public int fileUpload(@RequestParam("fileUpload") CommonsMultipartFile file) {
        if (FileUploadUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            return 1;
        }
        return -1;
    }
}
