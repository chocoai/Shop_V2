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
<title>退货详情 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
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
  <input type="hidden" id="backOrderId" value="${backOrderId}">
  <!-- 引用头 -->
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
            <li><a href="#">交易</a></li>
            <li><a href="#">交易管理</a></li>
            <li><a href="#">退单列表</a></li>
            <li class="active">退货详情</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel" id="print_box">
            <header class="panel-heading"> 详情 </header>
            <div class="panel-body">
              <div class="panel" style="border:solid 1px #eee">
                <header class="panel-heading" style="background:#eee"> 概况 </header>
                <table class="table">
                  <thead>
                    <tr>
                      <th>退货原因</th>
                      <th>申请凭据</th>
                      <th>退款金额</th>
                      <th>问题说明</th>
                      <th>上传凭证</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td id="reason"></td>
                      <td id="credential"></td>
                      <td id="backPrice"></td>
                      <td id="desc"></td>
                      <td id="pics"></td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="panel" style="border:solid 1px #eee">
                <header class="panel-heading" style="background:#eee"> 商品列表 </header>
                <table class="table">
                  <thead>
                    <tr>
                      <th width="80">商品图片</th>
                      <th>商品名称</th>
                      <th>商品货号</th>
                      <th>销售单价</th>
                      <th width="50">数量</th>
                      <th>实付金额</th>
                    </tr>
                  </thead>
                  <tbody id="skuHtmls">
                  </tbody>
                </table>
              </div>
              <div class="panel" style="border:solid 1px #eee">
                <header class="panel-heading" style="background:#eee"> 操作日志</header>
                <table class="table">
                  <tbody id="logbody">
                  </tbody>
                </table>
              </div>
              <div id="wuliu" class="panel" style="border:solid 1px #eee">
                  <header class="panel-heading" style="background:#eee"> 退单物流信息</header>
                  <table class="table">
                    <tbody>
                    <td>
                      退货物流名称:<span id="logisCompanyName"></span><br/>
                    </td>
                    <td class="sf-item">
                      退货物流单号:<span id="waybillCode"></span>
                    </td>
                    </tbody>
                  </table>
                </div>
                <div style="width:106px; margin:20px auto"><a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回</a></div>
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
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/store/storebackorderdetail.js"></script>
<!--script for this page only-->
</body>
</html>
