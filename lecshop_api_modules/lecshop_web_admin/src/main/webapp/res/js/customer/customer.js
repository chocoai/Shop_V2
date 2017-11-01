// 基本路径
var basePath = $("#basePath").val();

var addCustomerHtml = $("#add_dialog").html();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querycustomers.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {"name": "name", "data": "userName"},
            {"name": "level", "data": "customerLevel.name"},
            {"name": "mobile", "data": "mobile"},
            {"name": "email", "data": "email"},
            {
                "name": "isMobileVerification", "data": function (row) {
                if (row.isMobileVerification == "0") {
                    return '<span class="label label-default" style="cursor: default;">否</span>';
                } else {
                    return '<span class="label label-success" style="cursor: default;">是</span>';

                }
            }
            },
            {
                "name": "isEmailVerification", "data": function (row) {
                if (row.isEmailVerification == "0") {
                    return '<span class="label label-default" style="cursor: default;">否</span>';
                } else {
                    return '<span class="label label-success" style="cursor: default;">是</span>';

                }
            }
            },
            {
                "name": "status", "data": function (row) {
                if (row.status == "1") {
                    return '<span class="label label-success" style="cursor: default;">正常</span>';
                } else {
                    return '<span class="label label-default" style="cursor: default;">冻结</span>';

                }
            }
            },
            {
                "name": "type", "data": function (row) {
                if (row.type == "1") {
                    return '<span class="label label-default" style="cursor: default;">普通</span>';
                } else {
                    return '<span class="label label-success" style="cursor: default;">商家</span>';

                }
            }
            },
            {"name": "allPredeposit", "data": "allPredeposit"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-success btn-xs view_btn" type="button" onclick="todetail(' + row.id + ')"><i class="icon-eye-open"></i> 查看 </button>';
                    operator += '<button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 修改</button>';
                    operator += '<button  type="button"  class="btn btn-danger btn-xs del_btn" onclick="todelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button> </div>';
                    return operator;
                }
            }
        ],
        ordering: false
    })

};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
}

// 查看会员详情
function todetail(id) {
    window.location.href = basePath + "tocustomerinfo.htm?customerId=" + id;
}

// 跳转到删除会员页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除会员
function deleteCustomer() {
    LSFetch({
        url: basePath + '/deletecustomer.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}


// 准备删除全部会员
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的会员?", ids);
}

// 删除所有会员
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/deletecustomers.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}


// 添加会员
function toaddcustomer() {
    $("#add_dialog").html(addCustomerHtml);

    // 加载省份
    loadProvince();
    $('#add_dialog').modal('show');
}

// 保存会员
function savecustomer() {
    if (!$("#savecustomer").valid()) {
        return;
    }
    var customer = {};
    customer.userName = $("#username").val();
    customer.email = $("#email").val();
    customer.password = $("#password").val();
    customer.releName = $("#relename").val();
    customer.mobile = $("#mobile").val();
    customer.cardId = $("#cardId").val();
    customer.detailAddress = $("#detailAddress").val();
    customer.image = $("#image").attr("src");
    customer.province = $("#province").find("option:selected").val();
    customer.city = $("#city").find("option:selected").val();
    customer.county = $("#district").find("option:selected").val();
    customer.gender = $("input[name='addgender']:checked").val();

    LSFetch({
        url: basePath + '/addcustomer.htm',
        data: JSON.stringify(customer),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#add_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 上传用户头像
function updateload() {
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#savecustomer')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#image").attr("src", res);
    }).fail(function (res) {
    });
}

// 修改头像
function updatepic() {
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#updateCustomer')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#deimg").attr("src", res);
    }).fail(function (res) {
    });
}

// 加载省
function loadProvince() {
    LSFetch({
        url: basePath + 'queryallprovinces.htm',
        success: function (provinces) {
            var html = '<option  value="">选择省份</option>';
            for (var i = 0; i < provinces.length; i++) {
                var province = provinces[i];
                html += '<option  value="' + province.id + '"';
                html += '>' + province.name + '</option>';
            }
            $("#province").html(html);
        }
    })
}


// 加载市
function loadCity(obj) {

    // 如果省没有选择  则市也变成未选择
    if ($(obj).find("option:selected").val() == '') {
        $("#city").html('<option  value="">选择城市</option>');
        // 区也变成未选择
        loadDistrictById('');
        return;
    }

    LSFetch({
        url: basePath + 'querycitybyprovinceid.htm?provinceId=' + $(obj).find("option:selected").val(),
        success: function (citys) {
            var html = '<option  value="">选择城市</option>';
            for (var i = 0; i < citys.length; i++) {
                var city = citys[i];
                // 默认选中第一个 并且自动选择区
                if (i == 0) {
                    loadDistrictById(city.id);
                    html += '<option  selected="selected" value="' + city.id + '"';
                    html += '>' + city.name + '</option>';
                } else {
                    html += '<option  value="' + city.id + '"';
                    html += '>' + city.name + '</option>';
                }
            }
            $("#city").html(html);
        }
    })
}

// 加载区
function loadDistrict(obj) {
    loadDistrictById($(obj).find("option:selected").val());
}

// 加载区
function loadDistrictById(cityid) {

    // 如果没有选择市  则区也未选择
    if (cityid == '') {
        $("#district").html('<option  value="">选择区县</option>');
        return;
    }

    LSFetch({
        url: basePath + 'querydistrictbycityid.htm?cityId=' + cityid,
        success: function (distircts) {
            var html = '<option  value="">选择区县</option>';
            for (var i = 0; i < distircts.length; i++) {
                var distirct = distircts[i];
                // 默认选择第一个
                if (i == 0) {
                    html += '<option selected="selected"  value="' + distirct.id + '"';
                    html += '>' + distirct.name + '</option>';
                } else {
                    html += '<option  value="' + distirct.id + '"';
                    html += '>' + distirct.name + '</option>';
                }
            }
            $("#district").html(html);
        }
    })
}



// 跳转到更新页面
function toupdate(id) {
    uloadProvince();
    LSFetch({
        url: basePath + 'querycustomerbyid.htm?customerId=' + id,
        success: function (customer) {
            $("#ucustomerId").val(customer.id);
            if (customer.image != '') {
                $("#deimg").attr("src", customer.image);
            }
            $("#urealname").val(customer.releName);
            $("#ucardno").val(customer.cardId);
            $("#uaddress").val(customer.detailAddress);

            if (customer.province != '') {
                $("#uprovince").val(customer.province);
                uloadCityBId(customer.province,customer.city);
                $("#ucity").val(customer.city);
                $("#udistrict").val(customer.county);
            }

            $('#updateCustomer').find('input[type=radio]').each(function(){
                if ($(this).val() == customer.gender ) {
                    $(this).prop('checked',true)
                }
            })
        }
    })
    $('#update_dialog').modal('show');
}


// 加载省
function uloadProvince() {
    LSFetch({
        url: basePath + 'queryallprovinces.htm',
        async: false,
        success: function (provinces) {
            var html = '<option  value="">选择省份</option>';
            for (var i = 0; i < provinces.length; i++) {
                var province = provinces[i];
                html += '<option  value="' + province.id + '"';
                html += '>' + province.name + '</option>';
            }
            $("#uprovince").html(html);
        }
    })
}

function uloadCity(obj) {
    uloadCityBId($(obj).find("option:selected").val(),'');
}


// 加载市
function uloadCityBId(id,cid) {

    // 如果省没有选择  则市也变成未选择
    if (id == '') {
        $("#ucity").html('<option  value="">选择城市</option>');
        // 区也变成未选择
        uloadDistrictById('');
        return;
    }

    LSFetch({
        url: basePath + 'querycitybyprovinceid.htm?provinceId=' + id,
        async: false,
        success: function (citys) {
            var html = '<option  value="">选择城市</option>';
            for (var i = 0; i < citys.length; i++) {
                var city = citys[i];
                // 默认选中第一个 并且自动选择区
                if (i == 0 && cid != '') {
                    uloadDistrictById(cid);
                    html += '<option  value="' + city.id + '"';
                    html += '>' + city.name + '</option>';
                } else if (i == 0) {
                    uloadDistrictById(city.id);
                    html += '<option  selected="selected" value="' + city.id + '"';
                    html += '>' + city.name + '</option>';
                } else {
                    html += '<option  value="' + city.id + '"';
                    html += '>' + city.name + '</option>';
                }
            }
            $("#ucity").html(html);
        }
    })
}

// 加载区
function uloadDistrict(obj) {
    uloadDistrictById($(obj).find("option:selected").val());
}

// 加载区
function uloadDistrictById(cityid) {

    // 如果没有选择市  则区也未选择
    if (cityid == '') {
        $("#udistrict").html('<option  value="">选择区县</option>');
        return;
    }

    LSFetch({
        url: basePath + 'querydistrictbycityid.htm?cityId=' + cityid,
        async: false,
        success: function (distircts) {
            var html = '<option  value="">选择区县</option>';
            for (var i = 0; i < distircts.length; i++) {
                var distirct = distircts[i];
                // 默认选择第一个
                if (i == 0) {
                    html += '<option selected="selected"  value="' + distirct.id + '"';
                    html += '>' + distirct.name + '</option>';
                } else {
                    html += '<option  value="' + distirct.id + '"';
                    html += '>' + distirct.name + '</option>';
                }
            }
            $("#udistrict").html(html);
        }
    })
}

// 修改会员信息
function updatecustomer() {
    if (!$("#updateCustomer").valid()) {
        return;
    }
    var customer = {};
    customer.id = $("#ucustomerId").val();
    customer.releName = $("#urealname").val();
    customer.cardId = $("#ucardno").val();
    customer.detailAddress = $("#uaddress").val();
    customer.image = $("#deimg").attr("src");
    customer.province = $("#uprovince").find("option:selected").val();
    customer.city = $("#ucity").find("option:selected").val();
    customer.county = $("#udistrict").find("option:selected").val();
    customer.gender = $("input[name='ugenger']:checked").val();

    LSFetch({
        url: basePath + '/updatecustomer.htm',
        data: JSON.stringify(customer),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备发送通知
function tosendmsg() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    $("#sendform")[0].reset();

    $('#sms_dialog').modal('show');
}

// 发送通知
function sendmsg() {
    if (!$("#sendform").valid()) {
        return;
    }

    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    var letters = new Array();
    for (var i = 0; i < ids.length; i++) {
        var letter = {};
        letter.customerId = ids[i];
        letter.title = $("#title").val();
        letter.content = $("#content").val();
        letters.push(letter);
    }


    LSFetch({
        url: basePath + '/addstationletters.htm',
        data: JSON.stringify(letters),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#sms_dialog').modal('hide');
            showerror('发送成功!');
            refreshDataTable();
        }
    })


}