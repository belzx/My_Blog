function gettime(t){
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=_time.getMonth()+1;//7
    var   date=_time.getDate();//10
    var   hour=_time.getHours();//10
    var   minute=_time.getMinutes();//56
    var   second=_time.getSeconds();//15
    return   year+"年"+month+"月"+date+"日   "+hour+":"+minute+":"+second;//这里自己按自己需要的格式拼接
}

function request(path,params,requestType,successFn,errorFn) {
    $.ajax({
        type: requestType, //提交方式
        url: basePath + path,//路径
        async: false,
        data: params,//数据，这里使用的是Json格式进行传输
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            if(successFn){
                successFn(result);
            }
        },
        error:function (result) {
            if(errorFn){
                errorFn(result);
            }
        }
    });
}

