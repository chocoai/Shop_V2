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
    <link rel="shortcut icon" href="<%=basePath%>/res/img/favicon.png">
    <title>设置分销 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath%>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath%>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
    <!-- Custom styles for this template -->
    <link href="<%=basePath%>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath%>/res/css/style-responsive.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath%>/res/js/html5shiv.js"></script>
    <script src="<%=basePath%>/res/js/respond.min.js"></script>
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
                        <li><a href="#">分销</a></li>
                        <li><a href="#">分销管理</a></li>
                        <li class="active">微信支付设置信息</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 微信支付设置信息</header>
                        <div class="panel-body">
                            <div class="form-horizontal tasi-form set-form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">微信首页地址：</label>
                                    <div class="col-sm-10 set_box">
                                        <p class="form-control-static" id="sitUrl">${url}</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">微信首页二维码：</label>
                                    <div class="col-sm-10 set_box">
                                        <p class="form-control-static">
                                            <image width="120" height="120" src="<%=basePath %>/createqrcode.htm"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">上传凭证文件：</label>
                                    <div class="col-sm-10 set_box">
                                        <iframe name="fileIframe" style="display:none"></iframe>
                                        <form action="fileupload.htm" enctype="multipart/form-data" method="post" id="fileForm" target="fileIframe">
                                            <span class="btn btn-white btn-file">
                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择凭证文件</span>
                                                <span class="fileupload-exists" style="display: none"><i class="icon-undo"></i> 重新上传</span>
                                                <input type="file" class="default" name="fileUpload" onchange="changeTip()">
                                            </span>
                                            <button type="button" class="btn btn-primary" style="margin-left: 18px;" onclick="toSubmit()">提交上传</button>
                                        </form>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-5">
                                        <a type="button" class="btn btn-success btn-lg save_btn" href="<%=basePath%>/topayset.htm?firstMenus=7&secondMenus=11&thirdMenus=15">去修改微信支付信息</a>
                                    </div>
                                </div>
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
        <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a></div>
    </footer>
    <!--footer end-->
</section>
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>/res/js/jquery.js"></script>
<script src="<%=basePath%>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath%>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath%>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath%>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath%>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath%>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath%>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/distribution/wechatpayinfo.js"></script>
</body>
</html>
