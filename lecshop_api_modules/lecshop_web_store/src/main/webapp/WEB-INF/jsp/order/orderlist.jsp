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
<title>订单列表 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css" />
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
            <li class="active">订单列表</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 订单列表 </header>
              <div class="panel-body">
                  <div class="btn-group btn-group-justified list_tab" style="margin-bottom:30px">
                      <a onclick="changetab(this)" search-type=""
                         class="btn btn-lg btn-default btn-primary"
                         href="javascript:;">全部</a>
                      <a onclick="changetab(this)" search-type="1"
                         class="btn btn-lg btn-default"
                         href="javascript:;">待付款</a>
                      <a onclick="changetab(this)" search-type="2"
                         class="btn btn-lg btn-default"
                         href="javascript:;">待发货</a>
                      <a onclick="changetab(this)" search-type="3"
                         class="btn btn-lg btn-default"
                         href="javascript:;">待收货</a>
                      <a onclick="changetab(this)" search-type="4"
                         class="btn btn-lg btn-default"
                         href="javascript:;">已完成</a>
                      <a onclick="changetab(this)" search-type="5"
                         class="btn btn-lg btn-default"
                         href="javascript:;">已关闭</a>
                  </div>
                  <div class="form-inline m-bot15 clearfix search-form">
                      <form id="queryform">
                          <input type="hidden" name="status" value="" id="searchType">
                          <div class="form-group">
                              <label class="control-label">订单号</label>
                              <div class="clearfix">
                                  <input type="text" class="form-control" name="orderCode">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label">用户名</label>
                              <div class="clearfix">
                                  <input type="text" class="form-control" name="customerName">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label">开始时间</label>
                              <div class="clearfix" style="width:189px">
                                  <div class="input-group date search_datetime">
                                      <input size="16" type="text" readonly class="form-control" name="startTime">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label">结束时间</label>
                              <div class="clearfix" style="width:189px">
                                  <div class="input-group date search_datetime">
                                      <input size="16" type="text" readonly class="form-control" name="endTime">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label">&nbsp;</label>
                              <div class="clearfix">
                                  <button type="button" onclick="refreshDataTable()" class="btn btn-info">搜索</button>
                              </div>
                          </div>
                      </form>
              </div>
              <div class="adv-table clearfix">
                <table class="display table table-bordered table-striped" id="dataTable">
                  <thead>
                  <tr>
                    <th width="35"><input type="checkbox"></th>
                    <th>订单号</th>
                    <th>买家</th>
                    <th width="140">下单时间</th>
                    <th>订单总额</th>
                    <th>订单状态</th>
                    <th width="50">来源</th>
                    <th>操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
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

<div class="modal fade" id="cancel_dialog" aria-hidden="true" style="display: none;">
    <input type="hidden" id="cancelId">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">订单不能随意中断，你确定要修改吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="cancelOrder()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="confirm_dialog" aria-hidden="true" style="display: none;">
    <input type="hidden" id="confirmId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">系统提示</h4>
      </div>
      <div class="modal-body">确认付款后订单状态将修改为待发货，你确定要修改吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="confirOrder()">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="modify_dialog" aria-hidden="true" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">订单改价</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="update_price_form">
            <input type="hidden" id="updatePriceId">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>优惠的金额：</label>
            <div class="col-sm-3">
              <div class="input-group ace_group"> <span class="input-group-addon">¥</span>
                <input type="text" class="form-control required money"  id="updatePrice" >
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>原因：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required" maxlength="200" id="reason">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="modifyprice()">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="send_dialog" aria-hidden="true" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">订单发货</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="deliveryform">
            <input type="hidden" id="deliverId">
          <div class="form-group">
            <label class="col-sm-3 control-label">物流公司：</label>
            <div class="col-sm-9">
              <p class="form-control-static" id="expressName"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>运单号：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required" maxlength="20" id="expressNo">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button id="deliverybtn" class="btn btn-success" type="button" onclick="delivery()">发货</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--this page plugins-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/order/orderlist.js"></script>

</body>
</html>
