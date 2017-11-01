package com.lecshop.logistics.service.impl;

import com.lecshop.logistics.bean.ShippingMethod;
import com.lecshop.logistics.mapper.ShippingMethodMapper;
import com.lecshop.logistics.service.ShippingMethodAreaService;
import com.lecshop.logistics.service.ShippingMethodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/5/31.
 * 运费方式服务实现接口
 */
@Service
public class ShippingMethodServiceImpl implements ShippingMethodService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ShippingMethodServiceImpl.class);

    /**
     * 运费方式数据库接口
     */
    @Autowired
    private ShippingMethodMapper shippingMethodMapper;

    /**
     * 运费方式区域服务接口
     */
    @Autowired
    private ShippingMethodAreaService shippingMethodAreaService;

    @Transactional
    @Override
    public void addShippingMethod(List<ShippingMethod> shippingMethods) {
        logger.debug("addShippingMethod and shippingMethods:{}", shippingMethods);

        if (CollectionUtils.isEmpty(shippingMethods)) {
            logger.error("no need to addShippingMethod due to shippingMethods is null...");
            return;
        }

        //新增运费方式
        shippingMethodMapper.addShippingMethods(shippingMethods);

        // 新增运费方式的区域
        shippingMethods.parallelStream().forEach(shippingMethod -> {
            shippingMethod.setShippingMethodAreaMethodId();
            shippingMethodAreaService.addShippingMethodAreas(shippingMethod.getShippingMethodAreas());
        });
    }

    @Override
    public List<ShippingMethod> queryByTemplateId(long id) {
        logger.debug("queryByTemplateId and id:{}", id);

        List<ShippingMethod> shippingMethods = shippingMethodMapper.queryByTemplateId(id);

        if (CollectionUtils.isEmpty(shippingMethods)) {
            return shippingMethods;
        }

        shippingMethods.parallelStream().forEach(shippingMethod -> shippingMethod.setShippingMethodAreas(shippingMethodAreaService.queryByShippingMethodId(shippingMethod.getId())));

        return shippingMethods;
    }

    @Override
    public void deleteByTemplateId(long id) {
        logger.debug("deleteByTemplateId and id:{}", id);

        // 删除运费方式
        shippingMethodMapper.deleteByTemplateId(id);

        // 删除运费方式区域
        shippingMethodAreaService.deleteByTemplateId(id);
    }

    @Override
    public void updateShippingMethods(List<ShippingMethod> shippingMethods, long templateId) {
        logger.debug("updateShippingMethods and shippingMethods:{} \r\n templateId:{}", shippingMethods, templateId);

        // 删除运费方式和运费方式区域
        deleteByTemplateId(templateId);

        // 新增运费方式
        addShippingMethod(shippingMethods);
    }
}
