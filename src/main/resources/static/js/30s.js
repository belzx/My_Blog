
$( document ).ready(function() {
    alert(1);
    var myspan = document.getElementById('myspan');
    var number = myspan.innerHTML;
    number--;
    document.getElementById('myspan').innerHTML = number;
    if (number == 0) {
        window.location.href = "index";
    }
    $("#prev").click(function(){
        window.location.href = "/index";
   });
    //设置定时器，调用函数
    setInterval("count()", 1000);


});