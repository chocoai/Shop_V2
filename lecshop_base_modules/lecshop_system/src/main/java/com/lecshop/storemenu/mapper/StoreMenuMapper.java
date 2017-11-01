package com.lecshop.storemenu.mapper;

import com.lecshop.storemenu.bean.StoreMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * store端菜单mapper
 *
 * @author sunluyang on 2017/6/7.
 */
@Repository
public interface StoreMenuMapper {

    /**
     * 根据管理员id查询菜单
     *
     * @param customerId 管理员id
     * @return 管理员菜单实体类
     */
    List<StoreMenu> queryStoreMenu(Long customerId);
}
