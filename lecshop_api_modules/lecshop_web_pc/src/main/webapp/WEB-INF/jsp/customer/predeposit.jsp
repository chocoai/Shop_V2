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
  <title>我的预存款</title>
  <link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/user.css" type="text/css" rel="stylesheet">
  <script src="<%=basePath %>/res/layui/layui.js"></script>
  <script src="<%=basePath %>/res/js/common_usercenter.js"></script>
</head>
<body>
<jsp:include page="../common/head.jsp"></jsp:include>
<jsp:include page="../common/usercentrehead.jsp"></jsp:include>
<div class="container clearfix">
  <input type="hidden" value="<%=basePath%>" id="basePath">
  <div class="content clearfix">
    <jsp:include page="../common/usercentreleft.jsp"></jsp:include>
    <div class="main">
      <div class="myfollow-bd">
        <div class="rich-assets clearfix">
          <div class="deposit_box">
            <div class="deposit-title">我的可用余额</div>
            <div class="deposit-price"><em class="deposit-p-num" id="preDepositCount"></em></div>
          </div>
          <a class="recharge_btn" href="javascript:;">立即去充值</a>
        </div>
        <ul class="tab-trigger">
          <li class="curr"><a href="topredeposit.htm">全部</a></li>
          <li><a href="toamountreceived.htm">收入</a></li>
          <li><a href="toamountpaid.htm">支出</a></li>
        </ul>
        <div class="myfollow-main" style="clear:both">
          <table class="tb-void mt20">
            <thead>
            <tr>
              <th>交易类型</th>
              <th>收入金额</th>
              <th>支出金额</th>
              <th>当前余额</th>
              <th>时间</th>
              <th>备注</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
          </table>
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
<script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/customer/predeposit.js"></script>
</body>
</html>
