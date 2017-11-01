// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#brandList").attr("href", basePath + '/store_tobrandlist.htm' + window.location.search)
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_querycustombrand.htm',
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
                "name": "name", "data": "name"
            }, {
                "data": function (row) {
                    return '<img src="' + row.url + '" height="40" alt=""/>';
                }
            }, {
                "data": function (row) {
                    return '<img src="' + row.certificatUrl + '" height="40" alt=""/>';
                }
            }, {
                "data": function (row) {
                    if (row.status == 0) {
                        return '<span class="label label-info">待审核</span>';
                    }
                    if (row.status == 1) {
                        return '<span class="label label-success">通过</span>';
                    }
                    if (row.status == 2) {
                        return '<span class="label label-default">拒绝</span>';
                    }
                }
            },
            {
                "data": function (row) {
                    if (row.status == 2) {
                        return row.reason;
                    } else {
                        return '无';
                    }
                }
            },
            {
                "data": function (row) {
                    return '<div class="operation_box"><button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

function applyCustomBrand() {
    $('#add_dialog').html(addhtml);
    $('#add_dialog').modal('show');
}

// 上传商品图片
function uploadPic(obj) {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $('#' + obj).validate().resetForm();
        $("#" + obj + " img[name='" + obj + "']").attr("src", res);
    }).fail(function (res) {
    });
}

function applySaveBtn() {
    if (!$("#name").valid() | !$("#url").valid() || !$("#certificatUrl")) {
        return;
    }
    var brand = {
        name: $("#name input[name='name']").val(),
        url: $("#url  img[name='url']").attr("src"),
        certificatUrl: $("#certificatUrl img[name='certificatUrl']").attr("src")
    };
    LSFetch({
        url: basePath + '/store_addcustombrand.htm',
        data: JSON.stringify(brand),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 0) {
                showerror("系统错误");
                return;
            }
            if (data == 1) {
                $('#add_dialog').modal('hide');
                showerror("申请成功");
                refreshDataTable();
                return;
            }
            showerror("网络错误");
        }
    });
}

function deleteBtn(id) {
    $("input[name='deleteId']").val(id);
    $('#del_dialog').modal('show');
}


function deleteSaveBtn() {
    LSFetch({
        url: basePath + '/store_deletecustombrand.htm',
        data: {
            ids: $("input[name='deleteId']").val()
        },
        success: function (data) {
            if (data == 1) {
                $('#del_dialog').modal('hide');
                showerror("删除成功");
                refreshDataTable();
                return;
            }
            showerror("删除失败");
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
    showdeleteconfirm("确定要删除选择的品牌?", ids);
}

// 批量删除
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/store_deletecustombrand.htm',
        data: {
            ids: ids
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