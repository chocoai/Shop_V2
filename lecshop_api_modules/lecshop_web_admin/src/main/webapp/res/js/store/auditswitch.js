// 基本路径
var basePath = $("#basePath").val();

var init = function () {
    LSFetch({
        url: basePath + '/querybaseinfoset.htm',
        success: function (res) {
            if (res.storeSpuAudit == 0) {
                $("option[value='0']").attr("selected", true);
            } else if (res.storeSpuAudit == 1){
                $("option[value='1']").attr("selected", true);
            }
        }
    })
};

// 初始化页面
init();

// 保存设置
function saveSetting() {
    var storeSpuAudit = $("#isThirdAuditUsed").val();
    LSFetch({
        url: basePath + '/setAuditSwitch.htm?storeSpuAudit=' + storeSpuAudit,
        success: function () {
            location.reload();
        }
    })
}