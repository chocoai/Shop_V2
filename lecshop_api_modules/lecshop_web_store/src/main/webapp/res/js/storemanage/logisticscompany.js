var basePath = $("#basePath").val();


var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_querylogisticscompanys.htm',
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
            {"name": "name", "data": "name"},
            {"name": "code", "data": "code"},
            {"name": "express100Code", "data": "express100Code"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 修改</button>';
                    operator += '<button  type="button"  class="btn btn-danger btn-xs del_btn" onclick="todelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button> </div>';
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


// 准备添加物流公司
function toadd() {
    $("#savelogistics")[0].reset();
    $('#add_dialog').modal('show');
}

// 保存物流公司
function savelogistics() {
    if (!$("#savelogistics").valid()) {
        return;
    }

    var logisticscompany = {};
    logisticscompany.name = $("#aname").val();
    logisticscompany.code = $("#acode").val();
    logisticscompany.express100Code = $("#akuaidi").val();
    LSFetch({
        url: basePath + '/store_addlogisticscompany.htm',
        data: JSON.stringify(logisticscompany),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#add_dialog').modal('hide');
            refreshDataTable();
        }
    })
}


// 跳转到删除物流公司页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除物流公司
function deleteLogistics() {
    LSFetch({
        url: basePath + '/store_deletelogisticscompany.htm?id=' + $("#deleteId").val(),
        success: function (res) {
            $('#del_dialog').modal('hide');
            refreshDataTable();
            if (res == -1) {
                showerror('物流公司已经被物流模版使用,不能删除!');
            }
        }
    })
}

// 准备删除全部物流公司
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的物流公司?", ids);
}

// 删除所有物流公司
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/store_deletelogisticscompanys.htm?ids=' + ids,
        success: function (res) {
            if (res == -1) {
                showerror('物流公司已经被物流模版使用,不能删除!');
            }
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}

// 跳转到修改页
function toupdate(id) {
    $("#logComId").val(id);

    LSFetch({
        url: basePath + '/store_querylogisticscompanybyid.htm?id=' + id,
        success: function (res) {
            $("#uname").val(res.name);
            $("#ucode").val(res.code);
            $("#ukuaidi100Code").val(res.express100Code);
        }
    })
    $('#update_dialog').modal('show');
}


// 修改物流公司
function updatelogistics() {
    if (!$("#updatelogistics").valid()) {
        return;
    }
    var logisticscompany = {};
    logisticscompany.id = $("#logComId").val();
    logisticscompany.name = $("#uname").val();
    logisticscompany.code = $("#ucode").val();
    logisticscompany.express100Code = $("#ukuaidi100Code").val();
    LSFetch({
        url: basePath + '/store_updatelogisticscompany.htm',
        data: JSON.stringify(logisticscompany),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })
}