package com.lecshop.type.service;

import com.lecshop.type.bean.Type;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/10.
 * 类型服务接口
 */
public interface TypeService {

    /**
     * 分页查询类型
     *
     * @param pageHelper 分页帮助类
     * @param name       类型名称
     * @return 返回类型信息
     */
    PageHelper<Type> queryTypes(PageHelper<Type> pageHelper, String name);

    /**
     * 新增类型
     *
     * @param type 类型
     * @return 成功返回1 失败返回0
     */
    int addType(Type type);

    /**
     * 删除类型
     *
     * @param type 类型
     * @return 成功返回1  失败返回0
     */
    int deleteType(Type type);

    /**
     * 批量删除类型信息
     *
     * @param types 类型信息
     * @return 成功返回1 失败返回0
     */
    int batchDeleteTypes(List<Type> types);

    /**
     * 查询类型详情
     *
     * @param typeId 类型id
     * @return 返回类型详情(次接口返回类型, 属性, 属性值和类型关联的规格信息....)
     */
    Type queryTypeDetail(long typeId);

    /**
     * 查询所有的类型信息
     *
     * @return 返回所有的类型信息
     */
    List<Type> queryAllType();

    /**
     * 更新类型信息
     *
     * @param type 类型
     * @return 成功返回1  失败返回0
     */
    int updateType(Type type);
}
