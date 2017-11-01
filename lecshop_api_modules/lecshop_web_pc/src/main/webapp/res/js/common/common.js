// ajax 请求
function LSFetch(params) {

    // 默认参数
    var _params = {
        type: 'post',
        dataType: 'json',
        timeout: 60000,
        error: function () {
            showTip(1, '网络错误');
        }
    };
    $.extend(true, params, _params);
    $.ajax(params);
}

function showTip(result, tips, id) {
    var tipHtml = '';
    tipHtml += '<div class="masklayer"></div>';
    tipHtml += '<div id="tips_dialog" class="thickbox tip_dialog">' +
        '<div class="thicktitle"><span>提示</span></div>' +
        '<div class="ui_dialog_content">' +
        '<div class="tip_box">';
    if (result == 0) {
        tipHtml += '<span class="succ_icon m_icon"></span>';
    }
    if (result == 1 || result == 2) {
        tipHtml += '<span class="warn_icon m_icon"></span>';
    }
    tipHtml += '<div class="item_fore" style="padding-top:15px">';
    if (result == 0) {
        tipHtml += '<h3 style="color:#71b427">' + tips + '</h3>'
    }
    if (result == 1 || result == 2) {
        tipHtml += '<h3 class="ftx04">' + tips + '</h3>'
    }
    tipHtml += '</div><div class="op_btns">';
    if (result == 0 || result == 1) {
        tipHtml += '<a href="#" class="btn_h btn_cancel">关闭</a>';
    } else {
        tipHtml += '<a href="javascript:;" class="btn-9" onclick="deleteBtn(' + id + ')">确定</a>';
        tipHtml += '<a href="#" class="btn_h btn_cancel">取消</a></div>';
    }
    tipHtml += '</div>' +
        '</div>' +
        '<a href="javascript:;" class="thickclose">×</a>' +
        '</div>';
    $(document.body).append(tipHtml);
    $(".masklayer").show();
    $("#tips_dialog").show();
    $('.thickclose, .btn_cancel').click(function () {
        $(this).parents('.thickbox').hide();
        $('.masklayer').hide();
    });
}

