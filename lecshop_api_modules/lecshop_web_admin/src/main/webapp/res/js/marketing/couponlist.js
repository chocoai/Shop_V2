// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querycoupon.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                    copyUrl();
                }
            });
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {"name": "name", "data": "name"},
            {
                "name": "type", "data": function (row) {
                if (row.type == 1) {
                    return "满减";
                }
                if (row.type == 2) {
                    return "直降";
                }
            }
            },
            {"name": "limitNum", "data": "limitNum"},
            {"name": "startTime", "data": "startTime"},
            {"name": "endTime", "data": "endTime"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box copy_style"><button class="btn btn-info btn-xs copy_btn copy" type="button"  attr-id="' + row.id + '" target="_blank"><i class="icon-link"></i> 复制链接</button>';
                    operator += '<button class="btn btn-success btn-xs" type="button" onclick="toSeeDetails(' + row.id + ')"><i class="icon-eye-open"></i> 查看</button>';
                    operator += '<button class="btn btn-primary btn-xs" type="button" onclick="exportCoupon(' + row.id + ')"><i class="icon-external-link"></i> 导出券码</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteCoupon(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
                    return operator;
                }
            }
        ],
        ordering: false
    });
};

// 初始化列表
initDataTable();

//复制优惠券链接
function copyUrl() {
    var flag = "";
    var returnFlag = "";
    $(".copy").zclip({
        path: 'res/js/ZeroClipboard.swf',
        copy: function () {
            $.ajax({
                url: basePath + '/copycoupon.htm',
                data: {
                    couponId: $(this).attr("attr-id")
                },
                async: false,
                success: function (copyData) {
                    flag = copyData;
                    returnFlag = (copyData == "0" || copyData == -1) ? "" : copyData;
                }
            });
            return returnFlag;
        },
        afterCopy: function () {
            if (flag == "0") {
                showerror("优惠券已过期");
                return;
            }
            if (flag == "-1") {
                showerror("优惠券不存在");
            } else {
                $('#copy_dialog').modal('show');
            }
        }
    });
}
// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

//单个删除按钮点击事件
function deleteCoupon(id) {
    $("#delId").val(id);
    $('#del_dialog').modal('show');
}

//单个删除按钮保存事件
function deleteSaveBtn() {
    $('#del_dialog').modal('hide');
    LSFetch({
        url: basePath + '/deletecoupon.htm',
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
        url: basePath + '/deletecoupon.htm',
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

//导出券码
function exportCoupon(id) {
    window.open('exportcoupon.htm?couponId=' + id);
}

//查看优惠券详情
function toSeeDetails(id) {
    location.href = basePath + "tocoupondetails.htm" + window.location.search + "&cid=" + id;
}