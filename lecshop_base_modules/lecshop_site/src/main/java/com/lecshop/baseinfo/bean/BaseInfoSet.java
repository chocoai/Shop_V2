package com.lecshop.baseinfo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 基本信息设置实体类
 *
 * @author sunluyang on 2017/5/17.
 */
@Data
public class BaseInfoSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 前端地址
     */
    private String siteUrl;
    /**
     * 网站LOGO(前端搜索页旁边和店铺登陆页)
     */
    private String logo;
    /**
     * pc端，admin端，store端标签图片
     */
    private String labelLog;
    /**
     * pc端登录背景图片
     */
    private String siteLoginPic;
    /**
     * pc端注册背景图片
     */
    private String siteRegisterPic;
    /**
     * 店铺端登录图片
     */
    private String storeLoginPic;
    /**
     * 店铺端注册图片
     */
    private String storeRegisterPic;
    /**
     * 版权信息（pc和store底部）
     */
    private String copyrightInfo;
    /**
     * 注册协议（pc端）
     */
    private String siteRegisterProtocol;
    /**
     * 店铺端注册协议
     */
    private String storeRegisterProtocol;
    /**
     * 开店协议
     */
    private String storeOpenProtocol;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * admin端登录logo
     */
    private String adminLogo;
    /**
     * admin端首页logo
     */
    private String adminIndexLogo;
    /**
     * 是否开启验证码   0 开启 1 不开启 默认0
     */
    private String captchaOpen;
    /**
     * 店铺商品审核开关  0 需要审核 1 不需要 默认0
     */
    private String storeSpuAudit;

    /**
     * 判断店铺商品是否需要审核
     *
     * @return 需要返回true  不需要返回false
     */
    @JsonIgnore
    public boolean isSpuNeedAudit() {
        return "0".equals(this.storeSpuAudit);
    }
}
