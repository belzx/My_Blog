$(document).ready(function () {
    initIndex(0);
    function initIndex(pageNumber) {
        var pageSize = 10;
        $.ajax({
            type: "GET", //提交方式
            url: basePath + "/bloglabel",//路径
            async: false,
            data: {
                "params[0].column": "parentId",
                "params[0].value": 0
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
                            '            <a class="tag-cloud-li tag_gray" href="javascript: void(0);" onclick="convertIndex(\''+ content.id + '\')" target="_blank">' + content.lebelName + '</a>\n' +
                            '      </li>\n';
                    }

                    $("#blog-body").html("");
                    $("#blog-body").html(html);
                } else {

                }
            }
        });


    }

    $('#blog-body').on('click', function (e) {
        var parent = document.getElementById('blog-body')
        var children = parent.getElementsByTagName('li')

        for(var i=0;i<children.length;i++) {
            var sunzi = children[i].getElementsByTagName('a')[0]
            sunzi.style.color = '#ccc'
            sunzi.style.fontWeight = 'normal'
        }

        $(e.target).css({ fontWeight: 'bold', color: 'black' })

    })
});

function convertIndex(blogid) {
    parentId = blogid;
    initMainIndex();
}