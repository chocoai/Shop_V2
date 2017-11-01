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
  <title>会员列表 - 乐商商城</title>
  <!-- Bootstrap core CSS -->
  <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
  <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

  <!--external css-->
  <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
  <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet" />
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
            <header class="panel-heading"> 优惠券 </header>
            <div class="panel-body">
              <header class="panel-heading tab-bg-dark-navy-blue">
                <ul class="nav nav-tabs">
                  <li> <a onclick="changepoint()">基本信息</a> </li>
                  <li> <a href="">订单信息</a> </li>
                  <li class="active"> <a href="">优惠券</a> </li>
                  <li> <a href="">商品关注</a> </li>
                  <li> <a href="" onclick="querypoint()">用户积分</a> </li>
                </ul>
              </header>
              <div class="form-inline m-bot15 clearfix search-form" style="margin-top: 15px">
                <div class="form-group">
                  <label class="control-label">使用状态</label>
                  <div class="clearfix">
                    <select class="form-control" name="">
                      <option value="" selected="selected">
                        选择类型
                      </option>
                      <option value="0">
                        已使用优惠券
                      </option>
                      <option value="1">
                        未使用优惠券
                      </option>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label">&nbsp;</label>
                  <div class="clearfix">
                    <button type="submit" class="btn btn-info">搜索</button>
                  </div>
                </div>
              </div>
              <div class="clearfix">
                <div class="adv-table clearfix">
                  <table class="display table table-bordered table-striped">
                    <thead>
                    <tr>
                      <th>优惠券号码</th>
                      <th>类别</th>
                      <th>优惠金额</th>
                      <th>状态</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ customer.coupons}" var="coupon" varStatus="i">
                      <fmt:formatDate value="${coupon.createTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
                      <c:if test="${coupon.codeStatus == 1 }">
                        <tr>
                          <td>${coupon.couponNo }</td>
                          <td>
                            <c:if test="${coupon.ruleType==1}">
                              直降
                            </c:if>
                            <c:if test="${coupon.ruleType==2}">
                              满减
                            </c:if>
                          </td>
                          <td>
                            <span class="label label-success">未使用</span>
                            <span class="label label-default">已使用</span>
                          </td>
                          <td>
                            <c:if test="${coupon.ruleType==1}">
                              ${coupon.downPrice }
                            </c:if>
                            <c:if test="${coupon.ruleType==2}">
                              满${coupon.fullPrice }减${coupon.reductionPrice }
                            </c:if>
                          </td>
                          <td class="operation_box"><button class="btn btn-success btn-xs"><i class="icon-eye-open"></i> 查看</button></td>
                        </tr>
                      </c:if>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div style="width:142px; margin:20px auto"> <a class="btn btn-default btn-lg" href="javascript:history.go(-1);">返回会员列表</a> </div>
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
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>

</body>
</html>
