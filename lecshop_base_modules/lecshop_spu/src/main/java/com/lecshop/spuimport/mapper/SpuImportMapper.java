package com.lecshop.spuimport.mapper;

import com.lecshop.spuimport.bean.SpuImport;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/18.
 * 商品导入数据库接口
 */
public interface SpuImportMapper {

    /**
     * 查询商品导入总数
     *
     * @param params 查询参数
     * @return 返回商品导入总数
     */
    int querySpuImportCount(Map<String, Object> params);

    /**
     * 查询商品导入
     *
     * @param params 查询参数
     * @return 返回商品导入
     */
    List<SpuImport> querySpuImports(Map<String, Object> params);

    /**
     * 删除商品导入
     *
     * @param ids 商品导入集合
     * @return 成功返回>1 失败返回0
     */
    int deleteSpuImport(List<Long> ids);

    /**
     * 根据id查询商品导入
     *
     * @param id 商品导入id
     * @return 返回商品导入
     */
    SpuImport querySpuImprotById(long id);

    /**
     * 修改成已发布
     *
     * @param id 商品导入id
     */
    void updateToRelease(long id);

    /**
     * 新增商品导入
     *
     * @param spuImports 商品导入集合
     * @return 成功返回>1 失败返回0
     */
    int addSpuImports(List<SpuImport> spuImports);
}
