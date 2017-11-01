<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登录 - 乐商商城</title>
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
</head>
<body>
<script language="JavaScript">
    window.location.href = "tologin.htm";
</script>
</body>
</html>
