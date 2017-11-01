package com.lecshop.elasticsearch.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lecshop.util.IteratorUtils;
import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/6/26.
 * es的搜索结果
 */
@Data
public class EsSearchResponse {

    /**
     * 总记录数
     */
    private int total;

    /**
     * 记录数
     */
    private List<EsSku> datas;


    /**
     * 请求数据
     */
    @JsonIgnore
    private EsSearchRequest request;

    /**
     * 原始的ES查询结果
     */
    @JsonIgnore
    private SearchResponse originResponse;

    /**
     * 查询字符串
     */
    private String queryString;

    /**
     * 聚合后的数据 主要包含品牌和属性
     */
    private Map<String, List<String>> aggRes = new HashMap<>();

    /**
     * 构造es返回数据
     *
     * @param searchResponse es的搜索结果
     * @param request        请求数据
     * @param resultsMapper  结果映射
     * @return 返回搜索结果
     */
    public static EsSearchResponse buildEsSearchResponse(SearchResponse searchResponse, EsSearchRequest request, ResultsMapper resultsMapper) {

        EsSearchResponse esSearchResponse = new EsSearchResponse();

        if (Objects.isNull(searchResponse) || Objects.isNull(resultsMapper)) {
            return esSearchResponse;
        }

        esSearchResponse.request = request;
        esSearchResponse.datas = resultsMapper.mapResults(searchResponse, EsSku.class, null).getContent();
        esSearchResponse.total = (int) searchResponse.getHits().getTotalHits();
        esSearchResponse.aggRes = getAggResult(searchResponse);

        // 设置高亮
        IteratorUtils.zip(esSearchResponse.datas, Arrays.asList(searchResponse.getHits().getHits()), (o1, o2) -> o1.getSkuId().equals(o2.getId()), (o3, o4) -> {
            if (MapUtils.isNotEmpty(o4.getHighlightFields())) {
                o3.setSkuName(o4.getHighlightFields().get("skuName").getFragments()[0].string());
            }
        });


        return esSearchResponse;
    }

    /**
     * 获得总页数
     *
     * @return 返回总的页数 此方法不能删除
     */
    public int getTotalPages() {
        if (Objects.isNull(request)) {
            return 0;
        }
        return this.total % request.getPageSize() == 0 ? this.total / request.getPageSize() : this.total / request.getPageSize() + 1;
    }


    /***
     * 返回聚合的结果
     *
     * @param searchResponse es搜索结果
     * @return 返回聚合结果
     */
    @JsonIgnore
    private static Map<String, List<String>> getAggResult(SearchResponse searchResponse) {

        Map<String, List<String>> map = new HashMap<>();

        map.put("品牌", getStringTerms(searchResponse.getAggregations().get("brandName")));
        map.putAll(getAttributes(searchResponse.getAggregations().get("attributeValues")));
        return map;
    }

    /**
     * 获得StringTerms的值的集合
     *
     * @param aggregation 聚合
     * @return 返回StringTerms的值的集合
     */
    private static List<String> getStringTerms(Aggregation aggregation) {
        if (Objects.isNull(aggregation)) {
            return Collections.emptyList();
        }

        StringTerms stringTerms = (StringTerms) aggregation;

        if (CollectionUtils.isEmpty(stringTerms.getBuckets())) {
            return Collections.emptyList();
        }

        return stringTerms.getBuckets().stream().map(key -> (String) key.getKey()).collect(Collectors.toList());
    }

    /**
     * 获得属性的聚合
     *
     * @param aggregation 属性的聚合
     * @return 返回属性的聚合
     */
    private static Map<String, List<String>> getAttributes(Aggregation aggregation) {
        Map<String, List<String>> attributes = new HashMap<>();

        if (Objects.isNull(aggregation)) {
            return attributes;
        }

        InternalNested nested = (InternalNested) aggregation;
        StringTerms stringTerms = (StringTerms) nested.getAggregations().asList().get(0);
        stringTerms.getBuckets().stream().forEach(bucket -> attributes.put((String) bucket.getKey(), getStringTerms(bucket.getAggregations().asList().get(0))));
        return attributes;
    }


}
