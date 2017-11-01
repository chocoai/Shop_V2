<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息</title>
    <link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/user.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
</head>
<body>
<input type="hidden" value="<%=basePath%>" id="basePath">
<jsp:include page="../common/head.jsp"></jsp:include>
<jsp:include page="../common/usercentrehead.jsp"></jsp:include>
<div class="container clearfix">
    <div class="content clearfix">
        <jsp:include page="../common/usercentreleft.jsp"></jsp:include>
        <div class="main">
            <div class="myfollow-hd">
                <ul class="tab-trigger">
                    <li class="curr"><a onclick="changeTable(this)" target="baseInfo">基本信息</a></li>
                    <li><a onclick="changeTable(this)" target="headPortrait">头像照片</a></li>
                    <li><a onclick="changeTable(this)" target="moreInfo">更多个人信息</a></li>
                </ul>
                <div class="myfollow-bd fll">
                    <div class="user-set" id="baseInfo">
                        <div class="item">
                            <span class="label">昵称：</span>
                            <div class="fll">
                                <input type="text" class="itxt" maxlength="20" name="nickName" value="">
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">性别：</span>
                            <div class="fll">
                                <input type="radio" name="gender" value="0" checked="checked"><label style="margin-right:5px">保密</label>
                                <input type="radio" name="gender" value="1"><label style="margin-right:5px">男</label>
                                <input type="radio" name="gender" value="2"><label>女</label>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">生日：</span>
                            <div class="fll">
                                <select name="year" class="selt" id="birthdayYear">
                                </select>
                                <label class="ml5 mr5">年</label>
                                <select name="month" class="selt" id="birthdayMonth">
                                </select>
                                <label class="ml5 mr5">月</label>
                                <select name="day" class="selt" id="birthdayDay">
                                </select>
                                <label class="ml5 mr5">日</label>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">用户名：</span>
                            <div class="fll">
                                <strong name="userName"></strong>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">会员等级：</span>
                            <div class="fll">
                                <strong name="name"></strong>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">真实姓名：</span>
                            <div class="fll">
                                <input type="text" class="itxt" maxlength="20" name="releName" value="">
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">&nbsp;</span>
                            <div class="fll">
                                <a href="javascript:void(0)" class="btn-5" onclick="baseInfoSubmitBtn('baseInfo')">提交</a>
                            </div>
                        </div>
                    </div>
                    <div class="user-set" style="padding:20px;display: none" id="headPortrait">
                        <div class="clearfix">
                            <form id="imgForm" enctype="multipart/form-data" method="post">
                                <input type="file" name="image">
                                <a href="javascript:void(0)" class="btn-5" onclick="uploadPic('imgForm')">上传</a>
                                <img id="loading" style="display: none;" class="float:left" src="<%=basePath %>/res/img/loading.gif">
                            </form>
                            <div id="imgError"
                                 style="width: 300px; border: 1px solid rgb(219, 154, 154); color: rgb(204, 0, 0); text-align: left; display: none; background-color: rgb(255, 232, 232);">
                                文件类型错误。
                            </div>
                            <p style="line-height: 24px;">仅支持JPG、GIF、PNG、JPEG、BMP格式，文件小于4M</p>
                        </div>
                        <div class="img-cont">
                            <div class="img-b">
                                <img alt="" width="150" height="150" src="<%=basePath %>/res/img/userImg.png" name="image">
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">&nbsp;</span>
                            <div class="fll">
                                <a href="javascript:void(0)" class="btn-5" onclick="headPortraitSubmitBtn('headPortrait')">保存</a>
                            </div>
                        </div>
                    </div>
                    <div class="user-set" style="display: none" id="moreInfo">
                        <div class="item">
                            <span class="label">月收入：</span>
                            <div class="fll">
                                <select class="selt" style="width:100px" name="monthlyIncome">
                                    <option value="">请选择</option>
                                    <option value="0">无收入</option>
                                    <option value="1">2000元以下</option>
                                    <option value="2">2000-3999元</option>
                                    <option value="3">4000-5999元</option>
                                    <option value="4">6000-7999元</option>
                                    <option value="5">8000元以上</option>
                                </select>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">婚姻状况：</span>
                            <div class="fll">
                                <input type="radio" name="marriageStatus" value="0" checked="checked"><label style="margin-right:5px">保密</label>
                                <input type="radio" name="marriageStatus" value="1"><label style="margin-right:5px">未婚</label>
                                <input type="radio" name="marriageStatus" value="2"><label>已婚</label>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">身份证号码：</span>
                            <div class="fll">
                                <input type="text" class="itxt" maxlength="20" name="cardId" value="">
                                <div class="prompt-error" style="display: none">请输入正确的身份证号码！居民身份证号码为15或18位</div>
                            </div>
                        </div>
                        <div class="item">
                            <span class="label">&nbsp;</span>
                            <div class="fll">
                                <a href="javascript:void(0)" class="btn-5" onclick="moreInfoSubmitBtn('moreInfo')">保存</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/customer/personalinfo.js"></script>
</body>
</html>
