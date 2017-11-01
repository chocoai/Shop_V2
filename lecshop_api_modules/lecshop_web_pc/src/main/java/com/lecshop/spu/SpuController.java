package com.lecshop.spu;

import com.lecshop.elasticsearch.bean.EsSearchRequest;
import com.lecshop.elasticsearch.bean.EsSearchResponse;
import com.lecshop.elasticsearch.serivce.EsSearchService;
import com.lecshop.elasticsearch.util.EsConstant;
import com.lecshop.util.BaseResponse;
import com.lecshop.util.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dujinkai on 17/6/28.
 * 商品控制器
 */
@Controller
public class SpuController {


    /**
     * 注入es搜索服务接口
     */
    @Autowired
    private EsSearchService esSearchService;

    /**
     * 分类搜索跳转到商品列表页
     *
     * @return 返回商品列表页
     */
    @UnAuth
    @RequestMapping("/tospulist")
    public ModelAndView toSpuList(long cateId) {
        return new ModelAndView("spu/spulist").addObject("cateId", cateId);
    }

    /**
     * 关键字搜索跳转到商品列表页
     *
     * @param keyword 关键字
     * @return 返回商品列表页
     */
    @UnAuth
    @RequestMapping("/tokeywordsearch")
    public ModelAndView toKeywordSearch(String keyword) {
        return new ModelAndView("spu/spulist").addObject("keyword", keyword);
    }


    /**
     * 商品搜索
     *
     * @param esSearchRequest 搜索请求
     * @return 返回搜索的商品数据
     */
    @UnAuth
    @ResponseBody
    @RequestMapping("/searchspu")
    public EsSearchResponse searchSpu(@RequestBody EsSearchRequest esSearchRequest) {
        esSearchRequest.setIndexName(EsConstant.INDEX_NAME);
        esSearchRequest.setType(EsConstant.DOCUMENT_TYPE);
        return esSearchService.search(esSearchRequest);
    }

}
