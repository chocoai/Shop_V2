package com.lecshop.spuimport.service;

import com.lecshop.spuimport.bean.SpuImport;
import com.lecshop.util.PageHelper;

import java.io.InputStream;
import java.util.List;

/**
 * Created by dujinkai on 17/5/18.
 * 商品导入服务接口
 */
public interface SpuImportService {

    /**
     * 分页查询商品导入
     *
     * @param pageHelper 分页帮助类
     * @param name       商品名称
     * @return 返回商品导入数据
     */
    PageHelper<SpuImport> querySpuImports(PageHelper<SpuImport> pageHelper, String name);

    /**
     * 删除商品导入
     *
     * @param id 商品导入id
     * @return 成功返回 1 失败返回0
     */
    int deleteSpuImport(long id);

    /**
     * 批量删除商品导入
     *
     * @param ids 商品导入id
     * @return 成功返回>1 失败返回0
     */
    int deleteSpuImports(List<Long> ids);

    /**
     * 根据商品导入id查询商品导入信息
     *
     * @param id 商品导入id
     * @return 返回商品导入信息
     */
    SpuImport querySpuImprotById(Long id);

    /**
     * 修改成已发布状态
     *
     * @param id 商品导入id
     */
    void updateToRelease(long id);

    /**
     * 商品导入
     *
     * @param inputStream 输入流
     * @return 返回说名  0 失败 >1 成功
     */
    int importSpu(InputStream inputStream);
}
