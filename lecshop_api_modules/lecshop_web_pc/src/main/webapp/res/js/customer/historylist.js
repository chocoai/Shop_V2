var basePath = $("#basePath").val();

$(document).ready(function () {
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
    });
    //删除一天浏览记录
    $('body').on('click','.del-all',function () {
        $(this).parents('.goods-item').animate({
            opacity: 0
        },300, function () {
            $(this).remove();
        });
        LSFetch({
            url: basePath + 'customer/deletehistorybyday.htm?createTime=' + $("#createTime").html(),
            success: function (res) {
                if (res == 0) {
                    //删除失败

                }
            }
        });
    });
    //删除一个商品浏览记录
    $('body').on('click','.p-del',function () {
        $(this).parents('li').animate({
            opacity: 0
        },300, function () {
            if($(this).siblings().length == 0){
                $(this).parents('.goods-item').remove();
            }
            $(this).remove();
        });
        LSFetch({
            url: basePath + 'customer/deletehistorybyid.htm?id=' + $("#recordId").val(),
            success: function (res) {
                if (res == 0) {
                    //删除失败

                }
            }
        });
    });
});

queryMessage();

function queryMessage() {
    LSFetch({
        url: basePath + 'customer/queryhistorylist.htm',
        success: function (res) {
            var htmls = '';
            if (res.length > 0) {
                for (var i = res.length; i > 0; i--) {
                    htmls += '<div class="goods-item">';
                    htmls += '<div class="mt"><h2 id="createTime">' + res[i - 1].time + '</h2><span class="del-all">删除</span><i></i></div>';
                    htmls += '<div class="mc"><ul>';
                    for (var j = 0; j < res[i - 1].browseRecordList.length; j++) {
                        htmls += '<li>' +
                            '<div class="p-img">'
                            + '<a href="" target="_blank" title="' + res[i - 1].browseRecordList[j].sku.name + '">' +
                            '<img src="' + res[i - 1].browseRecordList[j].sku.url + '" width="220" height="220"></a>' +
                            '</div>' +
                            '<div class="p-price"><i>￥' + res[i - 1].browseRecordList[j].sku.price + '.00</i></div>' +
                            '<span class="p-del"><input type="hidden" id="recordId" value="' + res[i - 1].browseRecordList[j].id + '"/></span></li>';
                    }
                    htmls += '</ul></div>';
                    htmls += '</div>';
                }
            } else {
                $(".good-bottom").hide();
                htmls += '<div class="good-bottom">无浏览历史</div></div>';
            }
            $("#history").html(htmls);
        }
    })
}
