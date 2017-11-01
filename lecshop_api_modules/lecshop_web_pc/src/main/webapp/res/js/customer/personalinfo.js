var basePath = $("#basePath").val();

var queryPersonalInfo = function () {
    LSFetch({
        url: basePath + 'customer/querypersonalinfo.htm',
        success: function (data) {
            $("input[name='nickName']").val(data.nickName);
            $("input[name='gender'][value='" + data.gender + "']").prop("checked", true);
            $("strong[name='userName']").text(data.userName);
            $("strong[name='name']").text(data.customerLevel.name);
            $("input[name='releName']").val(data.releName);
            if (data.image != '') {
                $("img[name='image']").attr("src", data.image);
            }
            $("select[name='monthlyIncome']").val(data.monthlyIncome);
            $("input[name='marriageStatus'][value='" + data.marriageStatus + "']").prop("checked", true);
            $("input[name='cardId']").val(data.cardId);
            loadDate((data.birthday == null ? '' : data.birthday).substr(0, 10).split("-"));
        }
    });
};

queryPersonalInfo();

function changeTable(obj) {
    $(".tab-trigger").children('li').removeClass("curr");
    $(obj).parent("li").addClass("curr");
    $(".user-set").hide();
    $("#" + $(obj).attr("target")).show();
}
function baseInfoSubmitBtn(idElement) {
    updateUserInfo(idElement);
}

function headPortraitSubmitBtn(idElement) {
    updateUserInfo(idElement);
}

function moreInfoSubmitBtn(idElement) {
    updateUserInfo(idElement);
}

function updateUserInfo(idElement) {
    var param = 1;
    var nickName = '';
    var gender = '';
    var birthday = null;
    var releName = '';
    var image = '';
    var monthlyIncome = '';
    var marriageStatus = '';
    var cardId = '';
    if (idElement == 'baseInfo') {
        nickName = $("input[name='nickName']").val();
        gender = $("input[name='gender']:checked").val();
        if ($("select[name='year']").val() != 0 && $("select[name='month']").val() != 0 && $("select[name='day']").val() != 0) {
            birthday = $("select[name='year']").val() + '-' + $("select[name='month']").val() + '-' + $("select[name='day']").val() + ' 00:00:00';
        }
        releName = $("input[name='releName']").val();
        param = 1;
    }
    if (idElement == 'headPortrait') {
        image = $("img[name='image']").attr("src");
        param = 2;
    }
    if (idElement == 'moreInfo') {
        monthlyIncome = $("select[name='monthlyIncome']").val();
        marriageStatus = $("input[name='marriageStatus']:checked").val();
        cardId = $("input[name='cardId']").val();
        param = 3;
    }
    var customer = {
        nickName: nickName,
        gender: gender,
        birthday: birthday,
        releName: releName,
        image: image,
        monthlyIncome: monthlyIncome,
        marriageStatus: marriageStatus,
        cardId: cardId
    };
    LSFetch({
        url: basePath + 'customer/editpersonalinfo.htm?param=' + param,
        data: JSON.stringify(customer),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                showTip(0, '信息保存成功');
            } else {
                showTip(1, '信息保存失败');
            }
        }
    });
}
// 上传商品图片
function uploadPic(obj) {
    $("#loading").show();
    setTimeout(function () {
        $("#loading").hide();
    }, 800);
    $.ajax({
        url: basePath + 'customer/pcuploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("img[name='image']").attr("src", res);
    }).fail(function (res) {
        $("#imgError").show();
    });
}
function loadDate(originalBirthday) {
    var originalBirthdayYear = originalBirthday[0];  // 原年份
    var originalBirthdayMonth = parseIntAddZero(parseInt(originalBirthday[1], 10));// 原月份
    var originalBirthdayDay = parseIntAddZero(parseInt(originalBirthday[2], 10));// 原日期
    var nowDate = new Date(); // 获取当前时间的年份
    var nowYear = nowDate.getFullYear();// 当前年份
    var nowMonth = nowDate.getMonth() + 1;// 当前月份
    // 清空年份、月份的下拉框 进行重新添加选项
    $("#birthdayYear").empty();
    $("#birthdayMonth").empty();
    // 首先为年份字段 添加选项
    $("<option value='0'  selected='selected'>请选择：</option>").appendTo("#birthdayYear");
    for (var startYear = nowYear; startYear >= 1930; startYear--) {
        $("<option value='" + startYear + "'>" + startYear + "</option>").appendTo("#birthdayYear");
    }
    $("<option value='0' selected='selected'>请选择：</option>").appendTo("#birthdayMonth");
    for (var startMonth = 1; startMonth <= 12; startMonth++) {
        if (startMonth < 10) {
            $("<option value='0" + startMonth + "'>" + '0' + startMonth + "</option>").appendTo("#birthdayMonth");
        } else {
            $("<option value='" + startMonth + "'>" + startMonth + "</option>").appendTo("#birthdayMonth");
        }
    }
    $("<option value='0' selected='selected'>请选择：</option>").appendTo("#birthdayDay");
    if (originalBirthdayYear == null || originalBirthdayYear == "" || originalBirthdayYear == "1") {
        $("#birthdayYear").val(0);
        $("#birthdayMonth").val(0);
        $("#birthdayDay").val(0);
    } else {
        $("#birthdayYear").val(originalBirthdayYear);
        $("#birthdayMonth").val(originalBirthdayMonth);
        changeSelectBirthdayDay(originalBirthdayDay);
    }
    // 选择生日年份后触发
    $("#birthdayYear").change(function () {
        changeSelectBirthdayDay(originalBirthdayDay);
    });
    // 选择生日月份后触发
    $("#birthdayMonth").change(function () {
        changeSelectBirthdayDay(originalBirthdayDay);
    });
}
// 根据所选择的年份、月份计算月最大天数,并重新填充生日下拉框中的日期项
function changeSelectBirthdayDay(originalBirthdayDay) {
    var maxNum;
    var month = $("#birthdayMonth").val();
    var year = $("#birthdayYear").val();
    if (year == 0) { // 如果年份没有选择，则按照闰年计算日期(借用2004年为闰年)
        year = 2004;
    }
    if (month == 0) {
        maxNum = 31;
    } else if (month == 2) {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { // 判断闰年
            maxNum = 29;
        } else {
            maxNum = 28;
        }
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
        maxNum = 30;
    } else {
        maxNum = 31;
    }
    // 清空日期的下拉框 进行重新添加选项
    $("#birthdayDay").empty();
    if (month == 0) {
        $("<option value='0' disabled='' selected='selected'>请选择：</option>").appendTo("#birthdayDay");
    } else {
        for (var startDay = 1; startDay <= maxNum; startDay++) {
            if (startDay < 10) {
                $("<option value='0" + startDay + "'>" + '0' + startDay + "</option>").appendTo("#birthdayDay");
            } else {
                $("<option value='" + startDay + "'>" + startDay + "</option>").appendTo("#birthdayDay");
            }
        }
        if (maxNum >= originalBirthdayDay) {
            setTimeout(function () {
                $("#birthdayDay").val(originalBirthdayDay);
            }, 1);
            //设置当前年份为所选
        } else {
            setTimeout(function () {
                $("#birthdayDay").val('01');
            }, 1);
            //设置当前年份为所选
            originalBirthdayDay = 1;
        }
    }
}

function parseIntAddZero(date) {
    if (date < 10) {
        return '0' + date;
    }
}
