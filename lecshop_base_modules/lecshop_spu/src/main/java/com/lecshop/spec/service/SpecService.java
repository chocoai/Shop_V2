package com.lecshop.spec.service;

import com.lecshop.spec.bean.Spec;
import com.lecshop.util.PageHelper;

import java.util.List;

/**
 * Created by dujinkai on 17/5/8.
 * 规格服务接口
 */
public interface SpecService {

    /**
     * 新增规格
     *
     * @param spec 规格信息
     * @return 成功返回 1 失败返回0
     */
    int addSpec(Spec spec);

    /**
     * 修改规格信息
     *
     * @param spec
     * @return 成功返回1  失败返回0
     */
    int updateSpec(Spec spec);

    /**
     * 分页查询规格信息
     *
     * @param pageHelper 分页帮助类
     * @param name       条件查询名称
     * @return 返回规格信息
     */
    PageHelper<Spec> querySpecs(PageHelper<Spec> pageHelper, String name);

    /**
     * 根据规格id查询规格信息
     *
     * @param id 规格id
     * @return 返回规格信息(包含规格值)
     */
    Spec querySpecById(long id);

    /**
     * 根据规格id查询规格信息
     *
     * @param id 规格id
     * @return 返回规格信息 只有规格信息
     */
    Spec querySpecByIdSimple(long id);

    /**
     * 删除规格
     *
     * @param spec 规格
     * @return 成功返回1  失败返回0 -1 商品已经使用规格不能删除
     */
    int deleteSpec(Spec spec);

    /**
     * 批量删除规格
     *
     * @param specs 规格信息
     * @return 成功返回1  失败返回0 -1 商品已经使用规格不能删除
     */
    int deleteSpecs(List<Spec> specs);

    /**
     * 查询所有的规格信息
     *
     * @return 返回所有的规格信息
     */
    List<Spec> queryAllSpec();

    /**
     * 根据规格id查询规格信息
     *
     * @param ids 规格id集合
     * @return 返回规格信息(包含规格值)
     */
    List<Spec> querySpecsByIds(Long[] ids);
}
