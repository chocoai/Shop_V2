//新增时候防止重复提交
var servicesupportacount = 0;

// 修改时候防止重复提交
var servicesupportucount = 0;

function clearCount() {
    servicesupportacount = 0;
    servicesupportucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryservicesupport.htm',
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
                "name": "url", "data": function (row) {
                return '<img src="' + row.url + '" height="36">';
            }
            },
            {
                "name": "name", "data": "name"
            },
            {
                "name": "helpName", "data": "helpName"
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

// 弹出新增服务支持，并初始化所属帮助下拉框
function toAddServiceSupport() {
    LSFetch({
        url: basePath + '/queryhelp.htm',
        success: function (res) {
            var param = '<option value="0">---无---</option>';
            for (var i = 0; i < res.length; i++) {
                param += '<option value="' + res[i].id + '">' + res[i].name + '</option>';
            }
            $("#columnsSelect").append(param);
        }
    });
    $('#add_dialog').modal('show');
}

// 新增服务支持
function addServiceSupport() {
    if (servicesupportacount == 1) {
        return;
    }
    if (!$("#addservicesupport").valid()) {
        return;
    }
    var serviceSupport = {
        name: $("#serviceSupportName").val(),
        helpId: $("#columnsSelect").val()

    };
    servicesupportacount = 1;
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#addservicesupport')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        if (res != '') {
            serviceSupport.url = res;
        } else {
            serviceSupport.url = $("img").attr("src");
        }
        LSFetch({
            url: basePath + '/addservicesupport.htm',
            data: JSON.stringify(serviceSupport),
            contentType: "application/json;charset=utf-8",
            success: function () {
                $('#add_dialog').modal('hide');
                refreshDataTable();
            }
        })
        $("#addservicesupport")[0].reset();
    }).fail(function (res) {
    });
}

// 准备删除服务支持
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 删除服务支持
function deleteServiceSupport() {
    LSFetch({
        url: basePath + '/deleteservicesupport.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 批量删除服务支持
function toDeleteAll() {
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除已选择的服务支持?", ids);
}

// 删除所有已选中的服务支持
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeleteservicesupport.htm',
        data: {
            ids: ids
        },
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    });
}

// 弹出修改服务支持页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/queryservicesupportbyid.htm?id=' + id,
        success: function (res) {
            $("#updateServiceSupportId").val(res.id);
            $("#ssName").val(res.name);
            LSFetch({
                url: basePath + '/queryhelp.htm',
                success: function (data) {
                    var param = '';
                    for (var i = 0; i < data.length; i++) {
                        param += '<option value="' + data[i].id + '"';
                        if (res.helpId == data[i].id) {
                            param += ' selected';
                        }
                        param += '>' + data[i].name + '</option>';
                    }
                    $("#colsSelect").append(param);
                }
            });
            $('#update_dialog').modal('show');
        }
    });
}

// 修改服务支持
function updateServiceSupport() {
    if (servicesupportucount == 1) {
        return;
    }
    if (!$("#editservicesupport").valid()) {
        return;
    }
    var serviceSupport = {
        id: $("#updateServiceSupportId").val(),
        name: $("#ssName").val(),
        helpId: $("#colsSelect").val()
    };
    servicesupportucount = 1;
    LSFetch({
        url: basePath + '/updateservicesupport.htm',
        data: JSON.stringify(serviceSupport),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    });
}
