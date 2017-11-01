// 基本路径
var basePath = $("#basePath").val();
$('.print').click(function () {
    var printData = document.getElementById("print_box").innerHTML;
    window.document.body.innerHTML = printData;
    window.print();
});

// 加载订单详情
loadOrderDetail($("#orderId").val());

function loadOrderDetail(id) {

    LSFetch({
        url: basePath + '/queryorderbyid.htm?id=' + id,
        success: function (res) {

            // 加载订单详情
            loadOrderBaseInfo(res);

            // 加载物流信息
            loadOrderLogist(res);

            // 加载商品信息
            loadOrderSkus(res);

            // 加载订单操作日志
            loadOpeationLogs(res.orderOperatonLogs);
        }
    })
}

// 加载订单单品信息
function loadOrderSkus(order) {
    var goodshtml = "";
    for (var i = 0; i < order.orderSkus.length; i++) {
        var sku = order.orderSkus[i];

        goodshtml += '<tr>' +
            '<td><img src="' + sku.skuImage + '" width="60" height="60" alt=""></td>' +
            '<td>' + sku.skuName + '</td>' +
            '<td>¥' + sku.skuPrice.toFixed(2) + '</td>' +
            '<td>' + sku.num + '</td>' +
            '<td>' + sku.skuSpecs + '</td>' +
            '<td>¥' + sku.price.toFixed(2) + '</td>' +
            '</tr>';
    }
    $("#goodsinfo").html(goodshtml);
}

// 加载订单详情
function loadOrderBaseInfo(order) {

    if (order.status == '1') {
        $("#step1time").html(order.createTime);
        $("#step1").addClass("complete");
    } else if (order.status == '2') {
        $("#step1").addClass("active");
        $("#step2").addClass("complete");
        $("#step1time").html(order.createTime);
        $("#step2time").html(order.payTime);
    } else if (order.status == '3') {
        $("#step1").addClass("active");
        $("#step2").addClass("active");
        $("#step3").addClass("complete");
        $("#step1time").html(order.createTime);
        $("#step2time").html(order.payTime);
        $("#step3time").html(order.deliveryTime);
    } else {
        $("#step1").addClass("active");
        $("#step2").addClass("active");
        $("#step3").addClass("active");
        $("#step4").addClass("complete");
        $("#step1time").html(order.createTime);
        $("#step2time").html(order.payTime);
        $("#step3time").html(order.deliveryTime);
        $("#step4time").html(order.receivingTime);
    }


    $(".ordercode").html(order.orderCode);
    $(".ordertime").html(order.createTime);
    //判断状态
    if (order.status == '1') {
        $(".orderstatus").html("未付款");
    } else if (order.status == '2') {
        $(".orderstatus").html("已付款未发货");
    } else if (order.status == '3') {
        $(".orderstatus").html("已发货");
    } else if (order.status == '4') {
        $(".orderstatus").html("已完成");
    } else if (order.status == '5') {
        $(".orderstatus").html("关闭交易");
    } else if (order.status == '6') {
        $(".orderstatus").html("关闭交易");
    } else if (order.status == '7') {
        $(".orderstatus").html("关闭交易");
    }
    $(".coucode").html(order.couponNo);
    $(".oldprice").html(order.originalPrice.toFixed(2));
    $(".youhuiprice").html(order.concessionalRate.toFixed(2));
    $(".afterprice").html(order.price.toFixed(2));

    if (order.predepositPay == "0") {
        $(".predeposit").html("否");
    } else {
        $(".predeposit").html("是");
    }

    if (order.modifyPrice == null) {
        $(".modifyprice").html("0.00");
    } else {
        $(".modifyprice").html(order.modifyPrice.toFixed(2));
    }

    if (order.usePoint == null) {
        $(".usejifen").html("0");
    } else {
        $(".usejifen").html(order.usePoint);
    }

}

// 加载物流信息
function loadOrderLogist(order) {

    $(".expresscompany").html(order.logisticsTemplate.logisticsCompany.name);
    $(".expresscode").html(order.waybillCode);
    $(".peisongtype").html("快递配送");
    $(".expressfee").html(order.freightPrice.toFixed(2));

    $(".address").html(order.orderAttr.receiptAddress);
    $(".detailaddress").html(order.orderAttr.receiptDetailAddress);
    $(".personname").html(order.orderAttr.receiptName);
    $(".tele").html(order.orderAttr.receiptPhone);
    $(".mobile").html(order.orderAttr.receiptMobile);
    $(".youbian").html(order.orderAttr.receiptZipCode);
    $(".remark").html(order.orderAttr.remark);

    if (order.orderAttr.invoiceType == '0') {
        $("#fapiao").html('<tr><td width="50%">不需要发票</td></tr>');
    } else if (order.orderAttr.invoiceType == '1') {
        $("#fapiao").html('<tr><td width="50%">发票类型: 普通发票</td></tr><tr><td width="50%">发票抬头: ' + order.orderAttr.invoiceTitle + '</td></tr><tr><td width="50%">发票内容: ' + order.orderAttr.invoiceContent + '</td></tr>');
    }
}

// 加载订单操作日志
function loadOpeationLogs(opeationLogs) {
    var operatorhtml = '';
    for (var i = 0; i < opeationLogs.length; i++) {
        var opelog = opeationLogs[i];

        var html = '';

        if (opelog.type == '1') {
            html += '<td>修改状态</td>';
        } else if (opelog.type == '2') {
            html += '<td>修改金额</td>';
        } else if (opelog.type == '3') {
            html += '<td>发货</td>';
        } else if (opelog.type == '4') {
            html += '<td>取消订单</td>';
        }

        var remark = '';
        if (opelog.remark != null) {
            remark = opelog.remark;
        }
        operatorhtml += '<tr>' +
            html +
            '<td>' + opelog.operationName + '</td>' +
            '<td>' + opelog.createTime + '</td>' +
            '<td>' + remark + '</td>' +
            '</tr>';
    }

    $("#operatorinfo").html(operatorhtml);

}