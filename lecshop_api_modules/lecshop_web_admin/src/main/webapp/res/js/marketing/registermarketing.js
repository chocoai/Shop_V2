// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/queryregistermarketing.htm',
        success: function (data) {
            $("#registerMarketingId").val(data.id);
            $("#startTime").val(data.startTime);
            $("#endTime").val(data.endTime);
            $("#isUse").val(data.isUse);
            $("#pointNum").val(data.pointNum);
            var param = '<option value="-1">未选择优惠券</option>';
            for (var i = 0; i < data.couponList.length; i++) {
                param += '<option value="' + data.couponList[i].id + '"';
                if (data.couponId == data.couponList[i].id) {
                    param += ' selected';
                }
                param += '>' + data.couponList[i].name + '</option>';
            }
            $("#couponSelect").append(param);
        }
    });
};

// 初始化列表
initDataTable();

// 保存注册营销设置
function updateRegMark() {
    if (!$("#regMarket").valid()) {
        return;
    }
    var registerMarketing = {
        id: $("#registerMarketingId").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        isUse: $("#isUse").val(),
        couponId: $("#couponSelect").val(),
        pointNum: $("#pointNum").val()
    };
    LSFetch({
        url: basePath + '/updateregistermarketing.htm',
        data: JSON.stringify(registerMarketing),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                showerror('保存成功');
            } else {
                showerror('保存失败');
            }
        }
    })
}