// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querysellerunauditlist.htm',
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
                    operator += '<button class="btn btn-primary btn-xs review_btn" onclick="toStoreAudit(' + row.id + ')"><i class="icon-cog"></i> 审核</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="toDelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

// 查看
function seeStoreDetailInfo(id) {
    location.href = "tounauditstoredetailinfo.htm?storeId=" + id;
}

// 弹出审核对话框
function toStoreAudit(id) {
    $("#auditStoreId").val(id);
    $("#aud_dialog").modal("show");
}

// 保存审核
function auditSave() {
    if (!$("#submitform").valid()) {
        return;
    }
    if($('#storeStatus').find('option:selected').val() == 2) {
        var storeInfo = {
            id: $("#auditStoreId").val(),
            status: $("#storeStatus").val(),
            billingCycle: $("#billingCycle").val().join()
        };
        LSFetch({
            url: basePath + '/passstoreaudit.htm',
            data: JSON.stringify(storeInfo),
            contentType: "application/json;charset=utf-8",
            success: function (res) {
                if (res == 1) {
                    $("#aud_dialog").modal("hide");
                    refreshDataTable();
                }
            }
        });
    }else if($('#storeStatus').find('option:selected').val() == 3) {
        var storeInfo = {
            id: $("#auditStoreId").val(),
            status: $("#storeStatus").val(),
            reason: $("#refusereason").val()
        };
        LSFetch({
            url: basePath + '/refusestoreaudit.htm',
            data: JSON.stringify(storeInfo),
            contentType: "application/json;charset=utf-8",
            success: function (res) {
                if (res == 1) {
                    $("#aud_dialog").modal("hide");
                    refreshDataTable();
                }
            }
        });
    }
}

// 弹出删除商家对话框
function toDelete(id) {
    $("#deleteStoreId").val(id);
    $("#del_dialog").modal("show");
}

// 删除商家
function deleteStore() {
    LSFetch({
        url: basePath + '/deletestore.htm?id=' + $("#deleteStoreId").val(),
        success: function (res) {
            if (res == 1) {
                $('#del_dialog').modal('hide');
                refreshDataTable();
            }
        }
    })
}