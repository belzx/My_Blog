<#include "/corner/header.ftl">
<link rel="stylesheet" href="${hosturl}/css/admin/single.css">
<#include "/corner/nav.ftl">
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-12">
                <article class="post post-1" id="content-body">
                    <!--内容 -->
                </article>
                <section class="comment-area" id="comment-area">
                    <hr>
                    <form action="#"  class="comment-form">
                        <div class="row">
                            <div class=" unlogin-box text-center " id="comment-btn">
                                想对作者说点什么？
                                <!-- $curl 当前地址 -->
                                <a href="javascript: void(0);" onclick="openCommontarea()" class="btn btn-sm btn-red">我来说一句</a>
                            <#--<label for="id_name">名字：</label>-->
                            <#--<input type="text" id="id_name" name="name" required>-->
                            </div>

                            <div class="col-md-12 hidden" id="comment-area-push">
                                <label for="id_comment">评论：</label>
                                <textarea name="comment" id="id_comment" required></textarea>
                                <button type="button" class="comment-btn" onclick="pushComment('${id}')">发表</button>
                            </div>
                        </div>    <!-- row -->
                    </form>
                    <div class="comment-list-panel" id="commont-body">
                        <!-- 评论 -->
                    </div>
                </section>
            </main>
            <#--<aside class="col-md-4">-->
                <#--<#include "/corner/posts.ftl">-->
                <#--<#include "/corner/archives.ftl">-->
                <#--<#include "/corner/category.ftl">-->
                <#--<#include "/corner/tag-cloud.ftl">-->
                <#--<#include "/corner/rss.ftl">-->
            <#--</aside>-->
        </div>
    </div>
</div>
 <#include "/corner/footer.ftl">
<script src="${hosturl}/js/admin/single.js"></script>
<script>
    var id = "${id}";
</script>

