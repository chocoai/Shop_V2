// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querycustomerpoint.htm?customerId=' + $("#customerId").val(),
                data: dataTableAjaxData(data),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {"name": "point", "data": "point"},
            {"name": "detail", "data": "detail"},
            {"name": "createTime", "data": "createTime"},
        ],
        ordering: false
    })

};

// 初始化列表
initDataTable();

// 加载用户积分总额
loadcustomerpointcount();


// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
}

// 保存积分
function savepoint() {

    if (!$("#savepoint").valid()) {
        return;
    }

    var customerPoint = {};
    customerPoint.customerId = $("#customerId").val();
    customerPoint.detail = $("#remark").val();
    customerPoint.point = $("#point").val();
    LSFetch({
        url: basePath + '/addcustomerpoint.htm',
        data: JSON.stringify(customerPoint),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $("#remark").val('');
            $("#point").val('');
            showerror('修改积分成功');
            loadcustomerpointcount();
            refreshDataTable();
        }
    })
}

// 加载用户积分总额
function loadcustomerpointcount() {
    LSFetch({
        url: basePath + '/querycustomerpointcount.htm?customerId=' + $("#customerId").val(),
        success: function (res) {
            $("#totalpoint").html(res);
        }
    })
}