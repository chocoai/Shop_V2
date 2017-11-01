<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <title>锁屏</title>
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>/res/js/html5shiv.js"></script>
    <script src="<%=basePath %>/res/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="lock-screen" onload="startTime()">
<div class="lock-wrapper">
    <div id="time"></div>
    <div class="lock-box text-center"><img style="left:50%; margin-left:-42px" src="<%=basePath %>/res/img/badge.png" alt=""/>
        <h1 style="text-transform:none">${userName}</h1>
        <span class="locked">锁定</span>
        <div class="form-group col-lg-12">
            <input type="password" name="password" placeholder="Password" class="form-control lock-input" onfocus="divHidden()">
            <input type="hidden" name="username" value="${userName}">
            <button class="btn btn-lock" type="button" onclick="loginBtn()">
                <i class="icon-arrow-right"></i>
            </button>
            <div class="alert alert-block alert-danger fade in" style="margin: 10px 0 0 0;display: none;">
                <button data-dismiss="alert" class="close close-sm" type="button">
                    <i class="icon-remove"></i>
                </button>
                您输入的密码有误，请重新输入。
            </div>
        </div>
        <div style="margin-top: 20px"><a href="store_tologin.htm">使用其他账户登录</a></div>
    </div>
</div>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("body").keydown(function (e) {
            if (e.keyCode == 13) {
                loginBtn();
            }
        });
    });
    function divHidden() {
        $(".alert-block").hide();
    }
    function loginBtn() {
        if ($("input[name='password']").val() == '') {
            $(".alert-block").text('请输入密码');
            $(".alert-block").show();
            return;
        }
        $.ajax({
            url: 'store_locklogin.htm',
            type: 'POST',
            data: {
                username: $("input[name='username']").val(),
                password: $("input[name='password']").val()
            },
            success: function (data) {
                //-2 不存在该用户 -3 用户名或密码错误 -4 冻结 -5 未启用 1 成功
                if (data == -2) {
                    $(".alert-block").text('不存在该用户');
                    $(".alert-block").show();
                } else if (data == -3) {
                    $(".alert-block").text('用户名或密码不正确');
                    $(".alert-block").show();
                } else if (data == -4) {
                    $(".alert-block").text('该用户被禁用请联系平台');
                    $(".alert-block").show();
                } else if (data == -5) {
                    $(".alert-block").text('该用户未启用');
                    $(".alert-block").show();
                } else if (data == -6) {
                    $(".alert-block").text('店铺已被关闭');
                    $(".alert-block").show();
                } else {
                    location.href = "store_stateofbeing.htm"
                }
            }
        });
    }
    function startTime() {
        var today = new Date();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
        setTimeout(function () {
            startTime()
        }, 500);
    }
    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }
</script>
</body>
</html>
