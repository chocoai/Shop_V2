package com.lecshop.openstore.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 店铺信息实体类
 *
 * @author sunluyang on 2017/6/13.
 */
@Data
public class StoreInfo {
    /**
     * 主键id
     */
    private long id;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺状态： 0 填写资料中 1 店铺审核中 2 审核通过 3 审核不通过
     */
    private String status;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 公司电话
     */
    private String companyPhone;
    /**
     * 公司邮箱
     */
    private String companyEmail;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 法定代表人
     */
    private String legalPerson;
    /**
     * 身份证号码
     */
    private String cardNo;
    /**
     * 身份证照片
     */
    private String carPic;
    /**
     * 营业执照号
     */
    private String busLicense;
    /**
     * 营业执照图片
     */
    private String busLicensePic;
    /**
     * 组织机构代码图片
     */
    private String orgPic;
    /**
     * 税务登记图片
     */
    private String taxPic;
    /**
     * 银行开户名
     */
    private String bankUserName;
    /**
     * 公司银行账号
     */
    private String bankAccount;
    /**
     * 开户银行支行名称
     */
    private String bankName;
    /**
     * 支行银行号
     */
    private String bankNumber;
    /**
     * 开户银行所在地
     */
    private String bankAddress;
    /**
     * 开户银行许可证电子版
     */
    private String bankPic;
    /**
     * 结算周期
     */
    private String billingCycle;
    /**
     * 删除标记 0 未删除 1 删除 默认0
     */
    private String delFlag;
    /**
     * 0三证合一 1 三证不合一
     */
    private String isMerge;
    /**
     * 客服QQ
     */
    private String serviceQQ;
    /**
     * 拒绝原因
     */
    private String reason;
    /**
     * 店铺有效期
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime effectiveTime = LocalDateTime.now().plusYears(1);
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;
    /**
     * 删除时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime delTime;

    public StoreInfo getStoreInfoToEditStoreName(long storeId, String status, String storeName) {
        this.id = storeId;
        this.status = status;
        this.storeName = storeName;
        return this;
    }
}
