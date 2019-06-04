$(function () {

    //  日期插件初始化
    $('#last_day').datepicker({
        // startDate: new Date(_data),
        fontAwesome: 'font-awesome',//指定
        language: 'zh',//语言
        format: 'yyyy-mm-dd',//日期格式化
        autoclose: true,//选择后自动关闭
        minView: "month",//设置只显示到月份
        todayBtn: 'linked',//显示‘今日’按钮
        // clearBtn:true,//清除按钮
    });

    //  表单提交，触发nice validator验证
    $("#res_btn").click(
        function () {
            $("#resignation_form").trigger("validate");
        }
    )



    //  表单验证nice validator配置初始化
    $("#resignation_form").validator({
        valid: function (form) {

            var _table = {
                last_day: $("#last_day").val(),
                res_reason: $('#res_reason').val()
            };
            $._confirm('是否确认提交申请', function () {
                    $.ajax({
                        type: "POST",
                        url: "/apply/resignation",
                        data: $._encrypt('resignation', _table, _key),
                        // data: {type: 'resignation', formData: JSON.stringify(_table)},
                        contentType: "application/json",
                        success: function (data) {
                            $._confirm2(data.data);
                        }
                    })
                }, function () {
                }
            );
        }
    });

});