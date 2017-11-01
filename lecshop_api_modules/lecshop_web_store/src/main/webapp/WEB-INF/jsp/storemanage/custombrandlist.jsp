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
    <title>品牌管理 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css"/>
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
                        <li><a href="#">店铺管理</a></li>
                        <li class="active">品牌管理</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 品牌管理</header>
                        <div class="panel-body">
                            <div class="btn-group btn-group-justified list_tab" style="margin-bottom:30px">
                                <a class="btn btn-lg btn-default" href="" id="brandList">品牌列表</a>
                                <a class="btn btn-lg btn-default btn-primary" href="javascript:location.href=window.location.href">自定义品牌列表</a>
                            </div>
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form id="queryform">
                                    <div class="form-group">
                                        <label class="control-label">品牌名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">&nbsp;</label>
                                        <div class="clearfix">
                                            <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button id="add_btn" type="button" class="btn btn-primary" onclick="applyCustomBrand()"><i class="icon-plus-sign"></i> 申请自定义品牌</button>
                                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>品牌名称</th>
                                        <th>品牌图片</th>
                                        <th>品牌证书</th>
                                        <th>状态</th>
                                        <th>拒绝原因</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
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
                <input type="hidden" name="deleteId">
            </div>
            <div class="modal-body">确定要删除此品牌吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content form-horizontal tasi-form">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">申请自定义品牌</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <form id="name">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>品牌名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" name="name">
                        </div>
                    </form>
                </div>
                <div class="form-group">
                    <form id="url">
                        <label class="control-label col-md-3"><span class="label_red">*</span>品牌图片：</label>
                        <div class="col-sm-5">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="url"/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('url')"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="form-group">
                    <form id="certificatUrl">
                        <label class="control-label col-md-3"><span class="label_red">*</span>品牌证书：</label>
                        <div class="col-sm-5">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="certificatUrl"/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('certificatUrl')"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="applySaveBtn()">申请</button>
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
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/storemanage/custombrandlist.js"></script>
</body>
</html>
