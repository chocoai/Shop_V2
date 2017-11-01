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
<title>创建促销 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css">
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
            <li><a href="#">营销</a></li>
            <li><a href="#">营销管理</a></li>
            <li class="active">创建促销</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 抢购促销 </header>
            <div class="panel-body">
              <div class="form-horizontal tasi-form set-form">
                <form class="form-horizontal"  id="savepanicbuy" method="post" enctype="multipart/form-data">
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>促销名称：</label>
                    <div class="col-sm-10">
                      <input type="text" class="form-control required" id="name" maxlength="40">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>抢购折扣：</label>
                    <div class="col-sm-1 tooltips" data-placement="right" data-original-title="请输入0-1之间的小数">
                      <input type="text" class="form-control required zeroOne" id="discount">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>会员限购件数：</label>
                    <div class="col-sm-1">
                      <input type="text" class="form-control required integer" maxlength="7"  id="limitnum" >
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>开始时间：</label>
                    <div class="col-sm-3" style="width:250px">
                      <div class="input-group date search_datetime">
                        <input size="16" type="text" readonly class="form-control" id="startTime" name="startTime">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>结束时间：</label>
                    <div class="col-sm-3" style="width:250px">
                      <div class="input-group date search_datetime">
                        <input size="16" type="text" id="endTime" readonly class="form-control" name="endTime">
                      <span class="input-group-btn">
                      <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                      </span></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>PC端抢购图片：</label>
                    <div class="col-sm-9">
                      <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="height: 60px;"><img class="rushImage"
                                src="<%=basePath %>/res/img/no_img.png" alt=""/></div>
                        <div class="fileupload-preview fileupload-exists thumbnail"
                             style=" max-height: 80px; line-height: 20px;"></div>
                        <div> <span class="btn btn-white btn-file tooltips" data-placement="right"
                                    data-original-title="建议588*338px"> <span class="fileupload-new"><i
                                class="icon-paper-clip"></i> 选择本地图片</span> <span class="fileupload-exists"><i
                                class="icon-undo"></i> 重新上传</span>
                        <input type="file" id="pcimg" class="default"  name="rushImage" onchange="uploadpic('rushImage')"/>
                        </span></div>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>手机端抢购图片：</label>
                    <div class="col-sm-9">
                      <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="height: 60px;"><img class="mobilerushImage"
                                src="<%=basePath %>/res/img/no_img.png" alt=""/></div>
                        <div class="fileupload-preview fileupload-exists thumbnail"
                             style=" max-height: 80px; line-height: 20px;"></div>
                        <div> <span class="btn btn-white btn-file tooltips" data-placement="right"
                                    data-original-title="建议140*140px"> <span class="fileupload-new"><i
                                class="icon-paper-clip"></i> 选择本地图片</span> <span class="fileupload-exists"><i
                                class="icon-undo"></i> 重新上传</span>
                        <input type="file" id="mobileimg" class="default" name="mobilerushImage" onchange="uploadpic('mobilerushImage')"/>
                        </span></div>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">促销描述：</label>
                    <div class="col-sm-10">
                      <textarea rows="3" class="form-control" id="desc"></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>选择商品：</label>
                    <div class="col-sm-10">
                      <div class="clearfix m-bot15">
                        <button id="add_goods" type="button" onclick="tosearch()" class="btn btn-primary"><i
                                class="icon-plus-sign"></i> 选择促销商品
                        </button>
                      </div>
                      <div class="adv-table clearfix" style="max-height:400px; overflow:auto">
                        <table class="display table table-bordered table-striped">
                          <thead>
                          <tr>
                            <th>图片</th>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>规格</th>
                            <th>价格</th>
                            <th>操作</th>
                          </tr>
                          </thead>
                          <tbody id="choosesku">
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                      <a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回</a>
                      <button type="button" class="btn btn-success btn-lg" onclick="savepanic()">保存</button>
                    </div>
                  </div>
                </form>
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
    <div class="text-center"> 2016 &copy; LecShop. <a href="#" class="go-top"> <i class="icon-angle-up"></i> </a> </div>
  </footer>
  <!--footer end--> 
</section>
<!-- Modal -->
<div class="modal fade" id="spu_modal">
  <div class="modal-dialog" style="width:950px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择促销商品</h4>
      </div>
      <div class="modal-body">
        <div class="form-inline m-bot15 clearfix search-form">
          <form class="form-inline" id="queryform" >
            <div class="form-group">
              <label class="control-label">商品名称</label>
              <div class="clearfix">
                <input type="text" class="form-control" name="name">
              </div>
            </div>
            <div class="form-group">
              <label class="control-label">商品编号</label>
              <div class="clearfix">
                <input type="text" class="form-control" name="skuNo">
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
        <div class="adv-table clearfix">
          <table  class="display table table-bordered table-striped" id="dataTable">
            <thead>
            <tr>
              <th width="35"><input type="checkbox"></th>
              <th>图片</th>
              <th>商品编号</th>
              <th>商品名称</th>
              <th>规格</th>
              <th>价格</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" onclick="choosespus()" type="button">保存</button>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="basePath" value="<%=basePath %>"/>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster --> 
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>


<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/js/marketing/marketingcommon.js"></script>
<script src="<%=basePath %>/res/js/marketing/createpanicbuy.js"></script>
</body>
</html>
