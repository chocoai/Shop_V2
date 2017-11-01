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
    <title>创建店铺 - 乐商商城</title>
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
    <jsp:include page="../common/openstoreheader.jsp"></jsp:include>
    <div class="container">
        <!--main content start-->
        <section class="wrapper">
            <div class="row"></div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <div class="panel-body">
                            <ul class="steps">
                                <li data-step="1" class="active"><span class="step">1</span> <span class="title">在线协议</span></li>
                                <li data-step="2" class=""><span class="step">2</span> <span class="title">商家信息</span></li>
                                <li data-step="3" class=""><span class="step">3</span> <span class="title">店铺开店</span></li>
                            </ul>
                            <p class="help-block">请仔细阅读以下商城服务协议及规则：</p>
                            <div style=" height: 500px; overflow-y:auto;overflow-x:hidden;" id="protocol">
                            </div>
                            <label style="margin-top:15px">
                                <input type="checkbox" name="isCheck" style="vertical-align:text-top">&nbsp;&nbsp;我已阅读并同意以上协议
                            </label>
                            <div style="width:100%; text-align:center; margin:20px 0">
                                <a id="next_btn" class="btn btn-info btn-lg">下一步</a>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <!--main content end-->
        <!--footer start-->
        <footer class="site-footer">
            <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a></div>
        </footer>
        <!--footer end-->
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="tip_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">请阅读并同意协议！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common-scripts.js"></script>
<!--script for this page only-->
<script src="<%=basePath%>/res/js/openstore/openprotocol.js"></script>
</body>
</html>
