package com.lecshop.utils;

import com.lecshop.upyun.service.UpYunService;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Objects;

/**
 * 又拍云控制器
 *
 * @author sunluyang on 2017/7/5.
 */
@Controller
@RequestMapping("/customer")
public class UpYunController {

    /**
     * 注入又拍云服务接口
     */
    @Autowired
    private UpYunService upYunService;

    /**
     * 上传图片
     *
     * @return 返回图片在又拍云的地址
     * @throws Exception
     */
    @RequestMapping("/pcuploadtoupyun")
    @ResponseBody
    @UnAuth
    public String uploadToUpYun(MultipartHttpServletRequest request, String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            name = "image";
        }
        MultipartFile multipartFile = request.getFile(name);
        if (Objects.isNull(multipartFile)) {
            return "";
        }
        return upYunService.uploadToUpYun(multipartFile.getInputStream(), multipartFile.getBytes());
    }
}
