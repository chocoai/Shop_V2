package com.lecshop.wechatmenu.mapper;

import com.lecshop.wechatmenu.bean.WeChatSet;
import org.springframework.stereotype.Repository;

/**
 * 第三方微信设置mapper
 *
 * @author sunluyang on 2017/5/25.
 */
@Repository
public interface WeChatSetMapper {

    /**
     * 根据店铺id查询微信设置
     *
     * @param storeId 店铺id
     * @return 微信设置实体类
     */
    WeChatSet queryWeChatSet(long storeId);

    /**
     * 根据店铺id编辑店铺的微信设置
     *
     * @param weChatSet 微信信息实体类
     * @return 编辑返回码
     */
    int editStoreWeChatSet(WeChatSet weChatSet);
}
