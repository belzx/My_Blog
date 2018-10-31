<#include "/corner/header.ftl">
<link rel="stylesheet" href="${hosturl}/css/login.css">
<#include "/corner/nav.ftl">
<div class="content-body">
    <div class="container">
        <div class="row backgroud_login_body" backgroudimage>
            <main class="col-md-24 inner_backgroud_login_body">
                <div class="wrap" >
                    <div class="wrap-h3">
                        <h3 class="account-login clo" style="color:#c70000;">账号登录</h3>
                        <h3 class="account-register">账号注册</h3>
                    </div>
                    <div class="wrap-login" >
                        <form class="register-form">
                            <input  class="input" id="loginusername"  placeholder="账号"></input>
                            <input class="input" id="loginpassword" type="password" placeholder="密码"></input>
                            <div class="login-message">短信验证码登录</div>
                            <div class="btn" id="login_button">登录</div>
                            <div class="wrap-bt">
                                <input type="checkbox"  id="loginremenber"  checked/>
                                <span class="checkbox-text">记住账号</span>
                                <span>更多</span>
                            </div>
                        </form>
                        <div class="forget-password">忘记密码</div>
                    </div>
                    <div class="wrap-register">
                        <div class="f-input">
                            <input type="text" id="registerusername" placeholder="账号">
                            <img  class="icon"  src="${hosturl}/img/login/zc_06.jpg" />
                        </div>
                        <div class="f-input">
                            <input type="text" id="registerpassword"  placeholder="密码">
                            <img  class="icon" src="${hosturl}/img/login/zc_16.jpg" />
                        </div>
                        <div class="f-input">
                            <input type="text"  id="registeremail" placeholder="邮箱">
                            <img  class="icon" src="${hosturl}/img/login/zc_12.jpg" />
                        </div>
                        <div class="f-input">
                            <input type="text" id="registerverification" placeholder="验证码">
                            <img  class="icon" src="${hosturl}/img/login/zc_19.jpg" />
                        </div>
                        <div class="btn" id="register_button">注册</div>
                    </div>
                </div>

                <#--<div class="login_kuang">-->
                    <#--<div class="wrap-header">-->
                        <#--<h3>登录</h3>-->
                        <#--<h3>注册</h3>-->
                    <#--</div>-->
                        <#--<form action="login" method="post" class="login-form">-->
                            <#--<input name="username" type="text" class="kuang_txt" placeholder="账号">-->
                            <#--<p></p>-->
                            <#--<input name="password" type="password" class="kuang_txt" placeholder="密码">-->
                            <#--<div>-->
                                <#--&lt;#&ndash;<a href="#">忘记密码？</a>&ndash;&gt;-->
                                <#--<input name="" type="checkbox" value="" checked><span>记住我</span>-->
                            <#--</div>-->
                            <#--<input name="登录"  class="btn_zhuce" id="login_button"  value="登录">-->
                        <#--</form>-->

                    <#--&lt;#&ndash;<form action="register" method="post" class="register-form">&ndash;&gt;-->
                        <#--&lt;#&ndash;<input name="username" type="text" class="kuang_txt" placeholder="账号">&ndash;&gt;-->
                        <#--&lt;#&ndash;<p></p>&ndash;&gt;-->
                        <#--&lt;#&ndash;<input name="password" type="password" class="kuang_txt" placeholder="密码">&ndash;&gt;-->
                        <#--&lt;#&ndash;<div>&ndash;&gt;-->
                        <#--&lt;#&ndash;</div>&ndash;&gt;-->
                        <#--&lt;#&ndash;<input name="注册"  class="btn_zhuce" id="login_button"  value="注册">&ndash;&gt;-->
                    <#--&lt;#&ndash;</form>&ndash;&gt;-->
                <#--</div>-->
            </main>
            <aside class="col-md-4">
            </aside>
        </div>
    </div>
</div>
 <#include "/corner/footer.ftl">
<script src="${hosturl}/js/admin/login.js"></script>


