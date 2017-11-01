// 基本路径
var basePath = $("#basePath").val();

// 上传图片
function uploadpic(id) {

    $.ajax({
        url: 'store_uploadtoupyun.htm?name=' + id,
        type: 'POST',
        cache: false,
        data: new FormData($('#savepanicbuy')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("." + id).attr("src", res);
    }).fail(function (res) {
    });
}

// 添加抢购
function savepanic() {
    if (!$("#savepanicbuy").valid()| !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }

    var panicBuy = {};
    panicBuy.discount = $("#discount").val();
    panicBuy.limitNum = $("#limitnum").val();
    panicBuy.pcUrl = $(".rushImage").attr("src");
    panicBuy.mobileUrl = $(".mobilerushImage").attr("src");
    var marketing = {};
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.panicBuy = panicBuy;
    marketing.marketingSkus = new Array();

    $('#choosesku').find('tr').each(function () {
        var marketingSku = {};
        marketingSku.skuId = $(this).attr("id");
        marketing.marketingSkus.push(marketingSku);
    })

    if (marketing.marketingSkus.length == 0) {
        showerror('请至少选择一个商品!');
        return;
    }

    LSFetch({
        url: basePath + '/store_addpanicmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";

        }
    })

}