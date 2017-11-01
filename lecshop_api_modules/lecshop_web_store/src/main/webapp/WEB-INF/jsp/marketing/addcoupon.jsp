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
    <link href="<%=basePath %>res/css/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/select2.min.css" rel="stylesheet"/>
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
                        <li><a href="#">营销</a></li>
                        <li><a href="#">优惠券</a></li>
                        <li class="active">优惠券列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 添加优惠券</header>
                        <div class="panel-body">
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="form-horizontal tasi-form set-form">
                                        <form id="form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>优惠券名称：</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control required specstr" value="" name="name">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>生成张数：</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control required integer" value="" name="num">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>每人可领张数：</label>
                                                <div class="col-sm-2">
                                                    <input type="text" class="form-control required integer" value="" name="limitNum">
                                                </div>
                                            </div>
                                            <div class="form-group" id="selectType">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>选择规则：</label>
                                                <div class="col-sm-2">
                                                    <select class="form-control price_select required" name="type">
                                                        <option value="1">满减</option>
                                                        <option value="2">直降</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>开始时间：</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group date search_datetime">
                                                        <input size="16" type="text" value="" readonly class="form-control" name="startTime">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"><span class="label_red">*</span>结束时间：</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group date search_datetime">
                                                        <input size="16" type="text" value="" readonly class="form-control" name="endTime">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">优惠券描述：</label>
                                                <div class="col-sm-3">
                                                    <textarea rows="3" class="form-control" name="desc"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-10">
                                                    <button type="button" class="btn btn-success btn-lg" onclick="addSaveBtn()">保存</button>
                                                </div>
                                            </div>
                                        </form>
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
<div class="modal fade" id="success_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">优惠券添加成功</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">留此页面</button>
                <button data-dismiss="modal" class="btn btn-success" type="button" onclick="toSee()">去查看</button>
            </div>
        </div>
    </div>
</div>
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
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/marketing/addcoupon.js"></script>
</body>
</html>
