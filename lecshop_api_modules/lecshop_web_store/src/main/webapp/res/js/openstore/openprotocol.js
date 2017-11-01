// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_querybaseinfo.htm',
        success: function (data) {
            $(".logo img").attr("src", data.adminIndexLogo);
            $("#protocol").html(data.storeOpenProtocol);
        }
    });
};
// 初始化列表
initDataTable();

$('#next_btn').click(function () {
    if ($("input[name='isCheck']").is(':checked')) {
        location.href = "store_tofillinstoreinfo.htm?isCheck=" + $("input[name='isCheck']").is(':checked');
    } else {
        $('#tip_dialog').modal('show');
    }
});





