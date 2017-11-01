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
<title>地区管理 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/css/tasks.css" rel="stylesheet">
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
            <li><a href="#">订单</a></li>
            <li><a href="#">地区管理</a></li>
            <li class="active">地区管理</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 地区管理 </header>
            <div class="panel-body">
              <div class="col-lg-4">
                <input type="hidden" id="proid">
                <button type="button" onclick="toaddprovince()" class="btn btn-info btn-lg btn-block m-bot15 add_cate"><i class="icon-plus"></i> 添加省份</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="province">
                    </ul>
                  </div>
                </section>
              </div>
              <div class="col-lg-4">
                <input type="hidden" id="cityid">
                <button type="button" class="btn btn-info btn-lg btn-block m-bot15 add_cate" onclick="toaddcity()"><i class="icon-plus"></i> 添加城市</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="city">
                    </ul>
                  </div>
                </section>
              </div>
              <div class="col-lg-4">
                <input type="hidden" id="districtid">
                <button type="button" class="btn btn-info btn-lg btn-block m-bot15 add_cate" onclick="tooadddist()"><i class="icon-plus"></i> 添加区县</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="district">
                    </ul>
                  </div>
                </section>
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
<div class="modal fade" id="deldist_dialog">
  <input type="hidden" id="deldistid">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此地区吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deldist()">确定</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="delcity_dialog">
  <input type="hidden" id="delcityid">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此地区吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="delcity()">确定</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="delpro_dialog">
  <input type="hidden" id="delproid">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此地区吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="delpro()">确定</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="addprovince_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加省份</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="saveprovince">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>省份名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" id="addressName"  maxlength="20">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="pasort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="saveprovince()">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="addcity_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加城市</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="savecity" >
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>城市名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr"  id="cityname" maxlength="20">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>所属省份：</label>
            <div class="col-sm-9">
              <select class="search-select" style="width:150px" id="allpro" name="parentId">
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits"  id="casort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="savecity()">保存</button>
      </div>
    </div>
  </div>
</div>




<div class="modal fade" id="adddist_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加城市</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="savedist" >
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>区县名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" maxlength="20" id="distname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>所属城市：</label>
            <div class="col-sm-9">
              <select class="search-select" style="width:150px" id="allcitys" >
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="dasort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button  class="btn btn-success" type="button" onclick="savedist()">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="update_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改地区</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="updateform">
          <input type="hidden"  id="updateid">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>地区名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" maxlength="20" id="updatename">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="updatesort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="updatepro()">保存</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="update_city_dia">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改地区</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="update_city">
          <input type="hidden"   id="updatecid">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>地区名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" maxlength="20" id="updatecname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="updatecsort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="updateCity()">保存</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="update_district_dia">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改地区</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" id="update_distric">
          <input type="hidden"  id="updatedid">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>地区名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" maxlength="20" id="updatedname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="updatedsort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="updateDist()">保存</button>
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
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--common script for all pages--> 
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/order/area.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>

<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script>
$('.del_btn').click(function() {
    $('#del_dialog').modal('show');
});
$('body').on('click','.task-list li',function() {
    $(this).addClass('active').siblings().removeClass();
});
</script>
</body>
</html>
