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
<title>修改商品 - 乐商商城</title>
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
    <input id="spuId" type="hidden" value="${spuId}">

    <section class="wrapper site-min-height">
      <div class="row">
        <div class="col-lg-12"> 
          <!--breadcrumbs start -->
          <ul class="breadcrumb">
            <li><a href="#">商品</a></li>
            <li><a href="#">商品管理</a></li>
            <li class="active">修改商品</li>
          </ul>
          <!--breadcrumbs end --> 
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <section class="panel">
            <header class="panel-heading"> 修改商品 </header>
            <div class="panel-body">
              <div class="mypanel">
                <form class="form-horizontal tasi-form" id="updatespu">
                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>商品标题：</label>
                    <div class="col-sm-10">
                      <div class="fll" style="width: 80%"><input disabled type="text" class="form-control required specstr" id="spuName" maxlength="100"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">商品副标题：</label>
                    <div class="col-sm-10">
                      <div class="fll" style="width: 80%"><input disabled type="text"  class="form-control specstr" id="spuSubTitle" maxlength="100"></div>
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
                        <input type="text" disabled  class="form-control required isNoInteger" id="spuPrice">
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">商品品牌</label>
                    <div class="col-lg-10">
                      <select disabled class="search-select" style="width:150px" id="brands">
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
                    </div>
                  </div>

                  <div class="form-group">
                    <label class="col-sm-2 control-label"><span class="label_red">*</span>规格：</label>
                    <div class="col-lg-10">
                      <div>
                        <button type="button" class="btn btn-info" onclick="toBatchAudit()"><i class="icon-search"></i>批量审核</button>
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
                      <div class="summernotedesc" style="width:100%;max-height:400px;overflow:auto;padding:8px;min-height:44px;border:1px solid #e2e2e4;border-radius:4px"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">移动版详情：</label>
                    <div class="col-lg-10">
                      <div class="summernotemobile" style="width:100%;max-height:400px;overflow:auto;padding:8px;min-height:44px;border:1px solid #e2e2e4;border-radius:4px"></div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo标题：</label>
                    <div class="col-lg-10">
                      <input disabled type="text" class="form-control"  id="seoTitle"></input>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo关键字：</label>
                    <div class="col-lg-10">
                      <input disabled type="text" class="form-control"  id="seoKeywords" ></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label">seo描述：</label>
                    <div class="col-lg-10">
                      <input disabled type="text" class="form-control"  id="seoDesc" ></textarea>
                    </div>
                  </div>
                </form>
                <div style="width:252px; margin:20px auto"> <a class="btn btn-default btn-lg panel_prev" href="javascript:history.go(-1);">返回</a> </div>
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
<div class="modal fade" id="edit_dialog">
  <input type="hidden" id="skuuniqueid">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">编辑规格详情</h4>
      </div>
      <div class="modal-body">
        <form id="addSkuForm" enctype="multipart/form-data"  method="post">
          <div class="form-horizontal tasi-form">
            <div class="form-group">
              <label class="col-sm-3 control-label"><span class="label_red">*</span>商品标题：</label>
              <div class="col-sm-9">
                <input type="text" disabled class="form-control required specstr"  maxlength="100" id="skuname">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">商品副标题：</label>
              <div class="col-sm-9">
                <input type="text" disabled class="form-control specstr" id="skusubtitle">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">商品图片：</label>
              <div class="col-sm-9">
                <div id="skupics">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">货号：</label>
              <div class="col-sm-9">
                <input type="text" disabled class="form-control specstr" maxlength="20" id="skuno">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">预警库存：</label>
              <div class="col-sm-9">
                <input type="text" disabled class="form-control digits" maxlength="5" id="skuwarningstock">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">是否上架：</label>
              <div class="col-lg-9">
                <label class="checkbox-inline" style="padding-left:0">
                  <input disabled type="radio" name="up" value="1" checked>
                  立即上架 </label>
                <label class="checkbox-inline" style="padding-left:0">
                  <input disabled type="radio" name="up" value="0">
                  下架 </label>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="audit_dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">审核</h4>
      </div>
      <div class="modal-body">
        <input type="hidden" id="auditSkuId">
        <input type="hidden" id="auditType">
          <div class="form-horizontal tasi-form">
            <div class="form-group" style="border: 0">
              <label class="col-sm-3 control-label">审核：</label>
              <div class="col-sm-2">
                <select id="audit" class="form-control" name="">
                  <option value="1">通过</option>
                  <option value="2">不通过</option>
                </select>
              </div>
            </div>
            <div class="form-group" style="display: none">
              <label class="col-sm-3 control-label">拒绝原因：</label>
              <div class="col-sm-9">
                <textarea rows="3" class="form-control specstr" id="reason" maxlength="255"></textarea>
              </div>
            </div>
          </div>
      </div>
      <div class="modal-footer">
        <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
        <button class="btn btn-success" type="button" onclick="audit()">保存</button>
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
<script src="<%=basePath %>/res/js/store/auditspu.js"></script>
<script>
  $('#audit').change(function(){
    if ($(this).find('option:selected').val() == 2){
      $(this).parents('.form-group').next().show();
      $(this).parents('.form-group').css('border-bottom','1px solid #eff2f7')
    }
    else {
      $(this).parents('.form-group').next().hide();
      $(this).parents('.form-group').css('border','0')
    }
  })
</script>
</body>
</html>
