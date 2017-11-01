// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querybrandtobeaudit.htm',
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

            {
                "name": "storeName", "data": function (row) {
                    return row.storeName;
                }
            },

            {
                "name": "url", "data": function (row) {
                    return '<img width="30px" height="30px" src="' + row.brand.url + '"/>';
                }
            },

            {
                "name": "name", "data": function (row) {
                    return row.brand.name;
                }
            },

            {
                "name": "nickName", "data": function (row) {
                    return row.brand.nickName;
                }
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="topass(' + row.id + ')" ><i class="icon-ok"></i> 通过</button>';
                    operator += '<button type="button" class="btn btn-danger btn-xs del_btn" onclick="torefuse(' + row.id + ')"><i class="icon-minus-sign"></i> 拒绝</button> </div>';
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
};

// 弹出通过品牌审核页面
function topass(id) {
    $("#passId").val(id);
    $('#pas_dialog').modal('show');
}

// 通过品牌审核
function passBrandAudit() {
    LSFetch({
        url: basePath + '/passbrandaudit.htm?id=' + $("#passId").val(),
        success: function (res) {
            if (res == 1) {
                $('#pas_dialog').modal('hide');
                refreshDataTable();
            }
        }
    })
}

// 弹出拒绝品牌审核对话框
function torefuse(id) {
    $("#refuseBrandId").val(id);
    $("#refuse_dialog").modal("show");
}

// 拒绝商品审核
function refuse() {
    if (!$("#refuseform").valid()) {
        return;
    }
    var brandApply = {
        id: $("#refuseBrandId").val(),
        reason: $("#refusereason").val()
    }
    LSFetch({
        url: basePath + '/refusebrandaudit.htm',
        data: JSON.stringify(brandApply),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                $('#refuse_dialog').modal('hide');
                refreshDataTable();
            }
        }
    })
}

/**
 * 通过品牌审核确认提示框
 */
function showpassconfirm(tips, ids) {
    $("#confirm").remove();
    var diahtml = '<div id="confirm" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel"aria-hidden="true">';
    diahtml += '  <div class="modal-dialog">';
    diahtml += '  <div class="modal-content">';
    diahtml += '<div class="modal-header">';
    diahtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">确认提示</h4></div>';
    diahtml += ' <div class="modal-body errormsg">' + tips + '</div>';
    diahtml += '<div class="modal-footer"> <button data-dismiss="modal" class="btn btn-default" type="button">取消</button> <button class="btn btn-success copybtn" onclick="passAll(\'' + ids + '\')" type="button">确定</button></div></div></div></div>';

    $(document.body).append(diahtml);
    $('#confirm').modal('show');
}

// 准备通过所有已选中的商品审核
function toPassAll() {
    // 选中的id
    var passIds = getSelectIds("id");
    if (passIds.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showpassconfirm("确定要通过选择的品牌审核吗?", passIds);
}

// 通过所有已选中的商品审核
function passAll(passIds) {
    LSFetch({
        url: basePath + '/batchpassbrandaudit.htm',
        data: {
            ids: passIds
        },
        success: function (data) {
            if(data >= 1){
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}

/**
 * 拒绝品牌审核确认提示框
 */
function showrefuseconfirm(tips, ids) {
    $("#confirm").remove();
    var diahtml = '<div id="confirm" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
    diahtml += '<div class="modal-dialog">';
    diahtml += '<div class="modal-content">';
    diahtml += '<div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">品牌拒绝</h4></div>';
    diahtml += '<div class="modal-body"><form class="form-horizontal tasi-form" id="refuseAllForm"><div class="form-group">';
    diahtml += '<label class="col-sm-3 control-label">拒绝原因：</label>';
    diahtml += '<div class="col-sm-9">';
    diahtml += '<textarea rows="3" class="form-control required specstr" id="reason"></textarea>';
    diahtml += '</div></div></form></div>';
    diahtml += '<div class="modal-footer"><button data-dismiss="modal" class="btn btn-default" type="button">取消</button> <button class="btn btn-success copybtn" onclick="refuseAll(\'' + ids + '\')" type="button">保存</button></div></div></div></div>';
    $(document.body).append(diahtml);
    $('#confirm').modal('show');
}

// 准备拒绝所有已选中的商品审核
function toRefuseAll() {
    var deleteIds = getSelectIds("id");
    if (deleteIds.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showrefuseconfirm("确定要拒绝选择的品牌审核吗?", deleteIds);
}

// 拒绝所有已选中的商品审核
function refuseAll(deleteIds) {
    if (!$("#refuseAllForm").valid()) {
        return;
    }
    LSFetch({
        url: basePath + '/batchrefusebrandaudit.htm',
        data: {
            ids: deleteIds,
            reason: $("#reason").val()
        },
        success: function (data) {
            if(data >= 1){
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}