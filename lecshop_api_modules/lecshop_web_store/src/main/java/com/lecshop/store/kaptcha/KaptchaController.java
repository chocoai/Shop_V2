package com.lecshop.store.kaptcha;

import com.lecshop.kaptcha.CommonBaseController;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.UnAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dujinkai on 17/5/2.
 * 验证码控制器
 */
@Controller
public class KaptchaController extends CommonBaseController {

    /**
     * 生成验证码
     */
    @UnAuth
    @RequestMapping("/store_createkaptcha")
    public void createKaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        super.createKaptcha(response, code -> request.getSession().setAttribute(CommonConstant.STORE_LOGIN_KAPTCHA_KEY, code));
    }
}
