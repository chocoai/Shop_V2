// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryallstoretobeauditdspus.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            })
        },
        "columns": [
            {
                "name": "logo", "data": function (row) {
                return '<img src="' + row.url + '" height="36">';
            }
            },
            {"name": "name", "data": "name"},
            {"name": "price", "data": "price"},
            {"name": "thirdCateId", "data": "thirdCategory.name"},
            {"name": "brandId", "data": "brand.name"},
            {"name": "storeName", "data": "storeName"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-success btn-xs" onclick="toquerydetail(' + row.id + ')" ><i class="icon-eye-open"></i> 查询详情</button> </div>';
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

loadFirstCate();

// 加载一级分类
function loadFirstCate() {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=0',
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#firstCateId").html(html);
        }
    })
}

// 加载二级分类
function chooseFCate(obj) {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + $(obj).val(),
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#secondCateId").html(html);
        }
    })
}
// 加载三级分类
function chooseTCate(obj) {
    LSFetch({
        url: basePath + 'querycategorybyparentid.htm?parentId=' + $(obj).val(),
        success: function (data) {
            var html = '<option  value="0">选择分类</option>';
            for (var i = 0; i < data.length; i++) {
                var cate = data[i];

                html += '<option  value="' + cate.id + '"';
                html += '>' + cate.name + '</option>';

            }
            $("#thirdCateId").html(html);
        }
    })
}


// 查看详情
function toquerydetail(id) {
    window.location.href = "tospudetail.htm?id=" + id;
}

