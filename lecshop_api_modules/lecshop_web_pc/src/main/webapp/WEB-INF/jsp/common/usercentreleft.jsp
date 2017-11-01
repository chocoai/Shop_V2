<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="sub">
    <div class="menu">
        <dl>
            <dt>订单中心</dt>
            <dd><a href="" target="_self">我的订单</a></dd>
            <dd><a href="" target="_self">评价晒单</a></dd>
        </dl>
        <dl>
            <dt>关注中心</dt>
            <dd><a href="toattentionsku.htm" target="_self">关注的商品</a></dd>
            <dd><a href="toattentionstore.htm" target="_self">关注的店铺</a></dd>
            <dd><a href="tohistorylist.htm" target="_self">浏览历史</a></dd>
        </dl>
        <dl>
            <dt>账户中心</dt>
            <dd><a href="topersonalinfo.htm" target="_self">个人信息</a></dd>
            <dd><a href="" target="_self">账户安全</a></dd>
            <dd><a href="" target="_self">消费记录</a></dd>
            <dd><a href="tocustomerpoint.htm" target="_self">我的积分</a></dd>
            <dd><a href="topredeposit.htm" target="_self">我的预存款</a></dd>
            <dd><a href="tomycoupon.htm" target="_self">我的优惠券</a></dd>
            <dd><a href="" target="_self">收货地址</a></dd>
        </dl>
        <dl>
            <dt>消息中心</dt>
            <dd><a href="tocustomerinfo.htm" target="_self">我的消息</a></dd>
        </dl>
    </div>
</div>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script>
    $(function () {
        $(".menu dd").each(function () {
            if ($(this).children("a").attr("href") == window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1)) {
                $(this).addClass("curr");
            }
        });
        $('.navitems li').hover(
            function () {
                $(this).find('.dl').addClass('hover');
            },
            function () {
                $(this).find('.dl').removeClass('hover');
            }
        );
    });
</script>

