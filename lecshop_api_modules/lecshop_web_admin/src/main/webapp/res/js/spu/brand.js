// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var brandacount = 0;

// 修改时候防止重复提交
var branducount = 0;

function clearCount() {
    brandacount = 0;
    branducount = 0;
}


// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querybrands.htm',
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
            {
                "name": "logo", "data": function (row) {
                return '<img src="' + row.url + '" height="36">';
            }
            },
            {"name": "name", "data": "name"},
            {"name": "nickname", "data": "nickName"},
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


// 跳转到添加品牌页面
function toaddbrand() {
    clearCount();
    $("#add_dialog").html(addhtml);
    $('#add_dialog').modal('show');
}


// 保存品牌
function saveBrand() {

    if (brandacount == 1) {
        return;
    }
    if (!$("#brandoperator").valid()) {
        return;
    }

    var brand = {
        name: $("#brandname").val(),
        nickName: $("#brandNickName").val()
    };


    brandacount = 1;
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#brandoperator')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {

        if (res != '') {
            brand.url = res;
        } else {
            brand.url = $("#myimg").attr("src");
        }

        LSFetch({
            url: basePath + '/addbrand.htm',
            data: JSON.stringify(brand),
            contentType: "application/json;charset=utf-8",
            success: function () {
                $('#add_dialog').modal('hide');
                refreshDataTable();
            }
        })
    }).fail(function (res) {
    });

}


// 跳转到删除品牌页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除品牌
function deleteBrand() {
    LSFetch({
        url: basePath + '/deletebrand.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 弹出修改品牌页面
function toupdate(id) {
    clearCount();

    LSFetch({
        url: basePath + '/querybrand.htm?id=' + id,
        success: function (res) {
            $("#updateBrandId").val(res.id);
            $("#updateBrandName").val(res.name);
            $("#updateBrandNickName").val(res.nickName);
            if (res.url != '' && res.url != null) {
                $("#myimg").attr("src", res.url);
            }
            $('#update_dialog').modal('show');
        }
    })
}


// 修改品牌信息
function updateBrand() {

    if (branducount == 1) {
        return;
    }

    if (!$("#updateBrand").valid()) {
        return;
    }
    var brand = {
        id: $("#updateBrandId").val(),
        name: $("#updateBrandName").val(),
        nickName: $("#updateBrandNickName").val()
    };

    branducount = 1;
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#updateBrand')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        if (res != '') {
            brand.url = res;
        } else {
            brand.url = $("#myimg").attr("src");
        }

        LSFetch({
            url: basePath + '/updatebrand.htm',
            data: JSON.stringify(brand),
            contentType: "application/json;charset=utf-8",
            success: function () {
                $('#update_dialog').modal('hide');
                refreshDataTable();
            }
        })
    }).fail(function (res) {
    });
}

// 准备删除全部品牌
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的品牌?", ids);
}

// 删除所有品牌
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/batchdeletebrands.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}
