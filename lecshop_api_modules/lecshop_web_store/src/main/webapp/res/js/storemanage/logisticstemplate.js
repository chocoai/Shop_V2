// 基本路径
var basePath = $("#basePath").val();

// 查询模版信息
queryTemplates();

// 查询模版信信息
function queryTemplates() {
    LSFetch({
        url: basePath + '/store_queryalllogisticstemplates.htm',
        success: function (templates) {
            var htmls = '';
            for (var i = 0; i < templates.length; i++) {
                htmls += '<tr>';
                htmls += '<td>' + templates[i].name + '</td>';
                htmls += '<td>' + templates[i].logisticsCompany.name + '</td>';
                if (templates[i].isDefault == "0") {
                    htmls += '<td><span class="label label-success label-mini">是</span></td>';
                    htmls += '<td width="205" class="operation_box"> <button class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + templates[i].id + ')"><i class="icon-pencil"></i> 编辑</button> <button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + templates[i].id + ')"><i class="icon-trash"></i> 删除</button> </td>';
                } else {
                    htmls += '<td><span class="label label-default label-mini">否</span></td>';
                    htmls += '<td width="205" class="operation_box"> <button class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + templates[i].id + ')"><i class="icon-pencil"></i> 编辑</button> <button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + templates[i].id + ')"><i class="icon-trash"></i> 删除</button> <button type="button" class="btn btn-info btn-xs default_btn" onclick="tosetdefault(' + templates[i].id + ')">设为默认</button></td>';
                }
                htmls += '</tr>';
            }
            $("#templates").html(htmls);
        }
    })
}

// 准备设置默认模版
function tosetdefault(id) {
    $("#defaultId").val(id);
    $('#default_dialog').modal('show');
}

// 设置默认模版
function setdefault() {
    LSFetch({
        url: basePath + '/store_setdefaulttemplate.htm?id=' + $("#defaultId").val(),
        success: function (res) {

            $('#default_dialog').modal('hide');
            if (res == 1) {
                showerror('设置成功');
                queryTemplates();
            } else {
                showerror('设置失败');
            }
        }
    })
}

// 跳转都新增页面
function toadd() {
    window.location.href = basePath + "store_toaddtemplate.htm";
}

// 准备删除物流模版
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除物流模版
function deleteone() {
    LSFetch({
        url: basePath + '/store_deletelogisticstemplatebyid.htm?id=' + $("#deleteId").val(),
        success: function (res) {

            $('#del_dialog').modal('hide');
            if (res == -1) {
                showerror('默认物流模版不能删除!');
            } else if (res == 0) {
                showerror('删除失败');
            } else {
                showerror('删除成功!');
                queryTemplates();
            }
        }
    })
}

// 跳转到修改物流模版页面
function toupdate(id) {
    window.location.href = basePath + "store_toupdatelogisticstemplate.htm?id=" + id;
}