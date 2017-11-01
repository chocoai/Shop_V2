<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录 - 乐商商城</title>
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
</head>

<body>
<div class="container reg_logo clearfix"><a class="logo fll" href="#"><img style="max-height: 50px" src="" alt=""/></a>
    <h1>商家后台登录</h1>
</div>
<div class="login_bg">
    <div class="container clearfix">
        <div class="new_login login_box clearfix">
            <div class="new_login_con">
                <div class="n_title clearfix"><span>商家登录</span></div>
                <div class="n_row input_box clearfix">
                    <input type="text" class="n_text" placeholder="用户名" name="username">
                    <span class="login_name"></span>
                    <div class="n_tips" id="username">您输入的用户名不正确</div>
                </div>
                <div class="n_row input_box clearfix">
                    <input type="password" class="n_text" placeholder="密码" name="password">
                    <span class="login_password"></span>
                    <div class="n_tips" id="password">您输入用户名或密码不正确</div>
                </div>
                <div class="n_row clearfix">
                    <div class="input_box code_box fll clearfix">
                        <input type="text" class="n_text" style="width:65px" maxlength="4" name="code">
                    </div>
                    <span class="n_rg">验证码：</span>
                    <img width="80" height="36" class="code_img fll" src="<%=basePath %>/store_createkaptcha.htm" alt="" onclick="this.src=this.src+'?'+Math.random(); "/>
                    <a href="#" class="bluee code_btn fll">换一张</a>
                    <div class="n_tips" id="code">您输入的验证码不正确</div>
                </div>
                <butto type="button" class="n_btn" onclick="login()">登录</butto>
            </div>
        </div>
    </div>
</div>
<div class="container footer_main easy_footer">
    <div class="footer_text">
    </div>
</div>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script>
    $(function () {
        $("body").keydown(function (e) {
            if (e.keyCode == 13) {
                login();
            }
        });
        $("input").focus(function () {
            $(".n_tips").hide();
        });
        LSFetch({
            url: '<%=basePath%>/store_querybaseinfosetunauth.htm',
            success: function (data) {
                $(".logo.fll img").attr("src", data.adminLogo);
                $(".login_bg").css("background", "url(" + data.storeLoginPic + ") top center no-repeat");
                $(".footer_text").html(data.copyrightInfo);
            }
        });
    });
    function login() {
        var code = $("input[name='code']").val();
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        if (loginValid(code, "code", "验证码") | loginValid(username, "username", "用户名") | loginValid(password, "password", "密码")) {
            return;
        }
        $.ajax({
            url: '<%=basePath%>/store_login.htm',
            type: 'POST',
            data: {
                username: username, password: password, code: code
            },
            success: function (data) {
                if (data == 1) {
                    location.href = "store_stateofbeing.htm"
                } else {
                    var orderStatusDict = {
                        '0': '输入不能为空',
                        '-1': '验证码不正确',
                        '-2': '用户不存在',
                        '-3': '用户名或密码错误',
                        '-4': '改用户已被冻结',
                        '-5': '该用户暂未启用',
                        '-6': '店铺已被关闭'
                    };
                    $("#password").text(orderStatusDict[data.toString()]);
                    $("#password").show();
                }
            }
        });
    }
    function loginValid(param, paramName, tip) {
        if (param == "") {
            $("#" + paramName).text(tip + "不能为空");
            $("#" + paramName).show();
            return true;
        }
    }
</script>
</body>
</html>
