// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_querybaseinfo.htm',
        success: function (data) {
            $(".logo img").attr("src", data.adminIndexLogo);
        }
    });
};
// 初始化列表
initDataTable();