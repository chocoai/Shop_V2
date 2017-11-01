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
            <header class="panel-heading"> 订单信息 </header>
            <div class="panel-body">
              <header class="panel-heading tab-bg-dark-navy-blue">
                <ul class="nav nav-tabs">
                  <li> <a onclick="changepoint()">基本信息</a> </li>
                  <li class="active"> <a href="">订单信息</a> </li>
                  <li> <a href="">优惠券</a> </li>
                  <li> <a href="">商品关注</a> </li>
                  <li> <a href="" onclick="querypoint()">用户积分</a> </li>
                </ul>
              </header>
              <div class="clearfix">
                <div class="adv-table clearfix">
                  <table class="display table table-bordered table-striped">
                    <thead>
                    <tr>
                      <th width="80">商品图片</th>
                      <th>商品名称</th>
                      <th>订单编号</th>
                      <th>订单总价</th>
                      <th width="100">购买时间</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${customer.orders }" var="order" varStatus="i">
                      <fmt:formatDate value="${order.addTime }" pattern="yyyy-MM-dd HH:mm:ss" var="addTime"/>
                      <tr>
                        <td><img src="${ order.goods[0].goodsImg}" width="60" height="60" alt=""></td>
                        <td>
                          <c:if test="${fn:length(order.goods[0].goodsName)>10 }">
                            ${fn:substring(order.goods[0].goodsName,0,9) }...
                          </c:if>
                          <c:if test="${fn:length(order.goods[0].goodsName)<=10 }">
                            ${order.goods[0].goodsName}
                          </c:if>
                        </td>
                        <td>${order.orderNo }</td>
                        <td>¥${order.moneyPaid }</td>
                        <td>${addTime }</td>
                        <td class="operation_box">
                          <button class="btn btn-success btn-xs" type="button" onclick="jumptoOrder('${order.businessId}','${order.orderNo }')"><i class="icon-eye-open"></i> 查看订单</button>
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div style="width:142px; margin:20px auto"> <a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回会员列表</a> </div>
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

</body>
</html>
