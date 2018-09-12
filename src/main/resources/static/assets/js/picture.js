$(document).ready(function () {
    $(".uplodpicture").click(function () {
        $(".modal-backdrop-picture").css("display", "block");
		$(".modal-box").css("display", "block");
    });
    
    $("#box-btn-sure").on("click", function(){
			$(".modal-box").css("display", "none");
			$(".modal-backdrop-picture").css("display", "none");
	});

    $("#uploadfile").click(function () {
            var formData = new FormData($('#upload')[0]);
            console.log("111.231.246.11:8080");
            var url = "http://111.231.246.11:8080/file/uploadIco"

            console.log(formData);
            console.log(url);
            $.ajax({
                type: 'post',
                url: url,
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.code == 200) {
                        window.location.reload(true);
                        location.reload();
                        //back
                        $(".modal-box").css("display", "none");
			            $(".modal-backdrop-picture").css("display", "none");
                    };
                },
                error : function() {
                    alert("异常！");
                    //back
                        $(".modal-box").css("display", "none");
			            $(".modal-backdrop-picture").css("display", "none");
                }
            });
    });
});