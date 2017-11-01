package com.lecshop.elasticsearch.mapper;

import com.lecshop.elasticsearch.bean.EsSku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by dujinkai on 17/5/14.
 * es 数据库接口
 */
public interface EsSkuMapper extends ElasticsearchRepository<EsSku, String> {
}
