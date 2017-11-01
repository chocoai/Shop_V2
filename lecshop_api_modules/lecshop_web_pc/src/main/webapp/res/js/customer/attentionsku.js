// 分页组件
var attentionLayPage;
var basePath = $("#basePath").val();

layui.use(['laypage'], function () {
    attentionLayPage = layui.laypage;
    // 引入分页组件后执行查询
    queryAttention(0);
});

function queryAttention(pageNum) {
    LSFetch({
        url: basePath + 'customer/queryattentionsku.htm',
        data: {
            pageNum: pageNum,
            pageSize: 20
        },
        success: function (res) {
            var html = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var attention = res.data[i];
                    html += '<div class="mf-goods-item"><div class="p-img"><a href="" target="_blank">' +
                        '<img width="160" height="160" src="' + attention.sku.url + '" alt="三星(SAMSUNG)UA65KS9800JXXZ">' +
                        '</a></div><div class="p-name"><a href="" target="_blank" title="' + attention.sku.name + '">' + attention.sku.name + '</a>' +
                        '</div><div class="p-price">¥<strong>' + attention.sku.price + '</strong></div>' +
                        '<div class="p-stats"><div class="p-comment"><i></i><em>' + attention.commentCount + '</em></div></div>' +
                        '<div class="p-operate"><a href="" target="_blank" class="op-btn btn-cart"><i></i><em>加入购物车</em></a>' +
                        '<a href="javascript:;" target="_blank" class="op-btn btn-focus"><i></i><em>取消关注</em></a></div><div class="item-delete-tip">' +
                        '<div class="tip-main"><i class="tip-icon"></i><em class="tip-title">主人确认要抛弃我么~</em></div>' +
                        '<div class="tip-btnbox"><a href="javascript:;" class="btn-sure" onclick="cancelAttention(' + attention.skuId + ')">确认</a><a href="javascript:;" class="btn-cancle">取消</a></div>' +
                        '</div></div>';
                }
                $(".mf-goods-list").html(html);
                attentionLayPage({
                    cont: 'attentionPage',
                    pages: res.totalPage,
                    skin: '#5FB878',
                    curr: pageNum + 1,
                    jump: function (obj, first) {
                        if (!first) {
                            queryAttention(obj.curr - 1);
                        }
                    }
                });
            } else {
                html += '<div class="tip-main"><div class="tip-info"><h5 class="tip-title">您还没有关注过任何商品哦！</h5>' +
                    '<div class="tip-btnbox"><a href="" target="_blank" class="btn-primary">去首页逛逛</a></div></div></div>';
                $(".myfollow-main").html(html);
            }
            //商品取消关注
            $('.btn-focus').click(function () {
                $(this).parents('.mf-goods-item').addClass('z-delete-tip');
            });
            $('.item-delete-tip').find('.btn-cancle').click(function () {
                $(this).parents('.mf-goods-item').removeClass('z-delete-tip');
            })
            $('.item-delete-tip').find('.btn-sure').click(function () {
                $(this).parents('.mf-goods-item').animate({
                    opacity: 0
                }, 300, function () {
                    $(this).remove();
                })
            });
        }
    });
}

function cancelAttention(skuId) {
    LSFetch({
        url: basePath + 'customer/cancelattention.htm',
        data: {
            skuId: skuId
        },
        success: function (data) {
            if (data == 1) {
                queryAttention(0);
            } else {
                showTip(1, '取消关注失败');
            }
        }
    });
}