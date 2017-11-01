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
    <title>后台管理 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
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
    <input type="hidden" value="<%=basePath%>" id="basePath"/>
    <input type="hidden" value="${id}" id="id"/>
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
                        <li><a href="#">营销管理</a></li>
                        <li class="active">商品组合列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 商品组合列表</header>
                        <div class="panel-body">
                            <form id="goodscomqueryform">
                                <input type="hidden" value="${id}" name="id"/>
                            </form>
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button id="bak_btn" type="button" class="btn btn-info"><i class="icon-reply"></i> 返回商品组合列表</button>
                                <button id="add_btn" type="button" class="btn btn-primary" onclick="tosearch()"><i class="icon-plus-sign"></i> 添加商品</button>
                                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="skuDataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>货品图片</th>
                                        <th>货品名称</th>
                                        <th>销售价</th>
                                        <th>规格值</th>
                                        <th>商品编号</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="choosesku">

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
        <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a>
        </div>
    </footer>
    <!--footer end-->
</section>

<!-- Modal -->
<div class="modal fade" id="spu_modal">
    <div class="modal-dialog" style="width:950px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择促销商品</h4>
            </div>
            <div class="modal-body">
                <div class="form-inline m-bot15 clearfix search-form">
                    <form class="form-inline" id="queryform" >
                        <div class="form-group">
                            <label class="control-label">商品名称</label>
                            <div class="clearfix">
                                <input type="text" class="form-control" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">商品编号</label>
                            <div class="clearfix">
                                <input type="text" class="form-control" name="skuNo">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">&nbsp;</label>
                            <div class="clearfix">
                                <button type="button" onclick="refreshDataTable()" class="btn btn-info">搜索</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="adv-table clearfix">
                    <table  class="display table table-bordered table-striped" id="dataTable">
                        <thead>
                        <tr>
                            <th width="35"><input type="checkbox"></th>
                            <th>图片</th>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>价格</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" onclick="choosespus()" type="button">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 删除商品组合 -->
<div class="modal fade" id="del_dialog">
    <input type="hidden" id="deleteId">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定要删除此商品吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteSku()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath%>/res/js/jquery.js"></script>
<script src="<%=basePath%>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath%>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath%>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath%>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath%>/res/js/respond.min.js"></script>

<!--common script for all pages-->
<script src="<%=basePath%>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/marketing/managegoodscombination.js"></script>
<script>
    $('.del_btn').click(function () {
        $('#del_dialog').modal('show');
    });
    $('#add_btn,.edit_btn').click(function () {
        $('#add_dialog').modal('show');
    });
    $("#bak_btn").click(function () {
        window.location.href = document.referrer;
    })
</script>
</body>
</html>
