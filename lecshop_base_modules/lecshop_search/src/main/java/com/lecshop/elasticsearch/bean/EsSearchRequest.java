package com.lecshop.elasticsearch.bean;

import com.lecshop.elasticsearch.util.EsConstant;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by dujinkai on 17/6/26.
 * es搜索请求
 */
@Data
public class EsSearchRequest {

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 类型
     */
    private String type;

    /**
     * 查询字符串
     */
    private String queryString;


    /**
     * 店铺id 如果为-1 说明不过滤
     */
    private long storeId = EsConstant.NO_FILTER;

    /**
     * 分页大小
     */
    private int pageSize = 16;

    /**
     * 起始页
     */
    private int pageNum = 0;

    /**
     * 排序
     */
    private List<SortItem> sortItems;

    /**
     * 只显示有货 -1 不过滤
     */
    private int stockFilter = EsConstant.NO_FILTER;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 价格区间
     */
    private RangeItem<BigDecimal> priceRange;

    /**
     * 属性查询
     */
    private List<AttributeItem> attributes;

    /**
     * 设置排序
     *
     * @param nativeSearchQueryBuilder 查询主体
     */
    public void buildSort(Optional<NativeSearchQueryBuilder> nativeSearchQueryBuilder) {
        if (CollectionUtils.isEmpty(this.sortItems)) {
            return;
        }

        nativeSearchQueryBuilder.ifPresent(searchQueryBuilder ->
                this.sortItems.stream().forEach(sortItem -> {
                    if (!sortItem.isValidate()) {
                        return;
                    }
                    searchQueryBuilder.withSort(SortBuilders.fieldSort(sortItem.field).order(sortItem.getOrder()));
                }));
    }

    /**
     * 增加属性查询
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addAttributeQuery(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (CollectionUtils.isEmpty(this.attributes)) {
            return;
        }

        attributes.stream().forEach(attributeItem -> {
            if (!attributeItem.isValidate()) {
                return;
            }
            boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.nestedQuery("attributeValues", QueryBuilders.boolQuery().must(QueryBuilders.termQuery("attributeValues.attributeName", attributeItem.getName())).must(QueryBuilders.termQuery("attributeValues.attributeValue", attributeItem.getValue())))));
        });
    }

    /**
     * 添加库存过滤查询
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addStockFilter(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (stockFilter != EsConstant.NO_FILTER) {
            boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.rangeQuery("stock").gt(0)));
        }
    }

    /**
     * 添加店铺id过滤
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addStoreIdFilter(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (storeId != EsConstant.NO_FILTER) {
            boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.termQuery("storeId", this.storeId)));
        }
    }

    /**
     * 添加单品名称搜索
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addSkuNameQuery(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (StringUtils.isEmpty(this.queryString)) {
            return;
        }
        boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.matchQuery("skuName", this.queryString)));
    }


    /**
     * 添加品牌名称搜索
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addBrandNameQuery(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (!StringUtils.isEmpty(this.brandName)) {
            boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.termQuery("brandName", this.brandName)));
        }
    }

    /**
     * 添加上架状态的过滤
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addshelvesStatusFilter(Optional<BoolQueryBuilder> boolQueryBuilder) {
        boolQueryBuilder.ifPresent(bool -> bool.must(QueryBuilders.termQuery("shelvesStatus", "1")));
    }

    /**
     * 添加价格区间的搜索
     *
     * @param boolQueryBuilder bool 查询
     */
    public void addPriceRange(Optional<BoolQueryBuilder> boolQueryBuilder) {
        if (Objects.isNull(this.priceRange) || !this.priceRange.isValidate()) {
            return;
        }

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price");

        if (!StringUtils.isEmpty(this.priceRange.getMin())) {
            rangeQueryBuilder.from(this.priceRange.getMin());
        }


        if (!StringUtils.isEmpty(this.priceRange.getMax())) {
            rangeQueryBuilder.to(this.priceRange.getMax());
        }

        boolQueryBuilder.ifPresent(bool -> bool.must(rangeQueryBuilder));
    }

    /**
     * 范围区间
     *
     * @param <T> 范型值
     */
    @Data
    public static class RangeItem<T> {

        /**
         * 最小值
         */
        private T min;

        /**
         * 最大值
         */
        private T max;

        public boolean isValidate() {
            return null != min || null != max;
        }
    }

    /**
     * 属性查询
     */
    @Data
    public static class AttributeItem {
        /**
         * 属性名称
         */
        private String name;

        /**
         * 属性值
         */
        private String value;

        public boolean isValidate() {
            return !StringUtils.isEmpty(name) && !StringUtils.isEmpty(value);
        }
    }

    /**
     * 排序，order 0 表示升序；1表示降序
     */
    @Data
    public static class SortItem {
        /**
         * 排序的属性
         */
        private String field;

        /**
         * 排序规则
         */
        private int order;

        public SortOrder getOrder() {
            return order == 1 ? SortOrder.DESC : SortOrder.ASC;
        }

        public boolean isValidate() {
            return order != EsConstant.NO_SORT;
        }
    }
}
