package com.lecshop.logistics.service.impl;

import com.lecshop.area.bean.City;
import com.lecshop.area.service.AreaService;
import com.lecshop.logistics.bean.ShippingMethodArea;
import com.lecshop.logistics.mapper.ShippingMethodAreaMapper;
import com.lecshop.logistics.service.ShippingMethodAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式区域服务接口实现
 */
@Service
public class ShippingMethodAreaServiceImpl implements ShippingMethodAreaService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ShippingMethodAreaServiceImpl.class);

    /**
     * 注入运费方式区域数据库接口
     */
    @Autowired
    private ShippingMethodAreaMapper shippingMethodAreaMapper;

    /**
     * 注入区域服务接口
     */
    @Autowired
    private AreaService areaService;

    @Override
    public void addShippingMethodAreas(List<ShippingMethodArea> shippingMethodAreas) {
        logger.debug("addShippingMethodAreas and shippingMethodAreas:{}", shippingMethodAreas);

        if (CollectionUtils.isEmpty(shippingMethodAreas)) {
            logger.error("no need to addShippingMethodAreas .....");
            return;
        }

        shippingMethodAreaMapper.addShippingMethodAreas(shippingMethodAreas);
    }

    @Override
    public List<ShippingMethodArea> queryByShippingMethodId(long id) {
        logger.debug("queryByShippingMethodId and id:{}", id);

        List<ShippingMethodArea> shippingMethodAreas = shippingMethodAreaMapper.queryByShippingMethodId(id);

        if (CollectionUtils.isEmpty(shippingMethodAreas)) {
            return shippingMethodAreas;
        }

        shippingMethodAreas.stream().forEach(shippingMethodArea -> shippingMethodArea.setCity(areaService.queryCityById(shippingMethodArea.getCityId())));

        return shippingMethodAreas;
    }

    @Override
    public void deleteByTemplateId(long id) {
        logger.debug("deleteByTemplateId and id:{}", id);
        shippingMethodAreaMapper.deleteByTemplateId(id);
    }
}
