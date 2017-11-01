var basePath = $("#basePath").val();

// 修改又拍云
function updateupyun() {
    if (!$("#upyun").valid()) {
        return;
    }

    var upyun = {};
    upyun.id=$("#id").val();
    upyun.nameSpace = $("#nameSpace").val();
    upyun.userName = $("#userName").val();
    upyun.password = $("#password").val();
    upyun.address = $("#address").val();
    LSFetch({
        url: basePath + '/updateupyun.htm',
        data: JSON.stringify(upyun),
        contentType: "application/json;charset=utf-8",
        success: function () {
            showerror("修改成功!");
        }
    })
}