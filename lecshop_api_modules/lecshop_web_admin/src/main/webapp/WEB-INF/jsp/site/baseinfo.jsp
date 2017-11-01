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
    <title>基本信息 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
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
                        <li class="active">基本信息</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 基本信息</header>
                        <div class="panel-body">
                            <div class="alert alert-warning fade in">
                                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                                <strong>注意！</strong> 基本信息设置，若修改不当，会影响相关网站地址和logo，会造成页面显示和跳转出错。
                            </div>
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">基本设置</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab2">高级设置</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商城前台网址：</label>
                                                    <div class="col-sm-5 set_box">
                                                        <input type="text" class="form-control required" value="" name="siteUrl">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商城联系电话：</label>
                                                    <div class="col-sm-5 set_box">
                                                        <input type="text" class="form-control required" value="" name="phone">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="labelLog" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">标签页图标：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="labelLog"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议格式ico,尺寸16px*16px">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('labelLog')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="logo" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">网站LOGO：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="logo"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('logo')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="adminLogo" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">后台登陆LOGO：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="adminLogo"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('adminLogo')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="adminIndexLogo" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">后台首页LOGO：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="adminIndexLogo"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('adminIndexLogo')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="siteLoginPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">前台登录页面图片：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="siteLoginPic"/></div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png,尺寸1920px*590px">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('siteLoginPic')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="siteRegisterPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">前台注册页面图片：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="siteRegisterPic"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png,尺寸1920px*590px">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('siteRegisterPic')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="storeLoginPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">商家登录页面图片：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="storeLoginPic"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png,尺寸1920px*590px">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('storeLoginPic')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form class="form-horizontal tasi-form" id="storeRegisterPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">商家注册页面图片：</label>
                                                        <div class="col-sm-5">
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="storeRegisterPic"/>
                                                                </div>
                                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                                <div>
                                                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议jpg,jpeg,png,尺寸1920px*590px">
                                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                    <input type="file" class="default" name="image" onchange="uploadPic('storeRegisterPic')"/>
                                                                </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">版权信息：</label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-primary" onclick="$('#copyrightInfo').modal('show')">查看并修改</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">商家注册协议：</label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-primary" onclick="$('#storeRegisterProtocol').modal('show')">查看并修改</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">商家开店协议：</label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-primary" onclick="$('#storeOpenProtocol').modal('show')">查看并修改</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">商城用户注册协议：</label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-primary" onclick="$('#siteRegisterProtocol').modal('show')">查看并修改</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg" onclick="baseInfoSaveBtn(1)">保存设置</button>
                                                    </div>
                                                </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">后台登录验证码：</label>
                                                <div class="col-sm-2">
                                                    <select class="form-control" name="captchaOpen">
                                                        <option value="0">启用</option>
                                                        <option value="1">不启用</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-5">
                                                    <button type="button" class="btn btn-success btn-lg" onclick="baseInfoSaveBtn(0)">保存设置</button>
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
    <div class="modal fade" id="copyrightInfo">
        <div class="modal-dialog" style="width: 900px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">编辑版权信息</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="summernote" id="copyrightInfoDiv"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-success" type="button" onclick="$('#copyrightInfo').modal('hide')">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="storeRegisterProtocol">
        <div class="modal-dialog" style="width: 900px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">编辑商家注册协议</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="summernote" id="storeRegisterProtocolDiv"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-success" type="button" onclick="$('#storeRegisterProtocol').modal('hide')">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="storeOpenProtocol">
        <div class="modal-dialog" style="width: 900px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">编辑商家开店协议</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="summernote" id="storeOpenProtocolDiv"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-success" type="button" onclick="$('#storeOpenProtocol').modal('hide')">确定</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="siteRegisterProtocol">
        <div class="modal-dialog" style="width: 900px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">编辑用户协议</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="summernote" id="siteRegisterProtocolDiv"></div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-success" type="button" onclick="$('#siteRegisterProtocol').modal('hide')">确定</button>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="<%=basePath%>/res/assets/summernote/summernote.min.js"></script>
<script src="<%=basePath%>/res/assets/summernote/summernote-zh-CN.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/site/baseinfo.js"></script>
</body>
</html>
