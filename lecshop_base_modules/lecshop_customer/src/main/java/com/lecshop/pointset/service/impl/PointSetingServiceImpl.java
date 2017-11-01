package com.lecshop.pointset.service.impl;

import com.lecshop.pointset.bean.PointSeting;
import com.lecshop.pointset.mapper.PointSetingMapper;
import com.lecshop.pointset.service.PointSetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 17/5/23.
 * 积分设置服务实现接口
 */
@Service
public class PointSetingServiceImpl implements PointSetingService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(PointSetingServiceImpl.class);

    /**
     * 注入积分设置数据库接口
     */
    @Autowired
    private PointSetingMapper pointSetingMapper;

    @Override
    public int updatePointSeting(PointSeting pointSeting) {
        logger.debug("updatePointSeting and pointSeting");

        if (Objects.isNull(pointSeting)) {
            logger.error("updatePointSeting fail due to params is null...");
            return 0;
        }
        return pointSetingMapper.updatePointSeting(pointSeting);
    }

    @Override
    public PointSeting queryPointSeting() {
        logger.debug("begin to queryPointSeting");
        return pointSetingMapper.queryPointSeting();
    }
}
