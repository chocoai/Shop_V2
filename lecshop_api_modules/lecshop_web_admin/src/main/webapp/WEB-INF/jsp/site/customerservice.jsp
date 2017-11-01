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
    <title>在线客服 - 乐商商城</title>
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
                        <li><a href="#">站点</a></li>
                        <li><a href="#">聊天工具</a></li>
                        <li class="active">在线客服</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 在线客服</header>
                        <div class="panel-body">
                            <div class="form-horizontal tasi-form set-form">
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
                                    <label class="col-sm-2 control-label">客服栏标题：</label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="id" value="">
                                        <input type="text" class="form-control" name="title">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">客服列表：</label>
                                    <div class="col-sm-10">
                                        <button id="add_service" type="button" onclick="addCustomerServiceBtn()" class="btn btn-primary tooltips" data-placement="right"
                                                data-original-title="最多添加20条，名称不超过6个汉字"><i
                                                class="icon-plus-sign"></i> 添加客服
                                        </button>
                                        <table class="table table-bordered table-striped table-condensed" style="margin-top:10px">
                                            <thead>
                                            <tr>
                                                <th>客服QQ</th>
                                                <th>客服名称</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="tbodyContainer">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-5">
                                        <button type="button" class="btn btn-success btn-lg" onclick="editCustomerServiceBtn()">保存</button>
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
<!-- modal -->
<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加客服</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客服QQ：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="qq">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客服名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="edit_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑客服</h4>
                <input type="hidden" value="" id="idHidden">
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客服QQ：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="" name="qq">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">客服名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="" name="name">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="editSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="del_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
                <input type="hidden" value="" id="delId">
            </div>
            <div class="modal-body">要删除此客服吗?</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- modal -->
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
<script type="text/javascript" src="<%=basePath %>/res/js/site/customerservice.js"></script>
</body>
</html>
