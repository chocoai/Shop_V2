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
