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
<title>促销列表 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

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
  <input type="hidden" value="" id="searchType">

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
            <li><a href="#">营销</a></li>
            <li><a href="#">营销管理</a></li>
            <li class="active">促销列表</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 促销列表 </header>
            <div class="panel-body">
              <div class="panel">
                <header class="panel-heading tab-bg-dark-navy-blue ">
                  <ul class="nav nav-tabs">
                    <li class="active"> <a data-toggle="tab" href="javascript:;" onclick="changeType(0)">商品促销列表</a> </li>
                    <li class=""> <a data-toggle="tab" href="javascript:;" onclick="changeType(2)">团购促销列表</a> </li>
                    <li class=""> <a data-toggle="tab" href="javascript:;" onclick="changeType(3)">抢购促销列表</a> </li>
                  </ul>
                </header>
                <div class="panel-body">
                  <div class="tab-content">
                    <div id="tab1" class="tab-pane active">
                      <div class="form-inline m-bot15 clearfix search-form">
                        <form role="form" class="form-inline" id="queryform">
                        <div class="form-group">
                          <label class="control-label">促销名称</label>
                          <div class="clearfix">
                            <input type="text" class="form-control" name="name">
                          </div>
                        </div>
                          <div class="form-group" id="marketingType">
                            <label class="control-label">促销类型</label>
                            <div class="clearfix">
                              <select class="form-control" name="type">
                                <option value="0">选择类型</option>
                                <option value="1">直降促销</option>
                                <option value="4">满减促销</option>
                                <option value="5">满折促销</option>
                                <option value="6">包邮促销</option>
                              </select>
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
                      <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                        <div class="btn-group" id="addbtn">
                          <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" type="button"><i class="icon-plus-sign"></i> 添加促销 <span class="caret"></span></button>
                            <ul role="menu" class="dropdown-menu">
                                <li><a href="<%=basePath%>/toaddfallmarketing.htm">直降促销</a></li>
                                <li><a href="<%=basePath%>/toaddfulldownmarketing.htm">满减促销</a></li>
                                <li><a href="<%=basePath%>/toaddfulldiscount.htm">满折促销</a></li>
                            </ul>
                        </div>
                          <a id="addGroup" href="<%=basePath%>/toaddgroupmakerting.htm" class="btn btn-primary" style="display: none"><i class="icon-plus-sign"></i> 添加团购</a>
                          <a id="addPanic" href="<%=basePath%>/toaddpanicmarketing.htm" class="btn btn-primary" style="display: none"><i class="icon-plus-sign"></i> 添加抢购</a>
                        <button type="button" class="btn btn-danger"  onclick="todeleteall()"><i class="icon-trash"></i> 删除</button>
                      </div>
                      <div class="adv-table clearfix">
                        <table  class="display table table-bordered table-striped" id="dataTable">
                          <thead>
                            <tr>
                              <th width="35"><input type="checkbox"></th>
                              <th>促销名称</th>
                              <th style="min-width: 80px">营销规则</th>
                              <th>描述</th>
                              <th>开始时间</th>
                              <th>结束时间</th>
                              <th>操作</th>
                            </tr>
                          </thead>
                          <tbody>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
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
<div class="modal fade" id="del_dialog">
  <input type="hidden" id="deleteId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">系统提示</h4>
      </div>
      <div class="modal-body">确定要删除此促销吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteone()">确定</button>
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
<!--common script for all pages-->


<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/marketing/marketlist.js"></script>

<!--script for this page only-->
</body>
</html>
