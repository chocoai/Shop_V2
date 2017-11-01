
$(".search_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:00',
    weekStart: 1,
    autoclose: true,
    language: 'zh',
    todayBtn: true,
    viewSelect: 'hour'
});

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querypredeposits.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "transType", "data": function (row) {
                if (row.transType == '1') {
                    return '在线充值';
                } else if (row.transType == '2') {
                    return '订单消费';
                } else if (row.transType == '3') {
                    return '订单退款';
                } else {
                    return '未知';
                }

            }
            },
            {
                "name": "money", "data": function (row) {
                if (row.transType == '1' || row.transType == '3') {
                    return '<span style="color: green">+' + row.money + '</span>';
                } else {
                    return '<span style="color: red">-' + row.money + '</span>';
                }

            }
            },
            {"name": "createTime", "data": "createTime"},
            {"name": "transCode", "data": "transCode"},
            {"name": "currentMoney", "data": "currentMoney"},
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
