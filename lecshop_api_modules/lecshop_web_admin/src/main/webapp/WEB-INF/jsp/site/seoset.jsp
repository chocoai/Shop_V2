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
    <title>SEO设置 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
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
                        <li><a href="#">系统</a></li>
                        <li><a href="#">系统管理</a></li>
                        <li class="active">SEO设置</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> SEO设置</header>
                        <div class="panel-body">
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">SEO设置</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab2">网站地图</a></li>
                                        <li class=""><a data-toggle="tab" href="#tab3">Robots文件</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>SEO标题：</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="title">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>SEO关键字：</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" id="keyword">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>SEO描述：</label>
                                                    <div class="col-sm-10">
                                                        <textarea rows="6" class="form-control" id="seoDesc"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">是否启用：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" id="isOpen">
                                                            <option value="1">启用</option>
                                                            <option value="0">不启用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveSystemSeo()">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="alert alert-warning fade in">
                                                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                                                <p><strong>注意！</strong> 网站地图设置，搜索引擎收录http://shoptest.lecshop.cn:80的链接,生成后可在地址栏输入http://shoptest.lecshop.cn:80/sitemap.xml查看。</p>
                                                <p><strong>XML Sitemap</strong> 通常称为Sitemap。 简单来讲，Sitemap 就是网站内部所有链接的列表。 制作Sitemap，并提交给搜索引擎可以使网站的内容完全被收录，包括那些隐藏比较深的页面。这是一种网站与搜索引擎对话的好方式。</p>
                                            </div>
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-10">
                                                        <button type="button" class="btn btn-primary">生成网站地图</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">所属平台：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option value="PC版">PC版</option>
                                                            <option value="移动版">移动版</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>Sitemap内容：</label>
                                                    <div class="col-sm-10">
                                                        <textarea rows="6" class="form-control">网站内部所有链接的列表,提交给搜索引擎可以使网站的内容完全被收录</textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="tab3" class="tab-pane">
                                            <div class="alert alert-warning fade in">
                                                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                                                <p><strong>注意！</strong> roborts.txt文件设置，主要设置Kstore前台网站哪些可以收录,哪些不允许收录。</p>
                                                <p><strong>robots.txt是什么？</strong></p>
                                                <p>如果您的商城里面某个页面不想让百度和google收录，该怎么办？<br>
                                                    其实搜索引擎已经和我们达成一个约定，如果我们按约定那样做了，它们就不会被收录。这个文件就是：robots.txt。<br>
                                                    robots.txt是一个最简单的.txt文件，用以告诉搜索引擎哪些网页可以收录，哪些不允许收录。</p>
                                                <p><strong>关于robots.txt您需要注意以下几点：</strong></p>
                                                <p>1.如果你的站点对所有搜索引擎公开，则不用做这个文件或者robots.txt为空就行。<br>
                                                    2.一般情况下，robots.txt里只写着两个函数：User-agent和 Disallow。<br>
                                                    3.有几个禁止，就得有几个Disallow函数，并分行描述。<br>
                                                    4.至少要有一个Disallow函数，如果都允许收录，则写: Disallow: 如果都不允许收录，则写:Disallow: / (注：只是差一个斜杠)。</p>
                                                <p><strong>补充说明：</strong></p>
                                                <p>User-agent: * 星号说明允许所有搜索引擎收录<br>
                                                    Disallow: /search.html 说明 search.html 这个页面禁止搜索引擎抓取。<br>
                                                    Disallow: /index.php? 说明类似这样的页面都禁止搜索引擎抓取。</p>
                                            </div>
                                            <div class="form-horizontal tasi-form set-form">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">所属平台：</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option value="PC版">PC版</option>
                                                            <option value="移动版">移动版</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><span class="label_red">*</span>Sitemap内容：</label>
                                                    <div class="col-sm-10">
                            <textarea rows="6" class="form-control">
User-Agent: *
Allow: /
Disallow: /css
Disallow: /face
Disallow: /js
Disallow: /homeFirstPage
Disallow: /i9tea
Disallow: /images
Disallow: /index*
Disallow: /js
Disallow: /plugins
Disallow: /social
Disallow: /store
                            </textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-5">
                                                        <button type="button" class="btn btn-success btn-lg save_btn">保存设置</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
<div class="modal fade" id="success_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">设置保存成功！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确定</button>
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
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>

<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>

<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/js/site/seoset.js"></script>
<script>
    $('.save_btn').click(function () {
        $('#success_dialog').modal('show');
    });
</script>
</body>
</html>
