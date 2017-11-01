package com.lecshop.upyun.service;


import com.lecshop.upyun.bean.UpYunSetting;

import java.io.InputStream;

/**
 * Created by dujinkai on 17/5/8.
 * 又拍云服务接口
 */
public interface UpYunService {

    /**
     * 上传图片到又拍云
     *
     * @param inputStream 输入流
     * @param bytes       图片的字节
     * @return 返回图片在又拍云的地址
     */
    String uploadToUpYun(InputStream inputStream, byte[] bytes);

    /**
     * 查询系统设置的又拍云信息
     *
     * @return 返回又拍云信息
     */
    UpYunSetting queryUpYunSetting();

    /**
     * 修改又拍云信息
     *
     * @param upYunSetting 又拍云实体
     * @return 成功返回1  失败返回0
     */
    int updateUpYun(UpYunSetting upYunSetting);
}
