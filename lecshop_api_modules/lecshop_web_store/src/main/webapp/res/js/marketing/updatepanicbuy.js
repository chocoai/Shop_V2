// 基本路径
var basePath = $("#basePath").val();

// 加载抢购促销
loadPanicBuy();

function loadPanicBuy() {
    LSFetch({
        url: basePath + '/store_querymarketingbyid.htm?id=' + $("#marketId").val(),
        success: function (market) {
            $("#name").val(market.name);
            $("#discount").val(market.panicBuy.discount);
            $("#limitnum").val(market.panicBuy.limitNum);
            $("#startTime").val(market.startTime);
            $("#endTime").val(market.endTime);
            $(".rushImage").attr("src",market.panicBuy.pcUrl);
            $(".mobilerushImage").attr("src",market.panicBuy.mobileUrl);
            $("#desc").val(market.desc);

            selectedSkus = new Array();

            for (var i = 0; i < market.marketingSkus.length; i++) {
                var sku = {};
                sku.id = market.marketingSkus[i].skuId;
                sku.url = market.marketingSkus[i].sku.url;
                sku.skuNo = market.marketingSkus[i].sku.skuNo;
                sku.name = market.marketingSkus[i].sku.name;
                sku.specs = getSpecValues(market.marketingSkus[i].sku.skuSpecValues);
                sku.price = market.marketingSkus[i].sku.price;
                selectedSkus.push(sku);
            }

            loadSkus();
        }
    })
}

// 上传图片
function uploadpic(id) {

    $.ajax({
        url: 'store_uploadtoupyun.htm?name=' + id,
        type: 'POST',
        cache: false,
        data: new FormData($('#updatepanicbuy')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("." + id).attr("src", res);
    }).fail(function (res) {
    });
}

// 修改抢购
function updatepanic() {
    if (!$("#updatepanicbuy").valid()) {
        return;
    }

    var panicBuy = {};
    panicBuy.marketingId = $("#marketId").val();
    panicBuy.discount = $("#discount").val();
    panicBuy.limitNum = $("#limitnum").val();
    panicBuy.pcUrl = $(".rushImage").attr("src");
    panicBuy.mobileUrl = $(".mobilerushImage").attr("src");
    var marketing = {};
    marketing.id=$("#marketId").val();
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.panicBuy = panicBuy;
    marketing.marketingSkus = new Array();

    $('#choosesku').find('tr').each(function () {
        var marketingSku = {};
        marketingSku.skuId = $(this).attr("id");
        marketingSku.marketingId = $("#marketId").val();
        marketing.marketingSkus.push(marketingSku);
    })

    if (marketing.marketingSkus.length == 0) {
        showerror('请至少选择一个商品!');
        return;
    }

    LSFetch({
        url: basePath + '/store_updatepanicmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";
        }
    })

}
