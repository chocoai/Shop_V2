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
                                <li data-step="1" class="complete"><span class="step">1</span> <span class="title">在线协议</span></li>
                                <li data-step="2" class="complete"><span class="step">2</span> <span class="title">商家信息</span></li>
                                <li data-step="3" class="complete"><span class="step">3</span> <span class="title">店铺开店</span></li>
                            </ul>
                            <c:if test="${result==1}">
                                <div class="success_main">
                                    <h3>已经收到贵公司提交的在线入驻审核信息<br/>
                                        等待我们分配审核人员；<br/>
                                        请及时登录系统查看入驻审核情况。</h3>
                                    <p><a class="btn btn-info" href="store_tologin.htm">返回店铺登录页面</a></p>
                                </div>
                            </c:if>
                            <c:if test="${result==3}">
                                <div class="success_main error_main">
                                    <h3>贵公司的店铺申请材料未通过审核<br/>
                                        打回原因：${reason}</h3>
                                    <p><a class="btn btn-info" href="store_toopenprotocol.htm">重新填写申请资料</a></p>
                                </div>
                            </c:if>
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
<script type="text/javascript" src="<%=basePath %>/res/js/openstore/submitresults.js"></script>
</body>
</html>
