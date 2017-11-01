// 基本路径
var basePath = $("#basePath").val();
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querycommnts.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
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
            {"name": "skuName", "data": "sku.name"},
            {"name": "customerName", "data": "customerName"},
            {
                "name": "isAnonymous", "data": function (row) {
                if (row.isAnonymous == '0') {
                    return '否';
                } else {
                    return '是';
                }
            }
            },
            {"name": "comment", "data": "comment"},
            {"name": "createTime", "data": "createTime"},
            {
                "data": function (row) {
                    var operator = '';
                    operator += '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toview(' + row.id + ')" ><i class="icon-pencil"></i> 查看</button>';
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

// 查看评论详情
function toview(id) {
    LSFetch({
        url: basePath + '/querycommentbyid.htm?id=' + id,
        success: function (comment) {
            $("#skuName").html(comment.sku.name);
            $("#customerName").html(comment.customerName);
            $("#ptime").html(comment.createTime);
            $("#myimg").attr("src",comment.sku.url);
            $("#pcontent").html(comment.comment);
            var deschtml = '';
            if (comment.descScore == 1) {
                deschtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                deschtml += '<li class="star2 " style="width:15px;"><a href="javascript:;">2</a></li>';
                deschtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                deschtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                deschtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.descScore == 2) {
                deschtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                deschtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                deschtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                deschtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                deschtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.descScore == 3) {
                deschtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                deschtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                deschtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                deschtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                deschtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.descScore == 4) {
                deschtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                deschtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                deschtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                deschtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                deschtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else {
                deschtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                deschtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                deschtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                deschtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                deschtml += '<li class="star5 on" style="width:15px;"><a href="javascript:;">5</a></li>';
            }

            $("#descstat").html(deschtml);


            var sellerhtml = '';
            if (comment.sellerScore == 1) {
                sellerhtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                sellerhtml += '<li class="star2 " style="width:15px;"><a href="javascript:;">2</a></li>';
                sellerhtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                sellerhtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                sellerhtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.sellerScore == 2) {
                sellerhtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                sellerhtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                sellerhtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                sellerhtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                sellerhtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.sellerScore == 3) {
                sellerhtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                sellerhtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                sellerhtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                sellerhtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                sellerhtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.sellerScore == 4) {
                sellerhtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                sellerhtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                sellerhtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                sellerhtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                sellerhtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else {
                sellerhtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                sellerhtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                sellerhtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                sellerhtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                sellerhtml += '<li class="star5 on" style="width:15px;"><a href="javascript:;">5</a></li>';
            }

            $("#sellerstat").html(sellerhtml);


            var loghtml = '';
            if (comment.logisticsScore == 1) {
                loghtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                loghtml += '<li class="star2 " style="width:15px;"><a href="javascript:;">2</a></li>';
                loghtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                loghtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                loghtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.logisticsScore == 2) {
                loghtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                loghtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                loghtml += '<li class="star3 " style="width:15px;"><a href="javascript:;">3</a></li>';
                loghtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                loghtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.logisticsScore == 3) {
                loghtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                loghtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                loghtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                loghtml += '<li class="star4 " style="width:15px;"><a href="javascript:;">4</a></li>';
                loghtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else if (comment.logisticsScore == 4) {
                loghtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                loghtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                loghtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                loghtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                loghtml += '<li class="star5 " style="width:15px;"><a href="javascript:;">5</a></li>';
            } else {
                loghtml += '<li class="star1 on" style="width:15px;"><a href="javascript:;">1</a></li>';
                loghtml += '<li class="star2 on" style="width:15px;"><a href="javascript:;">2</a></li>';
                loghtml += '<li class="star3 on" style="width:15px;"><a href="javascript:;">3</a></li>';
                loghtml += '<li class="star4 on" style="width:15px;"><a href="javascript:;">4</a></li>';
                loghtml += '<li class="star5 on" style="width:15px;"><a href="javascript:;">5</a></li>';
            }

            $("#logstar").html(loghtml);

            var picHtmls = '';
            for (var i = 0; i < comment.commentPics.length; i++) {
                picHtmls+='<li><img src="'+comment.commentPics[i].url+'" alt=""></li>';
            }

            $("#imgList").html(picHtmls);
            $('#view_dialog').modal('show');
        }
    })
}

// 跳转到删除品牌页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#remove_dialog').modal('show');
}

// 删除品牌
function deleteComment() {
    LSFetch({
        url: basePath + '/deletecomment.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#remove_dialog').modal('hide');
            refreshDataTable();
        }
    })
}


// 准备删除选中的评论
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");

    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }

    showdeleteconfirm("确定要删除选择的评论?", ids);
}

// 删除所有品牌
function deleteAll(ids) {

    LSFetch({
        url: basePath + '/deletecomments.htm?ids=' + ids,
        success: function () {
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    })
}
