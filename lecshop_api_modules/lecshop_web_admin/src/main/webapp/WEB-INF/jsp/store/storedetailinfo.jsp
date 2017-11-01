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
    <title>商家详细信息 - 乐商商城</title>
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
    <input type="hidden" value="${storeId}" id="storeId">
    <input type="hidden" value="${status}" id="status">
    <!-- 引用头 -->
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
                        <li><a href="#">店铺</a></li>
                        <li><a href="#">商家详细信息</a></li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel" id="print_box">
                        <header class="panel-heading"> 商家详细信息</header>
                        <div class="panel-body">
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 商家信息</header>
                                <table class="table">
                                    <tbody id="storeInfo">
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 签约分类</header>
                                <ul class="class_box clearfix" id="category">

                                </ul>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 签约品牌</header>
                                <ul class="class_box clearfix" id="brand">
                                </ul>
                            </div>
                            <div class="panel" style="border:solid 1px #eee">
                                <header class="panel-heading" style="background:#eee"> 申请自定义品牌</header>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center;">名称</th>
                                        <th style="text-align: center;">品牌LOGO</th>
                                        <th style="text-align: center;">证书</th>
                                    </tr>
                                    </thead>
                                    <tbody id="myselfBrand">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                    <div style="width:142px; margin:20px auto"><a class="btn btn-default btn-lg" href="javascript:window.location.href = document.referrer;">返回商家列表</a></div>
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
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/store/storedetailinfo.js"></script>
<script>
    $('.print').click(function () {
        var printData = document.getElementById("print_box").innerHTML;
        window.document.body.innerHTML = printData;
        window.print();
    });
</script>
</body>
</html>
