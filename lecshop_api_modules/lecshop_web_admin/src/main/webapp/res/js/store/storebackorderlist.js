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
                url: basePath + '/querystorebackorders.htm',
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
                return '<span class="label ' + backOrderStatusColor[row.status] + ' label-mini">' + backOrderStatusMap[row.status] + '</span>';
            }
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box">';
                    operator += '<button class="btn btn-primary btn-xs" onclick="toDetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看详情</button>';
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

// 跳转到详情页面
function toDetail(id) {
    window.location.href = basePath + "tostorebackorderdetail.htm?backOrderId=" + id;
}