
// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryattentions.htm?customerId='+$("#customerId").val(),
                data: dataTableAjaxData(data),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "url", "data": function (row) {
                return '<img src="' + row.sku.url + '" height="36">';
            }
            },
            {"name": "name", "data": "sku.name"},
            {"name": "skuno", "data": "sku.skuNo"},
            {"name": "price", "data": "sku.price"},
            {"name": "createTime", "data": "createTime"},
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
