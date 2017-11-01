package com.lecshop.elasticsearch.serivce.impl;

import com.lecshop.elasticsearch.bean.EsSearchRequest;
import com.lecshop.elasticsearch.bean.EsSearchResponse;
import com.lecshop.elasticsearch.bean.EsSku;
import com.lecshop.elasticsearch.mapper.EsSkuMapper;
import com.lecshop.elasticsearch.serivce.EsSearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by dujinkai on 17/5/14.
 * es搜索服务接口
 */
@Service
public class EsSearchServiceImpl implements EsSearchService, InitializingBean {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(EsSearchServiceImpl.class);


    /**
     * 注入es数据库接口
     */
    @Autowired
    private EsSkuMapper esSkuMapper;

    /**
     * 注入es模版
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 结果映射
     */
    private ResultsMapper resultsMapper;

    @Override
    public void indexSkus(List<EsSku> esSkus) {
        logger.debug("indexSkus and esSkus : {}", esSkus);

        if (CollectionUtils.isEmpty(esSkus)) {
            logger.warn("do not need to indexSkus.....");
            return;
        }

        // 删除商品索引
        deleteSpu(esSkus.stream().findFirst().get().getSpuId());

        // 新增索引
        esSkuMapper.save(esSkus);
    }

    @Override
    public void deleteSpu(long spuId) {
        elasticsearchTemplate.delete(getDeleteQuery(spuId), EsSku.class);
    }

    @Override
    public EsSearchResponse search(EsSearchRequest request) {
        logger.debug("begin to search and request:{}", request);

        // 建立dsl搜索语句
        SearchQuery searchQuery = buildSearchQuery(request);

        logger.info("search from Elasticsearch and DSL :{}", searchQuery.getQuery().toString());

        return elasticsearchTemplate.query(searchQuery, searchResponse -> {
            logger.debug("result:" + searchResponse);
            return EsSearchResponse.buildEsSearchResponse(searchResponse, request,resultsMapper);
        });
    }

    /**
     * 构建ES 查询请求
     *
     * @param request 查询请求参数
     * @return Es查询请求
     */
    private SearchQuery buildSearchQuery(EsSearchRequest request) {

        //设置查询主体
        NativeSearchQueryBuilder nativeSearchQueryBuilder = buildNativeSearchQueryBuilder(request).withPageable(buildPageable(request)).withQuery(buildBoolQueryBuilder(request));

        // 增加聚合
        addAggregation(nativeSearchQueryBuilder);

        // 设置排序
        addSort(nativeSearchQueryBuilder, request);

        // 设置高亮
        setHighlighted(nativeSearchQueryBuilder);

        return nativeSearchQueryBuilder.build();
    }


    /**
     * 添加排序
     *
     * @param nativeSearchQueryBuilder 查询主题
     * @param request                  查询参数
     */
    private void addSort(NativeSearchQueryBuilder nativeSearchQueryBuilder, EsSearchRequest request) {
        request.buildSort(Optional.of(nativeSearchQueryBuilder));
    }

    /**
     * 增加聚合
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    private void addAggregation(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        // 添加品牌的聚合
        addBrandAggregation(nativeSearchQueryBuilder);

        // 添加属性聚合
        addAttributeAggregation(nativeSearchQueryBuilder);
    }

    /**
     * 增加属性的聚合
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    private void addAttributeAggregation(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.nested("attributeValues").path("attributeValues").subAggregation(AggregationBuilders.terms("attributeName").field("attributeValues.attributeName").
                size(0).subAggregation(AggregationBuilders.terms("attributeValue").field("attributeValues.attributeValue").size(0))));
    }

    /**
     * 增加品牌的聚合
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    private void addBrandAggregation(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("brandName").field("brandName").size(0));
    }


    private void setHighlighted(NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("skuName").preTags("<span class='red'>").postTags("</span>"));
    }

    /**
     * 构造NativeSearchQueryBuilder
     *
     * @param request 请求参数
     * @return 返回NativeSearchQueryBuilder
     */
    private NativeSearchQueryBuilder buildNativeSearchQueryBuilder(EsSearchRequest request) {
        return new NativeSearchQueryBuilder().withIndices(request.getIndexName()).withTypes(request.getType());
    }

    /**
     * 构造分页数据
     *
     * @param request 查询参数
     * @return 返回分页
     */
    private Pageable buildPageable(EsSearchRequest request) {
        return new PageRequest(request.getPageNum(), request.getPageSize());
    }


    /**
     * 获得bool查询
     *
     * @param request 查询参数
     * @return 返回bool查询
     */
    private BoolQueryBuilder buildBoolQueryBuilder(EsSearchRequest request) {

        // bool 查询 不懂自行补脑 https://es.xiaoleilu.com
        Optional<BoolQueryBuilder> boolQueryBuilder = Optional.of(QueryBuilders.boolQuery());

        // 添加单品名称查询
        request.addSkuNameQuery(boolQueryBuilder);

        // 店铺过滤
        request.addStoreIdFilter(boolQueryBuilder);

        // 库存过滤
        request.addStockFilter(boolQueryBuilder);

        // 根据品牌名称查询
        request.addBrandNameQuery(boolQueryBuilder);

        // 价格区间查询
        request.addPriceRange(boolQueryBuilder);

        // 属性查询
        request.addAttributeQuery(boolQueryBuilder);

        // 上架过滤
        request.addshelvesStatusFilter(boolQueryBuilder);

        return boolQueryBuilder.get();
    }


    /**
     * 获得删除商品的DeleteQuery
     *
     * @param spuId 商品id
     * @return
     */
    private DeleteQuery getDeleteQuery(long spuId) {
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(QueryBuilders.termQuery("spuId", spuId));
        return deleteQuery;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.nonNull(elasticsearchTemplate)) {
            logger.debug("begin to init es resultMapper");
            resultsMapper = new DefaultResultMapper(elasticsearchTemplate.getElasticsearchConverter().getMappingContext());
        }
    }
}
