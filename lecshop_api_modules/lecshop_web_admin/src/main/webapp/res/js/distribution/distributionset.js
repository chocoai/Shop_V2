// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querydistributionset.htm',
        success: function (data) {
            $("input[name='storeId']").val(data.storeId);
            $("input[name='fCommission']").val(data.fcommission);
            $("input[name='sCommission']").val(data.scommission);
            $("input[name='tCommission']").val(data.tcommission);
            $("select[name='isOpen']").val(data.isOpen);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

function saveBtn() {
    if (!$("#form").valid()) {
        return;
    }
    var distributionSet = {
        isOpen: $("select[name='isOpen']").val(),
        storeId: $("input[name='storeId']").val(),
        fcommission: $("input[name='fCommission']").val(),
        scommission: $("input[name='sCommission']").val(),
        tcommission: $("input[name='tCommission']").val()
    };
    LSFetch({
        url: basePath + '/editdistributionset.htm',
        data: JSON.stringify(distributionSet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1) {
                showerror("编辑失败");
            }
            if (data == 1) {
                refreshData();
                showerror("编辑成功");
            }
        }
    });
}