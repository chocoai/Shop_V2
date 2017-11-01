<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的信息</title>
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
      <div class="myfollow-bd">
        <ul class="tab-trigger">
          <li class="curr"><a href="">我的消息</a></li>
        </ul>
        <div class="myfollow-main" style="clear:both" id="customerinfo">

          <div class="mt20 clearfix">
            <div class=" flr" id="messagePage">

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/common_usercenter.js"></script>
<script src="<%=basePath %>/res/layui/layui.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/customer/customerinfo.js"></script>
</body>
</html>
