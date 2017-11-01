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
<title>商品类型 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/css/select2.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="<%=basePath %>/res/assets/data-tables/DT_bootstrap.css" />
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
            <li class="active">商品类型</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品类型 </header>
            <div class="panel-body">
              <div class="form-inline m-bot15 clearfix search-form">
                <form role="form" class="form-inline" id="queryform" >
                  <div class="form-group">
                    <label class="control-label">类型名称</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="name" >
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
                <button type="button" class="btn btn-primary" onclick="toaddtype()"><i class="icon-plus-sign"></i> 添加类型</button>
                <button type="button" class="btn btn-danger" onclick="todeletetypes()"><i class="icon-trash"></i> 批量删除</button>
              </div>
              <div class="adv-table clearfix">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th width="35"><input type="checkbox"></th>
                      <th>类型名称</th>
                      <th width="200">操作</th>
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
<div class="modal fade" id="type_dialog">
  <div class="modal-dialog" style="width:800px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加类型</h4>
      </div>
      <form  id="saveType">
        <div id="addType1" class="type_step">
          <div class="modal-body">
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>类型名称：</label>
                <div class="col-sm-9">
                  <input id="addname" type="text" class="form-control"   maxlength="20">
                </div>
              </div>
            </div>
          </div>

          <div class="modal-body">
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>选择规格：</label>
                <div class="col-sm-9">
                  <select id="addspecs" class="form-control select2" data-live-search="true" multiple="multiple"
                          name="specIds" style="width:100%">
                  </select>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_next" type="button" onclick="tostep2()">下一步</button>
          </div>
        </div>
        <div id="addType2" class="type_step" style="display:none">
          <div class="modal-body">
            <div class="adv-table editable-table ">
              <div class="btn-group m-bot15">
                <button type="button" onclick="addaAttribute(this)" class="btn btn-primary"> 添加属性 <i class="icon-plus"></i></button>
              </div>
              <table class="table table-striped table-hover table-bordered type_tab">
                <thead>
                <tr>
                  <th><span class="label_red">*</span>属性名</th>
                  <th><span class="label_red">*</span>属性值</th>
                  <th width="70"><span class="label_red">*</span>排序</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td class="form-group"><input type="text" class="form-control attributeName" maxlength="20"></td>
                  </td>
                  <td class="form-group">
                    <ul class="as-selections addselect">
                      <li class="as-original">
                        <input type="text" class="as-input" placeholder="输入属性值并回车添加..." autocomplete="off">
                      </li>
                    </ul>
                  </td>
                  <td class="form-group"><input type="text" class="form-control attributeSort"></td>
                  <td>&nbsp;</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_prev" type="button">上一步</button>
            <button class="btn btn-success" type="button" onclick="savetype()">保存</button>
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
        <h4 class="modal-title">修改类型</h4>
      </div>
      <form id="updateType">
        <input type="hidden" name="typeId" id="typeId" />
        <div id="updateType1" class="type_step">
          <div class="modal-body">
            <div class="alert alert-warning fade in">
              <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
              <strong>注意! </strong> 修改规格会影响已经存在的商品的规格，请谨慎修改。
            </div>
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>类型名称：</label>
                <div class="col-sm-9">
                  <input id="typeName" type="text" class="form-control"  maxlength="20">
                </div>
              </div>
            </div>
          </div>
          <div class="modal-body">
            <div class="form-horizontal tasi-form">
              <div class="form-group">
                <label class="col-sm-3 control-label"><span class="label_red">*</span>选择规格：</label>
                <div class="col-sm-9">
                  <select id="updatespecs" class="form-control select2" data-live-search="true" multiple="multiple"
                          name="specIds" style="width:100%">
                  </select>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_next" type="button" onclick="toupdatestep2()">下一步</button>
          </div>
        </div>
        <div id="updateType2" class="type_step" style="display:none">
          <div class="modal-body">
            <div class="alert alert-warning fade in">
              <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
              <strong>注意! </strong> 修改属性会影响已经存在的商品的属性，请谨慎修改。
            </div>
            <div class="adv-table editable-table ">
              <div class="btn-group m-bot15">
                <button type="button" class="btn btn-primary" onclick="addAttribute()"> 添加属性 <i class="icon-plus"></i></button>
              </div>
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th><span class="label_red">*</span>属性名</th>
                  <th><span class="label_red">*</span>属性值</th>
                  <th width="70"><span class="label_red">*</span>排序</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody id="updateattributes">
                </tbody>
              </table>
            </div>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
            <button class="btn btn-success type_prev" type="button">上一步</button>
            <button class="btn btn-success" type="button" onclick="updatetype()">保存</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<div class="modal fade" id="remove_dialog">
  <input type="hidden" id="removeTypeId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">系统提示</h4>
      </div>
      <div class="modal-body">确定要删除此类型吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteType()">确定</button>
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
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/select2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/spu/type.js"></script>
</body>
</html>
