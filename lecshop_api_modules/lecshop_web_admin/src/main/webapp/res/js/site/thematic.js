// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var linkacount = 0;

// 修改时候防止重复提交
var linkucount = 0;

function clearCount() {
    linkacount = 0;
    linkucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querythematic.htm',
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
                "name": "title", "data": "title"
            },

            {
                "name": "hideHeadTail", "data": function (row) {
                    if (row.hideHeadTail == '0') {
                        return '<span class="label label-success" style="cursor: default;">是</span>';
                    } else {
                        return '<span class="label label-success" style="cursor: default;">否</span>';
                    }
                }
            },

            {
                "name": "isUse", "data": function (row) {
                    if (row.isUse == '0') {
                        return '<span class="label label-success" style="cursor: default;">是</span>';
                    } else {
                        return '<span class="label label-success" style="cursor: default;">否</span>';
                    }
                }
            },
            {
                "name": "createTime", "data": "createTime"
            },
            {
                "name": "modifyTime", "data": "modifyTime"
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 编辑</button>';
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

// 跳转到添加专题页面
function toAddThematicSetting() {
    window.location.href = basePath + 'toaddthematic.htm' + window.location.search;
}

// 准备删除专题
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 删除专题
function deleteThematic() {
    LSFetch({
        url: basePath + '/deletethematic.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备删除已选中的专题
function toDeleteAll() {
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除已选择的专题?", ids);
}

// 删除所有已选中的专题
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletethematic.htm',
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

// 跳转到修改专题页面
function toupdate(id) {
    window.location.href = basePath + 'toeditthematic.htm' + window.location.search + "&id=" + id;
}
