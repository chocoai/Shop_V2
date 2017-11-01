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
    <title>已审核商家列表 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
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
    <input type="hidden" value="${storeId}" id="storeId">
    <!-- 引用头 -->
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
                        <li><a href="#">店铺</a></li>
                        <li><a href="#">商家审核</a></li>
                        <li class="active">已审核商家列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 已审核商家设置</header>
                        <div class="panel-body">
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">签约信息</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab2">结算/期限/关店</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                                <button id="add_btn" type="button" class="btn btn-primary" onclick="addCategoryBtn()"><i class="icon-plus-sign"></i> 添加签约分类</button>
                                            </div>
                                            <div class="adv-table clearfix">
                                                <table class="display table table-bordered table-striped dataTable">
                                                    <thead>
                                                    <tr>
                                                        <th>所属分类</th>
                                                        <th>分类名称</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="categoryTbody">

                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label"><span class="label_red">*</span>结算周期：</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control select2 required" id="billingCycle" data-live-search="true" multiple="multiple" style="width:100%">
                                                            <option value="1">每月1日</option>
                                                            <option value="2">每月2日</option>
                                                            <option value="3">每月3日</option>
                                                            <option value="4">每月4日</option>
                                                            <option value="5">每月5日</option>
                                                            <option value="6">每月6日</option>
                                                            <option value="7">每月7日</option>
                                                            <option value="8">每月8日</option>
                                                            <option value="9">每月9日</option>
                                                            <option value="10">每月10日</option>
                                                            <option value="11">每月11日</option>
                                                            <option value="12">每月12日</option>
                                                            <option value="13">每月13日</option>
                                                            <option value="14">每月14日</option>
                                                            <option value="15">每月15日</option>
                                                            <option value="16">每月16日</option>
                                                            <option value="17">每月17日</option>
                                                            <option value="18">每月18日</option>
                                                            <option value="19">每月19日</option>
                                                            <option value="20">每月20日</option>
                                                            <option value="21">每月21日</option>
                                                            <option value="22">每月22日</option>
                                                            <option value="23">每月23日</option>
                                                            <option value="24">每月24日</option>
                                                            <option value="25">每月25日</option>
                                                            <option value="26">每月26日</option>
                                                            <option value="27">每月27日</option>
                                                            <option value="28">每月28日</option>
                                                            <option value="29">每月29日</option>
                                                            <option value="30">每月30日</option>
                                                            <option value="31">每月31日</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group time_form">
                                                    <label class="col-sm-3 control-label"><span class="label_red">*</span>店铺期限：</label>
                                                    <div class="col-sm-3">
                                                        <div class="input-group date search_datetime">
                                                            <input type="text" name="effectiveTime" value="" readonly class="form-control">
                                                            <span class="input-group-btn">
                                                                <button type="button" class="btn btn-info date-set">
                                                                    <i class="icon-calendar"></i>
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">是否关店：</label>
                                                    <div class="col-sm-3">
                                                        <select class="form-control" name="status">
                                                            <option value="2">正常</option>
                                                            <option value="4">关店</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label"></label>
                                                    <div class="col-sm-3" style="text-align: center;">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="allInfoSaveBtn()">保存设置</button>
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
<div class="modal fade" id="del_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
                <input type="hidden" value="" name="deleteId">
            </div>
            <div class="modal-body">确定要删除此分类吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" onclick="deleteCategorySaveBtn()" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加签约分类</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选择分类：</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="categorySelect" onchange="showThreeCategory()">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">从属类目：</label>
                        <div class="col-lg-9">
                            <button id="all_btn" type="button" class="btn btn-primary">全选</button>
                            <div class="clearfix" id="thirdCategoryDiv">
                            </div>
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
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--this page plugins-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/select2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/store/sellerauditpassset.js"></script>
</body>
</html>
