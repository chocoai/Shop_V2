package com.lecshop.helpcenter.service;

import com.lecshop.helpcenter.bean.HelpCategory;
import com.lecshop.helpcenter.bean.HelpList;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * 帮助列表service层接口
 *
 * Created by LecShop on 2017/5/27.
 */
public interface HelpListService {

    /**
     * 分页查询帮助列表
     *
     * @param pageHelper 分页帮助类
     * @param name       帮助名称
     * @return           返回帮助数据
     */
    PageHelper<HelpList> queryHelpList(PageHelper<HelpList> pageHelper, String name);

    /**
     * 通过id查找帮助
     *
     * @param id 帮助id
     * @return   帮助
     */
    HelpList queryHelpById(long id);

    /**
     * 查找帮助分类
     *
     * @return 返回帮助分类集合
     */
    List<HelpCategory> queryHelpCate();

    /**
     * 添加帮助
     *
     * @param helpList 帮助
     * @return         成功返回1，失败返回0
     */
    int addHelp(HelpList helpList);

    /**
     * 删除帮助
     *
     * @param id 帮助id
     * @return   成功返回1  失败返回0
     */
    int deleteHelp(long id);

    /**
     * 批量删除帮助
     *
     * @param ids 帮助id数组
     * @return    成功返回1  失败返回0
     */
    int batchDeleteHelp(long [] ids);

    /**
     * 修改帮助
     *
     * @param helpList  帮助
     * @return         成功返回1，失败返回0
     */
    int updateHelp(HelpList helpList);

    /**
     * 查找所有服务
     *
     * @return 返回服务集合
     */
    List<HelpList> queryHelp();

}
