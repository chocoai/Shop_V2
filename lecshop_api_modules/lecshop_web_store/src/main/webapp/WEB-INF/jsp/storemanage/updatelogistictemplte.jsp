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
  <title>物流模板设置 - 乐商商城</title>
  <!-- Bootstrap core CSS -->
  <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <!--external css-->
  <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
  <link href="<%=basePath %>/res/css/zTreeStyle.css" rel="stylesheet">
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
  <input type="hidden" value="${id}" id="templateId">
  <!-- 引用上侧 -->
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
            <li><a href="#">系统</a></li>
            <li><a href="#">配送管理</a></li>
            <li class="active">物流模板设置</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 物流模板设置 </header>
            <div class="panel-body">
              <div class="form-horizontal tasi-form set-form">
                <form class="form-horizontal" id="updatetemplate">
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>模板名称：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control required" id="name" maxlength="7">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>运费承担：</label>
                    <div class="col-sm-2">
                      <select class="form-control freight_select" id="freightBear">
                        <option value="0">买家承担运费</option>
                        <option value="1">卖家承担运费</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>计价方式：</label>
                    <div class="col-sm-2">
                      <select class="form-control pricing_select" id="pricintMethod">
                        <option value="0">按件计价</option>
                        <option value="1">按重计价</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>运送方式：</label>
                    <div class="col-sm-2">
                      <select class="form-control"  id="company">
                      </select>
                    </div>
                  </div>
                  <div class="form-group freight_form">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>运费方式：</label>
                    <div class="col-sm-10">
                      <div class="panel" style="border:solid 1px #eee">
                        <header class="panel-heading clearfix" style="background:#eee">
                          <div class="pull-left freight_box" id="defaultShippingMethod" style="float:left"> 默认运费：

                          </div>

                          <div class="pull-right">
                            <button id="add_btn" type="button" class="btn btn-primary btn-sm" onclick="toadd()">添加收货地区</button>
                          </div>
                        </header>
                        <table class="table table-bordered">
                          <thead>
                          <tr>
                            <th width="50%">收货地区</th>
                            <th class="unit_th" id="unit_th">首件</th>
                            <th>首费(元)</th>
                            <th class="unit_th" id="unit_th_plu">续件</th>
                            <th>续费(元)</th>
                          </tr>
                          </thead>
                          <tbody id="tempdetail">

                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-success btn-lg" onclick="updatetemplate()">保存</button>
                      <button type="button" class="btn btn-default btn-lg" onclick="back()">取消</button>
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
<div class="modal fade" id="del_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此收货地址吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="add_dialog">
  <div class="modal-dialog" style="width:750px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加收货地区</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="addshipping">
          <div class="form-group">
            <label class="col-sm-3 control-label">收货地区：</label>
            <div class="col-sm-9">
              <div class="city_area pull-left">
                <ul id="treeDemo" class="ztree"></ul>
              </div>
              <div class="city_choose pull-left">
                <button type="button" class="btn btn-default" onclick="choosecitys()">&gt;&gt;</button>
              </div>
              <div class="city_choosed pull-left">
                <p></p><input type="hidden" value="" class="required" name="tempCity" id="tempCity"/>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">首件个数：</label>
            <div class="col-sm-2">
              <input type="text" class="form-control required digits" value="1" id="firstnum">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">首件运费：</label>
            <div class="col-sm-2">
              <input type="text" class="form-control required number" value="0" id="firstfee">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">续件个数：</label>
            <div class="col-sm-2">
              <input type="text" class="form-control required digits" value="1" id="contnum">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label">续件运费：</label>
            <div class="col-sm-2">
              <input type="text" class="form-control required number" value="0" id="contfee">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="toaddtemp()">保存</button>
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
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>

<script src="<%=basePath %>/res/js/storemanage/updatelogistictemplte.js"></script>

<!--script for this page only-->
<script src="<%=basePath %>/res/js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.ztree.excheck-3.5.min.js"></script>
</body>
</html>
