$(document).ready(function () {
    initMainIndex();
});

var parentId = "";
var pageNumber = 0; // 当前第几页
var createTimeByMonth = "";
var dataTotal = 0;//总数据量
var pageSize = 8;

function initMainIndex() {
    var params = {
        "params[1].column": "parentId",
        "params[1].value" : parentId,
        "params[2].column" : "createTimeByMonth",
        "params[2].value" : createTimeByMonth,
        "pageNumber": pageNumber,
        "pageSize": pageSize
    };

    $.ajax({
        type: "GET", //提交方式
        url: basePath + "/blogarticle",//路径
        async: false,
        data: params,//数据，这里使用的是Json格式进行传输
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        success: function (result) {//返回数据根据结果进行相应的处理
            if (result.status == 200) {
                //加载数据
                var html = "";
                var data = result.result.data;
                dataTotal = result.result.total;
                for (var i = 0; i < data.length; i++) {
                    var description = data[i].description;
                    html += ' <article class="post">\n' +
                        '                    <header class="entry-header">\n' +
                        '                        <h1 class="entry-title">\n' +
                        '                            <a target="_blank" href="' + basePath + '/admin/single?id=' + data[i].id + '">' + data[i].title + '</a>\n' +
                        '                        </h1>\n' +
                        '                        <div class="entry-meta">\n' +
                        '                            <span class="post-category">' + data[i].blogLabel.lebelName + '</span>\n' +
                        '                            <span class="post-date"><a href="#"><time class="entry-date"\n' +
                        '                                                                     >' + gettime(data[i].createTime) + '</time></a></span>\n' +
                        '                            <span class="post-author"><a href="#">' + data[i].author + '</a></span>\n' +

                        '                            <span class="views-eedit">' +

                        '                             </span>' +
                        '                        </div>\n' +
                        '                    </header>\n' +
                        '                    <div class="entry-content clearfix">\n' +
                        '                        <p>' + description + '</p>\n' +
                        // '                        <div class="read-more cl-effect-14">\n' +
                        //     '                            <a class="test"  href="' + basePath + '/admin/single?id=' + data[i].id + '"  target="_blank" class="more-link">继续阅读 <span class="meta-nav">→</span></a>\n' +
                        // '                        </div>\n' +
                        '                    </div>\n' +
                        '                            <span class="comments-link"><a href="#">' + data[i].link + ' <svg t="1540561093953" class="icon" style="position: relative;top: 3px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1566" xmlns:xlink="http://www.w3.org/1999/xlink" width="16" height="16"><defs><style type="text/css"> </style></defs><path d="M847.36 107.52h-665.6c-69.12 0-125.44 56.32-125.44 125.44v401.92c0 69.12 56.32 125.44 125.44 125.44h38.4l15.36 181.76 158.72-181.76h453.12c69.12 0 125.44-56.32 125.44-125.44V232.96c0-69.12-56.32-125.44-125.44-125.44z m-563.2 376.32c-33.28 0-61.44-28.16-61.44-61.44 0-33.28 28.16-61.44 61.44-61.44 33.28 0 61.44 28.16 61.44 61.44 2.56 33.28-25.6 61.44-61.44 61.44z m230.4 0c-33.28 0-61.44-28.16-61.44-61.44 0-33.28 28.16-61.44 61.44-61.44 33.28 0 61.44 28.16 61.44 61.44 0 33.28-28.16 61.44-61.44 61.44z m227.84 0c-33.28 0-61.44-28.16-61.44-61.44 0-33.28 28.16-61.44 61.44-61.44 33.28 0 61.44 28.16 61.44 61.44 2.56 33.28-25.6 61.44-61.44 61.44z" fill="" p-id="1567"></path></svg></a></span>\n' +
                        '                            <span class="views-count"><a href="#">' + data[i].count + ' <svg t="1540561228795" class="icon" style="position: relative;top: 3px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2357" xmlns:xlink="http://www.w3.org/1999/xlink" width="16" height="16"><defs><style type="text/css"></style></defs><path d="M972.3392 450.7648C964.096 439.3216 766.1312 170.3424 512 170.3424c-254.1056 0-452.0704 269.0048-460.3648 280.4224-11.1104 15.3088-11.1104 36.0192 0 51.328C59.9296 513.6128 257.92 782.5408 512 782.5408c254.1056 0 452.096-269.0048 460.3392-280.448C983.4752 486.784 983.4752 466.0224 972.3392 450.7648zM512 697.0624c-189.952 0-372.3776-218.6496-372.3776-218.6496s9.4208-10.88 25.9584-27.8528c43.0848-47.0784 115.0208-115.3536 201.3696-156.8256 46.8736-24.7552 96.7168-41.8816 145.0752-41.8816 47.7952 0 97.0496 16.7424 143.488 41.0624 87.3728 41.5488 160.2304 110.8992 203.4688 158.2592 16.2048 16.5888 25.4208 27.2384 25.4208 27.2384S701.952 697.0624 512 697.0624z" p-id="2358"></path><path d="M507.9552 252.7744c-123.3152 0-223.6672 100.352-223.6672 223.6672 0 123.3152 100.352 223.7184 223.6672 223.7184 123.3408 0 223.6928-100.4032 223.6928-223.7184C731.648 353.1264 631.296 252.7744 507.9552 252.7744zM432.2048 500.0448c5.248 21.5808-7.936 43.1616-29.4656 48.4096-3.1744 0.7936-6.3488 1.0496-9.472 1.0496-18.0736 0-34.432-12.2624-38.8864-30.5408-17.8688-73.5232 4.9664-121.856 27.2384-150.1696 30.4896-38.7072 77.8752-58.752 107.0336-58.752 0.0256 0 0.0256 0 0.0256 0 22.0928 0 40.0896 17.152 40.0384 39.2704-0.0512 21.9136-17.6896 39.2704-39.4752 39.6544-7.0656 0.7168-31.36 9.7536-47.0272 31.8208C427.776 441.2416 424.3968 467.8656 432.2048 500.0448zM592.5376 509.3888c27.776 0 50.2784 22.5536 50.2784 50.3552 0 27.7504-22.5792 50.2528-50.2784 50.2528-27.8272 0-50.2784-22.4512-50.2784-50.2528S564.736 509.3888 592.5376 509.3888z" p-id="2359"></path></svg></a></span>\n' +

                        '</article>';
                }
                $("#content-body").html("");
                $("#content-body").html(html);

                //加载选择页面标签
                initpagehelp();
            } else {

            }
        }
    });
}
