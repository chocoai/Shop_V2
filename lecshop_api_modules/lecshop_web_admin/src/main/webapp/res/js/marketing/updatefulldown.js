// 基本路径
var basePath = $("#basePath").val();

// 加载满减
loadFullDown();

$('body').on('click', '.del_btn', function () {
    $(this).parents('tr').remove();
});

var index = 1;
$('#add_sales_jian').click(function () {
    if ($(".manjian").length < 3) {
        index = index + 1;
        $(this).nextAll('table').append('<tr><td style="line-height:30px"><div class="fll">满</div><span class="form-group fll"><input id="' + (index + 1) + '" name="fullPrice" type="text" class="manjian form-control required isNumber" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">元，减</div> <span class="form-group fll"><input id="' + (index + 2) + '" type="text" name="reducePrice" class="form-control manjian2 required isNumber" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">元</div></td> <td class="operation_box"><button class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td></tr>');
        index = index + 1;
    }
});

function loadFullDown() {
    LSFetch({
        url: basePath + '/querymarketingbyid.htm?id=' + $("#marketId").val(),
        success: function (market) {
            $("#name").val(market.name);
            $("#startTime").val(market.startTime);
            $("#endTime").val(market.endTime);
            $("#desc").val(market.desc);

            var htmls = '';
            var delBtn = '<td></td>';
            for (var k = 0; k < market.fullDowns.length; k++) {
                if (k != 0) {
                    delBtn = '<td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td>';
                } else {
                    delBtn = '<td></td>';
                }
                var fullDown = market.fullDowns[k];
                index = index + 1;

                htmls += '<tr><td style="line-height:30px"> <div class="fll">满</div>' +
                    '<span class="form-group fll"><input id="' + (index + 1) + '" name="fullPrice" type="text" class="manjian form-control required isNumber" value="' + fullDown.fullPrice + '" style="width:80px; display:inline-block; margin:0 5px"></span>' +
                    '<div class="fll">元，减</div>' +
                    '<span class="form-group fll"><input id="' + (index + 2) + '"type="text" name="reducePrice" class="form-control required isNumber manjian2" value="' + fullDown.price + '" style="width:80px; display:inline-block; margin:0 5px"></span>' +
                    '<div class="fll">元</div> </td>' + delBtn + '</tr>';
                index = index + 1;

            }

            $("#fulldowntable").html(htmls);

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


// 修改满减
function updatefulldown() {
    if (!$("#updatefulldown").valid()) {
        return;
    }

    var marketing = {};
    marketing.id = $("#marketId").val();
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.fullDowns = new Array();
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

    $('#fulldowntable').find('tr').each(function () {
        var fullDown = {};
        fullDown.marketingId = $("#marketId").val();
        fullDown.fullPrice = $(this).find('input[type="text"]').eq(0).val();
        fullDown.price = $(this).find('input[type="text"]').eq(1).val();
        marketing.fullDowns.push(fullDown);
    });

    LSFetch({
        url: basePath + '/updatefulldown.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "toquerymarketing.htm";

        }
    })

}
