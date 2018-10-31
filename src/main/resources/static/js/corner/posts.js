$(document).ready(function () {
    initIndex(0);

    function initIndex(pageNumber) {
        var pageSize = 10;
        $.ajax({
            type: "GET", //提交方式
            url: basePath + "/blogarticle",//路径
            async: false,
            data: {
                "pageNumber": 0,
                "pageSize": 4
            },//数据，这里使用的是Json格式进行传输
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function (result) {//返回数据根据结果进行相应的处理
                if (result.status == 200) {
                    //加载数据
                    var html = "";
                    var data = result.result.data;

                    for (var i = 0; i < data.length; i++) {
                        var content = data[i];
                        html += ' <li>\n' +
                            '            <a target="_blank" href="'+basePath+'/admin/single?id='+content.id+'" >' + content.title + '</a>\n' +
                            '      </li>\n';
                    }

                    $("#posts-body").html("");
                    $("#posts-body").html(html);
                } else {

                }
            }
        });
    }
});

