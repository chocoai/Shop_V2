// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();

// 新增防止重复提交
var specacount = 0;

// 修改防止重复提交
var specucpunt = 0;


var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryspecs.htm',
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

// 弹出添加规格页面
function toaddspec() {

    clearCount();
    $("#add_dialog").html(addhtml);
    $(".type_step").eq(0).show();
    $(".type_step").eq(1).hide();
    $('#add_dialog').modal('show');
}

// 跳转到下一步
function nextform(obj) {
    if (!$("#addspec").valid()) {
        return;
    }
    $(obj).parents('.type_step').hide().next().show();
}


// 保存规格信息
function savespec() {


    if (specacount == 1) {
        return;
    }
    if (!$("#addspec").valid()) {
        return;
    }

    var specvalues = $(".specvalue");

    if (specvalues.length == 0) {
        showerror('至少添加一个规格值!');
        return;
    }


    specacount = 1;
    LSFetch({
        url: basePath + '/addspec.htm',
        data: JSON.stringify(getAddSpec()),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#add_dialog').modal('hide');
            refreshDataTable();
            j = j + 1;
        }
    })

}

// 获得新增的规格对象
function getAddSpec() {
    var spc = {};
    spc.name = $("#name").val();
    spc.nickName = $("#nickName").val();
    spc.specValues = new Array();


    var specvalues = $(".specvalue");

    for (var i = 0; i < specvalues.length; i++) {
        spc.specValues.push(getAddSpecValue(specvalues[i]));
    }
    return spc;
}

// 获得新增的规格值对象
function getAddSpecValue(obj) {
    var specvalue = {};
    specvalue.name = $(obj).val();
    specvalue.sort = $(obj).parent().next().find('input').val();
    return specvalue;
}

// 返回上一步
function goback(obj) {
    $(obj).parents('.type_step').hide().prev().show();
}

// 绑定删除按钮
$('body').on('click', '.del_tab_btn', function () {

    var obj = $(this);

    LSFetch({
        url: basePath + '/isspecvaluecandelete.htm?specValueId=' + $(this).parents('tr').find('.idclass').val(),
        success: function (res) {
            if (res) {
                obj.parents('tr').remove();
            } else {
                showerror('规格值已经被商品使用不能删除!');
            }
        }
    })
});

var i = 0;

// 添加规格值
function addSpecValues(obj, className) {
    i++;
    $(obj).parent().next().append('<tr><td class="form-group"><input id="specvalue' + i + '"  type="text" class="' + className + ' form-control specstr required" maxlength="10"></td><td class="form-group"><input id="specvaluesort' + i + '"   type="text" class="form-control required digits" maxlength="4"></td><td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_tab_btn"><i class="icon-trash"></i> 删除</button></td></tr>')
}

// 准备删除规格
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

//删除规格
function deletespec() {
    LSFetch({
        url: basePath + '/deletespec.htm?id=' + $("#deleteId").val(),
        success: function (res) {
            $('#del_dialog').modal('hide');
            if (res == -1) {
                showerror('规格已经被商品使用不能删除!');
            } else {
                refreshDataTable();
            }
        }
    })
}

// 准备批量删除规格
function todeletespcs() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的规格?", ids);
}

// 删除所有规格
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/deletespecs.htm?ids=' + ids,
        success: function (res) {
            $('#confirm').modal('hide');
            if (res == -1) {
                showerror('规格已经被商品使用不能删除!');
            } else {
                refreshDataTable();
            }
        }
    })
}


// 准备修改
function toupdate(id) {

    clearCount();
    $(".type_step").eq(2).show();
    $(".type_step").eq(3).hide();
    LSFetch({
        url: basePath + '/queryspecbyid.htm?id=' + id,
        success: function (res) {
            $("#specId").val(res.id);
            $("#uname").val(res.name);
            $("#unickname").val(res.nickName);


            var html = "";
            for (var i = 0; i < res.specValues.length; i++) {
                html +=
                    '<tr id="spec_detail_' + res.specValues[i].id + '">' +
                    '<input type="hidden"  class="idclass" value="' + res.specValues[i].id + '"/>' +
                    '<td class="form-group "><input id="specvalue' + res.specValues[i].id + '" type="text" class="form-control updatespecvalue required specstr"  value="' + res.specValues[i].name + '"></td>' +
                    '<td class="form-group "><input id="specvaluesort' + res.specValues[i].id + '" type="text" class="form-control required digits" name="specDetailSort" value="' + res.specValues[i].sort + '"></td>' +
                    '<td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_tab_btn"><i class="icon-trash"></i> 删除</button></td>' +
                    '</tr>';
            }
            $("#updatespecvalues").html(html);

            $('#update_dialog').modal('show');
        }
    })
}


// 更新的下一步
function nextformupdate(obj) {
    if (!$("#updatespec").valid()) {
        return;
    }
    $(obj).parents('.type_step').hide().next().show();
}


// 修改规格
function updatespec() {

    if (specucpunt == 1) {
        return;
    }

    if (!$("#updatespec").valid()) {
        return;
    }

    var updatespecvalue = $(".updatespecvalue");

    if (updatespecvalue.length == 0) {
        showerror('至少添加一个规格值!');
        return;
    }

    specucpunt = 1;

    LSFetch({
        url: basePath + '/updatespec.htm',
        data: JSON.stringify(getUpdateSpec()),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })

}

// 获得修改的规格
function getUpdateSpec() {
    var spec = {};
    spec.id = $("#specId").val();
    spec.name = $("#uname").val();
    spec.nickName = $("#unickname").val();

    spec.specValues = new Array();

    for (var i = 0; i < $('#updatespecvalues tr').length; i++) {
        spec.specValues.push(getUpdateSpecValue($('#updatespecvalues tr')[i]));
    }


    return spec;
}

// 获得修改的规格值
function getUpdateSpecValue(obj) {
    var specvalue = {};
    specvalue.specId = $("#specId").val();
    specvalue.name = $(obj).find('.updatespecvalue').val();
    specvalue.delFlag = "0";
    specvalue.sort = $(obj).find('.digits').val();
    if ($(obj).find('.idclass').length > 0) {
        specvalue.id = $(obj).find('.idclass').val();
    }

    return specvalue;
}

function clearCount() {
    specacount = 0;
    specucpunt = 0;
}
