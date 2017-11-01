var basePath = $("#basePath").val();

// 更新积分设置
function updatePointset() {

    if (!$("#pointform").valid()) {
        return;
    }
    var pointSet = {};
    pointSet.id = $('#id').val();
    pointSet.isOpen = $('#isOpen').find('option:selected').val();
    pointSet.emailPoint = $('#emailPoint').val();
    pointSet.mobilePoint = $('#mobilePoint').val();
    pointSet.commentPoint = $('#commentPoint').val();
    pointSet.usePoint = $('#usePoint').val();
    pointSet.offsetMoney = $('#offsetMoney').val();


    LSFetch({
        url: basePath + '/updatepointseting.htm',
        data: JSON.stringify(pointSet),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                showerror('修改成功!');
            } else {
                showerror('修改失败');
            }
        }
    })
}