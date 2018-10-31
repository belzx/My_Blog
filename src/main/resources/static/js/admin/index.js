$(function () {
    $('.carousel').carousel({
        interval: 5000
    })
    request("/index/sowingmap",null,"GET",function (result) {
        var carouselInner = document.getElementsByClassName('carousel-inner')[0]
        var imgList = carouselInner.getElementsByTagName('img')
        console.log('result', result);
        console.log('imgList:', imgList)

        for (var i=0;i<imgList.length;i++) {
            console.log(result.result[i].fileUrl)
            imgList[i].setAttribute('src', result.result[i].fileUrl)
        }

    },function (result) {

    })
})
