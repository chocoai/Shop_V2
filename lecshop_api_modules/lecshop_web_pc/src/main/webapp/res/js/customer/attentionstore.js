// 分页组件
var attentionStoreLayPage;
var basePath = $("#basePath").val();

layui.use(['laypage'], function () {
    attentionStoreLayPage = layui.laypage;
    // 引入分页组件后执行查询
    queryAttentionStore(0);
});

function queryAttentionStore(pageNum) {
    LSFetch({
        url: basePath + 'customer/queryattentionstore.htm',
        data: {
            pageNum: pageNum
        },
        success: function (res) {
            var html = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var attentionStore = res.data[i];
                    html += '<div class="shop-item">';
                    html += '<div class="item-inner">';
                    html += '<div class="shop-info"><ul class="shop-text"><li>店铺名称：' + attentionStore.storeInfo.storeName + '</li><li>关注时间：' + attentionStore.createTime + '</li>' +
                        '<li>商家地址：' + attentionStore.storeInfo.companyAddress + '</li></ul><div class="p-stats"> <div class="p-comment"><i></i><em>' + attentionStore.storeAttentionCount + '</em></div> </div>' +
                        '<div class="shop-btnbox"><a target="_blank" href="" class="shop-enter"><i></i><em>进入店铺</em></a>' +
                        '<a href="javascript:;" class="unfollow_btn"><i></i><em>取消关注</em></a></div></div>';
                    html += ' <ul class="goods-list clearfix">';
                    if (attentionStore.skuList.length > 0) {
                        for (var j = 0; j < attentionStore.skuList.length; j++) {
                            var sku = attentionStore.skuList[j];
                            html += ' <li> <div class="p-img"><a href="" title="' + sku.name + '" target="_blank"> ' +
                                '<img src="' + sku.url + '" alt="' + sku.name + '"> </a></div>' +
                                '<div class="p-price">¥<strong>' + sku.price + '</strong></div></li>';
                        }
                    } else {
                    }
                    html += '</ul>';

                    html += '</div>';
                    html += '<div class="item-delete-tip"><div class="tip-main"><i class="tip-icon"></i><em class="tip-title">主人确认要抛弃我么~</em></div>' +
                        '<div class="tip-btnbox"><a href="javascript:;" class="btn-sure" onclick="cancelStoreAttention(' + attentionStore.storeId + ')">确认</a><a href="javascript:;" class="btn-cancle">取消</a></div></div>';
                    html += '</div>';
                }
                $(".shop-list").html(html);
                attentionStoreLayPage({
                    cont: 'attentionStorePage',
                    pages: res.totalPage,
                    skin: '#5FB878',
                    curr: pageNum + 1,
                    jump: function (obj, first) {
                        if (!first) {
                            queryAttentionStore(obj.curr - 1);
                        }
                    }
                });
            } else {
                html += '<div class="tip-main"><div class="tip-info"><h5 class="tip-title">您还没有关注过任何店铺哦！</h5>' +
                    '<div class="tip-btnbox"><a href="" target="_blank" class="btn-primary">去首页逛逛</a></div></div></div>';
                $(".myfollow-main").html(html);
            }
            //店铺取消关注
            $('.unfollow_btn').click(function () {
                $(this).parents('.shop-item').addClass('z-delete-tip');
            });
            $('.item-delete-tip').find('.btn-cancle').click(function () {
                $(this).parents('.shop-item').removeClass('z-delete-tip');
            });
            $('.item-delete-tip').find('.btn-sure').click(function () {
                $(this).parents('.shop-item').animate({
                    opacity: 0
                }, 300, function () {
                    $(this).remove();
                });
            });
        }
    });
}

function cancelStoreAttention(storeId) {
    LSFetch({
        url: basePath + 'customer/cancelstoreattention.htm',
        data: {
            storeId: storeId
        },
        success: function (data) {
            if (data == 1) {
                queryAttentionStore(0);
            } else {
                showTip(1, '取消关注失败');
            }
        }
    });
}