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
    <title>栏目列表 - 乐商商城</title>
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
                        <li><a href="#">文章管理</a></li>
                        <li class="active">栏目列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 栏目列表</header>
                        <div class="panel-body">
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button id="add_btn" type="button" class="btn btn-primary" onclick="addColumnBtn()"><i class="icon-plus-sign"></i> 添加</button>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>栏目名称</th>
                                        <th>排序</th>
                                        <th width="75">是否显示</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="treetable"></tbody>
                                </table>
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
<div class="modal fade" id="del_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
                <input type="hidden" id="deleteId">
                <input type="hidden" id="deleteParentId">
            </div>
            <div class="modal-body">确定要删除此栏目吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加栏目</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form set-form">
                    <form id="checkForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>栏目名称：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control required" value="" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">上级栏目：</label>
                            <div class="col-sm-2">
                                <select class="form-control" id="columnsSelect">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control required digits" value="" name="sort">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否显示：</label>
                            <div class="col-sm-2">
                                <select class="form-control" name="isShow">
                                    <option value="0">是</option>
                                    <option value="1">否</option>
                                </select>
                            </div>
                        </div>
                    </form>
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
                <h4 class="modal-title">编辑栏目</h4>
                <input type="hidden" id="id">
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form set-form">
                    <form id="editForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>栏目名称：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control required" value="" name="name">
                            </div>
                        </div>
                        <div class="form-group" id="existParent">
                            <label class="col-sm-3 control-label">上级栏目：</label>
                            <div class="col-sm-2">
                                <select class="form-control" id="columnsSelectEdit">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>排序：</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control required digits" value="" name="sort">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否显示：</label>
                            <div class="col-sm-2">
                                <select class="form-control" name="isShow">
                                    <option value="0">是</option>
                                    <option value="1">否</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="editSaveBtn()">确定</button>
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
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script src="<%=basePath %>/res/js/jqtreetable.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/site/columnlist.js"></script>
</body>
</html>
