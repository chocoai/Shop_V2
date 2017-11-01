// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initData = function () {
    var html = "";
    LSFetch({
        url: basePath + '/querycolumnlist.htm',
        success: function (res) {
            for (var i = 0; i < res.length; i++) {
                html += '<tr class="even';
                if (res[i].parentId != 0) {
                    html += ' collapsed"';
                } else {
                    html += '"';
                }
                html += '><input type="hidden" value="' + res[i].parentId + '" name="parentId">';
                html += '<td>&nbsp;&nbsp;' + res[i].name + '</td><td>' + res[i].sort + '</td>';
                if (res[i].isShow == 0) {
                    html += '<td align="center"><span class="label label-success">是</span></td>';
                } else {
                    html += '<td align="center"><span class="label label-default">否</span></td>';
                }
                html += '<td width="140" class="operation_box"> ' +
                    '<button class="btn btn-primary btn-xs" onclick="editBtn(' + res[i].id + ',' + "'" + res[i].name + "'" + ',' + res[i].parentId + ',' + res[i].sort + ',' + res[i].isShow + ')"><i class="icon-pencil"></i> 编辑</button> ' +
                    '<button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(' + res[i].id + ',' + res[i].parentId + ')"><i class="icon-trash"></i> 删除</button> </td> </tr>';
            }
            $("#treetable").html(html);
            var maps = new Array();
            var num = "";
            $("#treetable input[name='parentId']").each(function (i) {
                if ($(this).val() == "0") {
                    maps.push($(this).val());
                    num = i + 1;
                } else {
                    maps.push(num + "");
                }
            });
            $("#treetable").jqTreeTable(maps, {
                openImg: basePath + "res/img/TreeTable/tv-collapsable.gif",
                shutImg: basePath + "res/img/TreeTable/tv-expandable.gif",
                leafImg: basePath + "res/img/TreeTable/tv-item.gif",
                lastOpenImg: basePath + "res/img/TreeTable/tv-collapsable.gif",
                lastShutImg: basePath + "res/img/TreeTable/tv-expandable.gif",
                lastLeafImg: basePath + "res/img/TreeTable/tv-item-last.gif",
                vertLineImg: basePath + "res/img/TreeTable/vertline.gif",
                blankImg: basePath + "res/img/TreeTable/blank.gif",
                collapse: false,
                column: 0,
                striped: true,
                highlight: true,
                state: false
            });
        }
    });
};

// 初始化列表
initData();

//添加按钮点击事件
function addColumnBtn() {
    LSFetch({
        url: basePath + '/queryparentcolumnlist.htm',
        success: function (data) {
            var param = '<option value="0">---无---</option>';
            for (var i = 0; i < data.length; i++) {
                param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $("#columnsSelect").html(param);
        }
    });
    $('#add_dialog').html(addhtml);
    $('#add_dialog').modal('show');
}

//添加保存事件
function addSaveBtn() {
    if (!$("#checkForm").valid()) {
        return;
    }
    var columnList = {
        id: '',
        name: $("#add_dialog input[name='name']").val(),
        parentId: $("#columnsSelect").val(),
        sort: $("#add_dialog input[name='sort']").val(),
        isShow: $("#add_dialog select[name='isShow']").val(),
        delFlag: ''
    };
    LSFetch({
        url: basePath + '/addcolumn.htm',
        data: JSON.stringify(columnList),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                $('#add_dialog').modal('hide');
                initData();
                showerror("添加成功");
                return;
            } else {
                showerror("添加失败");
                return;
            }
        }
    });
}

//编辑按钮点击事件
function editBtn(id, name, parentId, sort, isShow) {
    if (parentId == 0) {
        $("#existParent").hide();
        var param = '<option value="0" style="ce">---无---</option>';
        $("#columnsSelectEdit").html(param);
        $("#columnsSelectEdit").val(0);
    } else {
        $("#existParent").show();
        LSFetch({
            url: basePath + '/queryparentcolumnlist.htm',
            success: function (data) {
                var param = '<option value="0" style="ce">---无---</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == parentId) {
                        param += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    } else {
                        param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }
                $("#columnsSelectEdit").html(param);
            }
        });
    }
    $("#id").val(id);
    $("input[name='name']").val(name);
    $("input[name='sort']").val(sort);
    $("select[name='isShow']").val(isShow);
    $('#edit_dialog').modal('show');
}

//编辑保存事件
function editSaveBtn() {
    $('#edit_dialog').modal('hide');
    if (!$("#editForm").valid()) {
        return;
    }
    var columnList = {
        id: $("#id").val(),
        name: $("#edit_dialog input[name='name']").val(),
        parentId: $("#columnsSelectEdit").val(),
        sort: $("#edit_dialog input[name='sort']").val(),
        isShow: $("#edit_dialog select[name='isShow']").val(),
        delFlag: ''
    };
    LSFetch({
        url: basePath + '/editcolumn.htm',
        data: JSON.stringify(columnList),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                showerror("编辑成功");
                initData();
            } else {
                showerror("编辑失败");
            }
        }
    });
}

//删除按钮点击事件
function deleteBtn(id, parentId) {
    $("#deleteId").val(id);
    $("#deleteParentId").val(parentId);
    $('#del_dialog').modal('show');
}

//删除按钮保存事件
function deleteSaveBtn() {
    $('#del_dialog').modal('hide');
    var columnList = {
        id: $("#deleteId").val(),
        name: '',
        parentId: $("#deleteParentId").val(),
        sort: '',
        isShow: '',
        delFlag: ''
    };
    LSFetch({
        url: basePath + '/deletecolumn.htm',
        data: JSON.stringify(columnList),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data >= 1) {
                showerror("删除成功");
                initData();
                return;
            } else {
                showerror("删除失败");
                return;
            }
        }
    });
}