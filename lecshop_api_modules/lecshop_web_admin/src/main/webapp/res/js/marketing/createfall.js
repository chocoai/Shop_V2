// 基本路径
var basePath = $("#basePath").val();

// 保存直降
function savefall() {
    if (!$("#savefall").valid() | !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }



    var fall = {};
    fall.price = $("#price").val();

    var marketing = {};
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.fall = fall;
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
        url: basePath + '/addfallmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "toquerymarketing.htm";

        }
    })
}
