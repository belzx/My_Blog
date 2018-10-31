var wrapInput = document.getElementsByClassName('wrap-input')[0]
var searchInput = wrapInput.getElementsByTagName('input')[0]
var searchImg = wrapInput.getElementsByTagName('img')[0]
var wrapListImage = document.getElementsByClassName('wrap-list-image')[0]
$(function () {

    $("#uplodpicture").click(function () {
        $(".modal-backdrop-picture").css("display", "block");
        $(".modal-box").css("display", "block");
    });
    $("#box-btn-sure").on("click", function(){
        $(".modal-box").css("display", "none");
        $(".modal-backdrop-picture").css("display", "none");
    });

    $("#uploadfile").click(function () {
        var formData = new FormData($('#upload')[0]);
        var url = basePath +"/file/uploadFile"

        $.ajax({
            type: 'post',
            url: url,
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (result) {
                alert(result.message);
                window.location.reload(true);
                location.reload();
                //back
                $(".modal-box").css("display", "none");
                $(".modal-backdrop-picture").css("display", "none");
            },
            error : function() {
                alert("异常！");
                //back
                $(".modal-box").css("display", "none");
                $(".modal-backdrop-picture").css("display", "none");
            }
        });
    });

    //加载首页
    initMainIndex();
})

/**用于分页展示*/
var pageNumber = 0; // 当前第几页
var dataTotal = 0;//总数据量
var pageSize = 12;//一页数目大小

function initMainIndex() {
    var path = "/file";
    var params = {
        "pageNumber": pageNumber,
        "pageSize": pageSize
    };
    $.ajax({
        type: "GET", //提交方式
        url: basePath + path,//路径
        async: false,
        data: params,//数据，这里使用的是Json格式进行传输
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            if (result.status == 200) {
                wrapListImage.innerHTML = "";

                dataTotal = result.result.total;//统计数据大小

                //初始页面
                initImage(result.result.data);

                //加载选择页面标签
                initpagehelp();
            }else {
                alert(result.message);
            }
        },
        error:function (result) {
            alert("修改失败");
        }
    });
}

function initImage(arr) {

    var allLi = ''
    for (var i = 0; i < arr.length; i++) {
        allLi += '<li id="' + arr[i].id + '">' + '<img class="personal-photo" src="' + arr[i].fileUrl + '" />' + '<span><svg t="1540312666729" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1819" xmlns:xlink="http://www.w3.org/1999/xlink" width="200" height="200"><defs><style type="text/css"></style></defs><path d="M626.34496 121.8304c0 53.15072-43.07968 96.2304-96.2304 96.2304-53.1456 0-96.2304-43.07968-96.2304-96.2304C433.88928 68.67968 476.96896 25.6 530.11456 25.6c53.1456 0 96.2304 43.0848 96.2304 96.2304zM530.11456 833.9968c-46.50496 0-84.1984 37.69856-84.1984 84.1984s37.69856 84.1984 84.1984 84.1984 84.1984-37.69856 84.1984-84.1984-37.69344-84.1984-84.1984-84.1984z m398.18752-253.83936c-33.21856 0-60.14464-26.92096-60.14464-60.14464 0-33.21856 26.92608-60.14464 60.14464-60.14464 33.22368 0 60.14464 26.92608 60.14464 60.14464-0.00512 33.21856-26.9312 60.14464-60.14464 60.14464zM228.15744 520.0128c0-53.1456-43.07968-96.2304-96.2304-96.2304-53.1456 0-96.2304 43.07968-96.2304 96.2304 0 53.1456 43.07968 96.2304 96.2304 96.2304 53.15072 0 96.2304-43.0848 96.2304-96.2304z m88.448-349.59872c37.5808 37.5808 37.5808 98.5088 0 136.08448-37.5808 37.5808-98.5088 37.5808-136.0896 0s-37.5808-98.5088 0-136.0896 98.5088-37.5808 136.0896 0.00512z m444.03712 580.12672c-28.1856 28.1856-28.1856 73.8816-0.00512 102.0672 28.1856 28.1856 73.8816 28.1856 102.0672 0 28.1856-28.1856 28.1856-73.8816 0-102.0672-28.18048-28.19072-73.87648-28.19072-102.06208 0z m85.05856-478.06464c-18.7904 18.7904-49.25952 18.7904-68.03968 0-18.79552-18.79552-18.79552-49.25952 0-68.0448a48.09728 48.09728 0 0 1 68.03968 0c18.7904 18.7904 18.7904 49.25952 0 68.0448zM316.60544 733.52704c-37.5808-37.5808-98.5088-37.5808-136.0896 0s-37.5808 98.5088 0 136.08448c37.5808 37.5808 98.5088 37.5808 136.08448 0 37.5808-37.57568 37.5808-98.50368 0.00512-136.08448z" fill="#ffffff" p-id="1820"></path></svg></span>' + '<svg t="1540311433359" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1058" xmlns:xlink="http://www.w3.org/1999/xlink" width="200" height="200"><defs><style type="text/css"></style></defs><path d="M573.013333 725.333333V389.973333a30.72 30.72 0 0 1 61.44 0V725.333333a30.72 30.72 0 0 1-61.44 0m-183.466666 0V389.973333a30.72 30.72 0 0 1 61.44 0V725.333333a30.72 30.72 0 0 1-61.44 0M909.226667 237.653333h-152.746667V176.64A91.306667 91.306667 0 0 0 665.6 85.333333H359.253333a91.306667 91.306667 0 0 0-91.733333 91.306667v61.013333H114.773333a30.72 30.72 0 0 0 0 61.013334h794.453334a30.72 30.72 0 0 0 0-61.013334M328.533333 176.64a31.146667 31.146667 0 0 1 30.72-30.293333h306.346667a29.44 29.44 0 0 1 29.866667 30.293333v61.013333H328.533333V176.64M725.333333 938.666667H298.666667a91.306667 91.306667 0 0 1-91.733334-91.306667V389.973333a29.866667 29.866667 0 0 1 30.293334-30.293333 30.293333 30.293333 0 0 1 30.72 30.293333v457.386667a31.146667 31.146667 0 0 0 30.72 30.293333h426.666666a31.146667 31.146667 0 0 0 30.72-30.293333V391.253333a30.72 30.72 0 0 1 61.013334 0v456.106667A91.306667 91.306667 0 0 1 725.333333 938.666667z" p-id="1059" fill="#ffffff"></path></svg>' + '</li>'
    }
    wrapListImage.innerHTML = allLi

    setTimeout(function () {
        $('.personal-photo').on('click', function (e) {
            console.log($(e.target).attr('src'))
        })
    }, 300)

// 点击删除图片
    wrapListImage.addEventListener('click', function (e) {

        if (e.target.matches('svg')) {
            var target = e.target
            var loadingSvg = e.target.parentNode.childNodes[1].childNodes[0]
            var id = ''
            // 图片对应id
            var targetWithId = target

            while (!targetWithId.getAttribute('id')) {
                targetWithId = targetWithId.parentNode
            }

            loadingSvg.style.display = 'block'
            loadingSvg.style.transform = 'translateX(-50%) translateY(-50%) rotate(360deg)'

            // 获取图片id
            var id = targetWithId.getAttribute('id');

            request("/file/"+id,null,"delete",function (result) {
                alert("修改成功");
                initMainIndex();
            },function (result) {
                alert("修改失败");
            });
        }

    })
}

window.addEventListener('keydown', function (e) {
    if (e.key === 'Enter') {
        alert(123)
    }
})

// 点击搜索
searchImg.addEventListener('click', function (e) {
    console.log('input value', searchInput.value)
    searchInput.value = ''
})

// xss csrf
/*
  xss一般指提交执行恶意的js代码
  一般解决方案是过滤掉特殊字符
  内容安全策略，禁止执行一些特殊的方法如eval new function

  csrf
  web的身份验证机制可以保证一个请求来自于某个浏览器
  但无法保证请求是用户批准发送

  浏览器事件轮训、node事件轮训的区别
  微任务、宏任务都是属于异步任务中的一种

  flex布局的使用，居中的方案

  虚拟dom的具体细节

  setstate具体做了什么

  箭头函数和普通函数的区别

  闭包

  webpack的性能优化

  正则的使用

  事件委托

  node中间件与redux中间件的区别

  react双向数据绑定和vue的双向数据绑定的区别

  react和vue的区别

  canvas和svg的区别

  canvas一般通过js实现，可以引入图片，适合需要动态渲染的场景
  svg一般通过标签实现，不可引入图片，一般做一些小图标使用svg，svg不失真，放大缩小都很清晰

  http缓存细节

  cdn除了缓存还做了什么？

  浏览器事件循环 微任务和宏任务

  定时器是在设置的时间后将任务push进队列


*/

