package com.lizhi.controller;

import com.lizhi.bean.*;
import com.lizhi.service.CustomService;
import com.lizhi.service.IBlogCommontService;
import com.lizhi.service.IBlogArticleService;
import com.lizhi.utils.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 评论
 */
@RestController
@RequestMapping("/blogcommont")
public class BlogCommontController implements CustomController<BlogCommont,String> {

    @Resource
    private IBlogCommontService commontService;

    @Resource
    private IBlogArticleService iSingleService;

    @Override
    public CustomService<BlogCommont, String> getService() {
        return commontService;
    }

    @PostMapping(
            path = {"/push"}
    )
    public ResponseMessage<BlogCommont> push(HttpServletRequest request , String commontContent, String articleId) {
        BlogCommont commont = new BlogCommont();
        User user = (User)request.getSession().getAttribute("user");
        commont.setId(StringUtil.getUUID());
        commont.setContent(commontContent);
        commont.setArticleId(articleId);
        commont.setCreateTime(new Date());
        if(user == null){
            commont.setUserid(BlogCommont.NONE_NAME_USERID);
            commont.setUsername(BlogCommont.NONE_NAME);
        }else {
            commont.setUserid(user.getId());
            commont.setUsername(user.getUsername());
        }
        getService().insert(commont);
        BlogArticle single = iSingleService.selectByPK(articleId);
        single.setLink(single.getLink() + 1);
        iSingleService.update(single);
        return ResponseMessage.ok();
    }
}
