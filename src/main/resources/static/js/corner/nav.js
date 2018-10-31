var profile = [{'setting();':'设置'}];
$(document).ready(function () {
    var html = '';
    if(isLogin){
        profile.push({'logout();':'退出'});
    }else {
        profile.push({'login();':'登录'});
    }

    for (var i = 0; i < profile.length; i++) {
        var content = profile[i];
        for (var contentKey in content) {
            html += ' <li  onclick="'+contentKey+'">\n' +
                '            <a  href="javascript:void(0);" >' + content[contentKey] + '</a>\n' +
                '      </li>\n';
        }

        $("#profile-top").html("");
        $("#profile-top").html(html);
    }

});

function logout() {
    $.ajax({
        url: basePath + "/user/logout",
        data: {},
        type: "get",
        dataType: 'json',
        success: function (data) {
            window.location.href =basePath+"/admin/index";
        },
        error: function (data) {
            window.location.href =basePath+"/admin/index";
        }
    });
}

function login() {
    //定位到login页面
    window.location.href =basePath+"/admin/login";
}