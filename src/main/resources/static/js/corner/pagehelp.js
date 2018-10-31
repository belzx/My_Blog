function locationPage(pageNum) {
    pageNumber = pageNum;
    initMainIndex();
}

function pluspage(num) {
    var localIndex = pageNumber+num;
    var pageTotal = Math.floor(dataTotal/pageSize);//获取总页数 从0开始
    if(localIndex <= pageTotal && localIndex >= 0){
        pageNumber = localIndex;
        initMainIndex();
    }
}

function initpagehelp() {
    var pageHelpColumnNum = 10;

    var pageTotal = Math.floor(dataTotal/pageSize);//获取总页数 从0开始
    var pageHelpColumnTotal  = Math.floor(pageTotal/pageHelpColumnNum)//获取总栏目数 从0开始
    var pageTailTotal = pageTotal%pageHelpColumnNum;//最后一栏的总页数 从0开始

    var pageColumnIndex =  Math.floor(pageNumber/pageHelpColumnNum);//获取当前为第几栏 从0开始
    var pageIndexActive = pageNumber%pageHelpColumnNum; //获取当前一栏第几个激活 从0开始

    var isEnd = false; //该激活的页面是否在最后一个栏目
    if(pageHelpColumnTotal == pageColumnIndex){
        isEnd = true;
    }

    //初始化数据
    var html =  '<li>\n' +
        '            <a href="javascript:void(0);" aria-label="Previous" onclick="pluspage(-1)">\n' +
        '                <span aria-hidden="true">&laquo;</span>\n' +
        '            </a>\n' +
        '        </li>\n';

    console.log(isEnd);
    // console.log(pageTotal);
    // console.log(pageHelpColumnTotal);
    console.log(pageTailTotal);

    if(isEnd){
        for(var i = 0  ; i <= pageTailTotal ; i ++){
            //获取当前坐标
            var index = pageHelpColumnNum * pageColumnIndex + i;
            if (pageIndexActive == i) {
                html += ' <li class="active"><a  class="page_a" href="javascript:void(0);" onclick="locationPage(' + index + ')">'+(index+1)+'</a></li>';
            } else {
                html += ' <li><a  class="page_a" href="javascript:void(0);" onclick="locationPage(' + index + ')">'+(index+1)+'</a></li>';
            }
        }
    }else {
        for(var i = 1  ; i <= initpagehelp ; i ++){
            if (pageIndexActive == i) {
                html += ' <li class="active"><a  class="page_a" href="javascript:void(0);" onclick="locationPage(' + index + ')">'+(index+1)+'</a></li>';
            } else {
                html += ' <li><a  class="page_a" href="javascript:void(0);" onclick="locationPage(' + index + ')">'+(index+1)+'</a></li>';
            }
        }
    }

    html +=  '        <li>\n' +
            '            <a  "href="javascript:void(0);" aria-label="Next" onclick="pluspage(1)">\n' +
            '                <span aria-hidden="true">&raquo;</span>\n' +
            '            </a>\n' +
            '        </li>';

    $("#page_nav").html("");
    $("#page_nav").html(html);
}



