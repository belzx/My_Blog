package com.lizhi.controller;

import com.lizhi.bean.*;
import com.lizhi.service.CustomService;
import com.lizhi.service.IBlogLabelService;
import com.lizhi.service.IBlogArticleService;
import com.lizhi.utils.MarkDownUtil;
import com.lizhi.utils.StringUtil;
import com.lizhi.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 */
@RestController
@RequestMapping("/blogarticle")
public class BlogArticleController implements CustomController<BlogArticle, String> {
    private static Logger log = LoggerFactory.getLogger(BlogArticleController.class);

    @Resource
    private IBlogArticleService singleService;

    @Resource
    private IBlogLabelService iBlogLabelService;

    @Override
    public IBlogArticleService getService() {
        return singleService;
    }

    @GetMapping(
            path = {"/{id}"}
    )
    @Override
    public ResponseMessage<BlogArticle> getByPrimaryKey(@PathVariable String id) {
        BlogArticle single = this.getService().selectByPK(id);
        if (single == null) {
            return ResponseMessage.error(1000, "未知错误");
        }
        single.setContent(MarkDownUtil.markDownToHtml(single.getContent()));
        String blogLabelId = single.getBlogLabelId();
        List<String> split = StringUtil.split(blogLabelId, ":");
        List<BlogLabel> blogLabels = new ArrayList<>();
        for(String str : split){
            blogLabels.addAll(iBlogLabelService.query(CURDParam.getInstans().where("id",str))) ;
        }
        single.setLabelList(blogLabels);
        return ResponseMessage.ok(single);
    }

    @GetMapping
    public ResponseMessage<PagerResult<BlogArticle>> list(CURDParam param) {
        Map<String, Object> params = getParams(param);
        if (params == null) {
            return ResponseMessage.ok(this.getService().selectByJoin((String) null, (String) null, param.getPageNumber(), param.getPageSize()));
        } else {
            return ResponseMessage.ok(this.getService().selectByJoin((String) params.get("createTimeByMonth"),
                    (String) params.get("parentId"), param.getPageNumber(), param.getPageSize()));
        }
    }

    @GetMapping(
            path = {"/groupbycreatetime"}
    )
    public ResponseMessage<Object> groupByCreateTime() {
        PagerResult<Map<String, Object>> mapPagerResult = singleService.selectByDateYM();
        ResponseMessage<Object> objectResponseMessage = new ResponseMessage<>();
        objectResponseMessage.setResult(mapPagerResult);
        objectResponseMessage.setStatus(200);
        return objectResponseMessage;
    }


    @GetMapping(
            path = {"/writeedit"}
    )
    public ResponseMessage<Object> writeedit(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return ResponseMessage.error(1000, "未知错误");
        }
        BlogArticle single = this.getService().selectByPK(id);
        if (single == null) {
            return ResponseMessage.error(1000, "未知错误");
        }

        User onlineUser = WebUtil.getOnlineUser();
        if (onlineUser == null) {
            return ResponseMessage.error(1000, "请重新登录");
        }

        if (!single.getAuthor().equals(onlineUser.getUsername())) {
            return ResponseMessage.error(1000, "编辑文章无权限");
        }

        return ResponseMessage.ok(single);
    }

//    @GetMapping(
//            path = {"/listByCrateTime"}
//    )
//    public ResponseMessage<Object> listByCrateTime(CURDParam curdParam,String createTime ) {
//        CustomParam param = new CustomParam("date_format(createTime, '%Y-%m')" , createTime);
//        param.setOriginalSql(true);
//        curdParam.where("date_format(createTime, '%Y-%m')" , createTime);
//        return ResponseMessage.ok(this.getService().selectPager(curdParam));
//    }

    @PostMapping(path = {"/save"})
    public ResponseMessage create(CURDParam curdParam, HttpServletRequest request) {
        ResponseMessage responseMessage = new ResponseMessage();
        Map<String, Object> params = getParams(curdParam);
        String title = (String) params.get("title");
        String text = (String) params.get("text");
        String blog = (String) params.get("blogId");
        String id = (String) params.get("id");
        String description = (String) params.get("description");

        if (StringUtil.isNullOrEmpty(title)) {
            responseMessage.setMessage("title不能为空");
            responseMessage.setStatus(1000);
            return responseMessage;
        }

        if (StringUtil.isNullOrEmpty(text)) {
            responseMessage.setMessage("内容不能为空");
            responseMessage.setStatus(1000);
            return responseMessage;
        }

        if (StringUtil.isNullOrEmpty(blog)) {
            responseMessage.setMessage("标签不能为空");
            responseMessage.setStatus(1000);
            return responseMessage;
        }

        if (StringUtil.isNullOrEmpty(description)) {
            responseMessage.setMessage("描述不能为空");
            responseMessage.setStatus(1000);
            return responseMessage;
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            responseMessage.setMessage("请重新登录");
            responseMessage.setStatus(1000);
            return responseMessage;
        }

        BlogArticle single = new BlogArticle();

        if (StringUtil.isNullOrEmpty(id)) {
            int count = this.getService().count(CURDParam.getInstans().where("title", title).where("author", user.getUsername()));
            if (count != 0) {
                responseMessage.setMessage("标题不能重复");
                responseMessage.setStatus(1000);
                return responseMessage;
            }
            single.setTitle(title);
            single.setLink(0);
            single.setCount(0);
            single.setContent(text);
            single.setBlogLabelId(blog);
            single.setAuthor(user.getUsername());
            single.setId(StringUtil.getUUID());
            single.setCreateTime(new Date());
            single.setDescription(description);

        } else {
            single = this.getService().selectByPK(id);
            if (single == null) {
                responseMessage.setMessage("发生未知错误，请重新刷新");
                responseMessage.setStatus(1000);
                return responseMessage;
            }

            if (!single.getTitle().equals(title)) {
                responseMessage.setMessage("标题不能修改");
                responseMessage.setStatus(1000);
                return responseMessage;
            }

            single.setContent(text);
            single.setContent(description);
            single.setContent(blog);
            single.setUpdateTime(new Date());
        }

        try {
            this.getService().saveOrUpdate(single);
        } catch (Exception e) {
            responseMessage.setStatus(1000);
            responseMessage.setMessage("发生未知异常");
            log.error("发生未知异常,single:{}", e, single.getId());
            return responseMessage;
        }

        responseMessage.setStatus(200);
        responseMessage.setMessage("保存成功");
        return responseMessage;
    }

}