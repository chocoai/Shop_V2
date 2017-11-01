var basePath = $("#basePath").val();


$(function () {
    loadProvince();
})


// 加载省
function loadProvince() {
    $("#province").html('');
    $("#city").html('');
    $("#district").html('');

    LSFetch({
        url: basePath + 'queryallprovinces.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var province = data[i];
                html += '<li id="item' + province.id + '"  onclick="loadCity(this,' + province.id + ')" class="item">' +
                    '<div class="task-title item" > <span class="task-title-sp">' + province.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button href="javascript:;" class="btn btn-primary btn-xs" type="button" onclick="toupdatepro(' + province.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button  href="javascript:;" class="btn btn-danger btn-xs del_btn" type="button" onclick="todeletepro(' + province.id + ')"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#province").html(html);
            $("#province").find("li.item")[0].click();
        }
    })
}

// 加载市
function loadCity(obj, provinceId) {
    $("#proid").val(provinceId);
    $("#city").html('');
    $("#district").html('');
    clickItem(obj);

    LSFetch({
        url: basePath + 'querycitybyprovinceid.htm?provinceId=' + provinceId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var city = data[i];
                html += '<li id="item' + city.id + '" onclick="loadDistrict(this,' + city.id + ')"  class="item" ' +
                    '<div class="task-title item"> <span class="task-title-sp">' + city.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button class="btn btn-primary btn-xs" type="button" onclick="toupdatecity(' + city.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button class="btn btn-danger btn-xs del_btn" type="button" onclick="todeletecity(' + city.id + ')"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#city").html(html);
            if ($("#city").find("li.item").length > 0) {
                $("#city").find("li.item")[0].click();
            }
        }
    })
}


// 加载区
function loadDistrict(obj, cityId) {
    $("#cityid").val(cityId);
    $("#district").html('');
    clickItem(obj);

    LSFetch({
        url: basePath + 'querydistrictbycityid.htm?cityId=' + cityId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var district = data[i];
                html += '<li id="item' + district.id + '" onclick="clickDistrict(this,' + district.id + ')" class="item" >' +
                    '<div class="task-title item"> <span class="task-title-sp">' + district.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '<button class="btn btn-primary btn-xs" type="button" onclick="toupdatedist(' + district.id + ')"><i class="icon-pencil"></i></button>&nbsp;' +
                    '<button type="button" onclick="todeletedist(' + district.id + ')" class="btn btn-danger btn-xs del_btn"><i class="icon-trash "></i></button>' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#district").html(html);
            if ($("#district").find("li.item").length > 0) {
                $("#district").find("li.item")[0].click();
            }
        }
    })
}

function clickDistrict(obj, id) {
    $("#districtid").val(id);
    clickItem(obj);
}

function clickItem(obj) {
    if (obj == null) return;
    $(obj).parent().find("li.item").each(function () {
        $(this).removeClass("active");
    });
    $(obj).addClass("active");
}


// 跳转到添加省份页面
function toaddprovince() {
    $("#saveprovince")[0].reset();
    $('#addprovince_dialog').modal('show');
}


// 保存省份
function saveprovince() {
    if (!$("#saveprovince").valid()) {
        return;
    }

    var province = {};
    province.name = $("#addressName").val();
    province.sort = $("#pasort").val();

    LSFetch({
        url: basePath + '/addprovince.htm',
        data: JSON.stringify(province),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}

// 跳转到添加市页面
function toaddcity() {
    $("#savecity")[0].reset();

    LSFetch({
        url: basePath + 'queryallprovinces.htm',
        success: function (data) {
            var provinces = "";
            for (var i = 0; i < data.length; i++) {
                provinces += "<option class='pro" + data[i].id + "' value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            $("#allpro").html(provinces);
            $("#allpro").find("option.pro" + $("#proid").val() + "").prop("selected", true);
            $('#allpro').chosen('destroy');
            $('#allpro').chosen();
        }
    })

    $('#addcity_dialog').modal('show');
}

// 保存市
function savecity() {
    if (!$("#savecity").valid()) {
        return;
    }

    var city = {};
    city.name = $("#cityname").val();
    city.sort = $("#casort").val();
    city.provinceId = $("#allpro option:selected").val();
    LSFetch({
        url: basePath + '/addcity.htm',
        data: JSON.stringify(city),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}

// 跳转到添加区页面
function tooadddist() {
    $("#savedist")[0].reset();


    LSFetch({
        url: basePath + 'querycitybyprovinceid.htm?provinceId=' + $("#proid").val(),
        success: function (data) {
            var provinces = "";
            for (var i = 0; i < data.length; i++) {
                provinces += "<option class='city" + data[i].id + "' value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            $("#allcitys").html(provinces);
            $("#allcitys").find("option.city" + $("#cityid").val() + "").prop("selected", true);
            $('#allcitys').chosen('destroy');
            $('#allcitys').chosen();
        }
    })

    $('#adddist_dialog').modal('show');
}

// 保存区
function savedist() {
    if (!$("#savedist").valid()) {
        return;
    }

    var district = {};
    district.name = $("#distname").val();
    district.sort = $("#dasort").val();
    district.cityId = $("#allcitys option:selected").val();
    LSFetch({
        url: basePath + '/adddistrict.htm',
        data: JSON.stringify(district),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })
}

// 准备删除区
function todeletedist(id) {
    $("#deldistid").val(id);
    $('#deldist_dialog').modal('show');
}


// 删除区
function deldist() {
    LSFetch({
        url: basePath + '/deletedistrictbyid.htm?id=' + $("#deldistid").val(),
        success: function () {
            location.reload();
        }
    })
}


// 准备删除市
function todeletecity(id) {
    $("#delcityid").val(id);
    $('#delcity_dialog').modal('show');
}


// 删除市
function delcity() {
    LSFetch({
        url: basePath + 'deletecitybyid.htm?id=' + $('#delcityid').val(),
        success: function (res) {
            if (res == -1) {
                $('#delcity_dialog').modal('hide');
                showerror("该城市下存在区县信息，不允许删除!");
            } else {
                location.reload();
            }
        }
    })
}


// 准备删除省
function todeletepro(id) {
    $("#delproid").val(id);
    $('#delpro_dialog').modal('show');
}

// 删除省
function delpro() {
    LSFetch({
        url: basePath + 'deleteprovincebyid.htm?id=' + $('#delproid').val(),
        success: function (res) {
            if (res == -1) {
                $('#delpro_dialog').modal('hide');
                showerror("该省下存在市区信息，不允许删除!");
            } else {
                location.reload();
            }
        }
    })
}

// 准备修改省份
function toupdatepro(id) {
    $("#updateid").val(id);

    LSFetch({
        url: basePath + 'queryprovincebyid.htm?id=' + id,
        success: function (res) {
            $("#updatename").val(res.name);
            $("#updatesort").val(res.sort);
        }
    })

    $('#update_dialog').modal('show');
}

// 准备修改市
function toupdatecity(id) {
    $("#updatecid").val(id);

    LSFetch({
        url: basePath + 'querycitybyid.htm?id=' + id,
        success: function (res) {
            $("#updatecname").val(res.name);
            $("#updatecsort").val(res.sort);
        }
    })

    $('#update_city_dia').modal('show');
}

// 准备修改区
function toupdatedist(id) {
    $("#updatedid").val(id);

    LSFetch({
        url: basePath + 'querydistrictbyid.htm?id=' + id,
        success: function (res) {
            $("#updatedname").val(res.name);
            $("#updatedsort").val(res.sort);
        }
    })

    $('#update_district_dia').modal('show');
}

// 修改省份
function updatepro() {
    if (!$("#updateform").valid()) {
        return;
    }

    var province = {};
    province.id = $("#updateid").val();
    province.name = $("#updatename").val();
    province.sort = $("#updatesort").val();
    LSFetch({
        url: basePath + '/updateprovince.htm',
        data: JSON.stringify(province),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })

}

// 修改市
function updateCity() {
    if (!$("#update_city").valid()) {
        return;
    }

    var city = {};
    city.id = $("#updatecid").val();
    city.name = $("#updatecname").val();
    city.sort = $("#updatecsort").val();
    LSFetch({
        url: basePath + '/updatecity.htm',
        data: JSON.stringify(city),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })

}

// 修改区
function updateDist() {
    if (!$("#update_distric").valid()) {
        return;
    }

    var district = {};
    district.id = $("#updatedid").val();
    district.name = $("#updatedname").val();
    district.sort = $("#updatedsort").val();
    LSFetch({
        url: basePath + '/updatedistrict.htm',
        data: JSON.stringify(district),
        contentType: "application/json;charset=utf-8",
        success: function () {
            location.reload();
        }
    })

}