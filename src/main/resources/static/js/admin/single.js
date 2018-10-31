$(document).ready(function () {
    initSingle(id);
    initComment(id);
    function initSingle(id) {
        var pageSize = 10;
        $.ajax({
            type: "GET", //提交方式
            url: basePath + "/blogarticle/"+id,//路径
            async: false,
            data: {
            },
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function (result) {//返回数据根据结果进行相应的处理
                if (result.status == 200) {
                    //加载数据
                    var data = result.result;
                    var content = data.content;
                    var html = '            <header class="entry-header">\n' +
                        '                        <h1 class="entry-title">\n' +
                        '                            <a >' + data.title + '</a>\n' +
                        '                        </h1>\n' +

                        '                        <div class="entry-meta">\n' +
                        // '                            <span class="post-category"><a href="#">' + data.blogLabel.lebelName + '</a></span>\n' +
                        '                            <span class="post-date"><a href="#"><time class="entry-date"\n' +
                        '                                                                     >' + gettime(data.createTime) + '</time></a></span>\n' +
                        // '                            <span class="post-author"><a href="#">' + data.author + '</a></span>\n' +
                        // '                            <span class="comments-link"><a href="#">' + data.link + ' 评论</a></span>\n' +
                        // '                            <span class="views-count"><a href="#">' + data.count + ' 阅读</a></span>\n' +
                        '                                    <a href="' + basePath +'/admin/write?id=' + data.id + '" class="Box-btn-octicon btn-octicon float-right" aria-label="Edit this file">' +
                        '                                         <svg class="octicon octicon-pencil" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">' +
                        '                                        <path fill-rule="evenodd" d="M0 12v3h3l8-8-3-3-8 8zm3 2H1v-2h1v1h1v1zm10.3-9.3L12 6 9 3l1.3-1.3a.996.996 0 0 1 1.41 0l1.59 1.59c.39.39.39 1.02 0 1.41z">' +
                        '                                        </path>' +
                        '                                       </svg>  <n></n>' +
                        '                                    </a>' +
                        '                        </div>\n' +
                        '                    </header>\n' +
                        '                    <div class="entry-content clearfix">\n' + content +
                        '                    </div>\n';
                    $("#content-body").html("");
                    $("#content-body").html(html);
                } else {
                    $("#content-body").html(result.message);
                }
            }
        });


    }

});

function  initComment(id) {
    $.ajax({
        type: "GET", //提交方式
        url: basePath + "/blogcommont",//路径
        async: false,
        data: {
            "sorts[0].column": "createTime",
            "sorts[0].value": "desc",
            "params[0].column": "deleted",
            "params[0].value": 0,
            "params[1].column": "articleId",
            "params[1].value": id,
            "pageNumber":0,
            "pageSize":10
        },
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            if (result.status == 200) {
                var data = result.result.data;
                var total = result.result.total;
                var html =  '<h3>评论列表，共 <span>'+total+'</span> 条评论</h3>\n';
                html += '<ul class="comment-list list-unstyled"> <ul class="comment-list list-unstyled">';
                for ( var i = 0; i <data.length; i++){
                    var user = data[i].username;
                    var createTime = gettime(data[i].createTime);
                    var content = data[i].content;
                    html +=  '  <li class="comment-item">\n' +
                        ' <span class="nickname">'+user+'</span>\n' +
                        '   <time class="submit-date"  datetime="2016-08-03">'+createTime+'</time>\n' +
                        '   <div class="text">\n' + content+
                        '   </div>' +
                        '  </li>';
                }
                html += '</ul>';
                $("#commont-body").html("");
                $("#commont-body").html(html);
            }
        }
    });
}
function pushComment(id){
    var val = $("#id_comment").val();
    if(!val){
        alert("不能为空");
    }
    $.ajax({
        type: "POST", //提交方式
        url: basePath + "/blogcommont/push",//路径
        async: false,
        data: {
            "commontContent":val,
            "articleId":id
        },
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            closeCommnotarea();
            initComment(id);
        },
        error:function (result) {
            alert("发生未知错误");
            closeCommnotarea()
            location.reload();
        }
    });

}
function openCommontarea(){
    $("#comment-area-push").removeClass("hidden");
    $("#comment-btn").addClass("hidden");
}

function closeCommnotarea(){
    $("#comment-area-push").addClass("hidden");
    $("#comment-btn").removeClass("hidden");
    $("#id_comment").val('');
}