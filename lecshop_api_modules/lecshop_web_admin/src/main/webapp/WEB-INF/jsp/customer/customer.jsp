<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <title>会员列表 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

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
                        <li><a href="#">会员</a></li>
                        <li><a href="#">会员管理</a></li>
                        <li class="active">会员列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 会员列表</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form role="form" class="form-inline" id="queryform">
                                    <div class="form-group">
                                        <label class="control-label">用户名</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="userName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">真实姓名</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="releName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">手机号</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="mobile">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">邮箱</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">是否手机验证</label>
                                        <div class="clearfix">
                                            <select class="form-control" name="isMobileVerification">
                                                <option value="">
                                                    是否手机验证
                                                </option>
                                                <option value="0">
                                                    否
                                                </option>
                                                <option value="1">
                                                    是
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">是否邮箱验证</label>
                                        <div class="clearfix">
                                            <select class="form-control" name="isEmailVerification">
                                                <option value="">
                                                    是否邮箱验证
                                                </option>
                                                <option value="0">
                                                    否
                                                </option>
                                                <option value="1">
                                                    是
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">账户状态</label>
                                        <div class="clearfix">
                                            <select class="form-control" name="status">
                                                <option value="">
                                                    选择状态
                                                </option>
                                                <option value="1">
                                                    正常
                                                </option>
                                                <option value="2">
                                                    冻结
                                                </option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">账户类型</label>
                                        <div class="clearfix">
                                            <select class="form-control" name="type">
                                                <option value="">
                                                    选择类型
                                                </option>
                                                <option value="1">
                                                    普通
                                                </option>
                                                <option value="2">
                                                    商家
                                                </option>
                                            </select>
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
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button id="add_member" type="button" class="btn btn-primary" onclick="toaddcustomer()"><i class="icon-plus-sign"></i> 添加会员</button>
                                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                                <button id="sms_btn" type="button" class="btn btn-info" onclick="tosendmsg()"><i class="icon-comments"></i> 发送通知</button>
                                <div class="btn-group">
                                    <button data-toggle="dropdown" class="btn btn-primary dropdown-toggle" type="button"><i class="icon-external-link"></i> 导出 <span class="caret"></span></button>
                                    <ul role="menu" class="dropdown-menu">
                                        <li><a href="javascript:;" onclick="exportAll()">导出所有</a></li>
                                        <li><a href="javascript:;" onclick="exportChecked()">导出选中</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="adv-table clearfix">

                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>用户名</th>
                                        <th>等级</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>手机验证</th>
                                        <th>邮箱验证</th>
                                        <th>状态</th>
                                        <th>类型</th>
                                        <th>预存款总额</th>
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
<div class="modal fade" id="del_dialog" aria-hidden="true" style="display: none;">
    <input type="hidden" id="deleteId">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">确定要删除此用户吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteCustomer()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加会员</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <form id="savecustomer" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>用户名：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control required numandletter" maxlength="20" id="username">
                                <p id="customerUsernametip" class="help-block" style="display: none;">用户名已存在</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">邮箱：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control email" name="infoEmail" id="email">
                                <p id="emailtip" class="help-block" style="display: none">邮箱已使用</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control required" maxlength="16" minlength="6" id="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>重复密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control required" equalTo="#password" id="repassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">预览头像：</label>
                            <div class="col-sm-9">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="fileupload-new thumbnail" style="height: 50px; width:50px"><img id="image" src="<%=basePath%>/res/img/member.png"
                                                                                                                alt=""/></div>
                                    <div class="fileupload-preview fileupload-exists thumbnail"
                                         style="max-width: 50px; max-height: 50px; line-height: 20px;"></div>
                                    <div> <span class="btn btn-white btn-file tooltips" data-placement="right"
                                                data-original-title="建议100*100px"> <span class="fileupload-new"><i
                                            class="icon-paper-clip"></i> 选择本地图片</span> <span class="fileupload-exists"><i
                                            class="icon-undo"></i> 重新上传</span>
                  <input type="file" class="default" name="image" onchange="updateload()"/>
                  </span></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别：</label>
                            <div class="col-lg-9">
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="0" name="addgender" checked>
                                    保密 </label>
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="1" name="addgender">
                                    男 </label>
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="2" name="addgender">
                                    女 </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">真实姓名：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control isChineseandPonint" id="relename">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">手机号码：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control mobile" id="mobile">
                                <label id="mobiletip" class="help-block" style="display: none">手机号已使用</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">身份证号码：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control isIdCardNo" id="cardId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所在地区：</label>
                            <div class="col-sm-9 address_select">
                                <select class="form-control" id="province" onchange="loadCity(this)">
                                </select>
                                <select class="form-control" id="city" onchange="loadDistrict(this)">
                                    <option value="">选择城市</option>
                                </select>
                                <select class="form-control" id="district">
                                    <option value="">选择区县</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">详细地址：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="detailAddress">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="savecustomer()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="update_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改会员</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <form method="post" id="updateCustomer" enctype="multipart/form-data">
                        <input type='hidden' id="ucustomerId">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">预览头像：</label>
                            <div class="col-sm-9">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="fileupload-new thumbnail" style="height: 50px; width:50px"><img id="deimg" src="<%=basePath%>/res/img/member.png"
                                                                                                                alt=""/></div>
                                    <div class="fileupload-preview fileupload-exists thumbnail"
                                         style="max-width: 50px; max-height: 50px; line-height: 20px;"></div>
                                    <div> <span class="btn btn-white btn-file tooltips" data-placement="right"
                                                data-original-title="建议100*100px"> <span class="fileupload-new"><i
                                            class="icon-paper-clip"></i> 选择本地图片</span> <span class="fileupload-exists"><i
                                            class="icon-undo"></i> 重新上传</span>
                  <input type="file" class="default" name="image" onchange="updatepic()"/>
                  </span></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">性别：</label>
                            <div class="col-lg-9">
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="0" name="ugenger">
                                    保密 </label>
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="1" name="ugenger">
                                    男 </label>
                                <label class="checkbox-inline" style="padding-left:0">
                                    <input type="radio" value="2" name="ugenger">
                                    女 </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">真实姓名：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control isChineseandPonint" id="urealname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">身份证号码：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control isIdCardNo" id="ucardno">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所在地区：</label>
                            <div class="col-sm-9 address_select">
                                <select class="form-control" id="uprovince" onchange="uloadCity(this)">
                                </select>
                                <select class="form-control" id="ucity" onchange="uloadDistrict(this)">
                                    <option value="">选择城市</option>
                                </select>
                                <select class="form-control" id="udistrict">
                                    <option value="">选择区县</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">详细地址：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="uaddress">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="updatecustomer()">确定</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="sms_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">发送通知</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="sendform">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>标题：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" maxlength="1000" id="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>内容：</label>
                        <div class="col-sm-9">
                            <textarea type="text" class="form-control required" maxlength="2000" id="content"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="sendmsg()">确定</button>
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

<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/customer/customer.js"></script>

</body>
</html>
