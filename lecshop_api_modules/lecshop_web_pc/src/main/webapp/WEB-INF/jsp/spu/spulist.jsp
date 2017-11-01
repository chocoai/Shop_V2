<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="<%=basePath %>/res/css/css.css" type="text/css" rel="stylesheet">
<script src="<%=basePath %>/res/js/jquery-1.11.3.min.js"></script>
</head>

<body>

<input type="hidden" id="cateId" value="${cateId}">
<input type="hidden" id="keyword" value="${keyword}">

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
<div class="container pos_re clearfix" style="z-index:3">
  <div class="logo_main"><a class="logo" href="#"><img src="images/logo.png" alt=""/></a></div>
  <div class="search_content">
    <div class="search_main">
      <input class="search_input" type="text" placeholder="路由器">
      <input class="search_btn" type="button" value="搜索">
    </div>
    <div class="hotwords_link"><a class="red" href="#" target="_blank">11月11天</a><a href="#" target="_blank">羽绒被</a><a href="#" target="_blank">休闲裤女</a><a href="#" target="_blank">流苏靴</a><a href="#" target="_blank">诺心</a><a href="#" target="_blank">路由器</a></div>
  </div>
  <div class="shopping_cart"> <i class="icon-left"></i><a target="_blank" href="#">我的购物车<span class="red cartNum">3</span></a><i class="icon-right"></i>
    <div class="shopping_cart_layer">
      <div class="spacer"></div>
      <div class="scorder clearfix">购物车中还没有商品，再去逛逛吧！</div>
      <div class="mcBoxList">
        <ul class="scorder clearfix">
          <li class="sc_pic"><a href="#" target="_blank"><img src="images/1433208738660.jpg" alt=""/></a></li>
          <li class="sc_text"><a href="#" target="_blank" title="真维斯夏装新款 女装时尚蕾丝圆领假两件短袖T恤JW-43-273539 8411 浅黄色">真维斯夏装新款
            女装时尚蕾丝圆领假两件短袖T恤JW-43-273539 8411 浅黄色</a></li>
          <li class="sc_cost"><span class="red">¥59<em>×1</em></span><a href="#">删除</a></li>
        </ul>
        <ul class="scorder clearfix">
          <li class="sc_pic"><a href="#" target="_blank"><img src="images/1433128642809.jpg" alt=""/></a></li>
          <li class="sc_text"><a href="#" target="_blank" title="苹果（Apple）iPhone 6 (A1586) 16GB 深空灰 移动联通电信4G手机">苹果（Apple）iPhone
            6 (A1586) 16GB 深空灰 移动联通电信4G手机</a></li>
          <li class="sc_cost"><span class="red">¥4788<em>×1</em></span><a href="#">删除</a></li>
        </ul>
        <ul class="scorder clearfix">
          <li class="sc_pic"><a href="#" target="_blank"><img src="images/14331293979.jpg" alt=""/></a></li>
          <li class="sc_text"><a href="#" target="_blank" title="Apple MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存">Apple
            MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存</a></li>
          <li class="sc_cost"><span class="red">¥6288<em>×1</em></span><a href="#">删除</a></li>
        </ul>
      </div>
      <div class="shopping_cart_footer">
        <div class="price_total">共<b>4</b>件商品　共计<strong class="red">￥ 3436.00</strong></div>
        <a href="#" title="去购物车">去购物车</a> </div>
    </div>
  </div>
</div>
<div class="nav_main">
  <div class="container clearfix">
    <div class="categorys">
      <div class="categorys_title">全部商品分类</div>
      <div class="categorys_list hide">
        <div class="categorys_item"> <a target="_blank" href="#">家用电器</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">手机</a>、<a target="_blank" href="#">数码</a>、<a target="_blank" href="#">商城通信</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">电脑、办公</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">家居</a>、<a target="_blank" href="#">家具</a>、<a target="_blank" href="#">家装</a>、<a target="_blank" href="#">厨具</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">男装</a>、<a target="_blank" href="#">女装</a>、<a target="_blank" href="#">内衣</a>、<a target="_blank" href="#">珠宝</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">个护化妆</a>、<a target="_blank" href="#">清洁用品</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">鞋靴</a>、<a target="_blank" href="#">箱包</a>、<a target="_blank" href="#">钟表</a>、<a target="_blank" href="#">奢侈品</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">运动户外</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">汽车</a>、<a target="_blank" href="#">汽车用品</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">母婴</a>、<a target="_blank" href="#">玩具乐器</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">食品</a>、<a target="_blank" href="#">酒类</a>、<a target="_blank" href="#">生鲜</a>、<a target="_blank" href="#">特产</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">营养保健</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">图书</a>、<a target="_blank" href="#">音像</a>、<a target="_blank" href="#">电子书</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">彩票</a>、<a target="_blank" href="#">旅行</a>、<a target="_blank" href="#">充值</a>、<a target="_blank" href="#">票务</a> </div>
        <div class="categorys_item"> <a target="_blank" href="#">理财</a>、<a target="_blank" href="#">众筹</a>、<a target="_blank" href="#">白条</a>、<a target="_blank" href="#">保险</a> </div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
      <div class="categorys_layer">
        <div class="sub_items">
          <dl>
            <dt><a href="#" target="_blank">大家电</a></dt>
            <dd><a href="#" class="red" target="_blank">平板电视</a><a href="#" target="_blank">空调</a><a href="#" target="_blank">冰箱</a><a href="#" class="red" target="_blank">洗衣机</a><a href="#" target="_blank">家庭影院</a><a href="#" target="_blank">DVD</a><a href="#" target="_blank">迷你音响</a><a href="#" target="_blank">烟机/灶具</a><a href="#" target="_blank">热水器</a><a href="#" target="_blank">消毒柜/洗碗机</a><a href="#" target="_blank">冷柜/冰吧</a><a href="#" target="_blank">酒柜</a><a href="#" target="_blank">家电配件</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">生活电器</a></dt>
            <dd><a href="#" target="_blank">电风扇</a><a href="#" target="_blank">冷风扇</a><a href="#" class="red" target="_blank">净化器</a><a href="http://list.jd.com/list.html?cat=737,738,748" target="_blank">加湿器</a><a href="#" target="_blank">扫地机器人</a><a href="#" target="_blank">吸尘器</a><a href="#" target="_blank">挂烫机/熨斗</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电话机</a><a href="#" target="_blank">清洁机</a><a href="#" target="_blank">除湿机</a><a href="#" target="_blank">干衣机</a><a href="#" target="_blank">收录/音机</a><a href="#" class="red" target="_blank">取暖电器</a><a href="#" target="_blank">其它生活电器</a><a href="#" target="_blank">生活电器配件</a><a href="#" class="red" target="_blank">净水设备</a><a href="#" target="_blank">饮水机</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">厨房电器</a></dt>
            <dd><a href="#" class="red" target="_blank">电压力锅</a><a href="#" target="_blank">电饭煲</a><a href="http://list.jd.com/list.html?cat=737,752,756" class="red" target="_blank">豆浆机</a><a href="#" target="_blank">面包机</a><a href="#" target="_blank">咖啡机</a><a href="#" target="_blank">微波炉</a><a href="#" target="_blank">料理/榨汁机</a><a href="#" target="_blank">电烤箱</a><a href="#" target="_blank">电磁炉</a><a href="#" target="_blank">电饼铛/烧烤盘</a><a href="#" target="_blank">煮蛋器</a><a href="#" target="_blank">酸奶机</a><a href="#" target="_blank">电水壶/热水瓶</a><a href="#" target="_blank">电炖锅</a><a href="#" target="_blank">多用途锅</a><a href="#" target="_blank">果蔬解毒机</a><a href="#" target="_blank">养生壶/煎药壶</a><a href="#" target="_blank">电热饭盒</a><a href="#" target="_blank">其它厨房电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">个护健康</a></dt>
            <dd><a href="#" target="_blank">剃须刀</a><a href="#" target="_blank">剃/脱毛器</a><a href="#" class="red" target="_blank">口腔护理</a><a href="#" target="_blank">电吹风</a><a href="#" target="_blank">美容器</a><a href="#" target="_blank">理发器</a><a href="#" target="_blank">卷/直发器</a><a href="#" target="_blank">按摩椅</a><a href="#" target="_blank">按摩器</a><a href="#" class="red" target="_blank">足浴盆</a><a href="#" target="_blank">血压计</a><a href="#" target="_blank">健康秤/厨房秤</a><a href="#" target="_blank">血糖仪</a><a href="#" target="_blank">体温计</a><a href="#" target="_blank">计步器/脂肪检测仪</a><a href="#" target="_blank">其它健康电器</a></dd>
          </dl>
          <dl>
            <dt><a href="#" target="_blank">五金家装</a></dt>
            <dd><a href="#" class="red" target="_blank">电动工具</a><a href="#" target="_blank">手动工具</a><a href="#" target="_blank">仪器仪表</a><a href="#" target="_blank">浴霸/排气扇</a><a href="#" target="_blank">灯具</a><a href="#" target="_blank">LED灯</a><a href="#" class="red" target="_blank">洁身器</a><a href="#" target="_blank">水槽</a><a href="#" target="_blank">龙头</a><a href="#" target="_blank">淋浴花洒</a><a href="#" target="_blank">厨卫五金</a><a href="#" target="_blank">家具五金</a><a href="#" target="_blank">门铃</a><a href="#" target="_blank">电气开关</a><a href="#" target="_blank">插座</a><a href="#" target="_blank">电工电料</a><a href="#" target="_blank">监控安防</a><a href="#" target="_blank">电线/线缆</a></dd>
          </dl>
        </div>
        <div class="items_brands"><a href="#" target="_blank"><img src="images/562f4971Na5379aba.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9eef9N5bb8d27f.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef02N99d26435.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef10Nd206a236.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef28N00328d44.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef34N7cc61f4c.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef3eN99aef1f0.jpg" width="83" height="35" alt=""/></a> <a href="#" target="_blank"><img src="images/54d9ef4cN4fe57f9b.jpg" width="83" height="35" alt=""/></a></div>
        <div class="item_promotions"><a href="#" target="_blank"><img src="images/5634e5adN2bfad520.jpg" width="168" height="134" alt=""/></a><a href="#" target="_blank"><img src="images/5634e5bdNe68e2b4c.jpg" width="168" height="134" alt=""/></a></div>
      </div>
    </div>
    <div class="nav_links"><a href="#" target="_blank">家用电器</a><a href="#" target="_blank">婴儿玩具</a><a href="#" target="_blank">精品男装</a><a href="#" target="_blank">电脑办公</a><a href="#" target="_blank">时尚手机</a><a href="#" target="_blank">新品女装</a></div>
  </div>
</div>
<div class="container clearfix">
  <ul class="hot_sales">
    <li> <a class="hot_img" href="#" target="_blank"><img src="images/14331293979.jpg" width="100" height="100" alt=""/></a> <a class="hot_name" href="#" target="_blank">Apple MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存</a>
      <div class="hot_price">特价：<strong>￥218.00</strong></div>
      <div class="hot_btnbox"><a class="btn_default" target="_blank" href="#">立即抢购</a></div>
    </li>
    <li> <a class="hot_img" href="#" target="_blank"><img src="images/1433128642809.jpg" width="100" height="100" alt=""/></a> <a class="hot_name" href="#" target="_blank">Apple MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存</a>
      <div class="hot_price">特价：<strong>￥218.00</strong></div>
      <div class="hot_btnbox"><a class="btn_default" target="_blank" href="#">立即抢购</a></div>
    </li>
    <li> <a class="hot_img" href="#" target="_blank"><img src="images/rBEhVlKAOb8IAAAAAAEj0EEgATkAAFU_gLGy0YAASPo066.jpg" width="100" height="100" alt=""/></a> <a class="hot_name" href="#" target="_blank">Apple MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存</a>
      <div class="hot_price">特价：<strong>￥218.00</strong></div>
      <div class="hot_btnbox"><a class="btn_default" target="_blank" href="#">立即抢购</a></div>
    </li>
    <li> <a class="hot_img" href="#" target="_blank"><img src="images/560a4106N8bc5bfd7.jpg" width="100" height="100" alt=""/></a> <a class="hot_name" href="#" target="_blank">Apple MacBook Air MJVM2CH/A 11.6英寸宽屏笔记本电脑 128GB 闪存</a>
      <div class="hot_price">特价：<strong>￥218.00</strong></div>
      <div class="hot_btnbox"><a class="btn_default" target="_blank" href="#">立即抢购</a></div>
    </li>
  </ul>
  <div class="crumbs_box"><a href="#">电脑办公</a>&nbsp;&gt;&nbsp;<a href="#">电脑整机</a>&nbsp;&gt;&nbsp;<span>笔记本</span></div>
  <div class="list-rig">
    <div class="s-title">
      <h3><b>笔记本</b><em>商品筛选</em></h3>
      <div class="st-ext">共&nbsp;<span id="allspus"></span>个商品 </div>
    </div>
    <div class="pro_filter mt15">
      <div class="filterBox">
        <dl id="selected_filter" class="filter_wp hide clearfix">
          <dt>已选条件：</dt>
          <dd>
            <div class="filterList">
              <ul class="clearfix" id="chooseAttrHtmls">
              </ul>
            </div>
            <a class="cancel_filter" href="javascript:;" onclick="clearAllChoose()">全部撤销</a> </dd>
        </dl>
        <div id="attrhtmls">
          </div>
      </div>
    </div>
    <div class="filter_handle">
      <div class="show_more_filter"><a href="javascript:;">更多选项<b></b></a></div>
      <div class="show_less_filter hide"><a href="javascript:;">收起<b></b></a></div>
    </div>
  </div>
  <div class="aside_right" id="spu_main">
    <div class="operation_bar">
      <div class="operation_wp clearfix">
        <div class="f_sort"><a href="javascript:;" onclick="">综合</a><a href="javascript:;">销量</a><a id="price_sort" onclick="pricesort(this)" href="javascript:;">价格<i></i></a><a href="javascript:;">评论数</a><a  onclick="uptimesort(this)" href="javascript:;">上架时间<i></i></a></div>
        <div class="f_pager"><span class="fp_text" id="pagenum"></span><a href="javascript:;" onclick="toprepage()" id="fp_prev" class="fp_prev">&lt;</a><a href="javascript:;" onclick="tonextpage()" id="fp_next" class="fp_next">&gt;</a></div>
      </div>
      <div class="operation_wp goodstip clearfix">
        <div class="select_box"><a href="javascript:;" onclick="filterstock(this)"><i></i>仅显示有货</a></div>
        <div class="select_box"><a href="javascript:;" onclick="filterstore(this)"><i></i>自营商品</a></div>
      </div>
    </div>
    <div class="goods_list">
      <ul class="clearfix" id="spulists">
      </ul>
      <div id="spupage" class="layui-laypage flr">
      </div>
    </div>
  </div>
</div>
<jsp:include page="../common/foot.jsp"></jsp:include>

<div class="pop_compare">
  <p class="pop_compare_tips">对比栏已满，您可以删除不需要的栏内商品再继续添加哦！</p>
  <div class="compare_title clearfix">
    <h3 class="fll">对比栏</h3>
    <a class="hide_compare" href="javascript:;">隐藏</a> </div>
  <div class="com_items clearfix">
    <dl class="hasItem">
      <dt> <a target="_blank" href="#"><img src="images/55f0e7d7N34623c97 (1).jpg" alt="" width="50" height="50"></a> </dt>
      <dd> <a target="_blank" class="com_item_name" href="#">创维(Skyworth)LED 50S9 50英寸 智能酷开系统网络平板液晶电视(咖啡金)</a> <span class="com_price"><strong>￥2699.00</strong><a href="javascript:;" class="del_comp_item">删除</a></span> </dd>
    </dl>
    <dl class="item_empty">
      <dt>2</dt>
      <dd>您还可以继续添加</dd>
    </dl>
    <dl class="item_empty">
      <dt>3</dt>
      <dd>您还可以继续添加</dd>
    </dl>
    <dl class="item_empty">
      <dt>4</dt>
      <dd>您还可以继续添加</dd>
    </dl>
  </div>
  <div class="com_operate"><a target="_blank" href="javascript:;" class="compare_btn compare_active">对比</a><a href="javascript:;" class="del_items">清空对比栏</a></div>
</div>
<a class="cd-top" href=""></a>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{"bdSize":16}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

<script src="<%=basePath %>/res/js/common/common.js"></script>
<script src="<%=basePath %>/res/layui/layui.js"></script>
<script src="<%=basePath %>/res/js/spu/spulist.js"></script>

</body>
</html>
