package com.lecshop.brand.service.impl;

import com.lecshop.brand.bean.BrandApply;
import com.lecshop.brand.mapper.BrandApplyMapper;
import com.lecshop.brand.service.BrandApplyService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by dujinkai on 17/6/14.
 * 品牌申请服务接口实现
 */
@Service
public class BrandApplyServiceImpl implements BrandApplyService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(BrandApplyServiceImpl.class);

    /**
     * 品牌申请数据库接口
     */
    @Autowired
    private BrandApplyMapper brandApplyMapper;

    @Override
    public List<BrandApply> queryAllPassBrand(long storeId) {
        logger.debug("queryAllPassBrand and storeId:{}", storeId);
        return brandApplyMapper.queryAllPassBrand(storeId);
    }

    /**
     * 分页查询待审核的品牌
     *
     * @param pageHelper 分页帮助类
     * @param name       品牌名称
     * @param storeName  店铺名称
     * @return 待审核的品牌
     */
    @Override
    public PageHelper<BrandApply> queryBrandToBeAudit(PageHelper<BrandApply> pageHelper, String name, String storeName) {
        logger.debug("queryBrandToBeAudit and pageHelper :{} \r\n and name :{} \r\n and nickName :{} \r\n and storeId :{}", pageHelper, name, storeName);
        Map<String, Object> params = new HashMap<>();
        params.put("brandName", name);
        params.put("storeName", storeName);
        return pageHelper.setListDates(brandApplyMapper.queryBrandToBeAudit(pageHelper.getQueryParams(params, brandApplyMapper.queryBrandToBeAuditCount(params))));
    }

    /**
     * 通过品牌审核
     *
     * @param id 品牌审核id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int passBrandAudit(long id) {
        logger.debug("passBrandAudit and id :{}", id);
        return brandApplyMapper.passBrandAudit(id);
    }

    /**
     * 批量通过品牌审核
     *
     * @param ids 品牌审核id数组
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int batchPassBrandAudit(long[] ids) {
        logger.debug("batchPassBrandAudit and ids :{}", ids);
        return brandApplyMapper.batchPassBrandAudit(ids);
    }

    /**
     * 拒绝品牌审核
     *
     * @param brandApply 商品审核实例
     * @return 成功返回1，失败返回0
     */
    @Override
    public int refuseBrandAudit(BrandApply brandApply) {
        logger.debug("refuseBrandAudit and brandApply :{}", brandApply);
        return brandApplyMapper.refuseBrandAudit(brandApply);
    }

    /**
     * 批量拒绝品牌审核
     *
     * @param ids 品牌审核id数组
     * @param reason 拒绝原因
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int batchRefuseBrandAudit(long[] ids, String reason) {
        logger.debug("batchRefuseBrandAudit and ids :{} and reason :{}", ids, reason);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("reason", reason);
        return brandApplyMapper.batchRefuseBrandAudit(params);
    }

    /**
     * 根据店铺id查询品牌并通过审核
     *
     * @param storeId 店铺id
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int passBrandAuditByStoreId(long storeId) {
        logger.debug("passBrandAuditByStoreId and storeId :{}", storeId);
        return brandApplyMapper.passBrandAuditByStoreId(storeId);
    }

    /**
     * 开店-添加店铺品牌
     *
     * @param list 品牌集合
     * @return 添加返回码
     */
    @Override
    public int addStoreBrand(List<BrandApply> list) {
        logger.debug("addStoreBrand and list:{}", list);
        return brandApplyMapper.addStoreBrand(list);
    }

    /**
     * 处理添加签约品牌数据
     *
     * @param brandApply 实体类
     * @param storeId    店铺id
     * @return 添加返回码
     */
    @Override
    public int doAddStoreBrand(BrandApply brandApply, Long storeId) {
        logger.debug("doAddStoreBrand and brandApply:{}\r\n storeId:{}", brandApply, storeId);
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        List<Long> idList = new ArrayList<>();
        idList.add(brandApply.getBrandId());
        map.put("idArrays", idList);
        if (!Objects.isNull(brandApplyMapper.queryStoreBrandByStoreIdAndBrandId(map))) {
            logger.error("doAddStoreBrand error due to isnull");
            return -1;
        }
        List<BrandApply> list = new ArrayList<>();
        brandApply.setStoreId(storeId);
        list.add(brandApply);
        return addStoreBrand(list);
    }

    /**
     * 删除店铺品牌
     *
     * @param storeId 店铺id
     * @return 删除返回码
     */
    @Override
    public int deleteStoreBrand(long storeId) {
        logger.debug("deleteStoreBrand and storeId:{}", storeId);
        return brandApplyMapper.deleteStoreBrand(storeId);
    }

    /**
     * 根据店铺id和品牌id删除签约品牌
     *
     * @param storeId 删除条件
     * @param ids     品牌id
     * @return 删除返回码
     */
    @Override
    public int deleteStoreBrandByStoreIdAndBrandId(long storeId, long[] ids) {
        logger.debug("deleteStoreBrand and storeId:{}\r\n ids:{}", storeId, ids);
        Map<String, Object> param = new HashMap<>();
        param.put("storeId", storeId);
        param.put("idArrays", ids);
        return brandApplyMapper.deleteStoreBrandByStoreIdAndBrandId(param);
    }
}
