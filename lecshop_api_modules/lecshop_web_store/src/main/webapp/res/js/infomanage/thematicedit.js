// 基本路径
var basePath = $("#basePath").val();

// 初始化修改专题页面
var initData = function () {

    LSFetch({
        url: basePath + '/store_querythematicbyid.htm',
        data: {
            id: $("#id").val()
        },
        success: function (data) {
            $("#title").val(data.title);
            $("#seoKeywords").val(data.seoKeywords);
            $("#seoDesc").val(data.seoDesc);
            $("#hideHeadTail").val(data.hideHeadTail);
            $("#isUse").val(data.isUse);
            $("#desc").code(data.desc)
        }
    });

};

initData();

// 修改专题
function updateThematic() {
    if (!$("#editForm").valid()) {
        return;
    }
    var thematic = {
        id: $("#id").val(),
        title: $("#title").val(),
        seoKeywords: $("#seoKeywords").val(),
        seoDesc: $("#seoDesc").val(),
        hideHeadTail: $("#hideHeadTail").val(),
        isUse: $("#isUse").val(),
        desc: $("#desc").code()
    };
    LSFetch({
        url: basePath + '/store_editthematic.htm',
        data: JSON.stringify(thematic),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                window.location.href = document.referrer;
            } else {
                showerror("修改失败");
            }
        }
    });
}