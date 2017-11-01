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
    <title>菜单设置 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
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
                        <li><a href="#">分销</a></li>
                        <li><a href="#">分销管理</a></li>
                        <li class="active">菜单设置</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="inner_container_box side_l cell_layout">
                <div class="inner_container_box_bd">
                    <div class="inner_side fll">
                        <div class="bd">
                            <div class="menu_manage">
                                <div class="sub_title_bar light">
                                    <h4>菜单管理</h4>
                                    <div class="opr_wrp">
                                        <a href="javascript:;" class="opr_meta icon16_common add_gray" onclick="showAddModal()">添加</a>
                                    </div>
                                </div>
                                <div class="inner_menu_box gray with_switch ui-sortable ui-sortable-disabled" id="menu"></div>
                            </div>
                        </div>
                    </div>
                    <div class="inner_main flr">
                        <div class="bd">
                            <div class="action_setting">
                                <div class="sub_title_bar light">
                                    <h4>设置动作</h4>
                                </div>
                                <div class="action_content default jsMain" style="display: block;">
                                    <p class="action_tips">你可以先添加一个菜单，然后开始为其设置跳转页面</p>
                                </div>
                                <div class="action_content url jsMain">
                                    <p class="action_tips">订阅者点击该子菜单会跳到以下链接</p>
                                    <div class="frm_control_group">
                                        <span style="margin-right:10px">页面地址</span>
                                        <span class="frm_input_box" style="border: 0">
                                            <input type="text" class="frm_input" value="" id="url" readonly>
                                        </span>
                                        <p class="frm_msg fail">
                                            <span class="frm_msg_content" style="line-height:30px">请输入正确的URL</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="inner_container_box_ft">
                    <div class="tool_bar">
                        <a class="submit btn btn-primary" type="button" onclick="getDomToJson()">保存并发布</a>
                    </div>
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
<!-- Modal -->
<div class="modal fade" id="remove_dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">系统提示</h4>
                <input type="hidden" id="deleteHidden" value=""/>
            </div>
            <div class="modal-body">你确定要删除此菜单吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="twoDeleteSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="remove_dialog_one" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">系统提示</h4>
                <input type="hidden" id="oneDeleteHidden" value=""/>
            </div>
            <div class="modal-body">你确定要删除此菜单吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="oneDeleteSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">添加一级菜单</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="addForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" minlength="4" maxlength="8" id="addMenuName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">页面地址：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required url" minlength="4" id="addMenuUrl">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_sub_dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">添加二级菜单</h4>
                <input type="hidden" id="idHidden" value=""/>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="addSubForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" minlength="4" maxlength="8" id="addSubMenuName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">页面地址：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required url" minlength="4" id="addSubMenuUrl">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addSubSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="edit_dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">编辑一级菜单</h4>
                <input type="hidden" id="editHidden" value=""/>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="editForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" minlength="4" maxlength="8" id="editMenuName">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="editSaveBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="edit_sub_dialog" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">编辑二级菜单</h4>
                <input type="hidden" id="twoEditHidden" value=""/>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="editSubForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">菜单名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" minlength="4" maxlength="8" id="editSubMenuName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">页面地址：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required url" minlength="4" id="editSubMenuUrl">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="editSubSaveBtn()">确定</button>
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
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/distribution/wechatmenu.js"></script>
</body>
</html>
