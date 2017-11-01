<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <title>邮箱接口设置 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
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
                        <li><a href="#">系统</a></li>
                        <li><a href="#">接口管理</a></li>
                        <li class="active">邮箱接口设置</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 邮箱接口设置</header>
                        <div class="panel-body">
                            <div class="alert alert-warning fade in">
                                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                                <strong>注意！</strong> 邮箱接口，若修改不当，会影响邮件的接收和验证。
                            </div>
                            <div class="form-horizontal tasi-form set-form">
                                <form id="emailForm">
                                    <input type="hidden" name="id" value="">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>发信邮箱：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" value="" name="senderEmail" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>发信人：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" value="" name="senderName" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>SMTP服务器：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" value="" name="smtpServer" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>SMTP端口号：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" value="" name="smtpPort" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>SMTP账号：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" value="" name="username" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>SMTP密码：</label>
                                        <div class="col-sm-10">
                                            <input type="password" class="form-control required" value="" name="password" maxlength="20">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-sm-5">
                                            <button type="button" class="btn btn-success btn-lg" onclick="emailSaveBtn()">保存设置</button>
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
        <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a></div>
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
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/system/emilset.js"></script>
</body>
</html>
