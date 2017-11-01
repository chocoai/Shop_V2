// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + $("#queryurl").val(),
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
            {"name": "subTitle", "data": "subTitle"},
            {"name": "price", "data": "price"},
            {"name": "brandId", "data": "brand.name"},
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

//loadFirstCate();

// 加载一级分类
function loadFirstCate() {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=0',
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#firstCateId").html(html);
        }
    })
}

// 加载二级分类
function chooseFCate(obj) {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + $(obj).val(),
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#secondCateId").html(html);
        }
    })
}
// 加载三级分类
function chooseTCate(obj) {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + $(obj).val(),
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#thirdCateId").html(html);
        }
    })
}

// 跳转到删除商品页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除商品
function deleteSpu() {
    LSFetch({
        url: basePath + '/store_deletespu.htm?spuId=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备删除商品信息
function todeleteSpus() {    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的商品?", ids);
}

// 删除所有商品
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/store_deletespus.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备批量上架
function tobatchup() {
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    $('#up_dialog').modal('show');
}

// 批量上架
function batchup() {
    LSFetch({
        url: basePath + '/store_updateshelvesstatus.htm?ids=' + getSelectIds("id") + "&status=1",
        success: function () {
            $('#up_dialog').modal('hide');
            refreshDataTable();
            showerror('批量上架成功');
        }
    })
}


// 准备批量下架
function tobatchdown() {
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    $('#down_dialog').modal('show');
}

// 批量下架
function batchdown() {
    LSFetch({
        url: basePath + '/store_updateshelvesstatus.htm?ids=' + getSelectIds("id") + "&status=0",
        success: function () {
            $('#down_dialog').modal('hide');
            refreshDataTable();
            showerror('批量下架成功');
        }
    })
}

// 跳转到添加商品页面
function toaddspu() {
    window.location.href = basePath + "store_toaddspu.htm";
}


// 跳转到修改商品页面
function toupdate(id) {
    window.location.href = basePath + "store_toupdatespu.htm?id=" + id;
}
