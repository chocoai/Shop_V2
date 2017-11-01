// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

// 修改时候防止重复提交
var linkucount = 0;

function clearCount() {
    linkucount = 0;
}
// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_querywithdrawals.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [

            {
                "name": "tradeno", "data": "tradeno"
            },

            {
                "name": "customerid", "data": "customerid"
            },

            {
                "name": "amount", "data": "amount"
            },

            {
                "name": "status", "data": function (row) {
                if (row.status == '0') {
                    return '<span class="label label-success" style="cursor: default;">申请中</span>';
                } else if (row.status == '1') {
                    return '<span class="label label-success" style="cursor: default;">审核通过</span>';
                } else if (row.status == '2') {
                    return '<span class="label label-success" style="cursor: default;">提现完成</span>';
                } else if (row.status == '3') {
                    return '<span class="label label-success" style="cursor: default;">审核不通过</span>';
                } else {
                    return '';
                }
            }
            },

            {
                "name": "applytime", "data": "applytime"
            },

            {
                "name": "status", "data": function (row) {
                if (row.status == '0') {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs pass_btn" onclick="passReady(' + row.id + ')">' +
                        '<i class="icon-ok"></i> 通过</button> ';
                    operator += '<button type="button" class="btn btn-danger btn-xs refuse_btn" onclick="refuseReady(' + row.id + ')">'
                        + '<i class="icon-minus-sign"></i> 拒绝 </button></div>';
                    return operator;

                } else if (row.status == '1') {
                    var opr = '<div class="operation_box"><button class="btn btn-success btn-xs view_btn" type="button" onclick="tograntMoney(' + row.id + ')">' +
                        '<i class="icon-eye-open"></i> 发放 </button> ';
                    opr += '&nbsp<button class="btn btn-success btn-xs view_btn" type="button" onclick="rejectReady(' + row.id + ')">' +
                        '<i class="icon-eye-open"></i> 驳回 </button></div>';
                    return opr;
                } else {
                    return '';
                }
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

// 弹出拒绝申请对话框
function refuseReady(id) {
    $("#refuseIdHidden").val(id);
    $("#refuse_dialog").modal("show");
}

// 拒绝申请
function refuse() {
    if (!$("#refuseform").valid()) {
        return;
    }
    var withdrawals = {
        id: $("#refuseIdHidden").val(),
        reason: $("#refusereason").val()
    };
    LSFetch({
        url: basePath + '/store_refuse.htm',
        data: JSON.stringify(withdrawals),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#refuse_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 发放提现
function tograntMoney(id) {
    $("#withdraw_dialog").modal("show");
}

// 准备驳回提现申请
function rejectReady(id) {
    $("#rejectIdHidden").val(id);
    $("#reject_dialog").modal("show");
}

// 驳回提现申请
function reject() {
    var withdrawals = {
        id: $("#rejectIdHidden").val()
    };
    $("#reject_dialog").modal("hide");
    LSFetch({
        url: basePath + '/store_reject.htm',
        data: JSON.stringify(withdrawals),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#refuse_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备通过提现申请
function passReady(id) {
    $("#passIdHidden").val(id);
    $("#pass_dialog").modal("show");
}

// 通过提现申请
function pass() {
    var withdrawals = {
        id: $("#passIdHidden").val()
    };
    $("#pass_dialog").modal("hide");
    LSFetch({
        url: basePath + '/store_pass.htm',
        data: JSON.stringify(withdrawals),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#refuse_dialog').modal('hide');
            refreshDataTable();
        }
    })
}
