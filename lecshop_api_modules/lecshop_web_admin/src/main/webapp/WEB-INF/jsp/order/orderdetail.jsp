<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <title>订单详情 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
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
    <input type="hidden" id="orderId" value="${id}">

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
                        <li><a href="#">订单列表</a></li>
                        <li class="active">订单详情</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel" id="print_box">
                        <header class="panel-heading"> 订单号：<font class="ordercode"></font></header>
                        <div class="panel-body">
                            <ul class="steps">
                                <li id="step1"><span class="step">1</span> <span class="title">已下单<br/><font
                                        id="step1time"></font></span></li>
                                <li id="step2"><span class="step">2</span> <span class="title">已付款<br/><font
                                        id="step2time"></font></span></li>
                                <li id="step3"><span class="step">3</span> <span class="title">已发货<br/><font
                                        id="step3time"></font></span></li>
                                <li id="step4"><span class="step">3</span> <span class="title">已完成<br/><font
                                        id="step4time"></font></span></li>
                            </ul>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 订单概况</header>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td width="50%">订单编号：<font class="ordercode"></font></td>
                                        <td width="50%">下单时间：<font class="ordertime"></font></td>
                                    </tr>
                                    <tr>
                                        <td>订单状态：<font class="orderstatus"></font></td>
                                        <td>是否使用预存款支付：<font class="predeposit"></font></td>
                                    </tr>
                                    <tr>
                                        <td>优惠券码：<font class="coucode"> </font></td>
                                        <td>订单原始金额：<font class="oldprice"></font></td>
                                    </tr>
                                    <tr>
                                        <td>订单优惠金额：<font class="youhuiprice"></font></td>
                                        <td>订单交易金额：<font class="afterprice"></font></td>
                                    </tr>
                                    <tr>
                                        <td>订单使用积分：<font class="usejifen"></font></td>
                                        <td>订单修改金额：<font class="modifyprice"></font></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 物流信息</header>
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td width="50%">物流公司：<font class="expresscompany"> </font></td>
                                        <td width="50%">物流单号：<font class="expresscode"> </font></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">配送方式：<font class="peisongtype"></font></td>
                                        <td width="50%">运费：<font class="expressfee"></font></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">收货地址：<font class="address"></font></td>
                                        <td width="50%">详细地址：<font class="detailaddress"> </font></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">收货人：<font class="personname"></font></td>
                                        <td width="50%">联系电话：<font class="tele"> </font></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">手机：<font class="mobile"> </font></td>
                                        <td width="50%">邮编：<font class="youbian"> </font></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">客户留言：<font class="remark"></font></td>
                                        <td width="50%">&nbsp;</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 发票信息</header>
                                <table class="table">
                                    <tbody id="fapiao">
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 商品列表</header>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th width="80">商品图片</th>
                                        <th>商品名称</th>
                                        <th style="min-width:90px">销售价</th>
                                        <th style="min-width:90px">数量</th>
                                        <th width="150">商品规格</th>
                                        <th width="75">商品总价</th>
                                    </tr>
                                    </thead>
                                    <tbody id="goodsinfo">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                    <div class="panel">
                        <header class="panel-heading tab-bg-dark-navy-blue ">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#tab1">订单操作日志</a></li>
                            </ul>
                        </header>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div id="tab1" class="tab-pane active">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>操作类型</th>
                                            <th>操作人</th>
                                            <th>操作时间</th>
                                            <th>操作原因</th>
                                        </tr>
                                        </thead>
                                        <tbody id="operatorinfo">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="width:240px; margin:20px auto"><a class="btn btn-default btn-lg"
                                                                  href="javascript:history.go(-1);">返回订单列表</a>&nbsp;<a
                            class="btn btn-info btn-lg print"><i class="icon-print"></i> 打印</a></div>
                </div>
            </div>
        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a>
        </div>
    </footer>
    <!--footer end-->
</section>
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript"
        src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/order/orderdetail.js"></script>
<!--script for this page only-->
</body>
</html>
