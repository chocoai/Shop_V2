var addHtml = $("#add_custom_brand_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/store_querybaseinfo.htm',
        success: function (data) {
            $(".logo img").attr("src", data.adminIndexLogo);
        }
    });
    LSFetch({
        url: basePath + '/store_querybusinessinfo.htm',
        success: function (data) {
            $("input[name='storeName']").val(data.storeInfo.storeName);
            for (var i = 0; i < data.threeCategoryList.length; i++) {
                var threeCategory = data.threeCategoryList[i];
                for (var x = 0; x < data.twoCategoryList.length; x++) {
                    var twoCategory = data.twoCategoryList[x];
                    if (twoCategory.id == threeCategory.parentId) {
                        var html1 = '<tr><input type="hidden" name="id" value="' + threeCategory.id + '"><td>' + twoCategory.name + '</td>';
                        html1 += '<td>' + threeCategory.name + '</td><td>' + threeCategory.rate + '</td>' +
                            '<td class="operation_box"><button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td>';
                        html1 += '</tr>';
                        $("#categoryTbody").append(html1);
                    }
                }
            }
            for (var j = 0; j < data.storeBrandList.length; j++) {
                var storeBrand = data.storeBrandList[j];
                var html2 = '<tr><input type="hidden" name="id" value="' + storeBrand.id + '"><td style="vertical-align: middle;text-align: center;">' + storeBrand.name + '</td>' +
                    '<td style="text-align: center;vertical-align: middle;"><img src="' + storeBrand.url + '" height="60" alt=""/></td>' +
                    '<td class="operation_box" style="text-align: center;vertical-align: middle;"> <button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td></tr>';
                $("#brandTbody").append(html2);
            }
            for (var k = 0; k < data.mySelfBrandList.length; k++) {
                var myselfBrand = data.mySelfBrandList[k];
                var html3 = '<tr><input type="hidden" name="id" value="' + myselfBrand.certificatUrl + '"><td style="vertical-align: middle;text-align: center;" name="brandName">' + myselfBrand.name + '</td>' +
                    '<td style="text-align: center;vertical-align: middle;"><img src="' + myselfBrand.url + '" height="60" alt=""/></td>' +
                    '<td class="operation_box" style="text-align: center;vertical-align: middle;"> <button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td></tr>';
                $("#myselfBrandTbody").append(html3);
            }
            $('table.table-striped').each(function () {
                var a = $(this).children('tbody');
                var b = a.find('tr').length;
                var c = $(this).children('thead').find('th').length;
                if (b == 0) {
                    a.append('<tr class="empty_tr"><td colspan="' + c + '" style="text-align:center; font-size:16px">未找到相关数据</td></tr>');
                }
                else {
                    a.find('.empty_tr').remove();
                }
            });
        }
    });
};

// 初始化列表
initDataTable();

// 上一步
function toSecondStep() {
    location.href = "store_tofillinstoreinfo.htm"
}

//添加分类按钮点击事件
function addCategoryBtn() {
    LSFetch({
        url: basePath + '/store_oneandtwocategory.htm',
        success: function (data) {
            var html = '<select class="form-control">';
            for (var i = 0; i < data.length; i++) {
                if (data[i].parentId == 0) {
                    html += '<optgroup value="' + data[i].id + '" label="' + data[i].name + '">';
                    for (var j = 0; j < data[i].childCateGory.length; j++) {
                        html += '<option value="' + data[i].childCateGory[j].id + '">' + data[i].childCateGory[j].name + '</option>';
                    }
                    html += '</optgroup>';
                } else {
                    return true;
                }
            }
            $("#categorySelect").html(html);
            showThreeCategory();
        }
    });
    $('#add_class_dialog').modal('show');
}

//三级分类显示
function showThreeCategory() {
    LSFetch({
        url: basePath + '/store_threecategory.htm',
        data: {
            parentId: $("#categorySelect").val()
        },
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                html += '<label class="checkbox-inline"> <input name="threeCheckbox" threeCategoryName="' + data[i].name + '" rate="' + data[i].rate + '" value="' + data[i].id + '" type="checkbox">' + data[i].name + '</label>';
            }
            $("#thirdCategoryDiv").html(html);
        }
    });
}

//全选按钮点击事件
function allSelectBtn() {
    $('input[name="threeCheckbox"]').prop("checked", "true");
}

//添加分类保存按钮点击事件
function addCategorySaveBtn() {
    var selectNum = 0;
    $("input[name='threeCheckbox']").each(function () {
        if ($(this).prop("checked")) {
            selectNum++;
        }
    });
    if (selectNum == 0) {
        showerror("至少选择一种分类");
        return;
    }
    forEachInput($('#categorySelect option:selected').text());
    $('#add_class_dialog').modal('hide');
}

//将选中的三级分类添加到tbody中
function forEachInput(secondCategoryName) {
    var html = '';
    $("#thirdCategoryDiv input[name='threeCheckbox']").each(function () {
        if ($(this).prop("checked")) {
            if ($.inArray($(this).val(), getIds("categoryTbody")) != -1) {
                return true;
            } else {
                html += '<tr><input type="hidden" name="id" value="' + $(this).val() + '"><td>' + secondCategoryName + '</td>';
                html += '<td>' + $(this).attr("threeCategoryName") + '</td><td>' + $(this).attr("rate") + '</td>' +
                    '<td class="operation_box"><button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td>';
                html += '</tr>';
            }
        }
    });
    $("#categoryTbody").find('.empty_tr').remove();
    $("#categoryTbody").append(html);
}

//获取tbody中有已存在的分类id
function getIds(name) {
    var array = new Array();
    $("#" + name + " input[name='id']").each(function () {
        array.push($(this).val());
    });
    return array;
}

//删除分类按钮点击事件
function deleteBtn(obj) {
    var a = $(obj).parent().parent().parent();
    $(obj).parent().parent().remove();
    var b = a.find('tr').length;
    var c = $(a).parent().children('thead').find('th').length;
    if (b == 0) {
        a.append('<tr class="empty_tr"><td colspan="' + c + '" style="text-align:center; font-size:16px">未找到相关数据</td></tr>');
    }
    else {
        a.find('.empty_tr').remove();
    }
}

//添加品牌点击事件
function addBrandBtn() {
    LSFetch({
        url: basePath + '/store_queryallbrand.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                html += '<option url="' + data[i].url + '" value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $("#brandSelect").html(html);
            $('#brandSelect').chosen('destroy');
            $("#brandSelect").chosen();
        }
    });
    $('#add_brand_dialog').modal('show');
}

//添加品牌保存按钮点击事件
function addBrandSaveBtn() {
    if ($.inArray($('#brandSelect option:selected').val(), getIds("brandTbody")) != -1) {
        showerror("已添加,请重新选中");
    } else {
        var html = '<tr><input type="hidden" name="id" value="' + $('#brandSelect option:selected').val() + '"><td style="vertical-align: middle;text-align: center;">' + $('#brandSelect option:selected').text() + '</td>' +
            '<td style="text-align: center;vertical-align: middle;"><img src="' + $('#brandSelect option:selected').attr("url") + '" height="60" alt=""/></td>' +
            '<td class="operation_box" style="text-align: center;vertical-align: middle;"> <button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td></tr>';
        $("#brandTbody").append(html);
        $("#brandTbody").find('.empty_tr').remove();
        $('#add_brand_dialog').modal('hide');
    }
}

//添加自定义品牌点击事件
function addMyselfBrandBtn() {
    $('#add_custom_brand_dialog').html(addHtml);
    $('#add_custom_brand_dialog').modal('show');
}

//添加自定义品牌保存事件
function addMyselfBrandSaveBtn() {
    if (!$('#brandName').valid() | formValid("brandPic") | formValid("brandCertificate")) {
        return;
    }
    var brandName = $("input[name='brandName']").val();
    var brandPic = $("img[name='brandPic']").attr("src");
    var brandCertificate = $("img[name='brandCertificate']").attr("src");
    var html = '<tr><input type="hidden" name="id" value="' + brandCertificate + '"><td style="vertical-align: middle;text-align: center;" name="brandName">' + brandName + '</td>' +
        '<td style="text-align: center;vertical-align: middle;"><img src="' + brandPic + '" height="60" alt=""/></td>' +
        '<td class="operation_box" style="text-align: center;vertical-align: middle;"> <button class="btn btn-danger btn-xs del_btn" onclick="deleteBtn(this)"><i class="icon-trash"></i> 删除</button></td></tr>';
    $("#myselfBrandTbody").append(html);
    $("#myselfBrandTbody").find('.empty_tr').remove();
    $('#add_custom_brand_dialog').modal('hide');
}

function formValid(name) {
    if ($("img[name='" + name + "']").val() == basePath + "/res/img/no_img.png") {
        return !$("#" + name).valid();
    }
}
// 上传商品图片
function uploadPic(obj) {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $('#' + obj).validate().resetForm();
        $("img[name='" + obj + "']").attr("src", res);
    }).fail(function (res) {
    });
}

function toFourStep() {
    var storeName = $("input[name='storeName']").val();
    if (storeName == "") {
        showerror("请输入店铺名称");
        return;
    }
    if ($("#categoryTbody input[name='id']").length == 0) {
        showerror("请添加经营类目");
        return;
    }
    if ($("#brandTbody input[name='id']").length == 0) {
        showerror("请添加主营品牌");
        return;
    }
    var categoryIds = dealIdsToArray("categoryTbody", new Array());
    var brandIds = dealIdsToArray("brandTbody", new Array());
    var brandArray = dealBrandList(new Array());
    var storeBusiness = {
        storeName: storeName,
        categoryIds: categoryIds,
        brandIds: brandIds,
        brands: brandArray
    };
    LSFetch({
        url: basePath + '/store_businessinfo.htm',
        data: JSON.stringify(storeBusiness),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 0) {
                showerror("系统错误");
                return;
            }
            if (data == -1) {
                showerror("店铺名称重复");
                return;
            }
            if (data == 1) {
                location.href = "store_tosubmitresults.htm";
            }
        }
    });
}
function dealIdsToArray(name, array) {
    $("#" + name + " input[name='id']").each(function () {
        array.push($(this).val());
    });
    return array;
}

function dealBrandList(brandArray) {
    if ($("#myselfBrandTbody input[name='id']").length != 0) {
        $("#myselfBrandTbody tr").each(function () {
            var brand = {};
            brand.certificatUrl = $(this).find("input[name='id']").val();
            brand.name = $(this).find("td[name='brandName']").text();
            brand.url = $(this).find("img").attr("src");
            brandArray.push(brand);
        });
    }
    return brandArray;
}
