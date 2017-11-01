// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryloginset.htm',
        success: function (data) {
            $("#tab1 input[name='key']").val(data.sinaLogin.key);
            $("#tab1 input[name='value']").val(data.sinaLogin.value);
            $("#tab1 input[name='url']").val(data.sinaLogin.url);
            $("#tab1 select[name='isUse']").val(data.sinaLogin.isUse);

            $("#tab2 input[name='key']").val(data.qqLogin.key);
            $("#tab2 input[name='value']").val(data.qqLogin.value);
            $("#tab2 input[name='url']").val(data.qqLogin.url);
            $("#tab2 select[name='isUse']").val(data.qqLogin.isUse);

            $("#tab3 input[name='key']").val(data.wechatLogin.key);
            $("#tab3 input[name='value']").val(data.wechatLogin.value);
            $("#tab3 input[name='url']").val(data.wechatLogin.url);
            $("#tab3 select[name='isUse']").val(data.wechatLogin.isUse);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

function changeLoginSet(num) {
    $(".nav-tabs").children("li").removeClass("active");
    $(this).parent().addClass('active');
    $(".tab-content").children("div").removeClass("active");
    $("#tab" + num).addClass("active");
}

function editLoginSetBtn(numForm) {
    if (!$("#editLoginSetForm" + numForm).valid()) {
        return;
    }
    var params = {
        codeType: numForm,
        key: $("#tab" + numForm + " input[name='key']").val(),
        value: $("#tab" + numForm + " input[name='value']").val(),
        url: $("#tab" + numForm + " input[name='url']").val(),
        isUse: $("#tab" + numForm + " select[name='isUse']").val()
    };
    LSFetch({
        url: basePath + '/editloginset.htm',
        data: params,
        success: function (data) {
            if (data == -1) {
                showerror("系统错误");
                return;
            }
            if (data >= 1) {
                refreshData();
                $('#success_dialog').modal('show');
                return;
            } else {
                showerror("编辑修改出错");
                return;
            }
        }
    });

}