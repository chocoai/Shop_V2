//新增时候防止重复提交
var helplistacount = 0;

function clearCount() {
    helplistacount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    LSFetch({
        url: basePath + '/queryallhelpcategory.htm',
        success: function (res) {
            var param = '<option value="0">---无---</option>';
            for (var i = 0; i < res.length; i++) {
                param += '<option value="' + res[i].id + '">' + res[i].name + '</option>';
            }
            $("#columnsSelect").append(param);
        }
    });
};

initDataTable();

function addHelp() {
    if (helplistacount == 1) {
        return;
    }
    if (!$("#addForm").valid()) {
        return;
    }
    helplistacount = 1;
    var help = {
        name: $("#helpName").val(),
        helpCateId: $("#columnsSelect").val(),
        sort: $("#sort").val(),
        isShow: $("#isShow").val(),
        desc: $("#desc").code()
    };
    LSFetch({
        url: basePath + '/addhelp.htm',
        data: JSON.stringify(help),
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