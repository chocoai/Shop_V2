package com.lecshop.sku.service.impl;

import com.lecshop.sku.bean.Sku;
import com.lecshop.sku.mapper.SkuMapper;
import com.lecshop.sku.service.SkuImageService;
import com.lecshop.sku.service.SkuMemberPriceServicce;
import com.lecshop.sku.service.SkuService;
import com.lecshop.sku.service.SkuSpecValueService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dujinkai on 17/5/13.
 * 单品服务实现类
 */
@Service
public class SkuServiceImpl implements SkuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SkuServiceImpl.class);

    /**
     * 注入单品图片服务接口
     */
    @Autowired
    private SkuImageService skuImageService;

    /**
     * 注入单品会员价服务接口
     */
    @Autowired
    private SkuMemberPriceServicce skuMemberPriceServicce;

    /**
     * 注入数据库接口
     */
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 注入单品规格值服务接口
     */
    @Autowired
    private SkuSpecValueService skuSpecValueService;

    @Override
    public void addSkus(List<Sku> skus) {

        logger.debug("addSku and skus:{}", skus);

        if (CollectionUtils.isEmpty(skus)) {
            logger.error("addSku fail due to skus is null..");
            return;
        }

        // 设置单品的主键id
        IntStream.range(0, skus.size()).forEach(index -> skus.get(index).setCustomId(index));

        // 并行新增单品
        skus.parallelStream().forEach(this::addSku);
    }


    @Override
    public List<Sku> querySkuBySpuId(long spuId, long storeId) {
        logger.debug("querySkuBySpuId and spuId:{} \r\n storeId:{}", spuId, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("spuId", spuId);
        params.put("storeId", storeId);
        return skuMapper.querySkuBySpuId(params).parallelStream().map(this::setSkuDetail).collect(Collectors.toList());
    }

    @Override
    public void deleteBySpuId(long spuId, long storeId) {
        logger.debug("deleteBySpuId and spuId:{} \rn storeId:{}", spuId, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("spuId", spuId);
        params.put("storeId", storeId);

        if (skuMapper.deleteBySpuId(params) == 0) {
            logger.error("deleteBySpuId fail...");
            return;
        }

        // 删除单品图片
        skuImageService.deleteBySpuId(spuId);

        // 删除单品会员价
        skuMemberPriceServicce.deleteBySpuId(spuId);

        // 删除单品规格值
        skuSpecValueService.deleteBySpuId(spuId);

    }


    @Override
    public int updateShelvesStatus(List<Long> spuIds, String status, long storeId) {
        logger.debug("updateShelvesStatus and spuIds:{} \r\n status:{} \r\n storeId:{}", spuIds, status, storeId);

        if (CollectionUtils.isEmpty(spuIds)) {
            logger.error("updateShelvesStatus fail due to spuIds is null...");
            return 0;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("spuIds", spuIds);
        params.put("status", status);
        params.put("storeId", storeId);
        params.put("upTime", LocalDateTime.now());
        return skuMapper.updateShelvesStatus(params);
    }

    @Transactional
    @Override
    public void updateSkus(List<Sku> skus, long spuId, long storeId) {

        logger.debug("updateSkus and skus:{}\r\n spuId:{} \r\n storeId:{}", skus, spuId, storeId);

        // 根据商品id删除单品信息(物理删除)
        if (deleteBySpuIdPhysical(spuId, storeId) == 0) {
            logger.error("updateSkus fail...");
            return;
        }

        if (CollectionUtils.isEmpty(skus)) {
            logger.warn("not need to updateSkus");
            return;
        }

        // 设置单品的主键id
        IntStream.range(0, skus.size()).forEach(index -> skus.get(index).setCustomId(index));

        // 新增单品
        skus.parallelStream().forEach(this::addSku);
    }

    @Override
    public PageHelper<Sku> querySkusWithSpecs(PageHelper<Sku> pageHelper, long storeId, String name, String skuNo) {
        logger.debug("querySkusWithSpecs and storeId:{} \r\n pageHelper:{} \r\n name:{} \r\n skuNo:{}", storeId, pageHelper, name, skuNo);

        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("name", name);
        params.put("skuNo", skuNo);

        List<Sku> skus = skuMapper.querySkus(pageHelper.getQueryParams(params, skuMapper.querySkuCount(params)));

        if (CollectionUtils.isEmpty(skus)) {
            return pageHelper.setListDates(skus);
        }

        return pageHelper.setListDates(skus.parallelStream().map(sku -> {
            // 设置单品规格值信息
            sku.setSkuSpecValues(skuSpecValueService.queryBySkuId(sku.getId()));
            return sku;
        }).collect(Collectors.toList()));
    }

    @Override
    public Sku querySkuByIdWithSpecs(String skuId, long storeId) {
        logger.debug("querySkuByIdWithSpecs and skuId:{} storeId:{}", skuId, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", skuId);
        params.put("storeId", storeId);

        Sku sku = skuMapper.queryById(params);

        if (Objects.isNull(sku)) {
            logger.error("querySkuByIdWithSpecs fail ....");
            return sku;
        }

        // 设置单品规格值信息
        sku.setSkuSpecValues(skuSpecValueService.queryBySkuId(sku.getId()));

        return sku;
    }

    @Override
    public int updateSkuToAudit(List<Long> spuIds, long storeId) {
        logger.debug("updateSkuToAudit and spuIds:{}\r\n storeId:{}", spuIds, storeId);

        Map<String, Object> params = new HashMap<>();
        params.put("spuIds", spuIds);
        params.put("status", "2");
        params.put("storeId", storeId);

        return skuMapper.updateSkuAuditStatus(params);
    }

    @Transactional
    @Override
    public int auditPass(String[] skuIds) {
        logger.debug("auditPass and skuIds:{}", skuIds);

        if (ArrayUtils.isEmpty(skuIds)) {
            logger.error("auditPass fail ...");
            return 0;
        }

        Stream.of(skuIds).forEach(skuMapper::auditPass);

        return 1;
    }

    @Transactional
    @Override
    public int auditRefuse(String[] skuIds, String reason) {

        logger.debug("auditRefuse and skuIds:{} \r\n reason:{}", skuIds, reason);

        if (ArrayUtils.isEmpty(skuIds)) {
            logger.error("auditRefuse fail...");
            return 0;
        }

        Stream.of(skuIds).forEach(skuId -> {
            Map<String, Object> params = new HashMap<>();
            params.put("skuId", skuId);
            params.put("reason", reason);
            skuMapper.auditRefuse(params);
        });

        return 1;
    }

    /**
     * 根据店铺id查询前五条数据
     *
     * @param storeId 店铺id
     * @return 单品集合
     */
    @Override
    public List<Sku> queryFiveDataForAttentionStore(long storeId) {
        return skuMapper.queryFiveDataForAttentionStore(storeId);
    }

    @Override
    public Sku querySkuById(String skuId) {
        logger.debug("querySkuById and skuId:{}", skuId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", skuId);
        params.put("storeId", CommonConstant.QUERY_WITH_NO_STORE);
        return skuMapper.queryById(params);
    }

    /**
     * 设置单品的详细信息
     *
     * @param sku 单品
     * @return 返回单品信息包含(单品图片, 单品会员价格, 单品规格值)
     */
    private Sku setSkuDetail(Sku sku) {
        // 设置单品图片
        sku.setSkuImages(skuImageService.queryBySkuId(sku.getId()));

        // 设置单品会员价
        sku.setSkuMemberPrices(skuMemberPriceServicce.queryBySkuId(sku.getId()));

        // 设置单品规格值信息
        sku.setSkuSpecValues(skuSpecValueService.queryBySkuId(sku.getId()));

        return sku;
    }


    /**
     * 新增单品
     *
     * @param sku 单品信息
     */
    private void addSku(Sku sku) {

        logger.debug("addSku and sku :{}", sku);

        if (Objects.isNull(sku)) {
            logger.error("addSku fail due to sku is null...");
            return;
        }

        // 设置默认图片
        sku.setDefaultPic();

        // 新增单品
        skuMapper.addSku(sku);

        // 设置和单品关联信息的单品id和商品id
        sku.setSkuLinkedSkuIdAndSpuId();

        // 新增单品图片
        skuImageService.addSkuImages(sku.getSkuImages());

        // 新增单品会员价格
        skuMemberPriceServicce.addSkuMemberPrices(sku.getSkuMemberPrices());

        // 新增单品规格值
        skuSpecValueService.addSkuSpecValues(sku.getSkuSpecValues());
    }

    /**
     * 物理删除单品信息
     *
     * @param spuId 商品id
     */
    private int deleteBySpuIdPhysical(long spuId, long storeId) {

        Map<String, Object> params = new HashMap<>();
        params.put("spuId", spuId);
        params.put("storeId", storeId);

        // 删除单品信息(物理)
        if (skuMapper.deleteBySpuIdPhysical(params) == 0) {
            return 0;
        }


        // 删除单品图片(物理)
        skuImageService.deleteBySpuIdPhysical(spuId);

        // 删除单品会员价(物理)
        skuMemberPriceServicce.deleteBySpuIdPhysical(spuId);

        // 删除单品规格值(物理)
        skuSpecValueService.deleteBySpuIdPhysical(spuId);

        return 1;
    }


}
