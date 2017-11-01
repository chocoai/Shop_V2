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
<title>积分设置 - 乐商商城</title>
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
            <li><a href="#">会员</a></li>
            <li><a href="#">会员管理</a></li>
            <li class="active">积分设置</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 积分设置 </header>
            <div class="panel-body">
              <div class="alert alert-warning fade in">
                <button data-dismiss="alert" class="close close-sm" type="button"> <i class="icon-remove"></i> </button>
                <strong>注意！</strong> 积分设置，若修改不当，会造成用户对积分的获得和使用的混乱。 </div>
              <div class="form-horizontal tasi-form set-form">
                <form class="form-horizontal" id="pointform">
                    <input type="hidden" id="id" value="${pointset.id}">
                  <div class="form-group">
                    <label class="col-sm-2 control-label">是否开启：</label>
                      <div class="col-sm-1 set_box">
                          <select class="form-control" id="isOpen">
                              <option value="1" <c:if test="${pointset.isOpen=='1'}">selected</c:if>>启用</option>
                              <option value="0" <c:if test="${pointset.isOpen=='0'}">selected</c:if>>不启用</option>
                          </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱验证积分：</label>
                    <div class="col-sm-1">
                      <input type="text" class="form-control required digits" maxlength="7" id="emailPoint" value="${pointset.emailPoint}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">手机验证积分：</label>
                    <div class="col-sm-1">
                      <input type="text" class="form-control required digits" id="mobilePoint"  maxlength="7" value="${pointset.mobilePoint}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">发表评论积分：</label>
                    <div class="col-sm-1">
                      <input type="text" class="form-control required digits" id="commentPoint"  maxlength="7" value="${pointset.commentPoint}">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">积分使用规则：</label>
                    <div class="col-sm-10">
                      每消费<input style="width: 100px;display: inline-block;margin: 0 5px;"  maxlength="7" type="text" class="form-control required digits" id="usePoint" value="${pointset.usePoint}">积分,可抵消<input style="width: 100px;display: inline-block;margin: 0 5px;" type="text" class="form-control required digits"  maxlength="7" id="offsetMoney" value="${pointset.offsetMoney}">元
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-success btn-lg save_btn" onclick="updatePointset()">保存设置</button>
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
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/customer/pointset.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>

<!--script for this page only-->
</body>
</html>
