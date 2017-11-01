// 基本路径
var basePath = $("#basePath").val();

var addimporthtml = $("#import_dialog").html();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryspuimports.htm',
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
            {"name": "price", "data": "price"},
            {
                "name": "release", "data": function (row) {
                if (row.isRelease == '0') {
                    return '<span class="label label-default label-mini">未发布</span>';
                } else {
                    return '<span class="label label-success label-mini">已发布</span>';
                }
            }
            },
            {
                "data": function (row) {
                    var operator='<div class="operation_box">';

                    if (row.isRelease == '0') {
                        operator += ' <button type="button" onclick="toimport(' + row.id + ')" class="btn btn-info btn-xs"><i class="icon-th-large"></i> 发布</button>';
                    }
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


// 跳转到删除商品导入页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除商品导入
function deleteSpuImport() {
    LSFetch({
        url: basePath + '/deletespuimport.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}


// 准备删除全部商品导入
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的商品导入?", ids);
}

// 删除所有商品导入
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/deletespuimports.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}

// 弹出商品导入页面
function toimportspu() {
    $("#import_dialog").html(addimporthtml);
    $('#import_dialog').modal('show');
}

// 准备发布商品
function toimport(id) {
    window.location.href = basePath+"toaddspu.htm?importId=" + id;
}

// 导入商品
function importspu() {

    if (!$("#importForm").valid()) {
        return;
    }

    $.ajax({
        url: 'importspu.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#importForm')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $('#import_dialog').modal('hide');
        if (res == 0) {
            showerror('商品导入失败');
        } else if (res == -2) {
            showerror('请上传正确的xls文件');

        } else {
            showerror('商品导入成功');
            refreshDataTable();
        }

    }).fail(function (res) {
    });
}