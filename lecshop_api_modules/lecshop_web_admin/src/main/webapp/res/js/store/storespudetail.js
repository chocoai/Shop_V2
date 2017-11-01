var basePath = $("#basePath").val();

var editSkuHtml = $("#edit_dialog").html();


// 查询商品信息
queryspu();

// 更新的商品实体
var updateSpu = {};

// 查询商品信息
function queryspu() {
    LSFetch({
        url: basePath + '/queryspubyid.htm?id=' + $("#spuId").val(),
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
        url: basePath + '/queryallservicesupport.htm',
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {

                var checkFlag = '';
                for (var j = 0; j < updateSpu.spuServiceSupports.length; j++) {
                    if (updateSpu.spuServiceSupports[j].serviceSupportId == data[i].id) {
                        checkFlag = 'checked';
                    }
                }
                html += '<label class="checkbox-inline"> <input disabled '+checkFlag+' type="checkbox" name="servicesupport" value="' + data[i].id + '">' + data[i].name + ' </label>';
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
        url: basePath + '/queryallbrands.htm',
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
        html += '<div class="alert fade in select-pic"> <img src="' + updateSpu.spuImages[i].url + '" width="70" height="70" alt=""/></div>';
    }
    $("#spupics").append(html);
}


// 加载类型
function loadType() {
    $("#attr tbody").html('');
    LSFetch({
        url: basePath + '/querytypedetail.htm?id=' + updateSpu.typeId,
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
            attributeHtml = attributeHtml + "<td style='vertical-align:middle'><span class='text-danger'>*</span>" + attributes[i].name + "</td><td><select disabled attributename='" + attributes[i].name + "' class='form-control'  id='" + attributes[i].id + "'>";
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

    html += '<th width="85"><span class="label_red">*</span>重量(克)</th><th width="85"><span class="label_red">*</span>价格(元)</th><th width="80"><span class="label_red">*</span>库存</th><th width="80">状态</th><th width="80">审核</th><th>操作</th>';

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

        var auditStatusHtml = "";

        if (sku.status == "0") {
            auditStatusHtml = '<span class="label label-success label-mini">通过</span>';
        } else if (sku.status == "1") {
            auditStatusHtml = '<span class="label label-danger label-mini tooltips" data-placement="right" data-original-title="' + sku.reason + '">拒绝</span>';
        } else {
            auditStatusHtml = '<span class="label label-warning label-mini">待审核</span>';
        }


        skuHtml += '<td><input disabled unique="' + sku.unique + '" onchange="weightChange(this)" type="text" value="' + sku.weight + '" class="form-control sku_weight"></td>' +
            '<td> <input disabled unique="' + sku.unique + '" onchange="priceChange(this)" type="text" value="' + sku.price + '" class="form-control fll sku_price"> </td>' +
            '<td><input disabled onchange="stockChange(this)" unique="' + sku.unique + '" type="text" value="' + sku.stock + '" class="form-control sku_stock"></td>' +
            '<td>' + shelvesStatusHtml + '</td>' +
            '<td>' + auditStatusHtml + '</td>' +
            '<td><button type="button" onclick="toEditSku(' + sku.unique + ')" class="btn btn-primary btn-xs edit_btn"><i class="icon-pencil"></i> 查看详细</button> </td></tr>';
        skuHtmls += skuHtml;
    }
    $("#skuBodys").html(skuHtmls);
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
        skuImagesHmtl += '<div class="alert fade in select-pic"> <img src="' + sku.skuImages[i].url + '" width="70" height="70" alt=""/></div>';
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
