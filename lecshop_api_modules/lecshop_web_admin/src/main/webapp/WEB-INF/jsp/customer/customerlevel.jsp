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
<title>会员等级 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
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
            <li><a href="#">会员</a></li>
            <li><a href="#">会员管理</a></li>
            <li class="active">会员等级</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 会员等级 </header>
            <div class="panel-body">
              <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                <button id="add_member" type="button" class="btn btn-primary" onclick="toadd()"><i class="icon-plus-sign"></i> 添加</button>
              </div>
              <div class="adv-table clearfix">
                <table  class="display table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>等级名称</th>
                      <th>所需消费金额</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${customerLevels}" var="customerLevel" >
                    <tr>
                      <td>${customerLevel.name}</td>
                      <td>${customerLevel.minMoney}~${customerLevel.maxMoney}</td>
                      <td class="operation_box">
                        <button class="btn btn-primary btn-xs edit_btn" onclick="toupdate(${customerLevel.id})"><i class="icon-pencil"></i> 编辑</button>
                        <button type="button" class="btn btn-danger btn-xs del_btn" onclick="todelete(${customerLevel.id})"><i class="icon-trash"></i> 删除</button>
                      </td>
                    </tr>
                  </c:forEach>
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
<div class="modal fade" id="remove_dialog" aria-hidden="true" style="display: none;">
  <input type="hidden" id="deleteId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">系统提示</h4>
      </div>
      <div class="modal-body">确认要删除会员等级吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteLevel()">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="add_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <form id="addCustomerLevel">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">添加会员等级</h4>
        </div>
        <div class="modal-body">
          <div class="form-horizontal tasi-form">
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>等级名称：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control required specstr" maxlength="10" id="levelName">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>所需消费金额：</label>
              <div class="col-sm-9">
                <div class="row">
                  <div class="col-lg-4">
                    <input type="text" class="form-control required digits" id="minMoney" >
                  </div>
                  <div class="col-lg-1" style="padding:0; width:2%"> ~</div>
                  <div class="col-lg-4 tooltips" data-placement="right" data-original-title="会员消费金额达到此标准最低分值后会自动升级为当前等级">
                    <input type="text" class="form-control required digits" id="maxMoney" >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
          <button class="btn btn-success" type="button" onclick="addCustomerLevel()">确定</button>
        </div>
      </form>
    </div>
  </div>
</div>

<div class="modal fade" id="update_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <form  id="updatepointform">
        <input type="hidden" id="levelId">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">修改会员等级</h4>
        </div>
        <div class="modal-body">
          <div class="form-horizontal tasi-form">
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>等级名称：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control required specstr" maxlength="10" id="ulevelName">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>所需消费金额：</label>
              <div class="col-sm-9">
                <div class="row">
                  <div class="col-lg-4">
                    <input type="text" class="form-control required digits" id="uminMoney" >
                  </div>
                  <div class="col-lg-1" style="padding:0; width:2%"> ~</div>
                  <div class="col-lg-4 tooltips" data-placement="right" data-original-title="会员消费金额达到此标准最低分值后会自动升级为当前等级">
                    <input type="text" class="form-control required digits" id="umaxMoney" >
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
          <button class="btn btn-success" type="button" onclick="updatelevel()">确定</button>
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
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--common script for all pages--> 
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/customer/customerlevel.js"></script>
<!--script for this page only--> 
<script>
</script>
</body>
</html>
