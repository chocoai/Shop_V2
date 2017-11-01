// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryadminwechatinfo.htm',
        success: function (data) {
            var html = "";
            if (data != null) {
                var firstUrl = data.selfmenu_info.button[0].url != null ? data.selfmenu_info.button[0].url : "";
                $("#url").val(firstUrl);
                for (var i = 0; i < data.selfmenu_info.button.length; i++) {
                    var button = data.selfmenu_info.button[i];
                    button.url = button.url != null ? button.url : "";
                    html += ' <dl class="inner_menu" id="' + button.id + '">';
                    html += '<dt class="inner_menu_item" onclick="toLoadUrl(this)"> ' +
                        '<a href="javascript:;" class="inner_menu_link"><strong>' + button.name + '</strong></a> ' +
                        '<span class="menu_opr"> <a href="javascript:;" class="icon14_common jsAddBt" onclick="addSubBtn(this)"></a> ' +
                        '<a href="javascript:;" class="icon14_common jsEditBt" onclick="editBtn(this)"></a> ' +
                        '<a href="javascript:;" class="icon14_common jsDelBt" onclick="oneDeteBtn(this)"></a> </span> ' +
                        '<input type="hidden" value="' + button.url + '"/> </dt>';
                    if (data.selfmenu_info.button[i].sub_button != null) {
                        for (var j = 0; j < button.sub_button.list.length; j++) {
                            var sub = button.sub_button.list[j];
                            sub.url = sub.url != null ? sub.url : "";
                            html += '<dd class="inner_menu_item" onclick="toLoadUrl(this)" id="' + sub.id + '"> <i class="icon_dot">●</i> ' +
                                '<a href="javascript:;" class="inner_menu_link"><strong>' + sub.name + '</strong></a> ' +
                                '<input type="hidden" value="' + sub.url + '"/> ' +
                                '<span class="menu_opr"> <a href="javascript:;" class="icon14_common edit_gray jsSubEditBt" onclick="twoEditBtn(this)"></a> ' +
                                '<a href="javascript:;" class="icon14_common del_gray jsDelBt" onclick="twoDeleteBtn(this)"></a> </span> </dd>'
                        }
                    }
                    html += '</dl>';
                }
            } else {
                html += '<dl class="inner_menu"> <dt class="inner_menu_item"> <a href="javascript:;" class="inner_menu_link"><strong>暂无菜单</strong></a> </dt> </dl>'
            }
            $("#menu").html(html);
        }
    });
};

// 初始化列表
initData();

/**
 * 加载url-2
 */
function toLoadUrl(div) {
    $("#url").val($(div).find('input').val());
}

/**
 * 一级菜单按钮点击事件
 */
function showAddModal() {
    $("#add_dialog input").val("");
    $('#add_dialog').modal('show');
}

/**
 * 一级菜单保存
 */
function addSaveBtn() {
    if ($("#addForm").valid()) {
        var time = new Date().getTime();
        var html = '';
        html += '<dl class="inner_menu" id="' + time + '"><dt class="inner_menu_item" onclick="toLoadUrl(this)"><a href="javascript:;" class="inner_menu_link"><strong>';
        html += $("#addMenuName").val() + '</strong></a><span class="menu_opr"> <a href="javascript:;" class="icon14_common jsAddBt" onclick="addSubBtn(this)"></a>';
        html += ' <a href="javascript:;" class="icon14_common jsEditBt" onclick="editBtn(this)"></a><a href="javascript:;" class="icon14_common jsDelBt" onclick="oneDeteBtn(this)"></a></span> <input type="hidden" value="' + $("#addMenuUrl").val() + '"/></dt> </dl>';
        $(".inner_menu_box").append(html);
        $('#add_dialog').modal('hide');
    }
}

/**
 * 添加二级菜单显示弹出层
 */
function addSubBtn(div) {
    $("#add_sub_dialog input").val("");
    $("#idHidden").val($(div).parent().parent().parent().attr("id"));
    $('#add_sub_dialog').modal('show');
}

/**
 * 二级菜单保存时间
 */
function addSubSaveBtn() {
    var html = '';
    var time = new Date().getTime();
    html += '<dd class="inner_menu_item" onclick="toLoadUrl(this)" id="' + time + '"> <i class="icon_dot">●</i> <a href="javascript:;" class="inner_menu_link"><strong>' + $("#addSubMenuName").val() + '</strong></a> <input type="hidden" value="' + $("#addSubMenuUrl").val() + '"/> ' +
        '<span class="menu_opr"> <a href="javascript:;" class="icon14_common edit_gray jsSubEditBt" onclick="twoEditBtn(this)"></a> <a href="javascript:;" class="icon14_common del_gray jsDelBt" onclick="twoDeleteBtn(this)"></a> </span> </dd>';
    $('#' + $("#idHidden").val()).append(html);
    $('#add_sub_dialog').modal('hide');
}

/**
 * 二级菜单删除按钮点击事件
 */
function twoDeleteBtn(div) {
    $("#deleteHidden").val($(div).parent().parent().attr("id"));
    $('#remove_dialog').modal('show');
}

/**
 * 二级菜单删除保存事件
 */
function twoDeleteSaveBtn() {
    $('#' + $("#deleteHidden").val()).remove();
    $('#remove_dialog').modal('hide');
}

/**
 * 一级菜单删除按钮点击事件
 */
function oneDeteBtn(div) {
    $("#oneDeleteHidden").val($(div).parent().parent().parent().attr("id"));
    $('#remove_dialog_one').modal('show');
}

/**
 * 一级菜单删除保存事件
 */
function oneDeleteSaveBtn() {
    $('#' + $("#oneDeleteHidden").val()).remove();
    $('#remove_dialog_one').modal('hide');
}

/**
 * 一级菜单编辑按钮点击事件
 */
function editBtn(div) {
    $("#edit_dialog input").val("");
    var name = $(div).parent().parent().find("strong").text();
    if ($("#edit_dialog").find(".form-group").length == 2) {
        $("#edit_dialog").find(".form-group")[1].remove();
    }
    if ($(div).parent().parent().parent().find($(".inner_menu_item")).length == 1) {
        var html = '<div class="form-group"> <label class="col-sm-3 control-label">页面地址：</label> <div class="col-sm-5"> <input type="text" class="form-control required url" minlength="4" id="editMenuUrl"> </div> </div>'
        $("#editForm").append(html);
        var url = $(div).parent().parent().find("input").val();
        $("#editMenuUrl").val(url);
    }
    $("#editHidden").val($(div).parent().parent().parent().attr("id"));
    $("#editMenuName").val(name);
    $('#edit_dialog').modal('show');
}

/**
 * 一级菜单编辑保存事件
 */
function editSaveBtn() {
    $('#' + $("#editHidden").val()).find("dt").find("strong").text($("#editMenuName").val());
    $('#' + $("#editHidden").val()).find("dt").find("input").val($("#editMenuUrl").val());
    $('#edit_dialog').modal('hide');
}

/**
 * 二级菜单编辑按钮点击事件
 */
function twoEditBtn(div) {
    $("#edit_sub_dialog input").val("");
    var name = $(div).parent().parent().find("strong").text();
    var url = $(div).parent().parent().find("input").val();
    $("#editSubMenuName").val(name);
    $("#editSubMenuUrl").val(url);
    $("#twoEditHidden").val($(div).parent().parent().attr("id"));
    $('#edit_sub_dialog').modal('show');
}

/**
 * 二级菜单编辑保存事件
 */
function editSubSaveBtn() {
    $('#' + $("#twoEditHidden").val()).find("strong").text($("#editSubMenuName").val());
    $('#' + $("#twoEditHidden").val()).find("input").val($("#editSubMenuUrl").val());
    $('#edit_sub_dialog').modal('hide');
}
/**
 * 获取菜单json
 */
function getDomToJson() {
    var isError = false;
    if ($(".inner_menu_box").find("dl").length <= 0) {
        showerror("请先创建菜单");
        return;
    }
    $(".inner_menu").each(function () {
        if ($(this).find("dd").length <= 0) {
            if ($(this).find("input").val() == "") {
                isError = true;
                return false;
            }
        }
    });
    if (isError) {
        showerror("请输入一级菜单的URL");
        return;
    }
    var menuInfo = '{"button":[';
    var type = 'view';
    var sum = $(".inner_menu").length;
    var subSumParam = 0;
    $(".inner_menu").each(function (i, element1) {
        if ($(element1).find("dd").length <= 0) {
            menuInfo = menuInfo + '{"type":"' + type + '",' + '"name":"' + $(element1).find("strong").text() + '",' + '"url":"' + $(element1).find("input").val() + '"}';
            if (i < sum - 1) {
                menuInfo = menuInfo + ',';
            }
        } else {
            var subSum = $(this).find(".inner_menu_item").length;
            subSumParam = subSum;
            $(this).find(".inner_menu_item").each(function (j, element) {
                if (j == 0) {
                    menuInfo = menuInfo + '{"name":"' + $(element).find("strong").text() + '",' + '"sub_button":[';
                } else {
                    menuInfo = menuInfo + '{"type":"' + type + '",' + '"name":"' + $(element).find("strong").text() + '",' + '"url":"' + $(element).find("input").val() + '"}';
                    if (j < subSum - 1) {
                        menuInfo = menuInfo + ',';
                    }
                }
                if (j == subSum - 1) {
                    menuInfo = menuInfo + ']}';
                }
            });
            if (i < sum - 1) {
                menuInfo = menuInfo + ',';
            }
        }
    });
    if (sum == 1 && subSumParam == 0) {
        menuInfo = menuInfo + '';
    } else {
        menuInfo = menuInfo + ']}';
    }
    LSFetch({
        url: basePath + '/editwechatmenu.htm',
        type: "POST",
        data: {
            menuInfo: menuInfo
        },
        success: function (data) {
            if (data == -1) {
                showerror("编辑失败");
                return;
            }
            if (data == 1) {
                initData();
                showerror("编辑菜单成功");
            } else {
                showerror("编辑失败");
            }
        }
    });
}