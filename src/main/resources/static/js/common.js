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