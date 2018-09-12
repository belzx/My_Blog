<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>apple in your eyes</title>
    <link rel="icon" type="image/icon" href="assets/images/tabicon.ico">

    <link rel="stylesheet" type="text/css" href="">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,700,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Crimson+Text:400,700,700i|Josefin+Sans:700" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">
    <link rel="icon" href="assets/images/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">

</head>

<body>
	<!--实现背景变灰，弹出上传照片得狂-->
	<div class="modal-backdrop-picture"></div>
	<div class="modal-box ">
		<form action="#" id="upload"  method="post" enctype="multipart/form-data">
			<div class="box-title ">
			        <input type="file" class="btn3 center"" name="file" value="请上传图片" />
			        <input type="button" class="btn3"  id="uploadfile" value="上传"/>
			        <input href="javascript:void(0);" type="button"  class="btn3" id="box-btn-sure" value = "取消">
			</div>
		</form>
	</div>

    <div id="index">
        <!-- Index starts here -->
        <div class="container main">

            <div class="row home absol">
                <div class="logintop">
                    <div class="login">
                  		  <#if user.username=="">
							<a href="/admin/login">
	                       		 <span id="#" class="btn2 btn-rabbit" dispaly="inline-block">
	                       		 	登录
	                       		 </span>
	                       		 
                       		</a>
                       		<a href="/admin/register">
	                       		 <span id="#" class="btn2 btn-rabbit" dispaly="inline-block">
	                       		 	注册
	                       		 </span>
	                       		 
                       		</a>
						  <#else>
						    <a href="#">
                        		<span id="#" class="btn2 btn-rabbit" dispaly="inline-block">${user.username}</span>
                        	</a>     
                        	<a href="/admin/logout?username=${user.username}">
                        		<span id="#" class="btn2 btn-rabbit" dispaly="inline-block">注销</span>
                        	</a> 
						  </#if>
                    </div>
                </div>
                <div id="index_left " class="col-md-6 left uplodpicture">
                	<#if user.ico=="">
                    	<img id="picico" class="img-responsive img-rabbit" src="assets/images/home.jpg">
                 	<#else>
                  		 <img id="picico" class="img-responsive img-rabbit" src="${user.ico}">
                    </#if>
                </div>
                <div id="index_right" class="col-md-6 text-center right">

                    <div class="logo">
                        <img src="assets/images/logo.png">
                        <h4>Hi Rabbit</h4>
                    </div>
                    <p class="home-description">
                        		这个是我的小屋
                    </p>
                    <div class="btn-group-vertical custom_btn animated slideinright">
                        <div id="about" class="btn btn-rabbit">关于</div>
                        <div id="work" class="btn btn-rabbit">工作</div>
                        <div id="contact" class="btn btn-rabbit">链接</div>
                        <div id="clock" class="btn btn-rabbit">看钟</div>
                        <div id="websocked" class="btn btn-rabbit">聊天室</div>
                    </div>
                    <div class="social">
                        <a href="#">
                            <i class="fa fa-pinterest" aria-hidden="true"></i>
                        </a>
                        <a href="#">
                            <i class="fa fa-twitter" aria-hidden="true"></i>
                        </a>
                        <a href="#">
                            <i class="fa fa-linkedin" aria-hidden="true"></i>
                        </a>
                    </div>
                </div>
            </div>


        </div>
    </div>
    <!-- index ends here -->

    <div id="about_scroll" class="pages ">
        <!-- about strats here  -->
        <div class="container main">
            <div class="row">
                <div class="col-md-6 left " id="about_left ">
                    <img class="img-responsive img-rabbit" src="assets/images/about.jpg">
                </div>

                <div class="col-md-6 right" id="about_right">
                    <a href="#index" class="btn btn-rabbit back">
                        <i class="fa fa-angle-left" aria-hidden="true"></i> Back to home </a>
                    <div id="watermark">
                        <h2 class="page-title" text-center>About</h2>
                        <div class="marker">a</div>
                    </div>
                    <p class='subtitle'>Hi, I am chris Howard. A freelance web designer, front-end developer and digital planner based in US.
                    </p>
                    <p class="info">"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore
                        et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                        aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
                        dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
                        officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."</p>
                </div>
            </div>
        </div>
    </div>
    <!-- About ends here -->

    <div class="copyrights">Collect from
        <a href="http://www.cssmoban.com/">手机网站模板</a>
    </div>

    <div id="work_scroll" class="pages">
        <!-- Work starts here -->
        <div class="container main">
            <div class="row">
                <div class="col-md-6" id="work_left">
                    <div id="owl-demo" class="owl-carousel owl-theme">
                        <div class="item">
                            <img class="img-responsive img-rabbit" src="assets/images/work.jpg">
                        </div>
                        <div class="item">
                            <img class="img-responsive img-rabbit" src="assets/images/home.jpg">
                        </div>
                        <div class="item">
                            <img class="img-responsive img-rabbit" src="assets/images/contact.jpg">
                        </div>
                    </div>
                </div>

                <div class="col-md-6" id="work_right">
                    <a href="#index" class="btn btn-rabbit back">
                        <i class="fa fa-angle-left" aria-hidden="true"></i> Back to Home </a>
                    <div id="watermark">
                        <h2 class="page-title" text-center>Work</h2>
                        <div class="marker">w</div>
                    </div>
                    <p class='subtitle'>This is a selection of my web design and development work. I've been involve in many different types
                        of project.
                    </p>
                    <p class="info">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration
                        in some form, by injected humour, or randomised words which don't look even slightly believable.
                        If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing
                        hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined
                        chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of
                        over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum
                        which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected
                        humour, or non-characteristic words etc.</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Work ends here  -->


    <div id="contact_scroll" class="pages">
        <!-- Contact starts here -->
        <div class="container main">
            <div class="row">
                <div class="col-md-6 left" id="contact_left">
                    <img class="img-responsive img-rabbit" src="assets/images/contact.jpg">
                </div>

                <div class="col-md-6 right" id="contact_right">
                    <a href="#index" class="btn btn-rabbit back">
                        <i class="fa fa-angle-left" aria-hidden="true"></i> Back to Home </a>
                    <div id="watermark">
                        <h2 class="page-title" text-center>Contact</h2>
                        <div class="marker">c</div>
                    </div>
                    <p class='subtitle'>I'm based in cheltenham in the UK, not far from Bristol, Birmingham, Oxford and Swindom. Drop me a line.
                    </p>
                    <!-- form -->
                    <form class="form_edit">
                        <div class="form-group">
                            <input type="name" class="form-control" id="exampleInputName" placeholder="Name">
                        </div>

                        <div class="form-group">
                            <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
                        </div>

                        <div class="form-group">
                            <textarea class="form-control" rows="5" placeholder="Message"></textarea>
                        </div>
                        <button type="submit" class="btn btn-rabbit submit">Send Message</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
        <!-- contact_scroll ends here  -->


        <div id="websocked_scroll" class="pages">
            <!-- Contact starts here -->
            <div class="container main">
                <div class="row">
                    <div class="col-md-6 left" id="websocked_left">
                   			 <a href="#index" class="btn btn-rabbit back ">
                       	 			<i class="fa fa-angle-left" aria-hidden="true"></i> Back to Home 
                       		 </a>
                    		<div class="message">
			                    <ul class="list-group" id="message"></ul>
			                </div>
                    </div>

                    <div class="col-md-6 right" id="websocked_right">
                    	
			            <form>
			                <div class="form-group">
			                    <label for="comment"><h3>聊天框:${user.username}</h3></label> <textarea class="form-control" rows="5" id="text">你好啊</textarea>
			                    <input type="hidden"  id="userName" value="${user.username}"  />
			                </div>
			                <div class="form-group">
			                    <button onclick="send()" type="button" class="btn btn-primary">发送</button>
			                    <button onclick="closeWebSocket()" type="button" class="btn btn-danger">关闭</button>
			                    <button onclick="javascript:window.location.href='/'" type="button" class="btn btn-danger">登陆</button>
			                </div>
			            </form>
                    
                        
                        <div id="watermark">
                            <h2 class="page-title" text-center>Contact</h2>
                            <div class="marker">c</div>
                        </div>
                       	<p class='subtitle'>The Message will send to 401360313@qq.com </p>
                        <!-- form -->
                        <form class="form_edit">
                            <!--<div class="form-group">
                                <input type="name" class="form-control" id="exampleInputName" placeholder="Name">
                            </div>
                            -->
                            <div class="form-group">
                                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" value="401360313@qq.com" readonly="true">
                            </div>

                            <div class="form-group">
                                <textarea class="form-control" rows="5" placeholder="Message"></textarea>
                            </div>
                            <button type="submit" class="btn btn-rabbit submit">Send Message</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
            <!-- websocked ends here  -->


            <!-- Contact ends here -->

            <script src="assets/js/jquery-3.1.0.min.js"></script>
            <script src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/script.js"></script>
             <script src="assets/js/picture.js"></script>
</body>

<script type="text/javascript">
    var websocket = null;
	console.log("${Hosturl}");
	Hosturl=${Hosturl};
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://${Hosturl}/websocket");
        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("与服务器连接失败...");
        };

        //连接成功建立的回调方法
        websocket.onopen = function (event) {
            setMessageInnerHTML("与服务器连接成功...");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("已关闭当前链接");
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            websocket.close();
        }
    } else {
        alert('Not support websocket');
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        $("#message").append("<li class=\"list-group-item\">" + innerHTML + "</li>");
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
   		var name = $("#userName").val();
   		if(!name){
   			window.location.href="/admin/login";
   		}
        websocket.send(name+":"+$("#text").val());
    }
</script>
</html>