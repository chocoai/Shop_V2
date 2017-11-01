package com.lecshop.kaptcha;

import com.google.code.kaptcha.Producer;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by dujinkai on 17/5/2.
 * 控制器的公共类
 */
@Controller
public class CommonBaseController {

    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     * @param consumer 回调接口
     */
    public void createKaptcha(HttpServletResponse response, Consumer<String> consumer) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 获得生成的验证码
        String capText = producer.createText();

        // 回调
        consumer.accept(capText);

        // 返回验证码
        ImageIO.write(producer.createImage(capText), "jpg", response.getOutputStream());
    }
}
