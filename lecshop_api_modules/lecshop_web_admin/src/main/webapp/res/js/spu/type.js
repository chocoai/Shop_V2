var addHtml = $("#type_dialog").html();

// 防止新增重复提交
var acount = 0;

//防止修改重复提交
var bcount = 0;

function clearCount() {
    acount = 0;
    bcount = 0;
}


$('.search-select').chosen();
$(".select2").select2();
$('.type_prev').click(function() {
    $(this).parents('.type_step').hide().prev().show();
});
$('body').on('click','.del_tab_btn',function() {
    $(this).parents('tr').remove();
});
function addaAttribute (obj) {
    $(obj).parent().next().append('<tr><td class="form-group"><input type="text" class="form-control attributeName"></td><td class="form-group"><ul class="as-selections addselect"><li class="as-original"><input type="text" class="as-input" placeholder="输入属性值并回车添加..." autocomplete="off"></li></ul></td><td class="form-group"><input type="text"  class="form-control attributeSort"></td><td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_tab_btn"><i class="icon-trash"></i> 删除</button></td></tr>')
};
$('body').on('keydown', '.as-input', function (event) {
    if (event.keyCode == 13 && $(this).val() != '') {
        var type_value = $(this).val();
        $(this).parent().before('<li class="as-selection-item"><a class="as-close">×</a><span></span></li>').prev().find('span').text(type_value);
        $(this).val('').focus();
    }
});

$('body').on('click', ".as-close", function () {
    var parents = $(this).parents('ul');
    $(this).parent().remove();
    $(parents).find('.myvalue').val('');
    var type_li = $(parents).find('.as-selection-item');
    $(type_li).each(function () {
        var type_value = $(this).find('span').text();
        var alerdyvale = $(this).parents('ul').find('.myvalue').val();
        if (alerdyvale == "") {
            $(this).parents('ul').find('.myvalue').val(type_value);
        } else {
            $(this).parents('ul').find('.myvalue').val(alerdyvale + '|' + type_value);
        }
    });
});

$('body').on('click', ".update-as-close", function () {
    $(this).parent().addClass('hide');
    $(this).siblings('input').eq(1).val('1');
});

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querytypes.htm',
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


// 跳转到添加类型第一步
function toaddtype() {

    clearCount();

    $("#type_dialog").html(addHtml);

    clearerror();

    LSFetch({
        url: basePath + '/queryallspecs.htm',
        success: function (data) {
            $("#addspecs").html("");
            for (var i = 0; i < data.length; i++) {
                $("#addspecs").append("<option value=" + data[i].id + ">" + data[i].nickName + "</option>");
            }
            $('select[data-live-search="true"]').select2();
        }
    })

    $("#addType1").show();
    $("#addType2").hide();
    $("#addType3").hide();
    $("#saveType")[0].reset();
    $('#type_dialog').modal('show');
}

function clearerror() {
    $(".help-block").remove();
    $(".has-error").removeClass('has-error');
}

// 跳转到第二步
function tostep2() {
    // 校验
    if ($("#addname").val() == "") {
        if ($("#addname").parents('.form-group').hasClass('has-error')) {
            return;
        }
        $("#addname").parents('.form-group').addClass('has-error');
        $("#addname").parent().append('<p class="help-block">不能为空</p>');
        return;
    }

    // 校验规格
    if ($("#addspecs option:selected").length == 0) {
        if (!$("#addspecs").parents('.form-group').hasClass('has-error')) {
            $("#addspecs").parents('.form-group').addClass('has-error');
            $("#addspecs").parent().append('<p class="help-block">请至少选择一个规格</p>');
        }
        return;
    }

    clearerror();
    $("#addType1").hide();
    $("#addType2").show();
}


// 保存类型
function savetype() {

    if (acount == 1) {
        return;
    }
    clearerror();

    var haserror = false;
    // 校验属性名
    $(".attributeName").each(function () {
        if ($(this).val() == "") {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">不能为空</p>');
            }
        }
    });


    // 校验属性值
    $(".addselect").each(function () {
        if ($(this).find(".as-selection-item").length == 0) {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">不能为空</p>');
            }
        }
    });

    // 校验排序
    $(".attributeSort").each(function () {
        if (!(/^\d+$/.test($(this).val()))) {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">请输入数字</p>');
            }
        }
    });

    if (haserror) {
        return;
    }


    clearerror();

    var type = {};
    type.name = $("#addname").val();
    type.attributes = new Array();
    type.delFlag = "0";
    type.typeSpecs = new Array();
    $('.type_tab tbody tr').each(function () {
        var attribute = {};
        attribute.name = $(this).find('.attributeName').val();
        attribute.sort = $(this).find('.attributeSort').val();
        attribute.delFlag = "0";
        attribute.attributeValues = new Array();
        $(this).find('.as-selection-item').find('span').each(function () {
            var attributeValue = {};
            attributeValue.value = $(this).html();
            attributeValue.delFlag = "0";
            attribute.attributeValues.push(attributeValue)
        });
        type.attributes.push(attribute);
    })

    $('#addspecs option:selected').each(function () {
        var typeSpec = {};
        typeSpec.specId = $(this).val();
        typeSpec.delFlag = "0";
        type.typeSpecs.push(typeSpec);
    })

    acount = 1;
    LSFetch({
        url: basePath + '/addtype.htm',
        data: JSON.stringify(type),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#type_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备删除类型
function todelete(id) {
    $("#removeTypeId").val(id);
    $('#remove_dialog').modal('show');
}


// 删除类型
function deleteType() {
    LSFetch({
        url: basePath + '/deletetype.htm?id=' + $("#removeTypeId").val(),
        success: function () {
            $('#remove_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备批量删除
function todeletetypes() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的类型?",ids);
}

// 删除所有类型
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/batchdeletetypes.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}

// 跳转到修改页面
function toupdate(id)
{
    clearCount();
    $("#updateType1").show();
    $("#updateType2").hide();

    LSFetch({
        url: basePath + '/querytypedetail.htm?id=' +id,
        success: function (type) {
            $("#typeId").val(type.id);
            $("#typeName").val(type.name);

            // 加载规格
            LSFetch({
                url: basePath + '/queryallspecs.htm',
                async: false,
                success: function (specs) {
                    $("#updatespecs").html('');
                    for (var i = 0; i < specs.length; i++) {
                            $("#updatespecs").append("<option value=" + specs[i].id + ">" + specs[i].nickName + "</option>");
                    }
                }
            })

            // 设置选中的规格
            for (var j = 0; j < type.typeSpecs.length; j++) {
                    $("#updatespecs option[value=" + type.typeSpecs[j].specId + "]").attr("selected", true);
            }

            $('select[data-live-search="true"]').select2();


            // 加载属性
            var attributesHtml = "";
            $("#updateattributes").html("");
            for (var i = 0; i < type.attributes.length; i++) {
                attributesHtml = '';
                attributesHtml = attributesHtml + "<tr id='"+type.attributes[i].id+"'>" +
                    "<td class='form-group'><input class='form-control updateattributename' type='text' value='" + type.attributes[i].name + "' ></td>" +
                    '<td class="form-group">';

                attributesHtml += '<ul class="as-selections updateselect">';
                for (var k = 0; k < type.attributes[i].attributeValues.length; k++) {
                    attributesHtml += '<li class="as-selection-item">' +
                        '<a class="update-as-close">×</a><span id="'+type.attributes[i].attributeValues[k].id+'">' + type.attributes[i].attributeValues[k].value + '</span></li>';

                }
                attributesHtml += '<li class="as-original"><input type="text" class="as-input" placeholder="输入属性值并回车添加..." autocomplete="off"></li></ul></td>';
                attributesHtml += "<td class='form-group'><input class='form-control updateattributesort' maxlength='3'  value='" + type.attributes[i].sort + "' class='w50' ></td>";
                attributesHtml += '<td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_tab_btn"><i class="icon-trash"></i> 删除</button></td>' + "</tr>";
                $("#updateattributes").append(attributesHtml);
            }
        }
    })

    $('#update_dialog').modal('show');
}

// 更新跳转到第二步
function toupdatestep2() {

    // 校验
    if ($("#typeName").val() == "") {
        if ($("#typeName").parents('.form-group').hasClass('has-error')) {
            return;
        }
        $("#typeName").parents('.form-group').addClass('has-error');
        $("#typeName").parent().append('<p class="help-block">不能为空</p>');
        return;
    }

    // 校验规格
    if ($("#updatespecs option:selected").length == 0) {
        if (!$("#updatespecs").parents('.form-group').hasClass('has-error')) {
            $("#updatespecs").parents('.form-group').addClass('has-error');
            $("#updatespecs").parent().append('<p class="help-block">请至少选择一个规格</p>');
        }
        return;
    }

    clearerror();

    $("#updateType1").hide();
    $("#updateType2").show();
}


/**
 * 添加一个属性
 */
function addAttribute() {
    var html =
        '<tr>' +
        '<td class="form-group">' +
        '<input class="form-control updateattributename" type="text" >' +
        '</td>' +
        '<td class="form-group"><ul class="as-selections updateselect"><li class="as-original"><input type="text" class="as-input" placeholder="输入属性值并回车添加..." autocomplete="off"></li></ul>' +
        '<td class="form-group"><input class="form-control updateattributesort" type="text"></td>' +
        '<td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_tab_btn"><i class="icon-trash"></i> 删除</button></td>' +
        '</tr>';
    $("#updateattributes").append(html);
}


// 更新类型
function updatetype()
{

    if (bcount == 1) {
        return;
    }


    clearerror();

    var haserror = false;
    // 校验属性名
    $(".updateattributename").each(function () {
        if ($(this).val() == "") {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">不能为空</p>');
            }
        }
    });

    // 校验属性值
    $(".updateselect").each(function () {
        if (($(this).find(".as-selection-item").length - $(this).find(".as-selection-item.hide").length) == 0) {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">不能为空</p>');
            }
        }
    });

    // 校验排序
    $(".updateattributesort").each(function () {
        if (!(/^\d+$/.test($(this).val()))) {
            haserror = true;
            if (!$(this).parent().hasClass('has-error')) {
                $(this).parent().addClass('has-error');
                $(this).parent().append('<p class="help-block">请输入数字</p>');
            }
        }
    });

    if (haserror) {
        return;
    }


    var type = {};

    type.id = $("#typeId").val();
    type.name = $("#typeName").val();
    type.delFlag = "0";
    type.attributes = new Array();
    type.typeSpecs = new Array();
    $('#updateattributes tr').each(function () {
        var attribute = {};
        attribute.name = $(this).find('.updateattributename').val();
        attribute.typeId=type.id;
        attribute.sort = $(this).find('.updateattributesort').val();
        attribute.delFlag = "0";
        attribute.attributeValues = new Array();
        attribute.id="";
        if (typeof($(this).attr("id")) != "undefined") {
            attribute.id = $(this).attr("id");
        }

        $(this).find('.as-selection-item').find('span').each(function () {
            var attributeValue = {};
            attributeValue.value = $(this).html();
            attributeValue.typeId = type.id;
            attributeValue.delFlag = "0";
            attributeValue.id = "";
            if (typeof($(this).attr("id")) != "undefined") {
                attributeValue.id = $(this).attr("id");
            }

            attribute.attributeValues.push(attributeValue)

        });

        type.attributes.push(attribute);
    })

    $('#updatespecs option:selected').each(function () {
        var typeSpec = {};
        typeSpec.specId = $(this).val();
        typeSpec.typeId=type.id;
        typeSpec.delFlag = "0";
        type.typeSpecs.push(typeSpec);
    })


    bcount = 1;

    LSFetch({
        url: basePath + '/updatetype.htm',
        data: JSON.stringify(type),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })
}