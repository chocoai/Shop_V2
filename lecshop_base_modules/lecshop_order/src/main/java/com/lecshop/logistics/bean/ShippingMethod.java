package com.lecshop.logistics.bean;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式
 */
@Data
public class ShippingMethod {

    /**
     * 主键id
     */
    private long id;

    /**
     * 运费模版id
     */
    private long templateId;

    /**
     * 首件 或者 首重
     */
    private int first;

    /**
     * 首件或者首重的价格
     */
    private BigDecimal money;

    /**
     * 增加几件或者几克
     */
    private int firstPlu;

    /**
     * 增加的钱
     */
    private BigDecimal moenyPlu;

    /**
     * 是否默认  0 否 1 是 默认 0
     */
    private String isDefault;


    /**
     * 运费方式的区域
     */
    private String cityNames;


    /**
     * 运费方式的区域id
     */
    private String cityIds;


    /**
     * 运费方式区域
     */
    private List<ShippingMethodArea> shippingMethodAreas;

    /**
     * 获得市名称
     *
     * @return 返回市名称
     */
    public String getCityNames() {
        if (CollectionUtils.isEmpty(this.shippingMethodAreas)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        shippingMethodAreas.stream().forEach(shippingMethodArea -> sb.append(shippingMethodArea.getCity().getName()).append(","));

        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    /**
     * 获得市id
     *
     * @return 返回市id
     */
    public String getCityIds() {
        if (CollectionUtils.isEmpty(this.shippingMethodAreas)) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        shippingMethodAreas.stream().forEach(shippingMethodArea -> sb.append(shippingMethodArea.getCityId()).append(","));
        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    /**
     * 设置运费方式区域的运费方式id
     */
    public void setShippingMethodAreaMethodId() {
        if (CollectionUtils.isEmpty(shippingMethodAreas)) {
            return;
        }

        shippingMethodAreas.stream().forEach(shippingMethodArea -> {
            shippingMethodArea.setTemplateId(this.templateId);
            shippingMethodArea.setShippingMethodId(this.id);
        });
    }
}
