// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querydistributor.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {"name": "username", "data": "username"},
            {"name": "mobile", "data": "mobile"},
            {"name": "email", "data": "email"},
            {
                "name": "superiorId", "data": function (row) {
                if (row.superiorId == 0) {
                    return "一级分销商"
                } else {
                    return row.superiorId;
                }
            }
            },
            {
                "data": function (row) {
                    return '<div class="operation_box"><button class="btn btn-danger btn-xs del_btn" onclick="deleteDistributor(' + row.id + ',' + row.superiorId + ')"><i class="icon-trash"></i> 删除</button></div>';
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

//删除按钮点击事件
function deleteDistributor(customerId, superiorId) {
    $("#del_dialog input[name='customerId']").val(customerId);
    $("#del_dialog input[name='superiorId']").val(superiorId);
    $("#del_dialog").modal("show");
}

function deleteSaveBtn() {
    var distributor = {
        id: $("#del_dialog input[name='customerId']").val(),
        username: "",
        mobile: "",
        email: "",
        belongStoreId: "",
        superiorId: $("#del_dialog input[name='superiorId']").val()
    };
    LSFetch({
        url: basePath + '/deletedistributor.htm',
        data: JSON.stringify(distributor),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            $("#del_dialog").modal("hide");
            if (data == -1) {
                showerror("编辑失败");
            }
            if (data == 1) {
                showerror("编辑成功");
                refreshDataTable();
            }
        }
    });
}