package com.lecshop.elasticsearch.serivce;


import com.lecshop.elasticsearch.bean.EsSearchRequest;
import com.lecshop.elasticsearch.bean.EsSearchResponse;
import com.lecshop.elasticsearch.bean.EsSku;

import java.util.List;

/**
 * Created by dujinkai on 17/5/14.
 * es 搜索服务接口
 */
public interface EsSearchService {

    /**
     * 批量索引单品
     *
     * @param esSkus 单品集合
     */
    void indexSkus(List<EsSku> esSkus);


    /**
     * 删除商品
     *
     * @param spuId 商品id
     */
    void deleteSpu(long spuId);

    /**
     * 搜索
     *
     * @param request 请求对象
     * @return 返回搜索结果
     */
    EsSearchResponse search(EsSearchRequest request);
}
