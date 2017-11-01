var basePath = $("#basePath").val();

var editSkuHtml = $("#edit_dialog").html();

var addSpuCount = 0;

// 单品集合
var skus = new Array();

// 商品规格值
var spuSpecValues = new Array();

/* 富文本编辑框 */
$('.summernotedesc').summernote({
    height: 300,
    tabsize: 2,
    lang: 'zh-CN',
    onImageUpload: function (files, editor, $editable) {
        sendFile(files, editor, $editable);
    }
});


/* 富文本编辑框 */
$('.summernotemobile').summernote({
    height: 300,
    tabsize: 2,
    lang: 'zh-CN',
    onImageUpload: function (files, editor, $editable) {
        sendFile(files, editor, $editable);
    }
});

$(function () {
    loadFCate(0);
})

function loadFCate(parentId) {
    $("#fcate").html('');
    $("#scate").html('');
    $("#tcate").html('');
    $("#cateId").val('');
    LSFetch({
        url: basePath + 'store_queryspucategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '" onclick="loadSCate(this,' + cate.id + ',\'' + cate.name + '\')" class="item">' +
                    '<div class="task-title item" > <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }

            $("#fcate").html(html);
            $("#fcate").find("li.item")[0].click();
        }
    })
}


// 加载二级分类
function loadSCate(obj, parentId, parentName) {
    clickItem(obj);
    $("#scate").html('');
    $("#tcate").html('');
    $("#curfcateId").val(parentId);
    $("#cateId").val('');
    $("#fcateId").val(parentId);
    $("#fcatename").val(parentName);
    LSFetch({
        url: basePath + 'store_queryspucategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '"  onclick="loadTCate(this,' + cate.id + ',\'' + cate.name + '\')" class="item">' +
                    '<div class="task-title item"> <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#scate").html(html);
            if ($("#scate").find("li.item").length > 0) {
                $("#scate").find("li.item")[0].click();
            }

        }
    })
}

// 加载三级分类
function loadTCate(obj, parentId, parentName) {
    clickItem(obj);
    $("#tcate").html('');
    $("#curscateId").val(parentId);
    $("#cateId").val('');
    $("#scateId").val(parentId);
    $("#scatename").val(parentName);

    LSFetch({
        url: basePath + 'store_queryspucategorybyparentid.htm?parentId=' + parentId,
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];
                html += '<li id="item' + cate.id + '" class="item"  onclick="clickThirdCate(this,' + cate.id + ',\'' + cate.name + '\')">' +
                    '<div class="task-title item"> <span class="task-title-sp">' + cate.name + '</span>' +
                    '<div class="pull-right" style="display:none">' +
                    '</div>' +
                    '</div>' +
                    '</li>';
            }
            $("#tcate").html(html);
            if ($("#tcate").find("li.item").length > 0) {
                $("#tcate").find("li.item")[0].click();
            }
        }
    })
}

function clickThirdCate(obj, parentId, parentName) {
    $("#cateId").val(parentId);
    $("#tcatename").val(parentName);
    clickItem(obj);
}

/**
 * 点击按钮时，改变按钮状态。
 * @param obj 按钮对象
 */
function clickItem(obj) {
    if (obj == null) return;
    $(obj).parent().find("li.item").each(function () {
        $(this).removeClass("active");
    });
    $(obj).addClass("active");
}


// 加载品牌信息
function loadBrands() {
    LSFetch({
        url: basePath + '/store_queryallbrands.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var brand = data[i];
                html += '<option  value="' + brand.id + '"';
                html += '>' + brand.name + '</option>';
            }
            $("#brands").html(html);
            $('#brands').chosen();
        }
    })
}

/**
 * 跳转到第二步
 */
function toaddbaseinfo() {

    var catId = $("#cateId").val();

    //如果没有选择三级分类 则直接提示错误
    if (catId == "") {
        showerror("请选择三级分类！");
        return;
    }


    // 加载签约分类
    loadSignCategory();

    // 加载品牌
    loadBrands();


    //加载服务支持
    loadServiceSupport();

    $("#catenames").html($("#fcatename").val() + '--' + $("#scatename").val() + '--' + $("#tcatename").val());

    $("#addcate").hide();
    $("#addbaseinfo").show();
}

// 加载服务支持
function loadServiceSupport() {
    LSFetch({
        url: basePath + '/store_queryallservicesupport.htm.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                html += '<label class="checkbox-inline"> <input type="checkbox" name="servicesupport" value="' + data[i].id + '">' + data[i].name + ' </label>';
            }
            $("#servicesupprots").html(html);
        }
    })
}


// 加载类型信息
function toloadType(obj) {
    loadType($(obj).val().split(";")[1]);
}


// 加载签约分类
function loadSignCategory() {
    LSFetch({
        url: basePath + '/store_queryallsignedcategorys.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var signcategory = data[i];
                html += '<option  value="' + signcategory.cateId + ';' + signcategory.category.typeId + '"';
                html += '>' + signcategory.category.name + '</option>';

                if (i == 0) {
                    // 加载类型数据
                    loadType(signcategory.category.typeId);
                }

            }
            $("#signcategorys").html(html);
            $('#signcategorys').chosen();
        }
    })
}

// 加载类型
function loadType(id) {
    $("#attr tbody").html('');
    LSFetch({
        url: basePath + '/store_querytypedetail.htm?id=' + id,
        success: function (type) {
            // 加载属性
            loadAttributes(type.attributes);

            // 加载规格
            loadSpecs(type.typeSpecs);
        }
    })
}


// 加载属性
function loadAttributes(attributes) {
    var attributeHtml = "";
    if (attributes.length > 0) {
        for (var i = 0; i < attributes.length; i++) {
            if (i % 3 == 0) {
                attributeHtml = attributeHtml + "<tr>";
            }
            attributeHtml = attributeHtml + "<td style='vertical-align:middle'><span class='text-danger'>*</span>" + attributes[i].name + "</td><td><select attributename='" + attributes[i].name + "' class='form-control'  id='" + attributes[i].id + "'>";
            for (var k = 0; k < attributes[i].attributeValues.length; k++) {
                attributeHtml = attributeHtml + "<option value='" + attributes[i].attributeValues[k].id + "'>" + attributes[i].attributeValues[k].value + "</option>";
            }
            ;
            attributeHtml = attributeHtml + "</select></td>";
            if ((i + 1) % 3 == 0 || i == attributes.length - 1) {
                attributeHtml = attributeHtml + "</tr>";
            }
        }
    } else {
        attributeHtml = attributeHtml + "<tr><td>您选择的商品分类下没有属性!</td></tr>";
    }

    $("#attr tbody").html(attributeHtml);
}

// 加载规格
function loadSpecs(specs) {
    var specIds = new Array();

    for (var i = 0; i < specs.length; i++) {
        specIds.push(specs[i].specId);
    }


    // 查询规格信息
    LSFetch({
        url: basePath + '/store_queryspecsbyids.htm',
        data: {ids: specIds.toString()},
        success: function (specs) {
            var specHtmls = '';
            for (var i = 0; i < specs.length; i++) {
                var spec = specs[i];
                var specHtml = '';
                specHtml += '<div class="panel panel-default" style="margin-bottom:0">';
                specHtml += '<div class="panel-heading clearfix"> <h4 class="panel-title fll">' + spec.name + ':</h4></div>'
                specHtml += '<div class="panel-collapse">';
                specHtml += '<div class="panel-body" style="padding:5px">';
                specHtml += '<div class="gg_table clearfix">';
                specHtml += loadSpecValueHtml(specs[i].specValues, spec.id);
                specHtml += '</div>';
                specHtml += '</div>';
                specHtml += '</div>';
                specHtml += '</div>';
                specHtmls += specHtml;
            }

            $("#specHtmls").html(specHtmls);


        }
    })


}

// 加载规格值的htm
function loadSpecValueHtml(specValues, specId) {
    var specValueHtmls = '';
    for (var i = 0; i < specValues.length; i++) {
        var specValueHtml = '<div class="sku_item"><form id="form' + specValues[i].id + '">';
        specValueHtml += '<div class="input-group">';
        specValueHtml += '<span class="input-group-addon"><input type="checkbox"></span><input maxlength="10" myimage="" id="id' + specValues[i].id + '" type="text" detail="' + specId + '&&' + specValues[i].id + '" class="form-control" value="' + specValues[i].name + '"></div>';
        specValueHtml += '<div class="fileupload fileupload-new" style="float:left; margin:0" data-provides="fileupload"><div class="fileupload-preview fileupload-exists thumbnail"></div> <div style=" float:left"> <span class="btn btn-white btn-file"> <span class="fileupload-new"><i class="icon-paper-clip"></i></span> <span class="fileupload-exists"><i class="icon-undo"></i></span> <input type="file" name="image" onchange="uploadspecvalueimage(' + specValues[i].id + ')" class="default"/> </span> </div> </div>';
        specValueHtml += '</form></div>';
        specValueHtmls += specValueHtml;
    }

    return specValueHtmls;
}
// 上传规格值的图片
function uploadspecvalueimage(id) {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#form' + id)[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#id" + id).attr("myimage", res);
    }).fail(function (res) {
    });
}

// 上传商品图片
function uploadPic() {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#addspu')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        var html = '<div class="alert fade in select-pic"> <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i> </button> <img src="' + res + '" width="70" height="70" alt=""/></div>';
        $("#spupics").append(html);
    }).fail(function (res) {
    });
}

// 上传单品图片
function uploadSkuPic() {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#addSkuForm')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        var html = '<div class="alert fade in select-pic"> <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i> </button> <img src="' + res + '" width="70" height="70" alt=""/></div>';
        $("#skupics").append(html);
    }).fail(function (res) {
    });
}


function chooseSpecs() {

    // 清空单品集合 重新生成单品
    skus = new Array();

    // 清空商品规格值
    spuSpecValues = new Array();

    var allSpecValues = new Array();
    var allSpecNames = new Array();
    $('#specHtmls').find('.panel').each(function () {
        var spevvalues = new Array();
        var xk = 0;
        $(this).find('.sku_item').each(function () {
            if ($(this).find('input[type=checkbox]').prop("checked") == true) {
                // 规格名称只加载一次
                if (xk == 0) {
                    allSpecNames.push($(this).parents('.panel').find('h4').html());
                }
                spevvalues.push($(this).find('input[type=text]').val() + "&&" + $(this).find('input[type=text]').attr("detail") + "&&" + $(this).find('input[type=text]').attr("myimage"));
                xk = 1;
            }
        });
        // 说明有选中的规格值
        if (xk == 1) {
            allSpecValues.push(spevvalues);
        }
    });

    // 单品结果
    var resultArray = new Array();
    setSpuSpecValues(allSpecValues);
    permutationInt(allSpecValues, resultArray, 0, new Array(allSpecValues.length));
    loadSkuTableHead(allSpecNames);
    loadSkuBodys(resultArray);

    $(".sku_sum").show();


    console.log(skus);
}

// 设置商品的规格值
function setSpuSpecValues(allSpecValues) {
    for (var i = 0; i < allSpecValues.length; i++) {
        for (var k = 0; k < allSpecValues[i].length; k++) {
            var spuSpecValue = {};
            spuSpecValue.specId = allSpecValues[i][k].split("&&")[1];
            spuSpecValue.specValueId = allSpecValues[i][k].split("&&")[2];
            spuSpecValue.valueRemark = allSpecValues[i][k].split("&&")[0];
            spuSpecValue.url = allSpecValues[i][k].split("&&")[3];
            spuSpecValues.push(spuSpecValue);
        }
    }
}

// 获得商品图片
function getSpuPics() {

    var spuPics = new Array();
    $('#spupics').find('img').each(function () {
        var skuImage = {};
        skuImage.url = $(this).prop('src');
        skuImage.delFlag = '0';
        spuPics.push(skuImage);
    });
    return spuPics;
}

// 加载单品信息
function loadSkuBodys(resultArray) {
    var html = '';

    // 循环单品
    for (var i = 0; i < resultArray.length; i++) {

        var uniqueId = new Date().format("yyyyMMddhhmmss") + i;
        // 单品信息
        var sku = {};
        sku.skuNo = uniqueId;
        sku.unique = uniqueId;
        sku.stock = '';
        sku.price = $("#spuPrice").val();
        sku.weight = '';
        sku.shelvesStatus = '1';
        sku.delFlag = '0';
        sku.skuSpecValues = new Array();
        sku.skuMemberPrices = new Array();
        sku.skuImages = getSpuPics();
        sku.status = "2";
        var skuHtml = '<tr tid = ' + uniqueId + '>';

        var specAllValue = '';
        // 循环单品的规格值
        for (var k = 0; k < resultArray[i].length; k++) {
            var specValueRemark = resultArray[i][k].split("&&")[0];
            skuHtml += '<td><a class="sku_a" href="javascript:;">' + specValueRemark + '</a></td>';
            var skuSpecValue = {};
            skuSpecValue.specId = resultArray[i][k].split("&&")[1];
            skuSpecValue.specValueId = resultArray[i][k].split("&&")[2];
            skuSpecValue.valueRemark = resultArray[i][k].split("&&")[0];
            sku.skuSpecValues.push(skuSpecValue);
            if (k == 0) {
                specAllValue += specValueRemark;
            } else {
                specAllValue += "/" + specValueRemark;
            }
        }


        var auditStatusHtml = "";

        if (sku.status == "0") {
            auditStatusHtml = '<span class="label label-success label-mini">通过</span>';
        } else if (sku.status == "1") {
            auditStatusHtml = '<span class="label label-danger label-mini tooltips" data-placement="right" data-original-title="' + sku.reason + '">拒绝</span>';
        } else {
            auditStatusHtml = '<span class="label label-warning label-mini">待审核</span>';
        }


        skuHtml += '<td><input unique="' + uniqueId + '" onchange="weightChange(this)" type="text" value="" class="form-control sku_weight"></td>' +
            '<td> <input unique="' + uniqueId + '" onchange="priceChange(this)" type="text" value="' + sku.price + '" class="form-control fll sku_price">  </td>' +
            '<td><input onchange="stockChange(this)" unique="' + uniqueId + '" type="text" value="" class="form-control sku_stock"></td>' +
            '<td>' + auditStatusHtml + '</td>' +
            '<td>  <button type="button" onclick="toEditSku(' + uniqueId + ')" class="btn btn-primary btn-xs edit_btn"><i class="icon-pencil"></i> 编辑</button> <button type="button" onclick="toDelete(' + uniqueId + ')" class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button> </td></tr>';

        html += skuHtml;
        sku.remark = '(' + specAllValue + ')';
        sku.name = $("#spuName").val() + '(' + specAllValue + ')';
        sku.subTitle = $("#spuSubTitle").val() + '(' + specAllValue + ')';
        skus.push(sku);
    }

    $("#skuBodys").html(html);
}

// 改变重量
function weightChange(obj) {
    findSku($(obj).attr("unique")).weight = $(obj).val();
}

// 改变价格
function priceChange(obj) {
    findSku($(obj).attr("unique")).price = $(obj).val();
}

// 改变库存
function stockChange(obj) {
    findSku($(obj).attr("unique")).stock = $(obj).val();

}


function findSku(unique) {
    for (var i = 0; i < skus.length; i++) {
        if (skus[i].unique == unique) {
            return skus[i];
        }
    }
}

// 加载单品的表单头
function loadSkuTableHead(allSpecNames) {
    var html = '';
    for (var i = 0; i < allSpecNames.length; i++) {
        html += '  <th>' + allSpecNames[i] + '</th>';
    }

    html += '<th width="85"><span class="label_red">*</span>重量(克)</th><th width="85"><span class="label_red">*</span>价格(元)</th><th width="80"><span class="label_red">*</span>库存</th><th width="80">审核</th><th>操作</th>';

    $('#addSkuDia').modal('hide');
    $("#skuHead").html(html);
}


// 递归计算出规格的单品组合
function permutationInt(inputarray, resarray, start, array) {
    if (start == inputarray.length) {
        resarray.push(array.slice());
        return;
    }

    for (var i = 0; i < inputarray[start].length; i++) {
        array[start] = inputarray[start][i];
        permutationInt(inputarray, resarray, start + 1, array);
    }

}

// 修改单品信息
function toEditSku(id) {

    $("#edit_dialog").html(editSkuHtml);
    var sku = findSku(id);
    $("#skuuniqueid").val(id);
    $("#skuname").val(sku.name);
    $("#skusubtitle").val(sku.subTitle);
    $("#skuno").val(sku.skuNo);
    if (sku.warningStock != -1) {
        $("#skuwarningstock").val(sku.warningStock);
    }
    if (sku.shelvesStatus == "1") {
        $('input:radio:first').attr('checked', 'checked');
    } else {
        $('input:radio:last').attr('checked', 'checked');
    }

    var skuImagesHmtl = '';
    for (var i = 0; i < sku.skuImages.length; i++) {
        skuImagesHmtl += '<div class="alert fade in select-pic"> <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i> </button> <img src="' + sku.skuImages[i].url + '" width="70" height="70" alt=""/></div>';
    }

    $("#skupics").append(skuImagesHmtl);

    $('#edit_dialog').modal('show');
}

// 修改单品
function updatesku() {

    if (!$("#addSkuForm").valid()) {
        return;
    }

    var uniqueid = $("#skuuniqueid").val();
    var sku = findSku(uniqueid);
    sku.name = $("#skuname").val();
    sku.subTitle = $("#skusubtitle").val();
    sku.skuNo = $("#skuno").val();
    if ($("#skuwarningstock").val() == '') {
        sku.warningStock = -1;
    } else {
        sku.warningStock = $("#skuwarningstock").val();
    }
    sku.shelvesStatus = $('input:radio[name="up"]:checked').val();
    sku.skuImages = getSkuImages();

    $('#edit_dialog').modal('hide');

}

// 获得单品图片
function getSkuImages() {
    var sukImageas = new Array();

    $('#skupics').find('img').each(function () {
        var skuImage = {};
        skuImage.delFlag = '0';
        skuImage.url = $(this).prop('src');
        sukImageas.push(skuImage);
    });
    return sukImageas;
}

// 弹出规格页面
function tochoosespecs() {
    if (!$("#addspu").valid()) {
        return;
    }

    var picCount = 0;

    $('#spupics').find('img').each(function () {
        picCount++;
    });
    if (picCount == 0) {
        showerror("请上传商品图片!");
        return;
    }


    $('#addSkuDia').modal('show');
}

// 发布商品
function addSpu() {

    if (addSpuCount == 1) {
        return;
    }

    var spu = {};
    spu.spuImportId = $("#spuImportId").val();
    spu.name = $("#spuName").val();
    spu.subTitle = $("#spuSubTitle").val();
    spu.price = $("#spuPrice").val();
    spu.pcDesc = $('.summernotedesc').code();
    spu.mobileDesc = $(".summernotemobile").code();
    spu.seoTitle = $("#seoTitle").val();
    spu.seoKeywords = $("#seoKeywords").val();
    spu.seoDesc = $("#seoDesc").val();
    spu.storeFcateId = $("#fcateId").val();
    spu.storeScateId = $("#scateId").val();
    spu.storeTcateId = $("#cateId").val();
    spu.thirdCateId = $('#signcategorys option:selected').val().split(";")[0];
    spu.typeId = $('#signcategorys option:selected').val().split(";")[1];
    spu.brandId = $('#brands option:selected').val();
    spu.skus = skus;
    spu.spuImages = getSpuPics();
    spu.spuSpecValues = spuSpecValues;
    spu.spuAttributeValues = new Array();
    spu.spuServiceSupports = new Array();
    if (!$("#addspu").valid()) {
        return;
    }

    var picCount = 0;

    $('#spupics').find('img').each(function () {
        picCount++;
    });
    if (picCount == 0) {
        showerror("请上传商品图片!");
        return;
    }

    if (skus.length == 0) {
        showerror('请选择规格信息!');
        return;
    }


    var hasweighterror = false;

    for (var i = 0; i < $('#skuBodys').find(".sku_weight").length; i++) {
        if ($('#skuBodys').find(".sku_weight")[i].value == '' || !(/^\d+$/.test($('#skuBodys').find(".sku_weight")[i].value))) {
            hasweighterror = true;
        }
    }

    if (hasweighterror) {
        showerror('请输入正确的重量!');
        return;
    }

    var haspriceerror = false;

    for (var j = 0; j < $('#skuBodys').find(".sku_price").length; j++) {
        if ($('#skuBodys').find(".sku_price")[j].value == '' || !(/^([0-9][0-9]*(\.[0-9]{1,2})?|0\.(?!0+$)[0-9]{1,2})$/.test($('#skuBodys').find(".sku_price")[j].value))) {
            haspriceerror = true;
        }
    }

    if (haspriceerror) {
        showerror('请输入正确的价格!');
        return;
    }

    var hasstockerror = false;
    for (var k = 0; k < $('#skuBodys').find(".sku_stock").length; k++) {
        if ($('#skuBodys').find(".sku_stock")[k].value == '' || !(/^\d+$/.test($('#skuBodys').find(".sku_stock")[k].value))) {
            hasstockerror = true;
        }
    }

    if (hasstockerror) {
        showerror('请输入正确的库存!');
        return;
    }


    // 设置商品的属性
    $('#attr').find('select.form-control').each(function () {

        var spuAttributeValue = {};

        spuAttributeValue.attributeId = $(this).prop('id');
        spuAttributeValue.attributeName = $(this).attr('attributename');
        spuAttributeValue.attributeValueId = $(this).find('option:selected').val();
        spuAttributeValue.attributeValue = $(this).find('option:selected').html();
        spuAttributeValue.delFlag = '0';
        spu.spuAttributeValues.push(spuAttributeValue);
    });


    $("input[name='servicesupport']:checked").each(function () {
        var spuServiceSupport = {};
        spuServiceSupport.serviceSupportId = this.value;
        spu.spuServiceSupports.push(spuServiceSupport);
    });

    addSpuCount = 1;

    $("#addSpuId").attr("disabled", true);
    LSFetch({
        url: basePath + '/store_addspu.htm',
        data: JSON.stringify(spu),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 0) {
                showerror('添加商品失败,请重试!');
            } else {
                window.location.href = "store_toqueryspus.htm";
            }
        }
    })
}

// 批量设置重量,价格和库存
$('.batchall_set').click(function () {
    var weight = $(this).prevAll('.sku_weight').val();
    var price = $(this).prevAll('.sku_price').val();
    var stock = $(this).prevAll('.sku_stock').val();
    if (!isNaN(price) == false || !isNaN(stock) == false || !isNaN(weight) == false) {
        $('.price_Modal').modal('show');
    } else {
        if (price != '' && !isNaN(price) == true) {
            $('.sku_price').val(price);
        }
        if (stock != '' && !isNaN(stock) == true) {
            $('.sku_stock').val(stock);
        }
        if (weight != '' && !isNaN(weight) == true) {
            $('.sku_weight').val(weight);
        }

        for (var i = 0; i < skus.length; i++) {
            skus[i].price = price;
            skus[i].stock = stock;
            skus[i].weight = weight;
        }

    }
});

// 将图片应用到所有的单品
function pictoallsku() {
    for (var i = 0; i < skus.length; i++) {
        skus[i].skuImages = getSpuPics();
    }
    showerror('设置成功');
}

// 将商品名称设置到所有的单品
function nametoallsku() {
    for (var i = 0; i < skus.length; i++) {
        skus[i].name = $("#spuName").val() + skus[i].remark;
    }
    showerror('设置成功');
}

// 将商品副标题设置到所有的单品
function subtitletoallsku() {
    for (var i = 0; i < skus.length; i++) {
        skus[i].subTitle = $("#spuSubTitle").val() + skus[i].remark;
    }
    showerror('设置成功');
}

// 添加规格值
$('body').on('click', '.add_sku_btn', function () {
    var specValue = {};
    specValue.specId = $(this).parents('.sku_item').prev().find('input[type="text"]').attr('detail').split("&&")[0];
    specValue.name = $(this).parent().find('.sku_input').val();

    if (specValue.name == '') {
        showerror("请输入规格名称");
        return;
    }

    var current = $(this);

    LSFetch({
        url: basePath + '/addspecvalue.htm',
        data: JSON.stringify(specValue),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            current.parent().before('<div class="sku_item"><form id="form' + res + '"><div class="input-group"><span class="input-group-addon"><input type="checkbox"></span><input maxlength="10" myimage="" id="id' + res + '" type="text" detail="' + specValue.specId + '&&' + res + '" class="form-control" value="' + specValue.name + '"></div><div class="fileupload fileupload-new" style="float:left; margin:0" data-provides="fileupload"><div class="fileupload-preview fileupload-exists thumbnail"></div><div style=" float:left"><span class="btn btn-white btn-file"><span class="fileupload-new"><i class="icon-paper-clip"></i></span><span class="fileupload-exists"><i class="icon-undo"></i></span><input type="file" name="image" onchange="uploadspecvalueimage(' + res + ')" class="default"/></span></div></div></form></div>')
            current.parent().remove();
        }
    })
});


$('.panel_prev').click(function () {
    $(this).parents('.mypanel').hide().prev().show();
});
$('body').on('click', '.add_sku', function () {
    $(this).parent().next().find('.gg_table').append('<div class="sku_item"><div class="input-group"><input type="text" maxlength="10" class="form-control sku_input"></div><button type="button" class="btn btn-info add_sku_btn">确定</button></div>')
});

function toDelete(id) {
    $("#deleteId").val(id);
    $('.del_sku').modal('show');
}

function deleteSku() {
    var tid = $("#deleteId").val();
    $('#skuBodys').find('tr').each(function () {
        if ($(this).attr('tid') == tid) {
            $(this).remove();
            // 删除单品信息
            skus.removeSku(tid);
        }
    })
    $('.del_sku').modal('hide');
}