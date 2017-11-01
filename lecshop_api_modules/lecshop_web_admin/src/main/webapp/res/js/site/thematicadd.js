//新增时候防止重复提交
var thematicacount = 0;

function clearCount() {
    thematicacount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

function addThematicSetting() {
    if (thematicacount == 1) {
        return;
    }
    if (!$("#addForm").valid()) {
        return;
    }
    thematicacount = 1;
    var thematicSetting = {
        title: $("#title").val(),
        seoKeywords: $("#seoKeywords").val(),
        seoDesc: $("#seoDesc").val(),
        hideHeadTail: $("#hideHeadTail").val(),
        isUse: $("#isUse").val(),
        desc: $("#desc").code()
    };
    LSFetch({
        url: basePath + '/addthematic.htm',
        data: JSON.stringify(thematicSetting),
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