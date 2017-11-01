// 基本路径
var basePath = $("#basePath").val();
// 订单状态
var statusMap = {
    "1": "待付款",
    "2": "待发货",
    "3": "待收货",
    "4": "已完成",
    "5": "已关闭",
    "6": "已关闭",
    "7": "已关闭"
};

var statusColor = {
    "1": "label-warning",
    "2": "label-warning",
    "3": "label-warning",
    "4": "label-success",
    "5": "label-default",
    "6": "label-default",
    "7": "label-default"
};

// 订单来源
var ordersource = {
    "1": "PC",
    "2": "H5",
    "3": "APP"
};


$(".search_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:00',
    weekStart: 1,
    autoclose: true,
    language: 'zh',
    todayBtn: true,
    viewSelect: 'hour'
});

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryorders.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {"name": "orderCode", "data": "orderCode"},
            {"name": "customerName", "data": "customerName"},
            {"name": "createTime", "data": "createTime"},
            {"name": "price", "data": "price"},
            {
                "name": "status", "data": function (row) {
                return '<span class="label '+statusColor[row.status]+' label-mini">'+statusMap[row.status]+'</span>';
            }
            },
            {
                "name": "payType", "data": function (row) {
                if (row.payType == "1") {
                    return "在线支付";
                } else {
                    return "货到付款";
                }
            }
            },
            {
                "name": "source", "data": function (row) {
                return ordersource[row.source];
            }
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box">';
                    //待付款
                    if (row.status == '1') {
                        operator += '<button class="btn btn-success btn-xs" onclick="todetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>' +
                            '<button class="btn btn-primary btn-xs" onclick="toConfirmOrder(' + row.id + ');"> <i class="icon-ok"></i> 确认付款 </button>' +
                            '<button class="btn btn-primary btn-xs edit_price" onclick="tomodifyprice( ' + row.id + ',' + row.price + ')"><i class="icon-pencil"></i> 改价 </button>' +
                            '<button class="btn btn-danger btn-xs del_btn" onclick="tocancelOrder(' + row.id + ')"><i class="icon-minus"></i> 取消 </button>';
                    } else if (row.status == '2') {
                        // 待发货
                        operator += '<button class="btn btn-success btn-xs" onclick="todetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>' +
                            '<button class="btn btn-primary btn-xs send_btn" onclick="toDelivery(' + row.id + ')"><i class="icon-ok"></i> 发货</button>';
                    } else {
                        operator += '<button class="btn btn-success btn-xs" onclick="todetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>'
                    }
                    operator += '</div>';
                    return operator;
                }
            }
        ],
        ordering: false
    })

};

// 初始化列表
initDataTable();


// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
}

// 切换tab
function changetab(obj) {
    $("#searchType").val($(obj).attr("search-type"));
    $(obj).addClass('btn-primary').siblings('a').removeClass('btn-primary');

    refreshDataTable();
}

// 准备确认订单
function toConfirmOrder(id) {
    $("#confirmId").val(id);
    $('#confirm_dialog').modal('show');
}

// 确认付款
function confirOrder() {
    LSFetch({
        url: basePath + '/confirmorder.htm?id=' + $("#confirmId").val(),
        success: function () {
            $('#confirm_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备取消订单
function tocancelOrder(id) {
    $("#cancelId").val(id);
    $('#cancel_dialog').modal('show');
}

// 取消订单
function cancelOrder() {
    LSFetch({
        url: basePath + '/cancelorder.htm?id=' + $("#cancelId").val(),
        success: function () {
            $('#cancel_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备修改价格
function tomodifyprice(id, price) {
    $("#updatePrice").attr("max", price);
    $("#updatePriceId").val(id);
    $('#update_price_form')[0].reset();
    $('#modify_dialog').modal('show');
}

// 修改价格
function modifyprice() {
    if (!$('#update_price_form').valid()) {
        return;
    }


    LSFetch({
        url: basePath + '/modifyprice.htm',
        data: {
            id: $("#updatePriceId").val(),
            price: $("#updatePrice").val(),
            reason: $("#reason").val()
        },
        success: function () {
            $('#modify_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备发货
function toDelivery(id) {

    LSFetch({
        url: basePath + '/querylogisticstemplatebyorderid.htm?id=' + id,
        success: function (res) {
            $("#expressName").html(res.logisticsCompany.name);
        }
    })
    $('#deliveryform')[0].reset();
    $("#deliverId").val(id);
    $('#send_dialog').modal('show');
}

// 发货
function delivery() {
    if (!$('#deliveryform').valid()) {
        return;
    }
    LSFetch({
        url: basePath + '/deliverorder.htm',
        data: {
            id: $("#deliverId").val(),
            waybillCode: $("#expressNo").val()
        },
        success: function () {
            $('#send_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 查询订单详情
function todetail(id) {
    window.location.href = basePath + "toorderdetail.htm?id=" + id;
}