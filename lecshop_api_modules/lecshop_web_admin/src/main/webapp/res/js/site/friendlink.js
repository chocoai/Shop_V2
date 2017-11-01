// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var linkacount = 0;

// 修改时候防止重复提交
var linkucount = 0;

function clearCount() {
    linkacount = 0;
    linkucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryfriendlinks.htm',
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
                "name": "url", "data": "url"
            },

            {
                "name": "imageUrl", "data": function (row) {
                return '<img src="' + row.imageUrl + '" height="36">';
            }
            },
            {
                "name": "sort", "data": "sort"
            },
            {
                "name": "isShow", "data": "isShow"
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 修改</button>';
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

// 新增友情链接
function saveFriendLink() {
    if (linkacount == 1) {
        return;
    }
    if (!$("#friendlinkoperator").valid()) {
        return;
    }
    var friendLink = {
        name: $("#friendLinkName").val(),
        url: $("#friendLinkUrl").val(),
        sort: $("#friendLinkSort").val()
    };
    linkacount = 1;
    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#friendlinkoperator')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        if (res != '') {
            friendLink.imageUrl = res;
        } else {
            friendLink.imageUrl = $("#myimg").attr("src");
        }
        LSFetch({
            url: basePath + '/addfriendlink.htm',
            data: JSON.stringify(friendLink),
            contentType: "application/json;charset=utf-8",
            success: function () {
                $('#add_dialog').modal('hide');
                refreshDataTable();
            }
        })
        $("#friendlinkoperator")[0].reset();
        $("#myimg").removeAttr("src");
    }).fail(function (res) {});
}

// 跳转到删除友情链接页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除友情链接
function deleteFriendLink() {
    LSFetch({
        url: basePath + '/deletefriendlink.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 弹出修改友情链接页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/queryfriendlinkbyid.htm?id=' + id,
        success: function (res) {
            $("#updateFriendLinkId").val(res.id);
            $("#updateFriendLinkName").val(res.name);
            $("#updateFriendUrl").val(res.url);
            $("#updateFriendLinkSort").val(res.sort);
            if (res.imageUrl != '' && res.imageUrl != null) {
                $("#myimg").attr("src", res.imageUrl);
                $("#myimg").attr("alt", res.name);
            }
            $('#update_dialog').modal('show');
        }
    })
}

// 修改友情链接信息
function updateFriendLink() {

    if (linkucount == 1) {
        return;
    }

    if (!$("#updateFriendLink").valid()) {
        return;
    }

    var friendlink = {
        id: $("#updateFriendLinkId").val(),
        name: $("#updateFriendLinkName").val(),
        url: $("#updateFriendUrl").val(),
        sort: $("#updateFriendLinkSort").val()
        // imageUrl:'null'
    };

    linkucount = 1;

    $.ajax({
        url: 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#updateFriendLink')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        if (res != '') {
            friendlink.imageUrl = res;
            $("#myimg").attr("alt", friendlink.name);
        } else {
            friendlink.imageUrl = $("#myimg").attr("src");
            $("#myimg").attr("alt", friendlink.name);
        }

        LSFetch({
            url: basePath + '/updatefriendlink.htm',
            data: JSON.stringify(friendlink),
            contentType: "application/json;charset=utf-8",
            success: function () {
                $('#update_dialog').modal('hide');
                refreshDataTable();
            }
        })
    }).fail(function (res) {
    });
}

// 准备删除所有已选中的部友情链接
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的品牌?", ids);
}

// 删除所有友情链接
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletefriendlinks.htm',
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