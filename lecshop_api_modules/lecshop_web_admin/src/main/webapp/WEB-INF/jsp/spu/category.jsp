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

  <!-- 引用头 -->
  <jsp:include page="../common/header.jsp"></jsp:include>

  <!-- 引用左侧 -->
  <jsp:include page="../common/left.jsp"></jsp:include>
  <input type="hidden" value="<%=basePath%>" id="basePath">

  <!--main content start-->
  <section id="main-content">
    <input type="hidden" id="curfcateId">
    <input type="hidden" id="curscateId">
    <section class="wrapper site-min-height">
      <div class="row">
        <div class="col-lg-12"> 
          <!--breadcrumbs start -->
          <ul class="breadcrumb">
            <li><a href="#">商品</a></li>
            <li><a href="#">商品配置</a></li>
            <li class="active">商品分类</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品分类 </header>
            <div class="panel-body">
              <div class="col-lg-4">
                <button type="button" class="btn btn-info btn-lg btn-block m-bot15" onclick="toaddfcate()"><i class="icon-plus"></i> 添加一级分类</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="fcate">
                    </ul>
                  </div>
                </section>
              </div>
              <div class="col-lg-4">
                <button type="button" class="btn btn-info btn-lg btn-block m-bot15" onclick="toaddscate()"><i class="icon-plus"></i> 添加二级分类</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="scate">
                    </ul>
                  </div>
                </section>
              </div>
              <div class="col-lg-4">
                <button type="button" class="btn btn-info btn-lg btn-block m-bot15" onclick="toaddtcate()" ><i class="icon-plus"></i> 添加三级分类</button>
                <section class="panel tasks-widget categorys_box">
                  <div class="panel-body">
                    <ul class="task-list" id="tcate">
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
<div class="modal fade" id="del_dialog">
  <input type="hidden" id="deleteCateId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">确定要删除此分类吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteCate()">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="addfcate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="savefcate">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" id="faname" maxlength="10">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits"  id="fasort" maxlength="3">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button  class="btn btn-success" type="button" onclick="savefcate()">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="addscate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="savescate">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" maxlength="10" id="saname">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>上级分类：</label>
            <div class="col-sm-9">
              <select  class="search-select" style="width:150px"  id="addscatecate">
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits"  maxlength="3" id="sasort">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button  class="btn btn-success" type="button" onclick="savescate()">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="addtcate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">添加分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="savetcate">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input type="text" class="form-control required specstr" id="taname" maxlength="10">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>上级分类：</label>
            <div class="col-sm-9">
              <select  class="search-select" style="width:150px"  id="addtcatecate">
                <option>所有</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input type="text" class="form-control required digits" id="tasort" maxlength="3">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类扣率：</label>
            <div class="col-sm-3 tooltips" data-placement="right" data-original-title="针对第三方商家进行分类扣率结算时使用">
              <div class="input-group ace_group_r">
                <input type="text" class="form-control required zeroOne" id="arate">
                <span class="input-group-addon">%</span> </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>商品类型：</label>
            <div class="col-lg-3">
              <select id="addType" class="search-select" style="width:150px" name="typeId">
              </select>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="savetcate()">保存</button>
      </div>
    </div>
  </div>
</div>


<div class="modal fade" id="editfcate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" method="post" id="updatefcate">
          <input type="hidden" name="catId" id="updateCateId">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input id="fcatName" type="text" class="form-control required specstr" maxlength="10">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input id="fcatsort" type="text" class="form-control required digits"  maxlength="3">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button  class="btn btn-success" type="button" onclick="updatefcate()">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="editscate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form" method="post" id="updatescate">
          <input type="hidden" name="catId" id="updatesCateId">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input id="scatName" type="text" class="form-control required specstr"  maxlength="10">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>上级分类：</label>
            <div class="col-sm-9">
              <select class="search-select" style="width:150px"   id="editscatecate">
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input id="scatsort" type="text" class="form-control required digits" name="catSort" maxlength="3">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="updatescate()">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="edittcate">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">修改分类</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal tasi-form"  id="updatetcate">
          <input type="hidden" name="catId" id="updatetCateId">
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类名称：</label>
            <div class="col-sm-9">
              <input id="tcatName" type="text" class="form-control required specstr"  maxlength="10">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>上级分类：</label>
            <div class="col-sm-9">
              <select id="tcateselect"  class="search-select" style="width:150px" >
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类排序：</label>
            <div class="col-sm-3">
              <input id="tcatesort" type="text" class="form-control required digits"   maxlength="3">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>分类扣率：</label>
            <div class="col-sm-3 tooltips" data-placement="right" data-original-title="针对第三方商家进行分类扣率结算时使用">
              <div class="input-group ace_group_r">
                <input id="caterate" type="text" class="form-control required zeroOne">
                <span class="input-group-addon">%</span> </div>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><span class="label_red">*</span>商品类型：</label>
            <div class="col-lg-3">
              <select id="edittype" class="search-select" style="width:150px" >
              </select>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="updatetcate()">保存</button>
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
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--common script for all pages--> 
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/spu/category.js"></script>

<script>
$('.del_btn').click(function() {
    $('#del_dialog').modal('show');
});

$('#type').chosen();
$('#selectfcate').chosen();
</script>
</body>
</html>
