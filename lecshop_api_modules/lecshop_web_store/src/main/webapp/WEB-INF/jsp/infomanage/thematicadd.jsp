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
<title>专题设置 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
<link href="<%=basePath%>/res/assets/summernote/summernote.css" rel="stylesheet">
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
            <li><a href="#">网站</a></li>
            <li><a href="#">模板管理</a></li>
            <li class="active">专题设置</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 添加专题 </header>
            <div class="panel-body">
              <form id="addForm">
                <div class="form-horizontal tasi-form set-form">
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>专题标题：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control required" id="title" value="">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">SEO关键字：</label>
                    <div class="col-sm-10">
                      <textarea rows="3" class="form-control" id="seoKeywords"></textarea>
                      <span class="help-block">默认为专题标题(最大字数75)</span> </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">SEO描述：</label>
                    <div class="col-sm-10">
                      <textarea rows="3" class="form-control" id="seoDesc"></textarea>
                      <span class="help-block">默认为专题标题(最大字数255)</span> </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">去除头尾部：</label>
                    <div class="col-sm-2">
                      <select class="form-control" id="hideHeadTail">
                        <option value="0">是</option>
                        <option value="1">否</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">是否启用：</label>
                    <div class="col-sm-2">
                      <select class="form-control" id="isUse">
                        <option value="0">是</option>
                        <option value="1">否</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">专题内容：</label>
                    <div class="col-sm-10">
                      <div class="summernote upcountStr" id="desc"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-5">
                      <button type="button" class="btn btn-success btn-lg" onclick="addThematic()">保存</button>
                      <button type="button" class="btn btn-default btn-lg" onclick="window.location.href = document.referrer">取消</button>
                    </div>
                  </div>
                </div>
              </form>
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

<!--script for this page only-->
<script src="<%=basePath %>/res/assets/summernote/summernote.min.js"></script>
<script src="<%=basePath %>/res/assets/summernote/summernote-zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/infomanage/thematicadd.js"></script>

<script>
$('.summernote').summernote({
	height: 500,
    tabsize: 2,
    lang: 'zh-CN',
    onImageUpload: function(files, editor, $editable) {
		sendFile(files,editor,$editable);
    }
});
</script>
</body>
</html>
