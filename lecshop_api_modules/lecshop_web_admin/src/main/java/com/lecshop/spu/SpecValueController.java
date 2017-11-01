package com.lecshop.spu;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.specvalue.bean.SpecValue;
import com.lecshop.specvalue.service.SpecValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/5/18.
 * 规格值控制器
 */
@Controller
public class SpecValueController {

    /**
     * 注入规格值服务接口
     */
    @Autowired
    private SpecValueService specValueService;

    /**
     * 添加规格值信息
     *
     * @param specValue 规格值信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addspecvalue")
    @ResponseBody
    public String addSpecValue(@RequestBody SpecValue specValue, HttpServletRequest request) {
        return specValueService.addSpecValue(specValue.setDefaultValuesForAdd(specValue, AdminLoginUtils.getInstance().getManagerNameFromSession(request)));
    }

    /**
     * 判断规格值是否可以删除
     *
     * @param specValueId 规格值id
     * @return 可以返回true  不可以返回false
     */
    @RequestMapping("/isspecvaluecandelete")
    @ResponseBody
    public boolean isSpecValueCanDelete(String specValueId) {
        return specValueService.isSpecValueCanDelete(specValueId);
    }
}
