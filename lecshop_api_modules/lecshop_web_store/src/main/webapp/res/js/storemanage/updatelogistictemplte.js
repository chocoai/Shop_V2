// 基本路径
var basePath = $("#basePath").val();

$('.del_btn').click(function () {
    $('#del_dialog').modal('show');
});
$('#add_btn,.edit_btn').click(function () {
    $('#add_dialog').modal('show');
});
function unit_text() {
    if ($('.pricing_select').val() == 0) {
        $('.unit').text('件');
        $('.unit_th').eq(0).text('首件');
        $('.unit_th').eq(1).text('续件');
    }
    if ($('.pricing_select').val() == 1) {
        $('.unit').text('g');
        $('.unit_th').eq(0).text('首重');
        $('.unit_th').eq(1).text('续重');
    }
}
$('.pricing_select').change(function () {
    unit_text();
});
function freight() {
    if ($('.freight_select').val() == 0) {
        $('.freight_form').show();
    }
    if ($('.freight_select').val() == 1) {
        $(".input1").val('1');
        $(".input2").val('0');
        $(".input3").val('1');
        $(".input4").val('0');
        $(".input1").parents('td').find("span").html('1');
        $(".input2").parents('td').find("span").html('0');
        $(".input3").parents('td').find("span").html('1');
        $(".input4").parents('td').find("span").html('0');
        $('.freight_form').hide();
    }
}
$('.freight_select').change(function () {
    freight();
});


loadTemplate();

// 加载物流模版信息
function loadTemplate() {
    LSFetch({
        url: basePath + '/store_querylogisticstemplatebyid.htm?id=' + $("#templateId").val(),
        success: function (templage) {
            $("#name").val(templage.name);
            $("#freightBear").val(templage.freightBear);
            $("#pricintMethod").val(templage.pricintMethod);
            // 加载物流公司
            loadCompany(templage.logisticsCompany);

            var unit = '件';

            if (templage.pricintMethod == '1') {
                unit = 'g';
                $("#unit_th").html('首重');
                $("#unit_th_plu").html('续重');
            }
            // 加载运费方式
            loadShippingMethods(templage.shippingMethods, unit);

            freight();
        }
    })
}

// 加载运费方式
function loadShippingMethods(shippingMethods, unit) {

    var htmls = '';
    for (var i = 0; i < shippingMethods.length; i++) {
        var shippingmethod = shippingMethods[i];

        // 默认运费方式
        if (shippingmethod.isDefault == '1') {
            var html = ' 默认运费：<input type="text"  value="' + shippingmethod.first + '"  class="form-control input1"><span class="unit">' + unit + '</span>内, ';
            html += '<input type="text"  value="' + shippingmethod.money + '" class="form-control input2">元，  每增加';
            html += '<input type="text"    value="' + shippingmethod.firstPlu + '" class="form-control input3"><span class="unit">' + unit + '</span>, 增加运费';
            html += '<input type="text"    value="' + shippingmethod.moenyPlu + '"  class="form-control input4">元';
            $("#defaultShippingMethod").html(html);
        } else {
            htmls += '<tr><td> <div class="express_area"> <p>' + shippingmethod.cityNames + '</p>   <input type="hidden"  name="areas" class="input0" value="' + shippingmethod.cityIds + '"> </div> </td>';
            htmls += ' <td><input type="hidden" value="' + shippingmethod.first + '" class="input1"><span>' + shippingmethod.first + '</span> </td>';
            htmls += '<td><input type="hidden" value="' + shippingmethod.money + '" class="input2" ><span>' + shippingmethod.money + '</span> </td>';
            htmls += '<td><input type="hidden" value="' + shippingmethod.firstPlu + '" class="input3" /><span>' + shippingmethod.firstPlu + '</span> </td>';
            htmls += '<td><input type="hidden" value="' + shippingmethod.moenyPlu + '" class="input4" <span>' + shippingmethod.moenyPlu + '</span> </td>';
            htmls += ' <td width="140" class="operation_box"> <button class="btn btn-danger btn-xs del_btn" type="button" onclick="removeAdress(this)"><i class="icon-trash"></i> 删除</button></td> </td></tr>';
        }
    }

    $("#tempdetail").html(htmls);
}


// 加载物流公司
function loadCompany(logisticsCompany) {
    LSFetch({
        url: basePath + '/store_querycanusecompany.htm',
        success: function (data) {
            var html = '<option value="' + logisticsCompany.id + '">' + logisticsCompany.name + '</option>';
            for (var i = 0; i < data.length; i++) {
                var company = data[i];
                html += '<option  value="' + company.id + '"';
                html += '>' + company.name + '</option>';
            }
            $("#company").html(html);
        }
    })
}


// 删除运费方式
function removeAdress(obj) {
    $(obj).parents('tr').remove();
}


var setting = {
    check: {
        enable: true,
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    view: {
        showIcon: false
    }

};


// 选中城市
function choosecitys() {
    var treeObj = $.fn.zTree.getZTreeObj('treeDemo');
    var nodes = treeObj.getCheckedNodes(true);
    var v = '';
    var tempCityId = '';
    for (var i = 0; i < nodes.length; i++) {
        if (!(nodes[i].isParent)) {
            v += nodes[i].name + ',';
            tempCityId += nodes[i].cityId + ',';
        }
    }

    if (tempCityId != '') {
        $('.city_choosed p').html(v.substring(0, v.length - 1));
        $('#tempCity').val(tempCityId.substring(0, tempCityId.length - 1));
    } else {
        $('.city_choosed p').html('');
        $('#tempCity').val('');
    }
}

function toadd() {

    // 判断是否存在运送方式
    if ($("#logComId").val() == "-1") {
        showerror('请选择一个运送方式!');
        return;
    }

    $('.city_choosed p').html('');
    $("#tempCity").val('');
    $("#firstnum").val('1');
    $("#firstfee").val('0');
    $("#contnum").val('1');
    $("#contfee").val('0');


    if ($("#pricintMethod").val() == "1") {
        $("#num").html('首重');
        $("#fee").html('首重运费');
        $("#xujian").html('续重');
        $("#xufee").html('续重运费');
    } else {
        $("#num").html('首件个数');
        $("#fee").html('首件运费');
        $("#xujian").html('续件个数');
        $("#xufee").html('续件运费');
    }

    LSFetch({
        url: basePath + '/store_queryallprovinceswithcity.htm',
        success: function (data) {
            //查询出内容加载省份
            var zNodes = new Array();
            if (data != null) {
                var node = {
                    id: 1, pId: 0, name: '全国', open: true
                };
                zNodes.push(node);
                for (var i = 0; i < data.length; i++) {
                    var a = i + 2;
                    var node = {
                        id: a, pId: 1, name: data[i].name, open: false
                    };
                    zNodes.push(node);
                    //加载城市
                    if (data[i].cities != null) {
                        for (var j = 0; j < data[i].cities.length; j++) {
                            var b = a + "" + (j + 1);
                            var node = {
                                id: b, pId: a, name: data[i].cities[j].name, cityId: data[i].cities[j].id, open: false
                            };
                            zNodes.push(node);
                        }
                    }
                }
            }

            $.fn.zTree.init($("#treeDemo"), setting, zNodes);


            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getNodes();

            for (var k = 0; k < treeObj.transformToArray(nodes).length; k++) {
                var node = treeObj.transformToArray(nodes)[k];
                $("input[name=areas]").each(function () {
                    var $arr = new Array();
                    var $val = $(this).val();
                    if ($val != undefined) {
                        $arr = $val.split(",");
                    }
                    for (var p = 0; p < $arr.length; p++) {
                        if ($arr[p] == node.cityId) {
                            treeObj.setChkDisabled(node, true, false, true);
                        }
                    }

                });
            }
        }
    })
    $('#add_dialog').modal('show');
}

// 保存运费方式
function toaddtemp() {

    if (!$("#addshipping").valid()) {
        return;
    }

    var htm = '';
    htm += '<tr>';
    htm += ' <td>';
    htm += '    <p>' + $('.city_choosed p').html() + '</p>  <input class="input0" type="hidden"  name="areas" value="' + $("#tempCity").val() + '">';
    htm += ' <td>  <input type="hidden" class="input1" value="' + $("#firstnum").val() + '" ><span>' + $("#firstnum").val() + '</span></td>';
    htm += ' <td>  <input type="hidden" class="input2" value="' + $("#firstfee").val() + '"   ><span>' + $("#firstfee").val() + '</span></td>';
    htm += ' <td>  <input type="hidden" class="input3" value="' + $("#contnum").val() + '"   /><span>' + $("#contnum").val() + '</span></td>';
    htm += '<td>  <input type="hidden"  class="input4" value="' + $("#contfee").val() + '"><span>' + $("#contfee").val() + '</span></td>';
    htm += '<td width="140" class="operation_box"> <button class="btn btn-danger btn-xs del_btn" type="button" onclick="removeAdress(this)"><i class="icon-trash"></i> 删除</button></td>';
    $("#tempdetail").append(htm);
    $('#add_dialog').modal('hide');
}

// 更新物流模版
function updatetemplate() {
    if (!$("#updatetemplate").valid()) {
        return;
    }

    var template = {};
    template.id = $("#templateId").val();
    template.companyId = $('#company option:selected').val();
    template.name = $("#name").val();
    template.isDefault = "1";
    template.freightBear = $('#freightBear option:selected').val();
    template.pricintMethod = $('#pricintMethod option:selected').val();
    template.shippingMethods = new Array();

    var shippingMethod = {};
    shippingMethod.first = $('.freight_box').find('.form-control').eq(0).val();
    shippingMethod.money = $('.freight_box').find('.form-control').eq(1).val();
    shippingMethod.firstPlu = $('.freight_box').find('.form-control').eq(2).val();
    shippingMethod.moenyPlu = $('.freight_box').find('.form-control').eq(3).val();
    shippingMethod.isDefault = '1';
    template.shippingMethods.push(shippingMethod);
    $('#tempdetail').find('tr').each(function () {
        var shmethod = {};
        shmethod.first = $(this).find('td').eq(1).find('input').val();
        shmethod.money = $(this).find('td').eq(2).find('input').val();
        shmethod.firstPlu = $(this).find('td').eq(3).find('input').val();
        shmethod.moenyPlu = $(this).find('td').eq(4).find('input').val();
        shmethod.isDefault = '0';
        shmethod.shippingMethodAreas = new Array();

        var cityIds = $(this).find('td').eq(0).find('input').val().split(',');
        for (var i = 0; i < cityIds.length; i++) {
            var shippingMethodAreas = {};
            shippingMethodAreas.cityId = cityIds[i];
            shmethod.shippingMethodAreas.push(shippingMethodAreas);
        }
        template.shippingMethods.push(shmethod);
    });

    LSFetch({
        url: basePath + '/store_updatelogisticstemplate.htm',
        data: JSON.stringify(template),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + "store_toquerylogisticstemplate.htm";
        }
    })
}

// 返回
function back() {
    window.location.href = basePath + "store_toquerylogisticstemplate.htm";
}
