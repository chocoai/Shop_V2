// 基本路径
var basePath = $("#basePath").val();

// 加载退单详情
loadBackOrderDetail($("#backOrderId").val());

// 加载退单详情
function loadBackOrderDetail(id) {

    LSFetch({
        url: basePath + '/store_querybackorderbyid.htm?backOrderId=' + id,
        success: function (res) {

            var credentialStr = "没有任何凭据";
            if (res.credential == "1") {
                credentialStr = "有发票";
            } else if (res.credential == "2") {
                credentialStr = "有质检报告";
            }

            $("#reason").html(res.reason);
            $("#credential").html(credentialStr);
            $("#backPrice").html(res.backPrice);
            $("#desc").html(res.desc);

            if (res.predepositPay == "1") {
                $("#predepositpay").html("退款金额退至用户预存款中!");
            }

            var picHtmls = '';
            if (res.pics != null && res.pics != '') {
                for (var i = 0; i < res.pics.split(",").length; i++) {
                    picHtmls += '<a href="' + res.pics.split(",")[i] + '" target="_blank"><img src="' + res.pics.split(",")[i] + '" alt="" style="width:30px;height:30px;"/></a>';
                }
            }

            $("#pics").html(picHtmls);


            var logHtmls = '';
            for (var j = 0; j < res.backOrderLogs.length; j++) {
                if (res.backOrderLogs[j].status == '1') {
                    logHtmls += '<tr><td>提交退款申请(操作：顾客)</td><td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '2') {
                    logHtmls += '<tr> <td>退款' + res.backPrice + '元成功 (操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '3') {
                    logHtmls += '<tr> <td>拒绝退款 (操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                }
            }

            $("#logbody").html(logHtmls);

            if (res.status == "2" || res.status == "3") {
                $("#operator").remove();
                $('#oparatordiv').after('<div style="width:106px; margin:20px auto"><a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回列表</a></div>').remove();
            }
        }
    })
}

// 准备退款审核
function tobackorder() {
    var status = $("input[name='backLogStatus']:checked").val();
    if (status == 1) {
        $(".tip").html("你确定要退款吗？");
    }
    if (status == 2) {
        $(".tip").html("你确定要拒绝退款吗？");
    }
    $('#ok_dialog').modal('show');
}

// 退款
function backorder() {
    var status = $("input[name='backLogStatus']:checked").val();
    if (status == 1) {
        // 同意退款
        LSFetch({
            url: basePath + '/store_agreetorefund.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#backRemark").val()
            },
            success: function () {
                window.location.href = basePath + "store_toquerybackorder.htm";
            }
        })
    } else if (status == 2) {
        // 拒绝退款
        LSFetch({
            url: basePath + '/store_refuserefund.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#backRemark").val()
            },
            success: function () {
                window.location.href = basePath + "store_toquerybackorder.htm";
            }
        })
    }
}

