// 分页组件
var spulaypage;

// 搜索对象
var searchRequest = {
    sortItems: new Array(),
    attributes: new Array()
};


layui.use(['laypage'], function () {
    spulaypage = layui.laypage;
    // 引入分页组件后执行查询
    searchSpu(0);
});

// 跳转到下一页
function tonextpage() {
    searchSpu(searchRequest.pageNum + 1);
}

// 跳转到上一页
function toprepage() {
    searchSpu(searchRequest.pageNum - 1);
}

function searchSpu(pageNum) {

    $('.goods_list').before('<div class="notice-filter-loading"> <div class="nf-l-wrap"> <span>正在加载中，请稍后~~</span> </div> </div>');

    LSFetch({
        url: 'searchspu.htm',
        data: JSON.stringify(getSearchData(pageNum)),
        contentType: "application/json;charset=utf-8",
        success: function (res) {

            $('.notice-filter-loading').remove();
            $("#pagenum").html('<b>' + (pageNum + 1) + '</b>/' + res.totalPages);

            $("#allspus").html(res.total);

            // 只有在第一页的时候加载属性
            if (pageNum == 0) {
                loadAttr(res.aggRes);

                // 如果是第一页 则将上一页设置成隐藏
                $("#fp_prev").addClass('disabled');
                $("#fp_prev").attr("onclick", "javascript:;");
            } else {
                $("#fp_prev").removeClass('disabled');
                $("#fp_prev").attr("onclick", "toprepage()");
            }

            // 如果到了最后一页 则把下一页设置成隐藏
            if ((pageNum + 1) == res.totalPages) {
                $("#fp_next").addClass('disabled');
                $("#fp_next").attr("onclick", "javascript:;");
            } else {
                $("#fp_next").removeClass('disabled');
                $("#fp_next").attr("onclick", "tonextpage()");
            }


            if (res.datas.length > 0) {
                var spuHtlms = '';
                for (var i = 0; i < res.datas.length; i++) {

                    var storeHtml = '';
                    // 自营
                    if (res.datas[i].storeId == 0) {
                        storeHtml = '<div class="p-icons"><i class="goods-icons">自营</i></div>';
                    }

                    spuHtlms += '<li class="goods_box">' +
                        '<div class="goods_wp">' +
                        '<div class="goods_list_img"> <a target="_blank" href="#" alt="" title=""><img alt="" title="" data-original="' + res.datas[i].skuImages[0].url + '" width="220" height="220" src="' + res.datas[i].skuImages[0].url + '"></a> </div>' +
                        '<div class="g_slides clearfix"> <a class="g_slides_prev disabled" href="javascript:;" style="display: none;"></a>' +
                        '<div class="g_slides_wrap">' +
                        getSpuImageHtml(res.datas[i].skuImages) +
                        '</div>' +
                        '<a class="g_slides_next" href="javascript:;" style="display: none;"></a> </div>' +
                        '<div class="p_price"><em>¥</em>' + res.datas[i].price + '</div>' +
                        '<div class="p_name red"><a target="_blank" href="#" title="">' + res.datas[i].skuName + '</a></div>' +
                        storeHtml +
                        '<div class="p_operate clearfix" style="display: none;"><a href="javascript:;" class="contrast"><i></i>对比</a><a href="javascript:;" class="focus"><i></i>关注</a><a href="#" target="_blank" class="addcart"><i></i>加入购物车</a></div>' +
                        '</div>' +
                        '</li>';
                }

                $("#spulists").html(spuHtlms);

                $('.goods_box').hover(function () {
                    $(this).find('.p_operate').show();
                }, function () {
                    $(this).find('.p_operate').hide();
                });
            } else {

            }
            spulaypage({
                cont: 'spupage',
                pages: res.totalPages,
                skin: '#fff',
                skip: true,
                curr: pageNum + 1,
                jump: function (obj, first) {
                    if (!first) {
                        var top = $('#spu_main').offset().top;
                        $('body').scrollTop(top);
                        searchSpu(obj.curr - 1);
                    }
                }
            });
        }
    })
}

// 加载已选择的属性
function loadChooseAttr() {
    var htmls = '';

    if (searchRequest.brandName != undefined) {
        var brandValue = "品牌";
        htmls += '<li ><a href="javascript:;" onclick="clearChoose(\'' + brandValue + '\')">品牌 ：<em>' + searchRequest.brandName + '</em><b></b></a></li>';
    }


    if (searchRequest.attributes.length > 0) {
        for (var i = 0; i < searchRequest.attributes.length; i++) {
            var attrVaule = searchRequest.attributes[i].name;
            htmls += '<li ><a href="javascript:;" onclick="clearChoose(\'' + attrVaule + '\')">' + searchRequest.attributes[i].name + '：<em>' + searchRequest.attributes[i].value + '</em><b></b></a></li>';
        }

    }

    if (htmls != '') {
        $("#chooseAttrHtmls").html(htmls);
        $("#selected_filter").show();
    } else {
        $("#selected_filter ul li").remove();
        $("#selected_filter").hide();
    }
}

// 判断是否选择了该属性作为刷选条件
function hasChooes(key) {
    if (searchRequest.attributes.length == 0) {
        return false;
    }

    for (var i = 0; i < searchRequest.attributes.length; i++) {
        if (searchRequest.attributes[i].name == key) {
            return true;
        }

    }


    return false;
}

// 加载属性
function loadAttr(aggs) {
    var htmls = '';
    console.log(searchRequest.attributes);
    var brand = {};
    for (var key in aggs) {
        if ("品牌" == key) {
            brand = aggs[key];
        } else {
            if (!hasChooes(key)) {
                htmls += '<dl class="filter_item clearfix">' +
                    '<dt>' + key + '：</dt>' +
                    '<dd>' +
                    '<div class="filterList">' +
                    ' <ul class="clearfix">' +
                    getAttrvalues(aggs[key], key) +
                    ' </ul>' +
                    '</div>' +
                    '<a class="filter_op f_more hide" href="javascript:;">更多<b></b></a> <a class="filter_op f_less hide" href="javascript:;">收起<b></b></a> </dd>' +
                    '</dl>';
            }
        }
    }
    var brandhtml = '';


    // 品牌已经筛选过 则不显示
    if (searchRequest.brandName == undefined) {
        brandhtml = '<dl class="filter_item clearfix">' +
            '<dt>品牌</dt>' +
            '<dd>' +
            '<div class="filterList">' +
            ' <ul class="clearfix">' +
            getAttrvalues(brand, '品牌') +
            ' </ul>' +
            '</div>' +
            '<a class="filter_op f_more hide" href="javascript:;">更多<b></b></a> <a class="filter_op f_less hide" href="javascript:;">收起<b></b></a> </dd>' +
            '</dl>';
    }


    $("#attrhtmls").html(brandhtml + htmls);

    loadChooseAttr();

    $(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter"); //默认显示4项，其余隐藏
    var n = $(".pro_filter .filter_item").length;
    if (n < 5) {
        $('.show_more_filter,.show_less_filter').hide();
    }
    else {
        $('.show_more_filter').show();
        $('.show_less_filter').hide();
    }
    /* 点击展开按钮操作 */
    $(".show_more_filter").click(function () {
        $(".more_filter").removeClass("more_filter").show();
        $(this).hide();
        $(".show_less_filter").show();
    });
    /* 点击收起按钮操作 */
    $(".show_less_filter").click(function () {
        $(".filter_item:visible:eq(3)").nextAll(".filter_item:visible").addClass("more_filter").hide();
        $(this).hide();
        $(".show_more_filter").show();
    });
    /* 点击更多、收起按钮操作 */
    $(".f_more").click(function () {
        $(this).siblings(".filterList").height("auto");
        $(this).siblings(".f_less").show();
        $(this).hide();
    });
    $(".f_less").click(function () {
        $(this).siblings(".filterList").height("");
        $(this).siblings(".f_more").show();
        $(this).hide();
    });
}

/**
 * 清除全部的选择条件
 */
function clearAllChoose() {
    searchRequest.attributes = new Array();
    searchRequest.brandName = undefined;
    searchSpu(0);
}

/**
 * 清除选择的条件
 */
function clearChoose(key) {
    if ("品牌" == key) {
        searchRequest.brandName = undefined;
        searchSpu(0);
    } else {
        var newAttr = new Array();
        for (var i = 0; i < searchRequest.attributes.length; i++) {
            if (key != searchRequest.attributes[i].name) {
                newAttr.push(searchRequest.attributes[i]);
            }
        }
        searchRequest.attributes = newAttr;
        searchSpu(0);
    }

}

// 获得属性只的html
function getAttrvalues(attrvalues, attrname) {
    var html = '';
    for (var i = 0; i < attrvalues.length; i++) {
        html += '<li><a href="javascript:;" onclick="searchattr(this)" attrname="' + attrname + '">' + attrvalues[i] + '</a></li>';
    }
    return html;
}

// 根据属性搜索
function searchattr(obj) {
    if ($(obj).attr("attrname") == "品牌") {
        searchRequest.brandName = $(obj).html();
    } else {
        var attr = {};
        attr.name = $(obj).attr("attrname");
        attr.value = $(obj).html();
        searchRequest.attributes.push(attr);
    }
    searchSpu(0);
}


//获得商品的图片
function getSpuImageHtml(images) {

    var html = '<ul class="clearfix">';
    for (var i = 0; i < images.length; i++) {
        if (i == 0) {
            html += '<li data-big="' + images[i].url + '" class="cur"><a href="javascript:;"><img alt="" title="" src="' + images[i].url + '"></a></li>';
        } else {
            html += '<li data-big="' + images[i].url + '" class=""><a href="javascript:;"><img alt="" title="" src="' + images[i].url + '"></a></li>';
        }
    }
    html += '</ul>';
    return html;
}

// 获得搜索的条件
function getSearchData(pageNum) {
    searchRequest.queryString = $("#keyword").val();
    searchRequest.pageNum = pageNum;
    return searchRequest;
}

// 自营过滤
function filterstore(obj) {
    var cur = $(obj);
    cur.toggleClass('selected');
    if (cur.hasClass("selected")) {
        searchRequest.storeId = 0;
    } else {
        searchRequest.storeId = -1;
    }

    searchSpu(0);
}

// 库存过滤
function filterstock(obj) {
    var cur = $(obj);
    cur.toggleClass('selected');
    if (cur.hasClass("selected")) {
        searchRequest.stockFilter = 1;
    } else {
        searchRequest.stockFilter = -1
    }

    searchSpu(0);
}

// 价格排序
function pricesort(obj) {

    var cur = $(obj);
    cur.siblings('a').removeClass();
    if (!cur.hasClass('cur')) {
        cur.addClass('cur up');
    } else if (cur.hasClass("up")) {
        cur.addClass('down').removeClass('up');
    } else {
        cur.addClass('up').removeClass('down');
    }

    var sortItem = {};
    sortItem.field = 'price';
    if (cur.hasClass("up")) {
        sortItem.order = 0;
    } else if (cur.hasClass("down")) {
        sortItem.order = 1;
    } else {
        sortItem.order = -1;
    }

    searchRequest.sortItems = new Array();

    searchRequest.sortItems.push(sortItem);


    searchSpu(0);

}

// 上架时间排序
function uptimesort(obj) {
    var cur = $(obj);
    cur.siblings('a').removeClass();
    if (!cur.hasClass('cur')) {
        cur.addClass('cur up');
    } else if (cur.hasClass("up")) {
        cur.addClass('down').removeClass('up');
    } else {
        cur.addClass('up').removeClass('down');
    }

    var sortItem = {};
    sortItem.field = 'skuUpTime';
    if (cur.hasClass("up")) {
        sortItem.order = 0;
    } else if (cur.hasClass("down")) {
        sortItem.order = 1;
    } else {
        sortItem.order = -1;
    }

    searchRequest.sortItems = new Array();

    searchRequest.sortItems.push(sortItem);


    searchSpu(0);
}