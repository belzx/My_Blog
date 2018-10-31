<#include "/corner/header.ftl">
<#include "/corner/nav.ftl">
<div class="content-body">
    <div class="container">
        <div class="row">
            <main class="col-md-8 " id="content-body">
                <!-- 简单分页效果
                <div class="pagination-simple">
                    <a href="#">上一页</a>
                    <span class="current">第 6 页 / 共 11 页</span>
                    <a href="#">下一页</a>
                </div>
                -->
                <!--分页 -->
            </main>
            <aside class="col-md-4 aside-right" >
                 <#include "/corner/posts.ftl">
                <#include "/corner/archives.ftl">
                <#--<#include "/corner/category.ftl">-->
                <#include "/corner/tag-cloud.ftl">
                <#include "/corner/rss.ftl">
            </aside>
        </div>
        <#include "/corner/pagehelp.ftl">
    </div>
</div>
 <#include "/corner/footer.ftl">
<script src="${hosturl}/js/admin/blog.js"></script>
<script src="${hosturl}/js/common/common.js"></script>
<script src="${hosturl}/js/corner/pagehelp.js"></script>
