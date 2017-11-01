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

// 加载包邮促销
loadFreeShip();

function loadFreeShip() {
    LSFetch({
        url: basePath + '/queryfreeship.htm',
        success: function (market) {
            $("#marketingId").val(market.id);
            $("#name").val(market.name);
            if (market.freeShip != null) {
                $("#fullPrice").val(market.freeShip.fullPrice);
            }
            $("#startTime").val(market.startTime);
            $("#endTime").val(market.endTime);
            $("#desc").val(market.desc);
        }
    })
}

// 保存包邮促销
function saveFreeShip() {
    if (!$("#savefreeship").valid() | !dataValid("startTime") | !dataValid("endTime")) {
        return;
    }

    var freeShip = {};
    freeShip.fullPrice = $("#fullPrice").val();

    var marketing = {};
    marketing.id = $("#marketingId").val();
    marketing.name = $("#name").val();
    marketing.desc = $("#desc").val();
    marketing.startTime = $("#startTime").val();
    marketing.endTime = $("#endTime").val();
    marketing.freeShip = freeShip;


    LSFetch({
        url: basePath + '/addfreeship.htm',
        data: JSON.stringify(marketing),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "toquerymarketing.htm";

        }
    })
}
