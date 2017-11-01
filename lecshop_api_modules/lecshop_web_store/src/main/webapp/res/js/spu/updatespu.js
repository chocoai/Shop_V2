var basePath = $("#basePath").val();

var editSkuHtml = $("#edit_dialog").html();


// 查询商品信息
queryspu();

// 更新的商品实体
var updateSpu = {};

// 查询商品信息
function queryspu() {
    LSFetch({
        url: basePath + '/store_queryspubyid.htm?id=' + $("#spuId").val(),
        success: function (res) {
            updateSpu = res;

            // 设置单品的唯一编号
            setSkuUnique();

            // 加载商品基本信息
            loadSpuBaseInfo();

            // 加载品牌信息
            loadBrands();

            // 加载商品图片
            loadSpuImages();

            // 加载类型数据
            loadType();

            // 设置选中的属性值
            setAttributeValuesSelected();

            // 加载单品数据
            loadSkus();

            // 加载规格
            loadSpecs();

            //加载服务支持
            loadServiceSupport();
        }
    })
}


// 加载服务支持
function loadServiceSupport() {
    LSFetch({
        url: basePath + '/store_queryallservicesupport.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {

                var checkFlag = '';
                for (var j = 0; j < updateSpu.spuServiceSupports.length; j++) {
                    if (updateSpu.spuServiceSupports[j].serviceSupportId == data[i].id) {
                        checkFlag = 'checked';
                    }
                }
                html += '<label class="checkbox-inline"> <input  '+checkFlag+' type="checkbox" name="servicesupport" value="' + data[i].id + '">' + data[i].name + ' </label>';
            }
            $("#servicesupprots").html(html);
        }
    })
}


// 加载规格信息
function loadSpecs() {
    var specIds = new Array();
    var allHtml = '';
    for (var i = 0; i < updateSpu.spuSpecValues.length; i++) {

        // 规格只能有一个 如果有了 就不添加
        if (specIds.indexOf(updateSpu.spuSpecValues[i].spec.id) < 0) {

            var html = ' <div class="form-group"> <label class="col-sm-3 control-label">' + updateSpu.spuSpecValues[i].spec.name + '：</label>    <div class="col-sm-9">       <select class="form-control">';
            for (var k = 0; k < updateSpu.spuSpecValues[i].spec.specValues.length; k++) {
                html += '<option specId="' + updateSpu.spuSpecValues[i].spec.id + '" value="' + updateSpu.spuSpecValues[i].spec.specValues[k].id + '">' + updateSpu.spuSpecValues[i].spec.specValues[k].name + '</option>';
            }
            html += ' </select></div></div>';

            allHtml += html;
            specIds.push(updateSpu.spuSpecValues[i].spec.id);
        }
    }
    $("#spuspecspecvalues").html(allHtml);
}


// 将图片应用到所有的单品
function pictoallsku() {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        updateSpu.skus[i].skuImages = getSpuPics();
    }
    showerror('设置成功');
}


// 加载商品基本信息
function loadSpuBaseInfo() {
    $("#spuName").val(updateSpu.name);
    $("#spuSubTitle").val(updateSpu.subTitle);
    $("#spuPrice").val(updateSpu.price);
    $(".summernotedesc").html(updateSpu.pcDesc);
    $(".summernotemobile").html(updateSpu.mobileDesc);
    $("#seoTitle").val(updateSpu.seoTitle);
    $("#seoKeywords").val(updateSpu.seoKeywords);
    $("#seoDesc").val(updateSpu.seoDesc);

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
}

// 设置单品的唯一编号
function setSkuUnique() {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        updateSpu.skus[i].unique = new Date().format("yyyyMMddhhmmss") + i;
    }
}

// 加载品牌信息
function loadBrands() {
    LSFetch({
        url: basePath + '/store_queryallbrands.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var brand = data[i];
                if (brand.id == updateSpu.brandId) {
                    html += '<option selected="true"  value="' + brand.id + '"';
                } else {
                    html += '<option  value="' + brand.id + '"';
                }
                html += '>' + brand.name + '</option>';
            }
            $("#brands").html(html);
            $('#brands').chosen();
        }
    })
}

// 加载商品图片
function loadSpuImages() {
    var html = '';
    for (var i = 0; i < updateSpu.spuImages.length; i++) {
        html += '<div class="alert fade in select-pic"> <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i> </button> <img src="' + updateSpu.spuImages[i].url + '" width="70" height="70" alt=""/></div>';
    }
    $("#spupics").append(html);
}


// 加载类型
function loadType() {
    $("#attr tbody").html('');
    LSFetch({
        url: basePath + '/store_querytypedetail.htm?id=' + updateSpu.typeId,
        async: false,
        success: function (type) {
            // 加载属性
            loadAttributes(type.attributes);
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

// 设置选中的属性值
function setAttributeValuesSelected() {
    for (var i = 0; i < updateSpu.spuAttributeValues.length; i++) {
        $("#" + updateSpu.spuAttributeValues[i].attributeId).find("option[value='" + updateSpu.spuAttributeValues[i].attributeValueId + "']").attr("selected", true);
    }
}

// 选择新的规格
function tochoosespecs() {
    $('#addSkuDialog').modal('show');
}

// 判断规格和规格值的组合是否已经存在
function isSpecSpecValueExist(specspecvalue) {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        var existspecspecvalue = '';
        for (var k = 0; k < updateSpu.skus[i].skuSpecValues.length; k++) {
            existspecspecvalue += ',' + updateSpu.skus[i].skuSpecValues[k].specId + ',' + updateSpu.skus[i].skuSpecValues[k].specValueId;
        }

        if (specspecvalue == existspecspecvalue) {
            return true;
        }
    }
    return false;
}

// 判断商品规格是否存在
function isSpuSpecExist(spuSpec) {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        for (var k = 0; k < updateSpu.skus[i].skuSpecValues.length; k++) {
            if ((updateSpu.skus[i].skuSpecValues[k].specId + ',' + updateSpu.skus[i].skuSpecValues[k].specValueId) == spuSpec) {
                return true;
            }
        }
    }
    return false;
}

// 加载单品数据
function loadSkus() {
    // 加载单品的头
    loadSkuHead();
    // 加载单品的内容
    loadSkuBodys();
}

// 加载单品的头部
function loadSkuHead() {
    var specIds = new Array();
    var html = '';
    for (var i = 0; i < updateSpu.spuSpecValues.length; i++) {
        // 判断规格是否已经加载 如果没有加载则放入
        if (specIds.indexOf(updateSpu.spuSpecValues[i].specId) < 0) {
            html += '<th>' + updateSpu.spuSpecValues[i].spec.name + '</th>';
            specIds.push(updateSpu.spuSpecValues[i].specId);
        }
    }

    html += '<th width="85"><span class="label_red">*</span>重量(克)</th><th width="85"><span class="label_red">*</span>价格(元)</th><th width="80"><span class="label_red">*</span>库存</th><th width="80">审核</th><th width="80">状态</th><th>操作</th>';

    $("#skuHead").html(html);
}

// 加载单品的内容
function loadSkuBodys() {
    var skuHtmls = '';
    for (var i = 0; i < updateSpu.skus.length; i++) {
        var sku = updateSpu.skus[i];
        var skuHtml = '<tr tid = ' + sku.unique + '>';

        var remark = '';
        for (var k = 0; k < sku.skuSpecValues.length; k++) {
            skuHtml += '<td><a class="sku_a" href="javascript:;">' + sku.skuSpecValues[k].valueRemark + '</a></td>';

            if (k == 0) {
                remark += sku.skuSpecValues[k].valueRemark;
            } else {
                remark += "/" + sku.skuSpecValues[k].valueRemark;
            }
        }

        updateSpu.skus[i].remark = '(' + remark + ')';

        var shelvesStatusHtml = "";

        if (sku.shelvesStatus == "0") {
            shelvesStatusHtml = '<span class="label label-default label-mini">下架</span>';
        } else {
            shelvesStatusHtml = '<span class="label label-success label-mini">上架</span>';
        }

        var auditStatusHtml  = "";

        if (sku.status == "0") {
            auditStatusHtml = '<span class="label label-success label-mini">通过</span>';
        } else if (sku.status == "1") {
            auditStatusHtml = '<span class="label label-danger label-mini tooltips" data-placement="right" data-original-title="'+sku.reason+'">拒绝</span>';
        } else {
            auditStatusHtml = '<span class="label label-warning label-mini">待审核</span>';
        }


        skuHtml += '<td><input unique="' + sku.unique + '" onchange="weightChange(this)" type="text" value="' + sku.weight + '" class="form-control sku_weight"></td>' +
            '<td> <input unique="' + sku.unique + '" onchange="priceChange(this)" type="text" value="' + sku.price + '" class="form-control fll sku_price"></td>' +
            '<td><input onchange="stockChange(this)" unique="' + sku.unique + '" type="text" value="' + sku.stock + '" class="form-control sku_stock"></td>' +
                '<td>'+auditStatusHtml+'</td>'+
                '<td>'+shelvesStatusHtml+'</td>'+
            '<td> <button type="button" onclick="toEditSku(' + sku.unique + ')" class="btn btn-primary btn-xs edit_btn"><i class="icon-pencil"></i> 编辑</button> <button type="button" onclick="toDelete(' + sku.unique + ')" class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td></tr>';
        skuHtmls += skuHtml;
    }
    $("#skuBodys").html(skuHtmls);


    $('.tooltips').tooltip();
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

// 查询单品
function findSku(unique) {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        if (updateSpu.skus[i].unique == unique) {
            return updateSpu.skus[i];
        }
    }
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

    loadSkuBodys();
    $('#edit_dialog').modal('hide');

}

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
            updateSpu.skus.removeSku(tid);
        }
    })
    $('.del_sku').modal('hide');
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

// 更新商品信息
function updateSpuSave() {

    if (!$("#updatespu").valid()) {
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

    if (updateSpu.skus.length == 0) {
        showerror('请选择规格信息!');
        return;
    }



    var hasweighterror = false;

    for (var i = 0; i < $('#skuBodys').find(".sku_weight").length; i++) {
        if ($('#skuBodys').find(".sku_weight")[i].value == '' ||  !(/^\d+$/.test($('#skuBodys').find(".sku_weight")[i].value))) {
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
        return ;
    }

    var hasstockerror = false;
    for (var k = 0; k < $('#skuBodys').find(".sku_stock").length; k++) {
        if ($('#skuBodys').find(".sku_stock")[k].value == '' ||!(/^\d+$/.test($('#skuBodys').find(".sku_stock")[k].value))) {
            hasstockerror = true;
        }
    }

    if (hasstockerror) {
        showerror('请输入正确的库存!');
        return ;
    }

    $("#updateSpuId").attr("disabled", true);
    LSFetch({
        url: basePath + '/store_updatespu.htm',
        data: JSON.stringify(getSpuInfo()),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 0) {
                showerror('新增商品失败,请重试!');
            } else {
                window.location.href = "store_toqueryspus.htm";
            }
        }
    })
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


// 上传商品图片
function uploadPic() {
    $.ajax({
        url: 'store_uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#updatespu')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        var html = '<div class="alert fade in select-pic"> <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i> </button> <img src="' + res + '" width="70" height="70" alt=""/></div>';
        $("#spupics").append(html);
    }).fail(function (res) {
    });
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

        for (var i = 0; i < updateSpu.skus.length; i++) {
            updateSpu.skus[i].price = price;
            updateSpu.skus[i].stock = stock;
            updateSpu.skus[i].weight = weight;
        }

    }
});

// 获得商品信息
function getSpuInfo() {
    var spu = {};
    spu.id = updateSpu.id;
    spu.spuImportId = $("#spuImportId").val();
    spu.name = $("#spuName").val();
    spu.subTitle = $("#spuSubTitle").val();
    spu.price = $("#spuPrice").val();
    spu.pcDesc = $('.summernotedesc').code();
    spu.mobileDesc = $(".summernotemobile").code();
    spu.seoTitle = $("#seoTitle").val();
    spu.seoKeywords = $("#seoKeywords").val();
    spu.seoDesc = $("#seoDesc").val();
    spu.brandId = $('#brands option:selected').val();
    spu.skus = updateSpu.skus;
    spu.spuImages = getSpuPics();
    spu.spuSpecValues = updateSpu.spuSpecValues;
    spu.spuAttributeValues = new Array();
    spu.spuServiceSupports = new Array();

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

    return spu;
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

// 新增单品
function addupdatesku() {
    var specspecvalue = '';
    $('#spuspecspecvalues').find('select').each(function () {
        specspecvalue += ',' + $(this).find('option:selected').attr("specid") + ',' + $(this).find('option:selected').val();
    })

    // 规格值的组合是否存在
    if (isSpecSpecValueExist(specspecvalue)) {
        showerror('选中的规格值组合已经存在!');
        return;
    }

    var uniqueId = new Date().format("yyyyMMddhhmmss") + 0;
    // 单品信息
    var sku = {};
    sku.skuNo = uniqueId;
    sku.unique = uniqueId;
    sku.stock = '';
    sku.price = $("#spuPrice").val();
    sku.weight = '';
    sku.shelvesStatus = '1';
    sku.status='2';
    sku.delFlag = '0';
    sku.skuSpecValues = new Array();
    sku.skuImages = getSpuPics();
    var skuHtml = '<tr tid = ' + uniqueId + '>';

    var k = 0;
    var specAllValue = '';
    $('#spuspecspecvalues').find('select').each(function () {
        skuHtml += '<td><a class="sku_a" href="javascript:;">' + $(this).find('option:selected').html() + '</a></td>';
        var skuSpecValue = {};
        skuSpecValue.specId = $(this).find('option:selected').attr("specid");
        skuSpecValue.specValueId = $(this).find('option:selected').val();
        skuSpecValue.valueRemark = $(this).find('option:selected').html();
        sku.skuSpecValues.push(skuSpecValue);
        if (k == 0) {
            specAllValue += $(this).find('option:selected').html();
        } else {
            specAllValue += "/" + $(this).find('option:selected').html();
        }
        k = k + 1;

        // 商品规格值是否存在 果然不存在 则加入
        if (!isSpuSpecExist($(this).find('option:selected').attr("specid") + ',' + $(this).find('option:selected').val())) {
            var spuSpecValue = {};
            spuSpecValue.specId = $(this).find('option:selected').attr("specid");
            spuSpecValue.specValueId = $(this).find('option:selected').val();
            spuSpecValue.valueRemark = $(this).find('option:selected').html();
            updateSpu.spuSpecValues.push(spuSpecValue);
        }
    })

    var shelvesStatusHtml = "";

    if (sku.shelvesStatus == "0") {
        shelvesStatusHtml = '<span class="label label-default label-mini">下架</span>';
    } else {
        shelvesStatusHtml = '<span class="label label-success label-mini">上架</span>';
    }

    var auditStatusHtml  = "";

    if (sku.status == "0") {
        auditStatusHtml = '<span class="label label-success label-mini">通过</span>';
    } else if (sku.status == "1") {
        auditStatusHtml = '<span class="label label-danger label-mini tooltips" data-placement="right" data-original-title="'+sku.reason+'">拒绝</span>';
    } else {
        auditStatusHtml = '<span class="label label-warning label-mini">待审核</span>';
    }


    skuHtml += '<td><input unique="' + uniqueId + '" onchange="weightChange(this)" type="text" value="" class="form-control sku_weight"></td>' +
        '<td> <input unique="' + uniqueId + '" onchange="priceChange(this)" type="text" value="' + sku.price + '" class="form-control fll sku_price"></td>' +
        '<td><input onchange="stockChange(this)" unique="' + uniqueId + '" type="text" value="" class="form-control sku_stock"></td>' +
        '<td>' + auditStatusHtml + '</td>' +
        '<td>' + shelvesStatusHtml + '</td>' +
        '<td> <button type="button" onclick="toEditSku(' + uniqueId + ')" class="btn btn-primary btn-xs edit_btn"><i class="icon-pencil"></i> 编辑</button> <button type="button" onclick="toDelete(' + uniqueId + ')" class="btn btn-danger btn-xs del_btn"><i class="icon-trash"></i> 删除</button></td></tr>';

    sku.remark = '(' + specAllValue + ')';
    sku.name = $("#spuName").val() + '(' + specAllValue + ')';
    sku.subTitle = $("#spuSubTitle").val() + '(' + specAllValue + ')';
    updateSpu.skus.push(sku);
    $("#skuBodys").append(skuHtml);
    $('#addSkuDialog').modal('hide');
}


// 将商品名称设置到所有的单品
function nametoallsku() {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        updateSpu.skus[i].name = $("#spuName").val() + updateSpu.skus[i].remark;
    }
    showerror('设置成功');
}

// 将商品副标题设置到所有的单品
function subtitletoallsku() {
    for (var i = 0; i < updateSpu.skus.length; i++) {
        updateSpu.skus[i].subTitle = $("#spuSubTitle").val() + updateSpu.skus[i].remark;
    }
    showerror('设置成功');
}