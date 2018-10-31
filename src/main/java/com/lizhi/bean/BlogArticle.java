package com.lizhi.bean;

import java.util.Date;
import java.util.List;

/**
 * 博客单页的信息
 */
public class BlogArticle extends CustomEntity<String>  {

    //标题
    private String title;

    //创建时间
    private Date createTime;

    //修改时间
    private  Date updateTime;

    //作者
    private  String author;

    //评论次数
    private Integer link;

    //阅读量
    private  Integer count;

    //内容
    private  String content;

    //文章描述
    private  String description;

    //是否删除1:删除  0：没有删除
    private  Integer deleted;

    //文章标签
    private  String blogLabelId ;

    //标签名
    private  BlogLabel blogLabel ;

    private List<BlogLabel> labelList;

    public List<BlogLabel> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<BlogLabel> labelList) {
        this.labelList = labelList;
    }

    public BlogLabel getBlogLabel() {
        return blogLabel;
    }

    public void setBlogLabel(BlogLabel blogLabel) {
        this.blogLabel = blogLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getBlogLabelId() {
        return blogLabelId;
    }

    public void setBlogLabelId(String blogLabelId) {
        this.blogLabelId = blogLabelId;
    }
}
