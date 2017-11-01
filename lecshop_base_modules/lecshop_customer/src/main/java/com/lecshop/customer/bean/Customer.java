package com.lecshop.customer.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.level.bean.CustomerLevel;
import com.lecshop.util.CustomLocalDateTimeDeserializer;
import com.lecshop.util.CustomLocalDateTimeSerializer;
import com.lecshop.util.MD5Utils;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by dujinkai on 17/5/19.
 * 用户实体
 */
@Data
public class Customer {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户的头像
     */
    private String image;

    /**
     * 用户的真实姓名
     */
    private String releName;

    /**
     * 身份证号码
     */
    private String cardId;

    /**
     * 性别 0 保密 1男 2女 默认0
     */
    private String gender = "0";

    /**
     * 生日
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime birthday;

    /**
     * 月收入  0 无收入 1 2000以下 2 2000-3999 3 4000-5999
     * 4 6000－7999  5 8000以上
     */
    private String monthlyIncome;

    /**
     * 婚姻状况  0 保密 1未婚 2 已婚 默认0 保姆
     */
    private String marriageStatus = "0";

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户所在省份
     */
    private long province;

    /**
     * 用户所在市
     */
    private long city;

    /**
     * 用户所在区
     */
    private long county;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 兴趣爱好
     */
    private String interest;

    /**
     * 店铺id  平台的为0  默认为平台
     */
    private long storeId;

    /**
     * 1 普通用户 2 商家店铺用户
     */
    private String type;

    /**
     * 用户消费的总金额
     */
    private BigDecimal consumptionAmount = new BigDecimal(0);

    /**
     * 预存款支付密码
     */
    private String payPassword;

    /**
     * 用户注册来源 1 pc  2app  3 手机h5 4 管理员后台新增
     */
    private String source;

    /**
     * 用户状态 1 正常 2 冻结 默认1
     */
    private String status;

    /**
     * 手机是否验证  0 否 1 验证 默认0
     */
    private String isMobileVerification = "0";

    /**
     * 邮箱是否验证   0 否 1 验证 默认0
     */
    private String isEmailVerification = "0";

    /**
     * 会员所属的店铺id   平台的为0(分销的时候使用)
     */
    private long belongStoreId;

    /**
     * 会员的上级id 　如果为0 则说明没有上级
     */
    private long superiorId;

    /**
     * 会员所有的佣金
     */
    private BigDecimal allcommission;

    /**
     * 会员提现时候的授权id
     */
    private String withdrawopenid;

    /**
     * 登陆错误次数
     */
    private int loginErrorCount;

    /**
     * 删除标记 0 未删除 1删除 默认0
     */
    private String delFlag = "0";

    /**
     * 用户预存款总金额
     */
    private BigDecimal allPredeposit;

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
     * 锁定时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime lockTime;

    /**
     * 最后登录时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime lastLoginTime;

    /**
     * 会员等级
     */
    private CustomerLevel customerLevel;

    /**
     * 后端添加用户设置默认值
     *
     * @return 返回用户信息
     */
    public Customer setDefaultValuesForAdminAdd() {
        this.storeId = 0;
        this.type = "1";
        this.source = "4";
        this.status = "1";
        this.createTime = LocalDateTime.now();
        return this;
    }

    /**
     * 清除用户密码
     */
    public Customer clearPassword() {
        this.password = "**********";
        return this;
    }

    /**
     * 判断密码是否正确
     *
     * @param password 用户输入的密码
     * @return 正确返回true  不正确返回false
     */
    @JsonIgnore
    public boolean isPasswordRight(String password) {
        return StringUtils.isEmpty(password) ? false : MD5Utils.getInstance().createMd5(password).equals(this.password);
    }

    /**
     * 获取会员对象
     *
     * @param customerId 会员id
     * @return 会员对象
     */
    public Customer setCustomerId(long customerId) {
        this.id = customerId;
        return this;
    }
}
