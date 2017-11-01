// 基本路径
var basePath = $("#basePath").val();

// 保存团购
function savegroupbuy() {
    if (!$("#savegroupbuy").valid()| !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }
    var groupBuy = {};
    groupBuy.discount = $("#discount").val();

    var marketing = {};
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.groupBuy = groupBuy;
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
        url: basePath + '/store_addgroupmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";

        }
    })
}
