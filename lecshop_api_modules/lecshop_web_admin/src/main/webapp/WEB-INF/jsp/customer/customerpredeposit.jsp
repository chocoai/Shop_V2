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
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <!--external css-->
  <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
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
            <header class="panel-heading"> 会员详情 </header>
            <div class="panel-body">
              <header class="panel-heading tab-bg-dark-navy-blue">
                <ul class="nav nav-tabs">
                  <li> <a onclick="querycustomerinfo(${customerId})">基本信息</a> </li>
                  <li> <a href="">订单信息</a> </li>
                  <li> <a href="">优惠券</a> </li>
                  <li> <a onclick="toqueryattention(${customerId})" >商品关注</a> </li>
                  <li> <a onclick="querypoint(${customerId})">用户积分</a> </li>
                  <li class="active"> <a onclick="querypredeposit(${customerId})">用户预存款</a> </li>
                </ul>
              </header>
              <div class="panel-body">
              <div class="form-inline m-bot15 clearfix search-form">
                <form role="form" class="form-inline" id="queryform" >
                  <input type="hidden" id="customerId" name="customerId" value="${customerId}"/>
                  <div class="form-group">
                    <label class="control-label">交易单号</label>
                    <div class="clearfix">
                      <input type="text" class="form-control" name="transCode" value="">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">开始时间</label>
                    <div class="clearfix" style="width:189px">
                      <div class="input-group date search_datetime">
                        <input size="16" type="text" class="form-control" value="" name="startTime" id="startTime"
                               readonly="">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-info date-set"><i
                                                            class="icon-calendar"></i></button>
                                                </span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">结束时间</label>
                    <div class="clearfix" style="width:189px">
                      <div class="input-group date search_datetime">
                        <input size="16" type="text" class="form-control" value="" name="endTime" id="endTime"
                               readonly="">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-info date-set"><i
                                                            class="icon-calendar"></i></button>
                                                </span>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">&nbsp;</label>
                    <div class="clearfix">
                      <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索</button>
                    </div>
                  </div>
                </form>
              </div>
              <div class="adv-table clearfix">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th>交易类型</th>
                      <th>支出/收入</th>
                      <th>交易时间</th>
                      <th>交易单号</th>
                      <th>当前预存款</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                  </table>
                </div>
              <div style="width:142px; margin:20px auto"> <a class="btn btn-default btn-lg"  href="javascript:;" onclick="goback()">返回会员列表</a> </div>
                </div>
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
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/customer/customercommon.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/customer/customerpredeposit.js"></script>
</body>
</html>
