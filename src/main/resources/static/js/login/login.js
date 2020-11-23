layui.use(['layer', 'form', 'jquery'], function() {
    let $ = layui.jquery,
        layer = layui.layer,
        form = layui.form;
    
    form.on('submit(login)', function () {
        let index = layer.load();
        $.ajax({
            type: 'get',
            url: ctx + 'myLogin',
            data: {
                'userName': $('#loginId').val(),
                'password': $('#password').val()
            },
            //dataType: 'json',
            async: false,
            success: function (result) {
                console.log(result);
               window.location.href = ctx+'toSPage';
                // if (result.STATUS === 'SUCCESS') {
                //     $.cookie("loginId", result.USER.loginId);
                //     setTimeout(function() {
                //         layer.close(index);
                //         layer.msg('登录成功', {
                //             time: 50,
                //             icon: 1
                //         }, function () {
                //             window.location.href = 'index.html';
                //         });
                //     }, 50);
                // }
            }
        });
        return false;
    });
});
