package com.lecshop.thematicsetting.mapper;

import com.lecshop.thematicsetting.bean.Thematic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 专题数据库接口
 *
 * Created by LecShop on 2017/6/8.
 */
@Repository
public interface ThematicMapper {

    /**
     * 分页查询专题
     *
     * @param params 查询参数
     * @return       专题集合
     */
    List<Thematic> queryThematic(Map<String, Object> params);

    /**
     * 查询专题总记录数
     *
     * @param params 查询参数
     * @return       专题总记录数
     */
    int queryThematicCount(Map<String, Object> params);

    /**
     * 添加专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    int addThematic(Thematic thematic);

    /**
     * 删除专题
     * @param params 专题id及店铺id
     * @return       成功返回1，失败返回0
     */
    int deleteThematic(Map<String, Object> params);

    /**
     * 批量删除专题
     *
     * @param params 专题id数组及店铺id
     * @return       删除返回码 -1，失败 >=1成功
     */
    int batchDeleteThematic(Map<String, Object> params);

    /**
     * 根据id查找专题
     *
     * @param params 专题id及店铺id
     * @return       专题
     */
    Thematic queryThematicById(Map<String, Object> params);

    /**
     * 修改专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    int updateThematic(Thematic thematic);

}
