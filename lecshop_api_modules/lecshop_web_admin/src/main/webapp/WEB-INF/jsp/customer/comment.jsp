<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
<title>评论列表 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
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
            <li><a href="#">咨询评论</a></li>
            <li class="active">评论列表</li>
          </ul>
          <!--breadcrumbs end -->
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 评论列表 </header>
            <div class="panel-body">
              <div class="form-inline m-bot15 clearfix search-form">
                <form role="form" class="form-inline" id="queryform">
                  <div class="form-group">
                    <label class="control-label">发表人</label>
                    <div class="clearfix">
                      <input type="text" class="form-control" name="customerName" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">商品名称</label>
                    <div class="clearfix">
                      <input type="text"  class="form-control" name="skuName">
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
                    <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                </div>
              <div class="adv-table clearfix">
                <form >
                  <table class="display table table-bordered table-striped" id="dataTable">
                      <thead>
                      <tr>
                          <th width="35"><input type="checkbox"></th>
                          <th>商品名称</th>
                          <th>发表人</th>
                          <th>是否匿名</th>
                          <th>内容</th>
                          <th>发表时间</th>
                          <th>操作</th>
                      </tr>
                      </thead>
                    <tbody>
                    </tbody>
                  </table>
                </form>
              </div>
            </div>
          </section>
        </div>
      </div>
    </section>
  </section>

    <div class="modal fade" id="view_dialog" aria-hidden="true" style="display: none;">
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">评论详情</h4>
                </div>
                <div class="modal-body">
                    <div class="panel-body" style="padding-top:0">
                        <div class="task-thumb"> <img id="myimg"  alt="" width="90" height="90"> </div>
                        <div class="task-thumb-details">
                            <h1 id="skuName"></h1>
                            <p>发表人：<font id="customerName"></font></p>
                            <p>时间：<font id="ptime"></font></p>
                        </div>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <td>
                                <div class="pj_item clearfix">
                                    <div class="pj_label">描述相符：</div>
                                    <div class="put">
                                        <span class="commstar clearfix">
                                            <ul id="descstat">
                                            </ul>
                                        </span>
                                    </div>
                                </div>
                                <div class="pj_item clearfix">
                                    <div class="pj_label">卖家服务：</div>
                                    <div class="put">
                                        <span class="commstar clearfix">
                                            <ul id="sellerstat">
                                            </ul>
                                        </span>
                                    </div>
                                </div>
                                <div class="pj_item clearfix">
                                    <div class="pj_label">物流服务：</div>
                                    <div class="put">
                                        <span class="commstar clearfix">
                                            <ul id="logstar">
                                            </ul>
                                        </span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="pj_item clearfix">
                                    <div class="pj_label">评论内容：</div>
                                    <div class="put"><font id="pcontent"></font></div>
                                </div>
                                <div class="pj_item clearfix">
                                    <div class="pj_label">评论图片：</div>
                                    <div class="put">
                                        <ul id="imgList" class="imgList">
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
  <!--main content end-->
  <!--footer start-->
  <footer class="site-footer">
    <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a> </div>
  </footer>
  <!--footer end-->
</section>
<!-- Modal -->
<div class="modal fade" id="remove_dialog" aria-hidden="true" style="display: none;">
  <div class="modal-dialog">
    <input type="hidden" id="deleteId">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">系统提示</h4>
      </div>
      <div class="modal-body">确认要删除这条评论吗？</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="deleteComment()">确定</button>
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
<script src="<%=basePath %>/res/js/common/common.js"></script>


<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script src="<%=basePath %>/res/js/customer/comment.js"></script>

<!--script for this page only-->
</body>
</html>
