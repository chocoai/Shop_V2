// 基本路径
var basePath = $("#basePath").val();

// 加载直降促销
loadFall();

function loadFall() {
    LSFetch({
        url: basePath + '/store_querymarketingbyid.htm?id=' + $("#marketId").val(),
        success: function (market) {
            $("#name").val(market.name);
            $("#price").val(market.fall.price);
            $("#startTime").val(market.startTime);
            $("#endTime").val(market.endTime);
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

// 修改直降
function updatefall() {
    if (!$("#updatefall").valid()) {
        return;
    }
    var fall = {};
    fall.price = $("#price").val();
    fall.marketingId = $("#marketId").val();
    var marketing = {};
    marketing.id = $("#marketId").val();
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.fall = fall;
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
        url: basePath + '/store_updatefallmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";

        }
    })

}
