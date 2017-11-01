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

// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/querystoredetailinfo.htm',
        data: {
            storeId: $("#storeId").val()
        },
        success: function (data) {
            var storeInfo = data.storeInfo;
            var twoCategoryList = data.twoCategoryList;
            var threeCategoryList = data.threeCategoryList;
            var billingCycle = storeInfo.billingCycle;
            var billingCycleIds;
            if (billingCycle != null && billingCycle != '') {
                billingCycleIds = billingCycle.split(',');
                for (var x = 0; x < billingCycleIds.length; x++) {
                    $("#billingCycle option[value=" + billingCycleIds[x] + "]").attr("selected", true);
                }
            }
            $(".select2").select2();
            $("input[name='effectiveTime']").val(storeInfo.effectiveTime);
            $("select[name='status']").val(storeInfo.status);
            var categoryHtml = '';
            for (var i = 0; i < threeCategoryList.length; i++) {
                var threeCategory = threeCategoryList[i];
                for (var j = 0; j < twoCategoryList.length; j++) {
                    var twoCategory = twoCategoryList[j];
                    if (twoCategory.id == threeCategory.parentId) {
                        categoryHtml += '<tr><input type="hidden" value="' + threeCategory.id + '"> <td>' + twoCategory.name + '</td><td>' + threeCategory.name + '</td> <td class="operation_box">' +
                            '<button class="btn btn-danger btn-xs del_btn" onclick="deleteCategoryBtn(' + threeCategory.id + ')"><i class="icon-trash"></i> 删除</button></td></tr>';
                    }
                }
            }
            $("#categoryTbody").html(categoryHtml);
            $('.dataTable').dataTable({
                "bSort": false,
                "iDisplayLength": 10
            });
            $('.dataTables_length,.dataTables_filter').remove();
        }
    });
};
// 初始化列表
initDataTable();

//添加分类点击事件
function addCategoryBtn() {
    LSFetch({
        url: basePath + '/queryoneandtwocategory.htm',
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
    $('#add_dialog').modal('show');
}
//三级分类显示
function showThreeCategory() {
    LSFetch({
        url: basePath + '/querythreecategory.htm',
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

//全选点击事件
$('#all_btn').click(function () {
    $(this).parent().find('input').prop('checked', true);
});

//添加保存事件
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
    var threeCategoryIds = new Array();
    $("#thirdCategoryDiv input[name='threeCheckbox']").each(function () {
        if ($(this).prop("checked")) {
            threeCategoryIds.push($(this).val());
        }
    });
    LSFetch({
        url: basePath + '/addstoresignedcategory.htm',
        data: {
            categoryIds: threeCategoryIds.toString(),
            storeId: $("#storeId").val()
        },
        success: function (data) {
            if (data >= 1) {
                $('#add_dialog').modal('hide');
                showerror("添加成功");
                $('.dataTable').dataTable().fnDestroy();
                initDataTable();
                return;
            }
            if (data == -1) {
                showerror("没有选择分类");
                return;
            }
            if (data == -2) {
                $('#add_dialog').modal('hide');
                showerror("选择的分类已添加,请勿重复添加");
                return;
            }
            showerror("网络错误");
        }
    });
}

//删除按钮点击事件
function deleteCategoryBtn(id) {
    $("input[name='deleteId']").val(id);
    $('#del_dialog').modal('show');
}

//删除分类保存事件
function deleteCategorySaveBtn() {
    LSFetch({
        url: basePath + '/deletesingedcategory.htm',
        data: {
            storeId: $("#storeId").val(),
            cateId: $("input[name='deleteId']").val()
        },
        success: function (data) {
            if (data == 1) {
                $('#del_dialog').modal('hide');
                showerror("删除成功");
                $('.dataTable').dataTable().fnDestroy();
                initDataTable();
                return;
            }
            showerror("网络错误");
        }
    });
}

function allInfoSaveBtn() {
    var billingCycleIds = $("#billingCycle").val();
    if (null == billingCycleIds || billingCycleIds.length == 0) {
        showerror('结算周期不能为空');
        return;
    }
    if (!dataValid("effectiveTime")) {
        return;
    }
    var storeInfo = {
        id: $("#storeId").val(),
        billingCycle: $("#billingCycle").val().toString(),
        effectiveTime: $("input[name='effectiveTime']").val(),
        status: $("select[name='status']").val()
    };
    LSFetch({
        url: basePath + '/editstoretimeandisclose.htm',
        data: JSON.stringify(storeInfo),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1) {
                showerror("结算周期不能为空");
                return;
            }
            if (data == 1) {
                showerror("修改成功");
                if (storeInfo.status == 4) {
                    setTimeout(function () {
                        window.location.href = document.referrer;
                    }, 3000);
                }
                return;
            }
            showerror("网络错误");
        }
    });
}
