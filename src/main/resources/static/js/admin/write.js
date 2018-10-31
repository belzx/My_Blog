$(document).ready(function () {
    if(id == ""){

    }else{ //验证权限

        $.ajax({
            type: "GET", //提交方式
            url: basePath + "/blogarticle/writeedit",//路径
            async: false,
            data: {
                "id": id,
            },//数据，这里使用的是Json格式进行传输
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            success: function (result) {//返回数据根据结果进行相应的处理
                //加载数据
                var data = result.result;
                console.log(data)
                if (result.status == 200) {
                    //加载数据
                    var data = result.result;
                    $("#t_blogLabelId").val(data.blogLabelId);
                    $("#t_title").val(data.title);
                    $("#t_description").val(data.description);

                    setTimeout(function () {
                        var a =document.getElementById('iframe').contentWindow.document.body.getElementsByClassName("markdown-textarea");
                        a[0].value = data.content;
                    }, 500)

                }else {
                    alert(result.message);
                }
            }
        });
    }
});


function saveblog(){
    var blogLabelId = $("#t_blogLabelId").val();
    var title = $("#t_title").val();
    var description = $("#t_description").val();
    var a =document.getElementById('iframe').contentWindow.document.body.getElementsByClassName("markdown-textarea");
    var content  = a[0].value  ;
    $.ajax({
        type: "POST", //提交方式
        url: basePath + "/single/save",//路径
        async: false,
        data: {
            "params[0].column": "title",
            "params[0].value": title,
            "params[1].column": "content",
            "params[1].value": content,
            "params[2].column": "blogLabelId",
            "params[2].value": blogLabelId,
            "params[3].column": "description",
            "params[3].value": description,
            "params[4].column": "id",
            "params[4].value": id
        },//数据，这里使用的是Json格式进行传输
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            alert("保存成功："+result.message);
        }
    });
}




