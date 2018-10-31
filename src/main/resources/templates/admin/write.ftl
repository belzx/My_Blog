<#include "/corner/header.ftl">
<link rel="stylesheet" href="${hosturl}/css/admin/write.css">
<#include "/corner/nav.ftl">
<div class="content-body">
    <div class="container">
        <div class="row">
        <#--<main class="col-md-8">-->
        <#--<button onclick="logout(${user.id})">退出登录</button>-->
        <#--</main>-->
            <label for="id_name">标题：</label>
            <input type="text" id="t_title" name="name" required>
        </div>
        <div class="row">
        <#--<main class="col-md-8">-->
        <#--<button onclick="logout(${user.id})">退出登录</button>-->
        <#--</main>-->
            <label for="id_name">描述：</label>
            <input type="text" id="t_description" name="name" required>
        </div>
        <div class="row upload-img">
            <label for="id_name">标签：</label>
            <input type="text" id="t_blogLabelId" list="itemlist" name="java">
            <datalist id="itemlist">
                <option>java</option>
                <option>javaScript</option>
                <option>mysql</option>
                <option>笔记</option>
            </datalist>
            <label >
                <button onclick="saveblog()">保存</button>
            </label>
        </div>
        <iframe src="${hosturl}/markdown/index.html"  id="iframe" width="98%" height="500" scrolling="yes">
        </iframe>
    </div>
</div>


<#include "/corner/footer.ftl">
<script src="${hosturl}/js/admin/write.js"></script>
<script>
    var id = "${id}";
</script>



