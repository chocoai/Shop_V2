<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>关注的店铺</title>
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
                    <li><a href="toattentionsku.htm">关注的商品</a></li>
                    <li class="curr"><a href="toattentionstore.htm">关注的店铺</a></li>
                </ul>
            </div>
            <div class="myfollow-bd">
                <div class="myfollow-main">
                    <div class="shop-list clearfix"></div>
                    <div class="mt20 clearfix">
                        <div class="layui-laypage flr" id="attentionStorePage"></div>
                    </div>
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
<script type="text/javascript" src="<%=basePath %>/res/js/customer/attentionstore.js"></script>
</body>
</html>
