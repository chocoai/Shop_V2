package com.lecshop.logistics.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 物流公司模版
 */
@Data
public class LogisticsTemplate {

    /**
     * 主键id
     */
    private long id;

    /**
     * 物流公司id
     */
    private long companyId;

    /**
     * 物流模版名称
     */
    private String name;

    /**
     * 是否默认模版 0 是 1 否 默认1
     */
    private String isDefault;

    /**
     * 谁承担运费 0 买家 1 商家 默认 0 买家
     */
    private String freightBear;

    /**
     * 计价方式 0 按件  1 按重量  默认0
     */
    private String pricintMethod;

    /**
     * 店铺id
     */
    private long storeId;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 物流公司
     */
    private LogisticsCompany logisticsCompany;

    /**
     * 物流模版的运费方式
     */
    private List<ShippingMethod> shippingMethods;

    /**
     * 设置运费方式的模版id
     */
    public void setShippingMethodTemplateId() {
        if (CollectionUtils.isEmpty(shippingMethods)) {
            return;
        }
        shippingMethods.stream().forEach(shippingMethod -> shippingMethod.setTemplateId(this.id));
    }

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

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

    /**
     * 判断是否是默认模版 是返回true 不是返回false
     *
     * @return 默认模版返回true  不是返回false
     */
    public boolean isDefaultTemplate() {
        return "0".equals(this.isDefault);
    }

}
