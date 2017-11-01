// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    LSFetch({
        url: basePath + '/queryordersetting.htm',
        success: function (data) {
            $('.summernote').summernote({
                height: 500,
                tabsize: 2,
                lang: 'zh-CN',
                onImageUpload: function (files, editor, $editable) {
                    sendFile(files, editor, $editable);
                }
            });
            $("#orderSettingId").val(data.id);
            $("#allowBack").val(data.allowBack);
            $("#aotuConfirm").val(data.aotuConfirm);
            $("#cashonDelivery").val(data.cashonDelivery);
            $("#returnDescs").code(data.returnDesc);
            $("#refunds").code(data.refundsDesc);
        }
    });
};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

// 修改退货说明
function editReturn() {
    $("#return_dialog").modal("show");
}

// 保存退货说明
function saveReturn() {
    $("#return_dialog").modal("hide");
}

// 修改退款说明
function editRefunds() {
    $("#refund_dialog").modal("show");
}

// 保存退款说明
function saveRefunds() {
    $("#refund_dialog").modal("hide");
}

// 保存订单设置
function saveOrderSetting() {
    if (!$("#orderSettingForm").valid()) {
        return;
    }
    var orderSetting = {
        id: $("#orderSettingId").val(),
        allowBack: $("#allowBack").val(),
        aotuConfirm: $("#aotuConfirm").val(),
        cashonDelivery: $("#cashonDelivery").val(),
        returnDesc: $("#returnDescs").code(),
        refundsDesc: $("#refunds").code()
    };
    LSFetch({
        url: basePath + '/updateordersetting.htm',
        data: JSON.stringify(orderSetting),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                showerror('修改成功!');
            } else {
                showerror('修改失败');
            }
        }
    })
}