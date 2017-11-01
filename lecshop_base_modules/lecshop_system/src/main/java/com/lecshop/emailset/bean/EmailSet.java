package com.lecshop.emailset.bean;

import lombok.Data;

/**
 * 邮箱设置实体类
 */
@Data
public class EmailSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 发送者邮箱
     */
    private String senderEmail;
    /**
     * 发送至名称
     */
    private String senderName;
    /**
     * SMTP的服务器地址
     */
    private String smtpServer;
    /**
     * SMTP 的端口
     */
    private String smtpPort;
    /**
     * 邮箱帐号
     */
    private String username;
    /**
     * 邮箱密码
     */
    private String password;
}
