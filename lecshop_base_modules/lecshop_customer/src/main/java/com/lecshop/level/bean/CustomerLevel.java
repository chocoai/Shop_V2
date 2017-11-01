package com.lecshop.level.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/22.
 * 会员等级
 */
@Data
public class CustomerLevel {

    /**
     * 主键id
     */
    private long id;

    /**
     * 会员等级名称
     */
    private String name;

    /**
     * 最小的消费金额(包含该值)
     */
    private int minMoney;

    /**
     * 最大的消费金额 (不包含该值)
     */
    private int maxMoney;

    /**
     * 删除标记 0 未删除  1 删除
     */
    private String delFlag;

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
     * 根据传入的金额判断  是否是当前等级
     *
     * @param money 传入金金额
     * @return 判断规则 [0,100)  前闭后开   包含0  不包含100
     */
    public boolean isCurrentLevl(BigDecimal money) {
        if (Objects.isNull(money)) {
            return false;
        }
        return money.intValue() >= this.minMoney && money.intValue() < this.maxMoney;
    }


    /**
     * 判断传入的会员等级消费金额和当前的会员等级消费金额是否有交集
     *
     * @param customerLevel 会员等级
     * @return 有返回true  没有返回false 传入的值为空  返回false
     */
    public boolean hasIntersection(CustomerLevel customerLevel) {
        if (Objects.isNull(customerLevel)) {
            return false;
        }

        // 如果传入的最小值比当前的最大值要大于等于 则肯定没有交集
        if (customerLevel.minMoney >= this.maxMoney) {
            return false;
        }

        // 如果传入的最小值在当前会员等级最大值和最小值之间(不包含最大值 包含最小值) 则肯定会有交集
        if (customerLevel.minMoney >= this.minMoney && customerLevel.minMoney < this.maxMoney) {
            return true;
        }

        // 如果传入的最小值在比当前的最小值还小 则看传入的最大值
        if (customerLevel.minMoney < this.minMoney) {
            if (customerLevel.maxMoney <= this.minMoney) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 构造未知等级实体
     *
     * @return 返回未知等级实体
     */
    public static CustomerLevel buildNoLevel() {
        CustomerLevel customerLevel = new CustomerLevel();
        customerLevel.name = "未知等级";
        return customerLevel;
    }
}
