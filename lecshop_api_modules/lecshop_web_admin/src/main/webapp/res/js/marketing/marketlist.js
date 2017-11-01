// 基本路径
var basePath = $("#basePath").val();

var marketType = {
    "1": '直降促销',
    "2": '团购促销',
    "3": '抢购促销',
    "4": '满减促销',
    "5": '满折促销',
    "6": '包邮促销'
}

//tab切换
$('.nav').find('li').click(function(){
    $(this).addClass('active').siblings('li').removeClass();
});

// 获得请求参数
function getDates() {
    var dates = $("#queryform").serializeArray();

    if ($("#searchType").val() != '') {
        dates[1].value = $("#searchType").val();
    }
    return dates;
}

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querymarketings.htm',
                data: dataTableAjaxData(data, getDates()),
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
            {"name": "name", "data": "name"},
            {
                "name": "type", "data": function (row) {
                return marketType[row.type];
            }
            },
            {"name": "desc", "data": "desc"},
            {"name": "startTime", "data": "startTime"},
            {"name": "endTime", "data": "endTime"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ',' + row.type + ')" ><i class="icon-pencil"></i> 修改</button>';
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

function changeType(type) {
    if (type == 0) {
        $("#marketingType").show();
        $("#searchType").val('');
        $("#addbtn").show();
        $("#addGroup").hide();
        $("#addPanic").hide();
        refreshDataTable();
    } else if (type == 2) {
        $("#searchType").val(2);
        $("#marketingType").hide();

        $("#addbtn").hide();
        $("#addGroup").show();
        $("#addPanic").hide();

        refreshDataTable();
    }
    else if (type == 3) {
        $("#searchType").val(3);
        $("#marketingType").hide();

        $("#addbtn").hide();
        $("#addGroup").hide();
        $("#addPanic").show();
        refreshDataTable();
    }
}

// 跳转到修改页面
function toupdate(id, type) {
    if (type == "1") {
        // 直降
        window.location.href = basePath + "toupdatefallmarketing.htm?id=" + id;
    } else if (type == "2") {
        // 团购
        window.location.href = basePath + "toupdategroupmarketing.htm?id=" + id;
    } else if (type == "3") {
        // 抢购
        window.location.href = basePath + "toupdatepanicmarketing.htm?id=" + id;
    } else if (type == "4") {
        window.location.href = basePath + "toupdatefulldown.htm?id=" + id;
    }else if (type == "5") {
        window.location.href = basePath + "toupdatefulldiscount.htm?id=" + id;
    }
}
