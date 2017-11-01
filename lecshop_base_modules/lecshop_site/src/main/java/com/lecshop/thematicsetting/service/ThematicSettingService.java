package com.lecshop.thematicsetting.service;

import com.lecshop.thematicsetting.bean.Thematic;
import com.lecshop.util.PageHelper;

/**
 * 专题service
 *
 * Created by LecShop on 2017/6/8.
 */
public interface ThematicSettingService {

    /**
     * 分页查询专题
     *
     * @param pageHelper 分页帮助类
     * @param name       专题名称
     * @param storeId    店铺id
     * @return           返回专题数据
     */
    PageHelper<Thematic> queryThematic(PageHelper<Thematic> pageHelper, String name, long storeId);

    /**
     * 添加专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    int addThematic(Thematic thematic, long storeId);

    /**
     * 删除专题
     * @param id 专题id
     * @return   成功返回1，失败返回0
     */
    int deleteThematic(long id, long storeId);

    /**
     * 批量删除专题
     *
     * @param ids 专题数组
     * @return    删除返回码 -1，失败 >=1成功
     */
    int batchDeleteThematic(long [] ids, long storeId);

    /**
     * 根据id查找专题
     *
     * @param id 专题设置id
     * @return  专题设置
     */
    Thematic queryThematicById(long id, long storeId);

    /**
     * 修改专题
     *
     * @param thematic 专题
     * @return         成功返回1，失败返回0
     */
    int updateThematic(Thematic thematic, long storeId);

}
