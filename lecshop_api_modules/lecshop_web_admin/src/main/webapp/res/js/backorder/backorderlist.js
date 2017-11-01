// 基本路径
var basePath = $("#basePath").val();

var backOrderStatusMap = {
    "1": "退款申请",
    "2": "退款成功",
    "3": "退款拒绝",
    "4": "退货申请",
    "5": "退货拒绝",
    "6": "待用户填写物流",
    "7": "待商家收货",
    "8": "退货完成",
    "9": "退货失败"
};

var backOrderStatusColor = {
    "1": "label-info",
    "2": "label-success",
    "3": "label-default",
    "4": "label-info",
    "5": "label-default",
    "6": "label-warning",
    "7": "label-warning",
    "8": "label-success",
    "9": "label-danger"
};

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querybackorders.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {"name": "backCode", "data": "backCode"},
            {"name": "orderCode", "data": "orderCode"},
            {"name": "createTime", "data": "createTime"},
            {"name": "backPrice", "data": "backPrice"},
            {
                "name": "status", "data": function (row) {
                return '<span class="label '+backOrderStatusColor[row.status]+' label-mini">'+backOrderStatusMap[row.status]+'</span>';
            }
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box">';
                    if (row.status == '1') {
                        // 退款申请
                        operator += '<button class="btn btn-primary btn-xs" onclick="toRefundReview(' + row.id + ')"><i class="icon-search"></i> 退款审核</button>';
                    } else if (row.status == '2' || row.status == '3') {
                        // 退款详情
                        operator += '<button class="btn btn-primary btn-xs" onclick="toBackOrderDetail(' + row.id + ')"><i class="icon-eye-open"></i> 退款详情</button>';
                    } else if (row.status == '4' || row.status == '7') {
                        // 退货申请
                        operator += '<button class="btn btn-primary btn-xs" onclick="toReturnReview(' + row.id + ')"><i class="icon-search"></i> 退货审核</button>';
                    } else if (row.status == '6' || row.status == '5' || row.status == '8' || row.status == '9') {
                        // 退货详情
                        operator += '<button class="btn btn-primary btn-xs" onclick="toReturnReview(' + row.id + ')"><i class="icon-eye-open"></i> 退货详情</button>';
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

// 跳转到退款审核页面
function toRefundReview(id) {
    window.location.href = basePath + "torefundreview.htm?backOrderId=" + id;
}

// 退款详情
function toBackOrderDetail(id) {
    window.location.href = basePath + "torefundreview.htm?backOrderId=" + id;
}

// 跳转到退货审核页面
function toReturnReview(id) {
    window.location.href = basePath + "toreturnreview.htm?backOrderId=" + id;
}