// 分页组件
var myCouponLayPage;
var basePath = $("#basePath").val();

layui.use(['laypage'], function () {
    myCouponLayPage = layui.laypage;
    // 引入分页组件后执行查询
    queryMyCoupon(0, 1);
});

function queryMyCoupon(pageNum, status) {
    LSFetch({
        url: basePath + 'customer/querycouponinfo.htm',
        data: {
            pageNum: pageNum,
            status: status
        },
        success: function (res) {
            var html = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var myCoupon = res.data[i];
                    html += myCoupon.status == 1 ? '<div class="coupon-item">' : '<div class="coupon-item coupon-item-dgray">';
                    html += '<div class="c-type">';
                    var price = myCoupon.type == 1 ? myCoupon.price : myCoupon.fallPrice;
                    html += '<div class="c-price"><em>¥</em><strong>' + price + '</strong></div><div class="c-type-top"></div><div class="c-type-bottom"></div>';
                    html += '</div>';
                    html += '<div class="c-msg">';
                    var useRule = myCoupon.type == 1 ? '满' + myCoupon.fullPrice + '元可用' : '直降(直接抵扣现金)';
                    html += '<div class="c-range"><div class="range-item clearfix"><span class="label">限平台：</span><span class="txt">' + myCoupon.storeName + '</span></div>' +
                        '<div class="range-item clearfix"><span class="label">使用条件：</span><span class="txt">' + useRule + '</span></div>' +
                        '<div class="range-item clearfix"><span class="label">使用日期：</span><span class="txt">' + myCoupon.startTime + ' 至 ' + myCoupon.endTime + '</span></div></div>';
                    html += '</div>';
                    html += '</div>';
                }
                $(".coupon-items").html(html);
                myCouponLayPage({
                    cont: 'myCouponPage',
                    pages: res.totalPage,
                    skin: '#5FB878',
                    curr: pageNum + 1,
                    jump: function (obj, first) {
                        if (!first) {
                            queryMyCoupon(obj.curr - 1, status);
                        }
                    }
                });
            } else {
            }
        }
    });
}

function changeTable(obj) {
    $(".tab-trigger").children("li").removeClass("curr");
    $(obj).parent("li").addClass("curr");
    queryMyCoupon(0, $(obj).attr("search-type"));
}
