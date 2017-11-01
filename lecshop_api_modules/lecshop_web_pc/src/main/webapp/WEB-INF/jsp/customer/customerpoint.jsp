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
    <title>我的积分</title>
    <link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/user.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<input type="hidden" value="<%=basePath%>" id="basePath">
<jsp:include page="../common/head.jsp"></jsp:include>
<jsp:include page="../common/usercentrehead.jsp"></jsp:include>
<div class="container clearfix">
    <div class="content clearfix">
        <jsp:include page="../common/usercentreleft.jsp"></jsp:include>
        <div class="main">
            <div class="myfollow-hd">
                <div class="user-bean-cont">您当前的积分:<strong class="ftx01" id="pointCount"></strong></div>
            </div>
            <div class="myfollow-bd">
                <ul class="tab-trigger">
                    <li class="curr"><a href="">积分记录</a></li>
                </ul>
                <div class="myfollow-main" style="clear:both">
                    <table class="tb-void mt20">
                        <thead>
                        <tr>
                            <th>日期</th>
                            <th>获得积分</th>
                            <th>消耗积分</th>
                            <th>详细说明</th>
                        </tr>
                        </thead>
                        <tbody id="pointTbody">

                        </tbody>
                    </table>
                    <div class="mt20 clearfix">
                        <div class=" flr" id="pointPage">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/layui/layui.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/customer/customerpoint.js"></script>
</body>
</html>
