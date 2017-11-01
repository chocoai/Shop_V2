package com.lecshop.system;

import com.lecshop.upyun.bean.UpYunSetting;
import com.lecshop.upyun.service.UpYunService;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * Created by dujinkai on 17/5/8.
 * 又拍云控制器
 */
@Controller
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
    @RequestMapping("uploadtoupyun")
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

    /**
     * 跳转到又拍云页面
     *
     * @return 返回又拍云页面
     */
    @RequestMapping("toupyun")
    public ModelAndView toUpYun() {
        return new ModelAndView("system/upyun").addObject("upyun", upYunService.queryUpYunSetting());
    }

    /**
     * 修改又拍云信息
     *
     * @param upYunSetting 又拍云信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("updateupyun")
    @ResponseBody
    public int updateUpYun(@RequestBody UpYunSetting upYunSetting) {
        return upYunService.updateUpYun(upYunSetting);
    }
}
