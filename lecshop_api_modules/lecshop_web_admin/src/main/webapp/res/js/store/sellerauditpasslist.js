// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querysellerauditpasslist.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {"name": "companyName", "data": "companyName"},
            {"name": "storeName", "data": "storeName"},
            {"name": "effectiveTime", "data": "effectiveTime"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-success btn-xs" onclick="seeStoreDetailInfo(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>';
                    operator += '<button class="btn btn-primary btn-xs review_btn" onclick="storeInfoSet(' + row.id + ')"><i class="icon-cog"></i> 设置</button></div>';
                    return operator;
                }
            }
        ],
        ordering: false
    });
};

// 初始化列表
initDataTable();
// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

function seeStoreDetailInfo(id) {
    location.href = "tostoredetailinfo.htm?storeId=" + id;
}

function storeInfoSet(id) {
    location.href = "tostoreinfoset.htm?storeId=" + id;
}