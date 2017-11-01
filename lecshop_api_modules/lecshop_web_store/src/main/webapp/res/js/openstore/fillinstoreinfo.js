// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_querystoreinfo.htm',
        success: function (data) {
            if (data.delFlag != "1") {
                $("input[name='companyName']").val(data.companyName);
                $("input[name='companyAddress']").val(data.companyAddress);
                $("input[name='companyPhone']").val(data.companyPhone);
                $("input[name='companyEmail']").val(data.companyEmail);
                $("input[name='contactPerson']").val(data.contactPerson);
                $("input[name='contactPhone']").val(data.contactPhone);
                $("input[name='busLicense']").val(data.busLicense);
                $("input[name='legalPerson']").val(data.legalPerson);
                $("input[name='cardNo']").val(data.cardNo);
                $("img[name='carPic']").attr("src", data.carPic);
                $("img[name='taxPic']").attr("src", data.taxPic);
                $("img[name='busLicensePic']").attr("src", data.busLicensePic);
                $("img[name='orgPic']").attr("src", data.orgPic);
                $("select[name='isMerge']").val(data.isMerge);
                // 三证合一不合一选择
                selectThreeOrOne();
            }
        }
    });
    LSFetch({
        url: basePath + '/store_querybaseinfo.htm',
        success: function (data) {
            $(".logo img").attr("src", data.adminIndexLogo);
        }
    });
};
// 初始化列表
initDataTable();

// 上一步
function toFirstStep() {
    location.href = "store_toopenprotocol.htm"
}

// 下一步点击事件
function toThirdStep() {
    if (checkSelectWho()) {
        if (!$("#textForm").valid() | picVaild("taxPic") | picVaild("carPic")) {
            return;
        }
        submitDate();
    } else {
        if (!$("#textForm").valid() | picVaild("taxPic") | picVaild("carPic") | picVaild("busLicensePic") | picVaild("orgPic")) {
            return;
        }
        submitDate();
    }
}

function picVaild(formName) {
    if ($("#" + formName + "img[name='" + formName + "']").val() == basePath + '/res/img/no_img.png') {
        return !$("#" + formName).valid();
    }
}

// 提交数据
function submitDate() {
    var taxPic = "";
    var busLicensePic = "";
    var orgPic = "";
    if (checkSelectWho()) {
        taxPic = $("img[name='taxPic']").attr("src");
        busLicensePic = taxPic;
        orgPic = taxPic;
    } else {
        taxPic = $("img[name='taxPic']").attr("src");
        busLicensePic = $("img[name='busLicensePic']").attr("src");
        orgPic = $("img[name='orgPic']").attr("src");
    }
    var storeInfo = {
        companyName: $("input[name='companyName']").val(),
        companyAddress: $("input[name='companyAddress']").val(),
        companyPhone: $("input[name='companyPhone']").val(),
        companyEmail: $("input[name='companyEmail']").val(),
        contactPerson: $("input[name='contactPerson']").val(),
        contactPhone: $("input[name='contactPhone']").val(),
        busLicense: $("input[name='busLicense']").val(),
        legalPerson: $("input[name='legalPerson']").val(),
        cardNo: $("input[name='cardNo']").val(),
        carPic: $("img[name='carPic']").attr("src"),
        isMerge: $("select[name='isMerge']").val(),
        taxPic: taxPic,
        busLicensePic: busLicensePic,
        orgPic: orgPic
    };
    LSFetch({
        url: basePath + '/store_dealstoreinfo.htm',
        data: JSON.stringify(storeInfo),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 0) {
                showerror("网络出错");
                return;
            }
            if (data == 1) {
                location.href = "store_toscopeofbusiness.htm";
                return;
            }
            if (data == 2) {
                location.href = "store_tostoreinfo.htm";
                return;
            }
            if (data == 3) {
                location.href = "store_toscopeofbusiness.htm";
                return;
            }
            if (data == 4) {
                location.href = "store_tologin.htm";
                return;
            }
            location.href = "store_tologin.htm";
        }
    });
}
// 三证合一不合一选择
selectThreeOrOne();

// 下拉选择菜单
$('#three_in_one').change(function () {
    selectThreeOrOne();
    $("#taxPic").validate().resetForm();
    $("#busLicensePic").validate().resetForm();
    $("#orgPic").validate().resetForm();
    $("#taxPicDiv").removeClass("has-error");
    $("#busLicensePicDiv").removeClass("has-error");
    $("#orgPicDiv").removeClass("has-error");
});

// 显示隐藏
function selectThreeOrOne() {
    if (checkSelectWho()) {
        $("#taxPicDiv .control-label").html('<span class="label_red">*</span>三证合一证：');
        $("#busLicensePicDiv").hide();
        $("#orgPicDiv").hide();
    }
    else {
        $("#taxPicDiv .control-label").html('<span class="label_red">*</span>税务登记证：');
        $("#busLicensePicDiv").show();
        $("#orgPicDiv").show();
    }
}

// 选择了哪一个
function checkSelectWho() {
    return $('#three_in_one').val() == 0;
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