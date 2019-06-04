$(function () {

//  验证器全局配置
    $.validator.config({
        theme: "simple_bottom",
        validClass: "is-valid",
        invalidClass: "is-invalid",
        bindClassTo: ".form-control",
        messages: {
            required: "此处必填",
            email: "Please enter a valid email address.",
            notover30days: "选择最后工作日期小于系统自动选择时间，此项必填"
        },
        rules: {
            mobile: [/^1[3-9]\d{9}$/, "请填写有效的手机号"],
            chinese: [/^[\u0391-\uFFE5]+$/, "请填写中文字符"],
            notover30days: function () {
                return new Date($("#last_day").val()) < new Date(_data);
            },
        },
        fields: {
            last_day: 'required',
            res_remark: 'required(notover30days)',
            res_reason: 'required',
            email: "required;email;remote(/path/to/server)"
        },
        msgMaker: function (opt) {
            return $._popover(opt);
        },
    });

    _key =
        'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwRDfIh3C/Xg+/mhDiVVZF81ufJEz3/' +
        'N0VwdhWku38ml22EMrCIpwy9so3ug1Yrrc5OkqL38F/GlR+jhHf0at8MzyEaAw0sAiVTiFnP5R4/' +
        'wVnOr40H3To7VVA5Gvqjdn0gM5/NfB+phCsWXjOuTtcqTusRgXqLG98p6ZJtF2qm70/1tJI7rNQKP' +
        'V0l3q2wlFNvSPfiWgZdoq75c75Cn2jRWG75aduvVbARHQ+VWDQ3YQ5gct2V1qgVNGh4zZ69hcpkpITwZXPE' +
        'KNsCp0+Qdnwmab8HyUYz2o2XzhsLRAdxDAeQ+I+MQddNcIzIR1QbjNMAbJ3WpxORSXaK4DLLPR8QIDAQAB';
});
