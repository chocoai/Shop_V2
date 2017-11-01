package com.lecshop.thematicsetting.service.impl;

import com.lecshop.thematicsetting.bean.Thematic;
import com.lecshop.thematicsetting.mapper.ThematicMapper;
import com.lecshop.thematicsetting.service.ThematicSettingService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 专题service实现类
 *
 * Created by LecShop on 2017/6/8.
 */
@Service
public class ThematicSettingServiceImpl implements ThematicSettingService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(ThematicSettingServiceImpl.class);

    /**
     * 自动注入专题数据库接口
     */
    @Autowired
    private ThematicMapper thematicMapper;

    /**
     * 分页查询专题
     *
     * @param pageHelper 分页帮助类
     * @param name       专题名称
     * @param storeId    店铺id
     * @return           返回专题数据
     */
    @Override
    public PageHelper<Thematic> queryThematic(PageHelper<Thematic> pageHelper, String name, long storeId) {
        logger.debug("queryThematic and pageHelper :{} \r\n and name :{}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("storeId", storeId);
        return pageHelper.setListDates(thematicMapper.queryThematic(pageHelper.getQueryParams(params, thematicMapper.queryThematicCount(params))));
    }

    /**
     * 添加专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @Override
    public int addThematic(Thematic thematic, long storeId) {
        logger.debug("addThematic and thematic :{}", thematic);
        thematic.setStoreId(storeId);
        return thematicMapper.addThematic(thematic);
    }

    /**
     * 删除专题
     *
     * @param id 专题id
     * @return   成功返回1，失败返回0
     */
    @Override
    public int deleteThematic(long id, long storeId) {
        logger.debug("deleteThematic and id", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return thematicMapper.deleteThematic(params);
    }

    /**
     * 批量删除专题
     *
     * @param ids 专题数组
     * @return    删除返回码 -1，失败 >=1成功
     */
    @Override
    public int batchDeleteThematic(long[] ids, long storeId) {
        logger.debug("batchDeleteThematic and ids", ids);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("storeId", storeId);
        return thematicMapper.batchDeleteThematic(params);
    }

    /**
     * 根据id查找专题
     *
     * @param id 专题设置id
     * @return   专题设置
     */
    @Override
    public Thematic queryThematicById(long id, long storeId) {
        logger.debug("queryThematicById and id", id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);
        return thematicMapper.queryThematicById(params);
    }

    /**
     * 修改专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    @Override
    public int updateThematic(Thematic thematic, long storeId) {
        logger.debug("updateThematic and thematic", thematic);
        thematic.setStoreId(storeId);
        return thematicMapper.updateThematic(thematic);
    }

}
