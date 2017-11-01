// 基本路径
var basePath = $("#basePath").val();

// 跳转到添加会员等级页面
function toadd() {
    $('#add_dialog').modal('show');
}

// 新增会员等级
function addCustomerLevel() {

    if (!$("#addCustomerLevel").valid()) {
        return;
    }

    var customerLevel = {};
    customerLevel.name = $("#levelName").val();
    customerLevel.minMoney = $("#minMoney").val();
    customerLevel.maxMoney = $("#maxMoney").val();

    LSFetch({
        url: basePath + '/addcustomerlevel.htm',
        data: JSON.stringify(customerLevel),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == -1) {
                showerror("消费金额范围已经存在!");
            } else {
                location.reload();
            }
        }
    })
}

// 准备删除会员等级
function todelete(id) {
    $("#deleteId").val(id);
    $('#remove_dialog').modal('show');
}

// 删除会员等级
function deleteLevel() {
    LSFetch({
        url: basePath + '/deletecustomerlevel.htm?id=' + $("#deleteId").val(),
        success: function () {
            location.reload();
        }
    })
}

// 跳转到更新会员等级页面
function toupdate(id) {

    $("#levelId").val(id);

    LSFetch({
        url: basePath + '/querycustomerlevelbyid.htm?id=' + id,
        success: function (res) {
            $("#ulevelName").val(res.name);
            $("#uminMoney").val(res.minMoney);
            $("#umaxMoney").val(res.maxMoney);
        }
    })
    $('#update_dialog').modal('show');
}

// 修改会员等级
function updatelevel() {


    if (!$("#updatepointform").valid()) {
        return;
    }
    var customerLevel = {};
    customerLevel.name = $("#ulevelName").val();
    customerLevel.minMoney = $("#uminMoney").val();
    customerLevel.maxMoney = $("#umaxMoney").val();
    customerLevel.id = $("#levelId").val();
    LSFetch({
        url: basePath + '/updatecustomerlevel.htm',
        data: JSON.stringify(customerLevel),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == -1) {
                showerror("消费金额范围已经存在!");
            } else {
                location.reload();
            }
        }
    })
}