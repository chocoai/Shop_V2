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
    <title>创建店铺 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css"/>
    <link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
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
                                <li data-step="3" class="active"><span class="step">3</span> <span class="title">店铺开店</span></li>
                            </ul>
                            <div class="form-horizontal tasi-form set-form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>店铺名称：</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="storeName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>经营类目：</label>
                                    <div class="col-sm-10">
                                        <div class="clearfix m-bot15">
                                            <button id="add_class" type="button" class="btn btn-primary" onclick="addCategoryBtn()"><i class="icon-plus-sign"></i> 添加经营类目</button>
                                        </div>
                                        <div class="adv-table clearfix" style="max-height:400px; overflow:auto">
                                            <table class="display table table-bordered table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 200px">二级类目</th>
                                                    <th>三级类目</th>
                                                    <th>三级类目扣率</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="categoryTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>主营品牌：</label>
                                    <div class="col-sm-10">
                                        <div class="clearfix m-bot15">
                                            <button id="add_brand" type="button" class="btn btn-primary" onclick="addBrandBtn()"><i class="icon-plus-sign"></i> 添加主营品牌</button>
                                        </div>
                                        <div class="adv-table clearfix" style="max-height:400px; overflow:auto">
                                            <table class="display table table-bordered table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 280px;text-align: center;">品牌名称</th>
                                                    <th style="text-align: center;">品牌LOGO</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="brandTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>自定义品牌：</label>
                                    <div class="col-sm-10">
                                        <div class="clearfix m-bot15">
                                            <button id="add_custom_brand" type="button" class="btn btn-primary" onclick="addMyselfBrandBtn()"><i class="icon-plus-sign"></i> 添加自定义品牌</button>
                                        </div>
                                        <div class="adv-table clearfix" style="max-height:400px; overflow:auto">
                                            <table class="display table table-bordered table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 280px;text-align: center;">品牌名称</th>
                                                    <th style="text-align: center;">品牌LOGO</th>
                                                    <th style="text-align: center;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="myselfBrandTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="width:100%; text-align:center; margin:20px 0">
                                <a class="btn btn-default btn-lg" href="javascript:;" onclick="toSecondStep()">上一步</a>
                                &nbsp;&nbsp;
                                <a class="btn btn-info btn-lg" onclick="toFourStep()">下一步</a>
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
<div class="modal fade" id="add_class_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加经营类目</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择分类：</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="categorySelect" onchange="showThreeCategory()"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">从属类目：</label>
                        <div class="col-lg-9">
                            <button id="all_btn" type="button" class="btn btn-primary" onclick="allSelectBtn()">全选</button>
                            <div class="clearfix" id="thirdCategoryDiv"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addCategorySaveBtn()">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_brand_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加主营品牌</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>选择品牌：</label>
                        <div class="col-sm-9">
                            <select class="search-select" style="width:150px" id="brandSelect">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addBrandSaveBtn()">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_custom_brand_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">申请自定义品牌</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <div class="form-group">
                        <form id="brandName">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>品牌名称：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control required" name="brandName">
                            </div>
                        </form>
                    </div>
                    <div class="form-group">
                        <form id="brandPic" enctype="multipart/form-data" method="post">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>品牌图片：</label>
                            <div class="col-sm-5">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="fileupload-new thumbnail" style="height: 60px;">
                                        <img src="<%=basePath %>/res/img/no_img.png" alt="" name="brandPic"/>
                                    </div>
                                    <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                    <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('brandPic')"/>
                                    </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="form-group">
                        <form id="brandCertificate" enctype="multipart/form-data" method="post">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>品牌证书：</label>
                            <div class="col-sm-5">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="fileupload-new thumbnail" style="height: 60px;">
                                        <img src="<%=basePath %>/res/img/no_img.png" alt="" name="brandCertificate"/>
                                    </div>
                                    <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                    <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('brandCertificate')"/>
                                    </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addMyselfBrandSaveBtn()">保存</button>
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
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/openstore/scopeofbusiness.js"></script>
</body>
</html>
