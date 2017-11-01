// 基本路径
var basePath = $("#basePath").val();

var index = 1;
$('#add_sales_jian').click(function () {
    if ($(".manjian").length < 3) {
        index = index + 1;
        $(this).nextAll('table').append('<tr><td style="line-height:30px"><div class="fll">满</div><span class="form-group fll"><input id="' + (index + 1) + '" name="fullPrice" type="text" class="manjian form-control required isNumber" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">元，减</div> <span class="form-group fll"><input id="' + (index + 2) + '" type="text" name="reducePrice" class="form-control manjian2 required isNumber" value="" style="width:80px; display:inline-block; margin:0 5px"></span><div class="fll">元</div></td> <td class="operation_box"><button class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td></tr>');
        index = index + 1;
    }
});
$('body').on('click', '.del_btn', function () {
    $(this).parents('tr').remove();
});

// 新增满减
function savefulldown() {
    if (!$("#savefulldown").valid()| !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }

    var marketing = {};
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.fullDowns = new Array();
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

    $('#fulldowntable').find('tr').each(function () {
        var fullDown = {};
        fullDown.fullPrice = $(this).find('input[type="text"]').eq(0).val();
        fullDown.price = $(this).find('input[type="text"]').eq(1).val();
        marketing.fullDowns.push(fullDown);
    });

    LSFetch({
        url: basePath + '/store_addfulldownmarketing.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerymarketing.htm";

        }
    })

}