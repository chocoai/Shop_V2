package com.lecshop.spu.service.impl;

import com.lecshop.baseinfo.service.BaseInfoSetService;
import com.lecshop.brand.service.BrandService;
import com.lecshop.category.bean.Category;
import com.lecshop.category.service.CategoryService;
import com.lecshop.elasticsearch.serivce.EsSearchService;
import com.lecshop.sku.service.SkuService;
import com.lecshop.spu.bean.Spu;
import com.lecshop.spu.bean.SpuSearchCondition;
import com.lecshop.spu.mapper.SpuMapper;
import com.lecshop.spu.service.*;
import com.lecshop.spuimport.service.SpuImportService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.IteratorUtils;
import com.lecshop.util.PageHelper;
import com.lecshop.util.ThreadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/5/13.
 * 商品服务接口实现类
 */
@Service
public class SpuServiceImpl implements SpuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SpuServiceImpl.class);

    /**
     * 注入商品数据库接口
     */
    @Autowired
    private SpuMapper spuMapper;

    /**
     * 注入商品服务接口
     */
    @Autowired
    private SpuServiceSupportService spuServicceSupportService;

    /**
     * 注入商品规格值服务接口
     */
    @Autowired
    private SpuSpecValueService spuSpecValueService;

    /**
     * 注入单品服务接口
     */
    @Autowired
    private SkuService skuService;

    /**
     * 注入商品属性值服务接口
     */
    @Autowired
    private SpuAttributeValueService spuAttributeValueService;

    /**
     * 注入商品图片
     */
    @Autowired
    private SpuImageService spuImageService;

    /**
     * 商品导入服务接口
     */
    @Autowired
    private SpuImportService spuImportService;

    /**
     * 注入品牌服务接口
     */
    @Autowired
    private BrandService brandService;

    /**
     * 注入分类服务接口
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 注入es服务接口
     */
    @Autowired
    private EsSearchService esSearchService;

    /**
     * 注入系统基本设置
     */
    @Autowired
    private BaseInfoSetService baseInfoSetService;

    @Transactional
    @Override
    public int addSpu(Spu spu) {

        logger.debug("addSpu and spu :{}", spu);

        if (Objects.isNull(spu)) {
            logger.error("addSpu fail due to spu is null...");
            return 0;
        }

        setSpuCategory(spu);

        // 新增商品
        spuMapper.addSpu(spu);

        // 设置商品关联信息的商品id
        spu.setSpuLinkedSpuId();

        // 新增商品服务支持
        spuServicceSupportService.addSpuServicceSupport(spu.getSpuServiceSupports());

        // 新增商品规格值信息
        spuSpecValueService.addSpuSpecValues(spu.getSpuSpecValues());

        // 新增商品图片
        spuImageService.addSpuImages(spu.getSpuImages());

        // 新增商品属性值
        spuAttributeValueService.addSpuAttributeValues(spu.getSpuAttributeValues());

        // 设置单品的店铺和审核状态
        spu.setSkuStoreIdAndAudit(baseInfoSetService.isSkuNeedAudit());

        // 新增单品
        skuService.addSkus(spu.getSkus());

        // 将商品导入修改成已发布
        spuImportService.updateToRelease(spu.getSpuImportId());

        // 通知es
        indexEsSpu(spu.getId());

        return 1;
    }


    @Override
    public Spu querySpu(long id, long storeId) {

        logger.debug("querySpu and id :{} \r\n storeId:{}", id, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("storeId", storeId);

        // 查询商品信息
        Spu spu = spuMapper.querySpu(params);

        if (Objects.isNull(spu)) {
            logger.error("querySpu fail due to spu is not exist...");
            return spu;
        }

        // 设置商品的分类信息
        setSpuCategorys(spu);

        // 设置商品的品牌信息
        spu.setBrand(brandService.queryBrandById(spu.getBrandId(), CommonConstant.QUERY_WITH_NO_STORE));

        // 查询商品的服务支持
        spu.setSpuServiceSupports(spuServicceSupportService.queryBySpuId(id));

        // 查询商品的规格值信息(包括规格信息和规格值信息)
        spu.setSpuSpecValues(spuSpecValueService.queryBySpuIdWithSpec(id));

        // 查询商品图片
        spu.setSpuImages(spuImageService.queryBySpuId(id));

        // 查询商品属性值
        spu.setSpuAttributeValues(spuAttributeValueService.queryBySpuId(id));

        // 查询商品下面的单品信息
        spu.setSkus(skuService.querySkuBySpuId(id, spu.getStoreId()));

        return spu;
    }


    @Override
    public PageHelper<Spu> querySpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {

        logger.debug("querySpus and pageHelper:{} \r\n  spuSearchCondition:{}", pageHelper, spuSearchCondition);

        // 搜索参数
        Map<String, Object> params = spuSearchCondition.getSearchMap();

        return pageHelper.setListDates(setBrandAndCategorys(spuMapper.querySpus(pageHelper.getQueryParams(params, spuMapper.querySpuCount(params)))));
    }

    @Override
    public PageHelper<Spu> querySpusWithSku(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {
        logger.debug("querySpusWithSku and pageHelper:{} \r\n spuSearchCondition:{}", pageHelper, spuSearchCondition);

        // 搜索参数
        Map<String, Object> params = spuSearchCondition.getSearchMap();
        return pageHelper.setListDates(setBrandAndCategorys(spuMapper.querySpusWithSku(pageHelper.getQueryParams(params, spuMapper.querySpusWithSkuCount(params)))));
    }

    @Override
    public PageHelper<Spu> queryAllStoreToBeAuditdSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {

        logger.debug("queryAllStoreToBeAuditdSpus and pageHelper:{} \r\n spuSearchCondition:{}", pageHelper, spuSearchCondition);

        // 搜索参数
        Map<String, Object> params = spuSearchCondition.getSearchMap();
        return pageHelper.setListDates(setBrandAndCategorys(spuMapper.queryAllStoreToBeAuditdSpus(pageHelper.getQueryParams(params, spuMapper.queryAllStoreToBeAuditdSpusCount(params)))));
    }

    @Override
    public PageHelper<Spu> queryAllStoreSpus(PageHelper<Spu> pageHelper, SpuSearchCondition spuSearchCondition) {

        logger.debug("queryAllStoreSpus and pageHelper:{} \r\n spuSearchCondition:{} ", pageHelper, spuSearchCondition);

        // 搜索参数
        Map<String, Object> params = spuSearchCondition.getSearchMap();
        return pageHelper.setListDates(setBrandAndCategorys(spuMapper.queryAllStoreSpus(pageHelper.getQueryParams(params, spuMapper.queryAllStoreSpusCount(params)))));
    }

    @Transactional
    @Override
    public int deleteSpu(Spu spu) {

        logger.debug("deleteSpu and spu:{}", spu);

        if (Objects.isNull(spu)) {
            logger.error("deleteSpu fail due to spu is null...");
            return 0;
        }
        // 删除商品信息
        if (spuMapper.deleteSpu(spu) == 0) {
            logger.error("deleteSpu fail...");
            return 0;
        }

        // 删除商品属性值
        spuAttributeValueService.deleteBySpuId(spu.getId());

        // 删除商品图片
        spuImageService.deleteBySpuId(spu.getId());

        // 删除商品服务支持
        spuServicceSupportService.deleteBySpuId(spu.getId());

        // 删除商品规格值
        spuSpecValueService.deleteBySpuId(spu.getId());

        // 删除单品
        skuService.deleteBySpuId(spu.getId(), spu.getStoreId());

        // 删除es中的商品数据
        ThreadTask.getInstance().addTask(() -> esSearchService.deleteSpu(spu.getId()));

        return 1;
    }

    @Override
    public int deleteSpus(List<Spu> spus) {
        logger.debug("deleteSpus and spus:{}", spus);

        if (CollectionUtils.isEmpty(spus)) {
            return 0;
        }

        spus.parallelStream().forEach(this::deleteSpu);
        return 1;
    }

    @Override
    public int updateShelvesStatus(List<Long> spuIds, String status, long storeId) {
        logger.debug("updateShelvesStatus and spuIds:{} \r\n status:{} \r\n storeId:{}", spuIds, status, storeId);

        if (CollectionUtils.isEmpty(spuIds)) {
            logger.error("updateShelvesStatus fail due to spuIds is empty....");
            return 0;
        }

        // 如果不是平台的单品,并且审核开关是打开的则需要将店铺的单品状态改成待审核状态
        if (CommonConstant.ADMIN_STOREID != storeId && baseInfoSetService.isSkuNeedAudit()) {
            skuService.updateSkuToAudit(spuIds, storeId);
        }

        int result = skuService.updateShelvesStatus(spuIds, status, storeId);

        if (result > 0) {
            spuIds.stream().forEach(this::indexEsSpu);
        }
        return result;
    }

    @Transactional
    @Override
    public int updateSpu(Spu spu) {
        logger.debug("updateSpu  and spu:{}", spu);

        if (Objects.isNull(spu)) {
            logger.error("updateSpu fail due to spu is null....");
            return 0;
        }

        // 谁在商品的默认图片
        spu.setDefaultPic();

        // 如果不是平台的商品并且审核开关打开 则需要判断是否修改了商品如果商品修改了 则商品下的所有单品都需要审核
        if (CommonConstant.ADMIN_STOREID != spu.getStoreId() && baseInfoSetService.isSkuNeedAudit()) {
            setSkuAuditStatus(spu);
        }

        // 更新商品信息
        if (spuMapper.updateSpu(spu) == 0) {
            logger.error("updateSpu fail....");
            return 0;
        }

        spu.setSpuLinkedSpuId();

        // 更新商品图片
        spuImageService.updateSpuImages(spu.getSpuImages(), spu.getId());

        // 更新商品属性值
        spuAttributeValueService.updateSpuAttributValues(spu.getSpuAttributeValues(), spu.getId());

        // 更新商品服务支持
        spuServicceSupportService.updateSpuServiceSupport(spu.getSpuServiceSupports(), spu.getId());

        // 更新商品规格值
        spuSpecValueService.updateSpuSpecValues(spu.getSpuSpecValues(), spu.getId());

        // 更新单品
        skuService.updateSkus(spu.getSkus(), spu.getId(), spu.getStoreId());

        // 通知es
        indexEsSpu(spu.getId());
        return 1;
    }


    @Override
    public void indexEsSpu(long spuId) {
        logger.debug("begin to indexEsSpu and spuId:{}", spuId);
        ThreadTask.getInstance().addTask(() -> {
            Spu spu = this.querySpu(spuId, CommonConstant.QUERY_WITH_NO_STORE);
            if (Objects.nonNull(spu)) {
                esSearchService.indexSkus(spu.convertToEsSku());
            }
        });
    }

    /**
     * 设置单品的审核状态
     *
     * @param spu 商品信息
     */
    private void setSkuAuditStatus(Spu spu) {
        // 数据库中的商品信息
        Spu oldSpu = this.querySpu(spu.getId(), spu.getStoreId());

        if (Objects.isNull(oldSpu)) {
            return;
        }

        // 首先将单品审核状态全部改成待审核
        spu.getSkus().stream().forEach(sku -> sku.setAuditStatus(baseInfoSetService.isSkuNeedAudit()));

        // 如果商品没有真正修改过 则看单品有没有修改过
        if (!spu.isModifySpu(oldSpu.getUpdateString())) {
            IteratorUtils.zip(spu.getSkus(), oldSpu.getSkus(), (sku1, sku2) -> sku1.getId().equals(sku2.getId()), (sku1, sku2) -> {

                // 单品发生变化 则设置单品需要重新审核
                if (sku2.isModifySku(sku1.getUpdateString())) {
                    sku1.setAuditStatus(baseInfoSetService.isSkuNeedAudit());
                } else {
                    // 单品没有发生变化 则保持原来审核状态
                    sku1.setStatus(sku2.getStatus());
                }
            });
        }

    }

    /**
     * 设置商品的分类
     *
     * @param spu 商品信息
     */
    private void setSpuCategorys(Spu spu) {
        spu.setFirstCategory(categoryService.queryCategoryById(spu.getFirstCateId()));
        spu.setSecondCategory(categoryService.queryCategoryById(spu.getSecondCateId()));
        spu.setThirdCategory(categoryService.queryCategoryById(spu.getThirdCateId()));
    }

    /**
     * 设置商品的分类和品牌数据
     *
     * @param spu 商品信息
     */
    private void setBrandAndCategory(Spu spu) {
        spu.setBrand(brandService.queryBrandById(spu.getBrandId(), CommonConstant.QUERY_WITH_NO_STORE));
        spu.setThirdCategory(categoryService.queryCategoryById(spu.getThirdCateId()));
    }

    /**
     * 设置商品的分类信息
     *
     * @param spu 商品信息
     */
    private void setSpuCategory(Spu spu) {
        // 店铺的商品需要设置 平台的不需要 已经带过来
        if (spu.isStoreSpu()) {
            // 二级分类
            Category secondCate = categoryService.queryCategoryById(categoryService.queryCategoryById(spu.getThirdCateId()).getParentId());
            spu.setSecondCateId(secondCate.getId());
            spu.setFirstCateId(secondCate.getParentId());
        }
    }

    /**
     * 设置商品的三级分类和品牌信息
     *
     * @param spus 商品集合
     * @return 返回商品集合
     */
    private List<Spu> setBrandAndCategorys(List<Spu> spus) {

        spus.parallelStream().forEach(this::setBrandAndCategory);
        return spus;
    }
}
