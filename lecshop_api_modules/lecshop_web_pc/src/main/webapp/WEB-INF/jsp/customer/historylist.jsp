<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>浏览历史</title>
    <link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/user.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
<jsp:include page="../common/usercentrehead.jsp"></jsp:include>
<input type="hidden" value="<%=basePath%>" id="basePath">
<div class="container clearfix">
    <div class="content clearfix">
        <jsp:include page="../common/usercentreleft.jsp"></jsp:include>
        <div class="main">
            <div class="myfollow-hd">
                <ul class="tab-trigger">
                    <li class="curr"><a href="">我的足迹</a></li>
                </ul>
            </div>
            <div class="myfollow-bd"
                 style="background:url(<%=basePath %>/res/img/bg-history.jpg) no-repeat top center #f5f5f5">
                <div class="myfollow-main" style="clear:both; padding-right:0">
                    <div class="goods-content">
                        <div class="p-line"></div>
                        <div class="p-line-red"></div>
                        <div class="clearfix" id="history"></div>
                        <div class="good-bottom">已到最后，只保存最近 1 个月的浏览记录</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/customer/historylist.js"></script>
</body>
</html>

