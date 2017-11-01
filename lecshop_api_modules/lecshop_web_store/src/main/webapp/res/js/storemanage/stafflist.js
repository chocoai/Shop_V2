// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_querystaff.htm',
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
            }, {
                "name": "name", "data": "userName"
            }, {
                "name": "name", "data": "releName"
            }, {
                "name": "name", "data": "roleName"
            },
            {
                "data": function (row) {
                    if (row.status == 3) {
                        return '<span class="label label-default">否</span>';
                    } else {
                        return '<span class="label label-success">是</span>';
                    }
                }
            }, {
                "name": "loginTime", "data": function (row) {
                    if (row.loginTime == '' || row.loginTime == null) {
                        return '暂无登录信息';
                    } else {
                        return row.loginTime;
                    }
                }
            }, {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-primary btn-xs edit_btn" onclick="editManager(' + row.id + ',' + row.roleId + ',' + row.status + ')"><i class="icon-pencil"></i> 编辑</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteManager(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

//加载角色下拉菜单
function addStaffBtn() {
    $("#add_dialog").html(addhtml);
    LSFetch({
        url: basePath + '/store_querystaffrole.htm',
        success: function (data) {
            var html = '';
            if (data.length < 1) {
                html = '<option value="">请先添加职位</option>';
            } else {
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].roleName + '</option>';
                }
            }
            $("#addForm select[name='roleName']").html(html);
        }
    });
    $('#add_dialog').modal('show');
}

//添加管理员保存按钮点击事件
function addSaveBtn() {
    if (!$("#addForm").valid()) {
        return;
    }
    if ($("#addForm input[name='password']").val() != $("#addForm input[name='rePassword']").val()) {
        showerror("两次密码不一致");
        return;
    }
    var customerAndRole = {
        userName: $("#addForm input[name='userName']").val(),
        releName: $("#addForm input[name='releName']").val(),
        password: $("#addForm input[name='password']").val(),
        rePassword: $("#addForm input[name='rePassword']").val(),
        roleId: $("#addForm select[name='roleName']").val(),
        status: $("#addForm select[name='status']").val()

    };
    LSFetch({
        url: basePath + '/store_addstaff.htm',
        data: JSON.stringify(customerAndRole),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                $('#add_dialog').modal('hide');
                showerror("添加成功");
                refreshDataTable();
            } else if (data == -1) {
                showerror("员工已达最大数量");
            } else {
                showerror("添加失败");
            }
        }
    });
}

//管理员编辑按钮点击事件
function editManager(id, roleId, status) {
    $("#managerIdHidden").val(id);
    LSFetch({
        url: basePath + '/store_querystaffrole.htm',
        success: function (data) {
            var html = '';
            if (data.length < 1) {
                html = '<option value="">请先添加职位</option>';
            } else {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == roleId) {
                        html += '<option value="' + data[i].id + '" selected>' + data[i].roleName + '</option>';
                    } else {
                        html += '<option value="' + data[i].id + '">' + data[i].roleName + '</option>';
                    }
                }
            }
            $("#edit_dialog select[name='roleName']").html(html);
        }
    });
    $("#edit_dialog select[name='status']").val(status);
    $('#edit_dialog').modal('show');
}

//管理员编辑保存事件
function editSaveBtn() {
    if (!$("#editForm").valid()) {
        return;
    }
    var storeStaff = {
        id: $("#managerIdHidden").val(),
        roleId: $("#edit_dialog select[name='roleName']").val(),
        status: $("#edit_dialog select[name='status']").val()
    };
    LSFetch({
        url: basePath + '/store_editstaff.htm',
        data: JSON.stringify(storeStaff),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                $('#edit_dialog').modal('hide');
                refreshDataTable();
                showerror("编辑成功");
            } else {
                showerror("编辑失败");
            }
        }
    });
}

//删除单个管理员
function deleteManager(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

//删除单个管理员 保存事件
function deleteManagerSaveBtn() {
    LSFetch({
        url: basePath + '/store_deletestaff.htm',
        data: {
            customerIds: $("#deleteId").val()
        },
        success: function (data) {
            if (data == -1) {
                showerror("请至少选择一条记录");
            }
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    });
}

// 准备批量删除
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的员工?", ids);
}
// 批量删除
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/store_deletestaff.htm',
        data: {
            customerIds: ids
        },
        success: function (data) {
            if (data == -1) {
                showerror("请至少选择一条记录");
            }
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    });
}