package com.lecshop.upyun.service.impl;

import com.lecshop.upyun.bean.UpYunSetting;
import com.lecshop.upyun.mapper.UpYunMapper;
import com.lecshop.upyun.service.UpYunService;
import com.lecshop.util.YunUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Created by dujinkai on 17/5/8.
 * 又拍云服务实现类
 */
@Service
public class UpYunServiceImpl implements UpYunService {


    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(UpYunServiceImpl.class);

    /**
     * 注入又拍云数据库接口
     */
    @Autowired
    private UpYunMapper upYunMapper;

    @Override
    public String uploadToUpYun(InputStream inputStream, byte[] bytes) {

        logger.debug("Begin to uploadToUpYun....");
        return YunUploadUtils.getInstance().uploadToUpYun(queryUpYunSetting().getUpYunConf(), inputStream, bytes);
    }

    @Override
    public UpYunSetting queryUpYunSetting() {
        logger.debug("Begin to queryUpYunSetting....");
        return upYunMapper.queryUpYunSetting();
    }

    @Override
    public int updateUpYun(UpYunSetting upYunSetting) {
        logger.error("updateUpYun and upYunSetting:{}", upYunSetting);
        return upYunMapper.updateUpYun(upYunSetting);
    }
}
