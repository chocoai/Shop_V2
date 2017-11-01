// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_mystoreinfo.htm',
        success: function (data) {
            $("p[name='storeId']").text(data.id);
            $("p[name='storeName']").text(data.storeName);
            $("input[name='serviceQQ']").val(data.serviceQQ);
            $("p[name='companyName']").text(data.companyName);
            $("p[name='companyAddress']").text(data.companyAddress);
            $("input[name='companyPhone']").val(data.companyPhone);
            $("p[name='effectiveTime']").text(data.effectiveTime);
            $("input[name='busLicense']").val(data.busLicense);
            $("input[name='contactPerson']").val(data.contactPerson);
            $("input[name='contactPhone']").val(data.contactPhone);
            $("input[name='legalPerson']").val(data.legalPerson);
            $("input[name='cardNo']").val(data.cardNo);
            if (data.taxPic != null && data.taxPic != '') {
                $("img[name='taxPic']").attr("src", data.taxPic);
            }
            if (data.carPic != null && data.carPic != '') {
                $("img[name='carPic']").attr("src", data.carPic);
            }
            if (data.busLicensePic != null && data.busLicensePic != '') {
                $("img[name='busLicensePic']").attr("src", data.busLicensePic);
            }
            if (data.orgPic != null && data.orgPic != '') {
                $("img[name='orgPic']").attr("src", data.orgPic);
            }
            $("input[name='bankUserName']").val(data.bankUserName);
            $("input[name='bankAccount']").val(data.bankAccount);
            $("input[name='bankAddress']").val(data.bankAddress);
            $("input[name='bankName']").val(data.bankName);
            $("input[name='bankNumber']").val(data.bankNumber);
            if (data.bankPic != null && data.bankPic != '') {
                $("img[name='bankPic']").attr("src", data.bankPic);
            }
        }
    });
};

// 初始化列表
initDataTable();

function saveBtn(flag) {
    var isValid = false;
    if (flag == 2) {
        if ($("img[name='carPic']").attr("src") != basePath + "/res/img/no_img.png") {
            $("input[inputName='carPic']").removeClass("required");
        }
        if ($("img[name='busLicensePic']").attr("src") != basePath + "/res/img/no_img.png") {
            $("input[inputName='busLicensePic']").removeClass("required");
        }
        isValid = !$("#carPic").valid() | !$("#busLicensePic").valid()
    }
    if (flag == 3) {
        if ($("img[name='bankPic']").attr("src") != basePath + "/res/img/no_img.png") {
            $("input[inputName='bankPic']").removeClass("required");
        }
        isValid = !$("#bankPic").valid();
    }
    if (!$("#form" + flag).valid() | isValid) {
        return;
    }
    var storeInfo = {
        id: $("p[name='storeId']").text(),
        storeName: $("p[name='storeName']").text(),
        serviceQQ: $("input[name='serviceQQ']").val(),
        companyName: $("p[name='companyName']").text(),
        companyAddress: $("p[name='companyAddress']").text(),
        companyPhone: $("input[name='companyPhone']").val(),
        effectiveTime: $("p[name='effectiveTime']").text(),
        busLicense: $("input[name='busLicense']").val(),
        contactPerson: $("input[name='contactPerson']").val(),
        contactPhone: $("input[name='contactPhone']").val(),
        legalPerson: $("input[name='legalPerson']").val(),
        cardNo: $("input[name='cardNo']").val(),
        bankUserName: $("input[name='bankUserName']").val(),
        bankAccount: $("input[name='bankAccount']").val(),
        bankAddress: $("input[name='bankAddress']").val(),
        bankName: $("input[name='bankName']").val(),
        bankNumber: $("input[name='bankNumber']").val(),
        taxPic: $("img[name='taxPic']").attr("src"),
        carPic: $("img[name='carPic']").attr("src"),
        busLicensePic: $("img[name='busLicensePic']").attr("src"),
        orgPic: $("img[name='orgPic']").attr("src"),
        bankPic: $("img[name='bankPic']").attr("src")
    };
    LSFetch({
        url: basePath + '/store_editmystoreinfo.htm?flag=' + flag,
        data: JSON.stringify(storeInfo),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                initDataTable();
                $('#success_dialog').modal('show');
            }
        }
    });
}

// 上传商品图片
function uploadPic(obj) {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $('#' + obj).validate().resetForm();
        $("img[name='" + obj + "']").attr("src", res);
    }).fail(function (res) {
    });
}
