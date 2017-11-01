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
    <title>异常页面 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/assets/summernote/summernote.css" rel="stylesheet">
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
                        <li><a href="#">站点</a></li>
                        <li><a href="#">信息设置</a></li>
                        <li class="active">异常页面</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 异常页面</header>
                        <div class="panel-body">
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">404错误</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab2">500错误</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab3">400错误</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>页面标题：</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control required" value="" name="title">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">是否启用：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" name="isUse">
                                                            <option value="0">启用</option>
                                                            <option value="1">不启用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">页面描述：</label>
                                                    <div class="col-sm-10 ">
                                                        <div class="summernote" id="desc1"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="editSaveBtn(1)">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>页面标题：</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control required" value="" name="title">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">是否启用：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" name="isUse">
                                                            <option value="0">启用</option>
                                                            <option value="1">不启用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">页面描述：</label>
                                                    <div class="col-sm-10 ">
                                                        <div class="summernote" id="desc2"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="editSaveBtn(2)">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab3" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>页面标题：</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control required" value="" name="title">
                                                    </div>
                                                </div>
                                                <div class=" form-group">
                                                    <label class="col-sm-2 control-label">是否启用：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" name="isUse">
                                                            <option value="0">启用</option>
                                                            <option value="1">不启用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">页面描述：</label>
                                                    <div class="col-sm-10">
                                                        <div class="summernote" id="desc3"></div>

                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="editSaveBtn(3)">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<!-- Modal -->
<div class="modal fade" id="success_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">设置保存成功！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确定</button>
            </div>
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
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/site/exceptionpage.js"></script>
<script src="<%=basePath%>/res/assets/summernote/summernote.min.js"></script>
<script src="<%=basePath%>/res/assets/summernote/summernote-zh-CN.js"></script>
</body>
</html>
