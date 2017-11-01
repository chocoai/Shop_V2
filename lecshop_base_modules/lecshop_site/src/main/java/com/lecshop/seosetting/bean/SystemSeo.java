package com.lecshop.seosetting.bean;

import lombok.Data;

/**
 * 系统seo设置实体类
 *
 * Created by LecShop on 2017/6/30.
 */
@Data
public class SystemSeo {

    /**
     * 主键id
     */
    private long id;

    /**
     * seo标题
     */
    private String title;

    /**
     * seo关键字
     */
    private String keyWord;

    /**
     * see描述
     */
    private String seoDesc;

    /**
     * 是否开启  0 未开启 1 开启
     */
    private String isOpen;

}
