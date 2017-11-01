// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/articlelist.htm',
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
            {"name": "title", "data": "title"},
            {"name": "columnName", "data": "columnName"},
            {"name": "author", "data": "author"},
            {"name": "sort", "data": "sort"},
            {
                "data": function (row) {
                    if (row.isRelease == 0) {
                        return '<td align="center"><span class="label label-success">是</span></td>';
                    }
                    if (row.isRelease == 1) {
                        return '<td align="center"><span class="label label-default">否</span></td>';
                    }
                }
            },
            {
                "name": "modifyTime", "data": function (row) {
                if (row.modifyTime == '' || row.modifyTime == null) {
                    return '暂无修改信息';
                } else {
                    return row.modifyTime;
                }
            }
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-primary btn-xs edit_btn" onclick="editArticle(' + row.id + ')"><i class="icon-pencil"></i> 编辑</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteArticle(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

//单个删除按钮点击事件
function deleteArticle(id) {
    $("#delId").val(id);
    $('#del_dialog').modal('show');
}

//单个删除按钮保存事件
function deleteSaveBtn() {
    $('#del_dialog').modal('hide');
    LSFetch({
        url: basePath + '/deletearticle.htm',
        data: {
            ids: $("#delId").val()
        },
        success: function (data) {
            if (data == -1) {
                showerror("请至少选择一条记录");
                return;
            }
            if (data >= 1) {
                showerror("删除成功");
                refreshDataTable();
            }
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
    showdeleteconfirm("确定要删除选择的文章?", ids);
}

// 批量删除
function deleteAll(ids) {
    $('#confirm').modal('hide');
    LSFetch({
        url: basePath + '/deletearticle.htm',
        data: {
            ids: ids
        },
        success: function (data) {
            if (data == -1) {
                showerror("请至少选择一条记录");
            }
            if (data >= 1) {
                showerror("删除成功");
                refreshDataTable();
            }
        }
    });
}

// 跳转到添加文章页面
function toAddArticle() {
    location.href = basePath + 'toaddarticle.htm' + window.location.search;
}

function editArticle(id) {
    location.href = basePath + 'toeditarticle.htm' + window.location.search + "&aid=" + id;
}