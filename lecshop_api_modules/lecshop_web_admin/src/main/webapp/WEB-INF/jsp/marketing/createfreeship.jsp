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
    <title>创建促销 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css">
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
                        <li><a href="#">营销</a></li>
                        <li><a href="#">营销管理</a></li>
                        <li class="active">创建促销</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 包邮促销</header>
                        <div class="panel-body">
                            <div class="form-horizontal tasi-form set-form">
                                <input type="hidden" id="marketingId">

                                <form class="form-horizontal" id="savefreeship" method="post">

                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>促销名称：</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required" id="name" maxlength="40">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>包邮金额：</label>
                                        <div class="col-sm-4">
                                            <div class="fll" style="line-height: 34px">购物满</div>
                                            <div class="fll" style="margin:0 5px"><input type="text" class="form-control required isNumber" style="width:100px" id="fullPrice"></div>
                                            <div class="fll" style="line-height: 34px">元包邮</div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>开始时间：</label>
                                        <div class="col-sm-3" style="width:250px">
                                            <div class="input-group date search_datetime">
                                                <input size="16" type="text" id="startTime" name="startTime" readonly class="form-control">
                                                <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>结束时间：</label>
                                        <div class="col-sm-3" style="width:250px">
                                            <div class="input-group date search_datetime">
                                                <input size="16" type="text" id="endTime" name="endTime" readonly class="form-control">
                                                <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">促销描述：</label>
                                        <div class="col-sm-10">
                                            <textarea rows="3" class="form-control" id="desc"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-sm-10">
                                            <button type="button" onclick="saveFreeShip()" class="btn btn-success btn-lg">保存</button>
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
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=basePath %>/res/js/marketing/createfreeship.js"></script>
</body>
</html>
