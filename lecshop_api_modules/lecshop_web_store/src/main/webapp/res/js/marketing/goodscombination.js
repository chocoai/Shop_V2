// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var goodComacount = 0;

// 修改时候防止重复提交
var goodComucount = 0;

function clearCount() {
    goodComacount = 0;
    goodComucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/store_querygoodscombination.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                    return '<input type="checkbox" value="' + row.id + '" name="id">';
                }
            },

            {
                "name": "name", "data": "name"
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-success btn-xs view_btn" type="button" onclick="toEditGoods(' + row.id + ')"><i class="icon-eye-open"></i> 管理货品</button>';
                    operator += '<button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" >' +
                        '<i class="icon-pencil"></i> 编辑 </button>';
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
};

// 添加商品组合
function addGoodsCombination() {
    if (!$("#addForm").valid()) {
        return;
    }
    var goodsCombination = {
        name: $("#goodsCombination").val()
    }
    LSFetch({
        url: basePath + '/store_addgoodscombination.htm',
        data: JSON.stringify(goodsCombination),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $("#add_dialog").modal("hide");
            refreshDataTable();
        }
    });
}

// 跳转到删除友情链接页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除商品组合
function deleteGoodsCombination() {
    LSFetch({
        url: basePath + '/store_deletegoodscombination.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备删除所有已选中的商品组合
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的商品组合?", ids);
}

// 删除所有商品组合
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/store_batchdeletegoodscombination.htm',
        data: {
            ids: ids
        },
        success: function (data) {
            if(data >= 1){
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}

// 弹出修改商品组合页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/store_querygoodscombinationbyid.htm?id=' + id,
        success: function (res) {
            $("#updateGoodsCombinationId").val(res.id);
            $("#updateGoodsCombinationName").val(res.name);
            $('#update_dialog').modal('show');
        }
    })
}

// 修改商品组合
function update() {
    if (goodComucount == 1) {
        return;
    }
    if(!$("#updateGoodsCombination").valid()) {
        return;
    }
    goodComucount = 1;
    var goodsCombination = {
        id: $("#updateGoodsCombinationId").val(),
        name: $("#updateGoodsCombinationName").val()
    };
    LSFetch({
        url: basePath + '/store_updategoodscombination.htm',
        data: JSON.stringify(goodsCombination),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#update_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 跳转到管理货品页面
function toEditGoods(id) {
    window.location.href = basePath + "store_tomanagegoodscombination.htm" + window.location.search + "&id=" + id;
}