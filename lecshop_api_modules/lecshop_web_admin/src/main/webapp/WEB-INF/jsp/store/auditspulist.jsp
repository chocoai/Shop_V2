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
<title></title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/jquery-multi-select/css/multi-select.css" />
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
            <li><a href="#">商品</a></li>
            <li><a href="#">商品管理</a></li>
            <li class="active">商品列表</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品列表 </header>
            <div class="panel-body">
              <form role="form" id="queryform" class="form-inline">
                <div class="form-inline m-bot15 clearfix search-form">
                  <div class="form-group">
                    <label class="control-label">商品编号</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="id" >
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="control-label">商品标题</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="name" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">店铺名称</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="storeName" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">一级分类</label>
                    <div class="clearfix">
                      <select class="form-control" name="firstCateId" id="firstCateId" onchange="chooseFCate(this);">
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">二级分类</label>
                    <div class="clearfix">
                      <select class="form-control" id="secondCateId" name="secondCateId" onchange="chooseTCate(this);">
                        <option value="0">选择分类</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">三级分类</label>
                    <div class="clearfix">
                      <select class="form-control" name="thirdCateId" id="thirdCateId">
                        <option value="0">选择分类</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">&nbsp;</label>
                    <div class="clearfix">
                      <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索</button>
                    </div>
                  </div>
                </div>
              </form>
              <div class="adv-table clearfix">
                <form id="deletespus">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th width="80">商品图片</th>
                      <th>商品标题</th>
                      <th style="min-width:90px">销售价</th>
                      <th style="min-width:90px">分类</th>
                      <th style="min-width:90px">品牌</th>
                      <th>店铺名称</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                  </table>
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
<!-- js placed at the end of the document so the pages load faster --> 
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/store/auditspulist.js"></script>
<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script>
$('.search-select').chosen();
</script>
</body>
</html>
