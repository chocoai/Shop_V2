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
<title></title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/jquery-multi-select/css/multi-select.css" />
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
            <li><a href="#">商品</a></li>
            <li><a href="#">商品管理</a></li>
            <li class="active">商品列表</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 商品列表</header>
            <div class="panel-body">
              <div class="btn-group btn-group-justified list_tab" style="margin-bottom:30px">
                <a class="btn btn-lg btn-default "
                   href="<%=basePath %>/store_toqueryspus.htm">全部</a>
                <a class="btn btn-lg btn-default btn-primary "
                   href="<%=basePath %>/store_toquerytobeauditspus.htm">待审核</a>
                <a class="btn btn-lg btn-default " href="<%=basePath %>/store_toqueryrefusespus.htm">审核拒绝</a>
                <a class="btn btn-lg btn-default " href="<%=basePath %>/store_toqueryauditpass.htm">审核通过</a>
              </div>
              <form role="form" id="queryform" class="form-inline">
                <div class="form-inline m-bot15 clearfix search-form">
                  <input type="hidden" name="status" value="2">
                  <input type="hidden" id="queryurl" value="store_queryspuswithsku.htm">

                  <div class="form-group">
                    <label class="control-label">商品编号</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="id" >
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="control-label">商品标题</label>
                    <div class="clearfix">
                      <input type="text" class="form-control"  name="name" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">商品品牌</label>
                    <div class="clearfix">
                      <select class="search-select" style="width:150px" name="brandId">
                        <option value="0">选择品牌</option>
                        <c:forEach items="${brands }" var="brand">
                          <option value="${brand.id }">${brand.name }</option>
                        </c:forEach>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">一级分类</label>
                    <div class="clearfix">
                      <select class="form-control" name="firstCateId" id="firstCateId" onchange="chooseFCate(this);">
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">二级分类</label>
                    <div class="clearfix">
                      <select class="form-control" id="secondCateId" name="secondCateId" onchange="chooseTCate(this);">
                        <option value="0">选择分类</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">三级分类</label>
                    <div class="clearfix">
                      <select class="form-control" name="thirdCateId" id="thirdCateId">
                        <option value="0">选择分类</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label">&nbsp;</label>
                    <div class="clearfix">
                      <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索</button>
                    </div>
                  </div>
                </div>
              </form>
              <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                <button type="button" class="btn btn-danger" onclick="todeleteSpus()"><i class="icon-trash"></i> 删除</button>
                <button type="button" class="btn btn-info" onclick="tobatchup()"><i class="icon-arrow-up"></i> 批量上架</button>
                <button type="button" class="btn btn-info" onclick="tobatchdown()"><i class="icon-arrow-down"></i> 批下上架</button>
              </div>
              <div class="adv-table clearfix">
                <form id="deletespus">
                  <table class="display table table-bordered table-striped" id="dataTable">
                    <thead>
                    <tr>
                      <th width="35"><input type="checkbox"></th>
                      <th width="80">商品图片</th>
                      <th>商品标题</th>
                      <th>商品副标题</th>
                      <th style="min-width:90px">销售价</th>
                      <th style="min-width:90px">品牌</th>
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


  <!-- 删除商品-->
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
          <button class="btn btn-success" type="button" onclick="deleteSpu()">确定</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 上架商品-->
  <div class="modal fade" id="up_dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">确认提示</h4>
        </div>
        <div class="modal-body">确定要上架选中的商品吗？</div>
        <div class="modal-footer">
          <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
          <button class="btn btn-success" type="button" onclick="batchup()">确定</button>
        </div>
      </div>
    </div>
  </div>

  <!-- 下架商品-->
  <div class="modal fade" id="down_dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">确认提示</h4>
        </div>
        <div class="modal-body">确定要下架选中的商品吗？</div>
        <div class="modal-footer">
          <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
          <button class="btn btn-success" type="button" onclick="batchdown()">确定</button>
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
<!-- js placed at the end of the document so the pages load faster --> 
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/spu/spulist.js"></script>
<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script>
$('.search-select').chosen();
</script>
</body>
</html>
