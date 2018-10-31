<#include "/corner/header.ftl">
<link rel="stylesheet" href="${hosturl}/css/admin/photo.css">
<#include "/corner/nav.ftl">
    <div class="content-body">
        <div class="container">
            <div class="wrap-image">
                <div>
                    <div></div>
                    <div class="wrap-input">
                        <input type="text">
                        <label>
                            <button id="uplodpicture">上传图片</button>
                        </label>
                        <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAVCAMAAABxCz6aAAAAclBMVEUAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9eWEHEAAAAJXRSTlMAXmVDAvvo3cDi0Ly4mgbrpCEdGce1n5R+dG1WThMODO2EgTowVKGoPwAAAKdJREFUGNN1zukOgyAQBGAUOQTU1vu217z/KzaEgDSx82eSLwu7xOaY9QOVng9yJqngwpNgT8AsG9kWA7z8HJASlxRws3tlLSjfbU/oyJkOky2JNcIV0hZHE2GD3BZDGWEJZitH/TPJbRkkESYw7godoXb3lQJFsALCbaAMRTBG/T8M7Ug/dGwBmflHbwUXxdEHzegglRwouee4eQ35p+JKa4GeXKj6AvRZDUCS0srCAAAAAElFTkSuQmCC"/>
                    </div>
                    <div></div>
                </div>

            </div>

            <ul class="wrap-list-image">

            </ul>
            <div class="wrap-pagenation">
                <#include "/corner/pagehelp.ftl">
            </div>

        </div>
    </div>

<!--实现背景变灰，弹出上传照片的框-->
	<div class="modal-backdrop-picture"></div>
	<div class="modal-box ">
        <form action="#" id="upload" method="post" enctype="multipart/form-data">
            <div class="box-title ">
                <input type="file" class="btn3 center"" name="file" value="请上传图片" />
                <input type="button" class="btn3" id="uploadfile" value="上传"/>
                <input href="javascript:void(0);" type="button" class="btn3" id="box-btn-sure" value="取消">
            </div>
        </form>
    </div>

<#include "/corner/footer.ftl">
<script src="${hosturl}/js/admin/photo.js"></script>
<script src="${hosturl}/js/common/common.js"></script>
<script src="${hosturl}/js/corner/pagehelp.js"></script>