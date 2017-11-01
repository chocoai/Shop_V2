var customerpointlaypage;
var basePath = $("#basePath").val();
layui.use(['laypage'], function () {
    customerpointlaypage = layui.laypage;
    // 引入分页组件后执行查询
    queryMessage(0);
});

function queryMessage(pageNum) {
    LSFetch({
        url: basePath + 'customer/querypoint.htm',
        data: {
            pageNum: pageNum
        },
        success: function (res) {
            var htmls = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].type == '1') {
                        htmls += '<tr><td>' + res.data[i].createTime + '</td><td><span class="ftx02">' + res.data[i].point + '</span></td>' +
                            '<td><span class="ftx01">-</span></td><td>' + res.data[i].detail + '</td></tr>';
                    } else if (res.data[i].type == '2') {
                        htmls += '<tr><td>' + res.data[i].createTime + '</td><td><span class="ftx02">-</span></td>' +
                            '<td><span class="ftx01">' + res.data[i].point + '</span></td><td>' + res.data[i].detail + '</td></tr>';
                    }
                }
            } else {
                htmls += '<td colspan="4">暂无积分记录!</td>';
            }
            $("#pointTbody").html(htmls);
            customerpointlaypage({
                cont: 'pointPage',
                pages: res.totalPage,
                skin: '#5FB878',
                curr: pageNum + 1,
                jump: function (obj, first) {
                    if (!first) {
                        queryMessage(obj.curr - 1);
                    }
                }
            });
            LSFetch({
                url: basePath + 'customer/querycustomerpointcount.htm',
                success: function (res) {
                    $("#pointCount").text(res);
                }
            });
        }
    })
}

queryCustomerPointCount();

function queryCustomerPointCount() {

}