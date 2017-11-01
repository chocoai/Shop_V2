// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querychildcolumnlist.htm',
        success: function (data) {
            var param = '';
            for (var i = 0; i < data.length; i++) {
                param += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("select[name='columnlist']").append(param);
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
};

// 初始化列表
initData();

function addSaveBtn() {
    if (!$("#form").valid()) {
        return;
    }
    var articleList = {
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
        url: basePath + '/addarticle.htm',
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
