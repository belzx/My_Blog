package com.lizhi.bean;

import java.util.Date;

/**
 * 博客评论的信息
 */
public class BlogCommont extends CustomEntity<String>  {

    public static final String NONE_NAME="匿名";

    public static final String NONE_NAME_USERID="0";

    //创建时间
    private Date createTime;

    //创建人ID
    private  String userid;

    private  String articleId;

    //创建人名称
    private String username;

    //内容 248限制
    private  String content;

    //是否删除1:删除  0：没有删除
    private  int deleted = 0;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "BlogCommont{" +
                "createTime=" + createTime +
                ", userid='" + userid + '\'' +
                ", articleId='" + articleId + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
