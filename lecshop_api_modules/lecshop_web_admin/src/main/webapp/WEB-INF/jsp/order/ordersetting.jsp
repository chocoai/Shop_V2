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
<link rel="shortcut icon" href="<%=basePath%>/res/img/favicon.png">
<title>订单设置 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
  <link href="<%=basePath%>/res/assets/summernote/summernote.css" rel="stylesheet"/>
  <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

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
  <!-- 引用上侧 -->
  <jsp:include page="../common/header.jsp"></jsp:include>
  <!-- 引用左侧 -->
  <jsp:include page="../common/left.jsp"></jsp:include>

  <input type="hidden" value="<%=basePath%>" id="basePath">

  <!--main content start-->
  <section id="main-content">
    <section class="wrapper site-min-height">
      <div class="row">
        <div class="col-lg-12"> 
          <!--breadcrumbs start -->
          <ul class="breadcrumb">
            <li><a href="#">系统</a></li>
            <li><a href="#">系统管理</a></li>
            <li class="active">订单设置</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 订单设置 </header>
            <div class="panel-body">
              <div class="form-horizontal tasi-form set-form">
                <form id="orderSettingForm">
                  <input type="hidden" id="orderSettingId"/>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">是否允许退单：</label>
                    <div class="col-sm-2 set_box">
                        <select class="form-control" id="allowBack">
                          <option value="0">是</option>
                          <option value="1">否</option>
                        </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">订单自动收货时间：</label>
                    <div class="col-sm-3 set_box tooltips" data-placement="right" data-original-title="以天为单位">
                        <input type="text" class="form-control required digits" id="aotuConfirm" value="">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">货到付款：</label>
                    <div class="col-sm-2">
                      <select class="form-control" id="cashonDelivery">
                        <option value="0">是</option>
                        <option value="1">否</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">退货说明：</label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-primary" id="returnDesc" onclick="editReturn()">查看并修改</button>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">退款说明：</label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-primary" id="refundsDesc" onclick="editRefunds()">查看并修改</button>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveOrderSetting()">保存设置</button>
                    </div>
                  </div>
                </form>
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
<!-- 查看并修改退货说明 -->
<div class="modal fade" id="return_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">退货说明</h4>
      </div>
      <div class="modal-body">
          <div class="summernote upcountStr" id="returnDescs"></div>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="saveReturn()">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- 查看并修改退款说明 -->
<div class="modal fade" id="refund_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">退款说明</h4>
      </div>
      <div class="modal-body">
          <div class="summernote upcountStr" id="refunds"></div>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="saveRefunds()">确定</button>
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
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>

<!--script for this page only-->
<script src="<%=basePath %>/res/assets/summernote/summernote.min.js"></script>
<script src="<%=basePath %>/res/assets/summernote/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/res/js/order/ordersetting.js"></script>
</body>
</html>
