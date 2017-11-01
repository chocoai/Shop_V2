package com.lecshop.customer;

import com.lecshop.stationletter.bean.StationLetter;
import com.lecshop.stationletter.service.StationLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dujinkai on 17/5/27.
 * 站内信控制器
 */
@Controller
public class StationLetterController {

    /**
     * 注入站内信服务接口
     */
    @Autowired
    private StationLetterService stationLetterService;

    /**
     * 新增站内信
     *
     * @param stationLetters
     * @return 成功返回>1 失败返回0
     */
    @RequestMapping("/addstationletters")
    @ResponseBody
    public int addstationLetters(@RequestBody List<StationLetter> stationLetters) {
        return stationLetterService.addStationLetters(stationLetters);
    }
}
