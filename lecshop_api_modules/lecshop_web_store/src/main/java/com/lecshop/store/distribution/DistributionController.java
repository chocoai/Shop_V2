package com.lecshop.store.distribution;

import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.distributionset.bean.DistributionSet;
import com.lecshop.distributionset.service.DistributionSetService;
import com.lecshop.distributorlist.bean.Distributor;
import com.lecshop.distributorlist.service.DistributorService;
import com.lecshop.store.storeutil.StoreLoginUtils;
import com.lecshop.util.*;
import com.lecshop.wechatmenu.bean.MenuInfo;
import com.lecshop.wechatmenu.bean.WeChatSet;
import com.lecshop.wechatmenu.service.WeChatMenuSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * store分销管理
 *
 * @author sunluyang on 2017/6/8.
 */
@Controller
public class DistributionController {

    /**
     * 注入分销设置service
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
     * 跳转到分销设置
     *
     * @return 分销设置页面
     */
    @RequestMapping("/store_todistributionset")
    public ModelAndView toDistributionSet() {
        return new ModelAndView("distribution/distributionset");
    }

    /**
     * 跳转到微信菜单设置
     *
     * @return 微信菜单设置页面
     */
    @RequestMapping("/store_towechatmenuset")
    public ModelAndView toWeChatMenuSet() {
        return new ModelAndView("distribution/wechatmenu");
    }

    /**
     * 跳转到分销商列表
     *
     * @return 分销商列表页面
     */
    @RequestMapping("/store_todistributorlist")
    public ModelAndView toDistributorList() {
        return new ModelAndView("distribution/distributorlist");
    }

    /**
     * 跳转到微信分销商信息页面
     *
     * @return 微信分销商信息页面
     */
    @RequestMapping("/store_towechatpayinfo")
    public ModelAndView toWeChatPayInfo(HttpServletRequest request) {
        long storeId = StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId();
        return new ModelAndView("distribution/wechatpayinfo").addObject("url", baseInfoSetService.queryBaseInfoSet().getSiteUrl() + "/mobile/thirdStoreIndex.htm?storeId=" + storeId + "&reStoreId=" + storeId);
    }

    /**
     * 根据店铺id查询分销设置
     *
     * @return 分销设置实体类
     */
    @RequestMapping("/store_querydistributionset")
    @ResponseBody
    public DistributionSet queryDistributionSet(HttpServletRequest request) {
        return distributionSetService.queryDistributionSet(StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId());
    }

    /**
     * 编辑分销设置
     *
     * @param distributionSet 分销设置实体类
     * @return 编辑返回码 -1失败 1成功
     */
    @RequestMapping("/store_editdistributionset")
    @ResponseBody
    public int editDistributionSet(@RequestBody DistributionSet distributionSet, HttpServletRequest request) {
        return distributionSetService.editDistributionSet(new DistributionSet().getEditDistributionSetData(distributionSet,StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId()));
    }

    /**
     * 查询store端的微信菜单
     *
     * @return MenuInfo菜单
     */
    @RequestMapping("/store_queryadminwechatinfo")
    @ResponseBody
    public MenuInfo queryWechatInfo(HttpServletRequest request) {
        return weChatMenuSetService.queryWechatInfo(StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId());
    }

    /**
     * 编辑微信菜单
     *
     * @param menuInfo 菜单字符串
     * @return 返回码 -1失败 1成功
     */
    @RequestMapping("/store_editwechatmenu")
    @ResponseBody
    public int editWeChatMenu(String menuInfo, HttpServletRequest request) {
        return weChatMenuSetService.editWeChatMenu(menuInfo, StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId());
    }

    /**
     * 查询该店铺的分销商
     *
     * @param pageHelper 分页帮助类
     * @param username   用户名
     * @return 分销商
     */
    @RequestMapping("/store_querydistributor")
    @ResponseBody
    public BaseResponse queryDistributor(PageHelper<Distributor> pageHelper, String username, HttpServletRequest request) {
        return BaseResponse.build(distributorService.queryDistributor(pageHelper, StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId(), username));
    }

    /**
     * 删除分销商
     *
     * @param distributor 分销商实体类
     * @return 删除返回码-1 失败 1成功
     */
    @RequestMapping("/store_deletedistributor")
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
    @RequestMapping("/store_createqrcode")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        long storeId = StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId();
        QRCodeUtils.createQrCode(baseInfoSetService.queryBaseInfoSet().getSiteUrl() + "/mobile/thirdStoreIndex.htm?storeId=" + storeId + "&reStoreId=" + storeId, 500, 500, "png", stream);
        stream.flush();
        stream.close();
    }

    /**
     * 根据店铺id查询到店铺的微信设置
     *
     * @return 微信设置
     */
    @RequestMapping("/store_wechatsetinfo")
    @ResponseBody
    public WeChatSet queryStoreWeChatSetInfo(HttpServletRequest request) {
        return weChatMenuSetService.queryStoreWeChatSet(StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId());
    }

    /**
     * 根据店铺id编辑店铺的微信设置
     *
     * @return 编辑返回码
     */
    @RequestMapping("/store_editwechatinfo")
    @ResponseBody
    public int editStoreWeChatInfo(HttpServletRequest request, @RequestBody WeChatSet weChatSet) {
        return weChatMenuSetService.editStoreWeChatSet(StoreLoginUtils.getInstance().getCustomerFromSession(request).getStoreId(), weChatSet);
    }

    /**
     * 凭证文件上传
     *
     * @param file 文件
     * @return -1失败 1成功
     */
    @RequestMapping("/store_fileupload")
    @ResponseBody
    public int fileUpload(@RequestParam("fileUpload") CommonsMultipartFile file) {
        if (FileUploadUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            return 1;
        }
        return -1;
    }
}
