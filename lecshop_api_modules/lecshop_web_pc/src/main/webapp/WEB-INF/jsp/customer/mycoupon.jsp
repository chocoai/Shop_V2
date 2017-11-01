<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的优惠券</title>
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
                <ul class="tab-trigger">
                    <li class="curr"><a onclick="changeTable(this)" search-type="1">可用优惠券</a></li>
                    <li><a onclick="changeTable(this)" search-type="2">已用优惠券</a></li>
                    <li><a onclick="changeTable(this)" search-type="3">过期优惠券</a></li>
                </ul>
            </div>
            <div class="myfollow-bd">
                <div class="coupon-items clearfix"></div>
                <div class="myfollow-main" style="clear:both">
                    <div class="mt20 clearfix">
                        <div class="layui-laypage flr" id="myCouponPage"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/customer/mycoupon.js"></script>
</body>
</html>
