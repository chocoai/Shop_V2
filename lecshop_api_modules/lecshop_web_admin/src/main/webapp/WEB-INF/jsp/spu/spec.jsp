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
<title>商品规格 - 乐商商城</title>
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
  <!-- 引用头 -->
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
            <li><a href="#">商品</a></li>
            <li><a href="#">商品配置</a></li>
            <li class="active">商品规格</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品规格 </header>
            <div class="panel-body">
              <div class="form-inline m-bot15 clearfix search-form">
                <form role="form" class="form-inline" id="queryform">
                  <div class="form-group">
                    <label class="control-label">规格名称</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="name">
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
              <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                <button type="button" id="add_btn" class="btn btn-primary" onclick="toaddspec()"><i class="icon-plus-sign"></i> 添加规格</button>
                <button type="button" class="btn btn-danger" onclick="todeletespcs()"><i class="icon-trash"></i> 删除</button>
              </div>
              <div class="adv-table clearfix">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th width="35"><input type="checkbox"></th>
                      <th>规格名称</th>
                      <th>规格别名</th>
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
<!-- 删除规格 -->
<div class="modal fade" id="del_dialog">
  <input type="hidden" id="deleteId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此规格吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deletespec()">确定</button>
      </div>
    </div>
  </div>
</div>

<!--添加规格-->
<div class="modal fade" id="add_dialog">
  <div class="modal-dialog" style="width:800px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加规格</h4>
      </div>
      <form id="addspec" >
        <div  class="type_step">
          <div class="modal-body">
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>规格名称：</label>
                <div class="col-sm-5 tooltips" data-placement="right" data-original-title="在商品详情页展示(建议两个字)">
                  <input id="name"  type="text" class="form-control specstr required" maxlength="4">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>规格别名：</label>
                <div class="col-sm-5 tooltips" data-placement="right" data-original-title="在后台添加商品类型时使用">
                  <input id="nickName" type="text" class="form-control specstr required" maxlength="20">
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_next" type="button" onclick="nextform(this)">下一步</button>
          </div>
        </div>
        <div class="type_step" style="display:none">
          <div class="modal-body">
            <div class="adv-table editable-table ">
              <div class="btn-group m-bot15">
                <button id="editable-parameter" type="button" onclick="addSpecValues(this,'specvalue')" class="btn btn-primary"> 添加规格值 <i class="icon-plus"></i></button>
              </div>
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>规格值名称</th>
                  <th>排序</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_prev" onclick="goback(this)" type="button">上一步</button>
            <button class="btn btn-success" type="button" onclick="savespec()">保存</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<div class="modal fade" id="update_dialog">
  <div class="modal-dialog" style="width:800px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改规格</h4>
      </div>
      <form id="updatespec">
        <input type="hidden"  id="specId"/>
        <div  class="type_step">
          <div class="modal-body">
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>规格名称：</label>
                <div class="col-sm-5 tooltips" data-placement="right" data-original-title="在商品详情页展示(建议两个字)">
                  <input id="uname" type="text" class="form-control required specstr"  maxlength="4">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>规格别名：</label>
                <div class="col-sm-5 tooltips" data-placement="right" data-original-title="在后台添加商品类型时使用">
                  <input id="unickname" type="text" class="form-control specstr required">
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_next" type="button" onclick="nextformupdate(this)">下一步</button>
          </div>
        </div>
        <div class="type_step" style="display:none">
          <div class="modal-body">
            <div class="adv-table editable-table ">
              <div class="btn-group m-bot15">
                <button id="update-parameter" onclick="addSpecValues(this,'updatespecvalue')" type="button" class="btn btn-primary"> 添加规格值 <i class="icon-plus"></i></button>
              </div>
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>规格值名称</th>
                  <th>排序</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="updatespecvalues">
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_prev" type="button" onclick="goback(this)">上一步</button>
            <button class="btn btn-success" type="button" onclick="updatespec()">保存</button>
          </div>
        </div>
      </form>
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
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/spu/spec.js"></script>

</body>
</html>
