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
    <title>未审核商家列表 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
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
                        <li class="active">未审核商家列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 未审核商家列表</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form id="queryform">
                                    <div class="form-group">
                                        <label class="control-label">公司名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="companyName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">店铺名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="storeName">
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
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>公司名称</th>
                                        <th>店铺名称</th>
                                        <th width="160">到期时间</th>
                                        <th width="200">操作</th>
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
<!-- 删除商家 -->
<div class="modal fade" id="del_dialog">
    <input type="hidden" id="deleteStoreId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定要删除此商家吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteStore()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- 审核商家 -->
<div class="modal fade" id="aud_dialog">
    <input type="hidden" id="auditStoreId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">审核商家</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="submitform">
                    <input type="hidden" name="storeId" id="storeInfoId" value="1148">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">审核：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="storeStatus">
                                <option value="2">通过</option>
                                <option value="3">打回</option>
                            </select>
                        </div>
                    </div>
                    <div id="passed_form" class="form-group" style="border-bottom:0">
                        <label class="col-sm-3 control-label">结算周期：</label>
                        <div class="col-sm-9">
                            <select class="form-control select2 select2-hidden-accessible required" id="billingCycle" data-live-search="true" multiple="multiple" style="width:100%" tabindex="-1" aria-hidden="true">
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
                    <div class="form-group" id="reason" style="display: none">
                        <label class="col-sm-3 control-label">拒绝原因：</label>
                        <div class="col-sm-9">
                            <textarea rows="3" id="refusereason" class="form-control" name="reason" maxlength="30"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="auditSave()">保存</button>
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
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--this page plugins-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<!--script for this page only-->
<script src="<%=basePath %>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/select2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/store/sellerunauditlist.js"></script>
<script>
    $(".select2").select2();
    $('#storeStatus').change(function () {
        if($(this).find('option:selected').val() == 3){
            $('#passed_form').hide();
            $('#reason').show();
            $('#passed_form').find('select').removeClass('required');
            $('#reason').find('textarea').addClass('specstr');
        } else {
            $('#passed_form').show();
            $('#reason').hide();
            $('#reason').find('textarea').removeClass('specstr');
            $('#passed_form').find('select').addClass('required');
        }
    })
</script>
</body>
</html>
