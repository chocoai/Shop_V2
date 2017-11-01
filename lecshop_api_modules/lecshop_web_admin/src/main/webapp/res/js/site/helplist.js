// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var helplistacount = 0;

// 修改时候防止重复提交
var helplistucount = 0;

function clearCount() {
    helplistacount = 0;
    helplistucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryhelplist.htm',
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
                "name": "cateName", "data": "cateName"
            },

            {
                "name": "name", "data": "name"
            },

            {
                "name": "sort", "data": "sort"
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

// 跳转到修改帮助页面
function toupdate(id) {
    window.location.href = basePath + 'toedithelp.htm' + window.location.search + "&id=" + id;
}

// 准备删除帮助（单个删除）
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 删除帮助（单个删除）
function deleteHelp() {
    LSFetch({
        url: basePath + '/deletehelp.htm?id=' + $("#deleteId").val(),
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
    showdeleteconfirm("确定要删除已选择的帮助?", ids);
}

// 删除所有已选中的帮助分类
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletehelp.htm',
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

// 跳转到添加帮助页面
function toAddHelp() {
    location.href = basePath + 'toaddhelp.htm' + window.location.search;
}
