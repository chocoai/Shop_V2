package com.lecshop.area.service.impl;

import com.lecshop.area.bean.City;
import com.lecshop.area.bean.District;
import com.lecshop.area.bean.Province;
import com.lecshop.area.mapper.AreaMapper;
import com.lecshop.area.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/15
 * 地区服务实现接口
 */
@Service
public class AreaServiceImpl implements AreaService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    /**
     * 地区数据库接口
     */
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Province> queryAllProvinces() {
        logger.debug("queryAllProvinces.....");
        return areaMapper.queryAllProvinces();
    }

    @Override
    public List<City> queryCityByProvinceId(long provinceId) {
        logger.debug("queryCityByProvinceId and provinceId:{}", provinceId);
        return areaMapper.queryCityByProvinceId(provinceId);
    }

    @Override
    public List<District> queryDistrictByCityId(long cityId) {
        logger.debug("queryDistrictByCityId and cityId:{}", cityId);
        return areaMapper.queryDistrictByCityId(cityId);
    }

    @Override
    public int addProvince(Province province) {
        logger.debug("addProvince and province:{}", province);

        if (Objects.isNull(province)) {
            logger.error("addProvince fail due to province is null....");
            return 0;
        }
        return areaMapper.addProvince(province);
    }

    @Override
    public int addCity(City city) {
        logger.debug("addCity and city :{}", city);
        if (Objects.isNull(city)) {
            logger.error("addCity fail due to city is null....");
            return 0;
        }
        return areaMapper.addCity(city);
    }

    @Override
    public int addDistrict(District district) {

        logger.debug("addDistrict and district:{}", district);

        if (Objects.isNull(district)) {
            logger.error("addDistrict fail due to district is null...");
            return 0;
        }
        return areaMapper.addDistrict(district);
    }

    @Override
    public int deleteProvinceById(long provinceId) {
        logger.debug("deleteProvinceById and provinceId:{}", provinceId);

        if (!isProvinceCanDelete(provinceId)) {
            logger.error("deleteProvinceById fail due to province has city...");
            return -1;
        }

        return areaMapper.deleteProvinceById(provinceId);
    }

    @Override
    public int deleteCityById(long cityId) {
        logger.debug("deleteCityById and cityId:{}", cityId);

        if (!isCityCanDelete(cityId)) {
            logger.error("deleteCityById fail due to city has district");
            return -1;
        }
        return areaMapper.deleteCityById(cityId);
    }

    @Override
    public int deleteDistrictById(long districtId) {
        logger.debug("deleteDistrictById and districtId:{}", districtId);
        return areaMapper.deleteDistrictById(districtId);
    }


    @Override
    public Province queryProvinceById(long id) {
        logger.debug("queryProvinceById and id:{}", id);
        return areaMapper.queryProvinceById(id);
    }

    @Override
    public City queryCityById(long id) {
        logger.debug("queryCityById and id:{}", id);
        return areaMapper.queryCityById(id);
    }

    @Override
    public District queryDistrictById(long id) {
        logger.debug("queryDistrictById and id :{}", id);
        return areaMapper.queryDistrictById(id);
    }

    @Override
    public int updateProvince(Province province) {
        logger.debug("updateProvince and province:{}", province);
        if (Objects.isNull(province)) {
            logger.error("updateProvince fail ...");
            return 0;
        }
        return areaMapper.updateProvince(province);
    }

    @Override
    public int updateCity(City city) {
        logger.debug("updateCity and city:{}", city);

        if (Objects.isNull(city)) {
            logger.error("updateCity fail...");
            return 0;
        }
        return areaMapper.updateCity(city);
    }

    @Override
    public int updateDistrict(District district) {
        logger.debug("updateDistrict and district:{}", district);

        if (Objects.isNull(district)) {
            logger.error("updateDistrict fail....");
            return 0;
        }
        return areaMapper.updateDistrict(district);
    }

    @Override
    public List<Province> queryAllProvincesWithCity() {

        List<Province> provinces = this.queryAllProvinces();

        if (CollectionUtils.isEmpty(provinces)) {
            return Collections.emptyList();
        }
        provinces.stream().forEach(province -> province.setCities(this.queryCityByProvinceId(province.getId())));

        return provinces;
    }

    /**
     * 判断市是否可以删除
     *
     * @param cityId 市id
     * @return 可以返回true  不可以返回false  市下面有区则不能删除
     */
    private boolean isCityCanDelete(long cityId) {
        return CollectionUtils.isEmpty(this.queryDistrictByCityId(cityId));
    }

    /**
     * 判断省份是否可以删除
     *
     * @param provinceId 省份id
     * @return 可以删除 返回true  不可以删除 返回false (省份下面有市则不能删除)
     */
    private boolean isProvinceCanDelete(long provinceId) {
        return CollectionUtils.isEmpty(this.queryCityByProvinceId(provinceId));
    }
}
