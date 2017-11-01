var basePath = $("#basePath").val();

// 去添加一级分类
function toaddfcate() {
    $("#savefcate")[0].reset();
    $('#addfcate').modal('show');
}


// 保存一级分类
function savefcate() {

    if (!$("#savefcate").valid()) {
        return;
    }

    var category = {};
    category.name = $("#faname").val();
    category.sort = $("#fasort").val();
    category.grade = 1;
    category.delFlag = "0";
    LSFetch({
        url: basePath + 'addcategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}

$(function () {
    loadFCate(0);
})

function loadFCate(parentId) {
    $("#fcate").html('');
    $("#scate").html('');
    $("#tcate").html('');

    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '" onclick="loadSCate(this,' + cate.id + ')" class="item">' +
                    '<div class="task-title item" > <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button href="javascript:;" class="btn btn-primary btn-xs" onclick="toeditfcate(' + cate.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button  href="javascript:;" class="btn btn-danger btn-xs del_btn" onclick="todeletecate(' + cate.id + ')"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }

            $("#fcate").html(html);
            $("#fcate").find("li.item")[0].click();
        }
    })
}


// 加载二级分类
function loadSCate(obj, parentId) {
    clickItem(obj);
    $("#scate").html('');
    $("#tcate").html('');
    $("#curfcateId").val(parentId);


    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '"  onclick="loadTCate(this,' + cate.id + ')" class="item">' +
                    '<div class="task-title item"> <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button class="btn btn-primary btn-xs" onclick="toeditscate(' + cate.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button class="btn btn-danger btn-xs del_btn" onclick="todeletecate(' + cate.id + ')"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#scate").html(html);
            if ($("#scate").find("li.item").length > 0) {
                $("#scate").find("li.item")[0].click();
            }

        }
    })
}

// 加载三级分类
function loadTCate(obj, parentId) {
    clickItem(obj);
    $("#tcate").html('');
    $("#curscateId").val(parentId);

    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '" class="item"  onclick="clickThirdCate(this,' + cate.id + ')">' +
                    '<div class="task-title item"> <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button class="btn btn-primary btn-xs" onclick="toedittcate(' + cate.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button class="btn btn-danger btn-xs del_btn" onclick="todeletecate(' + cate.id + ')"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#tcate").html(html);
            if ($("#tcate").find("li.item").length > 0) {
                $("#tcate").find("li.item")[0].click();
            }
        }
    })
}

function clickThirdCate(obj, parentId) {
    $("#parentId3").val(parentId);
    clickItem(obj);
}

/**
 * 点击按钮时，改变按钮状态。
 * @param obj 按钮对象
 */
function clickItem(obj) {
    if (obj == null) return;
    $(obj).parent().find("li.item").each(function () {
        $(this).removeClass("active");
    });
    $(obj).addClass("active");
}

// 去添加二级分类
function toaddscate() {
    $("#savescate")[0].reset();

    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=0',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<option class="cate'+cate.id+'" value="' + cate.id + '">' + cate.name + '</option>';
            }
            $('#addscatecate').html(html);
            $("#addscatecate").find("option.cate" + $("#curfcateId").val() + "").prop("selected", true);
            $('#addscatecate').chosen('destroy');
            $('#addscatecate').chosen();
        }
    })
    $('#addscate').modal('show');
}

// 保存二级分类
function savescate()
{
    if (!$("#savescate").valid()) {
        return;
    }

    var category = {};
    category.name = $("#saname").val();
    category.sort = $("#sasort").val();
    category.delFlag = "0";
    category.grade = 2;
    category.parentId = $("#addscatecate option:selected").val();

    LSFetch({
        url: basePath + 'addcategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $("#item" + $("#curfcateId").val()).click();
        }
    })


    $('#addscate').modal("hide");
}


// 去添加三级分类
function toaddtcate()
{
    $("#savetcate")[0].reset();

    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId='+$("#curfcateId").val(),
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<option class="cate'+cate.id+'" value="' + cate.id + '">' + cate.name + '</option>';
            }
            $('#addtcatecate').html(html);
            $("#addtcatecate").find("option.cate" + $("#curscateId").val() + "").prop("selected", true);
            $('#addtcatecate').chosen('destroy');
            $('#addtcatecate').chosen();
        }
    })

    LSFetch({
        url: basePath + 'queryalltypes.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var type = data[i];
                html += '<option class="type'+type.id+'" value="' + type.id + '">' + type.name + '</option>';
            }
            $('#addType').html(html);
            $('#addType').chosen('destroy');
            $('#addType').chosen();
        }
    })

    $('#addtcate').modal('show');
}


// 保存三级分类
function savetcate() {
    if (!$("#savetcate").valid()) {
        return;
    }

    var category = {};
    category.name = $("#taname").val();
    category.sort = $("#tasort").val();
    category.delFlag = "0";
    category.grade = 3;
    category.parentId = $("#addtcatecate option:selected").val();
    category.typeId=$("#addType option:selected").val();
    category.rate = $("#arate").val();
    LSFetch({
        url: basePath + 'addcategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $("#item" + $("#curfcateId").val()).click();
        }
    })


    $('#addtcate').modal("hide");
}

// 准备删除分类
function todeletecate(id)
{
    $('#deleteCateId').val(id);
    $('#del_dialog').modal('show');
}

// 删除分类
function deleteCate() {
    LSFetch({
        url: basePath + 'deletecategory.htm?id=' + $('#deleteCateId').val(),
        success: function (res) {
            if (res == -1) {
                $('#del_dialog').modal('hide');
                showerror("该分类下包含子分类，不能删除！");
            } else {
                $('#del_dialog').modal('hide');
                location.reload();
            }
        }
    })
}

// 跳转到一级分类
function toeditfcate(id) {
    LSFetch({
        url: basePath + 'querycategorybyid.htm?id=' + id,
        success: function (data) {
            $("#fcatName").val(data.name);
            $("#fcatsort").val(data.sort);
            $("#updateCateId").val(data.id);
            $('#editfcate').modal('show');
        }
    })
}


// 跳转到二级分类修改
function toeditscate(id) {

    LSFetch({
        url: basePath + 'querycategorybyid.htm?id=' + id,
        success: function (data) {
            $("#scatName").val(data.name);
            $("#scatsort").val(data.sort);
            $("#updatesCateId").val(data.id);


            LSFetch({
                url: basePath + 'querycategorybyparentid.htm?parentId=0',
                success: function (data2) {
                    var html = '';
                    for (var i = 0; i < data2.length; i++) {
                        var cate = data2[i];
                        html += '<option class="cate' + cate.id + '" value="' + cate.id + '">' + cate.name + '</option>';
                    }

                    $('#editscatecate').html(html);
                    $("#editscatecate").find("option.cate" + data.parentId + "").prop("selected", true);
                    $('#editscatecate').chosen('destroy');
                    $('#editscatecate').chosen();
                }
            })

            $('#editscate').modal('show');
        }
    })
}

// 跳转到三级分类修改
function toedittcate(id) {



    LSFetch({
        url: basePath + 'querycategorybyid.htm?id=' + id,
        success: function (data) {
            $("#tcatName").val(data.name);
            $("#tcatesort").val(data.sort);
            $("#updatetCateId").val(data.id);
            $("#caterate").val(data.rate);

            LSFetch({
                url: basePath + 'querycategorybyparentid.htm?parentId='+$("#curfcateId").val(),
                success: function (data2) {
                    var html = '';
                    for (var i = 0; i < data2.length; i++) {
                        var cate = data2[i];
                        html += '<option class="cate'+cate.id+'" value="' + cate.id + '">' + cate.name + '</option>';
                    }

                    $('#tcateselect').html(html);
                    $("#tcateselect").find("option.cate"+data.parentId+"").prop("selected",true);
                    $('#tcateselect').chosen('destroy');
                    $('#tcateselect').chosen();


                    LSFetch({
                        url: basePath + 'queryalltypes.htm',
                        success: function (data3) {
                            var html = '';
                            for (var i = 0; i < data3.length; i++) {
                                var type = data3[i];
                                html += '<option class="type'+type.id+'" value="' + type.id + '">' + type.name + '</option>';
                            }
                            $('#edittype').html(html);
                            $("#edittype").find("option.type"+data.typeId+"").prop("selected",true);
                            $('#edittype').chosen('destroy');
                            $("#edittype").chosen();
                        }
                    })

                }
            })

            $('#edittcate').modal('show');
        }
    })
}


// 更新一级分类
function updatefcate() {
    if (!$("#updatefcate").valid()) {
        return;
    }

    var category = {};
    category.id=$("#updateCateId").val();
    category.name = $("#fcatName").val();
    category.sort = $("#fcatsort").val();

    LSFetch({
        url: basePath + 'updatecategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}

// 更新二级分类
function updatescate() {
    if (!$("#updatescate").valid()) {
        return;
    }
    var category = {};
    category.id=$("#updatesCateId").val();
    category.name = $("#scatName").val();
    category.sort = $("#scatsort").val();
    category.parentId=$("#editscatecate option:selected").val();
    LSFetch({
        url: basePath + 'updatecategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}


// 更新三级分类
function updatetcate() {
    if (!$("#updatetcate").valid()) {
        return;
    }
    var category = {};
    category.id=$("#updatetCateId").val();
    category.name = $("#tcatName").val();
    category.sort = $("#tcatesort").val();
    category.parentId=$("#tcateselect option:selected").val();
    category.typeId=$("#edittype option:selected").val();
    LSFetch({
        url: basePath + 'updatecategory.htm',
        data: JSON.stringify(category),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}