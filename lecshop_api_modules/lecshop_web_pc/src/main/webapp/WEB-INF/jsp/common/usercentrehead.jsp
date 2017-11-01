<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="user_top">
    <div class="container pos_re clearfix" style="z-index:3">
        <div class="logo_main"><a class="logo" href="#"><img src="<%=basePath %>/res/images/logo_w.png" alt=""/></a></div>
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
                            <a href="topersonalinfo.htm" target="_self"><span>个人信息</span></a>
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