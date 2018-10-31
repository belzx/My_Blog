$(function () {
    $('#login_button').click(function () {
        var user = $('#loginusername').val();
        var pwd = $('#loginpassword').val();
        var rememberMe = $('#loginremenber')[0].checked;
        if (user == '' || pwd == '') {
            $('.wrap-login #username').focus();
            alert("用户名或密码不能为空");
            return;
        } else {
            $.ajax({
                url: basePath + "/user/login",
                data: {'username': user, 'password': pwd,'rememberMe':rememberMe},
                type: "POST",
                dataType: 'json',
                success: function (data) {
                    if(data.status == 200){
                        alert("登录成功");
                        window.location.href =basePath+"/admin/index";
                    }else if(data.status == 302) {
                        alert("登录成功");
                        window.location.href =basePath+data.message;
                    }else {
                        alert(data.message);
                    }
                },
                error: function (data) {
                    alert("出错：" + data.message);
                }
            });
        }
    });

    $('#register_button').click(function () {
        var user = $('#registerusername').val();
        var pwd = $('#registerpassword').val();
        var email = $('#registeremail').val();
        var verification = $('#registerverification').val();
        if (user == '' || pwd == '') {
            $('.wrap-register #username').focus();
            alert("用户名或密码不能为空");
            return ;
        } else {
            $.ajax({
                url: basePath + "/user/register",
                data: {
                    'username': user,
                    'password': pwd,
                    'email':email,
                    'verification':verification
                },
                type: "POST",
                dataType: 'json',
                success: function (data) {
                    if(data.status == 200){
                        alert("注册成功");
                        window.location.href =basePath+"/admin/index";
                    }else {
                        alert(data.message);
                    }
                },
                error: function (data) {
                    alert("出错：" + data.message);
                }
            });
        }
    });

    $('.account-login').on('click', function(){

        $('.wrap-login').css({ display: 'block' })
        $('.wrap-register').css({ display: 'none' })
        $(this).css({ color: '#c70000' })
        $('.account-register').css({ color: 'black' })
    })

    $('.account-register').on('click', function(){
        $('.wrap-login').css({ display: 'none' })
        $('.wrap-register').css({ display: 'block' })
        $(this).css({ color: '#c70000' })
        $('.account-login').css({ color: 'black' })
    })
})

//
// (function () {
//     $('.wrap-header h3')[0].on('click', function () {
//         $('.login-form').css({ display: 'block' })
//         $('.register-form').css({ display: 'none' })
//     })
//
//     $('.wrap-header h3')[1].on('click', function () {
//         $('.login-form').css({ display: 'none' })
//         $('.register-form').css({ display: 'block' })
//     })
// })()

// (function(){
//
//
// })()