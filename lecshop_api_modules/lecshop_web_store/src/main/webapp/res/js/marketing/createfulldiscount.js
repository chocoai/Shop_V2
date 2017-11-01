// 基本路径
var basePath = $("#basePath").val();

var index = 1;
$('#add_sales_jian').click(function () {
    if ($(".manzhe").length < 3) {
        index = index + 1;
        $(this).nextAll('table').append('<tr><td style="line-height:30px"><div class="fll">满</div><span class="form-group fll"><input id="' + (index + 1) + '" name="fullPrice" type="text" class="manzhe form-control required isNumber" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">元，打</div> <span class="form-group fll"><input id="' + (index + 2) + '" type="text" name="reducePrice" class="form-control manzhe2 required zeroOne" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">折</div></td> <td class="operation_box"><button class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td></tr>');
        index = index + 1;
    }
});
$('body').on('click', '.del_btn', function () {
    $(this).parents('tr').remove();
});

// 新增满折
function savefulldiscount() {
    if (!$("#savefulldiscount").valid()| !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }

    var marketing = {};
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.fullDiscounts = new Array();
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

    $('#fulldiscounttable').find('tr').each(function () {
        var fullDiscount = {};
        fullDiscount.fullPrice = $(this).find('input[type="text"]').eq(0).val();
        fullDiscount.discount = $(this).find('input[type="text"]').eq(1).val();
        marketing.fullDiscounts.push(fullDiscount);
    });

    LSFetch({
        url: basePath + '/store_addfulldiscount.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";

        }
    })

}