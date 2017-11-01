<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
  <title>会员列表 - 乐商商城</title>
  <!-- Bootstrap core CSS -->
  <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
  <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
  <!-- Custom styles for this template -->
  <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
  <!--[if lt IE 9]>
  <script src="<%=basePath %>/res/js/html5shiv.js"></script>
  <script src="<%=basePath %>/res/js/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<section id="container">
  <input type="hidden" value="<%=basePath%>" id="basePath">

  <jsp:include page="../common/header.jsp"></jsp:include>
  <!-- 引用左侧 -->
  <jsp:include page="../common/left.jsp"></jsp:include>

  <!--main content start-->
  <section id="main-content">
    <section class="wrapper site-min-height">
      <div class="row">
        <div class="col-lg-12">
          <!--breadcrumbs start -->
          <ul class="breadcrumb">
            <li><a href="#">会员</a></li>
            <li><a href="#">会员管理</a></li>
            <li class="active">会员列表</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 基本信息 </header>
            <input type="hidden" id="customerId" value="${customer.id}">
            <div class="panel-body">
              <header class="panel-heading tab-bg-dark-navy-blue">
                <ul class="nav nav-tabs">
                  <li class="active"> <a onclick="querycustomerinfo(${customer.id})">基本信息</a> </li>
                  <li> <a href="">订单信息</a> </li>
                  <li> <a href="">优惠券</a> </li>
                  <li> <a onclick="toqueryattention(${customer.id})">商品关注</a> </li>
                  <li> <a onclick="querypoint(${customer.id})">用户积分</a> </li>
                  <li> <a onclick="querypredeposit(${customer.id})">用户预存款</a> </li>
                </ul>
              </header>
              <div class="clearfix">
                <div class="profile-nav" style="float:left">
                  <div class="user-heading round" style="background:none"> <a href="javascript:;" style="border-color:#eee"> <img src="<c:if test="${customer.image!='' }">${customer.image}</c:if><c:if test="${customer.image==null||customer.image==''}"><%=basePath %>/res/img/member.png</c:if>" alt=""> </a> </div>
                </div>
                <div class="panel-body bio-graph-info" style="float:left; width:80%">
                  <div class="row">
                    <div class="bio-row">
                      <p><span>用户名：</span>${customer.userName }</p>
                    </div>
                    <div class="bio-row">
                      <p><span>真实姓名：</span>${customer.releName}</p>
                    </div>
                    <div class="bio-row">
                      <p><span>兴趣爱好：</span>
                        <c:if test="${fn:length(customer.interest)>20 }">
                          ${fn:substring(customer.interest,0,19) }...
                        </c:if>
                        <c:if test="${fn:length(customer.interest)<=20 }">
                          ${customer.interest}
                        </c:if>
                      </p>
                    </div>
                    <div class="bio-row">
                      <p><span>昵称：</span>${customer.nickName }</p>
                    </div>
                    <div class="bio-row">
                      <p><span>身份证：</span>${customer.cardId}</p>
                    </div>
                    <div class="bio-row">
                      <p><span>收货地址：</span>
                      </p>
                    </div>
                    <div class="bio-row">
                      <p><span>性别：</span>
                        <c:if test="${customer.gender==0}"> 保密 </c:if>
                        <c:if test="${customer.gender==1}"> 男 </c:if>
                        <c:if test="${customer.gender==2}"> 女 </c:if>
                      </p>
                    </div>
                    <div class="bio-row">
                      <p><span>手机：</span>${customer.mobile}</p>
                    </div>
                    <div class="bio-row">
                      <p><span>月收入：</span>
                        <c:if test="${customer.monthlyIncome==0}"> 无收入 </c:if>
                        <c:if test="${customer.monthlyIncome==1}"> 2000元以下 </c:if>
                        <c:if test="${customer.monthlyIncome==2}"> 2000-3999元 </c:if>
                        <c:if test="${customer.monthlyIncome==3}"> 4000-5999元 </c:if>
                        <c:if test="${customer.monthlyIncome==4}"> 6000-7999元 </c:if>
                        <c:if test="${customer.monthlyIncome==5}"> 8000元以上 </c:if>
                      </p>
                    </div>
                    <div class="bio-row">
                      <p><span>邮箱：</span>${customer.email}</p>
                    </div>
                    <div class="bio-row">
                      <p><span>注册时间：</span>${customer.createTime}</p>
                    </div>
                    <div class="bio-row">
                      <p><span>消费总额：</span><font id="myallpoint">${customer.consumptionAmount}</font></p>
                    </div>
                    <div class="bio-row">
                      <p><span>修改时间：</span> ${customer.modifyTime}</p>
                    </div>
                  </div>
                </div>
              </div>
              <div style="width:142px; margin:20px auto"> <a class="btn btn-default btn-lg" href="javascript:;" onclick="goback()">返回会员列表</a> </div>
            </div>
          </section>
        </div>
      </div>
    </section>
  </section>
  <!--main content end-->
  <!--footer start-->
  <footer class="site-footer">
    <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a> </div>
  </footer>
  <!--footer end-->
</section>
<!-- Modal -->
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/customer/customercommon.js"></script>

</body>
</html>
