package com.lecshop.attention.service.impl;

import com.lecshop.attention.bean.Attention;
import com.lecshop.attention.mapper.AttentionMapper;
import com.lecshop.attention.service.AttentionService;
import com.lecshop.comment.service.CommentService;
import com.lecshop.util.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/5/27.
 * 商品关注服务接口实现
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AttentionServiceImpl.class);

    /**
     * 商品关注数据库接口
     */
    @Autowired
    private AttentionMapper attentionMapper;

    /**
     * 注入评论service
     */
    @Autowired
    private CommentService commentService;


    @Override
    public PageHelper<Attention> queryAttentions(PageHelper<Attention> pageHelper, long customerId) {
        logger.debug("queryAttentions and pageHelper:{} \r\n customerId:{}", pageHelper, customerId);
        return queryAttentionsCommon(pageHelper, customerId);
    }

    @Override
    public PageHelper<Attention> queryAttentionsForCustomerCentre(PageHelper<Attention> pageHelper, long customerId) {
        PageHelper<Attention> pageHelperData = queryAttentionsCommon(pageHelper, customerId);
        pageHelperData.getList().forEach((Attention attention) -> attention.setCommentCount(commentService.queryCommentCountBySkuId(String.valueOf(attention.getSkuId()))));
        return pageHelperData;
    }

    @Override
    public int cancelAttention(String skuId, long customerId) {
        return attentionMapper.cancelAttention(new Attention().getAttentionForCancelAttention(skuId, customerId));
    }

    private PageHelper<Attention> queryAttentionsCommon(PageHelper<Attention> pageHelper, long customerId) {
        logger.debug("queryAttentionsCommon and pageHelper:{} \r\n customerId:{}", pageHelper, customerId);
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        return pageHelper.setListDates(attentionMapper.queryAttentions(pageHelper.getQueryParams(params, attentionMapper.queryAttentionCount(params))));
    }
}
