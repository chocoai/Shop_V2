<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
<title>添加商品 - 乐商商城</title>
<!-- Bootstrap core CSS -->
<link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/tasks.css" rel="stylesheet">
  <link href="<%=basePath %>/res/assets/summernote/summernote.css" rel="stylesheet">
<!--external css-->
<link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="<%=basePath %>/res/css/chosen.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/jquery-multi-select/css/multi-select.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
<link href="<%=basePath %>/res/assets/dropzone/css/dropzone.css" rel="stylesheet"/>

<!-- Custom styles for this template -->
<link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
<link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
<script src="<%=basePath %>/res/js/html5shiv.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<![endif]-->
</head>

<body class="has-js">
<section id="container">
  <input type="hidden" value="<%=basePath%>" id="basePath">
  <!--main content start-->
  <!-- 引用上侧 -->
  <jsp:include page="../common/header.jsp"></jsp:include>
  <!-- 引用左侧 -->
  <jsp:include page="../common/left.jsp"></jsp:include>
  <!--main content start-->
  <section id="main-content">
    <input id="fcateId" type="hidden">
    <input id="scateId" type="hidden">
    <input id="cateId" type="hidden">
    <input id="typeId" type="hidden">
    <input id="fcatename" type="hidden">
    <input id="scatename" type="hidden">
    <input id="tcatename" type="hidden">
    <input id="spuImportId" type="hidden" value="${spuImport.id}">

    <section class="wrapper site-min-height">
      <div class="row">
        <div class="col-lg-12"> 
          <!--breadcrumbs start -->
          <ul class="breadcrumb">
            <li><a href="#">商品</a></li>
            <li><a href="#">商品管理</a></li>
            <li class="active">添加商品</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 添加商品 </header>
            <div class="panel-body">
              <div class="mypanel" id="addcate">
                <ul class="steps">
                  <li data-step="1" class="active"> <span class="step">1</span> <span class="title">选择商品分类</span> </li>
                  <li data-step="2" class=""> <span class="step">2</span> <span class="title">编辑商品信息</span> </li>
                </ul>
                <div class="form-group addgoods_multiple clearfix">
                  <div class="col-lg-4">
                    <section class="panel tasks-widget categorys_box">
                      <div class="panel-body">
                        <ul class="task-list" id="fcate">
                        </ul>
                      </div>
                    </section>
                  </div>
                  <div class="col-lg-4">
                    <section class="panel tasks-widget categorys_box">
                      <div class="panel-body">
                        <ul class="task-list" id="scate">
                        </ul>
                      </div>
                    </section>
                  </div>
                  <div class="col-lg-4">
                    <section class="panel tasks-widget categorys_box">
                      <div class="panel-body">
                        <ul class="task-list" id="tcate">
                        </ul>
                      </div>
                    </section>
                  </div>
                </div>
                <div style="width:300px; margin:20px auto"> <a class="btn btn-info btn-lg btn-block panel_next" href="javascript:;" onclick="toaddbaseinfo()">进入下一步</a> </div>
              </div>
              <div class="mypanel" id="addbaseinfo" style="display:none">
                <ul class="steps">
                  <li data-step="1" class="complete"> <span class="step">1</span> <span class="title">选择商品分类</span> </li>
                  <li data-step="2" class="active"> <span class="step">2</span> <span class="title">编辑基本信息</span> </li>
                </ul>
                <form class="form-horizontal tasi-form" id="addspu" enctype="multipart/form-data"  method="post" >
                  <div class="form-group">
                    <label class="col-sm-2 control-label">商品分类：</label>
                    <div class="col-sm-10">
                      <p class="form-control-static" id="catenames"></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商品标题：</label>
                    <div class="col-sm-10">
                      <div class="fll" style="width: 80%"><input type="text" value="${spuImport.name}" class="form-control required specstr" id="spuName" maxlength="100"></div>
                      <a href="javascript:;" onclick="nametoallsku()"  class="btn btn-info fll" style="margin-left: 10px">应用到所有规格</a>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">商品副标题：</label>
                    <div class="col-sm-10">
                      <div class="fll" style="width: 80%"><input type="text"  value="${spuImport.subTitle}" class="form-control specstr" id="spuSubTitle" maxlength="100"></div>
                      <a href="javascript:;" onclick="subtitletoallsku()" class="btn btn-info fll" style="margin-left: 10px">应用到所有规格</a>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">服务支持</label>
                    <div class="col-lg-10" id="servicesupprots">
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>销售价格：</label>
                    <div class="col-lg-2">
                      <div class="input-group ace_group"> <span class="input-group-addon">¥</span>
                        <input type="text" value="${spuImport.price}"  class="form-control required isNoInteger" id="spuPrice">
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">商品品牌</label>
                    <div class="col-lg-10">
                      <select class="search-select" style="width:150px" id="brands">
                      </select>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商品属性：</label>
                    <div class="col-lg-10">
                      <table class="table table-bordered table-striped table-condensed" id="attr">
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商品图片：</label>
                    <div class="col-sm-10">
                      <div id="spupics">
                      </div>
                      <div class="fileupload fileupload-new fll" data-provides="fileupload" style="margin-left:10px">
                        <span class="btn btn-white btn-file tooltips" style="width:82px; height:82px" data-placement="right" data-original-title="建议800px*800px">
                          <i class="icon-plus" style="font-size:50px; color:#ccc; position:relative; top:10px"></i>
                          <input type="file" onchange="uploadPic()" name="image" class="default"/>
                        </span>
                      </div>
                      <button type="button" onclick="pictoallsku()" class="btn btn-info fll" style="margin:24px 0 0 10px">应用到所有规格</button>
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>规格：</label>
                    <div class="col-lg-10">
                      <div>
                        <button type="button" class="btn btn-info" onclick="tochoosespecs()">选择规格</button>
                        <div  class="pull-right freight_box sku_sum" style="float:left; display: none"> 批量填充：
                          <input type="text" name="" value="" placeholder="重量" class="form-control sku_weight">
                          <input type="text" name="" value="" placeholder="价格" class="form-control sku_price">
                          <input type="text" name="" value="" placeholder="库存" class="form-control sku_stock">
                          <button type="button" class="btn btn-info batchall_set">确定</button>
                        </div>
                      </div>
                      <table class="table table-bordered table-striped table-condensed sku_table" style="margin-bottom:0">
                        <thead>
                        <tr id="skuHead">
                        </tr>
                        </thead>
                        <tbody id="skuBodys">
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">PC版详情：</label>
                    <div class="col-lg-10">
                      <div class="summernotedesc"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">移动版详情：</label>
                    <div class="col-lg-10">
                      <div class="summernotemobile"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo标题：</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" value="${spuImport.seoTitle}" id="seoTitle"></input>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo关键字：</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" value="${spuImport.seoKeywords}" id="seoKeywords" ></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo描述：</label>
                    <div class="col-lg-10">
                      <input type="text" class="form-control" value="${spuImport.seoDesc}" id="seoDesc" ></textarea>
                    </div>
                  </div>
                </form>
                <div style="width:220px; margin:20px auto"> <a class="btn btn-default btn-lg panel_prev" href="javascript:;">上一步</a> <a id="addSpuId" class="btn btn-info btn-lg" href="javascript:;" onclick="addSpu()">发布商品</a> </div>
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
<div class="modal fade goods_sku" id="addSkuDia">
  <div class="modal-dialog" style="width:900px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择规格</h4>
      </div>
      <div class="modal-body" id="specHtmls">
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" onclick="chooseSpecs()" type="button">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade del_sku">
  <input type="hidden" id="deleteId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">确认提示</h4>
      </div>
      <div class="modal-body">要删除此规格数据吗?</div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" onclick="deleteSku()" type="button">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade price_Modal" style="display: none;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">操作提示</h4>
      </div>
      <div class="modal-body">请输入正确的值！</div>
      <div class="modal-footer">
        <button class="btn btn-danger" type="button" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="edit_vip_price">
  <input type="hidden" id="customerPriceSkuId">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">编辑会员价格</h4>
      </div>
      <div class="modal-body">
        <form id="customerLevelForm">
        <table class="table table-bordered table-striped table-condensed sku_table" style="margin-bottom:0">
          <thead>
          <tr>
            <th>会员等级</th>
            <th>价格(元)</th>
          </tr>
          </thead>
          <tbody id="levels">
          </tbody>
        </table>
          </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" onclick="saveCustomerPrice()" type="button">确定</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="edit_dialog">
  <input type="hidden" id="skuuniqueid">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">编辑规格详情</h4>
      </div>
      <div class="modal-body">
        <div class="alert alert-warning fade in">
          <button data-dismiss="alert" class="close close-sm" type="button"> <i class="icon-remove"></i> </button>
          <strong>注意！</strong> 该页详情会在此规格商品详情页显示,规格商品图片如不上传则显示商品默认图片。 </div>
        <form id="addSkuForm" enctype="multipart/form-data"  method="post">
          <div class="form-horizontal tasi-form">
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>商品标题：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control required specstr"  maxlength="100" id="skuname">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">商品副标题：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control specstr" id="skusubtitle">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">商品图片：</label>
              <div class="col-sm-9">
                <div id="skupics">
                </div>
                <div class="fileupload fileupload-new fll" data-provides="fileupload" style="margin-left:10px">
                <span class="btn btn-white btn-file tooltips" style="width:82px; height:82px" data-placement="right"
                      data-original-title="建议800px*800px">
                  <i class="icon-plus" style="font-size:50px; color:#ccc; position:relative; top:10px"></i>
                  <input type="file" name="image"  onchange="uploadSkuPic()" class="default"/>
                </span>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">货号：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control specstr" maxlength="20" id="skuno">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">预警库存：</label>
              <div class="col-sm-9">
                <input type="text" class="form-control digits" maxlength="5" id="skuwarningstock">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">是否上架：</label>
              <div class="col-lg-9">
                <label class="checkbox-inline" style="padding-left:0">
                  <input type="radio" name="up" value="1" checked>
                  立即上架 </label>
                <label class="checkbox-inline" style="padding-left:0">
                  <input type="radio" name="up" value="0">
                  下架 </label>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" onclick="updatesku()" type="button">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- modal -->

<!-- js placed at the end of the document so the pages load faster --> 
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--common script for all pages--> 
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page only--> 
<script type="text/javascript" src="<%=basePath %>/res/js/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script src="<%=basePath %>/res/assets/dropzone/dropzone.js"></script>
<script src="<%=basePath %>/res/js/jquery.validate.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/assets/summernote/summernote.min.js"></script>
<script src="<%=basePath %>/res/assets/summernote/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/res/js/spu/addspu.js"></script>
</body>
</html>
