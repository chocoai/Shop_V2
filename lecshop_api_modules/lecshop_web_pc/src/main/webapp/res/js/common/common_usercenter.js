$(document).ready(function () {
  $('.navitems li').hover(
      function () {
        $(this).find('.dl').addClass('hover')
      },
      function () {
        $(this).find('.dl').removeClass('hover')
      }
  );
  //商品取消关注
  $('.btn-focus').click(function () {
    $(this).parents('.mf-goods-item').addClass('z-delete-tip');
  });
  $('.item-delete-tip').find('.btn-cancle').click(function () {
    $(this).parents('.mf-goods-item').removeClass('z-delete-tip');
  })
  $('.item-delete-tip').find('.btn-sure').click(function () {
    $(this).parents('.mf-goods-item').animate({
      opacity: 0
    },300, function () {
      $(this).remove();
    })
  });
  //店铺取消关注
  $('.unfollow_btn').click(function () {
    $(this).parents('.shop-item').addClass('z-delete-tip');
  });
  $('.item-delete-tip').find('.btn-cancle').click(function () {
    $(this).parents('.shop-item').removeClass('z-delete-tip');
  })
  $('.item-delete-tip').find('.btn-sure').click(function () {
    $(this).parents('.shop-item').animate({
      opacity: 0
    },300, function () {
      $(this).remove();
    })
  });
  //提示框关闭
  $('.btn_cancel').click(function () {
    $(this).parents('.thickbox').hide();
    $('.masklayer').hide();
  });
  //删除消息
  $('.mg-title s').click(function () {
    $(this).parents('.mg-coupon').animate({
      opacity: 0
    },300, function () {
      $(this).remove();
    })
  });
  //点击已读消息
  $('.mg-box').click(function () {
    $(this).removeClass('no-look');
  });
  //浏览记录导航槽
  function k() {
    $(".p-line").css("height", $(".goods-content").height())
  }
  k();
  var a = $(".p-line-red");
  $(window).scroll(function() {
      var b = $(window).scrollTop();
      a.animate({
            height: b + $(window).height() / 2 - 200
          }, 100, function() {});
          k()
  })
  //删除一天浏览记录
  $('.del-all').click(function () {
    $(this).parents('.goods-item').animate({
      opacity: 0
    },300, function () {
      $(this).remove();
    })
  });
  //删除一个商品浏览记录
  $('.p-del').click(function () {
    $(this).parents('li').animate({
      opacity: 0
    },300, function () {
      if($(this).siblings().length == 0){
        $(this).parents('.goods-item').remove();
      }
      $(this).remove();
    })
  });
});

//保存提示框
function updateUserInfo() {
  $('#save_dialog,.masklayer').fadeIn().delay(3000).fadeOut();
}