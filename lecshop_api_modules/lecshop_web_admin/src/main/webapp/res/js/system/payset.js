// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querypayset.htm',
        success: function (data) {
            $("#tab1 input[name='pid']").val(data.aliPaySet.pid);
            $("#tab1 input[name='key']").val(data.aliPaySet.key);
            $("#tab1 input[name='payee']").val(data.aliPaySet.payee);
            $("#tab1 input[name='beforeCallbackUrl']").val(data.aliPaySet.beforeCallbackUrl);
            $("#tab1 input[name='backCallbackUrl']").val(data.aliPaySet.backCallbackUrl);
            $("#tab1 input[name='mobilePayCallbackUrl']").val(data.aliPaySet.mobilePayCallbackUrl);
            $("#tab1 select[name='isUse']").val(data.aliPaySet.isUse);

            $("#tab2 input[name='appId']").val(data.wechatPaySet.appId);
            $("#tab2 input[name='appSecret']").val(data.wechatPaySet.appSecret);
            $("#tab2 input[name='merchantNum']").val(data.wechatPaySet.merchantNum);
            $("#tab2 input[name='apiKey']").val(data.wechatPaySet.apiKey);
            $("#tab2 input[name='payCallback']").val(data.wechatPaySet.payCallback);
            $("#tab2 select[name='isUse']").val(data.wechatPaySet.isUse);

            $("#tab3 input[name='merchantNum']").val(data.unionPaySet.merchantNum);
            $("#tab3 input[name='apiKey']").val(data.unionPaySet.apiKey);
            $("#tab3 input[name='backCallbackUrl']").val(data.unionPaySet.backCallbackUrl);
            $("#tab3 input[name='beforeCallbackUrl']").val(data.unionPaySet.beforeCallbackUrl);
            $("#tab3 select[name='isUse']").val(data.unionPaySet.isUse);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//编辑支付设置
function editPaySetBtn(numForm) {
    if (!($("#paySetForm" + numForm).valid())) {
        return;
    }
    var paySetCommon = {
        aliPaySet: {
            pid: $("#tab1 input[name='pid']").val(),
            key: $("#tab1 input[name='key']").val(),
            payee: $("#tab1 input[name='payee']").val(),
            beforeCallbackUrl: $("#tab1 input[name='beforeCallbackUrl']").val(),
            backCallbackUrl: $("#tab1 input[name='backCallbackUrl']").val(),
            mobilePayCallbackUrl: $("#tab1 input[name='mobilePayCallbackUrl']").val(),
            isUse: $("#tab1 select[name='isUse']").val()
        },
        wechatPaySet: {
            appId: $("#tab2 input[name='appId']").val(),
            appSecret: $("#tab2 input[name='appSecret']").val(),
            merchantNum: $("#tab2 input[name='merchantNum']").val(),
            apiKey: $("#tab2 input[name='apiKey']").val(),
            payCallback: $("#tab2 input[name='payCallback']").val(),
            isUse: $("#tab2 select[name='isUse']").val()
        },
        unionPaySet: {
            merchantNum: $("#tab3 input[name='merchantNum']").val(),
            apiKey: $("#tab3 input[name='apiKey']").val(),
            backCallbackUrl: $("#tab3 input[name='backCallbackUrl']").val(),
            beforeCallbackUrl: $("#tab3 input[name='beforeCallbackUrl']").val(),
            isUse: $("#tab3 select[name='isUse']").val()
        }
    };
    LSFetch({
        url: basePath + '/editpayset.htm?codeType=' + numForm,
        data: JSON.stringify(paySetCommon),
        contentType: "application/json;charset=utf-8",
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