$(function () {

    //  日期插件初始化
    $('#last_day').datepicker({
        startDate: new Date(_data),
        fontAwesome: 'font-awesome',//指定
        language: 'zh',//语言
        format: 'yyyy-mm-dd',//日期格式化
        autoclose: 1,//选择后自动关闭
        minView: "month",//设置只显示到月份
        todayBtn: 1,//显示‘今日’按钮
        // clearBtn:true,//清除按钮
    });// 2018,7,1 号能点击，要传入比开始的日期多一天


    //  表单提交，触发nice validator验证
    $("#res_btn").click(
        function () {
            $("#resignation_form").trigger("validate");
        }
    )

    //  表单验证nice validator配置初始化
    $("#resignation_form").validator({
        // theme: "",
        messages: {
            required: "此处必填",
            // email: "Please enter a valid email address.",
        },
        rules: {
            mobile: [/^1[3-9]\d{9}$/, "请填写有效的手机号"],
            chinese: [/^[\u0391-\uFFE5]+$/, "请填写中文字符"]
        },
        fields: {
            res_reason: 'required',
            // email: "required;email;remote(/path/to/server)"
        },
        valid: function (form) {
            debugger;
            var key =
                'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRDfIh3C/Xg+/mhDiVVZF81ufJEz3/' +
                'N0VwdhWku38ml22EMrCIpwy9so3ug1Yrrc5OkqL38F/GlR+jhHf0at8MzyEaAw0sAiVTiFnP5R4/' +
                'wVnOr40H3To7VVA5Gvqjdn0gM5/NfB+phCsWXjOuTtcqTusRgXqLG98p6ZJtF2qm70/1tJI7rNQKP' +
                'V0l3q2wlFNvSPfiWgZdoq75c75Cn2jRWG75aduvVbARHQ+VWDQ3YQ5gct2V1qgVNGh4zZ69hcpkpITwZXPE' +
                'KNsCp0+Qdnwmab8HyUYz2o2XzhsLRAdxDAeQ+I+MQddNcIzIR1QbjNMAbJ3WpxORSXaK4DLLPR8QIDAQAB';

            var _table = {
                last_day: $("#last_day").val(),
                res_reason: $('#res_reason').val()
            };
            $._confirm('是否确认提交申请', function () {
                    $.ajax({
                        type: "POST",
                        url: "/apply/resignation",
                        data: $._encrypt('resignation', _table, key),
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