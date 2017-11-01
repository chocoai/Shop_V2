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
    <title>店铺管理 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
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
    <!-- 引用上侧 -->
    <jsp:include page="../common/header.jsp"></jsp:include>
    <jsp:include page="../common/left.jsp"></jsp:include>
    <!-- 引用左侧 -->
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper site-min-height">
            <div class="row">
                <div class="col-lg-12">
                    <!--breadcrumbs start -->
                    <ul class="breadcrumb">
                        <li><a href="#">店铺管理</a></li>
                        <li class="active">店铺信息</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 店铺信息</header>
                        <div class="panel-body">
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">商家信息</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab2">公司信息</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab3">结算银行信息</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form1">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">商家编号：</label>
                                                        <div class="col-sm-10">
                                                            <p class="form-control-static" name="storeId"></p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">店铺名称：</label>
                                                        <div class="col-sm-10">
                                                            <p class="form-control-static" name="storeName"></p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">客服QQ：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="serviceQQ">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveBtn(1)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form2">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司名称：</label>
                                                        <div class="col-sm-10">
                                                            <p class="form-control-static" name="companyName"></p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司地址：</label>
                                                        <div class="col-sm-10">
                                                            <p class="form-control-static" name="companyAddress"></p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司电话：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="companyPhone">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">店铺有效期：</label>
                                                        <div class="col-sm-3">
                                                            <p class="form-control-static" name="effectiveTime"></p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">营业执照号：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="busLicense">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司联系人：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="contactPerson">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司联系人手机：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="contactPhone">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">法定代表人姓名：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="legalPerson">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">法定代表人身份证号：</label>
                                                        <div class="col-sm-3">
                                                            <input type="text" class="form-control required" value="" name="cardNo">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">税务登记证：</label>
                                                        <div class="col-sm-10">
                                                            <p class="form-control-static">
                                                                <a href="" target="_blank">
                                                                    <img src="<%=basePath %>/res/img/no_img.png" height="100" alt="" name="taxPic">
                                                                </a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="form-group"></div>
                                                <div class="form-group">
                                                    <form id="carPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">法人身份证：</label>
                                                        <div class="col-sm-10">
                                                            <a href="" target="_blank">
                                                                <img style="display:block; margin-bottom:10px" src="<%=basePath %>/res/img/no_img.png" height="100" alt="" name="carPic">
                                                            </a>
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                                                                <input type="file" class="default required" name="image" inputName="carPic" onchange="uploadPic('carPic')"/>
                                                            </span>
                                                                <span class="fileupload-preview" style="margin-left:5px;"></span>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <form id="busLicensePic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">营业执照副本：</label>
                                                        <div class="col-sm-10"><a href="" target="_blank">
                                                            <img style="display:block; margin-bottom:10px" src="<%=basePath %>/res/img/no_img.png" height="100" alt="" name="busLicensePic"></a>
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                                                                <input type="file" class="default required" name="image" inputName="busLicensePic" onchange="uploadPic('busLicensePic')"/>
                                                            </span>
                                                                <span class="fileupload-preview" style="margin-left:5px;"></span>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">组织机构代码证：</label>
                                                    <div class="col-sm-10">
                                                        <p class="form-control-static">
                                                            <a href="" target="_blank">
                                                                <img src="<%=basePath %>/res/img/no_img.png" height="100" alt="" name="orgPic">
                                                            </a>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveBtn(2)">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab3" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form3">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">银行开户名：</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control required" value="" name="bankUserName">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">公司银行账号：</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control required" value="" name="bankAccount">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">开户银行所在地：</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control required" value="" name="bankAddress">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">开户行支行名称：</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control required" value="" name="bankName">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">开户行支行联行号：</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control required" value="" name="bankNumber">
                                                        </div>
                                                    </div>
                                                </form>
                                                <div class="form-group"></div>
                                                <div class="form-group">
                                                    <form id="bankPic" enctype="multipart/form-data" method="post">
                                                        <label class="col-sm-2 control-label">银行开户许可证照片：</label>
                                                        <div class="col-sm-6">
                                                            <a href="" target="_blank">
                                                                <img style="display:block; margin-bottom:10px" src="<%=basePath %>/res/img/no_img.png" height="100" alt="" name="bankPic">
                                                            </a>
                                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                                    <span class="btn btn-white btn-file">
                                                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span>
                                                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                                                                        <input type="file" class="default required" name="image" inputName="bankPic" onchange="uploadPic('bankPic')"/>
                                                                    </span>
                                                                <span class="fileupload-preview" style="margin-left:5px;"></span>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5" style="text-align: center;">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveBtn(3)">保存设置</button>
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
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--this page plugins-->
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common-scripts.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/storemanage/storeinfo.js"></script>
</body>
</html>
