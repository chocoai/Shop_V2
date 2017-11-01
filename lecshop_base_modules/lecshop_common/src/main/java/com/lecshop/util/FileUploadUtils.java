package com.lecshop.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author sunluyang on 2017/5/26.
 */
public class FileUploadUtils {

    /**
     * 调试日志
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    public static int fileUploadCommon(CommonsMultipartFile file, String propertiesPath, Object object) {
        //当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (!file.isEmpty()) {
            String path = filePath(propertiesPath, object) + "/" + file.getOriginalFilename();// 存放位置
            logger.debug("fileUploadCommon and Properties path:{}:", path);
            File destFile = new File(path);
            try {
                //该方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            } catch (IOException e) {
                logger.error("fileUploadCommon error", e);
            }
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 读取配置文件信息
     */
    public static Properties readValue(String propertiesPath, Object object) {
        Properties properties = new Properties();
        try (InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream(propertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("read Properties error", e);
        }
        return properties;
    }

    /**
     * 文件路径处理
     */
    private static String filePath(String propertiesPath, Object object) {
        //得到上传文件的保存目录
        String savePath = readValue(propertiesPath, object).getProperty("FILEPATH") + "FileUpload";
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            logger.debug("path is not exist to create path");
            //创建目录
            file.mkdir();
        }
        return savePath;
    }
}
