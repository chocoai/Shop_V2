package com.lecshop.coupon.service.impl;

import com.lecshop.baseinfo.mapper.BaseInfoSetMapper;
import com.lecshop.coupon.bean.*;
import com.lecshop.coupon.mapper.CouponCodeMapper;
import com.lecshop.coupon.mapper.CouponFallMapper;
import com.lecshop.coupon.mapper.CouponFullMapper;
import com.lecshop.coupon.mapper.CouponMapper;
import com.lecshop.coupon.service.CouponService;
import com.lecshop.marketingmanager.service.RegisterMarketingService;
import com.lecshop.util.CommonConstant;
import com.lecshop.util.ExcelUtils;
import com.lecshop.util.PageHelper;
import com.lecshop.util.RandomMathLetter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 优惠券service实现类
 *
 * @author sunluyang on 2017/6/1.
 */
@Service
public class CouponServiceImpl implements CouponService {
    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    /**
     * 注入优惠券mapper
     */
    @Autowired
    private CouponMapper couponMapper;
    /**
     * 注入优惠券券码mapper
     */
    @Autowired
    private CouponCodeMapper couponCodeMapper;
    /**
     * 直降mapper
     */
    @Autowired
    private CouponFallMapper couponFallMapper;
    /**
     * 满减mapper
     */
    @Autowired
    private CouponFullMapper couponFullMapper;

    /**
     * 注入信息设置mapper
     */
    @Autowired
    private BaseInfoSetMapper baseInfoSetMapper;
    /**
     * 注入注册营销service
     */
    @Autowired
    private RegisterMarketingService registerMarketingService;

    /**
     * 添加优惠券
     *
     * @param coupon  优惠券实体类
     * @param storeId 店铺id
     * @return 添加返回码 -1 失败 -2 限领张数不正确 -3限领张数不能大于总张数 -4 开始时间大于结束时间 >=1 成功
     */
    @Override
    @Transactional
    public int addCoupon(Coupon coupon, long storeId) {
        logger.debug("addCoupon and coupon:{}", coupon);
        if (Objects.isNull(coupon)) {
            logger.error("addCoupon error due to coupon is null");
            return -1;
        }
        if (coupon.getLimitNum() <= 0) {
            return -2;
        }
        if (coupon.getNum() < coupon.getLimitNum()) {
            return -3;
        }
        if (coupon.toCompareTime()) {
            return -4;
        }
        coupon.setStoreId(storeId);
        couponMapper.addCoupon(coupon);
        if (coupon.getType() == 1) {//1满减
            CouponFull couponFull = new CouponFull();
            couponFull.setCouponId(coupon.getId());
            couponFull.setFullPrice(coupon.getFullPrice());
            couponFull.setPrice(coupon.getPrice());
            couponFullMapper.addCouponFull(couponFull);
        }
        if (coupon.getType() == 2) {//2直降
            CouponFall couponFall = new CouponFall();
            couponFall.setCouponId(coupon.getId());
            couponFall.setPrice(coupon.getPrice());
            couponFallMapper.addCouponFall(couponFall);
        }
        //优惠券卷码集合
        List<CouponCode> list = new ArrayList<>();
        for (int i = 0; i < coupon.getNum(); i++) {
            list.add(new CouponCode(0, coupon.getId(), RandomMathLetter.randomString(18), 0, "0", null, ""));
        }
        //添加优惠券卷码
        return couponCodeMapper.addCouponCode(list);
    }

    /**
     * 分页查询优惠券
     *
     * @param pageHelper 分页帮助类
     * @param name       优惠券名称
     * @return 优惠券集合
     */
    @Override
    public PageHelper<Coupon> queryCoupon(PageHelper<Coupon> pageHelper, String name, long storeId) {
        logger.debug("queryCoupon and pageHelper : {} \r\n name: {}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        params.put("name", name);
        return pageHelper.setListDates(couponMapper.queryCoupon(pageHelper.getQueryParams(params, couponMapper.queryCouponCount(params))));
    }

    /**
     * 删除优惠券
     *
     * @param ids 优惠券id数组
     * @return 删除返回码 -1 失败 >=1成功
     */
    @Override
    public int deleteCoupon(long[] ids, long storeId) {
        logger.debug("deleteCoupon and ids:{}", ids);
        if (ArrayUtils.isEmpty(ids)) {
            logger.error("deleteCoupon error due to ids is empty");
            return -1;
        }
        registerMarketingService.batchDeleteCoupon(ids);
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("ids", ids);
        couponCodeMapper.deleteCouponCode(ids);
        couponFallMapper.deleteCouponFall(ids);
        couponFullMapper.deleteCouponFull(ids);
        return couponMapper.deleteCoupon(map);
    }

    /**
     * 复制优惠券链接
     *
     * @param id      优惠券id
     * @param storeId 店铺id
     * @return 返回优惠券链接 -1:没有查询到该优惠券, 0:优惠券已过期, 其他字符串为优惠券链接
     */
    @Override
    public String copyCoupon(long id, long storeId) {
        logger.debug("copyCoupon and id:{}\r\n storeId:{}", id, storeId);
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("id", id);
        Coupon coupon = couponMapper.queryCouponById(map);
        //优惠券过期
        String value = "0";
        //不存在该优惠券
        if (Objects.isNull(coupon)) {
            value = "-1";
        }
        //该优惠券链接
        if (coupon.getEndTime().isAfter(LocalDateTime.now())) {
            value = baseInfoSetMapper.queryBaseInfoSet().getSiteUrl() + "/receiveCoupons-" + coupon.getId() + ".htm";
        }
        return value;
    }

    /**
     * 导出优惠券券码
     *
     * @param storeId  店铺id
     * @param couponId 优惠券id
     */
    @Override
    public void exportCoupon(HttpServletResponse response, long storeId, long couponId) {
        logger.debug("exportCoupon and couponId:{}\r\n storeId:{}", couponId, storeId);
        //宽度调整map
        Map<Integer, Integer> widthSetMap = new HashMap<>();
        widthSetMap.put(1, 11000);
        widthSetMap.put(2, 5000);
        widthSetMap.put(3, 5000);
        widthSetMap.put(4, 5000);
        //栏目标题map
        Map<Integer, String> titleSetMap = new HashMap<>();
        titleSetMap.put(0, "序号");
        titleSetMap.put(1, "券码");
        titleSetMap.put(2, "券码状态");
        titleSetMap.put(3, "领取人");
        titleSetMap.put(4, "领取时间");
        //查询优惠券map
        Map<String, Object> map = new HashMap<>();
        map.put("storeId", storeId);
        map.put("id", couponId);
        Map<String, Object> backMap = ExcelUtils.createExcel(response, "优惠券券码列表", widthSetMap, titleSetMap, couponMapper.queryCouponById(map).getName() + "-券码.xls");
        HSSFSheet sheet = (HSSFSheet) backMap.get("sheet");
        List<CouponCode> couponCodeList = couponCodeMapper.queryCouponCodeByCouponId(couponId);
        IntStream.range(0, couponCodeList.size()).forEach(index -> {
            HSSFRow tempRow = sheet.createRow(index + 1);
            tempRow.createCell(0).setCellValue(index + 1);
            tempRow.createCell(1).setCellValue(couponCodeList.get(index).getCode());
            tempRow.createCell(2).setCellValue(getCouponStatus(couponCodeList.get(index).getStatus()));
            tempRow.createCell(3).setCellValue(couponCodeList.get(index).getUsername());
            tempRow.createCell(4).setCellValue(Objects.isNull(couponCodeList.get(index).getReceiveTime()) ? "" : couponCodeList.get(index).getReceiveTime().toString());
        });
        OutputStream outputStream;
        HttpServletResponse res = (HttpServletResponse) backMap.get("response");
        try {
            outputStream = res.getOutputStream();
            HSSFWorkbook hssfWorkbook = (HSSFWorkbook) backMap.get("hssfWorkbook");
            hssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            logger.error("exportCoupon error", e);
        }
    }

    /**
     * 根据店铺id查询优惠券(用于设置注册营销)
     *
     * @param storeId 店铺id
     * @return 优惠券集合
     */
    @Override
    public List<Coupon> queryCouponToSet(long storeId) {
        logger.debug("exportCoupon and  storeId:{}", storeId);
        return couponMapper.queryCouponToSet(storeId);
    }

    /**
     * 查询优惠券详情页数据
     *
     * @return 优惠券详情页数据
     */
    @Override
    public CouponDetails queryCouponDetails(long storeId, long couponId) {
        logger.debug("queryCouponDetails and  storeId:{}\r\n couponId:{}", storeId, couponId);
        //1.查询优惠券
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("storeId", storeId);
        queryMap.put("id", couponId);
        Coupon coupon = couponMapper.queryCouponById(queryMap);
        if (coupon.getType() == 1) {
            CouponFull couponFull = couponFullMapper.queryCouponFullById(couponId);
            coupon.setFullPrice(couponFull.getFullPrice());
            coupon.setPrice(couponFull.getPrice());
        }
        if (coupon.getType() == 2) {
            CouponFall couponFall = couponFallMapper.queryCouponFallById(couponId);
            coupon.setPrice(couponFall.getPrice());
        }
        //2.查询优惠券券码
        return new CouponDetails(coupon, couponCodeMapper.queryCouponCodeByCouponId(couponId));
    }

    /**
     * 根据会员id查询会员领取优惠券信息
     *
     * @param customerId 会员id
     * @return 会员领取优惠券信息集合
     */
    @Override
    public PageHelper<CouponInfo> queryCouponInfoByCustomerId(PageHelper<CouponInfo> pageHelper, long customerId, String status) {
        logger.debug("queryCouponInfoByCustomerId and  customerId:{}\r\n status:{}", customerId, status);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("status", status);
        List<CouponInfo> couponInfoList = couponCodeMapper.queryCouponInfoByCustomerId(pageHelper.getQueryParams(params, couponCodeMapper.queryCouponInfoCountByCustomerId(params))).stream().map(CouponInfo::getCouponInfoToChangeStoreName).collect(Collectors.toList());
        return pageHelper.setListDates(couponInfoList);
    }

    /**
     * 将优惠券状态标识转为中文
     *
     * @param status 优惠卷状态  0 未领取 1已领取未使用 2 已使用 3 已失效
     * @return 优惠卷状态  0 未领取 1已领取未使用 2 已使用 3 已失效
     */
    private String getCouponStatus(String status) {
        String statusString = "未知状态";
        if ("0".equals(status)) {
            statusString = "未领取";
        }
        if ("1".equals(status)) {
            statusString = "已领取未使用";
        }
        if ("2".equals(status)) {
            statusString = "已使用";
        }
        if ("3".equals(status)) {
            statusString = "已失效";
        }
        return statusString;
    }
}
