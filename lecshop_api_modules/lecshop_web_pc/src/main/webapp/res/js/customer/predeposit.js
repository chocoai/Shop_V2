var basePath = $("#basePath").val();

// 分页组件
var predepositlaypage;

layui.use(['laypage'], function () {
    predepositlaypage = layui.laypage;
    // 引入分页组件后执行查询
    queryMessage(0);
});

function queryMessage(pageNum) {
    LSFetch({
        url: basePath + 'customer/querypredeposit.htm',
        data: {pageNum: pageNum},
        success: function (res) {
            var htmls = '';
            if (res.data.length == 0) {
                htmls += '<tr><td colspan="6">暂无记录!</td></tr>';
            } else {
                for (var i = 0; i < res.data.length; i++) {
                    var received;
                    var paid;
                    var transType;
                    if (res.data[i].transType == '1') {
                        transType = '在线充值';
                        received = res.data[i].money;
                        paid = '-'
                    } else if (res.data[i].transType == '2') {
                        transType = '订单消费';
                        received = '-';
                        paid = res.data[i].money;
                    } else if (res.data[i].transType == '3') {
                        transType = '订单退款';
                        received = res.data[i].money;
                        paid = '-'
                    }
                    htmls += '<tr><td>' + transType + '</td><td><span class="ftx02">' + received + '</span></td>' +
                        '<td><span class="ftx01">' + paid + '</span></td>' +
                        '<td>' + res.data[i].currentMoney + '</td>' +
                        '<td>' + res.data[i].createTime + '</td>' +
                        '<td>' + res.data[i].remark + '</td></tr>';
                }
            }
            $("tbody").html(htmls);
            predepositlaypage({
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

queryPreDepositCount();

function queryPreDepositCount() {
    LSFetch({
        url: basePath + 'customer/queryreepositcount.htm',
        success: function (res) {
            $("#preDepositCount").html(res);
        }
    });
}