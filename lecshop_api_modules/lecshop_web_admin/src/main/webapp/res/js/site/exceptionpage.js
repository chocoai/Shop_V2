// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryexceptionpage.htm',
        success: function (data) {
            for (var exceptionPage in data) {
                //404
                if (data[exceptionPage].type == 1) {
                    $("#tab1 input[name='title']").val(data[exceptionPage].title);
                    $("#tab1 select[name='isUse']").val(data[exceptionPage].isUse);
                    $("#tab1 #desc1").html(data[exceptionPage].desc);
                }
                //500
                if (data[exceptionPage].type == 2) {
                    $("#tab2 input[name='title']").val(data[exceptionPage].title);
                    $("#tab2 select[name='isUse']").val(data[exceptionPage].isUse);
                    $("#tab2 #desc2").html(data[exceptionPage].desc);
                }
                //400
                if (data[exceptionPage].type == 3) {
                    $("#tab3 input[name='title']").val(data[exceptionPage].title);
                    $("#tab3 select[name='isUse']").val(data[exceptionPage].isUse);
                    $("#tab3 #desc3").html(data[exceptionPage].desc);
                }
            }
            //富文本编辑框
            $('.summernote').summernote({
                height: 300,
                tabsize: 2,
                lang: 'zh-CN',
                onImageUpload: function (files, editor, $editable) {
                    sendFile(files, editor, $editable);
                }
            });
        }
    });
};

// 初始化列表
initData();

//编辑保存异常页面
function editSaveBtn(type) {
    var exceptionPage = {
        title: $("#tab" + type + " input[name='title']").val(),
        type: type,
        isUse: $("#tab" + type + " select[name='isUse']").val(),
        desc: $("#tab" + type + " #desc" + type).code()
    };
    LSFetch({
        url: basePath + '/editexceptionpage.htm',
        data: JSON.stringify(exceptionPage),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                $('#success_dialog').modal('show');
                initData();
                return;
            }
            if (data == -1) {
                showerror("编辑失败");
            }
        }
    });
}