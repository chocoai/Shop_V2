var fallHtml = '<div class="form-group remove"><label class="col-sm-2 control-label"><span class="label_red">*</span>减：</label>' +
    '<div class="col-sm-2"><input type="text" class="form-control required money" value="" name="price"></div></div>';
var fullHtml = '<div class="form-group remove"><label class="col-sm-2 control-label"><span class="label_red">*</span>满：</label> ' +
    '<div class="col-sm-2"><input type="text" class="form-control required money" value="" name="fullPrice"></div></div>' +
    '<div class="form-group remove"><label class="col-sm-2 control-label"><span class="label_red">*</span>减：</label>' +
    '<div class="col-sm-2"><input type="text" class="form-control required money" value="" name="price"></div></div>';
$(function () {
    if ($('.price_select').val() == 1) {
        $("#selectType").after(fullHtml);
    }
    if ($('.price_select').val() == 2) {
        $("#selectType").after(fallHtml);
    }

});
// 基本路径
var basePath = $("#basePath").val();

$(".search_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:00',
    weekStart: 1,
    autoclose: true,
    language: 'zh',
    todayBtn: true,
    viewSelect: 'hour'
});

$('.price_select').change(function () {
    $(".remove").remove();
    if ($(this).val() == 1) {
        $("#selectType").after(fullHtml);
    }
    if ($(this).val() == 2) {
        $("#selectType").after(fallHtml);
    }
});


function addSaveBtn() {
    if (!$("#form").valid() | !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }

    var coupon = {
        name: $("input[name='name']").val(),
        num: $("input[name='num']").val(),
        limitNum: $("input[name='limitNum']").val(),
        type: $("select[name='type']").val(),
        desc: $("textarea[name='desc']").val(),
        startTime: $("input[name='startTime']").val(),
        endTime: $("input[name='endTime']").val(),
        fullPrice: $("input[name='fullPrice']").val(),
        price: $("input[name='price']").val()
    };
    LSFetch({
        url: basePath + '/addcoupon.htm',
        data: JSON.stringify(coupon),
        contentType: "application/json;charset=utf-8",
        success: function (data) {//-1 失败 -2 限领张数不正确 -3 限领张数不能大于总张数 >=1 成功
            if (data >= 1) {
                $("#form input").val("");
                $("#form textarea").val("");
                $("#success_dialog").modal('show');
                return;
            }
            if (data == -1) {
                showerror("添加失败");
                return;
            }
            if (data == -2) {
                showerror("限领张数不正确");
                return;
            }
            if (data == -3) {
                showerror("限领张数不能大于总张数");
                return;
            }
            if (data == -4) {
                showerror("开始时间不能大于结束时间");
            } else {
                showerror("异常");
            }
        }
    });
}

function toSee() {
    location.href = basePath + 'tocouponlist.htm' + window.location.search.replace("thirdMenus=236", "thirdMenus=237");
}


