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
                } else if (res.backOrderLogs[j].status == '1') {
                    logHtmls += '<tr><td>提交退款申请(操作：顾客)</td><td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '2') {
                    logHtmls += '<tr> <td>退款' + res.backPrice + '元成功 (操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                } else if (res.backOrderLogs[j].status == '3') {
                    logHtmls += '<tr> <td>拒绝退款 (操作：平台)留言：' + res.backOrderLogs[j].message + '</td> <td>' + res.backOrderLogs[j].createTime + '</td></tr>';
                }
            }

            $("#logbody").html(logHtmls);

            if (res.logisCompanyName != null && res.logisCompanyName != '') {
                $("#logisCompanyName").html(res.logisCompanyName);
                $('#waybillCode').html(res.waybillCode);
            } else {
                $("#wuliu").hide();
            }

        }
    })
}