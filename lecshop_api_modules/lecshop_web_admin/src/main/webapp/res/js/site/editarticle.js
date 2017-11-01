// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryarticlebyid.htm',
        data: {
            id: $("#id").val()
        },
        success: function (data) {
            $("input[name='title']").val(data.title);
            $("input[name='author']").val(data.author);
            $("#desc").code(data.desc);
            // $("select[name='columnlist']").val(data.columnId);
            $("input[name='sort']").val(data.sort);
            $("select[name='isRelease']").val(data.isRelease);
            $("textarea[name='seoKeywords']").val(data.seoKeywords);
            $("textarea[name='seoDesc']").val(data.seoDesc);
            LSFetch({
                url: basePath + '/querychildcolumnlist.htm',
                success: function (res) {
                    var param = '';
                    for (var i = 0; i < res.length; i++) {
                        if (data.columnId == res[i].id) {
                            param += '<option value="' + res[i].id + '" selected>' + res[i].name + '</option>';
                        } else {
                            param += '<option value="' + res[i].id + '">' + res[i].name + '</option>';
                        }
                    }
                    $("select[name='columnlist']").append(param);
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

// 初始化列表
initData();

//编辑保存事件
function editSaveBtn() {
    if (!$("#form").valid()) {
        return;
    }
    var articleList = {
        id: $("#id").val(),
        title: $("input[name='title']").val(),
        author: $("input[name='author']").val(),
        desc: $("#desc").code(),
        columnId: $("select[name='columnlist']").val(),
        sort: $("input[name='sort']").val(),
        isRelease: $("select[name='isRelease']").val(),
        seoKeywords: $("textarea[name='seoKeywords']").val(),
        seoDesc: $("textarea[name='seoDesc']").val()
    };
    LSFetch({
        url: basePath + '/editarticle.htm',
        data: JSON.stringify(articleList),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                // 初始化列表
                window.location.href = document.referrer;
            } else {
                showerror("添加失败");
            }
        }
    });
}