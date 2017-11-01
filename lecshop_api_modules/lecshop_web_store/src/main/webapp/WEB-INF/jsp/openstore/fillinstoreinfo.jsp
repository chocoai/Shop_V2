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
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
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
                                <li data-step="2" class="active"><span class="step">2</span> <span class="title">商家信息</span></li>
                                <li data-step="3" class=""><span class="step">3</span> <span class="title">店铺开店</span></li>
                            </ul>
                            <div class="form-horizontal tasi-form set-form">
                                <div class="form-group">
                                    <form id="textForm">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>公司名称：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="companyName">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>公司地址：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="companyAddress">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>公司电话：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="companyPhone">
                                                <span class="help-block">格式为区号-号码（例：000-00000000）</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">电子邮箱：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="companyEmail">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>联系人姓名：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="contactPerson">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">联系人电话：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="contactPhone">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>工商营业执照号：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="busLicense">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>法定代表人姓名：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="legalPerson">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label"><span class="label_red">*</span>法人身份证号码：</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control required" name="cardNo">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="form-group">
                                    <form class="form-horizontal tasi-form" id="carPic" enctype="multipart/form-data" method="post">
                                        <label class="control-label col-md-2"><span class="label_red">*</span>法人身份证电子版：</label>
                                        <div class="controls col-md-10">
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="carPic"/>
                                                </div>
                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                <div>
                                                <span class="btn btn-white btn-file">
                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                    <input type="file" class="default required" name="image" onchange="uploadPic('carPic')"/>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>三证合一/不合一：</label>
                                    <div class="col-sm-1">
                                        <select id="three_in_one" class="form-control" name="isMerge">
                                            <option value="0" selected>三证合一</option>
                                            <option value="1">三证不合一</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="taxPicDiv">
                                    <form class="form-horizontal tasi-form" id="taxPic" enctype="multipart/form-data" method="post">
                                        <label class="control-label col-md-2"><span class="label_red">*</span>税务登记证：</label>
                                        <div class="controls col-md-10">
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="taxPic"/>
                                                </div>
                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                <div>
                                                <span class="btn btn-white btn-file">
                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                    <input type="file" class="default required" name="image" onchange="uploadPic('taxPic')"/>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="form-group" id="busLicensePicDiv" style="display:none">
                                    <form class="form-horizontal tasi-form" id="busLicensePic" enctype="multipart/form-data" method="post">
                                        <label class="control-label col-md-2"><span class="label_red">*</span>工商营业执照：</label>
                                        <div class="controls col-md-10">
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="busLicensePic"/>
                                                </div>
                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                <div>
                                                <span class="btn btn-white btn-file">
                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                    <input type="file" class="default required" name="image" onchange="uploadPic('busLicensePic')"/>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="form-group" id="orgPicDiv" style="display:none">
                                    <form class="form-horizontal tasi-form" id="orgPic" enctype="multipart/form-data" method="post">
                                        <label class="control-label col-md-2"><span class="label_red">*</span>组织机构代码证：</label>
                                        <div class="controls col-md-10">
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="orgPic"/>
                                                </div>
                                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                <div>
                                                <span class="btn btn-white btn-file">
                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                    <input type="file" class="default required" name="image" onchange="uploadPic('orgPic')"/>
                                                </span>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div style="width:100%; text-align:center; margin:20px 0">
                                <a class="btn btn-default btn-lg" href="javascript:;" onclick="toFirstStep()">上一步</a>
                                &nbsp;&nbsp;
                                <a class="btn btn-info btn-lg" onclick="toThirdStep()">下一步</a>
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
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script src="<%=basePath %>/res/js/common-scripts.js"></script>
<!--script for this page only-->
<script src="<%=basePath %>/res/js/openstore/fillinstoreinfo.js"></script>
</body>
</html>
