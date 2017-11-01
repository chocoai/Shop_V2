package com.lecshop.spec.mapper;

import com.lecshop.spec.bean.Spec;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/8.
 * 规格数据库接口
 */
public interface SpecMapper {

    /**
     * 新增规格
     *
     * @param spec 规格
     * @return 成功返回  失败返回0
     */
    int addSpec(Spec spec);

    /**
     * 查询规格
     *
     * @param params 查询参数
     * @return 返回规格信息
     */
    List<Spec> querySpecs(Map<String, Object> params);

    /**
     * 查询规格总数
     *
     * @param params 查询参数
     * @return 返回规格总数
     */
    int querySpecsCount(Map<String, Object> params);

    /**
     * 根据规格id查询规格信息
     *
     * @param id 主键id
     * @return 返回规格信息
     */
    Spec querySpecById(long id);

    /**
     * 删除规格
     *
     * @param spec 规格信息
     * @return 成功返回1  失败返回0
     */
    int deleteSpec(Spec spec);

    /**
     * 修改规格信心
     * @param spec 规格信息
     * @return 成功返回1  失败返回0
     */
    int updateSpec(Spec spec);

    /**
     * 查询所有规格信息
     *
     * @return 返回所有规格信息
     */
    List<Spec> queryAllSpec();
}
