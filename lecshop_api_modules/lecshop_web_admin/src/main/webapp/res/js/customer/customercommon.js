// 基本路径
var basePath = $("#basePath").val();

// 查询用户基本信息
function querycustomerinfo(id) {
    window.location.href = basePath + "tocustomerinfo.htm?customerId=" + id;
}

// 查询用户积分
function querypoint(id) {
    window.location.href = basePath + "toquerycustomerpoint.htm?customerId=" + id;
}

// 查询用户预存款
function querypredeposit(id) {
    window.location.href = basePath + "toquerypredeposit.htm?customerId=" + id;
}

// 查询用户商品关注
function toqueryattention(id) {
    window.location.href = basePath + "toqueryattention.htm?customerId=" + id;
}

// 返回到会员列表页面
function goback() {
    window.location.href = basePath + "toquerycustomer.htm";
}