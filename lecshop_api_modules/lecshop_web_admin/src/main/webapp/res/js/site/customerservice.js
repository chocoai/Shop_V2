// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querycustomerservice.htm',
        success: function (data) {
            $("select[name='isUse']").val(data.isUse);
            $("input[name='title']").val(data.title);
            $("input[name='id']").val(data.id);
            var html = '';
            if (data.customerServiceInfo == null) {
                html = '<tr><td colspan="3" style="text-align: center;">暂无数据</td></tr>';
            } else {
                for (var i = 0; i < data.customerServiceInfo.length; i++) {
                    html = html + '<tr><input type="hidden" value="' + data.customerServiceInfo[i].id + '" name="infoId" id="infoId' + data.customerServiceInfo[i].id + '"> <td>' + data.customerServiceInfo[i].qq + '</td><td>' + data.customerServiceInfo[i].name + '</td><td class="operation_box">' +
                        '<button class="btn btn-primary btn-xs edit_btn" onclick="editBtn(this)"><i class="icon-pencil"></i> 修改</button>' +
                        '<button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button>' +
                        '</td></tr>';
                }
            }
            $("#tbodyContainer").html(html);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

function editCustomerServiceBtn() {
    var params = new Array();
    if ($("tbody tr").length != 1 || $("tbody tr").children().text() != "暂无数据") {
        $("tbody tr").each(function () {
            var param = {
                id: $(this).children("input").val(),
                qq: $(this).children("input").next().text(),
                name: $(this).children("input").next().next().text()
            };
            params.push(param);
        });
    }
    var customerService = {
        id: $("input[name='id']").val(),
        isUse: $("select[name='isUse']").val(),
        title: $("input[name='title']").val(),
        customerServiceInfo: params
    };
    LSFetch({
        url: basePath + '/editcustomerserviceinfo.htm',
        data: JSON.stringify(customerService),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1) {
                showerror("编辑失败");
                return;
            }
            if (data == 1) {
                showerror("编辑成功");
                refreshData();
                return;
            }
            showerror("未知异常");
            return;
        }
    });
}

//编辑按钮点击事件
function editBtn(obj) {
    $("#edit_dialog #idHidden").val($(obj).parent().parent().children("input").val());
    $("#edit_dialog input[name='qq']").val($(obj).parent().parent().children("input").next().text());
    $("#edit_dialog input[name='name']").val($(obj).parent().parent().children("input").next().next().text());
    $('#edit_dialog').modal('show');
}
//编辑按钮点击保存事件
function editSaveBtn() {
    $("#infoId" + $("#edit_dialog #idHidden").val()).next().text($("#edit_dialog input[name='qq']").val());
    $("#infoId" + $("#edit_dialog #idHidden").val()).next().next().text($("#edit_dialog input[name='name']").val());
    $('#edit_dialog').modal('hide');
}

//删除按钮点击事件
function deleteBtn(obj) {
    $("#delId").val($(obj).parent().parent().children("input").val());
    $('#del_dialog').modal('show');
}

function deleteSaveBtn() {
    $("#infoId" + $("#delId").val()).parent().remove();
    $('#del_dialog').modal('hide');
    if ($("tbody tr").length == 0) {
        $("#tbodyContainer").html('<tr><td colspan="3" style="text-align: center;">暂无数据</td></tr>');
    }
}

//添加按钮点击事件
function addCustomerServiceBtn() {
    if ($("tbody tr").length >= 20) {
        showerror("已达最大限度");
        return;
    }
    $("#add_dialog").html(addhtml);
    $('#add_dialog').modal('show');
}

//添加保存按钮点击事件
function addSaveBtn() {
    var qq = $("#add_dialog input[name='qq']").val();
    var name = $("#add_dialog input[name='name']").val();
    var html = '<tr><input type="hidden" value="' + new Date().getTime() + '" name="infoId" id="infoId' + new Date().getTime() + '"> <td>' + qq + '</td><td>' + name + '</td><td class="operation_box">' +
        '<button class="btn btn-primary btn-xs edit_btn" onclick="editBtn(this)"><i class="icon-pencil"></i> 修改</button>' +
        '<button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button>' +
        '</td></tr>';
    if ($("tbody tr").length == 1 && $("tbody tr").children().text() == "暂无数据") {
        $("#tbodyContainer").html("");
    }
    $("#tbodyContainer").append(html);
    $('#add_dialog').modal('hide');
}