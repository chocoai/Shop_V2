// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_querycoupondetails.htm',
        data: {
            couponId: $("#cid").val()
        },
        success: function (data) {
            //加载优惠券详情
            var typeString = "";
            var priceString = "";
            if (data.coupon.type == 1) {
                typeString = '满减';
                priceString = "满：" + data.coupon.fullPrice + "减：" + data.coupon.price;
            } else {
                typeString = '直降';
                priceString = "直降金额：" + data.coupon.price;
            }
            var couponHtml = '<tr> <td width="33%">优惠券名称：' + data.coupon.name + '</td> <td width="33%">生成张数：' + data.coupon.num + '</td> <td width="33%">开始时间：' + data.coupon.startTime + '</td> </tr>';
            couponHtml += '<tr> <td >优惠券描述：' + data.coupon.desc + '</td><td>限领张数：' + data.coupon.limitNum + ' </td> <td>结束时间：' + data.coupon.endTime + '</td></tr> ';
            couponHtml += '<tr> <td >类型：' + typeString + '</td><td colspan="2"> ' + priceString + ' </td ></tr>';
            $("#coupon").append(couponHtml);
            //加载优惠券券码
            var couponCode = data.couponCode;
            if (couponCode.length > 0) {
                var aList = new Array();
                var bList = new Array();
                var cList = new Array();
                for (var i = 0; i < couponCode.length; i++) {
                    if ("0" == couponCode[i].status) {
                        aList.push(couponCode[i]);
                    }
                    if ("1" == couponCode[i].status) {
                        bList.push(couponCode[i]);
                    }
                    if ("2" == couponCode[i].status) {
                        cList.push(couponCode[i]);
                    }
                }
                $("#tbody1").html(dealData(aList));
                $("#tbody2").html(dealData(bList));
                $("#tbody3").html(dealData(cList));
                $('.dataTable').dataTable({
                    "bSort": false,
                    "iDisplayLength": 5
                });
                $('.dataTables_length,.dataTables_filter').remove();
            }
        }
    });
};

// 初始化列表
initDataTable();

function dealData(list) {
    var html = "";
    if (list.length > 0) {
        if ("0" == list[0].status) {
            $.each(list, function (index, couponCode) {
                html += '<tr> <td>' + couponCode.code + '</td> <td>可领取</td> </tr>';
            });
        } else {
            $.each(list, function (index, couponCode) {
                html += '<tr><td>' + couponCode.code + '</td><td>已使用</td><td>' + couponCode.receiveTime + '</td><td>' + couponCode.username + '</td></tr>';
            });
        }
    }
    return html;
}