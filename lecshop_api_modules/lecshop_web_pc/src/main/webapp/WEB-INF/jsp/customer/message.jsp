<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
<link href="<%=basePath %>/res/css/user.css" type="text/css" rel="stylesheet">
<script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>/res/js/common_usercenter.js"></script>
</head>

<body>
<div class="shortcut">
  <div class="container">
    <ul class="flr">
      <li>您好，欢迎来到乐商商城</li>
      <li> <a href="#">[请登录]</a> <a class="red" href="#">[免费注册]</a> </li>
      <li class="spacer"></li>
      <li class="shortcut_nav pos_re"><a href="#">我的订单<s></s></a>
        <div class="shortcut_nav_info" style="width:100px">
          <a href="#">待处理订单 <span class="red">12</span></a>
          <a href="#">已完成订单 <span class="red">123</span></a>
          <a href="#">已取消订单 <span class="red">1</span></a>
          <a href="#">已退货订单 <span class="red">45</span></a>
        </div>
      </li>
      <li class="spacer"></li>
      <li class="shortcut_nav pos_re"> <a href="#">会员中心<s></s></a>
        <div class="shortcut_nav_info">
          <a href="#">个人信息</a>
          <a href="#">账户安全</a>
          <a href="#">我的优惠券</a>
          <a href="#">我的收藏</a>
          <a href="#">我的消息</a>
        </div>
      </li>
      <li class="spacer"></li>
      <li class="bdsharebuttonbox"><a href="javascript:;" class="bds_more" data-cmd="more">分享给好友</a></li>
    </ul>
  </div>
</div>
<div class="user_top">
  <div class="container pos_re clearfix" style="z-index:3">
    <div class="logo_main"><a class="logo" href="#"><img src="../images/logo_w.png" alt=""/></a></div>
    <div class="navitems">
      <ul>
        <li>
          <a target="_self" href="">首页</a>
        </li>
        <li style="margin:0 5px">
          <div class="dl">
            <div class="dt">
              <span class="myjd-set">账户设置</span>
              <b></b>
            </div>
            <div class="dd">
              <a href="" target="_self"><span>个人信息</span></a>
              <a href="" target="_self"><span>账户安全</span></a>
              <a href="" target="_self"><span>收货地址</span></a>
            </div>
          </div>
        </li>
        <li>
          <a href="" target="_self">消息<i>34</i></a>
        </li>
      </ul>
    </div>
    <div class="search_content">
      <div class="search_main">
        <input class="search_input" type="text" placeholder="路由器">
        <input class="search_btn" type="button" value="搜索">
      </div>
    </div>
  </div>
</div>
<div class="container clearfix">
  <div class="content clearfix">
    <div class="sub">
      <div class="menu">
        <dl>
          <dt>订单中心</dt>
          <dd><a href="" target="_self">我的订单</a></dd>
          <dd><a href="" target="_self">评价晒单</a></dd>
        </dl>
        <dl>
          <dt>关注中心</dt>
          <dd><a href="" target="_self">关注的商品</a></dd>
          <dd><a href="" target="_self">关注的店铺</a></dd>
          <dd><a href="" target="_self">浏览历史</a></dd>
        </dl>
        <dl>
          <dt>账户中心</dt>
          <dd><a href="" target="_self">个人信息</a></dd>
          <dd><a href="" target="_self">账户安全</a></dd>
          <dd><a href="" target="_self">消费记录</a></dd>
          <dd><a href="" target="_self">我的积分</a></dd>
          <dd><a href="" target="_self">我的预存款</a></dd>
          <dd><a href="" target="_self">我的优惠券</a></dd>
          <dd><a href="" target="_self">收货地址</a></dd>
        </dl>
        <dl>
          <dt>消息中心</dt>
          <dd class="curr"><a href="" target="_self">我的消息</a></dd>
        </dl>
      </div>
    </div>
    <div class="main">
      <div class="myfollow-bd">
        <ul class="tab-trigger">
          <li class="curr"><a href="">我的消息</a></li>
        </ul>

        <div class="myfollow-main" style="clear:both">
          <div class="clearfix" id="customermessages">
          </div>
          <div class="mt20 clearfix">
              <div class=" flr" id="messagePage">
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container service_main bor_service clearfix">
  <ul class="service_box">
    <li>购物指南</li>
    <li><a href="#" target="_blank">购物流程</a> </li>
    <li><a href="#" target="_blank">账户安全</a> </li>
    <li><a href="#" target="_blank">联系客服</a> </li>
    <li><a href="#" target="_blank">会员介绍</a> </li>
  </ul>
  <ul class="service_box">
    <li>配送方式</li>
    <li><a href="#" target="_blank">商城快递</a> </li>
    <li><a href="#" target="_blank">配送服务查询</a> </li>
    <li><a href="#" target="_blank">上门自提</a> </li>
    <li><a href="#" target="_blank">物流费用收费标准</a> </li>
  </ul>
  <ul class="service_box">
    <li>支付方式</li>
    <li><a href="#" target="_blank">货到付款</a> </li>
    <li><a href="#" target="_blank">在线支付</a> </li>
    <li><a href="#" target="_blank">分期付款</a> </li>
    <li><a href="#" target="_blank">邮局汇款</a> </li>
  </ul>
  <ul class="service_box">
    <li>售后服务</li>
    <li><a href="#" target="_blank">售后政策</a> </li>
    <li><a href="#" target="_blank">价格保护</a> </li>
    <li><a href="#" target="_blank">退单说明</a> </li>
    <li><a href="#" target="_blank">取消订单</a> </li>
  </ul>
  <ul class="service_box">
    <li>联系我们</li>
    <li><a href="#" target="_blank">商家入驻</a> </li>
    <li><a href="#" target="_blank">营销中心</a> </li>
    <li><a href="#" target="_blank">关于我们</a> </li>
    <li><a href="#" target="_blank">广告服务</a> </li>
  </ul>
</div>
<div class="container footer_main">
  <div class="footer_text"><a href="#" target="_blank">关于我们</a>|<a href="#" target="_blank">联系我们</a>|<a href="#" target="_blank">人才招聘</a>|<a href="#" target="_blank">商家入驻</a>|<a href="#" target="_blank">广告服务</a>|<a href="#" target="_blank">手机版</a>|<a href="#" target="_blank">友情链接</a>|<a href="#" target="_blank">销售联盟</a>|<a href="#" target="_blank">English Site</a></div>
  <div class="footer_text">南京市公安局朝阳分局备案编号110105014669&nbsp;&nbsp;|&nbsp;&nbsp;京ICP证070359号&nbsp;&nbsp;|&nbsp;&nbsp;互联网药品信息服务资格证编号(京)-非经营性-2011-0034</div>
  <div class="footer_text">Copyright © 2004-2014&nbsp;&nbsp;江苏乐商网络科技有限公司&nbsp;版权所有</div>
  <div><a href="#" target="_blank"><img src="../images/54b8871eNa9a7067e.png" width="103" height="32" alt=""/></a><a href="#" target="_blank"><img src="../images/54b8872dNe37a9860.png" width="103" height="32" alt=""/></a><a href="#" target="_blank"><img src="../images/54b8874bN694454a5.png" width="103" height="32" alt=""/></a><a href="#" target="_blank"><img src="../images/54b8875fNad1e0c4c.png" width="103" height="32" alt=""/></a></div>
</div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{"bdSize":16}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/layui/layui.js"></script>


<script>

    // 分页组件
    var laypage;

    layui.use(['laypage'], function () {
        laypage = layui.laypage;
        // 引入分页组件后执行查询
        queryMessage(0);
    });


    function queryMessage(pageNum) {
        LSFetch({
            url: '/customer/querymessage.htm',
            data: {pageNum: pageNum},
            success: function (res) {
                if (res.data.length > 0) {
                    var htmls = '';
                    for (var i = 0; i < res.data.length; i++) {
                        htmls += '<div class="mg-coupon"> <span class="mg-time">' + res.data[i].createTime + '</span> <div class="mg-box no-look"> <div class="mg-title"> <h4 class="fll">' + res.data[i].title + '</h4>' +
                                '<s style="cursor:pointer"></s> </div> <div class="mg-content clearfix">' + res.data[i].content + '</div> </div> </div>';
                    }

                    $("#customermessages").html(htmls);

                } else {

                }
                laypage({
                    cont: 'messagePage',
                    pages: res.totalPage,
                    skin: '#5FB878',
                    curr: pageNum + 1,
                    jump: function (obj, first) {
                        if (!first) {
                            queryMessage(obj.curr - 1);
                        }
                    }
                });
            }
        })
    }
</script>
</body>
</html>
