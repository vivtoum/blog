$._confirm = function (content, a, b) {
    $.confirm({
        title: false,
        content: content,
        type: 'red',
        typeAnimated: true,
        buttons: {
            yes: {
                text: '确认',
                keys: ['enter', 'y', 'space'],
                btnClass: 'btn-success',
                action: a
            },
            no: {
                text: '取消',
                keys: ['esc', 'n'],
                btnClass: 'btn-danger',
                action: b
            }
        }
    });
};

$._confirm2 = function (content, a) {
    // var _a = a ? a : {};
    $.confirm({
        title: false,
        content: content,
        type: 'green',
        typeAnimated: true,
        buttons: {
            yes: {
                text: 'OK', // With spaces and symbols
                //   isHidden: true, // hide the button
                keys: ['enter', 'esc', 'space'],
                action: a
            }
        }
    });
};

$._encrypt = function (type, tableObjJson, key) {
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(key);
    return "{\"requestData\":" + encrypt.encryptLong(JSON.stringify({
        type: type,
        formData: '\"' + JSON.stringify(tableObjJson) + '\"'
    })) + "}"
};
