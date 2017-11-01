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
    <title>后台管理 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/xchart/xcharts.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="stylesheet" href="<%=basePath %>/res/css/owl.carousel.css" type="text/css">
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>/res/js/html5shiv.js"></script>
    <script src="<%=basePath %>/res/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="full-width">
<section id="container">
    <!-- 引用上侧 -->
    <jsp:include page="../common/header.jsp"></jsp:include>
    <section id="main-content">
        <section class="wrapper">
            <!--state overview start-->
            <div class="row state-overview">
                <div class="col-lg-3 col-sm-6">
                    <section class="panel">
                        <div class="symbol terques"><i class="icon-user"></i></div>
                        <div class="value">
                            <h1 class="count"> 0 </h1>
                            <p>今日新增用户</p>
                        </div>
                    </section>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <section class="panel">
                        <div class="symbol red"><i class="icon-tags"></i></div>
                        <div class="value">
                            <h1 class=" count2"> 0 </h1>
                            <p>今日销售额</p>
                        </div>
                    </section>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <section class="panel">
                        <div class="symbol yellow"><i class="icon-shopping-cart"></i></div>
                        <div class="value">
                            <h1 class=" count3"> 0 </h1>
                            <p>今日订单数</p>
                        </div>
                    </section>
                </div>
                <div class="col-lg-3 col-sm-6">
                    <section class="panel">
                        <div class="symbol blue"><i class="icon-bar-chart"></i></div>
                        <div class="value">
                            <h1 class=" count4"> 0 </h1>
                            <p>本周销售额</p>
                        </div>
                    </section>
                </div>
            </div>
            <!--state overview end-->

            <div class="row">
                <div class="col-lg-8">
                    <!--custom chart start-->
                    <section class="panel">
                        <header class="panel-heading"> 本周销售额</header>
                        <div class="panel-body">
                            <figure class="demo-xchart" id="chart" style="height:333px"></figure>
                        </div>
                    </section>
                    <!--custom chart end-->
                </div>
                <div class="col-lg-4">
                    <!--new earning start-->
                    <div class="panel terques-chart">
                        <div class="panel-body chart-texture">
                            <div class="chart">
                                <div class="heading"><span>订单数统计</span> <span>最后更新：2016-08-01</span></div>
                                <div class="sparkline" data-type="line" data-resize="true" data-height="75" data-width="90%" data-line-width="1" data-line-color="#fff" data-spot-color="#fff"
                                     data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="4" data-data="[200,135,667,333,526,996,564]"></div>
                            </div>
                        </div>
                        <div class="chart-tittle"><span class="title">本周订单数 1,今日订单数 0</span></div>
                    </div>
                    <!--new earning end-->

                    <!--total earning start-->
                    <div class="panel green-chart">
                        <div class="panel-body">
                            <div class="chart">
                                <div class="heading"><span>新用户统计</span> <span>最后更新：2016-08-01</span></div>
                                <div class="sparkline" data-type="line" data-resize="true" data-height="75" data-width="90%" data-line-width="1" data-line-color="#fff" data-spot-color="#fff"
                                     data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="4" data-data="[10,35,37,33,26,56,64]"></div>
                            </div>
                        </div>
                        <div class="chart-tittle"><span class="title">本周新增用户4,今日新增用户3</span></div>
                    </div>
                    <!--total earning end-->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <!--user info table start-->
                    <section class="panel">
                        <div class="panel-body progress-panel">
                            <div class="task-progress">
                                <h1><a href="#">待处理订单</a></h1>
                                <p>您有132条未处理订单</p>
                            </div>
                        </div>
                        <table class="table table-hover table-index">
                            <tbody>
                            <tr>
                                <td>商品</td>
                                <td>订单号</td>
                                <td>总价/数量</td>
                                <td>买家信息</td>
                                <td>下单时间</td>
                                <td>订单状态</td>
                                <td>实付金额</td>
                                <td>商家</td>
                                <td>操作</td>
                            </tr>
                            <tr>
                                <td><img src="" width="50" height="50" alt=""/></td>
                                <td>2016072717000231</td>
                                <td>3398.00(1件)</td>
                                <td>用户名: y123456 收件人: hghgfh 18545678987</td>
                                <td>2016-07-30 10:13</td>
                                <td><span class="label label-info">待发货</span></td>
                                <td>3598.00</td>
                                <td>乐商小店</td>
                                <td>
                                    <button type="button" class="btn btn-info "><i class="icon-truck"></i> 发货</button>
                                </td>
                            </tr>
                            <tr>
                                <td><img src="" width="50" height="50" alt=""/></td>
                                <td>2016072717000231</td>
                                <td>3398.00(1件)</td>
                                <td>用户名: y123456 收件人: hghgfh 18545678987</td>
                                <td>2016-07-30 10:13</td>
                                <td><span class="label label-info">待发货</span></td>
                                <td>3598.00</td>
                                <td>乐商小店</td>
                                <td>
                                    <button type="button" class="btn btn-info "><i class="icon-truck"></i> 发货</button>
                                </td>
                            </tr>
                            <tr>
                                <td><img src="" width="50" height="50" alt=""/></td>
                                <td>2016072717000231</td>
                                <td>3398.00(1件)</td>
                                <td>用户名: y123456 收件人: hghgfh 18545678987</td>
                                <td>2016-07-30 10:13</td>
                                <td><span class="label label-info">待发货</span></td>
                                <td>3598.00</td>
                                <td>乐商小店</td>
                                <td>
                                    <button type="button" class="btn btn-info "><i class="icon-truck"></i> 发货</button>
                                </td>
                            </tr>
                            <tr>
                                <td><img src="" width="50" height="50" alt=""/></td>
                                <td>2016072717000231</td>
                                <td>3398.00(1件)</td>
                                <td>用户名: y123456 收件人: hghgfh 18545678987</td>
                                <td>2016-07-30 10:13</td>
                                <td><span class="label label-info">待发货</span></td>
                                <td>3598.00</td>
                                <td>乐商小店</td>
                                <td>
                                    <button type="button" class="btn btn-info "><i class="icon-truck"></i> 发货</button>
                                </td>
                            </tr>
                            <tr>
                                <td><img src="" width="50" height="50" alt=""/></td>
                                <td>2016072717000231</td>
                                <td>3398.00(1件)</td>
                                <td>用户名: y123456 收件人: hghgfh 18545678987</td>
                                <td>2016-07-30 10:13</td>
                                <td><span class="label label-info">待发货</span></td>
                                <td>3598.00</td>
                                <td>乐商小店</td>
                                <td>
                                    <button type="button" class="btn btn-info "><i class="icon-truck"></i> 发货</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <!--user info table end-->
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

<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/jquery-1.8.3.min.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/jquery.sparkline.js" type="text/javascript"></script>
<script src="<%=basePath %>/res/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="<%=basePath %>/res/js/owl.carousel.js"></script>
<script src="<%=basePath %>/res/js/jquery.customSelect.min.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>

<!--script for this page-->
<script src="<%=basePath %>/res/assets/xchart/d3.v3.min.js"></script>
<script src="<%=basePath %>/res/assets/xchart/xcharts.min.js"></script>
<script src="<%=basePath %>/res/js/sparkline-chart.js"></script>
<script src="<%=basePath %>/res/js/easy-pie-chart.js"></script>
<script src="<%=basePath %>/res/js/count.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script>
    (function () {
        var data = [
            {
                "xScale": "ordinal",
                "comp": [],
                "main": [{
                    "className": ".main.l1",
                    "data": [{
                        "y": 12,
                        "x": "2012-11-19T00:00:00"
                    },
                        {
                            "y": 18,
                            "x": "2012-11-20T00:00:00"
                        },
                        {
                            "y": 8,
                            "x": "2012-11-21T00:00:00"
                        },
                        {
                            "y": 7,
                            "x": "2012-11-22T00:00:00"
                        },
                        {
                            "y": 6,
                            "x": "2012-11-23T00:00:00"
                        },
                        {
                            "y": 12,
                            "x": "2012-11-24T00:00:00"
                        },
                        {
                            "y": 8,
                            "x": "2012-11-25T00:00:00"
                        }]
                },
                ],
                "type": "line-dotted",
                "yScale": "linear"
            }];
        var order = [0, 1, 0, 2],
            i = 0,
            xFormat = d3.time.format('%A'),
            chart = new xChart('line-dotted', data[order[i]], '#chart', {
                axisPaddingTop: 5,
                dataFormatX: function (x) {
                    return new Date(x);
                },
                tickFormatX: function (x) {
                    return xFormat(x);
                },
                timing: 1250
            }),
            rotateTimer,
            toggles = d3.selectAll('.multi button'),
            t = 3500;

        function updateChart(i) {
            var d = data[i];
            chart.setData(d);
            toggles.classed('toggled',
                function () {
                    return (d3.select(this).attr('data-type') === d.type);
                });
            return d;
        }

        toggles.on('click',
            function (d, i) {
                clearTimeout(rotateTimer);
                updateChart(i);
            });

        function rotateChart() {
            i += 1;
            i = (i >= order.length) ? 0 : i;
            var d = updateChart(order[i]);
            rotateTimer = setTimeout(rotateChart, t);
        }

        rotateTimer = setTimeout(rotateChart, t);
    }());
</script>
</body>
</html>
