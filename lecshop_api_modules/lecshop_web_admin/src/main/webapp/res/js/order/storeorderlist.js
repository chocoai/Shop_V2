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
                url: basePath + '/querystoreorders.htm',
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
                return '<span class="label ' + statusColor[row.status] + ' label-mini">' + statusMap[row.status] + '</span>';
            }
            },
            {"name": "storeName", "data": "storeName"},
            {
                "name": "source", "data": function (row) {
                return ordersource[row.source];
            }
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box">';
                    operator += '<button class="btn btn-success btn-xs" onclick="todetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>'
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

// 查询订单详情
function todetail(id) {
    window.location.href = basePath + "tostoreorderdetail.htm?id=" + id;
}