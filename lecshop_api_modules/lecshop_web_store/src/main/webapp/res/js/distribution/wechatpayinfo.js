// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/store_wechatsetinfo.htm',
        success: function (data) {
            $("input[name='mchId']").val(data.mchId);
            $("input[name='apiSecret']").val(data.apiSecret);
            $("input[name='appSecret']").val(data.appSecret);
            $("input[name='appId']").val(data.appId);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
initData();

function saveBtn() {
    var weChatSet = {
        mchId: $("input[name='mchId']").val(),
        apiSecret: $("input[name='apiSecret']").val(),
        appSecret: $("input[name='appSecret']").val(),
        appId: $("input[name='appId']").val()
    };
    LSFetch({
        url: basePath + '/store_editwechatinfo.htm',
        data: JSON.stringify(weChatSet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                initData();
                showerror("编辑成功");
            } else {
                showerror("编辑失败");
            }
        }
    });
}
function toSubmit() {
    if ($("input[type='file']").val() == "" || $("input[type='file']").val() == null) {
        showerror("请先上传文件");
        return;
    }
    $("#fileForm").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData == 1) {
            $("input[type='file']").val("");
            showerror("上传成功");
            changeTip();
        } else {
            showerror("上传失败");
        }
    });
}
function changeTip() {
    if ($("input[type='file']").val() == "" || $("input[type='file']").val() == null) {
        $(".fileupload-new").show();
        $(".fileupload-exists").hide();
    } else {
        $(".fileupload-new").hide();
        $(".fileupload-exists").show();
    }
}

