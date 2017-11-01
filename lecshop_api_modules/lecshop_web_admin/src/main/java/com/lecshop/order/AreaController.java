package com.lecshop.order;

import com.lecshop.area.bean.City;
import com.lecshop.area.bean.District;
import com.lecshop.area.bean.Province;
import com.lecshop.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dujinkai on 17/5/15.
 * 区域控制器
 */
@Controller
public class AreaController {

    /**
     * 注入地区服务接口
     */
    @Autowired
    private AreaService areaService;

    /**
     * 跳转到地区页面
     *
     * @return 返回地区页面
     */
    @RequestMapping("/toarea")
    public ModelAndView toArea() {
        return new ModelAndView("order/area");
    }

    /**
     * 添加省份
     *
     * @param province 省份
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addprovince")
    @ResponseBody
    public int addProvince(@RequestBody Province province) {
        return areaService.addProvince(province.setDefaultValuesForAdd());
    }

    /**
     * 添加市
     *
     * @param city 市信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/addcity")
    @ResponseBody
    public int addCity(@RequestBody City city) {
        return areaService.addCity(city.setDefaultValuesForAdd());
    }

    /**
     * 添加区
     *
     * @param district 区信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/adddistrict")
    @ResponseBody
    public int addDistrict(@RequestBody District district) {
        return areaService.addDistrict(district.setDefaultValuesForAdd());
    }

    /**
     * 查询所有省份信息
     *
     * @return 返回所有省份信息
     */
    @RequestMapping("/queryallprovinces")
    @ResponseBody
    public List<Province> queryAllProvinces() {
        return areaService.queryAllProvinces();
    }


    /**
     * 根据省份id查询市
     *
     * @param provinceId 省份id
     * @return 返回市信息
     */
    @RequestMapping("/querycitybyprovinceid")
    @ResponseBody
    public List<City> queryCityByProvinceId(long provinceId) {
        return areaService.queryCityByProvinceId(provinceId);
    }

    /**
     * 根据市id查询下面的区
     *
     * @param cityId 市id
     * @return 返回市下面的区
     */
    @RequestMapping("/querydistrictbycityid")
    @ResponseBody
    public List<District> queryDistrictByCityId(long cityId) {
        return areaService.queryDistrictByCityId(cityId);
    }

    /**
     * 根据省份id删除 省份信息
     *
     * @param id 省份id
     * @return 成功返回1  失败返回0 -1 省下面有市信息不能删除
     */
    @RequestMapping("/deleteprovincebyid")
    @ResponseBody
    public int deleteProvinceById(long id) {
        return areaService.deleteProvinceById(id);
    }


    /**
     * 根据 市id删除市信息
     *
     * @param id 市id
     * @return 成功返回1  失败返回0   -1 下下面有区信息不能删除
     */
    @RequestMapping("/deletecitybyid")
    @ResponseBody
    public int deleteCityById(long id) {
        return areaService.deleteCityById(id);
    }

    /**
     * 根据区id删除区信息
     *
     * @param id 区id
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/deletedistrictbyid")
    @ResponseBody
    public int deleteDistrictById(long id) {
        return areaService.deleteDistrictById(id);
    }

    /**
     * 根据省份id查询省份信息
     *
     * @param id 省份id
     * @return 返回省份信息
     */
    @RequestMapping("/queryprovincebyid")
    @ResponseBody
    public Province queryProvinceById(long id) {
        return areaService.queryProvinceById(id);
    }

    /**
     * 根据市id查询市区信息
     *
     * @param id 市id
     * @return 返回市区信息
     */
    @RequestMapping("/querycitybyid")
    @ResponseBody
    public City queryCityById(long id) {
        return areaService.queryCityById(id);
    }

    /**
     * 根据区id查询区信息
     *
     * @param id 区id
     * @return 返回区信息
     */
    @RequestMapping("/querydistrictbyid")
    @ResponseBody
    public District queryDistrictById(long id) {
        return areaService.queryDistrictById(id);
    }

    /**
     * 修改省份信息
     *
     * @param province 省份信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updateprovince")
    @ResponseBody
    public int updateProvince(@RequestBody Province province) {
        return areaService.updateProvince(province);
    }

    /**
     * 修改市信息
     *
     * @param city 市信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatecity")
    @ResponseBody
    public int updateCity(@RequestBody City city) {
        return areaService.updateCity(city);
    }

    /**
     * 修改区信息
     *
     * @param district 区信息
     * @return 成功返回1  失败返回0
     */
    @RequestMapping("/updatedistrict")
    @ResponseBody
    public int updateDistrict(@RequestBody District district) {
        return areaService.updateDistrict(district);
    }

    /**
     * 查询所有省份  包括省份下面的市
     *
     * @return 返回所有省份
     */
    @RequestMapping("/queryallprovinceswithcity")
    @ResponseBody
    public List<Province> queryAllProvincesWithCity() {
        return areaService.queryAllProvincesWithCity();
    }
}
