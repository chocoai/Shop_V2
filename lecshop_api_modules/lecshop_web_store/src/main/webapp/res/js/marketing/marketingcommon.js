// 基本路径
var basePath = $("#basePath").val();


$(".search_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:00',
    weekStart: 1,
    autoclose: true,
    language: 'zh',
    todayBtn: true,
    viewSelect: 'hour'
});

// 选中的单品
var selectedSkus = new Array();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_queryskus.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input onclick="addsku(this);" type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {
                "name": "url", "data": function (row) {
                return '<img src="' + row.url + '" class="skuimg" width="30" height="30">';
            }
            },
            {"name": "skuNo", "data": "skuNo"},
            {"name": "name", "data": "name"},
            {
                "name": "specs", "data": function (row) {
                return getSpecValues(row.skuSpecValues);
            }
            },
            {"name": "price", "data": "price"},

        ],
        ordering: false,
        "iDisplayLength": 5,
        bStateSave: false,
        bLengthChange: false
    })

};

initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
}

// 选择商品
function choosespus() {
    loadSkus();
    $('#spu_modal').modal('hide');
}

// 新增单品
function addsku(obj) {
    if ($(obj).prop("checked") == true) {
        var sku = {};
        sku.id = $(obj).val();
        sku.url = $(obj).parents("tr").find("td").eq(1).find("img").attr("src");
        sku.skuNo = $(obj).parents("tr").find("td").eq(2).text();
        sku.name = $(obj).parents("tr").find("td").eq(3).text();
        sku.specs = $(obj).parents("tr").find("td").eq(4).text();
        sku.price = $(obj).parents("tr").find("td").eq(5).text();
        selectedSkus.push(sku);
    } else {
        selectedSkus.removeChooseSku($(obj).val());
    }
}

// 找出sku在数组中的索引
Array.prototype.indexOfChooseSku = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i].id == val) return i;
    }
    return -1;
};


Array.prototype.removeChooseSku = function (val) {
    var index = this.indexOfChooseSku(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
// 加载单品信息
function loadSkus() {
    var html = '';

    for (var i = 0; i < selectedSkus.length; i++) {
        var sku = selectedSkus[i];
        html += '<tr id="' + sku.id + '">';
        html += '<td><img src="' + sku.url + '" width="30" height="30"></td>';
        html += '<td>' + sku.skuNo + '</td>';
        html += '<td>' + sku.name + '</td>';
        html += '<td>' + sku.specs + '</td>';
        html += '<td>' + sku.price + '</td>';
        html += '<td class="operation_box"><button type="button" class="btn btn-danger btn-xs del_goods_btn"><i class="icon-trash"></i> 删除</button></td>';
        html += '</tr>';
    }

    $("#choosesku").html(html);
}

// 获得规格值信息
function getSpecValues(skuSpecValues) {
    var content = '';

    for (var i = 0; i < skuSpecValues.length; i++) {
        content += skuSpecValues[i].spec.name + ":" + skuSpecValues[i].valueRemark + "<br>";
    }
    return content;
}
// 全选
$('th input[type="checkbox"]').change(function () {
    if ($(this).prop("checked") == true) {
        $('#dataTable tbody').find('tr').each(function () {
            var obj = $(this).find('td').eq(0).find('input[type="checkbox"]');
            addsku(obj)
        })
    } else {
        $(this).parents('table').find('input[type="checkbox"]').prop("checked", false);
        $('#dataTable tbody').find('tr').each(function () {
            var obj = $(this).find('td').eq(0).find('input[type="checkbox"]');
            selectedSkus.removeChooseSku($(obj).val());
        })
    }
});

// 翻页重新暄软
$('#dataTable').on('draw.dt', function () {

    for (var i = 0; i < selectedSkus.length; i++) {
        var sku = selectedSkus[i];
        $('#dataTable tbody').find('tr').each(function () {
            var obj = $(this).find('td').eq(0).find('input[type="checkbox"]');
            if (obj.val() == sku.id) {
                $(obj).prop("checked", true)
            }
        })
    }

});

// 删除单品
$('body').on('click', '.del_goods_btn', function () {
    selectedSkus.removeChooseSku($(this).parents('tr').attr("id"));
    $(this).parents('tr').remove();
});

// 弹出商品搜索框
function tosearch() {
    $("#queryform")[0].reset();
    refreshDataTable();
    for (var i = 0; i < selectedSkus.length; i++) {
        var sku = selectedSkus[i];
        $('#dataTable tbody').find('tr').each(function () {
            var obj = $(this).find('td').eq(0).find('input[type="checkbox"]');
            if (obj.val() == sku.id) {
                $(obj).prop("checked", true)
            }
        })
    }
    // 初始化列表
    $('#spu_modal').modal('show');
}
