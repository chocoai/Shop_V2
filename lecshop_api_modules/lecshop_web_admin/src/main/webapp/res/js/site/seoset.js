// 基本路径
var basePath = $("#basePath").val();

// 初始化系统seo设置页面
var initData = function () {
    LSFetch({
        url: basePath + '/querysystemseo.htm',
        success: function (data) {
            $("#seoDesc").val(data.seoDesc);
            $("#title").val(data.title);
            $("#keyword").val(data.keyWord);
            $("#isOpen").val(data.isOpen);
        }
    });
};

// 保存系统seo设置
function saveSystemSeo() {
    var systemSeo = {
        seoDesc: $("#seoDesc").val(),
        title: $("#title").val(),
        keyWord: $("#keyword").val(),
        isOpen: $("#isOpen").val()
    };
    LSFetch({
        url: basePath + '/updatesystemseo.htm',
        data: JSON.stringify(systemSeo),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                initData();
            }
        }
    });
}

initData();

