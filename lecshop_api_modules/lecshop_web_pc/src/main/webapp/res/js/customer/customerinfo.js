$(document).ready(function () {
    $("body").on("click", ".customerinfoitem", function () {
        //点击已读消息
        $(this).removeClass('no-look');
        LSFetch({
            url: basePath + 'customer/updatecustomerinfoisread.htm?id=' + $(this).find(".customerInfoId").val(),
            success: function (res) {
                if (res == 0) {

                }
            }
        });
    });
    $("body").on("click", ".deleteCustomerInfoById", function () {
        //删除消息
        $(this).parents('.mg-coupon').animate({
            opacity: 0
        },300, function () {
            $(this).remove();
        });
        LSFetch({
            url: basePath + 'customer/deletecustomer.htm?id=' + $(this).parents('.customerinfoitem ').find(".customerInfoId").val(),
            success: function (res) {
                if (res == 0) {

                }
            }
        });

    });
});

var basePath = $("#basePath").val();

// 分页组件
var customerinfolaypage;

layui.use(['laypage'], function () {
    customerinfolaypage = layui.laypage;
    // 引入分页组件后执行查询
    queryMessage(0);
});

function queryMessage(pageNum) {
    LSFetch({
        url: basePath + 'customer/querymessage.htm',
        data: {pageNum: pageNum},
        success: function (res) {
            var htmls = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var isRead;
                    if (res.data[i].isRead == '0') {
                        isRead = 'no-look';
                    } else if (res.data[i].isRead == '1') {
                        isRead = '';
                    }
                    htmls += '<div class="clearfix"><div class="mg-coupon">' +
                        '<span class="mg-time">' + res.data[i].createTime + '</span>' +
                        '<div class="customerinfoitem mg-box ' + isRead + '"><input type="hidden" value="' + res.data[i].id + '" class="customerInfoId"/><div class="mg-title">' +
                        '<h4 class="fll">' + res.data[i].title + '</h4>' +
                        '<s style="cursor:pointer" class="deleteCustomerInfoById"></s>' +'' +
                        '</div><div class="mg-content clearfix">' + res.data[i].content + '</div></div></div></div>';
                }
            } else {
                htmls += '<div class="tip-main"><div class="tip-info"><h5 class="tip-title">您还没有任何消息哦！</h5></div></div>';
            }
            $("#customerinfo").prepend(htmls);
            customerinfolaypage({
                cont: 'messagePage',
                pages: res.totalPage,
                skin: '#5FB878',
                curr: pageNum + 1,
                jump: function (obj, first) {
                    if (!first) {
                        queryMessage(obj.curr - 1);
                    }
                }
            });
        }
    })
}
