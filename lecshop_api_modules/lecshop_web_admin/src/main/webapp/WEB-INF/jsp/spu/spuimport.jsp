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
<title>商品列表 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
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
            <li class="active">商品导入</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品导入 </header>
            <div class="panel-body">
              <div class="form-inline m-bot15 clearfix search-form">
                <form role="form" class="form-inline"
                      id="queryform">
                  <div class="form-group">
                    <label class="control-label">商品标题</label>
                    <div class="clearfix">
                      <input type="text" class="form-control" name="name">
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
                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                <button type="button" class="btn btn-info" onclick="window.open('<%=basePath %>/template/spuimport.xls')"><i class="icon-cloud-download"></i> 下载模板</button>
                <button id="import_goods" onclick="toimportspu()" type="button" class="btn btn-info"><i class="icon-download-alt"></i> 导入商品</button>
              </div>
              <div class="adv-table clearfix">
                <form role="form" class="form-inline">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th width="35"><input type="checkbox"></th>
                      <th>商品标题</th>
                      <th style="min-width:90px">销售价</th>
                      <th style="min-width:90px">发布状态</th>
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
<!-- Modal -->
<div class="modal fade" id="import_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">导入商品</h4>
      </div>
        <div class="modal-body">
          <form role="form"  id="importForm" method="post"
                enctype="multipart/form-data">
          <div class="form-horizontal tasi-form">
            <div class="form-group" style="margin:0">
              <label class="control-label col-md-3">上传数据文件：</label>
              <div class="controls col-md-9">
                <div class="fileupload fileupload-new" data-provides="fileupload"> <span class="btn btn-white btn-file"> <span
                        class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span> <span
                        class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                <input id="importfile" type="file" class="default required" name="spuimport"/>
                </span> <span class="fileupload-preview" style="margin-left:5px;"></span></div>
              </div>
            </div>
          </div>
          </form>
        </div>
        <div class="modal-footer">
          <button  data-dismiss="modal" class="btn btn-default" type="button">取消</button>
          <button class="btn btn-success" type="button" onclick="importspu()">确定</button>
        </div>
    </div>
  </div>
</div>

<!-- 删除商品导入 -->
<div class="modal fade" id="del_dialog">
  <input type="hidden" id="deleteId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此商品导入吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteSpuImport()">确定</button>
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
<script src="<%=basePath %>/res/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/spu/spuimport.js"></script>

</body>
</html>
