$(document).ready(function () {
    initArchives(0);

    function initArchives(pageNumber) {
        var pageSize = 10;
        $.ajax({
            type: "GET", //提交方式
            url: basePath + "/blogarticle/groupbycreatetime",//路径
            async: false,
            data: {
                // "pageNumber": pageNumber,
                // "pageSize": pageSize
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
                            '            <a class="archives-el" href="javascript: void(0);" target="_blank" onclick="convertIndexArchives(\''+ content.createY + '-'+content.createM+ '\')">' + content.createY + '年'+content.createM+'月份  数量 '+content.count + '</a>\n' +
                            '      </li>\n';
                    }

                    $("#archives-body").html("");
                    $("#archives-body").html(html);
                } else {

                }
            }
        });
    }
});

function convertIndexArchives(createTime) {
    var archivesArr = $('.archives-el')

    for(var i=0;i < archivesArr.length;i++) {
        $(archivesArr[i]).on('click', function (e) {
            for (var j=0;j < archivesArr.length;j++) {
                $(archivesArr[j]).css('fontWeight', 'normal')
            }
            $(e.target).css('fontWeight', 'bold')
        })
    }

    initMainIndex();
}