// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querybaseinfoset.htm',
        success: function (data) {
            $("#tab1 input[name='siteUrl']").val(data.siteUrl);
            $("#tab1 input[name='phone']").val(data.phone);
            if (data.labelLog != '' && data.labelLog != null) {
                $("#tab1 img[name='labelLog']").attr("src", data.labelLog);
            }
            if (data.logo != '' && data.logo != null) {
                $("#tab1 img[name='logo']").attr("src", data.logo);
            }
            if (data.adminLogo != '' && data.adminLogo != null) {
                $("#tab1 img[name='adminLogo']").attr("src", data.adminLogo);
            }
            if (data.adminIndexLogo != '' && data.adminIndexLogo != null) {
                $("#tab1 img[name='adminIndexLogo']").attr("src", data.adminIndexLogo);
            }
            if (data.siteLoginPic != '' && data.siteLoginPic != null) {
                $("#tab1 img[name='siteLoginPic']").attr("src", data.siteLoginPic);
            }
            if (data.siteRegisterPic != '' && data.siteRegisterPic != null) {
                $("#tab1 img[name='siteRegisterPic']").attr("src", data.siteRegisterPic);
            }
            if (data.storeLoginPic != '' && data.storeLoginPic != null) {
                $("#tab1 img[name='storeLoginPic']").attr("src", data.storeLoginPic);
            }
            if (data.storeRegisterPic != '' && data.storeRegisterPic != null) {
                $("#tab1 img[name='storeRegisterPic']").attr("src", data.storeRegisterPic);
            }
            if (data.captchaOpen != '' && data.captchaOpen != null) {
                $("#tab2 select[name='captchaOpen']").val(data.captchaOpen);
            }
            $("#copyrightInfoDiv").html(data.copyrightInfo);
            $("#storeRegisterProtocolDiv").html(data.storeRegisterProtocol);
            $("#storeOpenProtocolDiv").html(data.storeOpenProtocol);
            $("#siteRegisterProtocolDiv").html(data.siteRegisterProtocol);
            //富文本编辑框
            $('.summernote').summernote({
                height: 300,
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

// 刷新列表
var refreshData = function () {
    initData();
};

//编辑基本信息
function baseInfoSaveBtn(type) {
    var baseInfoSet = {
        siteUrl: $("#tab1 input[name='siteUrl']").val(),
        phone: $("#tab1 input[name='phone']").val(),
        labelLog: $("#tab1 img[name='labelLog']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='labelLog']").attr("src") : "",
        logo: $("#tab1 img[name='logo']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='logo']").attr("src") : "",
        adminLogo: $("#tab1 img[name='adminLogo']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='adminLogo']").attr("src") : "",
        adminIndexLogo: $("#tab1 img[name='adminIndexLogo']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='adminIndexLogo']").attr("src") : "",
        siteLoginPic: $("#tab1 img[name='siteLoginPic']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='siteLoginPic']").attr("src") : "",
        siteRegisterPic: $("#tab1 img[name='siteRegisterPic']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='siteRegisterPic']").attr("src") : "",
        storeLoginPic: $("#tab1 img[name='storeLoginPic']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='storeLoginPic']").attr("src") : "",
        storeRegisterPic: $("#tab1 img[name='storeRegisterPic']").attr("src").indexOf("no_img.png") == -1 ? $("#tab1 img[name='storeRegisterPic']").attr("src") : "",
        copyrightInfo: $("#copyrightInfoDiv").code(),
        storeRegisterProtocol: $("#storeRegisterProtocolDiv").code(),
        storeOpenProtocol: $("#storeOpenProtocolDiv").code(),
        siteRegisterProtocol: $("#siteRegisterProtocolDiv").code(),
        captchaOpen: $("#tab2 select[name='captchaOpen']").val()
    };
    LSFetch({
        url: basePath + '/editbaseinfoset.htm?type=' + type,
        data: JSON.stringify(baseInfoSet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1 || data == 0) {
                showerror("编辑失败");
            }
            if (data == 1) {
                showerror("编辑成功");
                refreshData();
            }
        }
    });
}

// 上传商品图片
function uploadPic(obj) {
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#tab1 img[name='" + obj + "']").attr("src", res);
        console.log(res);
    }).fail(function (res) {
    });
}