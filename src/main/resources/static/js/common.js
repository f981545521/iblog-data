/**
 * ajax post提交
 * @param ajaxdata 提交数据
 * @param ajaxurl 提交路径
 * @param successcallback 成功回调
 * @param errorcallback 失败回调
 */
function ajaxPost(ajaxdata, ajaxurl, successcallback, errorcallback) {
    $.ajax({
        cache: true,
        type: "post",
        dataType: "json",
        url: ajaxurl,
        data: ajaxdata,
        async: true,
        success: function (data) {
            if ($.isFunction(successcallback)) {
                successcallback.call(this, data);
            }
        },
        error: function (data) {
            if ($.isFunction(errorcallback)) {
                errorcallback.call(this, data);
            }
        }
    });
}

/**
 * processData设置为false。因为data值是FormData对象，不需要对数据做处理。
 * <form>标签添加enctype="multipart/form-data"属性。
 * cache设置为false，上传文件不需要缓存。
 * contentType设置为false。因为是由<form>表单构造的FormData对象，且已经声明了属性enctype="multipart/form-data"，所以这里设置为false。
 *
 * ajax Formpost提交
 */
function ajaxFormPost(ajaxdata, ajaxurl, successcallback, errorcallback) {
    $.ajax({
        type: "post",
        url: ajaxurl,
        data: ajaxdata,
        async: true,
        dataType: "json",
        contentType : false,
        processData: false,
        success: function (data) {
            if ($.isFunction(successcallback)) {
                successcallback.call(this, data);
            }
        },
        error: function (data) {
            if ($.isFunction(errorcallback)) {
                errorcallback.call(this, data);
            }
        }
    });
}