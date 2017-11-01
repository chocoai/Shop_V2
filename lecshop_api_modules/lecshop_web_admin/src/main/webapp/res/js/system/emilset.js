// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryemailset.htm',
        success: function (data) {
            $("input[name='id']").val(data.id);
            $("input[name='senderEmail']").val(data.senderEmail);
            $("input[name='senderName']").val(data.senderName);
            $("input[name='smtpServer']").val(data.smtpServer);
            $("input[name='smtpPort']").val(data.smtpPort);
            $("input[name='username']").val(data.username);
            $("input[name='password']").val(data.password);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//修改邮箱设置
function emailSaveBtn() {
    if (!$("#emailForm").valid()) {
        return;
    }
    var email = {
        id: $("input[name='id']").val(),
        smtpPort: $("input[name='smtpPort']").val(),
        username: $("input[name='username']").val(),
        password: $("input[name='password']").val(),
        senderName: $("input[name='senderName']").val(),
        smtpServer: $("input[name='smtpServer']").val(),
        senderEmail: $("input[name='senderEmail']").val()
    };
    LSFetch({
        url: basePath + '/editemailset.htm',
        data: JSON.stringify(email),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1) {
                showerror("系统错误");
                return;
            }
            if (data == 1) {
                showerror("修改成功");
                refreshData();
                return;
            } else {
                showerror("编辑修改出错");
                return;
            }
        }
    });
}