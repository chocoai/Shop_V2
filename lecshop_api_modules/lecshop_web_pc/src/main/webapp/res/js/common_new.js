//分类导航
$(document).ready(function () {
    $('.categorys_item').mouseenter(function () {
        $(this).addClass('hover').siblings().removeClass('hover');
        var item_length = $(this).prevAll().length;
        $('.categorys_layer').eq(item_length).show().siblings('.categorys_layer').hide();
    });
    $('.categorys').mouseleave(function () {
        $(this).find('.categorys_item').removeClass('hover');
        $(this).find('.categorys_layer').hide();
    });
	$('.categorys').hover(
        function () {
            $(this).find('.categorys_list').show();
        },
        function () {
            $(this).find('.categorys_list').hide();
        }
	);
});
//首页轮播图
$(function()
{
	//生成下部小按钮
	var length = $('.slideshow_photo li').length;
	for(var i = 0; i < length; i++)
	{
		$('.slideshow_footbar').prepend('<a class="slideshow-bt" index="'+(length-i)+'"></a>');
  }
    $('.slideshow_footbar .slideshow-bt:first').addClass('bt-on');
    $('.slideshow_footbar .slideshow-bt').mousedown(function(e)
    {
		slideTo(this);
    });

	
    var indexAllowAutoSlide = true;
    $('.slideshow_wrapper').mousedown(function()
    {
		indexAllowAutoSlide = false;
    }).mouseleave(function()
    {
		indexAllowAutoSlide = true;
    });

	//滚动
    setInterval(function()
    {
		if (indexAllowAutoSlide) slideDown(); 
    },3000);

});

function slideDown()
{
	var currentBt = $('.slideshow_footbar .slideshow-bt.bt-on');
    if (currentBt.length <= 0) return;
    var nxt = currentBt.get(0).nextSibling;
    slideTo(nxt?nxt:$('.slideshow_footbar .slideshow-bt:first').get(0));
}

function slideTo(o)
{
	if (!o) return;
	var currentIndex = $('.slideshow_footbar .slideshow-bt.bt-on').attr('index'),
		current = $('.slideshow_photo li[index='+currentIndex+']');
	var nxt = $('.slideshow_photo li[index='+$(o).attr('index')+']');
	if (currentIndex == $(o).attr('index')) { return; }
		
	$('.slideshow_footbar .slideshow-bt.bt-on').removeClass('bt-on');
	$(o).addClass('bt-on');
	
	nxt.css('z-index',2);
	
	current.css('z-index',3).stop().fadeOut(500,function()
	{
		$(this).css('z-index','1').show();
	});
}
//slideshow end
//加法函数
function aceAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	
	return ((arg1 * m + arg2 * m) / m).toFixed(2);
} 

//加法函数
function aceAddInt(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	
	return (arg1 * m + arg2 * m) / m;
} 
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。 
Number.prototype.add = function (arg) {
	return aceAdd(arg, this);
};

//减法函数
function aceSub(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
     //last modify by deeka
     //动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

//减法函数
function aceSubInt(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
   //last modify by deeka
   //动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m);
}

//给Number类型增加一个add方法，，使用时直接用 .sub 即可完成计算。 
Number.prototype.sub = function (arg) {
	return aceSub(this, arg);
};

//乘法函数
function aceMul(arg1, arg2) {
    if(arg1==null){
        return;
    }
    if(arg2==null){
        return;
    }
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	}
	catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	}
	catch (e) {
	}
	return (Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)).toFixed(2);
} 
//给Number类型增加一个mul方法，使用时直接用 .mul 即可完成计算。 
Number.prototype.mul = function (arg) {
	return aceMul(arg, this);
}; 

//除法函数
function aceDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
	}
	try {
		t2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
	}
	with (Math) {
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return ((r1 / r2) * pow(10, t2 - t1)).toFixed(2);
	}
} 
//给Number类型增加一个div方法，，使用时直接用 .div 即可完成计算。 
Number.prototype.div = function (arg) {
	return aceDiv(this, arg);
};
//商品筛选
$(function () {
	
});
$(document).ready(function () {
	//迷你购物车删除
	$('.shopping_cart').hover(function () {
		$('.shopping_cart_layer').show();
	}, function () {
		$('.shopping_cart_layer').hide();
	});
	$('.sc_cost a').click(function () {
		$(this).parents('ul.scorder').remove();
	});
	//分类导航
	$('.categorys_item').mouseenter(function () {
		$(this).addClass('hover').siblings().removeClass('hover');
		var item_length = $(this).prevAll().length;
		$('.categorys_layer').eq(item_length).show().siblings('.categorys_layer').hide();
	});
	$('.categorys').mouseleave(function () {
		$(this).find('.categorys_item').removeClass('hover');
		$(this).find('.categorys_layer').hide();
	});
	$('.dorpdown').hover(function () {
		$(this).find('.categorys_list').show();
	}, function () {
		$(this).find('.categorys_list').hide();
	});

	/* 点击删除每一个筛选类型操作 */
	$(document).on('click','#selected_filter ul a',function () {
		var _num = $(this).parent("li").attr("data-num");
		$(this).parent("li").remove();
		$(".pro_filter").find("dl[data-num=" + _num + "]").show().removeClass("selected");
		if ($(".filter_item:visible").length > 4 && $(".show_less").css("display") == "none") {
			$(".filter_item:visible:last").addClass("more_filter").hide();
			$(".show_more").show();
		};
		if ($("#selected_filter ul li").length == 0) {
			$("#selected_filter").hide()
		};
	});
	$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter"); //默认显示4项，其余隐藏
	var n = $(".pro_filter .filter_item").length;
	if ( n < 5 ){
		$('.show_more,.show_less').hide();
	}
	/* 点击展开按钮操作 */
	$(".show_more").click(function () {
		$(".more_filter").removeClass("more_filter").show();
		$(this).hide();
		$(".show_less").show();
	});
	/* 点击收起按钮操作 */
	$(".show_less").click(function () {
		$(".filter_item:visible:eq(3)").nextAll(".filter_item:visible").addClass("more_filter").hide();
		$(this).hide();
		$(".show_more").show();
	});
	/* 点击更多、收起按钮操作 */
	$(".f_more").click(function () {
		$(this).siblings(".filterList").height("auto");
		$(this).siblings(".f_less").show();
		$(this).hide();
	});
	$(".f_less").click(function () {
		$(this).siblings(".filterList").height("");
		$(this).siblings(".f_more").show();
		$(this).hide();
	});
	/* 点击全部撤销操作 */
	$(".cancel_filter").click(function () {
		$("#selected_filter ul li").remove();
		$("#selected_filter").hide();
		$(".filter_item").show();
		$(".pro_filter .filter_item:eq(3)").nextAll(".filter_item").addClass("more_filter").hide();
		$(".show_less").hide();
		$(".show_more").show();
	});
	//商品列表切换图
	$(".goods_box").each(function () {
		var _this = $(this);
		var _next = _this.find(".g_slides_next");
		var _prev = _this.find(".g_slides_prev");
		var _ul = _this.find(".g_slides_wrap ul");
		var _width = _ul.find("li").length * 31;
		var n = _ul.find("li").length - 5;
		var _step = 0;
		var _time = 0;
		_ul.find("li").mouseover(function () {
			var _li = $(this);
			var _src = _li.attr("data-big");
			_time = setTimeout(function () {
				_li.siblings(".cur").removeClass("cur");
				_li.addClass("cur");
				_this.find(".goods_list_img img").attr({
					"src": _src
				}, {
					"data-original": _src
				});
			}, 200);
		}).mouseout(function () {
			clearTimeout(_time);
		});
		if (n <= 0) {
			_this.find(".g_slides_prev, .g_slides_next").hide();
		} else {
			_this.find(".g_slides_prev, .g_slides_next").show();
			_this.find(".g_slides_wrap ul").width(_width);
			_next.click(function () {
				_prev.removeClass("disabled");
				if (_step < n) {
					_step++;
					go_next();
				}
			});
			_prev.click(function () {
				_next.removeClass("disabled");
				if (_step > 0) {
					_step--;
					go_prev();
				}
			});

			function go_next() {
				_ul.animate({
					left: -31 * _step
				});
				if (_step == n) {
					_next.addClass("disabled");
				}
			}

			function go_prev() {
				_ul.animate({
					left: -31 * _step
				});
				if (_step == 0) {
					_prev.addClass("disabled");
				}
			}
		}
	});
	//商品对比
	$('.contrast').click(function () {
		$(this).toggleClass('selected');
	});
	$('.hide_compare').click(function () {
		$(this).parents('.pop_compare').hide();
	});
	$('.hasItem').hover(function () {
		$(this).find('.del_comp_item').show();
	}, function () {
		$(this).find('.del_comp_item').hide();
	});
	$('.contrast').click(function () {
		$('.pop_compare').show();
	});
	//商品关注
	$('.focus').click(function () {
		$(this).addClass('focused');
	});
	//加减
	$('.plus').click(function () {
		var inputvalue = parseInt($(this).siblings(".num_text").val());
		inputvalue += 1;
		$(this).siblings(".num_text").attr("value", inputvalue);
		$(this).siblings(".num_text").prop("value", inputvalue);
		$(this).siblings(".num_text").trigger("change");
	});
	$('.minus').click(function () {
		var inputvalue = parseInt($(this).siblings(".num_text").val());
		if (inputvalue > 1) {
			inputvalue -= 1;
			$(this).siblings(".num_text").attr("value", inputvalue);
			$(this).siblings(".num_text").prop("value", inputvalue);
			$(this).siblings(".num_text").trigger("change");
		}
	});
	$('.num_text').keyup(function () {
		$(this).val($(this).val().replace(/\D|^0/g, ''));
		if ($(this).val() == '') {
			$(this).val('1');
		}
	});
	//详情页商品图片展示
	$(".thumb_scroll_wp li:first").addClass("cur");
	var li_n = $(".thumb_scroll_wp li").length - 5;
	var li_step = 0;
	$(".thumb_scroll_wp li img").mouseover(function () {
		var _li = $(this).parent("li");
		_li.siblings(".cur").removeClass("cur");
		_li.addClass("cur");
	});
	if (li_n > 0) {
		$(".thumb_scroll_wp ul").width($(".thumb_scroll_wp li").length * 59);
		$(".thumb_scroll_next").removeClass("disabled");
		$(".thumb_scroll_next").click(function () {
			$(".thumb_scroll_prev").removeClass("disabled");
			if (li_step < li_n) {
				li_step++;
				$(".thumb_scroll_wp ul").animate({
					left: -59 * li_step
				}, 300);
				if (li_step == li_n) {
					$(".thumb_scroll_next").addClass("disabled");
				}
			}
		});
		$(".thumb_scroll_prev").click(function () {
			$(".thumb_scroll_next").removeClass("disabled");
			if (li_step > 0) {
				li_step--;
				$(".thumb_scroll_wp ul").animate({
					left: -59 * li_step
				}, 300);
				if (li_step == 0) {
					$(".thumb_scroll_prev").addClass("disabled");
				}
			}
		});
	}
	/* 评论回复 */
	$('.comment_replylist .comment_replyitem:eq(4)').nextAll('.comment_replyitem').hide();
	$('.view_all_reply a').click(function() {
        $(this).parent().hide().prevAll().show();
    });
	//套餐购买
    $('.fitting_con').each(function(){
	    var fitting = $(this).find('li').length;
	    var fitting_width = aceMul(197, fitting) + 'px';
	    $(this).css('width', fitting_width);
    });

	function choose_price() {
	    $('.stabcon').each(function () {
	        var check_input = $(this).find("input[type='checkbox']:checked");
	        var zongjia_new = 0;
	        check_input.next().find('span').each(function () {
	            var xiaoji = $(this).text();
	            zongjia_new = aceAdd(xiaoji, zongjia_new);
	            $(this).parents('.stabcon').find('.infos').find('.p-price').find('span').text(zongjia_new);
	        });
	    });
	}
	choose_price();
    $('.fitting_con').each(function () {
	$(this).find('input').change(function () {
		var choose_num = $(this).parents('.fitting_con').find('input:checked').length;
		var choose_text = '已选择' + choose_num + '个搭配';
		$(this).parents('.stabcon').find('.infos').find('.p-name').text(choose_text);
		choose_price();
	});
    });
    $('.stab li').click(function(){
        var e = $(this).prevAll().length;
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).parent().nextAll('.stabcon').eq(e).show().siblings('.stabcon').hide();
    });
	//地区选择
	$(".choose_area").mouseenter(function () {
		$(this).addClass("choose_area_hover");
	}).mouseleave(function () {
		$(this).removeClass("choose_area_hover");
	});
	//关注
	$(".store-goods").click(function () {
		if ($(this).hasClass("liked")) {
			$(this).removeClass("liked");
		} else {
			$(this).addClass("liked");
		}
	});
	//tab切换
	function tab(nav, content, on, type) {
		$(nav).children().on(type, (function () {
			var tab = $(this);
			var tab_index = tab.prevAll().length;
			tab.siblings().removeClass(on);
			tab.addClass(on);
			$(content).children().hide();
			$(content).children().eq(tab_index).show();
		}));
	}
	$(function () {
		tab(".tabs .tabsNav", ".tabs .tabsContent", "on", "mouseover");
		tab(".tabs_one .tabsNav_one", ".tabs_one .tabsContent_one", "cur", "click");
		tab(".tabs_two .tabsNav_two", ".tabs_two .tabsContent_two", "cur", "click");
	});
	//评论晒单
	$(".od_list").each(function () {
		var _this = $(this);
		_this.find("a").click(function () {
			if ($(this).parent().hasClass("cur")) {
				$(this).parent().removeClass("cur");
				_this.next(".photo_viewer").hide().width(0).height(0);
				_this.next(".photo_viewer").find("img").attr("src", "").width(0).height(0);
			} else {
				var _src = $(this).parent("li").attr("data-img");
				var img_url = _src + "?" + Date.parse(new Date());
				var _img = new Image();
				_img.src = img_url;
				var nw = _this.next(".photo_viewer").width();
				var nh = _this.next(".photo_viewer").height();
				_this.find(".cur").removeClass("cur");
				$(this).parent("li").addClass("cur");
				_this.next(".photo_viewer").show().width(nw).height(nh);
				_this.next(".photo_viewer").find("img").attr("src", _src);
				_img.onload = function () {
					var nw = _img.width + 8;
					var nh = _img.height + 8;
					_this.next(".photo_viewer").find("img").animate({
						width: nw - 8,
						height: nh - 8
					}, 500);
					_this.next(".photo_viewer").animate({
						width: nw,
						height: nh
					}, 500);
				};
			}
		});
		_this.next(".photo_viewer").click(function () {
			_this.find(".cur").removeClass("cur");
			$(this).hide().width(0).height(0);
			$(this).find("img").attr("src", "").width(0).height(0);
		});
	});
	//回复评价
	$('.reply_btn').click(function () {
		$(this).parent('li').next().toggle();
	});
	// 返回顶部
	var offset = 300,
		offset_opacity = 1200,
		scroll_top_duration = 700,
		$back_to_top = $('.cd-top');
	$(window).scroll(function () {
		($(this).scrollTop() > offset) ? $back_to_top.addClass('cd-is-visible'): $back_to_top.removeClass('cd-is-visible cd-fade-out');
		if ($(this).scrollTop() > offset_opacity) {
			$back_to_top.addClass('cd-fade-out');
		}
	});
	$back_to_top.on('click', function (event) {
		event.preventDefault();
		$('body,html').animate({
			scrollTop: 0
		}, scroll_top_duration);
	});
	//登录
	$(".n_text").each(function () {
		$(this).focus(function () {
			$(this).parents('.new_login_con').find('.input_box').removeClass('input_box_focus');
			$(this).parent('.input_box').addClass('input_box_focus');
			$(this).parents('.n_row').find('.form_tips').show();
			$(this).parent().removeClass('input_box_error');
			$(this).parents('.n_row').find('.n_tips').hide();
		});
		$(this).blur(function () {
			$(this).parents('.new_login_con').find('.input_box').removeClass('input_box_focus');
			$(this).parents('.n_row').find('.form_tips').hide();
		});
		if ($(this).parent().hasClass('input_box_error')) {
			$(this).blur(function () {
				$(this).parent().addClass('input_box_error');
				$(this).parents('.n_row').find('.n_tips').show();
			});
		}
	});
	//注册
	$('#protocol').click(function () {
		$('.agreementbox, .masklayer').show();
	});
	$('.thickclose, .btn_cancel').click(function () {
		$(this).parents('.thickbox').hide();
		$('.masklayer').hide();
	});
	//placeholder
	var doc = document,
		inputs = doc.getElementsByTagName('input'),
		supportPlaceholder = 'placeholder' in doc.createElement('input'),
		placeholder = function (input) {
			var text = input.getAttribute('placeholder'),
				defaultValue = input.defaultValue;
			if (defaultValue === '') {
				input.value = text;
			}
			input.onfocus = function () {
				if (input.value === text) {
					this.value = '';
				}
			};
			input.onblur = function () {
				if (input.value === '') {
					this.value = text;
				}
			};
		};
	if (!supportPlaceholder) {
		for (var i = 0, len = inputs.length; i < len; i++) {
			var input = inputs[i],
				text = input.getAttribute('placeholder');
			if (input.type === 'text' && text) {
				placeholder(input);
			}
		}
	}
	//购物车加减
	$('.p_quantity .num_minus').click(function () {
		var quantity = $(this).siblings('.num_text').val();
		var price = $(this).parents('.p_quantity').siblings('.p_price').find('span').text();
		var sum = aceMul(price, quantity);
		$(this).parents('.p_quantity').siblings('.p_sum').text(sum);
	});
	$('.p_quantity .plus').click(function () {
		var price = $(this).parents('.p_quantity').siblings('.p_price').find('span').text();
		var zongjia = $('.sumPrice').find('span').text();
		var zongjia_new = aceAdd(zongjia, price);
		if ($(this).parents('.p_quantity').siblings('.p_checkbox').find('.acecheckbox').prop('checked') === true) {
			$('.sumPrice').find('span').text(zongjia_new);
		}
	});
	$('.p_quantity .minus').mousedown(function () {
		var price = $(this).parents('.p_quantity').siblings('.p_price').find('span').text();
		var zongjia = $('.sumPrice').find('span').text();
		var zongjia_new = aceSub(zongjia, price);
		var inputvalue = $(this).siblings(".num_text").val();
		if ($(this).parents('.p_quantity').siblings('.p_checkbox').find('.acecheckbox').prop('checked') === true && inputvalue > 1) {
			$('.sumPrice').find('span').text(zongjia_new);
		}
	});
	//购物车优惠
	$('.sales_promotion').click(function (event) {
		event.stopPropagation();
		$(this).parents('.cart_container').find('.promotion_tips').hide();
		$(this).next('.promotion_tips').show();
		$(this).parents('.item_list').find('.p_price').css('z-index', '0');
		$(this).parents('.p_price').css('z-index', '30');
	});
	$('.op_btns a, .promotion_tit').click(function () {
		$(this).parents('.promotion_tips').hide();
	});
	$('.promotion_tips').click(function (event) {
		event.stopPropagation();
	});
	$(document).click(function () {
		$('.promotion_tips').hide();
	});
	//购物车编辑
	function zongjia() { //总价
		var check_input = $('.cart_container').find("input[type='checkbox']:checked");
		var zongjia_new = 0;
		check_input.parents('.item_form').find('.p_sum').each(function () {
			var xiaoji = $(this).text();
			zongjia_new = aceAdd(xiaoji, zongjia_new);
			$('.sumPrice').find('span').text(zongjia_new);
		});
	}

	function goods_sum() { //总数
		var m = $('.item_list').find("input[type='checkbox']").length;
		var n = $('.item_list').find("input[type='checkbox']:checked").length;
		if (m === n) {
			$('.toggle_checkboxes').prop('checked', true);
		} else {
			$('.toggle_checkboxes').prop('checked', false);
		}
		$('.amount_sum em').text(n);
	}
	$('.item_list').find("input[type='checkbox']").change(function () {
		var sm = $(this).parents('.item_list').find("input[type='checkbox']").length;
		var sn = $(this).parents('.item_list').find("input[type='checkbox']:checked").length;
		if (sm === sn) {
			$(this).parents('.cart_list').find('.checkShop').prop('checked', true);
		} else {
			$(this).parents('.cart_list').find('.checkShop').prop('checked', false);
		}
		zongjia();
		goods_sum();
	});
	$('.checkShop').change(function () {
		var check_input = $(this).parents('.shop').next('.item_list').find("input[type='checkbox']");
		if ($(this).prop('checked') === true) {
			check_input.prop('checked', true);
		} else {
			check_input.prop('checked', false);
		}
		zongjia();
		goods_sum();
	});
	$('.toggle_checkboxes').change(function () {
		var check_input = $(this).parents('.cart_container').find("input[type='checkbox']");
		if ($(this).prop('checked') === true) {
			check_input.prop('checked', true);
			zongjia();
		} else {
			check_input.prop('checked', false);
			$('.sumPrice').find('span').text('0.00');
		}
		goods_sum();
	});
	$('.cart_container').find("input[type='checkbox']").change(function () {
		var checkgoods = $('.cart_container').find("input[type='checkbox']:checked").length;
		if (checkgoods === 0) {
			$('.sumPrice').find('span').text('0.00');
		}
	});
	//购物车删除
	$('.cart_remove').click(function () {
		$('#cart_dialog, .masklayer').show();
	});
	//购物车工具栏
	car_toolbar();
	function car_toolbar() {
	    var body_height = $('body').height();
	    var win_height = $(window).height();
	    var scroll_height = body_height - win_height - 135;
	    if (scroll_height > 0) {
	     	$('.cart_toolbar').addClass('fixed_bottom');
	    }
	    $(window).scroll(function () {
		    if ($(window).scrollTop() > scroll_height) {
		    	$('.cart_toolbar').removeClass('fixed_bottom');
		    }
		    if ($(window).scrollTop() < scroll_height) {
		    	$('.cart_toolbar').addClass('fixed_bottom');
		    }
	    });
	}
	//收货地址
	$('.consignee_list li').hover(function () {
		$(this).addClass('li_hover').siblings().removeClass('li_hover');
	}, function () {
		$(this).removeClass('li_hover');
	});
	$('.consignee_item').click(function () {
		$(this).addClass('item_selected').parents('li').siblings().find('.consignee_item').removeClass('item_selected');
	});
	$('body').on('click', '.setdefault_consignee', function () {
		var addr_name = $(this).parents('ul').find('.item_selected').next().find('.addr_name').text();
		var addr_pro = $(this).parents('ul').find('.item_selected').next().find('.addr_pro').text();
		var consignee = addr_name + ' ' + addr_pro;
		$(this).parents('ul').find('.item_selected').find('span').text(consignee);
		$(this).parents('ul').find('.item_selected').removeClass('item_selected').nextAll('.op_btns').prepend('<a href="javascript:;" class="ftx05 setdefault_consignee">设为默认地址</a>');
		$(this).parents('li').find('.consignee_item').addClass('item_selected').find('span').text('默认地址');
		$(this).remove();
	});
	$('.switch_off').click(function () {
		$(this).hide().prev('.switch_on').show();
		$('.consignee_list li').hide();
		$('.consignee_list').find('.item_selected').parent('li').show();
	});
	$('.switch_on').click(function () {
		$(this).hide().next('.switch_off').show();
		$('.consignee_list li').show();
	});
	$('.payment_list li').hover(function () {
		$(this).addClass('payment_item_hover').siblings().removeClass('payment_item_hover');
	}, function () {
		$(this).removeClass('payment_item_hover');
	});
	$(document).on('click', '.payment_list li', function () {
		if ($(this).hasClass('payment_item_disabled')) {
			return false;
		} else {
			$(this).addClass('item_selected').siblings().removeClass('item_selected');
			$(this).siblings().find('.update_invoice').hide().prev().show().parent().prev().blur().attr('readonly', true);
		}
	});
	$('.qmark_icon').hover(function () {
		$(this).find('.online_tips').show();
	}, function () {
		$(this).find('.online_tips').hide();
	});
	//优惠券
	$('.toggler').click(function () {
		$(this).parents('.toggle_title').next('.toggle_wrap').toggle();
		$(this).parents('.item').toggleClass('toggle_active');
	});
	$('#showcode_btn').click(function () {
		$(this).parents('.list').next('.gift_form').toggle();
	});
	$(".code_form .itxt").keyup(function () {
		var $this = $(this);
		$this.val($this.val().replace(/[^a-zA-Z0-9]/g, '').toUpperCase());
		$this.val($this.val().replace('O', '0'));
		if ($this.val().length == 4 && $this.attr('id') != 'couponKeyPressFourth') {
			$this.next().next().focus();
		}
	});
	$('#coupon_btn').click(function () {
		$('#coupon_dialog, .masklayer').show();
	});
	//收货地址
	$('#add_addr').click(function () {
		$('#address_dialog, .masklayer').show();
	});
	$('.del_consignee').click(function () {
		$('#del_addr, .masklayer').show();
	});
	//新增单位发票
	$('#add_invoice').click(function () {
		$(this).parent().hide().prev().show().find('input').val('').focus();
	});
	$('#save_tit').click(function () {
		var name = $(this).parent().prev().val();
		$(this).parents('.invoice_item').hide().prev().find('.payment_list li').removeClass('item_selected');
		$(this).parents('.invoice_item').hide().prev().find('#gr_item').after('<li class="item_selected"><b></b><input type="text" name="" value="' + name + '" readonly><div class="invoice_btns hide"><a href="javascript:;" class="ftx05 edit_invoice">编辑</a><a href="javascript:;" class="ftx05 update_invoice hide">保存</a><a href="javascript:;" class="ftx05 del_invoice">删除</a></div></li>');
		$(this).parents('.invoice_item').next('.link_box').show();
	});
	$(document).on('mouseover', '.invoice_thickbox .payment_list li', function () {
		$(this).find('.invoice_btns').show();
	});
	$(document).on('mouseout', '.invoice_thickbox .payment_list li', function () {
		$(this).find('.invoice_btns').hide();
	});
	$(document).on('click', '.edit_invoice', function () {
		$(this).hide().next().show().parent().prev().focus().removeAttr('readonly');
	});
	$(document).on('click', '.update_invoice', function () {
		$(this).hide().prev().show().parent().prev().blur().attr('readonly', true);
	});
	$(document).on('click', '.del_invoice', function () {
		$(this).parents('li.item_selected').prev().addClass('item_selected');
		$(this).parents('li').remove();
	});
	$('#uninvoice_btn').click(function () {
		$('.invoice_box').hide();
		$('#uninvoice_box').show();
	});
	$('#invoice_btn').click(function () {
		$('.invoice_box').hide();
		$('#invoice_box').show();
	});
	$('#vat_btn').click(function () {
		$('.invoice_box').hide();
		$('#vat_box').show();
	});
	$(document).on('click', '.save_invoice', function () {
		var tit = $('#invoice_tit').find('.item_selected').find('input').val();
		var text = $('#invoice_text').find('.item_selected').find('span').text();
		var name = $('#com_name').val();
		if ($('#uninvoice_btn').hasClass('item_selected')) {
			$('.invoice_cont').find('span').text('不需要发票　');
		}
		if ($('#invoice_btn').hasClass('item_selected')) {
			$('.invoice_cont').find('span').text('普通发票　' + tit + '　' + text + '　');
		}
		if ($('#vat_btn').hasClass('item_selected')) {
			$('.invoice_cont').find('span').text('增值税专用发票　' + name + '　' );
		}
		$(this).parents('.thickbox').hide();
		$('.masklayer').hide();
	});
	$('.next_btn').click(function() {
        $(this).hide().parents('.invoice_box').find('.form-group').eq(0).hide();
		$(this).parents('.invoice_box').find('.form-group').eq(1).show();
		$(this).siblings('.prev_btn,.save_invoice').show();
    });
	$('.prev_btn').click(function() {
        $(this).hide().parents('.invoice_box').find('.form-group').eq(0).show();
		$(this).parents('.invoice_box').find('.form-group').eq(1).hide();
		$(this).siblings('.save_invoice').hide();
		$(this).siblings('.next_btn').show();
    });
	$('#invoice_edit').click(function () {
		$('#invoice_dialog, .masklayer').show();
	});
	//收银台订单详情
	$('#orderDetail a').click(function () {
		$(this).toggleClass('opened');
		$(this).parents('.order').find('.o-list').toggle();
		if ($(this).hasClass('opened')) {
			$(this).find('span').text('收起详情');
		} else {
			$(this).find('span').text('订单详情');
		}
	});
	//收银台支付方式
	$('.pay_type').find('a').click(function () {
		$(this).addClass('cur').siblings().removeClass();
	});
	$('#pay_now').click(function () {
		$('#pay_dialog, .masklayer').show();
	});
	//个人中心-收货地址
	$('.delete-address').click(function () {
		$(this).parents('.air-addressInfo').remove();
	});
	//个人中心-tag切换
	$("div.content").find("div.layout:not(:first-child)").hide();
	$("div.content div.layout").attr("id", function () {
		return idNumber("No") + $("div.content div.layout").index(this);
	});
	$("ul.menu li").click(function () {
		var c = $("ul.menu li");
		var index = c.index(this);
		var p = idNumber("No");
		show(c, index, p);
	});

	function show(controlMenu, num, prefix) {
		var content = prefix + num;
		$('#' + content).siblings().hide();
		$('#' + content).show();
		controlMenu.eq(num).addClass("current").siblings().removeClass("current");
	}

	function idNumber(prefix) {
		var idNum = prefix;
		return idNum;
	}
});