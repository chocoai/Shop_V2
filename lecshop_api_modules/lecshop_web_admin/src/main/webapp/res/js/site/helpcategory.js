// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var helpcateacount = 0;

// 修改时候防止重复提交
var helpcateucount = 0;

function clearCount() {
    helpcateacount = 0;
    helpcateucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryhelpcategory.htm',
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
                "name": "name", "data": "name"
            },

            {
                "name": "sort", "data": "sort"
            },

            {
                "name": "isShow", "data": function (row) {
                    if (row.isShow == 0) {
                        return '<span class="label label-success" style="cursor: default;">是</span>';
                    } else if (row.isShow == 1) {
                        return '<span class="label label-success" style="cursor: default;">否</span>';
                    } else {
                        return '';
                    }
                 }
            },

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
};

// 准备删除帮助分类
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 添加帮助分类
function saveHelpCategory() {
    if (helpcateacount == 1) {
        return;
    }
    if (!$("#helpcategoryoperator").valid()) {
        return;
    }
    var helpCategory = {
        name: $("#helpCategoryName").val(),
        sort: $("#helpCategorySort").val(),
        isShow: $("#isShow").val()
    }
    helpcateacount = 1;
    LSFetch({
        url: basePath + '/addhelpcategory.htm',
        data: JSON.stringify(helpCategory),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#add_dialog').modal('hide');
            refreshDataTable();
        }
    })
    $("#helpcategoryoperator")[0].reset();
}

// 删除帮助分类（单个删除）
function deleteHelpClassify() {
    LSFetch({
        url: basePath + '/deletehelpcategory.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备删除所有已选中的帮助分类
function toDeleteAll() {
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除已选择的帮助分类?", ids);
}

// 删除所有已选中的帮助分类
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchDeletehelpcategory.htm',
        data: {
            ids: ids
        },
        success: function (data) {
            if(data >= 1){
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}

// 弹出修改帮助分类页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/queryhelpcategorybyid.htm?id=' + id,
        success: function (res) {
            $("#updateHelpCategoryId").val(res.id);
            $("#updateHelpCategoryName").val(res.name);
            $("#updateHelpCategorySort").val(res.sort);
            $("#isShowInUpdate").val(res.isShow);
            $('#update_dialog').modal('show');
        }
    })
}

function updateHelpCategory() {
    if (helpcateucount == 1) {
        return;
    }
    if (!$("#updateFriendLink").valid()) {
        return;
    }
    var helpCategory = {
        id: $("#updateHelpCategoryId").val(),
        name: $("#updateHelpCategoryName").val(),
        sort: $("#updateHelpCategorySort").val(),
        isShow: $("#isShowInUpdate").val()
    }
    helpcateucount = 1;
    LSFetch({
        url: basePath + '/updatehelpcategory.htm',
        data: JSON.stringify(helpCategory),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })
}
