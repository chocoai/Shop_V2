// 基本路径
var basePath = $("#basePath").val();

// 加载退单信息
loadBackOrder($("#backOrderId").val());
function loadBackOrder(id) {
    LSFetch({
        url: basePath + '/querybackorderbyid.htm?backOrderId=' + id,
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

            var skuHtmls = '';
            for (var j = 0; j < res.orderSkus.length; j++) {
                var sku = res.orderSkus[j];
                skuHtmls += '<tr>';
                skuHtmls += '<td><img src="' + sku.skuImage + '" width="60" height="60" alt=""></td>';
                skuHtmls += '<td>' + sku.skuName + '</td>';
                skuHtmls += '<td>' + sku.skuNo + '</td>';
                skuHtmls += '<td>' + sku.skuPrice + '</td>';
                skuHtmls += '<td>' + sku.num + '</td>';
                skuHtmls += '<td>' + sku.price + '</td>';
                skuHtmls += '</tr>';

            }

            $("#skuHtmls").html(skuHtmls);


            var logHtmls = '';
            for (var j = 0; j < res.backOrderLogs.length; j++) {
                if (res.backOrderLogs[j].status == '4') {
                    logHtmls += '<tr><td>提交退货申请(操作：顾客)</td><td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '6') {
                    logHtmls += '<tr><td>同意退货(操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '5') {
                    logHtmls += '<tr> <td>拒绝退货(操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '7') {
                    logHtmls += '<tr> <td>填写快递信息(操作：顾客)</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '8') {
                    logHtmls += '<tr> <td>退款' + res.realBackPrice + '成功 (操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '9') {
                    logHtmls += '<tr> <td>拒绝退货(操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                }

            }

            if (res.predepositPay == "1") {
                $("#predepositpay").html("退款金额退至用户预存款中!");
            }

            $("#logbody").html(logHtmls);

            if (res.logisCompanyName != null && res.logisCompanyName != '') {
                $("#logisCompanyName").html(res.logisCompanyName);
                $('#waybillCode').html(res.waybillCode);
            } else {
                $("#wuliu").hide();
            }

            if (res.status == '4') {
                $('#stepone').show().after('<div style="width:240px; margin:20px auto"><a class="btn btn-default btn-lg"  href="javascript:history.go(-1);">返回</a>&nbsp;<a class="btn btn-info btn-lg print" onclick="save()">保存</a></div>');
            }

            if (res.status == '7') {
                $('#steptwo').show().after('<div style="width:240px; margin:20px auto"><a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回</a>&nbsp;<a class="btn btn-info btn-lg print" onclick="savetwo()">保存</a></div>');
            }

            if (res.status == '5' || res.status == '6' || res.status == '8' || res.status == '9') {
                $('#stepone').remove();
                $('#steptwo').after('<div style="width:106px; margin:20px auto"><a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回</a></div>').remove();
            }
        }
    })
}


function save() {
    var status = $("input[name='backLogStatus']:checked").val();
    if (status == 1) {
        LSFetch({
            url: basePath + '/agreetoreturn.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#backRemark").val()
            },
            success: function () {
                window.location.href = basePath + "toquerybackorder.htm";
            }
        })
    } else if (status == 2) {
        LSFetch({
            url: basePath + '/refusereturn.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#backRemark").val()
            },
            success: function () {
                window.location.href = basePath + "toquerybackorder.htm";
            }
        })
    }
}

function savetwo() {
    var status = $("input[name='backOrderStatus']:checked").val();
    if (status == 1) {
        $(".tip").html("你确定要退款吗？");

    } else if (status == 2) {
        $(".tip").html("你确定要拒绝退款吗？");
    }

    $('#ok_dialog').modal('show');

}

function savebackorder() {
    var status = $("input[name='backOrderStatus']:checked").val();

    if (status == 1) {
        if (!$("#steptwoform").valid()) {
            $('#ok_dialog').modal('hide');
            return;
        }
        LSFetch({
            url: basePath + '/confirmreturn.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#remark").val(),
                money: $("#backRealPrice").val()
            },
            success: function () {
                window.location.href = basePath + "toquerybackorder.htm";
            }
        })
    } else if (status == 2) {
        LSFetch({
            url: basePath + '/refusetoreceive.htm',
            data: {
                backOrderId: $("#backOrderId").val(),
                message: $("#remark").val()
            },
            success: function () {
                window.location.href = basePath + "toquerybackorder.htm";
            }
        })
    }
}


function showPrice(obj) {
    if (obj == 1) {
        $("#orderProceStatus").hide();
    } else {
        $("#orderProceStatus").show();
    }
}