package com.lecshop.wechatmenu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lecshop.payset.bean.PaySetCommon;
import com.lecshop.payset.mapper.PaySetMapper;
import com.lecshop.wechatmenu.bean.MenuInfo;
import com.lecshop.wechatmenu.bean.WeChatInfo;
import com.lecshop.wechatmenu.bean.WeChatSet;
import com.lecshop.wechatmenu.mapper.WeChatSetMapper;
import com.lecshop.wechatmenu.service.WeChatMenuSetService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信菜单设置service
 *
 * @author sunluyang on 2017/5/24.
 */
@Service
public class WeChatMenuSetServiceImpl implements WeChatMenuSetService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(WeChatMenuSetServiceImpl.class);

    /**
     * 微信获取菜单接口
     */
    private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=";

    /**
     * 微信获取token接口
     */
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";

    /**
     * 微信创建菜单接口
     */
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";


    /**
     * 注入支付接口设置mapper
     */
    @Autowired
    private PaySetMapper paySetMapper;

    /**
     * 注入微信设置mapper
     */
    @Autowired
    private WeChatSetMapper weChatSetMapper;

    /**
     * 根据店铺id查询,微信设置信息,获取菜单
     *
     * @param storeId 店铺id
     * @return 获取菜单
     */
    @Override
    public MenuInfo queryWechatInfo(long storeId) {
        logger.debug("queryWechatInfo and storeId:{}", storeId);
        //请求链接
        HttpGet httpGet = new HttpGet(GET_MENU_URL + getWeChatToken(storeId).get("access_token"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 获取菜单
        try {
            return JSONObject.parseObject(EntityUtils.toString(httpclient.execute(httpGet).getEntity(), "UTF-8"), MenuInfo.class);
        } catch (IOException e) {
            logger.error("queryWechatInfo exception", e);
        } finally {
            try {
                // 关闭连接,释放资源
                httpclient.close();
            } catch (IOException e) {
                logger.error("queryWechatInfo exception", e);
            }
        }
        return null;
    }

    /**
     * 修改微信菜单
     *
     * @param menuInfo 菜单对象
     * @param storeId  店铺id
     * @return 返回码 -1失败 1成功
     */
    @Override
    public int editWeChatMenu(String menuInfo, long storeId) {
        logger.debug("editWeChatMenu and menuInfo:{}\r\n storeId:{}", menuInfo, storeId);
        HttpPost httpPost = new HttpPost(CREATE_MENU_URL + getWeChatToken(storeId).get("access_token"));
        httpPost.setEntity(new StringEntity(menuInfo.replaceAll("\\\\", "\""), "UTF-8"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = "";
        try {
            result = EntityUtils.toString(httpclient.execute(httpPost).getEntity(), "UTF-8");
        } catch (IOException e) {
            logger.error("queryWechatInfo exception", e);
        }
        if (result.indexOf("\"errmsg\":\"ok\"") != -1) {
            return 1;
        } else {
            logger.error("queryWechatInfo error...");
            return 0;
        }
    }

    /**
     * 根据店铺id查询微信token
     *
     * @param storeId 店铺id
     * @return token Map
     */
    @Override
    public Map<String, String> getWeChatToken(long storeId) {
        logger.debug("getWeChatToken and storeId:{}", storeId);
        //查询admin微信配置信息
        PaySetCommon paySetCommon = PaySetCommon.getPaySetCommon(new PaySetCommon(), paySetMapper.queryPaySet());
        WeChatSet weChatSet = queryStoreWeChatSet(storeId);
        //查询商家公众号配置信息--->storeId不为零则为第三方,查询微信appId和screenId
        WeChatInfo weChatInfo = storeId == 0
                ? new WeChatInfo(0, paySetCommon.getWechatPaySet().getAppId(), paySetCommon.getWechatPaySet().getAppSecret())
                : new WeChatInfo(storeId, weChatSet.getAppId(), weChatSet.getAppSecret());
        //调用微信获取token接口
        HttpGet httpGet = new HttpGet(GET_TOKEN_URL + weChatInfo.getAppId() + "&secret=" + weChatInfo.getAppSecret());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Map<String, String> resultMap = new HashMap<>();
        try {
            JSONObject jsonData = JSONObject.parseObject(EntityUtils.toString(httpclient.execute(httpGet).getEntity()));
            resultMap.put("access_token", jsonData.getString("access_token").toString());
            resultMap.put("expires_in", jsonData.getString("expires_in").toString());
        } catch (IOException e) {
            logger.error("getWeChatToken exception", e);
        } finally {
            try {
                // 关闭连接,释放资源
                httpclient.close();
            } catch (IOException e) {
                logger.error("getWeChatToken exception", e);
            }
        }
        return resultMap;
    }

    /**
     * 根据店铺id查询到店铺的微信设置
     *
     * @param storeId 店铺id
     * @return 微信设置
     */
    @Override
    public WeChatSet queryStoreWeChatSet(long storeId) {
        logger.debug("queryStoreWeChatSet and storeId:{}", storeId);
        return weChatSetMapper.queryWeChatSet(storeId);
    }

    /**
     * 根据店铺id编辑店铺的微信设置
     *
     * @param storeId   店铺id
     * @param weChatSet 微信信息实体类
     * @return 编辑返回码
     */
    @Override
    public int editStoreWeChatSet(long storeId, WeChatSet weChatSet) {
        if (Objects.isNull(weChatSet)) {
            logger.error("editStoreWeChatSet error due to weChatSet is null");
            return -1;
        }
        logger.debug("editStoreWeChatSet and storeId:{}\n\r weChatSet:{}", storeId, weChatSet);
        weChatSet.setStoreId(storeId);
        return weChatSetMapper.editStoreWeChatSet(weChatSet);
    }
}
