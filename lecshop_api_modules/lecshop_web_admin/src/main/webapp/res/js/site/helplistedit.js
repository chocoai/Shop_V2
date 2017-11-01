// 基本路径
var basePath = $("#basePath").val();

// 初始化修改帮助页面
var initData = function () {
    LSFetch({
        url: basePath + '/queryhelpbyid.htm',
        data: {
            id: $("#id").val()
        },
        success: function (data) {
            $("#helpName").val(data.name);
            $("#columnsSelect").val(data.helpCateId);
            $("#sort").val(data.sort);
            $("#isShow").val(data.isShow);
            $("#desc").code(data.desc);
            LSFetch({
                url: basePath + '/queryallhelpcategory.htm',
                success: function (res) {
                    var param = '';
                    for (var i = 0; i < res.length; i++) {
                        param += '<option value="' + res[i].id + '"';
                        if (data.helpCateId == res[i].id) {
                            param += ' selected';
                        }
                        param += '>' + res[i].name + '</option>';
                    }
                    $("#columnsSelect").append(param);
                    $('.summernote').summernote({
                        height: 500,
                        tabsize: 2,
                        lang: 'zh-CN',
                        onImageUpload: function (files, editor, $editable) {
                            sendFile(files, editor, $editable);
                        }
                    });
                }
            });
        }
    });
    $('.summernote').summernote({
        height: 500,
        tabsize: 2,
        lang: 'zh-CN',
        onImageUpload: function (files, editor, $editable) {
            sendFile(files, editor, $editable);
        }
    });
};

initData();

// 修改帮助
function updateHelp() {
    if (!$("#editForm").valid()) {
        return;
    }
    var help = {
        id: $("#id").val(),
        name: $("#helpName").val(),
        helpCateId: $("#columnsSelect").val(),
        sort: $("#sort").val(),
        isShow: $("#isShow").val(),
        desc: $("#desc").code()
    };
    LSFetch({
        url: basePath + '/updatehelp.htm',
        data: JSON.stringify(help),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = document.referrer;
        }
    });
}